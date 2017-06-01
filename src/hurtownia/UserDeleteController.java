/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jon
 */
public class UserDeleteController implements Initializable {

    @FXML private Button closeButton;
    @FXML private Button updateButton;
    @FXML private Text alertText;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
            
    } 
        @FXML
    private void deleteUser (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            UserDAO.deleteUserWithId(UserController.getSelectedUserId());
            Stage stage = (Stage) updateButton.getScene().getWindow();
            stage.close();           
            //resultArea.setText("");
        } catch (SQLException e) {
            //resultArea.setText(" " + e);
            throw e;
        }
    }


    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
  
    }
    

