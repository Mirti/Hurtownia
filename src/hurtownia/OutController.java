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
 * FXML Controller class to provide method to OutTab
 *
 * @author Jon
 */
public class OutController implements Initializable {

    @FXML
    private TableColumn<Out, String> outCustomerColumn;
    @FXML
    private TableColumn<Out, String> outUserColumn;
    @FXML
    private TableColumn<Out, String> outCommentColumn; 
    @FXML
    private TableColumn<Out,String> outValueColumn;
    @FXML
    private TableView outTable;   
    @FXML
    private static int selectedOutId;
    
    /**
     * 
     * @return ID of order selected in table
     */
    public static int getSelectedOutId(){
        return selectedOutId;
    }
    
    /**
     * Method to set values to table headers and set ID's to all row in table
     * 
     * @param url - unused
     * @param rb - unused
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        outCustomerColumn.setCellValueFactory(cellData -> cellData.getValue().outCustomerProperty());
        outUserColumn.setCellValueFactory(cellData -> cellData.getValue().outUserProperty());
        outCommentColumn.setCellValueFactory(cellData -> cellData.getValue().outCommentProperty());
        outValueColumn.setCellValueFactory(cellData -> cellData.getValue().outValueProperty());
        
        outTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Out>() {
            public void onChanged(ListChangeListener.Change<? extends Out> c) {

                for (Out o : c.getList()) {
                    selectedOutId = o.getOutId();
                    
                }
            
            }
        });
        outTable.setOnMouseClicked(new EventHandler<MouseEvent>() {

    /**
     * Handle double click on row and open window with details of order
     * 
     * @param click - Click with mouse
     */
    @Override
    public void handle(MouseEvent click) {

        if (click.getClickCount() == 2) {
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
    }
    });
    } 
    
    /**
     * Method to getting data form database and show it in table
     * 
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when occurs problem with OutDAO class
     */
    @FXML
    private void searchOut() throws SQLException, ClassNotFoundException {
        try {
            //Get all Contractorsinformation
            ObservableList<Out> OutData = OutDAO.searchNewOut();
            //Populate Contractors on TableView
            populateOut(OutData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting Contractors information from DB.\n" + e);
            throw e;
        }
    }
        
    /**
     * Method to show values in table
     * 
     * @param rptData - List of objects to show in table
     */
    @FXML
    private void populateOut (ObservableList<Out> rptData) {
        //Set items to the ContractorTable
        outTable.setItems(rptData);
    }  
    
    /**
     * Method to open OrderWindow.fxml
     * 
     * @throws Exception - To show information about unexpected error
     */
    @FXML  
    public void openOrderWindow() throws Exception {               
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
    
}
