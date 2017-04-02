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
 *
 * @author Jon
 */
public class ContractorDAO {
    
    //*******************************
    //SELECT Contractor
    //*******************************
    public static ObservableList<Contractor> searchContractor () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM dostawca_importer,klient";
 
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
 
    //Select * from Contractor operation
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
}
