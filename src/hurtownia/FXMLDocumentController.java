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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        switch (Polaczenie.getCurrentUser()[3]) {
            case "Pracownik":
                tabKontrahenci.setDisable(true);
                tabRaporty.setDisable(true);
                tabZarzadzanie1.setDisable(true);
                tabZarzadzanie.setDisable(true);  
                permissionStatusField.setText(Polaczenie.getCurrentUser()[3]);
                userNameField.setText(Polaczenie.getCurrentUser()[1]+" "+Polaczenie.getCurrentUser()[2]);
                break;
            case "Ksiegowy":
                tabWydanie.setDisable(true);
                tabPrzyjecie.setDisable(true);
                tabZarzadzanie.setDisable(true);
                permissionStatusField.setText(Polaczenie.getCurrentUser()[3]);
                userNameField.setText(Polaczenie.getCurrentUser()[1]+" "+Polaczenie.getCurrentUser()[2]);
                break;
            default:
                permissionStatusField.setText(Polaczenie.getCurrentUser()[3]);
                userNameField.setText(Polaczenie.getCurrentUser()[1]+" "+Polaczenie.getCurrentUser()[2]);
                break;
        }
        
        
        
       
        
    }

}
