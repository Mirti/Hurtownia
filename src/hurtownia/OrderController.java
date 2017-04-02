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
public class OrderController implements Initializable {
 
   @FXML
    private TableColumn<Out, String> orderCustomerColumn;
    @FXML
    private TableColumn<Out, String> orderUserColumn;
    @FXML
    private TableColumn<Out, String> orderCommentColumn;      
    @FXML
    private TableView orderTable;   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orderCustomerColumn.setCellValueFactory(cellData -> cellData.getValue().outCustomerProperty());
        orderUserColumn.setCellValueFactory(cellData -> cellData.getValue().outUserProperty());
        orderCommentColumn.setCellValueFactory(cellData -> cellData.getValue().outCommentProperty());
    } 
    
    @FXML
    private void searchOrder(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
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
        
    @FXML
    private void populateOrder (ObservableList<Out> rptData) throws ClassNotFoundException {
        //Set items to the ContractorTable
        orderTable.setItems(rptData);
    }  
    
     @FXML  
    public void openFormularzZamowien(ActionEvent event) throws Exception {               
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
