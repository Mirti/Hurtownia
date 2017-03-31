/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.sql.*;


public class Polaczenie {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public Polaczenie (){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hurtownia","root","");
            st  = con.createStatement();
            
            
            
        }catch(Exception ex){
            System.out.println("Błąd"+ ex);
        }
    }
    
    /*
        public void getData(){
          try{
              String query = "select * from produkt";
              rs = st.executeQuery(query);
              System.out.println("rekordy z bazy danych");
              while(rs.next()){
                  String nazwa = rs.getString("nazwa");
                  System.out.println("nazwa:"+nazwa);
                  
              }
              
          }catch(Exception ex){
              System.out.println(ex);
          } 
        }
     */   
    }
    


