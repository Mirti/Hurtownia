/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import com.wlasnyjar.reports.*;

/**
 * FXML Controller class. It controls method for Reports Tab
 *
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
    @FXML
    private static int selectedReportId;

    /**
     * Returns report id for double click
     *
     * @return report id
     */
    public static int getSelectedReportId() {
        return selectedReportId;
    }

    /**
     * Assigns names to table header and handle double click on row by assign ID
     * for every row.
     *
     * @param url - Unused
     * @param rb - Unused
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rptTypeColumn.setCellValueFactory(cellData -> cellData.getValue().reportTypeProperty());
        rptDateColumn.setCellValueFactory(cellData -> cellData.getValue().reportDateProperty());
        rptAuthorColumn.setCellValueFactory(cellData -> cellData.getValue().reportAuthorProperty());
        rptPathColumn.setCellValueFactory(cellData -> cellData.getValue().reportPathProperty());

        reportTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Report>() {
            public void onChanged(ListChangeListener.Change<? extends Report> c) {

                for (Report r : c.getList()) {
                    selectedReportId = r.getReportId();
                }

            }
        });
        reportTable.setOnMouseClicked(new EventHandler<MouseEvent>() {

            /**
             * Handle what program should do after double click
             *
             * @param click - click of the mouse
             */
            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    try {
                        openReport(ReportDAO.getReportPath(selectedReportId));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * Show report from database in table using ReportDAO class.
     *
     * @see ReportDAO
     * @param actionEvent
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws where program can't find
     * RaportDAO class
     */
    @FXML
    private void searchReport() throws SQLException, ClassNotFoundException {
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

    /**
     * Adds rows to table
     *
     * @param rptData - List of reports object
     */
    @FXML
    private void populateReport(ObservableList<Report> rptData) {
        reportTable.setItems(rptData);
    }

    /**
     * Returns String array with current date on index 0, start date on index 1
     * and finish date on index 2
     *
     * @return Array with dates
     */
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

    /**
     * Method to create reports according to value on comboBox
     *
     * @see ExpDateCreate
     * @see InCreate
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws IOException - Throws when can't create new file with report
     */
    @FXML
    private void createReport() throws SQLException, IOException {
        String[] dates = getDates();
        if (reportSelect.getValue().equals("Raport dat ważności")) {
            ResultSet rs = Polaczenie.getData("SELECT p.nazwa,p.data_waznosci,p.ilosc,di.nazwa FROM"
                    + " produkt p, dostawca_importer di WHERE "
                    + "p.dostawca_importer_id = di.dostawca_importer_id AND "
                    + "data_waznosci BETWEEN '" + dates[0] + "' AND '" + dates[1] + "' ORDER BY data_waznosci ASC");

            ExpDateCreate edc = new ExpDateCreate(Polaczenie.getCurrentUser(),rs);
            edc.create();      
            ReportDAO.addReportToDB("Daty waznosci", dates[2], Polaczenie.getCurrentUser()[0],
                    "reports\\\\" + "RaportWaznosci" + dates[2] + ".pdf");

        }
        if (reportSelect.getValue().equals("Raport przyjęć")) {
            ResultSet rs =Polaczenie.getData("SELECT * FROM produkt_temp pt, przyjecie p, dostawca_importer di WHERE  "
                    + "pt.data_waznosci BETWEEN '" + dates[0] + "' AND '" + dates[1] + "'"
                    + "AND di.dostawca_importer_id = pt.dostawca_importer_id "
                    + "AND pt.przyjecie_id=p.przyjecie_id"
                    + " ORDER BY p.data_przyjecia ASC");
            InCreate ic = new InCreate(dates,Polaczenie.getCurrentUser(),rs);
            ic.create();
            ReportDAO.addReportToDB("Raport Przyjecia", dates[2], Polaczenie.getCurrentUser()[0],
                    "reports\\\\" + "RaportPrzyjecia" + dates[2] + ".pdf");
        }
    }

    /**
     * Open file with report in PDF
     *
     * @param path - Path to file with report
     * @throws IOException - Throws when it is problem with file
     */
    @FXML
    private void openReport(String path) throws IOException {
        String reportPath = System.getProperty("user.dir") + "\\" + path;
        System.out.print(reportPath);
        File reportFile = new File(path);
        Desktop.getDesktop().open(reportFile);
    }

}
