package App.Domain;

public class Tag {

    private String name;
    private int id;
    private String type;
    private String detail;

    public void setName(String name){ this.name = name; }
    public String getName(){ return this.name; }

    public void setId(int id){ this.id = id; }
    public int getId(){ return this.id; }

    public void setType(String type){ this.type = type; }
    public String getType(){ return this.type; }

    public void setDetail(String detail){ this.detail = detail; }
    public String getDetail(){ return this.detail; }
}
