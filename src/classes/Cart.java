package classes;

public class Cart {
    public int id;
    public String proName;
    public String proDes;
    public int qty;
    public double amount;
    public double totalAmount;
    public double profit;

    public Cart(int id, String proName, String proDes, int qty, double amount, double totalAmount,double profit) {
        this.id = id;
        this.proName = proName;
        this.proDes = proDes;
        this.qty = qty;
        this.amount = amount;
        this.totalAmount = totalAmount;
        this.profit = profit;
    }

    public int getId() {
        return id;
    }

    public String getProName() {
        return proName;
    }

    public String getProDes() {
        return proDes;
    }

    public int getQty() {
        return qty;
    }

    public double getAmount() {
        return amount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getProfit() {
        return profit;
    }
}
