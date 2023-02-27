package controllers.ProductsController;



import classes.Category;
import classes.Product;
import classes.admin;
import controllers.SceneController;
import controllers.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    @FXML
    Label topUsernameLabel;
    @FXML
    Button switchToDashboard,switchToPos,switchToProducts,switchToCategories,switchToSubcategories,switchToReport,switchToSetting,switchToAdmin,logout;
    //
    @FXML
    public TableView<Product> proTable;
    @FXML
    TableColumn<Product,Integer> colNo;
    @FXML
    TableColumn<Product,String> colProName;
    @FXML
    TableColumn<Product,String> colDescription;
    @FXML
    TableColumn<Product,String> colCategory;
    @FXML
    TableColumn<Product,String> colSubCategory;
    @FXML
    TableColumn<Product,Double> colBuyPrice;
    @FXML
    TableColumn<Product,Double> colSellPrice;
    @FXML
    TableColumn<Product,Integer> colStock;
//
    SceneController sceneController = new SceneController();
    //
//
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


    public void deleteItem(ActionEvent e){
        int selectedId = proTable.getSelectionModel().getSelectedItem().id;
        ArrayList<Product> list2 = new ArrayList<>();
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
                list2.add(new Product(ids,proname,description,category,subcat,stock,buyprice,sellprice));
            }

            reader.close();
            for(int j =0; j<list2.size(); j++){
                if(list2.get(j).id == selectedId){
                    list2.remove(j);
                }
            }

            FileWriter fw = new FileWriter("src/database/products.txt");
            BufferedWriter writer = new BufferedWriter(fw);
            int i=1;
            for(Product ad:list2){
                writer.write(i+"###"+ad.ProductName+"###"+ad.productDescription+"###"+ad.productCategory+"###"+ad.productSubcategory+"###"+ad.currentStock+"###"+ad.buyPrice+"###"+ad.sellPrice);
                writer.newLine();
                i++;
            }
            writer.close();
            pupulateCategoriesTable();

        }catch (IOException E){
            E.printStackTrace();
        }
        //adminTable.getItems().removeAll(adminTable.getSelectionModel().getSelectedItem());
        //System.out.println(adminTable.getSelectionModel().getSelectedItem().username);

    }
    public void addAdmin(ActionEvent e){
        sceneController.switchToAddProduct(e);
    }
    public void addStock(ActionEvent e){
        sceneController.switchToAddStock(e);
    }


    public void pupulateCategoriesTable(){
        colNo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("productCategory"));
        colSubCategory.setCellValueFactory(new PropertyValueFactory<>("productSubcategory"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("currentStock"));
        colBuyPrice.setCellValueFactory(new PropertyValueFactory<>("buyPrice"));
        colSellPrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));




        ObservableList<Product> proList = FXCollections.observableArrayList();
        try{
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
                proList.add(new Product(ids,proname,description,category,subcat,stock,buyprice,sellprice));
            }
            reader.close();

        }catch (IOException e){
            e.printStackTrace();
        }

        proTable.setItems(proList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        topUsernameLabel.setText(Session.userName);
        pupulateCategoriesTable();
    }
}
