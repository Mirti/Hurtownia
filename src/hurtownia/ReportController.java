/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import raports.ExpDateCreate;
import raports.InCreate;
import raports.SellCreate;

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
    @FXML
    private DatePicker dateFrom, dateTo;
    @FXML
    private ComboBox reportSelect;

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
        } catch (SQLException e) {
            System.out.println("Error occurred while getting Contractors information from DB.\n" + e);
            throw e;
        }
    }

    @FXML
    private void populateReport(ObservableList<Report> rptData) throws ClassNotFoundException {
        //Set items to the ContractorTable
        reportTable.setItems(rptData);
    }

    @FXML
    private String[] getDates() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date1 = formatter.format(dateFrom.getValue());
        String date2 = formatter.format(dateTo.getValue());
        String[] dates = {date1, date2};
        return dates;
    }

    @FXML
    private void createReport() {
        String[] dates = getDates();
        if(reportSelect.getValue().equals("Raport dat ważności")){
            ExpDateCreate.create(dates[0], dates[1]);          
        }
        if(reportSelect.getValue().equals("Raport sprzedaży")){
            SellCreate.create(dates[0],dates[1]);
        }
        if(reportSelect.getValue().equals("Raport przyjęcia")){
            InCreate.create(dates[0],dates[1]);
        }
        
    }

}
