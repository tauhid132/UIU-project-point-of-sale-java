package controllers;

import classes.Cart;
import classes.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class PosController implements Initializable {
    @FXML
    Label topUsernameLabel;
    @FXML
    Button switchToDashboard,switchToPos,switchToProducts,switchToCategories,switchToSubcategories,switchToReport,switchToSetting,switchToAdmin,logout;
    @FXML
    ChoiceBox catOP,subCatOP;
    @FXML
    TextField qtyTF,totalPriceTF,discountTF,grandTotalTF,orderNoTF,mobileTF;
    @FXML
    public TableView<Product> proTable;
    @FXML
    TableColumn<Product,Integer> colNo;
    @FXML
    TableColumn<Product,String> colProName;
    @FXML
    TableColumn<Product,String> colDes;
    @FXML
    TableColumn<Product,Double> colBuyPrice;
    @FXML
    TableColumn<Product,Double> colSellPrice;
    @FXML
    TableColumn<Product,Integer> colStock;
    @FXML
    public TableView<Cart> cartTable;
    @FXML
    TableColumn<Cart,Integer> colNoCart;
    @FXML
    TableColumn<Cart,String> colProNameCart;
    @FXML
    TableColumn<Cart,Integer> colQtyCart;
    @FXML
    TableColumn<Cart,Double> colAmountCart;
    @FXML
    TableColumn<Cart,Double> colTotalCart;

    //Button Controllers
    SceneController sceneController = new SceneController();
    public void switchToDashboard(ActionEvent e){
        sceneController.switchToDashboard(e);
    }
    public void switchToPos(ActionEvent e){
        sceneController.switchToPos(e);
    }
    public void switchToProducts(ActionEvent e){
        sceneController.switchToProducts(e);
    }
    public void switchToCategories(ActionEvent e){
        sceneController.switchToCategoris(e);
    }
    public void switchToSubcategories(ActionEvent e){
        sceneController.switchToSubCategories(e);
    }
    public void switchToReport(ActionEvent e){
        sceneController.switchToReport(e);
    }
    public void switchToSetting(ActionEvent e){
        sceneController.switchToSettings(e);
    }
    public void switchToAdmin(ActionEvent e){
        sceneController.switchToAdmin(e);
    }
    public void logout(ActionEvent e){
        sceneController.logout(e);
    }

    public void QtyUP(ActionEvent actionEvent){
        int existing = Integer.parseInt(qtyTF.getText());
        int next = existing+1;
        qtyTF.setText(next+"");
    }
    public void QtyDown(ActionEvent actionEvent){
        int existing = Integer.parseInt(qtyTF.getText());
        if(existing > 0) {
            int next = existing - 1;
            qtyTF.setText(next + "");
        }
    }


    public void proSelector(){
        ObservableList<String> cats = FXCollections.observableArrayList();
        ObservableList<String> subCats = FXCollections.observableArrayList();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/database/categories.txt"));
            String line;
            while((line=reader.readLine())!=null){
                String [] parts = line.split("###");
                String parentCatName = parts[1];
                cats.add(parentCatName);
            }
            reader.close();
        }catch (IOException E){
            E.printStackTrace();
        }
        catOP.setItems(cats);
        catOP.setOnAction((event) -> {
            String selectedCat = (String) catOP.getValue();
            try{
                subCats.clear();
                subCatOP.setItems(subCats);
                System.out.println("removed");
                BufferedReader reader = new BufferedReader(new FileReader("src/database/sub-categories.txt"));
                String line;
                while((line=reader.readLine())!=null){
                    String [] parts = line.split("###");
                    String parentCatName = parts[2];
                    if(parentCatName.equals(selectedCat)) {
                        subCats.add(parts[1]);
                    }
                }
                reader.close();
            }catch (IOException E){
                E.printStackTrace();
            }
            subCatOP.setItems(subCats);
        });
        subCatOP.setOnAction((event) -> {
            ObservableList<Product> proList = FXCollections.observableArrayList();
            String selectedCat2 = (String) catOP.getValue();
            try{
                String selectedSubCat = (String) subCatOP.getValue();
                colNo.setCellValueFactory(new PropertyValueFactory<>("id"));
                colProName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
                colDes.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
                colStock.setCellValueFactory(new PropertyValueFactory<>("currentStock"));
                colBuyPrice.setCellValueFactory(new PropertyValueFactory<>("buyPrice"));
                colSellPrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));


                FileReader fr = new FileReader("src/database/products.txt");
                BufferedReader reader = new BufferedReader(fr);
                String line;
                while((line=reader.readLine())!=null){
                    String parts[] = line.split("###");
                    int ids = Integer.parseInt(parts[0]);
                    String proname = parts[1];
                    String description = parts[2];
                    String category = parts[3];
                    String subcat = parts[4];
                    int stock = Integer.parseInt(parts[5]);
                    double buyprice = Double.parseDouble(parts[6]);
                    double sellprice = Double.parseDouble(parts[7]);
                    if(category.equals(selectedCat2) && subcat.equals(selectedSubCat)) {
                        proList.add(new Product(ids, proname, description, category, subcat, stock, buyprice, sellprice));
                    }
                }
                    reader.close();

            }catch (IOException E){
                E.printStackTrace();
            }
            proTable.setItems(proList);
            System.out.println("showing Table");

        });
    }
    ObservableList<Cart> cart = FXCollections.observableArrayList();
    int selectedProductFromTable = 0;
    int cartProductId = 1;
    public void addToCart(ActionEvent actionEvent){
        selectedProductFromTable = proTable.getSelectionModel().getSelectedItem().id;
        System.out.println(selectedProductFromTable);
        if(selectedProductFromTable == 0){
            System.out.println("Select Product First");
        }else{
            int qty = Integer.parseInt(qtyTF.getText());
            try{
                FileReader fr = new FileReader("src/database/products.txt");
                BufferedReader reader = new BufferedReader(fr);
                String line;
                while((line=reader.readLine())!=null){
                    String parts[] = line.split("###");
                    int id = Integer.parseInt(parts[0]);
                    String proname = parts[1];
                    String description = parts[2];
                    double buyprice = Double.parseDouble(parts[6]);
                    double sellprice = Double.parseDouble(parts[7]);
                    double profit = sellprice - buyprice;
                    int stock = Integer.parseInt(parts[5]);
                    if(selectedProductFromTable == id ) {
                        cart.add(new Cart(cartProductId,proname,description,qty,sellprice,(qty*sellprice),(profit*qty)));
                    }
                }
                reader.close();

            }catch (IOException e){
                e.printStackTrace();
            }
            populateCartTable();
            cartProductId++;
            selectedProductFromTable = 0;
            qtyTF.setText(0+"");
            updateTotalPrice();
        }
    }
    public void updateTotalPrice(){
        double total=0;
        for(Cart item:cart){
            total = total+item.totalAmount;
        }
        totalPriceTF.setText(total+"");
        double discount = Double.parseDouble(discountTF.getText());
        double grandTotal = total - (total*(discount)/100);
        grandTotalTF.setText(grandTotal+"");

    }
    int selectedFromCart;
    public void removeFromCart(){
        selectedFromCart = cartTable.getSelectionModel().getSelectedItem().id;
        int i = 0;
        for(Cart item:cart){
            if(item.id == selectedFromCart){
                cart.remove(i);
                break;
            }
            i++;
        }
        populateCartTable();
        updateTotalPrice();
        cartProductId--;
    }
    public void populateCartTable(){
        colNoCart.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProNameCart.setCellValueFactory(new PropertyValueFactory<>("proName"));
        colQtyCart.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colAmountCart.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colTotalCart.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        cartTable.setItems(cart);
    }

    @FXML
    TextField customerName;
    public void checkOut(ActionEvent actionEvent){
        int orderNo = Integer.parseInt(orderNoTF.getText());
        String cusName = customerName.getText();
        double totalPrice = Double.parseDouble(totalPriceTF.getText());
        double discount = Double.parseDouble(discountTF.getText());
        double grandTotal = Double.parseDouble(grandTotalTF.getText());
        double profit=0;
        for(Cart item:cart){
            profit = profit+item.profit;
        }
        profit = profit - Double.parseDouble(discountTF.getText());
        String mobile = mobileTF.getText();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/database/sales-report.txt",true));
            writer.write(orderNo+"###"+orderNo+"###"+cusName+"###"+mobile+"###"+totalPrice+"###"+discount+"###"+grandTotal+"###"+profit+"###"+Session.FullName+"###"+dateFormat.format(date));
            writer.newLine();
            writer.close();
        }catch (IOException E){
            E.printStackTrace();
        }
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/database/carts/"+orderNo+".txt"));
            for(Cart cr:cart){
                writer.write(cr.id+"###"+cr.proName+"###"+cr.proDes+"###"+cr.qty+"###"+cr.amount+"###"+cr.totalAmount);
                writer.newLine();
            }
            writer.close();
        }catch (IOException E){
            E.printStackTrace();
        }

        ArrayList<Product> proList = new ArrayList<>();
        try{
            FileReader fr = new FileReader("src/database/products.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while((line=reader.readLine())!=null) {
                String parts[] = line.split("###");
                int ids = Integer.parseInt(parts[0]);
                String proname = parts[1];
                String description = parts[2];
                String category = parts[3];
                String subcat = parts[4];
                int stock = Integer.parseInt(parts[5]);
                double buyprice = Double.parseDouble(parts[6]);
                double sellprice = Double.parseDouble(parts[7]);
                proList.add(new Product(ids,proname,description,category,subcat,stock,buyprice,sellprice));
            }

            reader.close();

            for(Cart cr:cart){
                for(Product pro:proList){
                    if(pro.ProductName.equals(cr.proName)){
                        pro.currentStock = pro.currentStock - cr.qty;
                    }
                }
            }

            FileWriter fw = new FileWriter("src/database/products.txt");
            BufferedWriter writer = new BufferedWriter(fw);
            int i=1;
            for(Product ad:proList){
                writer.write(i+"###"+ad.ProductName+"###"+ad.productDescription+"###"+ad.productCategory+"###"+ad.productSubcategory+"###"+ad.currentStock+"###"+ad.buyPrice+"###"+ad.sellPrice);
                writer.newLine();
                i++;
            }
            writer.close();


        }catch (IOException E){
            E.printStackTrace();
        }

        sceneController.switchToPos(actionEvent);

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        topUsernameLabel.setText(Session.FullName);
        proSelector();
        try{
            int ord=0;
            BufferedReader reader = new BufferedReader(new FileReader("src/database/sales-report.txt"));
            String line;
            while ((line=reader.readLine())!=null){
                ord++;
            }
            orderNoTF.setText(ord+1+"");
        }catch (IOException E){
            E.printStackTrace();
        }
    }
}
