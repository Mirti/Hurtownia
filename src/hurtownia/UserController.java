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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class to provide methods for user management
 *
 * @author Jon, Kinga
 */
public class UserController implements Initializable {

    @FXML
    private TextField userFirstNameText;
    @FXML
    private TextField userLastNameText;
    @FXML
    private TextField userLoginText;
    @FXML
    private ComboBox userPermissionText;
    @FXML
    private TableColumn<User, String> usrFirstNameColumn;
    @FXML
    private TableColumn<User, String> usrLastNameColumn;
    @FXML
    private TableColumn<User, String> usrPermissionsColumn;
    @FXML
    private TableColumn<User, String> usrLoginColumn;
    @FXML
    private TableColumn<User, String> usrCommentColumn;
    @FXML
    private TableView userTable;
    @FXML
    private static int selectedUserId;

    /**
     *
     * @return ID of selected in table user
     */
    public static int getSelectedUserId() {
        return selectedUserId;
    }

    /**
     * Method to set values to table headers
     *
     * @param url - unused
     * @param rb - unused
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usrFirstNameColumn.setCellValueFactory(cellData -> cellData.getValue().UserFirstNameProperty());
        usrLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().UserLastNameProperty());
        usrPermissionsColumn.setCellValueFactory(cellData -> cellData.getValue().UserPermissionsProperty());
        usrLoginColumn.setCellValueFactory(cellData -> cellData.getValue().UserLoginProperty());
        usrCommentColumn.setCellValueFactory(cellData -> cellData.getValue().UserCommentProperty());

        userTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<User>() {

            /**
             * Add ID to every row and get it on click
             *
             * @param c - Listener
             */
            public void onChanged(ListChangeListener.Change<? extends User> c) {

                for (User u : c.getList()) {
                    selectedUserId = u.getUserId();
                }

            }
        });
    }

    /**
     * Get data about users and show it on table
     *
     * @throws SQLException - Throws when occurs error with SQL query
     * @throws ClassNotFoundException - Throws when occurs problem with using
     * another class
     */
    @FXML
    private void searchUser() throws SQLException, ClassNotFoundException {
        try {
            //Get all Contractorsinformation
            ObservableList<User> UserData = UserDAO.searchUser();
            //Populate Contractors on TableView
            populateUser(UserData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting Contractors information from DB.\n" + e);
            throw e;
        }
    }

    /**
     * Method to show items in table
     * 
     * @param usrData - List of objects to show in table
     * @throws ClassNotFoundException 
     */
    @FXML
    private void populateUser(ObservableList<User> usrData) {
        //Set items to the ContractorTable
        userTable.setItems(usrData);
    }

    /**
     * Method to show window with adding user possibility
     * 
     * @throws Exception - Throws when occurs unexpected error
     */
    @FXML
    public void openEdycjaDodawanieUsera() throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EdycjaDodawanieUsera.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to show window with editing user possibility
     * 
     * @throws Exception - Throws when occurs unexpected error
     */
    @FXML
    public void openUserUpdate() throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserUpdateForm.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to show window with user delete confirmation
     * 
     * @throws Exception 
     */
    @FXML
    public void openPotwierdzenieUsuwaniaUsera() throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PotwierdzenieUsuwaniaUsera.fxml"));
            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root2));
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
