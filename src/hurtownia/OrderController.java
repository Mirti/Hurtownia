/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class for OrderWindow.fxml
 *
 * @author Jon
 */
public class OrderController implements Initializable {
 
   @FXML
    private TableColumn<Out, String> orderCustomerColumn;
    @FXML
    private TableColumn<Out, String> orderUserColumn;
    @FXML
    private TableColumn<Out, String> orderCommentColumn;
    @FXML
    private TableColumn<Out,String> orderValueColumn;
    @FXML
    private TableView orderTable;  
    @FXML
    private static int selectedOrderId;
    
    public static int getSelectedOrderId() {
        return selectedOrderId;
    }
    
     /**
     * Method to clear selected value ID
     */
    public static void clearID(){
        selectedOrderId =0;
    }
    

    /**
     * Method to set values to table headers
     * 
     * @param url - unused
     * @param rb - unused
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orderCustomerColumn.setCellValueFactory(cellData -> cellData.getValue().outCustomerProperty());
        orderUserColumn.setCellValueFactory(cellData -> cellData.getValue().outUserProperty());
        orderCommentColumn.setCellValueFactory(cellData -> cellData.getValue().outCommentProperty());
        orderValueColumn.setCellValueFactory(cellData -> cellData.getValue().outValueProperty());        
        orderTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Out>() {
            public void onChanged(ListChangeListener.Change<? extends Out> c) {

                for (Out o : c.getList()) {
                    selectedOrderId = o.getOutId();
                }
            
            }
        });
        orderTable.setOnMouseClicked(new EventHandler<MouseEvent>() {

    /**
     * Handle double click on row and open window with details of order
     * 
     * @param click - Click with mouse
     */
    @Override
    public void handle(MouseEvent click) {

        if (click.getClickCount() == 2) {
        try {
            OutController.clearID();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderWindow.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.showAndWait();
                
        } catch(Exception e) {
           e.printStackTrace();
          }
        }
    }
    });
       
    } 
    
    /**
     * Method to take data from database and show it's values in table
     * 
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when occurs problem with using OutDAO class
     */
    @FXML
    private void searchOrder() throws SQLException, ClassNotFoundException {
        try {
            //Get all Contractorsinformation
            ObservableList<Out> OutData = OutDAO.searchOut();
            //Populate Contractors on TableView
            populateOrder(OutData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting Contractors information from DB.\n" + e);
            throw e;
        }
    }
    
    /**
     * Method to set values from list in parametres into table
     * 
     * @param rptData - List of objects to show into table
     */
    @FXML
    private void populateOrder (ObservableList<Out> rptData) {
        //Set items to the ContractorTable
        orderTable.setItems(rptData);
    }  
    
    /**
     * Method to open FXML file with order formular
     * 
     * @throws Exception - Throws when occurs unexpected problem
     */
     @FXML  
    public void openFormularzZamowien() throws Exception {               
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
}
