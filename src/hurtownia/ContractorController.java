package hurtownia;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jon, Kinga
 */

public class ContractorController implements Initializable {
    
    @FXML
    private TableColumn<Contractor, String> cntrNameColumn;
    @FXML
    private TableColumn<Contractor, String> cntrTypeColumn;
    @FXML
    private TableColumn<Contractor, String> cntrOwnerColumn;
    @FXML
    private TableColumn<Contractor, String> cntrAdressColumn;    
    @FXML
    private TableColumn<Contractor, Integer> cntrPhoneColumn;
    @FXML
    private TableColumn<Contractor, String> cntrEmailColumn;   
    
    @FXML
    private TableView ContractorTable;   
    
    @FXML
    private static int selectedContractorId;
    
    public static int getSelectedContractorId(){
        return selectedContractorId;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cntrNameColumn.setCellValueFactory(cellData -> cellData.getValue().ContractorNameProperty());
        cntrTypeColumn.setCellValueFactory(cellData -> cellData.getValue().ContractorTypeProperty());
        cntrOwnerColumn.setCellValueFactory(cellData -> cellData.getValue().ContractorOwnerProperty());
        cntrAdressColumn.setCellValueFactory(cellData -> cellData.getValue().ContractorAdressProperty());
        cntrPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().ContractorPhoneProperty().asObject());
        cntrEmailColumn.setCellValueFactory(cellData -> cellData.getValue().ContractorEmailProperty());
        
        ContractorTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Contractor>() {
            public void onChanged(ListChangeListener.Change<? extends Contractor> c) {

                for (Contractor u : c.getList()) {
                    selectedContractorId = u.getContractorId();
                }

            }
        });
        
        
    } 
    
    @FXML
    private void searchContractor(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Contractorsinformation
            ObservableList<Contractor> cntrData = ContractorDAO.searchContractor();
            //Populate Contractors on TableView
            populateContractor(cntrData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting Contractors information from DB.\n" + e);
            throw e;
        }
    }
        
    @FXML
    private void populateContractor (ObservableList<Contractor> cntrData) throws ClassNotFoundException {
        //Set items to the ContractorTable
        ContractorTable.setItems(cntrData);
    }
    @FXML  
    public void openSupplierForm(ActionEvent event) throws Exception {               
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SupplierForm.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.showAndWait();
                
        } catch(Exception e) {
           e.printStackTrace();
          }
    }   
    
    
    public void openPotwierdzenieUsuwaniaKontrahenta(ActionEvent event) throws Exception {               
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PotwierdzenieUsuwaniaKontrahenta.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.showAndWait();
                
        } catch(Exception e) {
           e.printStackTrace();
          }
    }   
    
    
    @FXML  
    public void openSupplierForm2(ActionEvent event) throws Exception {               
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SupplierForm2.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.showAndWait();
                
        } catch(Exception e) {
           e.printStackTrace();
          }
    }   
    
}
