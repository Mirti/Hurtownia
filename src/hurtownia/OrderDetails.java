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
 * Declare Cargo Table Columns
 * 
 * @author Jon
 */
public class OrderDetails {
    private IntegerProperty orderDetailsId;
    private StringProperty orderDetailsName;
    private StringProperty orderDetailsQuantity;
    private SimpleObjectProperty<Date> orderDetailsDate;
    private StringProperty orderDetailsComment;
    
    /**
     * Constructor
     */
    public OrderDetails(){
        this.orderDetailsId = new SimpleIntegerProperty();
        this.orderDetailsName = new SimpleStringProperty();
        this.orderDetailsQuantity =  new SimpleStringProperty();
        this.orderDetailsDate = new SimpleObjectProperty<>();
        this.orderDetailsComment =  new SimpleStringProperty();    
    }
        
        /**
         * 
         * @return Order Details ID
         */
        public int getOrderDetailsId(){
            return orderDetailsId.get();
        }
        
        /**
         * Set Order Details ID
         * @param Id 
         */
        public void setOrderDetailsId(int Id){
            this.orderDetailsId.set(Id);            
        }
        
        /**
         * 
         * @return Order Detail ID for table
         */
        public IntegerProperty orderDetailsIdProperty(){
            return orderDetailsId;
        }
        
        /**
         * 
         * @return Order Details Name
         */
        public String getOrderDetailsName(){
            return orderDetailsName.get();
        }
        
        /**
         * Set Order Details Name
         * @param Name
         */
        public void setOrderDetailsName(String Name){
            this.orderDetailsName.set(Name);            
        }
        
        /**
         * 
         * @return Order Details Name for table
         */
        public StringProperty orderDetailsNameProperty(){
            return orderDetailsName;
        } 
        
        /**
         * 
         * @return Order Details Quantity
         */
        public String getOrderDetailsQuantity(){
            return orderDetailsName.get();
        }
        
        /**
         * Set Order Details quantity
         * @param quantity
         */
        public void setOrderDetailsQuantity(String quantity){
            this.orderDetailsQuantity.set(quantity);           
        }
        
        /**
         * 
         * @return Order Details quantity for table
         */
        public StringProperty orderDetailsQuantityProperty(){
            return orderDetailsQuantity;
        }  
        
        /**
         * 
         * @return Order Details expiration date
         */
        public Date getOrderDetailsDate(){
            return orderDetailsDate.get();
        }
        
        /**
         * Set Order Details expiration date
         * @param Date 
         */
        public void setOrderDetailsDate(Date Date){
            this.orderDetailsDate.set(Date);           
        }
        
        /**
         * 
         * @return Order Details expiration date for table
         */
        public SimpleObjectProperty<Date> orderDetailsDateProperty(){
            return orderDetailsDate;
        } 
        
        /**
         * 
         * @return Order Details Comment
         */
        public String getOrderDetailsComment(){
            return orderDetailsComment.get();
        }
        
        /**
         * Set Order Details Comment
         * @param comment 
         */
        public void setOrderDetailsComment(String comment){
            this.orderDetailsComment.set(comment);            
        }
        
        /**
         * 
         * @return Order Details Comment for table
         */
        public StringProperty orderDetailsCommentProperty(){
            return orderDetailsComment;
        }
        
                  
}
