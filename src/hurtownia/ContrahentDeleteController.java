/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cmptr
 */
public class ContrahentDeleteController implements Initializable {

   @FXML
   private Button closeButton;
   @FXML
   private Button updateButton;
   
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void deleteUser (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            ContractorDAO.deleteContractorWithId(ContractorController.getSelectedContractorId());
            Stage stage = (Stage) updateButton.getScene().getWindow();
            stage.close();           
            //resultArea.setText("");
        } catch (SQLException e) {
            //resultArea.setText(" " + e);
            throw e;
        }
    }
    
    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
