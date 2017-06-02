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
 * Class to provide connection between controller and database
 *
 * @author Jon
 */
public class ReportDAO {
    
    /**
     * Method to get records about reports objects from database
     * 
     * @return - List of record objects
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when occurs problem with using another class
     */
    public static ObservableList<Report> searchReport() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT r.raport_id,r.typ,r.data_wygenerowania,u.nazwisko,r.sciezka FROM raport r,uzytkownik u WHERE r.uzytkownik_id=u.uzytkownik_id";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRpt = Polaczenie.getData(selectStmt);

            //Send ResultSet to the getContractorList method and get Contractor object
            ObservableList<Report> RptList = getReportList(rsRpt);

            //Return Contractor object
            return RptList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    /**
     * Method to get list of objects from raw ResultSet
     * 
     * @param rs - ResultSet with data from database
     * @return - List of objects
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when occurs problem with another class
     */
    private static ObservableList<Report> getReportList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Contractor objects
        ObservableList<Report> rptList = FXCollections.observableArrayList();

        while (rs.next()) {
            Report rpt = new Report();
            rpt.setReportId(rs.getInt("raport_id"));
            rpt.setReportType(rs.getString("typ"));
            rpt.setReportDate(rs.getDate("data_wygenerowania"));
            rpt.setReportAuthor(rs.getString("nazwisko"));
            rpt.setReportPath(rs.getString("sciezka"));
            //Add Cargo to the ObservableList
            rptList.add(rpt);
        }
        //return crgList (ObservableList of Contractors)
        return rptList;
    }

    /**
     * Method to add record about new report to database
     * 
     * @param type - Type of report
     * @param date - Date of report
     * @param user_id - ID of report creator
     * @param path - Path to report file
     * @throws SQLException - Throws when occurs problem with SQL query
     */
    public static void addReportToDB(String type, String date, String user_id, String path) throws SQLException {
        String sqlStatement = "INSERT INTO raport (typ,data_wygenerowania,uzytkownik_id,sciezka)"
                + "VALUES ('" + type + "','" + date + "','" + user_id + "','" + path + "')";
        Polaczenie.update(sqlStatement);
    }

    /**
     * Method to get path to file with report
     * 
     * @param id - ID of report
     * @return - Path to report file
     * @throws SQLException - Throws when occurs problem with SQL query
     */
    public static String getReportPath(int id) throws SQLException {
        String sqlStatement = "SELECT sciezka FROM raport WHERE raport_id=" + id;
        System.out.print(sqlStatement);
        ResultSet rs = Polaczenie.getData(sqlStatement);
        rs.next();
        return (rs.getString("sciezka"));

    }

}
