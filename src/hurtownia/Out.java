/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import java.sql.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jon
 */
public class Out {
   //Declare Contractor Table Columns
    private IntegerProperty outId;
    private StringProperty outCustomer;
    private StringProperty outUser;
    private StringProperty outComment;
    
    //Constructor
    public Out(){
        this.outId = new SimpleIntegerProperty();
        this.outCustomer = new SimpleStringProperty();
        this.outUser = new SimpleStringProperty();
        this.outComment =  new SimpleStringProperty();     
    }
        //Contractor Id
        public int getOutId(){
            return outId.get();
        }
        
        public void setOutId(int Id){
            this.outId.set(Id);            
        }
        
        public IntegerProperty outIdProperty(){
            return outId;
        }
        
        //Contractor Name
        public String getOutCustomer(){
            return outCustomer.get();
        }
        
        public void setOutCustomer(String OutCustomer){
            this.outCustomer.set(OutCustomer);            
        }
        
        public StringProperty outCustomerProperty(){
            return outCustomer;
        } 
        
        //Contractor Owner
        public String getOutUser(){
            return outUser.get();
        }
        
        public void setOutUser(String OutUser){
            this.outUser.set(OutUser);            
        }
        
        public StringProperty outUserProperty(){
            return outUser;
        }
        
        //Contractor Adress
        public String getOutComment(){
            return outComment.get();
        }
        
        public void setOutComment(String OutComment){
            this.outComment.set(OutComment);            
        }
        
        public StringProperty outCommentProperty(){
            return outComment;
        }        
}
