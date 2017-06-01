package hurtownia;

import javafx.beans.property.*;
import java.sql.Date;


public class Cargo {
    
    /**
     * Declare Cargo Table Columns
     */
    private IntegerProperty cargoId;
    private StringProperty cargoName;
    private StringProperty cargoPrice;
    private SimpleObjectProperty<Date> cargoExpirationDate;
    private IntegerProperty cargoQuantity;
    private StringProperty cargoPosition;
    private StringProperty cargoProvider;
    private StringProperty cargoOrigin;
    
    /**
     * Constructor
     */
    
    public Cargo(){
        this.cargoId = new SimpleIntegerProperty();
        this.cargoName = new SimpleStringProperty();
        this.cargoPrice =  new SimpleStringProperty();
        this.cargoExpirationDate = new SimpleObjectProperty<>();
        this.cargoQuantity =  new SimpleIntegerProperty();
        this.cargoPosition =  new SimpleStringProperty();
        this.cargoProvider =  new SimpleStringProperty();
        this.cargoOrigin =  new SimpleStringProperty();        
    }
        /**
         * 
         * @return ID of Cargo
         */
        public int getCargoId(){
            return cargoId.get();
        }
        
        /**
         * Set ID of cargo
         * 
         * @param cargoId 
         */
        public void setCargoId(int cargoId){
            this.cargoId.set(cargoId);            
        }
        
        /**
         * 
         * @return Cargo ID for table
         */
        public IntegerProperty cargoIdProperty(){
            return cargoId;
        }
        
        /**
         * 
         * @return Cargo name
         */
        public String getCargoName(){
            return cargoName.get();
        }
        
        /**
         * Set Cargo name
         * @param cargoName 
         */
        public void setCargoName(String cargoName){
            this.cargoName.set(cargoName);            
        }
        
        /**
         * 
         * @return Cargo name for table
         */
        public StringProperty cargoNameProperty(){
            return cargoName;
        } 
        
        /**
         * 
         * @return Cargo price
         */
        public String getCargoPrice(){
            return cargoName.get();
        }
        
        /**
         * Set Cargo price
         * @param cargoPrice 
         */
        public void setCargoPrice(String cargoPrice){
            this.cargoPrice.set(cargoPrice);           
        }
        
        /**
         * 
         * @return Cargo price for table
         */
        public StringProperty cargoPriceProperty(){
            return cargoPrice;
        }  
        
        /**
         * 
         * @return Cargo expiration date
         */
        public Date getCargoExpirationDate(){
            return cargoExpirationDate.get();
        }
        
        /**
         * Set Cargo expiration date
         * @param cargoExpirationDate 
         */
        public void setCargoExpirationDate(Date cargoExpirationDate){
            this.cargoExpirationDate.set(cargoExpirationDate);           
        }
        
        /**
         * 
         * @return Cargo expiration date for table
         */
        public SimpleObjectProperty<Date> cargoExpirationDateProperty(){
            return cargoExpirationDate;
        } 
        
        /**
         * 
         * @return Cargo quantity
         */
        public int getCargoQuantity(){
            return cargoQuantity.get();
        }
        
        /**
         * Set Cargo quantity
         * @param cargoQuantity 
         */
        public void setCargoQuantity(int cargoQuantity){
            this.cargoQuantity.set(cargoQuantity);            
        }
        
        /**
         * 
         * @return Cargo quantity for table
         */
        public IntegerProperty cargoQuantityProperty(){
            return cargoQuantity;
        } 
        
        /**
         * 
         * @return Cargo position
         */
        public String getCargoPosition(){
            return cargoPosition.get();
        }
        
        /**
         * Set Cargo position
         * @param cargoPosition 
         */
        public void setCargoPosition(String cargoPosition){
            this.cargoPosition.set(cargoPosition);            
        }
        
        /**
         * 
         * @return Cargo position for table
         */
        public StringProperty cargoPositionProperty(){
            return cargoPosition;
        }
        
        /**
         * 
         * @return Cargo provider
         */
        public String getCargoProvider(){
            return cargoProvider.get();
        }
        
        /**
         * Set Cargo provider
         * @param cargoProvider 
         */
        public void setCargoProvider(String cargoProvider){
            this.cargoProvider.set(cargoProvider);            
        }
        
        /**
         * 
         * @return Cargo provider for table
         */
        public StringProperty cargoProviderProperty(){
            return cargoProvider;
        } 
        
        /**
         * 
         * @return Cargo Origin
         */
        public String getCargoOrigin(){
            return cargoOrigin.get();
        }
        
        /**
         * Set Cargo origin
         * @param cargoOrigin 
         */
        public void setCargoOrigin(String cargoOrigin){
            this.cargoOrigin.set(cargoOrigin);            
        }
        
        /**
         * 
         * @return Cargo origin for table
         */
        public StringProperty cargoOriginProperty(){
            return cargoOrigin;
        }         
}

