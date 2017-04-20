/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Mirti
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    public void createDB() throws MalformedURLException, IOException {
        String url = "http://localhost/DBInstaller/install.php";
        try {
            URL myURL = new URL(url);
            URLConnection myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            BufferedInputStream in = new BufferedInputStream(myURLConnection.getInputStream());
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Tworzenie bazy danych");
            alert.setHeaderText(null);
            alert.setContentText("Baza danych utworzona pomyślnie \n Zrestartuj program");

            alert.showAndWait();

        } catch (MalformedURLException e) {
            // new URL() failed
            e.printStackTrace();
        } catch (IOException e1) {
            // openConnection() failed
            e1.printStackTrace();
        }

    }

    @FXML
    public void importDB() throws IOException, FileNotFoundException, SQLException {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        String path = fileChooser.showOpenDialog(stage).getAbsolutePath();
        System.out.print(path);
        Polaczenie.runScript(path);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
