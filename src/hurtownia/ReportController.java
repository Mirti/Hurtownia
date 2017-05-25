/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
    private TableColumn<Report, String> rptPathColumn;
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
        rptPathColumn.setCellValueFactory(cellData -> cellData.getValue().reportPathProperty());
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
        LocalDateTime date = LocalDateTime.now();
        String currentDate = formatter.format(date);
        String date1 = formatter.format(dateFrom.getValue());
        String date2 = formatter.format(dateTo.getValue());
        String[] dates = {date1, date2, currentDate};
        return dates;
    }

    @FXML
    private void createReport() throws SQLException, IOException {
        String[] dates = getDates();
        if (reportSelect.getValue().equals("Raport dat ważności")) {
            ExpDateCreate.create(dates[0], dates[1]);
            ReportDAO.addReportToDB("Daty waznosci", dates[2], Polaczenie.getCurrentUser()[0],
                    "reports/" + "Raport Waznosci " + dates[2] + ".pdf");

        }
        if (reportSelect.getValue().equals("Raport sprzedaży")) {
            SellCreate.create(dates[0], dates[1]);
        }
        if (reportSelect.getValue().equals("Raport przyjęcia")) {
            InCreate.create(dates[0], dates[1]);
        }
    }
    @FXML
    private void openReport(String path) throws IOException{
        String reportPath = System.getProperty("user.dir")+"/"+path;
        Runtime rt = Runtime.getRuntime();
        rt.exec(reportPath);
    }

}
