package App.Infrastructure;

import App.Domain.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RecipeRepository implements IRepository<RecipeDetailed> {

    private JdbcTemplate databaseConnection;

    @Override
    public void setDatabaseConnection(JdbcTemplate databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    @Override
    public RecipeDetailed create(RecipeDetailed recipe){
        // Assumes related entities (ingredients, implements, steps, etc.) are already created
        // Insert to COMPONENT
        String sql = STR."INSERT INTO COMPONENT VALUES (\{recipe.getId()}, '\{recipe.getName()}', 'recipe');";
        this.databaseConnection.execute(sql);
        // Insert to RECIPE
        sql = STR."INSERT INTO RECIPE VALUES (\{recipe.getId()}, \{recipe.getSource()}, \{recipe.getBase()});";
        this.databaseConnection.execute(sql);
        for (Step step : recipe.getSteps()){
            // Insert to INCLUDES
            sql = STR."INSERT INTO INCLUDES VALUES (\{recipe.getId()}, \{step.getId()}, \{step.getOrder()});";
            this.databaseConnection.execute(sql);
            // Insert to REQUIRES
            for (Component ingredient : step.getIngredients()){
                sql = STR."INSERT INTO REQUIRES VALUES (\{recipe.getId()}, \{step.getOrder()}, \{ingredient.getId()}, \{ingredient.getQuantity()}, '\{ingredient.getUnit()}');";
                this.databaseConnection.execute(sql);
            }
            for (Component equipment : step.getEquipment()){
                sql = STR."INSERT INTO REQUIRES VALUES (\{recipe.getId()}, \{step.getOrder()}, \{equipment.getId()}, \{equipment.getQuantity()}, '\{equipment.getUnit()}');";
                this.databaseConnection.execute(sql);
            }
            for (Component subrecipe : step.getSubrecipes()){
                sql = STR."INSERT INTO REQUIRES VALUES (\{recipe.getId()}, \{step.getOrder()}, \{subrecipe.getId()}, \{subrecipe.getQuantity()}, '\{subrecipe.getUnit()})';";
                this.databaseConnection.execute(sql);
            }
        }
        return recipe;
    }

    @Override
    public List<RecipeDetailed> get(String id){
        String sql = STR."SELECT Component_name name, Recipe_id id, Source_id source, Base_id base FROM COMPONENT, RECIPE WHERE Component_id = Recipe_id AND Recipe_id = \{id};";
        List<RecipeDetailed> recipes = this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(RecipeDetailed.class));
        for (RecipeDetailed recipe : recipes){
            sql = STR."SELECT Tag_name [name], TAG.Tag_id id, Tag_type [type], Tag_detail detail FROM TAGGED, TAG WHERE TAGGED.Tag_id = TAG.Tag_id AND Recipe_id = \{recipe.getId()};";
            List<Tag> tags = this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(Tag.class));
            recipe.setTags(tags);
            sql = STR."SELECT STEP.Step_id id, [Order], [Action], Duration FROM INCLUDES, STEP WHERE Recipe_id = \{recipe.getId()} AND INCLUDES.Step_id = STEP.Step_id;";
            List<Step> steps = this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(Step.class));
            recipe.setSteps(steps);
            for (Step step : steps){
                sql = STR."SELECT COMPONENT.Component_id id, Component_name as [name], Quantity, Unit FROM REQUIRES, COMPONENT WHERE Recipe_id = \{recipe.getId()} AND REQUIRES.Component_id = COMPONENT.Component_id AND Step_number = \{step.getOrder()} AND Component_type = 'ingredient'; ";
                List<Component> ingredients = this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(Component.class));
                step.setIngredients(ingredients);
                sql = STR."SELECT COMPONENT.Component_id id, Component_name as [name], Quantity, Unit FROM REQUIRES, COMPONENT WHERE Recipe_id = \{recipe.getId()} AND REQUIRES.Component_id = COMPONENT.Component_id AND Step_number = \{step.getOrder()} AND Component_type = 'implement'; ";
                List<Component> equipment = this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(Component.class));
                step.setEquipment(equipment);
                sql = STR."SELECT COMPONENT.Component_id id, Component_name as [name], Quantity, Unit FROM REQUIRES, COMPONENT WHERE Recipe_id = \{recipe.getId()} AND REQUIRES.Component_id = COMPONENT.Component_id AND Step_number = \{step.getOrder()} AND Component_type = 'recipe'; ";
                List<Component> subrecipes = this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(Component.class));
                step.setSubrecipes(subrecipes);
            }
        }
        return recipes;
    }

    @Override
    public List<RecipeDetailed> get(){
        String sql = "SELECT Recipe_id id FROM RECIPE;";
        return this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(RecipeSimple.class))
                .stream().filter(r -> r.getId() != -1) // Filter out dummy base recipe
                .map(rec -> this.get(String.valueOf(rec.getId()))) // Do the full get to populate steps
                .flatMap(List::stream).toList(); // Force it back into the right format
    }

    @Override
    public int update(String id, RecipeDetailed recipe){
        // Let's assume we're only changing source/base, otherwise this gets very complex
        String sql = STR."UPDATE RECIPE SET Source_id = \{recipe.getSource()}, Base_id = \{recipe.getBase()} WHERE Recipe_id = \{id};";
        return this.databaseConnection.update(sql);
    }

    @Override
    public boolean delete(String id){
        // Assumes related entities (ingredients, implements, steps, etc.) are used by other recipes
        // and do not require deletion. Also assumes no other recipes depend on this recipe; if they do,
        // we would need to delete them first

        // Delete from CONTAINS (collections)
        String sql = STR."DELETE FROM [CONTAINS] WHERE Recipe_id = \{id}; "
                // Delete from PAIRING (well, PAIRING.Second; PAIRING.First will be caught by CASCADE)
                + STR."DELETE FROM PAIRING WHERE Second = \{id}; "
                // Update recipes based on this to instead be based on whatever this is based on
                + STR."UPDATE RECIPE SET Base_id = (SELECT Base_id FROM RECIPE WHERE Recipe_id = \{id}) WHERE Base_id = \{id}; "
                // Delete from RECIPE, which CASCADEs to INCLUDES, REQUIRES, TAGGED, and PAIRING (Pairing.First, anyway)
                + STR."DELETE FROM RECIPE WHERE Recipe_id = \{id}; "
                // Finally, delete from COMPONENT
                + STR."DELETE FROM COMPONENT WHERE Component_id = \{id};";
        this.databaseConnection.execute(sql);
        return true;
    }
}
