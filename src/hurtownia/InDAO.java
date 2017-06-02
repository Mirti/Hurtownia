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
        String selectStmt = "SELECT pt.nazwa, pt.cena_jednostkowa, pt.data_waznosci, pt.ilosc, pt.polozenie, d.nazwa, pt.kraj_pochodzenia"
                + " FROM produkt_temp pt, dostawca_importer d"
                + " WHERE pt.dostawca_importer_id=d.dostawca_importer_id";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsIn = Connect.getData(selectStmt);
 
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
            in.setInName(rs.getString("pt.nazwa"));
            in.setInPrice(rs.getString("pt.cena_jednostkowa"));
            in.setInDate(rs.getDate("pt.data_waznosci"));
            in.setInQuantity(rs.getInt("pt.ilosc"));
            in.setInPosition(rs.getString("pt.polozenie"));
            in.setInProvider(rs.getString("d.nazwa"));
            in.setInOrigin(rs.getString("pt.kraj_pochodzenia"));
            //Add Cargo to the ObservableList
            inList.add(in);
        }
        //return crgList (ObservableList of Cargos)
        return inList;
    }     
}
