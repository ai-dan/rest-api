package App.Domain;

import java.util.List;

public class Collection {

    private String name;
    private int id;
    private String type;
    private int owner;
    private List<CollectionItem> contents;

    public void setName(String name){ this.name = name; }
    public String getName(){ return this.name; }

    public void setId(int id){ this.id = id; }
    public int getId(){ return this.id; }

    public void setType(String type){ this.type = type; }
    public String getType(){ return this.type; }

    public void setOwner(int owner){ this.owner = owner; }
    public int getOwner(){ return this.owner; }

    public void setContents(List<CollectionItem> contents){ this.contents = contents; }
    public List<CollectionItem> getContents(){ return this.contents; }
}
