package App.Application.Controllers;

import App.Domain.Collection;
import App.Domain.CollectionItem;
import App.Infrastructure.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/Collection")
public class CollectionController {

    @Autowired
    private JdbcTemplate databaseConnection;

    private CollectionRepository collectionRepository;

    public CollectionController(){ this.collectionRepository = new CollectionRepository(); }

    @GetMapping("/{id}")
    public List<Collection> get(@PathVariable("id") String id){
        this.collectionRepository.setDatabaseConnection(this.databaseConnection);
        return this.collectionRepository.get(id);
    }

    @GetMapping("/")
    public List<Collection> get(){
        this.collectionRepository.setDatabaseConnection(this.databaseConnection);
        return this.collectionRepository.get();
    }

    @PostMapping("/") // Add a new collection
    public String post(@RequestBody() Collection collection){
        this.collectionRepository.setDatabaseConnection(this.databaseConnection);
        this.collectionRepository.create(collection);
        return collection.getName();
    }

    @PostMapping("/{id}") // Add a recipe to an existing collection
    public List<Collection> add(@RequestBody() CollectionItem item, @PathVariable("id") String id){
        this.collectionRepository.setDatabaseConnection(this.databaseConnection);
        return this.collectionRepository.add(id, item);
    }

    @PutMapping("/{id}") // Update an existing collection
    public void put(@RequestBody() Collection collection, @PathVariable("id") String id){
        this.collectionRepository.setDatabaseConnection(this.databaseConnection);
        this.collectionRepository.update(id, collection);
    }

    @PutMapping("/{from}/{to}") // Move rank-1 recipe(s) to history and decrement other ranks
    public List<Collection> cook(@PathVariable("from") String from, @PathVariable("to") String to){
        this.collectionRepository.setDatabaseConnection(this.databaseConnection);
        return this.collectionRepository.cook(from, to);
    }


    @DeleteMapping("/{id}") // Delete a collection
    public void delete(@PathVariable("id") String id){
        this.collectionRepository.setDatabaseConnection(this.databaseConnection);
        this.collectionRepository.delete(id);
    }

    @DeleteMapping("/{cid}/{rid}") // Delete all instances of a recipe from a collection
    public List<Collection> remove(@PathVariable("cid") String cid, @PathVariable("rid") String rid){
        this.collectionRepository.setDatabaseConnection(this.databaseConnection);
        return this.collectionRepository.remove(cid, rid);
    }
}
