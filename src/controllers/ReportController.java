package controllers;

import classes.Product;
import classes.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    @FXML
    Label topUsernameLabel;
    @FXML
    Button switchToDashboard,switchToPos,switchToProducts,switchToCategories,switchToSubcategories,switchToReport,switchToSetting,switchToAdmin,logout;

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


    public TableView<Report> reportTable;
    @FXML
    TableColumn<Report,Integer> colNo;
    @FXML
    TableColumn<Report,Integer> colOrderNo;
    @FXML
    TableColumn<Product,String> colCusName;
    @FXML
    TableColumn<Product,String> colMobile;
    @FXML
    TableColumn<Product,Double> colTotal;
    @FXML
    TableColumn<Product,Double> colDiscount;
    @FXML
    TableColumn<Product,Double> colGrandTotal;
    @FXML
    TableColumn<Product,Double> colProfit;
    @FXML
    TableColumn<Product,String> colSalesPerson;
    @FXML
    TableColumn<Product,String> colTimestamp;


    public void populateTable(){
        colNo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colOrderNo.setCellValueFactory(new PropertyValueFactory<>("orderNo"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobileNo"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalPur"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colGrandTotal.setCellValueFactory(new PropertyValueFactory<>("grandTotal"));
        colProfit.setCellValueFactory(new PropertyValueFactory<>("profit"));
        colSalesPerson.setCellValueFactory(new PropertyValueFactory<>("salesPerson"));
        colTimestamp.setCellValueFactory(new PropertyValueFactory<>("timeStamp"));

        ObservableList<Report> proList = FXCollections.observableArrayList();
        try{
            FileReader fr = new FileReader("src/database/sales-report.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while((line=reader.readLine())!=null){
                String parts[] = line.split("###");
                int id = Integer.parseInt(parts[0]);
                int orderNo = Integer.parseInt(parts[1]);
                String cusName = parts[2];
                String mobile = parts[3];
                double price = Double.parseDouble(parts[4]);
                double discount = Double.parseDouble(parts[5]);
                double grand = Double.parseDouble(parts[6]);
                double profit = Double.parseDouble(parts[7]);
                String salesPerson = parts[8];
                String time = parts[9];
                proList.add(new Report(id,orderNo,cusName,mobile,price,discount,grand,profit,salesPerson,time));
            }
            reader.close();

        }catch (IOException e){
            e.printStackTrace();
        }
        reportTable.setItems(proList);
    }
    public int selectedOrderNo;
    public void viewReport(ActionEvent actionEvent) throws IOException {
        selectedOrderNo = reportTable.getSelectionModel().getSelectedItem().orderNo;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/view-report.fxml"));
        Parent root = loader.load();
        viewReport vr = loader.getController();
        vr.setData(selectedOrderNo);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("View Report");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTable();
        topUsernameLabel.setText(Session.FullName);
    }
}
