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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author nykie_000
 */
public class UserAddController {

    @FXML
    private TextField UName;
    @FXML
    private TextField USName;
    @FXML
    private TextField UPass;
    @FXML
    private TextField ULog;
    @FXML
    private ComboBox UPos;
    @FXML
    private TextArea UNote;
    @FXML
    private Button UConf;
    @FXML
    private Text txt;

    @FXML
    protected void dodawanie(ActionEvent event) throws IOException, SQLException {

                String login = ULog.getText();
                String pass = UPass.getText();
                String name = UName.getText();
                String sname = USName.getText();
                String position = UPos.getSelectionModel().getSelectedItem().toString();
                String note = UNote.getText();
                if(login.equals("") || pass.equals("") || name.equals("") || sname.equals(""))
                {txt.setText("Wypełnij wymagane pola!");}
                else{
                String test = "SELECT * FROM UZYTKOWNIK WHERE login = \"" + login + "\"";
                String query = "insert into uzytkownik(imie,nazwisko,login,haslo,uprawnienia,uwagi) VALUES(\"" + name + "\", \"" + sname + "\",\"" + login + "\",\"" + pass + "\",\"" + position + "\",\"" + note + "\")";
                Polaczenie con = new Polaczenie();
                try {
                    //Sprawdzam czy user istnieje/nie powtarza sie login
                    if (con.getData(test).next()) {
                        txt.setText("Login zajęty!");
                        System.out.print("1");
                    } //Funkcja dodawania Usera
                    else {
                        txt.setText(" ");
                        System.out.print("2");
                        try {
                            con.update(query);
                            txt.setText("Użytkownik dodany!");
                        } catch (SQLException ex) {
                            Logger.getLogger(UserAddController.class.getName()).log(Level.SEVERE, null, ex);
                            txt.setText("Nie powiodło się!");
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UserAddController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    
}
