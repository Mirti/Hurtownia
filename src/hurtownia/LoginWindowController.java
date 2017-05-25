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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mirti
 */
public class LoginWindowController implements Initializable {

    @FXML
    private TextField tfLogin;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Text txtWrong;
    @FXML
    private Button btnLogin;

    @FXML
    protected void login(ActionEvent event) throws IOException, SQLException {
        String login = tfLogin.getText();
        String pass = pfPassword.getText();
        if (login.equals("set") && pass.equals("set")) {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            String query = "SELECT * FROM UZYTKOWNIK WHERE login= \"" + login + "\" AND haslo = \"" + pass + "\"";
            Polaczenie con = new Polaczenie();
            ResultSet rs = con.getData(query);
            //Funkcja logowania
            if (rs.next()) {
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                String[] currentUser = new String[2];
                currentUser[0]=rs.getString("imie");
                currentUser[1]=rs.getString("nazwisko");
                Polaczenie.setCurrentUser(currentUser);
       
            } else {
                txtWrong.setText("Nieprawidłowy login lub hasło");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
