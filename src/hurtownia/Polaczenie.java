/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Polaczenie {

    private static Connection con;
    private static Statement st;
    private static ResultSet rs;

    public Polaczenie() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException a) {
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/hurtowniaspozywcza", "root", "");
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
     public static void update(String query) throws SQLException {
        st.executeUpdate(query);
    }
    

    public static void runScript(String path) throws FileNotFoundException, IOException, SQLException {
        ScriptRunner runner = new ScriptRunner(con, false, false);
        String file = path;
        runner.runScript(new BufferedReader(new FileReader(file)));
    }
}
