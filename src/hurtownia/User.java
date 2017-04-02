package hurtownia;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class User {
   //Declare Contractor Table Columns
    private IntegerProperty userId;
    private StringProperty userFirstName;
    private StringProperty userLastName;
    private StringProperty userPermissions;
    private StringProperty userLogin;
    
    //Constructor
    public User(){
        this.userId = new SimpleIntegerProperty();
        this.userFirstName = new SimpleStringProperty();
        this.userLastName =  new SimpleStringProperty();
        this.userPermissions = new SimpleStringProperty();
        this.userLogin =  new SimpleStringProperty();     
    }
        //Contractor Id
        public int getUserId(){
            return userId.get();
        }
        
        public void setUserId(int UserId){
            this.userId.set(UserId);            
        }
        
        public IntegerProperty UserIdProperty(){
            return userId;
        }
        
        //Contractor Name
        public String getUserFirstName(){
            return userFirstName.get();
        }
        
        public void setUserFirstName(String UserFirstName){
            this.userFirstName.set(UserFirstName);            
        }
        
        public StringProperty UserFirstNameProperty(){
            return userFirstName;
        } 
        
        //Contractor Type
        public String getUserLastName(){
            return userLastName.get();
        }
        
        public void setUserLastName(String UserLastName){
            this.userLastName.set(UserLastName);            
        }
        
        public StringProperty UserLastNameProperty(){
            return userLastName;
        }
        
        //Contractor Owner
        public String getUserPermissions(){
            return userPermissions.get();
        }
        
        public void setUserPermissions(String UserPermissions){
            this.userPermissions.set(UserPermissions);            
        }
        
        public StringProperty UserPermissionsProperty(){
            return userPermissions;
        }
        
        //Contractor Adress
        public String getUserLogin(){
            return userLogin.get();
        }
        
        public void setUserLogin(String UserLogin){
            this.userLogin.set(UserLogin);            
        }
        
        public StringProperty UserLoginProperty(){
            return userLogin;
        }       
    
}
