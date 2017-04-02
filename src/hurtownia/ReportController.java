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
public class ReportController implements Initializable {

   
    @FXML
    private TableColumn<Report, String> rptTypeColumn;
    @FXML
    private TableColumn<Report, Date> rptDateColumn;
    @FXML
    private TableColumn<Report, String> rptAuthorColumn;
    @FXML
    private TableColumn<Report, String> rptCommentColumn;      
    @FXML
    private TableView reportTable;   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rptTypeColumn.setCellValueFactory(cellData -> cellData.getValue().reportTypeProperty());
        rptDateColumn.setCellValueFactory(cellData -> cellData.getValue().reportDateProperty());
        rptAuthorColumn.setCellValueFactory(cellData -> cellData.getValue().reportAuthorProperty());
        rptCommentColumn.setCellValueFactory(cellData -> cellData.getValue().reportCommentProperty());
    } 
    
    @FXML
    private void searchReport(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Contractorsinformation
            ObservableList<Report> ReportData = ReportDAO.searchReport();
            //Populate Contractors on TableView
            populateReport(ReportData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting Contractors information from DB.\n" + e);
            throw e;
        }
    }
        
    @FXML
    private void populateReport (ObservableList<Report> rptData) throws ClassNotFoundException {
        //Set items to the ContractorTable
        reportTable.setItems(rptData);
    }  
    
}
