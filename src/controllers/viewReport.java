package controllers;

import classes.Cart;
import classes.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class viewReport {
    @FXML
    TextField orderTF, cusNameTF, mobileTF;
    @FXML
    public TableView<Cart> cartTable;
    @FXML
    TableColumn<Cart,Integer> colNoCart;
    @FXML
    TableColumn<Cart,String> colProNameCart;
    @FXML
    TableColumn<Cart,String> colProDes;
    @FXML
    TableColumn<Cart,Integer> colQtyCart;
    @FXML
    TableColumn<Cart,Double> colAmountCart;
    @FXML
    TableColumn<Cart,Double> colTotalCart;

    public void setData(int orderNo){
        colNoCart.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProNameCart.setCellValueFactory(new PropertyValueFactory<>("proName"));
        colProDes.setCellValueFactory(new PropertyValueFactory<>("proDes"));
        colQtyCart.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colAmountCart.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colTotalCart.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        ObservableList<Cart> proList = FXCollections.observableArrayList();
        try{
            FileReader fr = new FileReader("src/database/carts/"+orderNo+".txt");
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while((line=reader.readLine())!=null){
                String parts[] = line.split("###");
                int id = Integer.parseInt(parts[0]);
                String proName = parts[1];
                String proDes = parts[2];
                int qty = Integer.parseInt(parts[3]);
                double amount = Double.parseDouble(parts[4]);
                double total = Double.parseDouble(parts[5]);
                proList.add(new Cart(id,proName,proDes,qty,amount,total,0.0));
            }
            reader.close();
            cartTable.setItems(proList);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
