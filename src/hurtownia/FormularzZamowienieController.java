/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author nykie_000
 */
public class FormularzZamowienieController implements Initializable {

    private String zapytanie = "insert into produkt_zamowienie(produkt_id,zamowienie_id,ilosc,cena_jednostkowa) VALUES";
    private int cenaZamowienia = 0;
    private ObservableList<ZamowienieDataModel> data;
    @FXML
    private TextField ilosc_towaru;
    @FXML
    private DatePicker max_data;
    @FXML
    private TextField cena;
    @FXML
    private TextArea uwagi;
    @FXML
    private ComboBox dostawcy;
    @FXML
    private ComboBox towar;
    @FXML
    private Button dodaj_towar;
    @FXML
    private Button utworz_zam;
    @FXML
    private CheckBox dost_blok;
    @FXML
    private CheckBox cena_blok;
    @FXML
    private TableView<ZamowienieDataModel> tabela;

    @FXML
    private TableColumn<ZamowienieDataModel, String> nazwa_col;
    @FXML
    private TableColumn<ZamowienieDataModel, String> ilosc_col;
    @FXML
    private TableColumn<ZamowienieDataModel, String> dostawca_col;
    @FXML
    private TableColumn<ZamowienieDataModel, String> data_col;

    public FormularzZamowienieController() {
        this.data = FXCollections.observableArrayList();
    }

    @FXML
    protected void zatwierdzanie(ActionEvent event) throws IOException, SQLException {
        String uwagi_zam = uwagi.getText();
        String nowe = "Nowe";
        String tworzZam = "insert into zamowienie(klient_id,uzytkownik_id,uwagi,wartosc,stan) VALUES(1,1,\"" + uwagi_zam + "\", \"" + cenaZamowienia + "\",\"" + nowe +"\")";
        Polaczenie con = new Polaczenie();
        con.update(tworzZam);
        //wykonanie dodawania do bazy
        zapytanie = zapytanie.substring(0, zapytanie.length() - 1);
        System.out.println(zapytanie);
        con.update(zapytanie);
        System.out.println(zapytanie);
        zapytanie = "insert into produkt_zamowienie(produkt_id,zamowienie_id,ilosc,cena_jednostkowa) VALUES";
    }

    @FXML
    protected void dodawanie(ActionEvent event) throws IOException, SQLException {
        String ilosc_tow = (String) ilosc_towaru.getText();
        LocalDate data_zam = max_data.getValue();
        String cena_tow = cena.getText();
        String dostawca = dostawcy.getSelectionModel().getSelectedItem().toString();
        String towar_zam = towar.getSelectionModel().getSelectedItem().toString();

        Polaczenie con = new Polaczenie();

        String dostawca_id = "SELECT dostawca_importer_id FROM dostawca_importer where nazwa = \"" + dostawca + "\" order by dostawca_importer_id DESC limit 1";
        int dost_id = 0;
        ResultSet rs = con.getData(dostawca_id);
        while (rs.next()) {
            dost_id = rs.getInt(1);
        }

        String produkt_id = "SELECT produkt_id FROM produkt where dostawca_importer_id = \"" + dost_id + "\" and nazwa = \"" + towar_zam + "\" and data_waznosci >! \"" + data_zam + "\" order by produkt_id DESC limit 1";
        int prod_id = 0;
        ResultSet rs2 = con.getData(produkt_id);
        while (rs2.next()) {
            prod_id = rs2.getInt(1);
        }

        String zamowienie_id = "SELECT zamowienie_id FROM zamowienie order by zamowienie_id DESC limit 1";
        int zam_id = 0;
        ResultSet rs3 = con.getData(zamowienie_id);
        while (rs3.next()) {
            zam_id = rs3.getInt(1);
        }
        if (cena_blok.isSelected()) {
            String queryOCene = "SELECT cena_jednostkowa FROM produkt where produkt_id = \"" + prod_id + "\" order by cena_jednostkowa DESC limit 1";
            int cena_pobierana = 0;
            ResultSet rs4 = con.getData(queryOCene);
            while (rs4.next()) {
                cena_pobierana = rs4.getInt(1);
                cenaZamowienia += cena_pobierana;
                String dodawanieElemZamow = "(\"" + prod_id + "\",\"" + (zam_id + 1) + "\",\"" + ilosc_tow + "\", \"" + cena_pobierana + "\"),";
                zapytanie += dodawanieElemZamow;
            }
        } else {
            int temp = Integer.parseInt(cena_tow);
            cenaZamowienia += temp * Integer.parseInt(ilosc_tow);
            String dodawanieElemZamow = "(\"" + prod_id + "\",\"" + (zam_id + 1) + "\",\"" + ilosc_tow + "\", \"" + temp + "\"),";
            zapytanie += dodawanieElemZamow;
        }
        
        //data =
        //    FXCollections.observableArrayList(
         //   new ZamowienieDataModel(towar_zam, ilosc_tow, dostawca, ""+data_zam));
        
            ZamowienieDataModel zdm = new ZamowienieDataModel(towar_zam, ilosc_tow, dostawca, ""+data_zam);
            data.add(zdm); 
            tabela.setItems(data);
    }

    public void dostBlockUnblock(ActionEvent e) {
        if (e.getSource() == dost_blok) {
            dostawcy.setDisable(dost_blok.isSelected());
        }
    }

    public void priceBlockUnblock(ActionEvent e) {
        if (e.getSource() == cena_blok) {
            cena.setDisable(cena_blok.isSelected());
        }
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
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
            dostawcy.getItems().setAll(pom);
        } catch (SQLException ex) {
            Logger.getLogger(FormularzZamowienieController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Polaczenie con = new Polaczenie();
            String query = "Select distinct nazwa from produkt";
            String query2 = "SELECT COUNT(distinct nazwa) FROM produkt";
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
            towar.getItems().setAll(pom);
        } catch (SQLException ex) {
            Logger.getLogger(FormularzZamowienieController.class.getName()).log(Level.SEVERE, null, ex);
        }

      
        nazwa_col.setCellValueFactory(new PropertyValueFactory<ZamowienieDataModel, String>("rName"));
        ilosc_col.setCellValueFactory(new PropertyValueFactory<ZamowienieDataModel, String>("rNum"));
        dostawca_col.setCellValueFactory(new PropertyValueFactory<ZamowienieDataModel, String>("rSupp"));
        data_col.setCellValueFactory(new PropertyValueFactory<ZamowienieDataModel, String>("rDate"));
    }
}

