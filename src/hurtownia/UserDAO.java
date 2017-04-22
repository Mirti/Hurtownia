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
public class UserDAO {
     
    //*******************************
    //SELECT Contractor
    //*******************************
    public static ObservableList<User> searchUser () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM uzytkownik";
 
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsUsr = Polaczenie.getData(selectStmt);
 
            //Send ResultSet to the getContractorList method and get Contractor object
            ObservableList<User> UsrList = getUserList(rsUsr);
 
            //Return Contractor object
            return UsrList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
 
    //Select * from Contractor operation
    private static ObservableList<User> getUserList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Contractor objects
        ObservableList<User> usrList = FXCollections.observableArrayList();
 
        while (rs.next()) {
            User usr = new User();
            usr.setUserId(rs.getInt("uzytkownik_id"));
            usr.setUserFirstName(rs.getString("imie"));
            usr.setUserLastName(rs.getString("nazwisko"));
            usr.setUserPermissions(rs.getString("uprawnienia"));
            usr.setUserLogin(rs.getString("login"));
            //Add Cargo to the ObservableList
            usrList.add(usr);
        }
        //return crgList (ObservableList of Contractors)
        return usrList;
    }     
}
