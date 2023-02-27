package controllers;

import classes.Product;
import classes.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.NetworkUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {
    @FXML
    Label topUsernameLabel,adminTF,productTF,stockLabel,profitLabel;
    @FXML
    Button switchToDashboard,switchToPos,switchToProducts,switchToCategories,switchToSubcategories,switchToReport,switchToSetting,switchToAdmin,logout;
    public TableView<Report> recentTable;
    @FXML
    TableColumn<Report,Integer> colNo;
    @FXML
    TableColumn<Report,Double> colTotal;
    @FXML
    TableColumn<Report,Double> colProfit;
    @FXML
    TableColumn<Report,String> colSalesPerson;
    @FXML
    TableColumn<Report,String> colTime;
    SceneController sceneController = new SceneController();
    Thread readThread;

    NetworkUtil networkUtilClient;
    @FXML
    TextArea messageTA;
    @FXML
    TextField messageTF;
    //Button Controllers
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

    public double countStock(){
        double count=0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/database/products.txt"));
            String line;
            while ((line=reader.readLine())!=null){
                String parts[] = line.split("###");
                double buyprice = Double.parseDouble(parts[6])*Integer.parseInt(parts[5]);
                count = count + buyprice;

            }
        }catch (IOException E){
            E.printStackTrace();
        }
        return count;
    }
    public double countSale(){
        double count=0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/database/sales-report.txt"));
            String line;
            while ((line=reader.readLine())!=null){
                String parts[] = line.split("###");
                double sale = Double.parseDouble(parts[6]);
                count = count + sale;

            }
        }catch (IOException E){
            E.printStackTrace();
        }
        return count;
    }
    public double countProfit(){
        double count=0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/database/sales-report.txt"));
            String line;
            while ((line=reader.readLine())!=null){
                String parts[] = line.split("###");
                double sale = Double.parseDouble(parts[7]);
                count = count + sale;

            }
        }catch (IOException E){
            E.printStackTrace();
        }
        return count;
    }
    public int countProduct(){
        int count=0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/database/products.txt"));
            String line;
            while ((line=reader.readLine())!=null){
                count++;
            }
        }catch (IOException E){
            E.printStackTrace();
        }
        return count;
    }

    public void populateTable(){
        colNo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalPur"));
        colProfit.setCellValueFactory(new PropertyValueFactory<>("profit"));
        colSalesPerson.setCellValueFactory(new PropertyValueFactory<>("salesPerson"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("timeStamp"));

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
        recentTable.setItems(proList);
    }
    public void readMsg(){
        System.out.println("Start");
        try {
            while(true) {
                //System.out.println("Start");
                String msgIn = (String) networkUtilClient.read();
                System.out.println(msgIn);
                String []parts = msgIn.split("###");
                messageTA.appendText(parts[0]+": " + parts[1] + "\n");
                System.out.println(msgIn);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                networkUtilClient.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void sendMsg() throws IOException {
        String text = messageTF.getText();
        networkUtilClient.write(Session.FullName+"###"+text);
        messageTA.appendText("ME: "+text+"\n");
        messageTF.setText("");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        topUsernameLabel.setText(Session.FullName);
        adminTF.setText(countProduct()+"");
        productTF.setText(countStock()+"");
        stockLabel.setText(countSale()+"");
        profitLabel.setText(countProfit()+"");
        populateTable();
        String addr = "127.0.0.1";
        int port = 44448;
        try {
            networkUtilClient = new NetworkUtil(addr,port);
            networkUtilClient.write(Session.FullName);
            readThread = new Thread(this::readMsg);
            readThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
