/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/*
 * Class to provide mehtod to add user
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

    /**
     * Method to add new user to database
     * 
     * @throws SQLException - Throws when occurs problem with SQL query
     */
    @FXML
    protected void dodawanie() throws SQLException {

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
                String query = "insert into uzytkownik(imie,nazwisko,login,haslo,uprawnienia,uwagi) VALUES(\"" + name + "\", \"" + sname + "\",\"" + login + "\",SHA(\"" + pass + "\"),\"" + position + "\",\"" + note + "\")";
                Connect con = new Connect();
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
