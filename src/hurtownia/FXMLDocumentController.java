/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 *
 * @author Mirti
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    
    @FXML  
    public void openSupplierForm(ActionEvent event) throws Exception {               
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SupplierForm.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.showAndWait();
                
        } catch(Exception e) {
           e.printStackTrace();
          }
    }
    
     @FXML  
    public void openOrderWindow(ActionEvent event) throws Exception {               
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderWindow.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.showAndWait();
                
        } catch(Exception e) {
           e.printStackTrace();
          }
    }
    @FXML  
    public void openFormularzZamowienie(ActionEvent event) throws Exception {               
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormularzZamowienie.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.showAndWait();
                
        } catch(Exception e) {
           e.printStackTrace();
          }
    }
    @FXML  
    public void openEdycjaDodawanieUsera(ActionEvent event) throws Exception {               
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EdycjaDodawanieUsera.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.showAndWait();
                
        } catch(Exception e) {
           e.printStackTrace();
          }
    }
    @FXML
    public void addTestData (){
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
