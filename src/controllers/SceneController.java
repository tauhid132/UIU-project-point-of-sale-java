package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class SceneController {
    Stage stage;
    Scene scene;
    Parent root;
    public void switchToDashboard(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("../fxml/mydashboard.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Dashboard");
            stage.show();
        }catch (Exception exp){

        }
    }
    public void switchToPos(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("../fxml/pos.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("CART");
            stage.show();
        }catch (Exception exp){

        }
    }
    public void switchToCategoris(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("../fxml/categories.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Categories");
            stage.show();
        }catch (Exception exp){

        }
    }
    public void switchToSubCategories(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("../fxml/sub-categories.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Sub-Categories");
            stage.show();
        }catch (Exception exp){

        }
    }
    public void switchToProducts(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("../fxml/products.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Products");
            stage.show();
        }catch (Exception exp){

        }
    }
    public void switchToReport(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("../fxml/report.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Report");
            stage.show();
        }catch (Exception exp){

        }
    }
    public void switchToAdmin(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("../fxml/admin.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Admin");
            stage.show();
        }catch (Exception exp){

        }
    }
    public void switchToSettings(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("../fxml/pos.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception exp){

        }
    }
    public void logout(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("../fxml/login.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception exp){

        }
    }
    public void switchToAddAdmin(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("../fxml/add-admin.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Add Admin");
            stage.show();
        }catch (Exception exp){

        }
    }
    public void switchToAddProduct(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("../fxml/add-product.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Add Product");
            stage.show();
        }catch (Exception exp){

        }
    }
    public void switchToAddStock(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("../fxml/add-stock.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Add Stock");
            stage.show();
        }catch (Exception exp){

        }
    }

}
