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
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 *
 *
 * FXML Controller class to provide method for OrderWindow
 */
public class OrderWindowController implements Initializable {

    @FXML
    private TableColumn<OrderDetails, String> orderDetailsNameColumn;
    @FXML
    private TableColumn<OrderDetails, String> orderDetailsQuantityColumn;
    @FXML
    private TableColumn<OrderDetails, Date> orderDetailsDateColumn;
    @FXML
    private TableColumn<OrderDetails, String> orderDetailsCommentColumn;
    @FXML
    private TableView orderDetailsTable;
    @FXML
    private Label lblOrderId;
    @FXML
    private TextArea taComments;

    private int orderDetailId;

    /**
     * Method to set value on table headers
     *
     * @param url - unused
     * @param rb - unused
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orderDetailsNameColumn.setCellValueFactory(cellData -> cellData.getValue().orderDetailsNameProperty());
        orderDetailsQuantityColumn.setCellValueFactory(cellData -> cellData.getValue().orderDetailsQuantityProperty());
        orderDetailsDateColumn.setCellValueFactory(cellData -> cellData.getValue().orderDetailsDateProperty());
        orderDetailsCommentColumn.setCellValueFactory(cellData -> cellData.getValue().orderDetailsCommentProperty());

        
        int parentSceneControl;
        parentSceneControl = OrderController.getSelectedOrderId();
        if (parentSceneControl == 0) {
            orderDetailId = OutController.getSelectedOutId();
        }  else orderDetailId=OrderController.getSelectedOrderId();
        
        lblOrderId.setText("Zamówienie: #" + orderDetailId);

   

}

/**
 * Method to get data about Order Details from database
 *
 * @throws SQLException - Throws when occurs problem with SQL query
 * @throws ClassNotFoundException - Throws when occurs problem with
 * OrderDetailsDAO class
 */
@FXML
        private void searchOrderDetails() throws SQLException, ClassNotFoundException {
        try {
            //Get all Employees information
            ObservableList<OrderDetails> ordData = OrderDetailsDAO.searchOrderDetails(orderDetailId);
            //Populate Employees on TableView
            populateOrderDetails(ordData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting cargos information from DB.\n" + e);
            throw e;
        }
    }

    /**
     * Method to show data in table
     *
     * @param ordData - List of data to show in talbe
     */
    @FXML
        private void populateOrderDetails(ObservableList<OrderDetails> ordData) {
        //Set items to the employeeTable
        orderDetailsTable.setItems(ordData);
    }

    /**
     * Method to invoke method to confirm release of product in database
     *
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when occurs problem with using
     * OrderDetailsDAO
     */
    @FXML
        private void confirmRelease() throws SQLException, ClassNotFoundException {
        OrderDetailsDAO.confirmRelease(orderDetailId);
        Stage stage = (Stage) lblOrderId.getScene().getWindow();
        stage.close();
    }

    /**
     * Method to invoke method to report problem with order
     */
    @FXML
        private void reportProblem() {
        String comments = taComments.getText();
        OrderDetailsDAO.reportProblem(orderDetailId, comments);
        Stage stage = (Stage) lblOrderId.getScene().getWindow();
        stage.close();
    }

}
