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
 * @author Jon
 */
public class InController implements Initializable {

   @FXML
    private TableColumn<In, String>  inNameColumn;
    @FXML
    private TableColumn<In, String> inPriceColumn;
    @FXML
    private TableColumn<In, Date> inDateColumn;
    @FXML
    private TableColumn<In, Integer> inQuantityColumn;
    @FXML
    private TableColumn<In, String> inPositionColumn;    
    @FXML
    private TableColumn<In, String> inProviderColumn;
    @FXML
    private TableColumn<In, String> inOriginColumn;   
    @FXML
    private TableView inTable;   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inNameColumn.setCellValueFactory(cellData -> cellData.getValue().inNameProperty());
        inPriceColumn.setCellValueFactory(cellData -> cellData.getValue().inPriceProperty());
        inDateColumn.setCellValueFactory(cellData -> cellData.getValue().inDateProperty());
        inQuantityColumn.setCellValueFactory(cellData -> cellData.getValue().inQuantityProperty().asObject());
        inPositionColumn.setCellValueFactory(cellData -> cellData.getValue().inPositionProperty());
        inProviderColumn.setCellValueFactory(cellData -> cellData.getValue().inProviderProperty());
        inOriginColumn.setCellValueFactory(cellData -> cellData.getValue().inOriginProperty());
    } 
    
    @FXML
    private void searchIn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Employees information
            ObservableList<In> inData = InDAO.searchIn();
            //Populate Employees on TableView
            populateIn(inData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting cargos information from DB.\n" + e);
            throw e;
        }
    }
        
    @FXML
    private void populateIn (ObservableList<In> inData) throws ClassNotFoundException {
        //Set items to the employeeTable
        inTable.setItems(inData);
    }  
    
}
