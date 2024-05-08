package App.Infrastructure;

import App.Domain.Analytics;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AnalyticsRepository implements IRepository<Analytics> {

    private JdbcTemplate databaseConnection;

    public void setDatabaseConnection(JdbcTemplate databaseConnection){ this.databaseConnection = databaseConnection; }

    public List<Analytics> getPopular(int k){
        // Get the k recipes that show up the most frequently across all user queues over the past week
        String sql = "SELECT Component_name recipe, Component_id id, COUNT(*) instances FROM [COLLECTION], COMPONENT, [CONTAINS] WHERE [COLLECTION].Collection_id = [CONTAINS].Collection_id AND Component_id = Recipe_id AND Time_added > DATEADD(DAY, -7, CURRENT_TIMESTAMP) GROUP BY Component_name, Component_id ORDER BY instances DESC;";
        return this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(Analytics.class)).stream().limit(k).toList();
    }

    public List<Analytics> getMostQueued(int k){
        // Get the k recipes that show up the most frequently across non-history user queues over the past week
        String sql = "SELECT Component_name recipe, Component_id id, COUNT(*) instances FROM [COLLECTION], COMPONENT, [CONTAINS] WHERE [COLLECTION].Collection_type <> 'history' AND [COLLECTION].Collection_id = [CONTAINS].Collection_id AND Component_id = Recipe_id AND Time_added > DATEADD(DAY, -7, CURRENT_TIMESTAMP) GROUP BY Component_name, Component_id ORDER BY instances DESC;";
        return this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(Analytics.class)).stream().limit(k).toList();
    }

    public List<Analytics> getMostCooked(int k){
        // Get the k recipes that show up the most frequently across all user histories over the past week
        String sql = "SELECT Component_name recipe, Component_id id, COUNT(*) instances FROM [COLLECTION], COMPONENT, [CONTAINS] WHERE [COLLECTION].Collection_type = 'history' AND [COLLECTION].Collection_id = [CONTAINS].Collection_id AND Component_id = Recipe_id AND Time_added > DATEADD(DAY, -7, CURRENT_TIMESTAMP) GROUP BY Component_name, Component_id ORDER BY instances DESC;";
        return this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(Analytics.class)).stream().limit(k).toList();
    }

    // Create/update/delete don't make much sense for analytics queries
    // but we'll implement them so we can conform to the template
    public Analytics create(Analytics analytics){
        return analytics;
    }

    public int update(String id, Analytics analytics){
        return 0;
    }

    public boolean delete(String id){
        return false;
    }

    public List<Analytics> get(String id){
        return this.getPopular(Integer.parseInt(id));
    }

    public List<Analytics> get(){
        return this.getPopular(5);
    }
}
