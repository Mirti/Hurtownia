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
 * Declare Orders Table Columns
 */
public class Out {
    private IntegerProperty outId;
    private StringProperty outCustomer;
    private StringProperty outUser;
    private StringProperty outComment;
    private StringProperty outValue;
    private StringProperty outState;
    
    /**
     * Constructor
     */
    public Out(){
        this.outId = new SimpleIntegerProperty();
        this.outCustomer = new SimpleStringProperty();
        this.outUser = new SimpleStringProperty();
        this.outComment =  new SimpleStringProperty();
        this.outValue = new SimpleStringProperty();
        this.outState = new SimpleStringProperty();
    }
        /**
         * 
         * @return Orders product ID
         */
        public int getOutId(){
            return outId.get();
        }
        
        /**
         * Set Orders product ID
         * @param Id 
         */
        public void setOutId(int Id){
            this.outId.set(Id);            
        }
        
        /**
         * 
         * @return Orders product ID for table
         */
        public IntegerProperty outIdProperty(){
            return outId;
        }
        
        /**
         * 
         * @return Orders product customer
         */
        public String getOutCustomer(){
            return outCustomer.get();
        }
        
        /**
         * Set Orders customer
         * @param OutCustomer 
         */
        public void setOutCustomer(String OutCustomer){
            this.outCustomer.set(OutCustomer);            
        }
        
        /**
         * 
         * @return Orders customer for table
         */
        public StringProperty outCustomerProperty(){
            return outCustomer;
        } 
        
        /**
         * 
         * @return Orders creator
         */
        public String getOutUser(){
            return outUser.get();
        }
        
        /**
         * Set Orders creator
         * @param OutUser 
         */
        public void setOutUser(String OutUser){
            this.outUser.set(OutUser);            
        }
        
        /**
         * 
         * @return Orders creator for table
         */
        public StringProperty outUserProperty(){
            return outUser;
        }
        
        /**
         * 
         * @return Orders comment
         */
        public String getOutComment(){
            return outComment.get();
        }
        
        /**
         * Set Orders comment
         * @param OutComment 
         */
        public void setOutComment(String OutComment){
            this.outComment.set(OutComment);            
        }
        
        /**
         * 
         * @return Orders comment for table
         */
        public StringProperty outCommentProperty(){
            return outComment;
        }  
        
        /**
         * 
         * @return Orders value
         */
        public String getOutValue(){
            return outValue.get();
        }
        
        /**
         * Set Orders value
         * @param OutValue 
         */
        public void setOutValue(String OutValue){
            this.outValue.set(OutValue);
        }
        
        /**
         * 
         * @return Orders value for table
         */
        public StringProperty outValueProperty(){
            return outValue;
        }
        
        /**
         * 
         * @return Orders state
         */
         public String getOutState(){
            return outState.get();
        }
        
        /**
         * Set Orders state
         * @param OutState 
         */
        public void setOutState(String OutState){
            this.outValue.set(OutState);
        }
        
        /**
         * 
         * @return Orders state for table
         */
        public StringProperty outStateProperty(){
            return outState;
        }
}
