package hurtownia;

import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

/**
 * Class for handle methods in Cargo tab
 */
public class CargoController implements Initializable {

    @FXML
    private TableColumn<Cargo, String> crgNameColumn;
    @FXML
    private TableColumn<Cargo, String> crgPriceColumn;
    @FXML
    private TableColumn<Cargo, Date> crgExpirationDateColumn;
    @FXML
    private TableColumn<Cargo, Integer> crgQuantityColumn;
    @FXML
    private TableColumn<Cargo, String> crgPositionColumn;
    @FXML
    private TableColumn<Cargo, String> crgProviderColumn;
    @FXML
    private TableColumn<Cargo, String> crgOriginColumn;
    @FXML
    private TableView cargoTable;
    @FXML
    private TextField tfSearch;
    @FXML
    private ComboBox cbLimit;

    /**
     * Set values in table header
     * 
     * @param url - unused
     * @param rb - unused
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crgNameColumn.setCellValueFactory(cellData -> cellData.getValue().cargoNameProperty());
        crgPriceColumn.setCellValueFactory(cellData -> cellData.getValue().cargoPriceProperty());
        crgExpirationDateColumn.setCellValueFactory(cellData -> cellData.getValue().cargoExpirationDateProperty());
        crgQuantityColumn.setCellValueFactory(cellData -> cellData.getValue().cargoQuantityProperty().asObject());
        crgPositionColumn.setCellValueFactory(cellData -> cellData.getValue().cargoPositionProperty());
        crgProviderColumn.setCellValueFactory(cellData -> cellData.getValue().cargoProviderProperty());
        crgOriginColumn.setCellValueFactory(cellData -> cellData.getValue().cargoOriginProperty());
        //Limit default first value
        cbLimit.getSelectionModel().selectFirst();
        cbLimit.valueProperty().addListener(new ChangeListener<String>() {
            
            /**
             * Method to show alert when user change all values
             * @param ov - changed field
             * @param t - previous value on combo box
             * @param t1 - value on combo box
             */
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (t1.equals("Wszystko")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ostrzeżenie");
                    alert.setHeaderText(null);
                    alert.setContentText("Wyszukanie wszystkich pozycji \n może spowodować długie wyszukiwanie");
                    alert.showAndWait();
                }

            }

        });
    }

    /**
     * Method to show date from database in table
     * 
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when application can't find class CargoDAO
     */
    @FXML
    private void showCargo() throws SQLException, ClassNotFoundException {
        try {
            String limit = cbLimit.getValue().toString();
            if (limit.equals("Wszystko")) limit = "9999";
            //Get all Employees information
            ObservableList<Cargo> crgData = CargoDAO.showCargo(limit);
            //Populate Employees on TableView
            populateCargo(crgData);
            
            
        } catch (SQLException e) {
            System.out.println("Error occurred while getting cargos information from DB.\n" + e);
            throw e;
        }
    }

    /**
     * Method to show data in table after search
     * 
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when can't find class CargoDAO
     */
    @FXML
    private void searchCargo() throws SQLException, ClassNotFoundException {
        try {
            String pharse = tfSearch.getText();
            String limit = cbLimit.getValue().toString();
            if (limit.equals("Wszystko")) limit = "9999";
            ObservableList<Cargo> crgData = CargoDAO.searchCargo(pharse, limit);
            //Populate Employees on TableView
            populateCargo(crgData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting cargos information from DB.\n" + e);
            throw e;
        }
    }

    /**
     * Set items to cargoTable table
     * 
     * @param crgData - List with Cargo objects
     * @throws ClassNotFoundException - Throws when can't use other class
     */
    @FXML
    private void populateCargo(ObservableList<Cargo> crgData) throws ClassNotFoundException {
        cargoTable.setItems(crgData);
    }

}
