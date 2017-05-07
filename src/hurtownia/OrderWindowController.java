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
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Mirti
 */
public class OrderWindowController implements Initializable {
  @FXML
    private TableColumn<OrderDetails, String>  orderDetailsNameColumn;
    @FXML
    private TableColumn<OrderDetails, String> orderDetailsQuantityColumn;
    @FXML
    private TableColumn<OrderDetails, Date> orderDetailsDateColumn;
    @FXML
    private TableColumn<OrderDetails, String> orderDetailsCommentColumn; 
    @FXML
    private TableView orderDetailsTable;   
    private int orderDetailId = OutController.getSelectedOutId();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orderDetailsNameColumn.setCellValueFactory(cellData -> cellData.getValue().orderDetailsNameProperty());
        orderDetailsQuantityColumn.setCellValueFactory(cellData -> cellData.getValue().orderDetailsQuantityProperty());
        orderDetailsDateColumn.setCellValueFactory(cellData -> cellData.getValue().orderDetailsDateProperty());
        orderDetailsCommentColumn.setCellValueFactory(cellData -> cellData.getValue().orderDetailsCommentProperty());
    } 
    
    @FXML
    private void searchOrderDetails(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Employees information
            ObservableList<OrderDetails> ordData = OrderDetailsDAO.searchOrderDetails(orderDetailId);
            //Populate Employees on TableView
            populateOrderDetails(ordData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting cargos information from DB.\n" + e);
            throw e;
        }
    }
        
    @FXML
    private void populateOrderDetails (ObservableList<OrderDetails> ordData) throws ClassNotFoundException {
        //Set items to the employeeTable
        orderDetailsTable.setItems(ordData);
    }    
    
}
