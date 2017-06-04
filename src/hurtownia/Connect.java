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
public class Connect {

    private static Connection con;
    private static Statement st;
    private static ResultSet rs;
    private static String[] currentUser;
    private static String dbUser = "root";
    private static String dbPassword = "";
    private static String dbName = "hurtowniaspozywcza";
    private static String dbHost = "jdbc:mysql://localhost:3306/";
    private static String dbImportPath;



    /**
     * Constructor Creates connection with SQL Database when initialize
     *
     */
    public Connect() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException a) {
        }
        try {
            setDefaultCon();
            st = con.createStatement();

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
     *
     * @param dbName - DataBase Name
     * @throws SQLException
     */
    public static void createDB(String dbName) throws SQLException{
        try {         
            con = setConn(dbHost,"", dbUser, dbPassword);            
            Statement s = con.createStatement();
            String stmt = "CREATE DATABASE "+dbName;
            s.executeUpdate(stmt);
        }catch (Exception ex) {
            System.out.println("Błąd" + ex);
        }
    }
    
    /**
     *
     * @param dbName - DataBase Name
     * @throws SQLException
     */
    public static void dropDB(String dbName) throws SQLException{
        try {         
            con = setConn(dbHost,"", dbUser, dbPassword);            
            Statement s = con.createStatement();
            String stmt = "DROP DATABASE "+dbName;
            s.executeUpdate(stmt);
        }catch (Exception ex) {
            System.out.println("Błąd" + ex);
        }
    }    
    
    /**
     *
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void setDbImportPath() throws IOException, FileNotFoundException{
        try{
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            dbImportPath = fileChooser.showOpenDialog(stage).getAbsolutePath();
        } catch (Exception ex) {
            System.out.println("Błąd" + ex);
        } 
    }

    /**
     *
     * @throws SQLException
     */
    public static void setDefaultCon() throws SQLException{
        con = DriverManager.getConnection(dbHost+dbName, dbUser, dbPassword);
    }

    /**
     *
     * @param dbHost - DataBase host Name
     * @param dbName - DataBase Name
     * @param dbUser - DataBase User Name
     * @param dbPassword - DataBase User Password
     * @return - returning connection with provided data
     * @throws SQLException
     */
    public static Connection setConn(String dbHost, String dbName, String dbUser, String dbPassword) throws SQLException{
        Connection conn = DriverManager.getConnection(dbHost+dbName, dbUser, dbPassword);
        return conn;
       }

    /**
     * Runs MySQL script form file
     *
     * @param con - DB connection
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

    /**
     *
     * @throws IOException
     * @throws FileNotFoundException
     * @throws SQLException
     */
    public static void importDB() throws IOException, FileNotFoundException, SQLException {
        createDB(dbName);       
        setDbImportPath();  
        setDefaultCon();        
        Connect.runScript(dbImportPath, con);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Potwierdzenie utworzenia bazy");
        alert.setHeaderText(null);
        alert.setContentText("Baza utworzona pomyślnie");
        alert.showAndWait();
        System.out.println("Baza utworzona pomyślnie");
    }

    /**
     *
     * @param user- Current User Data
     */
    public static void setCurrentUser(String[] user) {
        currentUser = user;
    }

    /**
     *
     * @return - Current User Data
     */
    public static String[] getCurrentUser() {
        return currentUser;
    }

    /**
     *
     * @return
     */
    public static String getDbUser() {
        return dbUser;
    }

    /**
     *
     * @param dbUser
     */
    public static void setDbUser(String dbUser) {
        Connect.dbUser = dbUser;
    }

    /**
     *
     * @return
     */
    public static String getDbPassword() {
        return dbPassword;
    }

    /**
     *
     * @param dbPassword
     */
    public static void setDbPassword(String dbPassword) {
        Connect.dbPassword = dbPassword;
    }

    /**
     *
     * @return
     */
    public static String getDbName() {
        return dbName;
    }

    /**
     *
     * @param dbName
     */
    public static void setDbName(String dbName) {
        Connect.dbName = dbName;
    }

    /**
     *
     * @return
     */
    public static String getDbHost() {
        return dbHost;
    }

    /**
     *
     * @param dbHost
     */
    public static void setDbHost(String dbHost) {
        Connect.dbHost = dbHost;
    }

    /**
     *
     * @return
     */
    public static String getDbImportPath() {
        return dbImportPath;
    }

    /**
     *
     * @param dbImportPath
     */
    public static void setDbImportPath(String dbImportPath) {
        Connect.dbImportPath = dbImportPath;
    }
}
