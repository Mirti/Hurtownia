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
public class OrderDetailsDAO {
   //*******************************
    //SELECT Cargos
    //*******************************
    public static ObservableList<OrderDetails> searchOrderDetails (int id) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT pz.produkt_zamowienie_id,p.nazwa,pz.ilosc,p.data_waznosci,z.uwagi from produkt p,produkt_zamowienie pz,zamowienie z where pz.produkt_id = p.produkt_id and pz.zamowienie_id = z.zamowienie_id and z.zamowienie_id ="+id;
 
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
 
    //Select * from Cargos operation
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
}
