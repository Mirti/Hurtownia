/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.stage.FileChooser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jon
 */
public class ConnectIT {
    
    public ConnectIT() {
    }
    
    @BeforeClass
    public static void setUpClass(){

    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException{
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() throws SQLException {
    }
    
    /**
     * Test of getData method, of class Connect.
     */
    @Test
    public void testGetData() throws Exception {
        Connect conn = new Connect();
        String query = "SELECT imie from uzytkownik where uzytkownik_id=99";
        String expResult = "admin";
        String result = "";
        ResultSet rs = conn.getData(query);
                while (rs.next()) {
                result = rs.getString(1);}
        assertEquals(expResult, result);
    }
    
    /**
     * Test of createDb method, of class Connect.
     */
    @Test
    public void testCreateDb() throws Exception {
        Connect conn = new Connect();
        String dbName = "testdb";
        String result="";
        String expResult = "testdb";
        Connect.createDB(dbName);
        Connection con = DriverManager.getConnection(Connect.getDbHost(), Connect.getDbUser(), Connect.getDbPassword());            
        Statement s = con.createStatement();
        String stmt = "SHOW DATABASES LIKE '"+dbName+"';";
        ResultSet rs = s.executeQuery(stmt);
             while (rs.next()) {
                 result = rs.getString(1);}

        assertEquals(expResult, result);
    } 
    
    /**
     * Test of RunScript method, of class Connect.
     */
    @Test
    public void testRunScript() throws Exception {
        Connect conn = new Connect();
        String dbName = "testdb";
        String dbTestPath = System.getProperty("user.dir")+"/test/test.sql";
        String result="";
        String expResult = "czwarty";
        Connect.createDB(dbName);
        Connection con = DriverManager.getConnection(Connect.getDbHost()+dbName, Connect.getDbUser(), Connect.getDbPassword());
        Connect.runScript(dbTestPath, con);
        Statement s = con.createStatement();
        String stmt = "SELECT * from test";
        ResultSet rs = s.executeQuery(stmt);
             while (rs.next()) {
                 result = rs.getString(1);}
             System.out.println(result);

        assertEquals(expResult, result);
    } 
    /**
     * Test of setConn method, of class Connect.
     */
    @Test
    public void testSetConn() throws Exception {
        Connection con = Connect.setConn(Connect.getDbHost(),Connect.getDbName(), Connect.getDbUser(), Connect.getDbPassword());
        assertNotNull(con);
        
    }     

}
