/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jon
 */
public class UserController implements Initializable {
    
    @FXML
    private TableColumn<User, String> usrFirstNameColumn;
    @FXML
    private TableColumn<User, String> usrLastNameColumn;
    @FXML
    private TableColumn<User, String> usrPermissionsColumn;
    @FXML
    private TableColumn<User, String> usrLoginColumn;      
    @FXML
    private TableView UserTable;   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usrFirstNameColumn.setCellValueFactory(cellData -> cellData.getValue().UserFirstNameProperty());
        usrLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().UserLastNameProperty());
        usrPermissionsColumn.setCellValueFactory(cellData -> cellData.getValue().UserPermissionsProperty());
        usrLoginColumn.setCellValueFactory(cellData -> cellData.getValue().UserPermissionsProperty());
    } 
    
    @FXML
    private void searchUser(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Contractorsinformation
            ObservableList<User> UserData = UserDAO.searchUser();
            //Populate Contractors on TableView
            populateUser(UserData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting Contractors information from DB.\n" + e);
            throw e;
        }
    }
        
    @FXML
    private void populateUser (ObservableList<User> usrData) throws ClassNotFoundException {
        //Set items to the ContractorTable
        UserTable.setItems(usrData);
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
    
}
