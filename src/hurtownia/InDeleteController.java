
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
 
 * @author Kinga
 */
public class InDeleteController implements Initializable{
    
   @FXML
   private Button closeButton;
   @FXML
   private Button updateButton;
   
    @FXML
    private void deleteIn (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            InDAO.deleteInWithId(InController.getSelectedInId());
            Stage stage = (Stage) updateButton.getScene().getWindow();
            stage.close();           
    }
    
    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    
}
