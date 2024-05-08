package App.Domain;

public class Analytics {

    private String recipe;
    private int id;
    private int instances;

    public void setRecipe(String recipe){ this.recipe = recipe; }
    public String getRecipe(){ return this.recipe; }

    public void setId(int id){ this.id = id; }
    public int getId(){ return this.id; }

    public void setInstances(int instances){ this.instances = instances; }
    public int getInstances(){ return this.instances; }
}
