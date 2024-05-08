package App.Domain;

public class Recipe extends RecipeSimple {

    private int source;
    private int base;

    public void setSource(int source){ this.source = source; }
    public int getSource(){ return this.source; }

    public void setBase(int base){ this.base = base; }
    public int getBase(){ return this.base; }
}
