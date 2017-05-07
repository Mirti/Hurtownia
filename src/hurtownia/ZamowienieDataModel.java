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
public class ZamowienieDataModel {

    SimpleStringProperty rName;
    SimpleStringProperty rNum;
    SimpleStringProperty rSupp;
    SimpleStringProperty rDate;

     public ZamowienieDataModel(String rName, String rNum, String rSupp, String rDate) {
        this.rName = new SimpleStringProperty(rName);
        this.rNum = new SimpleStringProperty(rNum);
        this.rSupp = new SimpleStringProperty(rSupp);
        this.rDate = new SimpleStringProperty(rDate);
    } 

    public String getRName() {
    return rName.get();
}

    public void setRName(String v) {
        this.rName.set(v);
    }

    public String getRDate() {
        return rDate.get();
    }

    public void setRDate(String d) {
        this.rDate.set(d);
    }

    public String getRNum() {
        return rNum.get();
    }

    public void setRNum(String n) {
         this.rNum.set(n);
    }

    public String getRSupp() {
        return rSupp.get();
    }

    public void setRSupp(String p) {
       this.rSupp.set(p); 
    }
}
