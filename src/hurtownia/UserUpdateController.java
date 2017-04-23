/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jon
 */
public class UserUpdateController implements Initializable {

    @FXML
    private TextField userFirstNameText;
    @FXML
    private TextField userLastNameText;
    @FXML
    private TextField userLoginText;
    @FXML
    private TextField userPasswordText;    
    @FXML
    private ComboBox userPermissionText;
    @FXML
    private TextArea userCommentText;
    @FXML    
    private Text alertText;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String userName;
        String userSurname;
        String userPermission;
        String userLogin;
        String userPassword;
        String userComments;
        
        int userID = UserController.getSelectedUserId();
        try {
            ResultSet rs = UserDAO.getUserData(userID);
            userName = rs.getString("imie");
            userSurname = rs.getString("nazwisko");
            userPermission = rs.getString("uprawnienia");
            userLogin = rs.getString("login");
            userPassword = rs.getString("haslo");
            userComments = rs.getString("uwagi");
            
            userFirstNameText.setText(userName);
            userLastNameText.setText(userSurname);
            userPermissionText.setValue(userPermission);
            userLoginText.setText(userLogin);
            userPasswordText.setText(userPassword);
            userCommentText.setText(userComments);
            
        } catch (SQLException ex) {
            System.out.print("na ten błąd nie patrzcie, on jest do dodawania do formualrza");
           // Logger.getLogger(UserUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML    
    private void updateUser(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            if (userFirstNameText.equals("") || userLastNameText.equals("") || userLoginText.equals("") || userPasswordText.equals("") || userPasswordText.equals("")) {
                alertText.setText("Wypełnij wymagane pola!");
            } else {
                UserDAO.updateUser(UserController.getSelectedUserId(), userFirstNameText.getText(), userLastNameText.getText(), userPermissionText.getSelectionModel().getSelectedItem().toString(), userLoginText.getText(), userPasswordText.getText(), userCommentText.getText());
                alertText.setText("udało się");
            }
        } catch (SQLException e) {
            alertText.setText("Problem occurred while updating: " + e);
        }
    }    
    
}
