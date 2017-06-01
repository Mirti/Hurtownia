
package hurtownia;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author Kinga
 */
public class ContractorUpdateController implements Initializable {
    
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
    private Button Ok;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        String  contractorName;
        String  contractorType;
        String  contractorOwner;
        String  contractorAdress;
        String  contractorPhone;
        String  contractorEmail;
        
        int contractorID = ContractorController.getSelectedContractorId();
        try {
             ResultSet rs = ContractorDAO.getContractorData(contractorID);
             contractorName = rs.getString("nazwa");
             contractorType = rs.getString("typ");
             contractorOwner = rs.getString("wlasciciel");
             contractorAdress = rs.getString("adres");
             contractorPhone = rs.getString("telefon");
             contractorEmail = rs.getString("email");
             
            CName.setText(contractorName);
            CCargo.setText(contractorType);
            COwner.setText(contractorOwner);
            CAdress.setText(contractorAdress);
            CTel.setText(contractorPhone);
            CEmail.setText(contractorEmail);
        }  catch (SQLException ex) {
            System.out.print("na ten błąd nie patrzcie, on jest do dodawania do formualrza");
           
        }
        
    }
    
    @FXML    
    private void updateContractor(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            if (CName.equals("") || CCargo.equals("") || COwner.equals("") || CAdress.equals("") || CTel.equals("") || CEmail.equals("")) {
                txt.setText("Wypełnij wymagane pola!");
            } else {
                ContractorDAO.updateContractor(ContractorController.getSelectedContractorId(), CName.getText(), CCargo.getText(), COwner.getText(), CAdress.getText(), CTel.getText(), CEmail.getText());
                txt.setText("udało się");
            }
        } catch (SQLException e) {
            txt.setText("Problem occurred while updating: " + e);
        }
    } 
    
    
    
    
}
