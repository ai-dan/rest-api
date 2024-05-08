package App.Infrastructure;

import App.Domain.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserRepository implements IRepository<User> {

    private JdbcTemplate databaseConnection;

    @Override
    public void setDatabaseConnection(JdbcTemplate databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    @Override
    public User create(User user) {
        // Don't create any groups or queues or membership, just make the user
        String sql = STR."INSERT INTO AGENT VALUES (\{user.getId()}, 'user');"
                + STR."INSERT INTO [USER] VALUES (\{user.getId()}, '\{user.getFirstName()}', '\{user.getLastName()}', '\{user.getEmail()}');";
        this.databaseConnection.execute(sql);
        return user;
    }

    @Override
    public int update(String id, User user) {
        // Allow updates to first & last name and email, but not id
        String sql = STR."UPDATE [USER] SET First_name = \{user.getFirstName()}, Last_name = \{user.getLastName()}, Email = \{user.getEmail()} WHERE id = \{id};";
        return this.databaseConnection.update(sql);
    }

    @Override
    public boolean delete(String id) {
        // AGENT will CASCADE to USER, but need to clean up other tables
        String sql = STR."UPDATE RECIPE SET Source_id = -1 WHERE Source_id = \{id}; "
                + STR."DELETE FROM [COLLECTION] WHERE Owner_id = \{id}; "
                + STR."DELETE FROM [MEMBERSHIP] WHERE User_id = \{id}; "
                + STR."DELETE FROM [VIEWERSHIP] WHERE Viewer_id = \{id}; "
                + STR."DELETE FROM DELETE FROM AGENT WHERE Agent_id = \{id};";
        this.databaseConnection.execute(sql);
        return true;
    }

    @Override
    public List<User> get(String id) {
        String sql = STR."SELECT User_id id, First_name firstName, Last_name lastName, Email email FROM [USER] WHERE User_id = \{id};";
        List<User> users = this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(User.class));
        for (User user : users){
            sql = STR."SELECT Group_name [name], Group_id id, 'owner' [role] FROM [GROUP] WHERE Owner_id = \{user.getId()} "
                    + "UNION ALL SELECT Group_name [name], [GROUP].Group_id id, 'member' [role] FROM [GROUP], MEMBERSHIP "
                    + STR."WHERE User_id = \{user.getId()} AND [GROUP].Group_id = MEMBERSHIP.Group_id AND Owner_id <> \{user.getId()};";
            List<UserGroup> groups = this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(UserGroup.class));
            user.setGroups(groups);
            sql = STR."SELECT Collection_name [name], Collection_id id, Collection_type [type], 'owner' [role] FROM [COLLECTION] WHERE Owner_id = \{user.getId()} "
                    + "UNION ALL SELECT Collection_name [name], [COLLECTION].Collection_id id, Collection_type [type], 'viewer' [role] FROM [COLLECTION], VIEWERSHIP "
                    + STR."WHERE Viewer_id = \{user.getId()} AND [COLLECTION].Collection_id = VIEWERSHIP.Collection_id AND Owner_id <> \{user.getId()};";
            List<UserCollection> collections = this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(UserCollection.class));
            user.setCollections(collections);
            sql = STR."SELECT Component_name [name], Recipe_id id FROM COMPONENT, RECIPE WHERE Component_id = Recipe_id AND Source_id = \{user.getId()};";
            List<RecipeSimple> recipes = this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(RecipeSimple.class));
            user.setRecipes(recipes);
        }
        return users;
    }

    @Override
    public List<User> get() {
        String sql = "SELECT User_id id FROM [USER];";
        return this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(User.class))
                .stream().map(user -> this.get(String.valueOf(user.getId()))) // Populate user data
                .flatMap(List::stream).toList(); // Force it back into the right format
    }
}
