package controllers.SubCategoriesController;


import classes.SubCategory;
import classes.admin;
import controllers.SceneController;
import controllers.Session;
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

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import classes.Category;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SubCategoriesController implements Initializable {
    @FXML
    Label topUsernameLabel;
    @FXML
    Button switchToDashboard,switchToPos,switchToProducts,switchToCategories,switchToSubcategories,switchToReport,switchToSetting,switchToAdmin,logout;
    @FXML
    public TableView<SubCategory> catTable;
    @FXML
    TableColumn<SubCategory,Integer> noCol;
    @FXML
    TableColumn<SubCategory,String> catNameCol;
    @FXML
    TableColumn<SubCategory,String> colParentCat;

    //Start sidebar Controllers
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
    //End Of sidebar Controllers


    public void addNewCat(ActionEvent e){
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../fxml/add-new-SubCategory.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Create New Sub Categories");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }catch (Exception E){
            E.printStackTrace();
        }
    }
    public void deleteItem(ActionEvent e){
        String selectedCat = catTable.getSelectionModel().getSelectedItem().subCatName;
        ArrayList<SubCategory> list2 = new ArrayList<>();
        try{
            FileReader fr = new FileReader("src/database/sub-categories.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while((line=reader.readLine())!=null) {
                String parts[] = line.split("###");
                int id = Integer.parseInt(parts[0]);
                String SubcatName = parts[1];
                String parentCatName = parts[2];
                list2.add(new SubCategory(id, SubcatName,parentCatName));
            }

            reader.close();
            for(int j =0; j<list2.size(); j++){
                if(list2.get(j).subCatName.equals(selectedCat)){
                    list2.remove(j);
                }
            }

            FileWriter fw = new FileWriter("src/database/sub-categories.txt");
            BufferedWriter writer = new BufferedWriter(fw);
            int i=1;
            for(SubCategory ad:list2){
                writer.write(i+"###"+ad.subCatName+"###"+ad.parentCatName);
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
    public void pupulateCategoriesTable(){
        noCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        catNameCol.setCellValueFactory(new PropertyValueFactory<>("SubCatName"));
        colParentCat.setCellValueFactory(new PropertyValueFactory<>("parentCatName"));
        ObservableList<SubCategory> categoriesList = FXCollections.observableArrayList();
        try{
            FileReader fr = new FileReader("src/database/sub-categories.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while((line=reader.readLine())!=null){
                String parts[] = line.split("###");
                int id = Integer.parseInt(parts[0]);
                String catName = parts[1];
                String parentCat = parts[2];
                categoriesList.add(new SubCategory(id,catName,parentCat));
            }
            reader.close();

        }catch (IOException e){
            e.printStackTrace();
        }

        catTable.setItems(categoriesList);
    }

    public void refreshBtn(ActionEvent e){
        sceneController.switchToSubCategories(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pupulateCategoriesTable();
        topUsernameLabel.setText(Session.FullName);
    }
}
