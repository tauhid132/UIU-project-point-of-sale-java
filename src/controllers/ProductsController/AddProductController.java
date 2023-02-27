package controllers.ProductsController;



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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {
    @FXML
    Label topUsernameLabel;
    @FXML
    Button switchToDashboard,switchToPos,switchToProducts,switchToCategories,switchToSubcategories,switchToReport,switchToSetting,switchToAdmin,logout;
    @FXML
    TextField proNameTF,  stockTF,buyPriceTF,sellPriceTF;
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
    public void backBtn(ActionEvent actionEvent){
        sceneController.switchToProducts(actionEvent);
    }

    public void saveBtn(ActionEvent actionEvent){
        String proName = proNameTF.getText();
        String description = descriptionTF.getText();
        String selectedCat = (String) categoryOP.getValue();
        String selectedSubCat = (String) subCatOP.getValue();
        int stock = Integer.parseInt(stockTF.getText());
        double buyPrice = Double.parseDouble(buyPriceTF.getText());
        double sellPrice = Double.parseDouble(sellPriceTF.getText());

        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/database/products.txt"));
            String line;
            int count=0;
            while((line=reader.readLine())!=null){
                count++;
            }
            FileWriter fw = new FileWriter("src/database/products.txt",true);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(count+1+"###"+proName+"###"+description+"###"+selectedCat+"###"+selectedSubCat+"###"+stock+"###"+buyPrice+"###"+sellPrice);
            writer.newLine();
            writer.close();
            //Stage stage = (Stage) saveBtn().getScene().getWindow();
            //stage.close();
            sceneController.switchToProducts(actionEvent);

        }catch (IOException E){
            E.printStackTrace();
        }
    }

    public void changedCat(){
        System.out.println(categoryOP.getValue());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        topUsernameLabel.setText(Session.userName);


        ObservableList<String> cats = FXCollections.observableArrayList();
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
        categoryOP.setItems(cats);
        categoryOP.setOnAction((event) -> {
            ObservableList<String> subCats = FXCollections.observableArrayList();
            try{
                String selectedCat = (String) categoryOP.getValue();
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

    }
}
