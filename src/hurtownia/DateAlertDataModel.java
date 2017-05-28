/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author nykie_000
 */
public class DateAlertDataModel {
    
    SimpleStringProperty rName;
    SimpleStringProperty rNum;
    SimpleStringProperty rPos;
    SimpleStringProperty rDate;
/**
 * Constructor which needs four String variables to set values of this class variables.
 * @param rName variable used to set value of cell "Nazwa" of TableView in file FormularzZamowienie.fxml
 * @param rNum variable used to set value of cell "Ilosc" of TableView in file FormularzZamowienie.fxml
 * @param rPos variable used to set value of cell "Dostawca" of TableView in file FormularzZamowienie.fxml
 * @param rDate variable used to set value of cell "Data Waznosci" of TableView in file FormularzZamowienie.fxml
 */
     public DateAlertDataModel(String rName, String rNum, String rPos, String rDate) {
        this.rName = new SimpleStringProperty(rName);
        this.rNum = new SimpleStringProperty(rNum);
        this.rPos = new SimpleStringProperty(rPos);
        this.rDate = new SimpleStringProperty(rDate);
    } 
/**
 * Method returns value of variable rName immediately.
 * @return rName of product
 */
    public String getRName() {
    return rName.get();
}
/**
 * Method sets value variable of rName immediately.
 * @param v used to set value of rName, String variable.
 */
    public void setRName(String v) {
        this.rName.set(v);
    }
/**
 * Method returns value of rDate always immediately.
 * @return rDate of product
 */
    public String getRDate() {
        return rDate.get();
    }
/**
 * Method sets value variable of rDate immediately.
 * @param d used to set value of rDate, String variable. 
 */
    public void setRDate(String d) {
        this.rDate.set(d);
    }
/**
 * Method returns value of rNum always immediately.
 * @return rNum of product 
 */
    public String getRNum() {
        return rNum.get();
    }
/**
 * Method sets value variable of rNum immediately.
 * @param n used to set value of rNum, String variable. 
 */
    public void setRNum(String n) {
         this.rNum.set(n);
    }
/**
 * Method returns value of rSupp always immediately.
 * @return rSupp of product 
 */
    public String getRPos() {
        return rPos.get();
    }
/**
 * Method sets value variable of rSupp immediately.
 * @param p used to set value of rSupp, String variable.  
 */
    public void setRPos(String p) {
       this.rPos.set(p); 
    }
}
