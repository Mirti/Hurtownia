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
 * FXML Controller class
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
    private TableView outTable;   
    @FXML
    private static int selectedOutId;
    
    public static int getSelectedOutId(){
        return selectedOutId;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        outCustomerColumn.setCellValueFactory(cellData -> cellData.getValue().outCustomerProperty());
        outUserColumn.setCellValueFactory(cellData -> cellData.getValue().outUserProperty());
        outCommentColumn.setCellValueFactory(cellData -> cellData.getValue().outCommentProperty());
        
        outTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Out>() {
            public void onChanged(ListChangeListener.Change<? extends Out> c) {

                for (Out o : c.getList()) {
                    selectedOutId = o.getOutId();
                    
                }
            
            }
        });
        outTable.setOnMouseClicked(new EventHandler<MouseEvent>() {

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
    
    @FXML
    private void searchOut(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
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
        
    @FXML
    private void populateOut (ObservableList<Out> rptData) throws ClassNotFoundException {
        //Set items to the ContractorTable
        outTable.setItems(rptData);
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
    
}
