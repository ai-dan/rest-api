package App.Application.Controllers;

import App.Domain.Analytics;
import App.Infrastructure.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/Analytics")
public class AnalyticsController {

    @Autowired
    private JdbcTemplate databaseConnection;

    private AnalyticsRepository analyticsRepository;

    public AnalyticsController(){ this.analyticsRepository = new AnalyticsRepository(); }

    @GetMapping("/Popular/{num}")
    public List<Analytics> getPopular(@PathVariable("num") int num){
        this.analyticsRepository.setDatabaseConnection(databaseConnection);
        return this.analyticsRepository.getPopular(num);
    }

    @GetMapping("/MostQueued/{num}")
    public List<Analytics> getMostQueued(@PathVariable("num") int num){
        this.analyticsRepository.setDatabaseConnection(databaseConnection);
        return this.analyticsRepository.getMostQueued(num);
    }

    @GetMapping("/MostCooked/{num}")
    public List<Analytics> getMostCooked(@PathVariable("num") int num){
        this.analyticsRepository.setDatabaseConnection(databaseConnection);
        return this.analyticsRepository.getMostCooked(num);
    }
}
