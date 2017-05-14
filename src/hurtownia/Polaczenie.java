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

/**
 * Class to handling connection with SQL Database
 *
 * @author Kinga, Mirti
 * 
 */
public class Polaczenie {

    private static Connection con;
    private static Statement st;
    private static ResultSet rs;

    /**
     * Constructor Creates connection with SQL Database when initialize
     *
     */
    public Polaczenie() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException a) {
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hurtowniaspozywcza", "root", "");
            st = con.createStatement();
            System.out.print("ok");

        } catch (Exception ex) {
            System.out.println("Błąd" + ex);
        }
    }

    /**
     *
     * Executes query in database and returns result in ResultSet
     *
     * @param query - String with SQL query
     * @return ResultSet of the executed query
     * @throws SQLException - When statement is incorrect
     */
    public static ResultSet getData(String query) throws SQLException {
        rs = st.executeQuery(query);
        return rs;

    }

    /**
     * Executes update statement in datebase
     *
     * @param query - Query with update statement
     * @throws SQLException - When statement is incorrect
     */
    public static void update(String query) throws SQLException {
        st.executeUpdate(query);
    }

    /**
     * Runs MySQL script form file
     *
     * @param path - URL to script file
     * @throws FileNotFoundException - if file isn't exist
     * @throws IOException - When e.x file has invalid file format
     * @throws SQLException - When statement is incorrect
     */
    public static void runScript(String path) throws FileNotFoundException, IOException, SQLException {
        ScriptRunner runner = new ScriptRunner(con, false, false);
        String file = path;
        runner.runScript(new BufferedReader(new FileReader(file)));
    }
}
