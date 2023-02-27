package controllers.ProductsController;



import classes.Cart;
import classes.Product;
import controllers.SceneController;
import controllers.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddStock implements Initializable {
    @FXML
    Label topUsernameLabel;
    @FXML
    Button switchToDashboard,switchToPos,switchToProducts,switchToCategories,switchToSubcategories,switchToReport,switchToSetting,switchToAdmin,logout;
    @FXML
    TextField proNameTF,  stockTF,buyPriceTF,sellPriceTF, currentTF,newAddedTF;
    @FXML
    ChoiceBox categoryOP, subCatOP;
    @FXML
    TextArea descriptionTF;




    SceneController sceneController = new SceneController();

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
//        String selectedAdmin = adminTable.getSelectionModel().getSelectedItem().username;
//        ArrayList<admin> list2 = new ArrayList<>();
//        try{
//            FileReader fr = new FileReader("src/database/admin.txt");
//            BufferedReader reader = new BufferedReader(fr);
//            String line;
//            while((line=reader.readLine())!=null) {
//                String parts[] = line.split("###");
//                int id = Integer.parseInt(parts[0]);
//                String userName = parts[1];
//                String pass = parts[2];
//                String name = parts[3];
//                String email = parts[4];
//                String userType = parts[5];
//                list2.add(new admin(id, userName, pass, name, email, userType));
//            }
//
//            reader.close();
//            for(int j =0; j<list2.size(); j++){
//                if(list2.get(j).username.equals(selectedAdmin)){
//                    list2.remove(j);
//                }
//            }
//
//            FileWriter fw = new FileWriter("src/database/admin.txt");
//            BufferedWriter writer = new BufferedWriter(fw);
//            int i=1;
//            for(admin ad:list2){
//                writer.write(i+"###"+ad.username+"###"+ad.password+"###"+ad.fullName+"###"+ad.emailAddress+"###"+ad.userType);
//                writer.newLine();
//                i++;
//            }
//            writer.close();
//            populateTable();
//
//        }catch (IOException E){
//            E.printStackTrace();
//        }
//        //adminTable.getItems().removeAll(adminTable.getSelectionModel().getSelectedItem());
//        //System.out.println(adminTable.getSelectionModel().getSelectedItem().username);

    }
    public void addAdmin(ActionEvent e){
//        //sceneController.switchToAddAdmin(e);
//        try{
//            Stage stage = new Stage();
//            Parent root = FXMLLoader.load(getClass().getResource("../fxml/add-new-admin.fxml"));
//            Scene scene = new Scene(root);
//            stage.setTitle("Create New Admin");
//            stage.setResizable(false);
//            stage.setScene(scene);
//            stage.show();
//        }catch (Exception E){
//            E.printStackTrace();
//        }

    }

    public void saveBtn(ActionEvent actionEvent){
        int currentStock = Integer.parseInt(currentTF.getText());
        int newAdded = Integer.parseInt(newAddedTF.getText());
        int newStock = currentStock+newAdded;
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
            String selectedPro = (String) categoryOP.getValue();
            for(Product pro:proList){
                if(pro.ProductName.equals(selectedPro)){
                    pro.currentStock = newStock;
                    break;
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
            sceneController.switchToProducts(actionEvent);
        }catch (IOException E){
            E.printStackTrace();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        topUsernameLabel.setText(Session.userName);


        ObservableList<String> cats = FXCollections.observableArrayList();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/database/products.txt"));
            String line;
            while((line=reader.readLine())!=null){
                String [] parts = line.split("###");
                String proName = parts[1];
                cats.add(proName);
            }
            reader.close();
        }catch (IOException E){
            E.printStackTrace();
        }
        categoryOP.setItems(cats);
        categoryOP.setOnAction((event) -> {
            String selectedPro = (String) categoryOP.getValue();
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

                for(Product pro:proList){
                    if(pro.ProductName.equals(selectedPro)){
                        currentTF.setText(pro.currentStock+"");
                    }
                }


//                FileWriter fw = new FileWriter("src/database/products.txt");
//                BufferedWriter writer = new BufferedWriter(fw);
//                int i=1;
//                for(Product ad:proList){
//                    writer.write(i+"###"+ad.ProductName+"###"+ad.productDescription+"###"+ad.productCategory+"###"+ad.productSubcategory+"###"+ad.currentStock+"###"+ad.buyPrice+"###"+ad.sellPrice);
//                    writer.newLine();
//                    i++;
//                }
//                writer.close();


            }catch (IOException E){
                E.printStackTrace();
            }
        });




    }
}
