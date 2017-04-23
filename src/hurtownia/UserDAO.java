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
            usr.setUserPassword(rs.getString("haslo"));
            usr.setUserComment(rs.getString("uwagi"));            
            //Add Cargo to the ObservableList
            usrList.add(usr);
        }
        //return crgList (ObservableList of Contractors)
        return usrList;
    }
    
        public static void updateUser (int userId, String userFirstName, String userLastName, String userPermission, String userLogin, String userPassword, String userComment) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt =
                        "   UPDATE uzytkownik\n" +
                        "      SET imie = '" + userFirstName + "',nazwisko = '" + userLastName + "',uprawnienia = '" + userPermission + "',login = '" + userLogin + "',haslo = '" + userPassword +"',uwagi = '" + userComment +    "'\n" +
                        "    WHERE uzytkownik_ID = " + userId + ";\n";
 
        //Execute UPDATE operation
        try {
            Polaczenie.update(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }
 
    //*************************************
    //DELETE an user
    //*************************************
    public static void deleteUserWithId (int userId) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                        "   DELETE FROM uzytkownik\n" +
                        "         WHERE uzytkownik_id ="+ userId +";\n";
 
        //Execute UPDATE operation
        try {
            Polaczenie.update(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}
