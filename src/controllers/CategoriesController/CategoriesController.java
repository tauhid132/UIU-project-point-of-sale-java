package controllers.CategoriesController;

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

public class CategoriesController implements Initializable {
    @FXML
    Label topUsernameLabel;
    @FXML
    Button switchToDashboard,switchToPos,switchToProducts,switchToCategories,switchToSubcategories,switchToReport,switchToSetting,switchToAdmin,logout;
    @FXML
    public TableView<Category> catTable;
    @FXML
    TableColumn<Category,Integer> noCol;
    @FXML
    TableColumn<Category,String> catNameCol;

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

    public void refreshBtn(ActionEvent e){
        sceneController.switchToCategoris(e);
    }
    public void addNewCat(ActionEvent e){
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../fxml/add-new-category.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Create New Admin");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }catch (Exception E){
            E.printStackTrace();
        }
    }
    public void deleteItem(ActionEvent e){
        String selectedCat = catTable.getSelectionModel().getSelectedItem().catName;
        ArrayList<Category> list2 = new ArrayList<>();
        try{
            FileReader fr = new FileReader("src/database/categories.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while((line=reader.readLine())!=null) {
                String parts[] = line.split("###");
                int id = Integer.parseInt(parts[0]);
                String catName = parts[1];
                list2.add(new Category(id, catName));
            }

            reader.close();
            for(int j =0; j<list2.size(); j++){
                if(list2.get(j).catName.equals(selectedCat)){
                    list2.remove(j);
                }
            }

            FileWriter fw = new FileWriter("src/database/categories.txt");
            BufferedWriter writer = new BufferedWriter(fw);
            int i=1;
            for(Category ad:list2){
                writer.write(i+"###"+ad.catName);
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
        catNameCol.setCellValueFactory(new PropertyValueFactory<>("catName"));
        ObservableList<Category> categoriesList = FXCollections.observableArrayList();
        try{
            FileReader fr = new FileReader("src/database/categories.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while((line=reader.readLine())!=null){
                String parts[] = line.split("###");
                int id = Integer.parseInt(parts[0]);
                String catName = parts[1];
                categoriesList.add(new Category(id,catName));
            }
            reader.close();

        }catch (IOException e){
            e.printStackTrace();
        }

        catTable.setItems(categoriesList);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pupulateCategoriesTable();
        topUsernameLabel.setText(Session.FullName);

    }
}
