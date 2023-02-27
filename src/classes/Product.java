package classes;

public class Product {
    public int id;
    public String ProductName;
    public String productDescription;
    public String productCategory;
    public String productSubcategory;
    public int currentStock;
    public double buyPrice;
    public double sellPrice;

    public Product(int id, String productName, String productDescription, String productCategory, String productSubcategory, int currentStock, double buyPrice, double sellPrice) {
        this.id = id;
        ProductName = productName;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
        this.productSubcategory = productSubcategory;
        this.currentStock = currentStock;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getProductSubcategory() {
        return productSubcategory;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }
}
