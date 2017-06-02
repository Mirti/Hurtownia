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
 * Declare Contractor Table Columns
 */
public class Out {
    private IntegerProperty outId;
    private StringProperty outCustomer;
    private StringProperty outUser;
    private StringProperty outComment;
    private StringProperty outValue;
    
    /**
     * Constructor
     */
    public Out(){
        this.outId = new SimpleIntegerProperty();
        this.outCustomer = new SimpleStringProperty();
        this.outUser = new SimpleStringProperty();
        this.outComment =  new SimpleStringProperty();
        this.outValue = new SimpleStringProperty();
    }
        /**
         * 
         * @return Out product ID
         */
        public int getOutId(){
            return outId.get();
        }
        
        /**
         * Set Out product ID
         * @param Id 
         */
        public void setOutId(int Id){
            this.outId.set(Id);            
        }
        
        /**
         * 
         * @return Out product ID for table
         */
        public IntegerProperty outIdProperty(){
            return outId;
        }
        
        /**
         * 
         * @return Out product customer
         */
        public String getOutCustomer(){
            return outCustomer.get();
        }
        
        /**
         * Set Out product customer
         * @param OutCustomer 
         */
        public void setOutCustomer(String OutCustomer){
            this.outCustomer.set(OutCustomer);            
        }
        
        /**
         * 
         * @return Out product customer for table
         */
        public StringProperty outCustomerProperty(){
            return outCustomer;
        } 
        
        /**
         * 
         * @return Out product creator
         */
        public String getOutUser(){
            return outUser.get();
        }
        
        /**
         * Set Out product creator
         * @param OutUser 
         */
        public void setOutUser(String OutUser){
            this.outUser.set(OutUser);            
        }
        
        /**
         * 
         * @return Out product creator for table
         */
        public StringProperty outUserProperty(){
            return outUser;
        }
        
        /**
         * 
         * @return Out product comment
         */
        public String getOutComment(){
            return outComment.get();
        }
        
        /**
         * Set Out product comment
         * @param OutComment 
         */
        public void setOutComment(String OutComment){
            this.outComment.set(OutComment);            
        }
        
        /**
         * 
         * @return Out product comment for table
         */
        public StringProperty outCommentProperty(){
            return outComment;
        }  
        
        /**
         * 
         * @return Out products value
         */
        public String getOutValue(){
            return outValue.get();
        }
        
        /**
         * Set Out products value
         * @param OutValue 
         */
        public void setOutValue(String OutValue){
            this.outValue.set(OutValue);
        }
        
        /**
         * 
         * @return Out products value for table
         */
        public StringProperty outValueProperty(){
            return outValue;
        }
}
