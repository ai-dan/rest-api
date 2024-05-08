package App.Infrastructure;

import App.Domain.Collection;
import App.Domain.CollectionItem;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CollectionRepository implements IRepository<Collection> {

    private JdbcTemplate databaseConnection;

    @Override
    public void setDatabaseConnection(JdbcTemplate databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Collection create(Collection collection){
        String sql = STR."INSERT INTO [COLLECTION] VALUES (\{collection.getId()}, '\{collection.getName()}', '\{collection.getType()}', \{collection.getOwner()});";
        this.databaseConnection.execute(sql);
        return collection;
    }

    public List<Collection> add(String id, CollectionItem item){
        String sql = STR."INSERT INTO [CONTAINS] (Collection_id, Recipe_id, Rank, Note) VALUES (\{id}, \{item.getId()}, \{item.getRank() > 0 ? item.getRank() : -1}, '\{item.getNote()}');";
        this.databaseConnection.execute(sql);
        return this.get(id);
    }

    @Override
    public int update(String id, Collection collection){
        // Let's only allow changing the name of a collection
        String sql = STR."UPDATE [COLLECTION] SET Collection_name = '\{collection.getName()}' WHERE Collection_id = \{id};";
        return this.databaseConnection.update(sql);
    }

    public List<Collection> cook(String from, String to){
        String sql = STR."UPDATE [CONTAINS] SET Rank = Rank - 1 WHERE Collection_id = \{from};"
                + STR."INSERT INTO [CONTAINS] (Collection_id, Recipe_id, Note) SELECT \{to} Collection_id, Recipe_id, Note FROM [CONTAINS] WHERE Collection_id = \{from} AND Rank = 0;"
                + STR."DELETE FROM [CONTAINS] WHERE Collection_id = \{from} AND Rank = 0;";
        this.databaseConnection.execute(sql);
        return this.get(from);
    }

    @Override
    public boolean delete(String id){
        // The contents of the collection live in CONTAINS, but this will CASCADE
        String sql = STR."DELETE FROM [COLLECTION] WHERE Collection_id = \{id};";
        this.databaseConnection.execute(sql);
        return true;
    }

    public List<Collection> remove(String cid, String rid){
        String sql = STR."DELETE FROM [CONTAINS] WHERE Collection_id = \{cid} AND Recipe_id = \{rid};";
        this.databaseConnection.execute(sql);
        return this.get(cid);
    }

    @Override
    public List<Collection> get(String id){
        String sql = STR."SELECT Collection_name [name], Collection_id id, Collection_type [type], Owner_id [owner] FROM [COLLECTION] WHERE Collection_id = \{id};";
        List<Collection> collections = this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(Collection.class));
        for (Collection collection : collections){
            sql = STR."SELECT Component_name [name], Recipe_id id, Note, Time_added, Rank FROM COMPONENT, [CONTAINS] WHERE Component_id = Recipe_id AND Collection_id = \{collection.getId()} ORDER BY Rank ASC, Time_added DESC;";
            List<CollectionItem> contents = this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(CollectionItem.class));
            collection.setContents(contents);
        }
        return collections;
    }

    @Override
    public  List<Collection> get(){
        String sql = "SELECT Collection_name [name], Collection_id id, Collection_type [type], Owner_id [owner] FROM [COLLECTION];";
        List<Collection> collections = this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(Collection.class));
        for (Collection collection : collections){
            sql = STR."SELECT Component_name [name], Recipe_id id, Note, Time_added, Rank FROM COMPONENT, [CONTAINS] WHERE Component_id = Recipe_id AND Collection_id = \{collection.getId()} ORDER BY Rank ASC, Time_added DESC;";
            List<CollectionItem> contents = this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(CollectionItem.class));
            collection.setContents(contents);
        }
        return collections;    }
}
