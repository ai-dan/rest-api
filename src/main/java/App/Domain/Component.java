package App.Domain;

public class Component {

    private String name;
    private int quantity;
    private String unit;
    private int id;

    public void setId(int id){ this.id = id; }
    public int getId(){ return this.id; }

    public void setName(String name){ this.name = name; }
    public String getName(){ return this.name; }

    public void setQuantity(int quantity){ this.quantity = quantity; }
    public  int getQuantity(){ return this.quantity; }

    public void setUnit(String unit){ this.unit = unit; }
    public String getUnit(){ return this.unit; }
}
