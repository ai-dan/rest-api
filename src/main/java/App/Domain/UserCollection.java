package App.Domain;

public class UserCollection {

    private String name;
    private int id;
    private String type;
    private String role;

    public void setName(String name){ this.name = name; }
    public String getName(){ return this.name; }

    public void setId(int id){ this.id = id; }
    public int getId(){ return this.id; }

    public void setType(String type){ this.type = type; }
    public String getType(){ return this.type; }

    public void setRole(String role){ this.role = role; }
    public String getRole(){ return this.role; }
}
