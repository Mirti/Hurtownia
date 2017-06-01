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


public class Contractor {
    
    /**
     * Declare Contractor Table Columns
     */
    private IntegerProperty contractorId;
    private StringProperty contractorName;
    private StringProperty contractorType;
    private StringProperty contractorOwner;
    private StringProperty contractorAdress;
    private IntegerProperty contractorPhone;
    private StringProperty contractorEmail;
    
    /**
     * Constructor
     */
    public Contractor(){
        this.contractorId = new SimpleIntegerProperty();
        this.contractorName = new SimpleStringProperty();
        this.contractorType =  new SimpleStringProperty();
        this.contractorOwner = new SimpleStringProperty();
        this.contractorAdress =  new SimpleStringProperty();
        this.contractorPhone =  new SimpleIntegerProperty();
        this.contractorEmail =  new SimpleStringProperty();      
    }
        //Contractor Id
        public int getContractorId(){
            return contractorId.get();
        }
        
        public void setContractorId(int ContractorId){
            this.contractorId.set(ContractorId);            
        }
        
        public IntegerProperty ContractorIdProperty(){
            return contractorId;
        }
        
        //Contractor Name
        public String getContractorName(){
            return contractorName.get();
        }
        
        public void setContractorName(String ContractorName){
            this.contractorName.set(ContractorName);            
        }
        
        public StringProperty ContractorNameProperty(){
            return contractorName;
        } 
        
        //Contractor Type
        public String getContractorType(){
            return contractorType.get();
        }
        
        public void setContractorType(String ContractorType){
            this.contractorType.set(ContractorType);            
        }
        
        public StringProperty ContractorTypeProperty(){
            return contractorType;
        }
        
        //Contractor Owner
        public String getContractorOwner(){
            return contractorOwner.get();
        }
        
        public void setContractorOwner(String ContractorOwner){
            this.contractorOwner.set(ContractorOwner);            
        }
        
        public StringProperty ContractorOwnerProperty(){
            return contractorOwner;
        }
        
        //Contractor Adress
        public String getContractorAdress(){
            return contractorAdress.get();
        }
        
        public void setContractorAdress(String ContractorAdress){
            this.contractorAdress.set(ContractorAdress);            
        }
        
        public StringProperty ContractorAdressProperty(){
            return contractorAdress;
        }
        
        //Contractor Phone
        public int getContractorPhone(){
            return contractorPhone.get();
        }
        
        public void setContractorPhone(int ContractorPhone){
            this.contractorPhone.set(ContractorPhone);            
        }
        
        public IntegerProperty ContractorPhoneProperty(){
            return contractorPhone;
        }
        
        //Contractor Email
        public String getContractorEmail(){
            return contractorEmail.get();
        }
        
        public void setContractorEmail(String ContractorEmail){
            this.contractorEmail.set(ContractorEmail);            
        }
        
        public StringProperty ContractorEmailProperty(){
            return contractorEmail;
        }
       
        
    
}
