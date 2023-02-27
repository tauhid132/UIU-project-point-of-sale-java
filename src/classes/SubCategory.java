package classes;

public class SubCategory {
    public int id;
    public String subCatName;
    public String parentCatName;

    public SubCategory(int id, String subCatName, String patentCatName) {
        this.id = id;
        this.subCatName = subCatName;
        this.parentCatName = patentCatName;
    }

    public int getId() {
        return id;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public String getParentCatName() {
        return parentCatName;
    }
}
