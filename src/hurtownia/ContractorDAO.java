/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class for connect Contractor controller with database
 * 
 */
public class ContractorDAO {
    
    /**
     * Method for taking data from database
     * 
     * @return List with Contractor objects
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException Throws when method can't use other class
     */
    public static ObservableList<Contractor> searchContractor () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM dostawca_importer";
 
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsCntr = Polaczenie.getData(selectStmt);
 
            //Send ResultSet to the getContractorList method and get Contractor object
            ObservableList<Contractor> CntrList = getContractorList(rsCntr);
 
            //Return Contractor object
            return CntrList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
 
     /**
     * Return list of Contractor objects from the raw ResultSet
     * 
     * @param rs ResultSet with data from database
     * @return list of Contractor objects
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when method can't use other class
     */
    private static ObservableList<Contractor> getContractorList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Contractor objects
        ObservableList<Contractor> cntrList = FXCollections.observableArrayList();
 
        while (rs.next()) {
            Contractor cntr = new Contractor();
            cntr.setContractorId(rs.getInt("dostawca_importer_id"));
            cntr.setContractorName(rs.getString("nazwa"));
            cntr.setContractorType(rs.getString("typ"));
            cntr.setContractorOwner(rs.getString("wlasciciel"));
            cntr.setContractorAdress(rs.getString("adres"));
            cntr.setContractorPhone(rs.getInt("telefon"));
            cntr.setContractorEmail(rs.getString("email"));
            //Add Cargo to the ObservableList
            cntrList.add(cntr);
        }
        //return crgList (ObservableList of Contractors)
        return cntrList;
    }  
    
    /**
     * Method for delete user with given ID from database
     * 
     * @param userId - ID of user to delete
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when occurs problem with other class
     */
    public static void deleteContractorWithId(int userId) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt
                = "   DELETE FROM dostawca_importer\n"
                + "         WHERE dostawca_importer_id =" + userId + ";\n";

        //Execute UPDATE operation
        try {
            Polaczenie.update(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }

    }
    
    /**
     * Method for getting information about contractor with ID given as parameter
     * 
     * @param contractorID - ID of contractor in database
     * @return ResultSet with data about contractor with selected ID
     * @throws SQLException - Throws when occurs problem with SQL query
     */
    public static ResultSet getContractorData(int contractorID) throws SQLException{
              String query = "SELECT * FROM dostawca_importer WHERE dostawca_importer_id=" + contractorID+"" ;
              ResultSet rs = Polaczenie.getData(query);
              System.out.print(rs.next()); //do sprawdzenia
              return rs;
          }
    
    /**
     * Update row with ID given as parameter with data given as other parametres
     * 
     * @param contractorId
     * @param contractorName
     * @param contractorType
     * @param contractorOwner
     * @param contractorAdress
     * @param contractorPhone
     * @param contractorEmail
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException  - Throws when can't 
     */
    public static void updateContractor(int contractorId, String contractorName, String contractorType, String contractorOwner, String contractorAdress, String contractorPhone, String contractorEmail) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt
                = "   UPDATE dostawca_importer\n"
                + "      SET nazwa = '" + contractorName + "',typ = '" + contractorType + "',wlasciciel = '" + contractorOwner + "',adres = '" + contractorAdress + "',telefon= '" + contractorPhone + "' ,email = '" + contractorEmail + "'\n"
                + "    WHERE dostawca_importer_ID = " + contractorId + ";\n";

        //Execute UPDATE operation
        try {
            Polaczenie.update(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }
    
}
