package App.Domain;

public class UserGroup {

    private String name;
    private int id;
    private String role;

    public void setName(String name){ this.name = name; }
    public String getName(){ return this.name; }

    public void setId(int id){ this.id = id; }
    public int getId(){ return this.id; }

    public void setRole(String role){ this.role = role; }
    public String getRole(){ return this.role; }
}
