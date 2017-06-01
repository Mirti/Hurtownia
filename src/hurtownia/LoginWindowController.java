
package hurtownia;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class, response for login by users.
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
    

    /**
     * Method response for user login. When user write login:CreateDB and password:CreateDB
     * he has possibility to create new database from file. 
     * When user give correct login and password he will be diverted to main window of the program
     * 
     * @throws IOException - Throws when can't find file with new database
     * @throws SQLException - Throws when occurs problem with SQL query
     */
    @FXML
    protected void login() throws IOException, SQLException {
        
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
         
        
        
        Polaczenie con= new Polaczenie();
        String query1 = "select nazwa, ilosc, data_waznosci, polozenie from produkt where data_waznosci < \""+localDate+"\"";
        
        ResultSet rsa = null;
        try {
            rsa = con.getData(query1);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if(rsa.next())
            {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DateAlert.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                    
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        String login = tfLogin.getText();
        String pass = pfPassword.getText();
        if (login.equals("createDB") && pass.equals("createDB")) {
            Polaczenie.importDB();
            tfLogin.clear();
            pfPassword.clear();
        } else {
            String query = "SELECT * FROM UZYTKOWNIK WHERE login= \"" + login + "\" AND haslo =SHA(\"" + pass + "\")";
            System.out.print(query);
            ResultSet rs = con.getData(query);
            //Funkcja logowania
            if (rs.next()) {
                
                 String[] currentUser = new String[4];
                currentUser[0]=rs.getString("uzytkownik_id");
                currentUser[1]=rs.getString("imie");
                currentUser[2]=rs.getString("nazwisko");
                currentUser[3]=rs.getString("uprawnienia");
                Polaczenie.setCurrentUser(currentUser); 
                
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
       
            } else {
                txtWrong.setText("Nieprawidłowy login lub hasło");
            }
        }
    }

    /**
     * Method which starts automatically with this class. At current moment it unused
     * 
     * @param location - Unused
     * @param resources - Unused
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
