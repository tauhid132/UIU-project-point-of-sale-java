package classes;

public class Category {
    public int id;
    public String catName;

    public Category(int id, String catName) {
        this.id = id;
        this.catName = catName;
    }

    public int getId() {
        return id;
    }

    public String getCatName() {
        return catName;
    }
}
