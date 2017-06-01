

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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jon, Kinga
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
    
    @FXML
    private static int selectedInId;
    
    public static int getSelectedInId(){
        return selectedInId;
    }
    
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        
        inNameColumn.setCellValueFactory(cellData -> cellData.getValue().inNameProperty());
        inPriceColumn.setCellValueFactory(cellData -> cellData.getValue().inPriceProperty());
        inDateColumn.setCellValueFactory(cellData -> cellData.getValue().inDateProperty());
        inQuantityColumn.setCellValueFactory(cellData -> cellData.getValue().inQuantityProperty().asObject());
        inPositionColumn.setCellValueFactory(cellData -> cellData.getValue().inPositionProperty());
        inProviderColumn.setCellValueFactory(cellData -> cellData.getValue().inProviderProperty());
        inOriginColumn.setCellValueFactory(cellData -> cellData.getValue().inOriginProperty());
        
        inTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<In>() {
            public void onChanged(ListChangeListener.Change<? extends In> c) {

                for (In u : c.getList()) {
                    selectedInId = u.getInId();
                }

            }
        });
        
        try {
            Polaczenie con = new Polaczenie();
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
        }}
    
    
    
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
    
    
    @FXML
    protected void dodawanie(ActionEvent event) throws IOException, SQLException {
        
        String nazwaTowaru = TName.getText();
        String dostawca = Provider.getSelectionModel().getSelectedItem().toString();
        String ilosc = Number.getText();
        LocalDate data = Data.getValue();
        String cena = Price.getText();
        String pozycja = Position.getText();
        String kraj = Country.getText();
        String dodaj = Add.getText();
        Polaczenie con = new Polaczenie();
                
        if(nazwaTowaru.equals("")||(dostawca.equals(""))||(ilosc.equals(""))||(cena.equals(""))||(pozycja.equals(""))||(kraj.equals(""))||(data.equals("")))
        {txt.setText("Wypełnij wymagane pola!");}
        else
        {
            String query = "insert into produkt_temp() "
                    + "VALUES(\"" + nazwaTowaru + "\", \"" + cena + "\",\"" + data + "\",\"" + ilosc + "\",\"" + pozycja + "\",\"" + dostawca + "\",\"" + kraj + "\")";
            con.update(query);
            
        }
    }    
    
    
    public void openInDelete(ActionEvent event) throws Exception {               
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InDelete.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.showAndWait();
                
        } catch(Exception e) {
           e.printStackTrace();
          }
    }   
    
    
}
