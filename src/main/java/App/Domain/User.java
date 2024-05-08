package App.Domain;

import java.util.List;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private List<UserCollection> collections;
    private List<UserGroup> groups;
    private List<RecipeSimple> recipes;



    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){ return this.firstName; }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastName(){ return this.lastName; }

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }

    public void setCollections(List<UserCollection> collections){ this.collections = collections; }
    public List<UserCollection> getCollections(){ return this.collections; }

    public void setGroups(List<UserGroup> groups){ this.groups = groups; }
    public List<UserGroup> getGroups(){ return this.groups; }

    public void setRecipes(List<RecipeSimple> recipes){ this.recipes = recipes; }
    public List<RecipeSimple> getRecipes(){ return this.recipes; }
}
