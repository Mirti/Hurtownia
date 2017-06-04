/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.sql.Date;
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
        String selectStmt = "SELECT pt.produkt_temp_id, pt.nazwa, pt.cena_jednostkowa, pt.data_waznosci, pt.ilosc, pt.polozenie, d.nazwa, pt.kraj_pochodzenia"
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
            in.setInId(rs.getInt("pt.produkt_temp_id"));
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
    
    /**
     * Method to change product from table product_temp to product
     * 
     * @param id - ID of product to accept in database
     * @throws SQLException - Throws when occurs problem with SQL query
     */
    public static void acceptProduct(int id) throws SQLException{
        String sqlQuery = "SELECT * FROM produkt_temp WHERE produkt_temp_id="+id;
        ResultSet rs = Connect.getData(sqlQuery);
        
        rs.next();
        String nazwa = rs.getString("nazwa");
        int cena_jednostkowa = rs.getInt("cena_jednostkowa");
        Date data_waznosci = rs.getDate("data_waznosci");
        int ilosc = rs.getInt("ilosc");
        String polozenie = rs.getString("polozenie");
        String kraj_pochodzenia = rs.getString("kraj_pochodzenia"); 
        int dostawca_importer_id = rs.getInt("dostawca_importer_id");
        Date data_przyjecia = rs.getDate("data_przyjecia");
        
        String insertQuery = "INSERT INTO produkt(nazwa,cena_jednostkowa,data_waznosci,ilosc,polozenie,"
                + "kraj_pochodzenia,dostawca_importer_id,data_przyjecia) "
                + "VALUES('"+nazwa+"',"+cena_jednostkowa+",'"+data_waznosci+"',"+ilosc+",'"+polozenie+"',"
                + "'"+kraj_pochodzenia+"',"+dostawca_importer_id+",'"+data_przyjecia+"')";
        System.out.print(insertQuery);
        Connect.update(insertQuery);
        
        deleteProduct(id);
    }
    
    /**
     * Method to delete product form product_temp talbe
     * 
     * @param id - ID of product to delete
     * @throws SQLException - When occurs problem with SQL query
     */
    public static void deleteProduct(int id)throws SQLException{
        String query = "DELETE FROM produkt_temp WHERE produkt_temp_id="+id;
        Connect.update(query);
    }
    
}
