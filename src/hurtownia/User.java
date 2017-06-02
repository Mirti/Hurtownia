package hurtownia;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Declare Contractor Table Columns
 */
public class User {
    private IntegerProperty userId;
    private StringProperty userFirstName;
    private StringProperty userLastName;
    private StringProperty userPermissions;
    private StringProperty userLogin;
    private StringProperty userPassword;    
    private StringProperty userComment;  
    
    /**
     * Constructor
     */
    public User(){
        this.userId = new SimpleIntegerProperty();
        this.userFirstName = new SimpleStringProperty();
        this.userLastName =  new SimpleStringProperty();
        this.userPermissions = new SimpleStringProperty();
        this.userLogin =  new SimpleStringProperty(); 
        this.userPassword =  new SimpleStringProperty();   
        this.userComment =  new SimpleStringProperty();           
    }
        /**
         * 
         * @return User ID
         */
        public int getUserId(){
            return userId.get();
        }
        
        /**
         * Set user ID
         * @param UserId 
         */
        public void setUserId(int UserId){
            this.userId.set(UserId);            
        }
        
        /**
         * 
         * @return User ID for table
         */
        public IntegerProperty UserIdProperty(){
            return userId;
        }
        
        /**
         * 
         * @return User first name
         */
        public String getUserFirstName(){
            return userFirstName.get();
        }
        
        /**
         * Set user first name
         * @param UserFirstName 
         */
        public void setUserFirstName(String UserFirstName){
            this.userFirstName.set(UserFirstName);            
        }
        
        /**
         * 
         * @return User first name for table
         */
        public StringProperty UserFirstNameProperty(){
            return userFirstName;
        } 
        
        /**
         * 
         * @return User last name
         */
        public String getUserLastName(){
            return userLastName.get();
        }
        
        /**
         * Set user last name
         * @param UserLastName 
         */
        public void setUserLastName(String UserLastName){
            this.userLastName.set(UserLastName);            
        }
        
        /**
         * 
         * @return jser last name for table
         */
        public StringProperty UserLastNameProperty(){
            return userLastName;
        }
        
        /**
         * 
         * @return User permission
         */
        public String getUserPermissions(){
            return userPermissions.get();
        }
        
        /**
         * Set user permission
         * @param UserPermissions 
         */
        public void setUserPermissions(String UserPermissions){
            this.userPermissions.set(UserPermissions);            
        }
        
        /**
         * 
         * @return User permission for table
         */
        public StringProperty UserPermissionsProperty(){
            return userPermissions;
        }
        
        /**
         * 
         * @return User login
         */
        public String getUserLogin(){
            return userLogin.get();
        }
        
        /**
         * Set user login
         * @param UserLogin 
         */
        public void setUserLogin(String UserLogin){
            this.userLogin.set(UserLogin);            
        }
        
        /**
         * 
         * @return user login for table
         */
        public StringProperty UserLoginProperty(){
            return userLogin;
        } 
        
        /**
         * 
         * @return User password
         */
        public String getUserPassword(){
            return userPassword.get();
        }
        
        /**
         * Set user password
         * @param UserPassword 
         */
        public void setUserPassword(String UserPassword){
            this.userPassword.set(UserPassword);            
        }
        
        /**
         * 
         * @return User password for table
         */
        public StringProperty UserPasswordProperty(){
            return userPassword;
        } 
        
        /**
         * 
         * @return User comment
         */
        public String getUserComment(){
            return userComment.get();
        }
        
        /**
         * Set user comment
         * @param UserComment 
         */
        public void setUserComment(String UserComment){
            this.userComment.set(UserComment);            
        }
        
        /**
         * 
         * @return user comment for table
         */
        public StringProperty UserCommentProperty(){
            return userComment;
        }         
    
}
