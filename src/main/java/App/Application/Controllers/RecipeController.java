package App.Application.Controllers;

import App.Domain.RecipeDetailed;
import App.Infrastructure.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/Recipe")
public class RecipeController {

    @Autowired
    private JdbcTemplate databaseConnection;

    private RecipeRepository recipeRepository;

    public RecipeController(){ this.recipeRepository = new RecipeRepository(); }

    @GetMapping("/{id}")
    public List<RecipeDetailed> get(@PathVariable("id") String id){
        this.recipeRepository.setDatabaseConnection(databaseConnection);
        return this.recipeRepository.get(id);
    }

    @GetMapping("/")
    public List<RecipeDetailed> get(){
        this.recipeRepository.setDatabaseConnection(this.databaseConnection);
        return this.recipeRepository.get();
    }

    @PostMapping("/")
    public String post(@RequestBody() RecipeDetailed recipe){
        this.recipeRepository.setDatabaseConnection(this.databaseConnection);
        this.recipeRepository.create(recipe);
        return recipe.getName();
    }

    @PutMapping("/{id}")
    public void put(@RequestBody() RecipeDetailed recipe, @PathVariable("id") String id){
        this.recipeRepository.setDatabaseConnection(this.databaseConnection);
        this.recipeRepository.update(id, recipe);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        this.recipeRepository.setDatabaseConnection((this.databaseConnection));
        this.recipeRepository.delete(id);
    }
}
