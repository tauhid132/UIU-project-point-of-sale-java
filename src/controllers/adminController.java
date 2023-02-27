package controllers;

import javafx.beans.Observable;
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

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import classes.admin;
public class adminController implements Initializable {
    @FXML
    Button switchToDashboard,switchToPos,switchToProducts,switchToCategories,switchToSubcategories,switchToReport,switchToSetting,switchToAdmin,logout;
    @FXML
    public TableView<admin> adminTable;
    @FXML
    TableColumn<admin,Integer> colNo;
    @FXML
    TableColumn<admin,String> colUsername;
    @FXML
    TableColumn<admin,String> colPassword;
    @FXML
    TableColumn<admin,String> colFullName;
    @FXML
    TableColumn<admin,String> colEmail;
    @FXML
    TableColumn<admin,String> colUserType;
    @FXML
    Button deleteItem,addAdminBtn;
    @FXML
    Label topUsernameLabel;

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
        String selectedAdmin = adminTable.getSelectionModel().getSelectedItem().username;
        ArrayList<admin> list2 = new ArrayList<>();
        try{
            FileReader fr = new FileReader("src/database/admin.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while((line=reader.readLine())!=null) {
                String parts[] = line.split("###");
                int id = Integer.parseInt(parts[0]);
                String userName = parts[1];
                String pass = parts[2];
                String name = parts[3];
                String email = parts[4];
                String userType = parts[5];
                list2.add(new admin(id, userName, pass, name, email, userType));
            }

            reader.close();
            for(int j =0; j<list2.size(); j++){
                if(list2.get(j).username.equals(selectedAdmin)){
                    list2.remove(j);
                }
            }

            FileWriter fw = new FileWriter("src/database/admin.txt");
            BufferedWriter writer = new BufferedWriter(fw);
            int i=1;
            for(admin ad:list2){
                writer.write(i+"###"+ad.username+"###"+ad.password+"###"+ad.fullName+"###"+ad.emailAddress+"###"+ad.userType);
                writer.newLine();
                i++;
            }
            writer.close();
            populateTable();

        }catch (IOException E){
            E.printStackTrace();
        }
        //adminTable.getItems().removeAll(adminTable.getSelectionModel().getSelectedItem());
        //System.out.println(adminTable.getSelectionModel().getSelectedItem().username);

    }
    public void addAdmin(ActionEvent e){
        //sceneController.switchToAddAdmin(e);
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/add-new-admin.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Create New Admin");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }catch (Exception E){
            E.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        topUsernameLabel.setText(Session.FullName);
        populateTable();
        //adminTable.getColumns().addAll(colNo,colUsername,colPassword,colStatus);
        //        Object selectedItems = adminTable.getSelectionModel().getSelectedItems().get(0);
//        String first_Column = selectedItems.toString().split(",")[0].substring(1);
//        System.out.println(first_Column);
    }


    public void populateTable(){
        colNo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        colUserType.setCellValueFactory(new PropertyValueFactory<>("userType"));

        System.out.println("Clledd");

        ObservableList<admin> list = FXCollections.observableArrayList();
        try{
            FileReader fr = new FileReader("src/database/admin.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while((line=reader.readLine())!=null) {
                String parts[] = line.split("###");
                int id = Integer.parseInt(parts[0]);
                String userName = parts[1];
                String pass = parts[2];
                String name = parts[3];
                String email = parts[4];
                String userType = parts[5];
                list.add(new admin(id, userName, pass, name, email, userType));
            }
            reader.close();

        }catch (IOException e){
            e.printStackTrace();
        }
        //list.add(new admin(1,"Tauhid","1235g",1));
        //list.add(new admin(2,"Shimul","12345",2));
        adminTable.setItems(list);
    }
    public void refreshBtn(ActionEvent e){
        sceneController.switchToAdmin(e);
    }
}

