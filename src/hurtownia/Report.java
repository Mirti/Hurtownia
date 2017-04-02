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
public class Report {
   //Declare Contractor Table Columns
    private IntegerProperty reportId;
    private StringProperty reportType;
    private SimpleObjectProperty<Date> reportDate;
    private StringProperty reportAuthor;
    private StringProperty reportComment;
    
    //Constructor
    public Report(){
        this.reportId = new SimpleIntegerProperty();
        this.reportType = new SimpleStringProperty();
        this.reportDate = new SimpleObjectProperty<>();
        this.reportAuthor = new SimpleStringProperty();
        this.reportComment =  new SimpleStringProperty();     
    }
        //Contractor Id
        public int getReportId(){
            return reportId.get();
        }
        
        public void setReportId(int ReportId){
            this.reportId.set(ReportId);            
        }
        
        public IntegerProperty reportIdProperty(){
            return reportId;
        }
        
        //Contractor Name
        public String getReportType(){
            return reportType.get();
        }
        
        public void setReportType(String ReportType){
            this.reportType.set(ReportType);            
        }
        
        public StringProperty reportTypeProperty(){
            return reportType;
        } 
        
        //Contractor Type
        public Date getReportDate(){
            return reportDate.get();
        }
        
        public void setReportDate(Date ReportDate){
            this.reportDate.set(ReportDate);            
        }
        
        public SimpleObjectProperty<Date> reportDateProperty(){
            return reportDate;
        }
        
        //Contractor Owner
        public String getReportAuthor(){
            return reportAuthor.get();
        }
        
        public void setReportAuthor(String ReportAuthor){
            this.reportAuthor.set(ReportAuthor);            
        }
        
        public StringProperty reportAuthorProperty(){
            return reportAuthor;
        }
        
        //Contractor Adress
        public String getReportComment(){
            return reportComment.get();
        }
        
        public void setReportComment(String ReportComment){
            this.reportComment.set(ReportComment);            
        }
        
        public StringProperty reportCommentProperty(){
            return reportComment;
        }       
        
}
