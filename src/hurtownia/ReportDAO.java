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
public class ReportDAO {

    //*******************************
    //SELECT Contractor
    //*******************************
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

    //Select * from Contractor operation
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

    public static void addReportToDB(String type, String date, String user_id, String path) throws SQLException {
        String sqlStatement = "INSERT INTO raport (typ,data_wygenerowania,uzytkownik_id,sciezka)"
                + "VALUES ('" + type + "','" + date + "','" + user_id + "','" + path +"')";
        Polaczenie.update(sqlStatement);
    }

    public static String getReportPath(int id) throws SQLException {
        String sqlStatement = "SELECT sciezka FROM raport WHERE raport_id=" + id;
        System.out.print(sqlStatement);
        ResultSet rs = Polaczenie.getData(sqlStatement);
        rs.next();
        return (rs.getString("sciezka"));

    }

}
