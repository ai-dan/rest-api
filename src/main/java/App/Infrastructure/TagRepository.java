package App.Infrastructure;

import App.Domain.RecipeSimple;
import App.Domain.Tag;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class TagRepository implements IRepository<Tag> {

    private JdbcTemplate databaseConnection;

    @Override
    public void setDatabaseConnection(JdbcTemplate databaseConnection){ this.databaseConnection = databaseConnection; }

    @Override
    public Tag create(Tag tag){
        String sql = STR."INSERT INTO TAG VALUES (\{tag.getId()}, '\{tag.getType()}', '\{tag.getName()}', '\{tag.getDetail()}')";
        this.databaseConnection.execute(sql);
        return tag;
    }

    public boolean tag(String rid, String tid){
        String sql = STR."INSERT INTO TAGGED VALUES (\{rid}, \{tid});";
        this.databaseConnection.execute(sql);
        return true;
    }

    @Override
    public int update(String id, Tag tag){
        // Don't allow id to be updated, but other attributes are ok
        String sql = STR."UPDATE TAG SET Tag_type = '\{tag.getType()}', Tag_name = '\{tag.getName()}', Tag_detail = '\{tag.getDetail()}' WHERE Tag_id = \{id};";
        return this.databaseConnection.update(sql);
    }

    @Override
    public boolean delete(String id){
        // Have to delete from TAGGED, since that CASCADES on recipe rather than tag
        String sql = STR."DELETE FROM TAGGED WHERE Tag_id = \{id}; DELETE FROM TAG WHERE Tag_id = \{id};";
        this.databaseConnection.execute(sql);
        return true;
    }

    @Override
    public List<Tag> get(String id){
        String sql = STR."SELECT Tag_name [name], Tag_id id, Tag_type [type], Tag_detail detail FROM TAG WHERE Tag_id = \{id};";
        return this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(Tag.class));
    }

    @Override
    public List<Tag> get(){
        String sql = "SELECT Tag_name [name], Tag_id id, Tag_type [type], Tag_detail detail FROM TAG;";
        return this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(Tag.class));
    }

    public List<RecipeSimple> getWithTag(String id){
        String sql = STR."SELECT Component_name [name], TAGGED.Recipe_id id FROM COMPONENT, TAGGED WHERE Component_id = Recipe_id AND Tag_id = \{id};";
        return this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(RecipeSimple.class));
    }
}
