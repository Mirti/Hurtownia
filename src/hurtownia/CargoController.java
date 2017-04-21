
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
import javafx.fxml.Initializable;

public class CargoController implements Initializable {
    
    @FXML
    private TableColumn<Cargo, String>  crgNameColumn;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crgNameColumn.setCellValueFactory(cellData -> cellData.getValue().cargoNameProperty());
        crgPriceColumn.setCellValueFactory(cellData -> cellData.getValue().cargoPriceProperty());
        crgExpirationDateColumn.setCellValueFactory(cellData -> cellData.getValue().cargoExpirationDateProperty());
        crgQuantityColumn.setCellValueFactory(cellData -> cellData.getValue().cargoQuantityProperty().asObject());
        crgPositionColumn.setCellValueFactory(cellData -> cellData.getValue().cargoPositionProperty());
        crgProviderColumn.setCellValueFactory(cellData -> cellData.getValue().cargoProviderProperty());
        crgOriginColumn.setCellValueFactory(cellData -> cellData.getValue().cargoOriginProperty());
    } 
    
    @FXML
    private void showCargo(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Employees information
            ObservableList<Cargo> crgData = CargoDAO.showCargo();
            //Populate Employees on TableView
            populateCargo(crgData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting cargos information from DB.\n" + e);
            throw e;
        }
    }
     @FXML
    private void searchCargo(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            String pharse = tfSearch.getText();
            ObservableList<Cargo> crgData = CargoDAO.searchCargo(pharse);
            //Populate Employees on TableView
            populateCargo(crgData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting cargos information from DB.\n" + e);
            throw e;
        }
    }
        
    @FXML
    private void populateCargo (ObservableList<Cargo> crgData) throws ClassNotFoundException {
        //Set items to the employeeTable
        cargoTable.setItems(crgData);
    }
   
    
}
