package App.Domain;

public class CollectionItem {

    private String name;
    private int id;
    private String note;
    private String timeAdded;
    private int rank;

    public void setName(String name){ this.name = name; }
    public String getName(){ return this.name; }

    public void setId(int id){ this.id = id; }
    public int getId(){ return this.id; }

    public void setNote(String note){ this.note = note; }
    public String getNote(){ return this.note; }

    public void setTimeAdded(String timeAdded){ this.timeAdded = timeAdded; }
    public String getTimeAdded(){ return this.timeAdded; }

    public void setRank(int rank){ this.rank = rank; }
    public int getRank(){ return this.rank; }
}
