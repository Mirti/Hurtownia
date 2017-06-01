package hurtownia;

import java.sql.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Declare Cargo Table Columns
 */
public class In {
    private IntegerProperty inId;
    private StringProperty inName;
    private StringProperty inPrice;
    private SimpleObjectProperty<Date> inDate;
    private IntegerProperty inQuantity;
    private StringProperty inPosition;
    private StringProperty inProvider;
    private StringProperty inOrigin;
    
    /**
     * Constructor
     */
    public In(){
        this.inId = new SimpleIntegerProperty();
        this.inName = new SimpleStringProperty();
        this.inPrice =  new SimpleStringProperty();
        this.inDate = new SimpleObjectProperty<>();
        this.inQuantity =  new SimpleIntegerProperty();
        this.inPosition =  new SimpleStringProperty();
        this.inProvider =  new SimpleStringProperty();
        this.inOrigin =  new SimpleStringProperty();        
    }
        //Cargo Id
        public int getInId(){
            return inId.get();
        }
        
        public void setInId(int Id){
            this.inId.set(Id);            
        }
        
        public IntegerProperty inIdProperty(){
            return inId;
        }
        
        //Cargo Name
        public String getInName(){
            return inName.get();
        }
        
        public void setInName(String Name){
            this.inName.set(Name);            
        }
        
        public StringProperty inNameProperty(){
            return inName;
        } 
        
        //cargo Price
        public String getInPrice(){
            return inName.get();
        }
        
        public void setInPrice(String Price){
            this.inPrice.set(Price);           
        }
        
        public StringProperty inPriceProperty(){
            return inPrice;
        }  
        
        //cargo Expiration Date
        public Date getInDate(){
            return inDate.get();
        }
        
        public void setInDate(Date Date){
            this.inDate.set(Date);           
        }
        
        public SimpleObjectProperty<Date> inDateProperty(){
            return inDate;
        } 
        //cargo Quantity
        public int getInQuantity(){
            return inQuantity.get();
        }
        
        public void setInQuantity(int in){
            this.inQuantity.set(in);            
        }
        
        public IntegerProperty inQuantityProperty(){
            return inQuantity;
        } 
        
        //cargo Position
        public String getInPosition(){
            return inPosition.get();
        }
        
        public void setInPosition(String Position){
            this.inPosition.set(Position);            
        }
        
        public StringProperty inPositionProperty(){
            return inPosition;
        }
        
        //cargo Provider
        public String getInProvider(){
            return inProvider.get();
        }
        
        public void setInProvider(String Provider){
            this.inProvider.set(Provider);            
        }
        
        public StringProperty inProviderProperty(){
            return inProvider;
        } 
        
        //cargo Provider
        public String getInOrigin(){
            return inOrigin.get();
        }
        
        public void setInOrigin(String Origin){
            this.inOrigin.set(Origin);            
        }
        
        public StringProperty inOriginProperty(){
            return inOrigin;
        }    
}
