/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * 
 * Class to provide permission to users group
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Tab tabWyszukiwarka;
    @FXML private Tab tabWydanie;
    @FXML private Tab tabPrzyjecie;
    @FXML private Tab tabZarzadzanie1;
    @FXML private Tab tabKontrahenci;
    @FXML private Tab tabRaporty; 
    @FXML private Tab tabZarzadzanie;
    @FXML private Label permissionStatusField;
    @FXML private Label userNameField;    

    /**
     * After login this method set disable on tabs adequate to user permission
     * 
     * @param url - unused
     * @param rb  - unused
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        switch (Connect.getCurrentUser()[3]) {
            case "Pracownik":
                tabKontrahenci.setDisable(true);
                tabRaporty.setDisable(true);
                tabZarzadzanie1.setDisable(true);
                tabZarzadzanie.setDisable(true);  
                permissionStatusField.setText(Connect.getCurrentUser()[3]);
                userNameField.setText(Connect.getCurrentUser()[1]+" "+Connect.getCurrentUser()[2]);
                break;
            case "Ksiegowy":
                tabWydanie.setDisable(true);
                tabPrzyjecie.setDisable(true);
                tabZarzadzanie.setDisable(true);
                permissionStatusField.setText(Connect.getCurrentUser()[3]);
                userNameField.setText(Connect.getCurrentUser()[1]+" "+Connect.getCurrentUser()[2]);
                break;
            default:
                permissionStatusField.setText(Connect.getCurrentUser()[3]);
                userNameField.setText(Connect.getCurrentUser()[1]+" "+Connect.getCurrentUser()[2]);
                break;
        }
        

    }

}
