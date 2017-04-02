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
public class OutDAO {
     
    //*******************************
    //SELECT Contractor
    //*******************************
    public static ObservableList<Out> searchOut () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT z.zamowienie_id,k.nazwa,u.nazwisko,z.uwagi from zamowienie z,klient k,uzytkownik u where z.uzytkownik_id=u.uzytkownik_id AND z.klient_id=k.klient_id";
 
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsOut = Polaczenie.getData(selectStmt);
 
            //Send ResultSet to the getContractorList method and get Contractor object
            ObservableList<Out> OutList = getOutList(rsOut);
 
            //Return Contractor object
            return OutList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
 
    //Select * from Contractor operation
    private static ObservableList<Out> getOutList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Contractor objects
        ObservableList<Out> ourList = FXCollections.observableArrayList();
 
        while (rs.next()) {
            Out out = new Out();
            out.setOutId(rs.getInt("zamowienie_id"));
            out.setOutCustomer(rs.getString("nazwa"));
            out.setOutUser(rs.getString("nazwisko"));
            out.setOutComment(rs.getString("uwagi"));
            //Add Cargo to the ObservableList
            ourList.add(out);
        }
        //return crgList (ObservableList of Contractors)
        return ourList;
    }            
}
