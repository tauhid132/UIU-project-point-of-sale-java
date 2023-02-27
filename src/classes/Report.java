package classes;

public class Report {
    public int id;
    public int orderNo;
    public String customerName;
    public String mobileNo;
    public double totalPur;
    public double discount;
    public double grandTotal;
    public double profit;
    public String salesPerson;
    public String timeStamp;

    public Report(int id, int orderNo, String customerName, String mobileNo, double totalPur, double discount, double grandTotal, double profit, String salesPerson, String timeStamp) {
        this.id = id;
        this.orderNo = orderNo;
        this.customerName = customerName;
        this.mobileNo = mobileNo;
        this.totalPur = totalPur;
        this.discount = discount;
        this.grandTotal = grandTotal;
        this.profit = profit;
        this.salesPerson = salesPerson;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public double getTotalPur() {
        return totalPur;
    }

    public double getDiscount() {
        return discount;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public String getSalesPerson() {
        return salesPerson;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public double getProfit() {
        return profit;
    }
}
