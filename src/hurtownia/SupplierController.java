/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class to provide method to SupplierForm
 *
 * @author Jon
 */
public class SupplierController implements Initializable {

    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML 
    private TextField CName;
    @FXML
    private TextField CAdress;
    @FXML
    private TextField COwner;
    @FXML
    private TextField CTel;
    @FXML
    private TextField CEmail;
    @FXML
    private TextField CCargo;
    @FXML
    private Text txt;
    @FXML
    private Label CPromptCargo;
    @FXML
    private Label CPromptDisc;
    @FXML
    private CheckBox CClient;
    @FXML
    private TextField CDisc;
    @FXML
    private Button Ok;
    
    /**
     * Method to add supplier to database
     * 
     * @throws IOException
     * @throws SQLException 
     */
    @FXML private void dodawanie() throws IOException, SQLException {
        
        String nazwa = CName.getText();
        String adres = CAdress.getText();
        String wlasciciel = COwner.getText();
        String tel = CTel.getText();
        String eMail = CEmail.getText();
        String towar = CCargo.getText();
        String znizka = CDisc.getText();
        Connect con = new Connect();
        
        if(CClient.isSelected()){
            if(nazwa.equals("")||(adres.equals(""))||(wlasciciel.equals(""))||(tel.equals(""))||(eMail.equals(""))||(znizka.equals("")))
        {txt.setText("Wypełnij wymagane pola!");}
        else
        {
            String query = "insert into klient(nazwa,wlasciciel,adres,telefon,email,rabat) "
                    + "VALUES(\"" + nazwa + "\",\"" + wlasciciel + "\",\"" + adres + "\",\"" + tel + "\",\"" + eMail + "\", \"" + znizka + "\")";
            con.update(query);
            
        }
        }   else{
        if(nazwa.equals("")||(adres.equals(""))||(wlasciciel.equals(""))||(tel.equals(""))||(eMail.equals(""))||(towar.equals("")))
        {txt.setText("Wypełnij wymagane pola!");}
        else
        {
            String query = "insert into dostawca_importer(nazwa,typ,wlasciciel,adres,telefon,email) "
                    + "VALUES(\"" + nazwa + "\", \"" + towar + "\",\"" + wlasciciel + "\",\"" + adres + "\",\"" + tel + "\",\"" + eMail + "\")";
            con.update(query);
            
        }
 
}
    }
 public void block(ActionEvent e) {
        if (e.getSource() == CClient) {
            CDisc.setVisible(CClient.isSelected());
            CPromptDisc.setVisible(CClient.isSelected());
            CCargo.setVisible(!CClient.isSelected());
            CPromptCargo.setVisible(!CClient.isSelected());
            
        }
    }
    
}
