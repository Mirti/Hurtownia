/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class to provide method from InTab.fxml
 */
public class InController implements Initializable {

    @FXML
    private TableColumn<In, String> inNameColumn;
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
    @FXML
    private static int selectedProductID;
    @FXML
    private Button BtnAccept, BtnDelete;

    /**
     * Method to set table headers and fill table with data and disable buttons
     *
     * @param fxmlFileLocation - unused
     * @param resources - unused
     */
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        
        if(Connect.getCurrentUser()[3].equals("Kierownik")){
            BtnAccept.disableProperty().setValue(Boolean.FALSE);
            BtnDelete.disableProperty().setValue(Boolean.FALSE);
        }

        inNameColumn.setCellValueFactory(cellData -> cellData.getValue().inNameProperty());
        inPriceColumn.setCellValueFactory(cellData -> cellData.getValue().inPriceProperty());
        inDateColumn.setCellValueFactory(cellData -> cellData.getValue().inDateProperty());
        inQuantityColumn.setCellValueFactory(cellData -> cellData.getValue().inQuantityProperty().asObject());
        inPositionColumn.setCellValueFactory(cellData -> cellData.getValue().inPositionProperty());
        inProviderColumn.setCellValueFactory(cellData -> cellData.getValue().inProviderProperty());
        inOriginColumn.setCellValueFactory(cellData -> cellData.getValue().inOriginProperty());

        inTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<In>() {
            public void onChanged(ListChangeListener.Change<? extends In> c) {

                for (In i : c.getList()) {
                    selectedProductID = i.getInId();
                }

            }
        });

        try {
            Connect con = new Connect();
            String query = "Select distinct nazwa from dostawca_importer";
            String query2 = "SELECT COUNT(distinct nazwa) FROM dostawca_importer";
            ResultSet pomrs = con.getData(query2);
            int rozm = 0;
            while (pomrs.next()) {
                rozm = pomrs.getInt(1);
            }
            String[] pom = new String[rozm];
            int temp = 0;
            ResultSet rsa = con.getData(query);
            while (rsa.next()) {
                pom[temp] = rsa.getString(1);
                temp++;
            }
            Provider.getItems().setAll(pom);
        } catch (SQLException ex) {
            Logger.getLogger(InController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Method to take data about delivered products from database
     *
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when can't find method InDAO
     */
    @FXML
    private void searchIn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Employees information
            ObservableList<In> inData = InDAO.searchIn();
            //Populate Employees on TableView
            populateIn(inData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting cargos information from DB.\n" + e);
            throw e;
        }
    }

    /**
     * Method to fill table with data
     *
     * @param inData - List with In type objects
     */
    @FXML
    private void populateIn(ObservableList<In> inData) {
        //Set items to the employeeTable
        inTable.setItems(inData);
    }

    @FXML
    private TextField TName;
    @FXML
    private TextField Number;
    @FXML
    private ComboBox Provider;
    @FXML
    private TextField Price;
    @FXML
    private TextField Position;
    @FXML
    private TextField Country;
    @FXML
    private Button Add;
    @FXML
    private DatePicker Data;
    @FXML
    private Text txt;

    /**
     * Returns ID of selected product in table
     *
     * @return ID of selected product
     */
    public static int getSelectedProductId() {
        return selectedProductID;
    }

    /**
     * Method to add product to database
     *
     * @throws SQLException - throws when occurs problem with SQL query
     */
    @FXML
    protected void dodawanie() throws SQLException {

        String nazwaTowaru = TName.getText();
        String dostawca = Provider.getSelectionModel().getSelectedItem().toString();
        String ilosc = Number.getText();
        LocalDate data = Data.getValue();
        String cena = Price.getText();
        String pozycja = Position.getText();
        String kraj = Country.getText();
        String dodaj = Add.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime date = LocalDateTime.now();
        String currentDate = formatter.format(date);

        if (nazwaTowaru.equals("") || (dostawca.equals("")) || (ilosc.equals("")) || (cena.equals("")) || (pozycja.equals("")) || (kraj.equals("")) || (data.equals(""))) {
            txt.setText("Wypełnij wymagane pola!");
        } else {
            String supplierIdQuery = "SELECT dostawca_importer_id FROM dostawca_importer "
                    + "WHERE NAZWA = '" + dostawca + "'";
            ResultSet rs = Connect.getData(supplierIdQuery);
            rs.next();
            String supplierID = rs.getString("dostawca_importer_id");
            String query = "insert into produkt_temp(nazwa,cena_jednostkowa,data_waznosci,ilosc,polozenie,dostawca_importer_id,kraj_pochodzenia,data_przyjecia) "
                    + "VALUES(\"" + nazwaTowaru + "\", \"" + cena + "\",\"" + data + "\",\"" + ilosc + "\",\"" + pozycja + "\",\"" + supplierID + "\",\"" + kraj + "\",'"+
                    currentDate+"')";
            Connect.update(query);

        }
    }

    /**
     * Method to accept product from delivery to main products
     * 
     * @throws SQLException - When occurs problem with SQL query
     */
    @FXML
    protected void acceptProduct() throws SQLException {
        InDAO.acceptProduct(selectedProductID);
    }
    
    /**
     * Method to delete product from database
     * 
     * @throws SQLException - When occurs problem with SQL query
     */
    @FXML
    protected void deleteProduct() throws SQLException{
        InDAO.deleteProduct(selectedProductID);
    }

}
