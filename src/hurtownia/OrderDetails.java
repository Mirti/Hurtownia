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
public class OrderDetails {
     //Declare Cargo Table Columns
    private IntegerProperty orderDetailsId;
    private StringProperty orderDetailsName;
    private StringProperty orderDetailsQuantity;
    private SimpleObjectProperty<Date> orderDetailsDate;
    private StringProperty orderDetailsComment;
    
    //Constructor
    public OrderDetails(){
        this.orderDetailsId = new SimpleIntegerProperty();
        this.orderDetailsName = new SimpleStringProperty();
        this.orderDetailsQuantity =  new SimpleStringProperty();
        this.orderDetailsDate = new SimpleObjectProperty<>();
        this.orderDetailsComment =  new SimpleStringProperty();    
    }
        //Cargo Id
        public int getOrderDetailsId(){
            return orderDetailsId.get();
        }
        
        public void setOrderDetailsId(int Id){
            this.orderDetailsId.set(Id);            
        }
        
        public IntegerProperty orderDetailsIdProperty(){
            return orderDetailsId;
        }
        
        //Cargo Name
        public String getOrderDetailsName(){
            return orderDetailsName.get();
        }
        
        public void setOrderDetailsName(String Name){
            this.orderDetailsName.set(Name);            
        }
        
        public StringProperty orderDetailsNameProperty(){
            return orderDetailsName;
        } 
        
        //cargo Price
        public String getOrderDetailsQuantity(){
            return orderDetailsName.get();
        }
        
        public void setOrderDetailsQuantity(String Price){
            this.orderDetailsQuantity.set(Price);           
        }
        
        public StringProperty orderDetailsQuantityProperty(){
            return orderDetailsQuantity;
        }  
        
        //cargo Expiration Date
        public Date getOrderDetailsDate(){
            return orderDetailsDate.get();
        }
        
        public void setOrderDetailsDate(Date Date){
            this.orderDetailsDate.set(Date);           
        }
        
        public SimpleObjectProperty<Date> orderDetailsDateProperty(){
            return orderDetailsDate;
        } 
        
        //cargo Position
        public String getOrderDetailsComment(){
            return orderDetailsComment.get();
        }
        
        public void setOrderDetailsComment(String Position){
            this.orderDetailsComment.set(Position);            
        }
        
        public StringProperty orderDetailsCommentProperty(){
            return orderDetailsComment;
        }
        
                  
}
