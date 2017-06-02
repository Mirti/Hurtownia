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
        
        /**
         * @return In product ID
         */
        public int getInId(){
            return inId.get();
        }
        
        /**
         * Set In product ID
         * @param Id 
         */
        public void setInId(int Id){
            this.inId.set(Id);            
        }
        
        /**
         * 
         * @return In product ID for table
         */
        public IntegerProperty inIdProperty(){
            return inId;
        }
        
        /**
         * 
         * @return In product name
         */
        public String getInName(){
            return inName.get();
        }
        
        /**
         * Set In product name
         * @param Name 
         */
        public void setInName(String Name){
            this.inName.set(Name);            
        }
        
        /**
         * 
         * @return In product name for table
         */
        public StringProperty inNameProperty(){
            return inName;
        } 
        
        /**
         * 
         * @return In product price
         */
        public String getInPrice(){
            return inName.get();
        }
        
        /**
         * Set In product price
         * @param Price 
         */
        public void setInPrice(String Price){
            this.inPrice.set(Price);           
        }
        
        /**
         * 
         * @return In product price for table
         */
        public StringProperty inPriceProperty(){
            return inPrice;
        }  
        
        /**
         * 
         * @return In product expiration date
         */
        public Date getInDate(){
            return inDate.get();
        }
        
        /**
         * Set In product expiration date
         * @param Date 
         */
        public void setInDate(Date Date){
            this.inDate.set(Date);           
        }
        
        /**
         * 
         * @return In product expiration date for table
         */
        public SimpleObjectProperty<Date> inDateProperty(){
            return inDate;
        } 
        
        /**
         * 
         * @return In product quantity
         */
        public int getInQuantity(){
            return inQuantity.get();
        }
        
        /**
         * Set In product quantity
         * @param in 
         */
        public void setInQuantity(int in){
            this.inQuantity.set(in);            
        }
        
        /**
         * 
         * @return In product quantity for table
         */
        public IntegerProperty inQuantityProperty(){
            return inQuantity;
        } 
        
        /**
         * 
         * @return In product postition
         */
        public String getInPosition(){
            return inPosition.get();
        }
        
        /**
         * Set In product position
         * @param Position 
         */
        public void setInPosition(String Position){
            this.inPosition.set(Position);            
        }
        
        /**
         * 
         * @return In product position for table
         */
        public StringProperty inPositionProperty(){
            return inPosition;
        }
        
        /**
         * 
         * @return In product provider
         */
        public String getInProvider(){
            return inProvider.get();
        }
        
        /**
         * Set In product provider
         * @param Provider 
         */
        public void setInProvider(String Provider){
            this.inProvider.set(Provider);            
        }
        
        /**
         * 
         * @return In product provider for table
         */
        public StringProperty inProviderProperty(){
            return inProvider;
        } 
        
        /**
         * 
         * @return In product origin
         */
        public String getInOrigin(){
            return inOrigin.get();
        }
        
        /**
         * Set In product origin
         * @param Origin 
         */
        public void setInOrigin(String Origin){
            this.inOrigin.set(Origin);            
        }
        
        /**
         * 
         * @return In product origin for table
         */
        public StringProperty inOriginProperty(){
            return inOrigin;
        }    
}
