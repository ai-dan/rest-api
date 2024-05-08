package App.Application.Controllers;

import App.Domain.RecipeSimple;
import App.Domain.Tag;
import App.Infrastructure.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/Tag")
public class TagController {

    @Autowired
    private JdbcTemplate databaseConnection;

    private TagRepository tagRepository;

    public TagController(){ this.tagRepository = new TagRepository(); }

    @GetMapping("/")
    public List<Tag> get(){
        this.tagRepository.setDatabaseConnection(this.databaseConnection);
        return this.tagRepository.get();
    }

    @GetMapping("/{id}")
    public List<Tag> get(@PathVariable("id") String id){
        this.tagRepository.setDatabaseConnection(this.databaseConnection);
        return this.tagRepository.get(id);
    }

    @GetMapping("/{id}/recipes")
    public List<RecipeSimple> getWithTag(@PathVariable("id") String id){
        this.tagRepository.setDatabaseConnection(this.databaseConnection);
        return this.tagRepository.getWithTag(id);
    }

    @PostMapping("/")
    public String post(@RequestBody() Tag tag){
        this.tagRepository.setDatabaseConnection(this.databaseConnection);
        this.tagRepository.create(tag);
        return tag.getName();
    }

    @PostMapping("/{rid}/{tid}")
    public void tag(@PathVariable("rid") String rid, @PathVariable("tid") String tid){
        this.tagRepository.setDatabaseConnection(this.databaseConnection);
        this.tagRepository.tag(rid, tid);
    }

    @PutMapping("/{id}")
    public void put(@PathVariable("id") String id, @RequestBody() Tag tag){
        this.tagRepository.setDatabaseConnection(this.databaseConnection);
        this.tagRepository.update(id, tag);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        this.tagRepository.setDatabaseConnection(this.databaseConnection);
        this.tagRepository.delete(id);
    }
}
