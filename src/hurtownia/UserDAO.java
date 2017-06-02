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
 * Method to provide connection between controller and database
 *
 * @author Jon
 */
public class UserDAO {

    /**
     * Method to get records about users from database
     *
     * @return - List with user objects
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when occurs problem with using
     * another class
     */
    public static ObservableList<User> searchUser() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM uzytkownik";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsUsr = Connect.getData(selectStmt);

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

    /**
     * Method to returning list of object from raw ResultSet
     *
     * @param rs - ResultSet from database
     * @return - List of user objects
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when occurs problem with using
     * another class
     */
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

    /**
     * Method to update user information in database
     *
     * @param userId - ID of user
     * @param userFirstName - First name of user
     * @param userLastName - Last name of user
     * @param userPermission - Permission of user
     * @param userLogin - Login of user
     * @param userPassword - Password of user
     * @param userComment - Comment to user
     * @throws SQLException - Throw when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when occurs problem with using
     * another class
     */
    public static void updateUser(int userId, String userFirstName, String userLastName, String userPermission, String userLogin, String userPassword, String userComment) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt
                = "   UPDATE uzytkownik\n"
                + "      SET imie = '" + userFirstName + "',nazwisko = '" + userLastName + "',uprawnienia = '" + userPermission + "',login = '" + userLogin + "',haslo = SHA('" + userPassword + "'),uwagi = '" + userComment + "'\n"
                + "    WHERE uzytkownik_ID = " + userId + ";\n";

        //Execute UPDATE operation
        try {
            Connect.update(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }

    /**
     * Method to delete user from database
     *
     * @param userId - ID of user to delete
     * @throws SQLException - Throws when occurs problem with SQL query
     */
    public static void deleteUserWithId(int userId) throws SQLException {
        //Declare a DELETE statement
        String updateStmt
                = "   DELETE FROM uzytkownik\n"
                + "         WHERE uzytkownik_id =" + userId + ";\n";

        //Execute UPDATE operation
        try {
            Connect.update(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }

    }

    /**
     * Method to getting data about concrete user
     * 
     * @param userID - ID of user to getting data
     * @return - ResultSet with information about user
     * @throws SQLException - Throws when occurs problem with SQL query
     */
    public static ResultSet getUserData(int userID) throws SQLException {
        String query = "SELECT * FROM uzytkownik WHERE uzytkownik_id=" + userID + "";
        ResultSet rs = Connect.getData(query);
        System.out.print(rs.next()); 
        return rs;
    }
}
