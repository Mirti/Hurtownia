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
 * Class for provide connection between controller and database
 *
 * @author Jon
 */
public class InDAO {
    
    /**
     * Method for returning list of In objects from database
     * 
     * @return List with In object from database
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when occurs problems with other class
     */
    public static ObservableList<In> searchIn () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT nazwa, cena_jednostkowa, data_waznosci, ilosc, polozenie, dostawca, kraj_pochodzenia FROM `produkt_temp`";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsIn = Polaczenie.getData(selectStmt);
 
            //Send ResultSet to the getCargoList method and get Cargo object
            ObservableList<In> inList = getInList(rsIn);
 
            //Return Cargo object
            return inList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
 
    /**
     * Method for returning list of In object from raw ResultSet
     * 
     * @param rs - ResultSet from database
     * @return - List of In objects
     * @throws SQLException - When occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when occurs problem with using another class
     */
    private static ObservableList<In> getInList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Cargo objects
        ObservableList<In> inList = FXCollections.observableArrayList();
 
        while (rs.next()) {
            In in = new In();
            in.setInName(rs.getString("nazwa"));
            in.setInPrice(rs.getString("cena_jednostkowa"));
            in.setInDate(rs.getDate("data_waznosci"));
            in.setInQuantity(rs.getInt("ilosc"));
            in.setInPosition(rs.getString("polozenie"));
            in.setInProvider(rs.getString("dostawca"));
            in.setInOrigin(rs.getString("kraj_pochodzenia"));
            //Add Cargo to the ObservableList
            inList.add(in);
        }
        //return crgList (ObservableList of Cargos)
        return inList;
    }     
}
