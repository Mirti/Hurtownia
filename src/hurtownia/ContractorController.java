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
 * Class for handle methods in Contractor Tab
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
    
    /**
     * 
     * @return ID of Contractor ID selected in tab
     */
    public static int getSelectedContractorId(){
        return selectedContractorId;
    }
    
    /**
     * Set values in table headers
     * 
     * @param url - unused
     * @param rb - unused
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cntrNameColumn.setCellValueFactory(cellData -> cellData.getValue().ContractorNameProperty());
        cntrTypeColumn.setCellValueFactory(cellData -> cellData.getValue().ContractorTypeProperty());
        cntrOwnerColumn.setCellValueFactory(cellData -> cellData.getValue().ContractorOwnerProperty());
        cntrAdressColumn.setCellValueFactory(cellData -> cellData.getValue().ContractorAdressProperty());
        cntrPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().ContractorPhoneProperty().asObject());
        cntrEmailColumn.setCellValueFactory(cellData -> cellData.getValue().ContractorEmailProperty());
        
        ContractorTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Contractor>() {
            
            /**
             * Match ID for every row in the table
             * @param c - list of Contractors
             */
            public void onChanged(ListChangeListener.Change<? extends Contractor> c) {

                for (Contractor u : c.getList()) {
                    selectedContractorId = u.getContractorId();
                }

            }
        });
        
        
    } 
    
    /**
     * Show data from database in table
     * 
     * @throws SQLException - Throws when occur problem with SQL query
     * @throws ClassNotFoundException  - Throws when method can't find class ContractorDAO
     */
    @FXML
    private void searchContractor() throws SQLException, ClassNotFoundException {
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
        
    /**
     * Set items to the Contractor table
     * 
     * @param cntrData - List with Contractor objects
     * @throws ClassNotFoundException - Throws when can't find class ContractorDAO
     */
    @FXML
    private void populateContractor (ObservableList<Contractor> cntrData) throws ClassNotFoundException {
        ContractorTable.setItems(cntrData);
    }
    
    /**
     * Opens SupplierForm.xml in new window
     * 
     * @throws Exception - To print information about error
     */
    @FXML  
    public void openSupplierForm() throws Exception {               
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
    
    /**
     * Opens SupplierDeleteConfirm.fxml in new window
     * 
     * @throws Exception - To print information about error
     */
    public void openSupplierDeleteConfirm() throws Exception {               
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SupplierDeleteConfirm.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.showAndWait();
                
        } catch(Exception e) {
           e.printStackTrace();
          }
    }   
    
    /**
     * Opens SupplierForm2
     * 
     * @throws Exception - To print information about error
     */
    @FXML  
    public void openSupplierForm2() throws Exception {               
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
