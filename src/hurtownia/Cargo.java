package hurtownia;

import javafx.beans.property.*;
import java.sql.Date;


public class Cargo {
    
    //Declare Cargo Table Columns
    private IntegerProperty cargoId;
    private StringProperty cargoName;
    private StringProperty cargoPrice;
    private SimpleObjectProperty<Date> cargoExpirationDate;
    private IntegerProperty cargoQuantity;
    private StringProperty cargoPosition;
    private StringProperty cargoProvider;
    private StringProperty cargoOrigin;
    
    //Constructor
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
        //Cargo Id
        public int getCargoId(){
            return cargoId.get();
        }
        
        public void setCargoId(int cargoId){
            this.cargoId.set(cargoId);            
        }
        
        public IntegerProperty cargoIdProperty(){
            return cargoId;
        }
        
        //Cargo Name
        public String getCargoName(){
            return cargoName.get();
        }
        
        public void setCargoName(String cargoName){
            this.cargoName.set(cargoName);            
        }
        
        public StringProperty cargoNameProperty(){
            return cargoName;
        } 
        
        //cargo Price
        public String getCargoPrice(){
            return cargoName.get();
        }
        
        public void setCargoPrice(String cargoPrice){
            this.cargoPrice.set(cargoPrice);           
        }
        
        public StringProperty cargoPriceProperty(){
            return cargoPrice;
        }  
        
        //cargo Expiration Date
        public Date getCargoExpirationDate(){
            return cargoExpirationDate.get();
        }
        
        public void setCargoExpirationDate(Date cargoExpirationDate){
            this.cargoExpirationDate.set(cargoExpirationDate);           
        }
        
        public SimpleObjectProperty<Date> cargoExpirationDateProperty(){
            return cargoExpirationDate;
        } 
        //cargo Quantity
        public int getCargoQuantity(){
            return cargoQuantity.get();
        }
        
        public void setCargoQuantity(int cargoQuantity){
            this.cargoQuantity.set(cargoQuantity);            
        }
        
        public IntegerProperty cargoQuantityProperty(){
            return cargoQuantity;
        } 
        
        //cargo Position
        public String getCargoPosition(){
            return cargoPosition.get();
        }
        
        public void setCargoPosition(String cargoPosition){
            this.cargoPosition.set(cargoPosition);            
        }
        
        public StringProperty cargoPositionProperty(){
            return cargoPosition;
        }
        
        //cargo Provider
        public String getCargoProvider(){
            return cargoProvider.get();
        }
        
        public void setCargoProvider(String cargoProvider){
            this.cargoProvider.set(cargoProvider);            
        }
        
        public StringProperty cargoProviderProperty(){
            return cargoProvider;
        } 
        
        //cargo Provider
        public String getCargoOrigin(){
            return cargoOrigin.get();
        }
        
        public void setCargoOrigin(String cargoOrigin){
            this.cargoOrigin.set(cargoOrigin);            
        }
        
        public StringProperty cargoOriginProperty(){
            return cargoOrigin;
        }         
}

