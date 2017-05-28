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
import java.io.Reader;
import java.sql.*;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    private static String[] currentUser;

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

    public static void importDB() throws IOException, FileNotFoundException, SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=");
        Statement s = con.createStatement();
        s.executeUpdate("CREATE DATABASE hurtowniaspozywcza");
        s.close();

        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        String path = fileChooser.showOpenDialog(stage).getAbsolutePath();
        System.out.print(path);
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hurtowniaspozywcza", "root", "");
        Polaczenie.runScript(path, con);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Potwierdzenie utworzenia bazy");
        alert.setHeaderText(null);
        alert.setContentText("Baza utworzona pomyślnie");
        alert.showAndWait();
    }

    /**
     * Runs MySQL script form file
     *
     * @param path - URL to script file
     * @throws FileNotFoundException - if file isn't exist
     * @throws IOException - When e.x file has invalid file format
     * @throws SQLException - When statement is incorrect
     *
     */
    public static void runScript(String path, Connection con) throws FileNotFoundException, IOException, SQLException {
        try {
            // Initialize object for ScripRunner
            ScriptRunner sr = new ScriptRunner(con);

            // Give the input file to Reader
            Reader reader = new BufferedReader(
                    new FileReader(path));

            // Exctute script
            sr.runScript(reader);

        } catch (Exception e) {
            System.err.println("Failed to Execute" + path
                    + " The error is " + e.getMessage());
        }
    }

    public static void setCurrentUser(String[] user) {
        currentUser = user;
    }

    public static String[] getCurrentUser() {
        return currentUser;
    }
}
