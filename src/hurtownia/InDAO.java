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
public class InDAO {
   //*******************************
    //SELECT Cargos
    //*******************************
    public static ObservableList<In> searchIn () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT p.nazwa, p.cena_jednostkowa, p.data_waznosci, p.ilosc, p.polozenie, d.nazwa as dostawca, p.kraj_pochodzenia FROM produkt_temp p,dostawca_importer d";
 
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
 
    //Select * from Cargos operation
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
    
    public static void deleteInWithId(int inId) throws SQLException, ClassNotFoundException {
        
        String updateStmt
                = "DELETE FROM produkt_temp"
                + " WHERE produkt_temp_id =" + inId;

        
        try {
            Polaczenie.update(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }

    }
    
    public static ResultSet getInData(int inID) throws SQLException{
              String query = "SELECT * FROM produkt_temp WHERE produkt_temp_id=" + inID+"" ;
              ResultSet rs = Polaczenie.getData(query);
              System.out.print(rs.next()); 
              return rs;
          }
}
