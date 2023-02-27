package controllers.CategoriesController;


import controllers.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class AddCategoryController implements Initializable {
    @FXML
    TextField categoryNameTF;

    @FXML
    Button saveButton;




    public void saveBtn(ActionEvent e){
        String catName = categoryNameTF.getText();

        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/database/categories.txt"));
            String line;
            int count=0;
            while((line=reader.readLine())!=null){
                count++;
            }
            FileWriter fw = new FileWriter("src/database/categories.txt",true);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(count+1+"###"+catName);
            writer.newLine();
            writer.close();

            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();

        }catch (IOException E){
            E.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

