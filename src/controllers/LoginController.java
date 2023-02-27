package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import classes.admin;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {
    @FXML
    TextField usernameTxtField;
    @FXML
    PasswordField passwordTxtField;
    @FXML
    Button loginBtn;

    public void onClickLogin(ActionEvent actionEvent){
        String username = usernameTxtField.getText();
        String password = passwordTxtField.getText();
        String fullname = null;
        ArrayList<admin> adminlist = new ArrayList<>();
        try{
            FileReader fr = new FileReader("src/database/admin.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while((line=reader.readLine())!=null){
                String parts[] = line.split("###");
                int id = Integer.parseInt(parts[0]);
                String userName = parts[1];
                String pass = parts[2];
                String name = parts[3];
                String email = parts[4];
                String userType = parts[5];
                adminlist.add(new admin(id,userName,pass,name,email,userType));

            }
            reader.close();
            boolean successLogin = false;
            for (admin ad:adminlist){
                if(ad.username.equals(username) && ad.password.equals(password)){
                    fullname = ad.fullName;
                    successLogin = true;
                }
            }
            if(successLogin){
                Session session = new Session(username,fullname);
                SceneController sceneController = new SceneController();
                sceneController.switchToDashboard(actionEvent);
            }else{
                System.out.println("Login failed");
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameTxtField.setPromptText("Username");
    }
}
