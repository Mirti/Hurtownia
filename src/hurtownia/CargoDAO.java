package hurtownia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList; 
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class CargoDAO {

    //*******************************
    //SELECT Cargos
    //*******************************
    public static ObservableList<Cargo> showCargo (String limit) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM produkt LIMIT " +limit;
 
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsCrgs = Polaczenie.getData(selectStmt);
 
            //Send ResultSet to the getCargoList method and get Cargo object
            ObservableList<Cargo> crgList = getCargoList(rsCrgs);
 
            //Return Cargo object
            return crgList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    
        public static ObservableList<Cargo> searchCargo (String pharse, String limit) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT DISTINCT * FROM produkt p, dostawca_importer d WHERE p.dostawca_importer_id = d.dostawca_importer_id AND "
                + "p.nazwa LIKE \"%" + pharse + "%\" OR "
                + "p.cena_jednostkowa =\""+pharse + "\" OR "
                + "p.ilosc =\""+ pharse+ "\" OR "
                + "p.polozenie LIKE \"%" + pharse +"%\" OR "
                + "p.kraj_pochodzenia LIKE \"%"+ pharse +"%\" OR "
                + "d.nazwa LIKE \"%"+ pharse + "%\" "
                + "GROUP BY p.nazwa "
                + "LIMIT " + limit
                ;
                
  
 
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsCrgs = Polaczenie.getData(selectStmt);
 
            //Send ResultSet to the getCargoList method and get Cargo object
            ObservableList<Cargo> crgList = getCargoList(rsCrgs);
 
            //Return Cargo object
            return crgList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
 
    //Select * from Cargos operation
    private static ObservableList<Cargo> getCargoList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Cargo objects
        ObservableList<Cargo> crgList = FXCollections.observableArrayList();
 
        while (rs.next()) {
            Cargo crg = new Cargo();
            crg.setCargoId(rs.getInt("produkt_id"));
            crg.setCargoName(rs.getString("nazwa"));
            crg.setCargoPrice(rs.getString("cena_jednostkowa"));
            crg.setCargoExpirationDate(rs.getDate("data_waznosci"));
            crg.setCargoQuantity(rs.getInt("ilosc"));
            crg.setCargoPosition(rs.getString("polozenie"));
            crg.setCargoProvider(rs.getString("dostawca_importer_id"));
            crg.setCargoOrigin(rs.getString("kraj_pochodzenia"));
            //Add Cargo to the ObservableList
            crgList.add(crg);
        }
        //return crgList (ObservableList of Cargos)
        return crgList;
    }    
}