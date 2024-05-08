package App.Domain;

import java.util.List;

public class Step {

    private int order;
    private String action;
    private String duration;
    private List<Component> ingredients;
    private List<Component> equipment;
    private List<Component> subrecipes;
    private int id;

    public void setId(int id){ this.id = id; }
    public int getId(){ return this.id; }

    public void setOrder(int order){ this.order = order; }
    public int getOrder(){ return this.order; }

    public void setAction(String action){ this.action = action; }
    public String getAction(){ return this.action; }

    public void setDuration(String duration){ this.duration = duration; }
    public String getDuration(){ return this.duration; }

    public void setIngredients(List<Component> ingredients){ this.ingredients = ingredients; }
    public List<Component> getIngredients(){ return this.ingredients; }

    public void setEquipment(List<Component> equipment){ this.equipment = equipment; }
    public List<Component> getEquipment(){ return this.equipment; }

    public void setSubrecipes(List<Component> subrecipes){ this.subrecipes = subrecipes; }
    public List<Component> getSubrecipes(){ return this.subrecipes; }
}
