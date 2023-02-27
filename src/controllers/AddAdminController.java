package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import classes.admin;
import javafx.stage.Stage;

public class AddAdminController implements Initializable {
    @FXML
    TextField userTextField,passwordTextField,fullNameTextField,emailTF;
    @FXML
    ChoiceBox choiceBox;
    @FXML
    Button saveButton;
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
        sceneController.switchToPos(e);
    }
    public void switchToCategories(ActionEvent e){
        sceneController.switchToCategoris(e);
    }
    public void switchToSubcategories(ActionEvent e){
        sceneController.switchToCategoris(e);
    }
    public void switchToReport(ActionEvent e){
        sceneController.switchToCategoris(e);
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

    ObservableList<String> userTypes = FXCollections.observableArrayList("Admin");

    public void saveBtn(ActionEvent e){
        String username = userTextField.getText();
        String password = passwordTextField.getText();
        String fullName = fullNameTextField.getText();
        String email = emailTF.getText();
        String userType = (String) choiceBox.getValue();

        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/database/admin.txt"));
            String line;
            int count=0;
            while((line=reader.readLine())!=null){
                count++;
            }
            FileWriter fw = new FileWriter("src/database/admin.txt",true);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(count+1+"###"+username+"###"+password+"###"+fullName+"###"+email+"###"+userType);
            writer.newLine();
            writer.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/admin.fxml"));
            Parent root = loader.load();
            adminController ac = loader.getController();




            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();

        }catch (IOException E){
            E.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.setValue("Admin");
        choiceBox.setItems(userTypes);
    }
}
