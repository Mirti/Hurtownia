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
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Class to provide method to handle mehtods in DateAlert.fxml
 */
public class DateAlert implements Initializable {
    public DateAlert() {
        this.data = FXCollections.observableArrayList();
    }
    @FXML
    private ObservableList<DateAlertDataModel> data;
    @FXML
    private TableView<DateAlertDataModel> tabela;
    @FXML
    private TableColumn<DateAlertDataModel, String> name;
    @FXML
    private TableColumn<DateAlertDataModel, String> date;
    @FXML
    private TableColumn<DateAlertDataModel, String> number;
    @FXML
    private TableColumn<DateAlertDataModel, String> position;
    
    
    /**
     * Method to show products with short expiration date in table at the start of the program
     * 
     * @throws SQLException - Throws when occurs problem with SQL query
     */
    @FXML
    public void wyswietl ()throws SQLException 
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        Polaczenie con= new Polaczenie();
        localDate = localDate.plusMonths(1);
        System.out.println(localDate);
        String query1 = "select nazwa, ilosc, data_waznosci, polozenie from produkt where data_waznosci < \""+localDate+"\"";
        
        ResultSet dane = null;
        try {
            dane = con.getData(query1);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String nazwa = "";
        String ilosc = "";
        Date data_waznosci;
        String polozenie = "";
        
        while (dane.next()) {
            nazwa = dane.getString(1);
            ilosc = dane.getString(2);
            data_waznosci = dane.getDate(3);
            polozenie = dane.getString(4);
            System.out.println(" "+nazwa+" "+ ilosc+" "+ data_waznosci+ " "+polozenie+" ");
            data.add(new DateAlertDataModel(nazwa,ilosc,polozenie,""+data_waznosci));
            tabela.setItems(data);  
        }
                    
        
    }
    
    /**
     * Method to set values to table headers
     * 
     * @param fxmlFileLocation - unused
     * @param resources - unused
     */
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        name.setCellValueFactory(new PropertyValueFactory<DateAlertDataModel, String>("rName"));
        number.setCellValueFactory(new PropertyValueFactory<DateAlertDataModel, String>("rNum"));
        position.setCellValueFactory(new PropertyValueFactory<DateAlertDataModel, String>("rPos"));
        date.setCellValueFactory(new PropertyValueFactory<DateAlertDataModel, String>("rDate"));
    }
    
}
