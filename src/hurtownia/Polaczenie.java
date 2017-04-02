/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.sql.*;

public class Polaczenie {

    private static Connection con;
    private static Statement st;
    private static ResultSet rs;

    public Polaczenie() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException a) {
            System.out.print("zjebane");
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hurtowniaspozywcza", "root", "");
            st = con.createStatement();
            System.out.print("ok");

        } catch (Exception ex) {
            System.out.println("Błąd" + ex);
        }
    }

    public static ResultSet getData(String query) throws SQLException {
        rs = st.executeQuery(query);
        return rs;

    }
}
