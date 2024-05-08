package App.Application.Controllers;

import App.Domain.User;
import App.Infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/User")
public class UserController {

    @Autowired
    private JdbcTemplate databaseConnection;

    private UserRepository userRepository;

    public UserController(){
        this.userRepository = new UserRepository();
    }


    @PostMapping("/")
    public String post(@RequestBody() User user){
        this.userRepository.setDatabaseConnection(this.databaseConnection);
        return this.userRepository.create(user).getEmail();
    }

    @PutMapping("/{id}")
    public void put(@PathVariable("id") @RequestBody() String id, User user){
        this.userRepository.setDatabaseConnection(this.databaseConnection);
        this.userRepository.update(id, user);
    }
    @GetMapping("/{id}")
    public List<User> get(@PathVariable("id") String id) {
        this.userRepository.setDatabaseConnection(this.databaseConnection);
        return this.userRepository.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        this.userRepository.setDatabaseConnection(this.databaseConnection);
        this.userRepository.delete(id);
    }

    @GetMapping("/")
    public List<User> get(){
        this.userRepository.setDatabaseConnection(this.databaseConnection);
        return this.userRepository.get();
    }
}