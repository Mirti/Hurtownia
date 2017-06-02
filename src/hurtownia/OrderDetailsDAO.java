/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class for provide connection between controller and database
 *
 * @author Jon
 */
public class OrderDetailsDAO {
    
    /**
     * Method for select data about Order Details from database
     * 
     * @param id - ID of Order Details to select data
     * @return - List of products from select statement
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when occurs problem with using other class
     */
    public static ObservableList<OrderDetails> searchOrderDetails(int id) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT pz.produkt_zamowienie_id,p.nazwa,pz.ilosc,p.data_waznosci,z.uwagi from produkt p,produkt_zamowienie pz,zamowienie z where pz.produkt_id = p.produkt_id and pz.zamowienie_id = z.zamowienie_id and z.zamowienie_id =" + id;

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsOrd = Polaczenie.getData(selectStmt);

            //Send ResultSet to the getCargoList method and get Cargo object
            ObservableList<OrderDetails> ordList = getOrderDetailsList(rsOrd);

            //Return Cargo object
            return ordList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    /**
     * Method to return List of objects from row ResultSet
     * 
     * @param rs - ResultSet to get data from
     * @return - List of Order objects
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when occurs problem with using another class
     */
    private static ObservableList<OrderDetails> getOrderDetailsList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Cargo objects
        ObservableList<OrderDetails> ordList = FXCollections.observableArrayList();

        while (rs.next()) {
            OrderDetails ord = new OrderDetails();
            ord.setOrderDetailsId(rs.getInt("produkt_zamowienie_id"));
            ord.setOrderDetailsName(rs.getString("nazwa"));
            ord.setOrderDetailsQuantity(rs.getString("ilosc"));
            ord.setOrderDetailsDate(rs.getDate("data_waznosci"));
            ord.setOrderDetailsComment(rs.getString("uwagi"));
            //Add Cargo to the ObservableList
            ordList.add(ord);
        }
        //return crgList (ObservableList of Cargos)
        return ordList;
    }

    /**
     * Method to confirm release orders
     * 
     * @param orderId - ID of order to release
     */
    public static void confirmRelease(int orderId) {
        String statement = "UPDATE zamowienie SET stan = 'Wykonane' WHERE zamowienie_id=" + orderId;
        try {
            Polaczenie.update(statement);
        } catch (SQLException ex) {
            System.out.print("SQL Update operation has been failed" + ex);
        }
    }

    /**
     * Method to report problem with order
     * 
     * @param orderId - ID of order to report
     * @param comments - comments to order
     */
    public static void reportProblem(int orderId, String comments) {
        String statement = "UPDATE zamowienie SET stan = 'Uwagi', uwagi='" + comments + "' "
                + "WHERE zamowienie_id=" + orderId;
        try {
            Polaczenie.update(statement);
        } catch (SQLException ex) {
            System.out.print("SQL Update operation has been failed" + ex);
        }
    }
}
