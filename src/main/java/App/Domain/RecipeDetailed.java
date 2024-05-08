package App.Domain;

import java.util.List;

public class RecipeDetailed extends Recipe {

    private List<Tag> tags;
    private List<Step> steps;

    public void setTags(List<Tag> tags){ this.tags = tags; }
    public List<Tag> getTags(){ return this.tags; }

    public void setSteps(List<Step> steps){ this.steps = steps; }
    public List<Step> getSteps(){ return this.steps; }

}
