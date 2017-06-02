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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

/**
 * Class used to create order. It checks if there is enough suppies in the
 * storage, if not class wont create order.
 *
 * @author nykie_000
 */
public class FormularzZamowienieController implements Initializable {

    private String zapytanie = "insert into produkt_zamowienie(produkt_id,zamowienie_id,ilosc,cena_jednostkowa) VALUES";
    private int cenaZamowienia = 0;
    private ObservableList<ZamowienieDataModel> data;
    private int klientid;
    private boolean allowed;
    Map<String, Integer> Towary = new HashMap<String, Integer>();
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
    private ComboBox klient;
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

    /**
     *
     */
    public FormularzZamowienieController() {
        this.data = FXCollections.observableArrayList();
    }

    @FXML
    /**
     * Method checks if there is enough items in storage to create an order. If
     * yes it will call method zatwierdzanie
     *
     */
    protected void sprawdzanieRezerwacja(ActionEvent event) throws IOException, SQLException {

        List<String> listaKluczy = new ArrayList<String>(Towary.keySet());
        int rozm = listaKluczy.size();
        int[] takNie = new int[rozm];
        for (int i = 0; i < rozm; i++) {
            int ilosc_tow = Towary.get(listaKluczy.get(i));
            String ile = "select ilosc from produkt where nazwa = \"" + listaKluczy.get(i) + "\"";

            ResultSet ileNaStanie = Polaczenie.getData(ile);
            int stan = 0;
            while (ileNaStanie.next()) {
                stan = ileNaStanie.getInt(1);
            }
            if(zapytanie.length()<90)
            {
               try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NieWybranoTowarow.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.showAndWait();

                } catch (Exception e) {
                    e.printStackTrace();
                }  
            }
            else
            {
            if (stan < ilosc_tow) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BrakTowaru.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.showAndWait();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                takNie[i] = 1;
                
                zapytanie = "insert into produkt_zamowienie(produkt_id,zamowienie_id,ilosc,cena_jednostkowa) VALUES";
                data.clear();
                tabela.setItems(data);
            
            } else {
                allowed = true;
                takNie[i] = 0;
            }
            }
        }
        int test = 0;
        for (int i = 0; i < rozm; i++) {
            test = test + takNie[i];
        }
        if (test == 0) {
            zatwierdzanie();
        }
    }

    @FXML
    /**
     * Method puts created order into database and substracts number of items in
     * order from number of items stored.
     *
     */
    protected void zatwierdzanie() throws IOException, SQLException {
        if (allowed) {
// rezerwowanie towaru
            List<String> listaKluczy = new ArrayList<String>(Towary.keySet());
            int rozm = listaKluczy.size();
            for (int i = 0; i < rozm; i++) {
                int ilosc_tow = Towary.get(listaKluczy.get(i));
                String ile = "select ilosc from produkt where nazwa = \"" + listaKluczy.get(i) + "\"";

                ResultSet ileNaStanie = Polaczenie.getData(ile);
                int stan = 0;
                while (ileNaStanie.next()) {
                    stan = ileNaStanie.getInt(1);
                }
                int poRezerwacji = stan - ilosc_tow;
                String rezerwacjaTowaru1 = "UPDATE produkt SET ilosc = \"" + poRezerwacji + "\" WHERE nazwa = \"" + listaKluczy.get(i) + "\"";
                Polaczenie.update(rezerwacjaTowaru1);
            }

            String uwagi_zam = uwagi.getText();
            String nowe = "Nowe";
            System.out.print(klientid);
            String tworzZam = "insert into zamowienie(klient_id,uzytkownik_id,uwagi,wartosc,stan) VALUES(\"" + klientid + "\","+Polaczenie.getCurrentUser()[0]+",\"" + uwagi_zam + "\", \"" + cenaZamowienia + "\",\"" + nowe + "\")";
            Polaczenie con = new Polaczenie();
            con.update(tworzZam);
//wykonanie dodawania do bazy
            zapytanie = zapytanie.substring(0, zapytanie.length() - 1);
            con.update(zapytanie);
            zapytanie = "insert into produkt_zamowienie(produkt_id,zamowienie_id,ilosc,cena_jednostkowa) VALUES";
            data.clear();
            tabela.setItems(data);
        } else {
            zapytanie = "insert into produkt_zamowienie(produkt_id,zamowienie_id,ilosc,cena_jednostkowa) VALUES";
            data.clear();
            tabela.setItems(data);
        }
    }

    @FXML
    /**
     * Method used to fill TableView and used to create order via form
     * FormularzZamowienie.fxml
     *
     */
    protected void dodawanie(ActionEvent event) throws IOException, SQLException {
// odczyt danych z formularza

        if((String) ilosc_towaru.getText() == null || max_data.getValue() == null || dostawcy.getSelectionModel().getSelectedItem().toString() == null || towar.getSelectionModel().getSelectedItem().toString() == null || klient.getSelectionModel().getSelectedItem().toString() == null || cena.getText() == null)
        {
           try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NieWybranoTowarow.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.showAndWait();

                } catch (Exception e) {
                    e.printStackTrace();
                }    
        }else{
    
        String ilosc_tow = (String) ilosc_towaru.getText();
        LocalDate data_zam = max_data.getValue();
        String cena_tow = cena.getText();
        String dostawca = dostawcy.getSelectionModel().getSelectedItem().toString();
        String towar_zam = towar.getSelectionModel().getSelectedItem().toString();
        String klientcb = klient.getSelectionModel().getSelectedItem().toString();

        
        Polaczenie con = new Polaczenie();

//ustalanie id klienta
        String querynaid = "SELECT klient_id FROM klient where nazwa = \"" + klientcb + "\" order by klient_id DESC limit 1";
        int klie_id = 0;
        ResultSet rs0 = con.getData(querynaid);
        while (rs0.next()) {
            klie_id = rs0.getInt(1);
        }
        klientid = klie_id;
        //ustalanie id dostawcy
        String dostawca_id = "SELECT dostawca_importer_id FROM dostawca_importer where nazwa = \"" + dostawca + "\" order by dostawca_importer_id DESC limit 1";
        int dost_id = 0;
        ResultSet rs = con.getData(dostawca_id);
        while (rs.next()) {
            dost_id = rs.getInt(1);
        }
//ustalanie id produktu
        String produkt_id = "SELECT produkt_id FROM produkt where dostawca_importer_id = \"" + dost_id + "\" and nazwa = \"" + towar_zam + "\" and data_waznosci >! \"" + data_zam + "\" order by produkt_id DESC limit 1";
        int prod_id = 0;
        ResultSet rs2 = con.getData(produkt_id);
        while (rs2.next()) {
            prod_id = rs2.getInt(1);
        }
//ustalanie id zamowienie
        String zamowienie_id = "SELECT zamowienie_id FROM zamowienie order by zamowienie_id DESC limit 1";
        int zam_id = 0;
        ResultSet rs3 = con.getData(zamowienie_id);
        while (rs3.next()) {
            zam_id = rs3.getInt(1);
        }
//dodawanie towarw i ich ilości do mapy

        Towary.put(towar_zam, Integer.parseInt(ilosc_tow));

        // Tworzenie zapytania
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

        // wypełnianie tabeli
        ZamowienieDataModel zdm = new ZamowienieDataModel(towar_zam, ilosc_tow, dostawca, "" + data_zam);
        data.add(zdm);
        tabela.setItems(data);

        System.out.println(zapytanie);
        }
    }
//wyszarzanie Comboboxa Dostawcy

    public void dostBlockUnblock(ActionEvent e) {
        if (e.getSource() == dost_blok) {
            dostawcy.setDisable(dost_blok.isSelected());
        }
    }
//wyszarzanie Comboboxa cena

    public void priceBlockUnblock(ActionEvent e) {
        if (e.getSource() == cena_blok) {
            cena.setDisable(cena_blok.isSelected());
        }
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        //wypełnianie Comboboxów
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
            String query = "Select distinct nazwa from klient";
            String query2 = "SELECT COUNT(distinct nazwa) FROM klient";
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
            klient.getItems().setAll(pom);
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

        // wypełnianie TableView
        nazwa_col.setCellValueFactory(new PropertyValueFactory<ZamowienieDataModel, String>("rName"));
        ilosc_col.setCellValueFactory(new PropertyValueFactory<ZamowienieDataModel, String>("rNum"));
        dostawca_col.setCellValueFactory(new PropertyValueFactory<ZamowienieDataModel, String>("rSupp"));
        data_col.setCellValueFactory(new PropertyValueFactory<ZamowienieDataModel, String>("rDate"));
    }
}
