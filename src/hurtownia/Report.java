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
 * Class to handle Reports object
 * 
 */
public class Report {
   //Declare Contractor Table Columns
    private IntegerProperty reportId;
    private StringProperty reportType;
    private SimpleObjectProperty<Date> reportDate;
    private StringProperty reportAuthor;
    private StringProperty reportPath;
    
    /**
     * Constructor. Creates fields.
     */
    public Report(){
        this.reportId = new SimpleIntegerProperty();
        this.reportType = new SimpleStringProperty();
        this.reportDate = new SimpleObjectProperty<>();
        this.reportAuthor = new SimpleStringProperty();
        this.reportPath =  new SimpleStringProperty();     
    }
        /**
         * Get report ID
         * @return report ID
         */
        public int getReportId(){
            return reportId.get();
        }
        
        /**
         * Set report ID
         * @param ReportId 
         */
        public void setReportId(int ReportId){
            this.reportId.set(ReportId);            
        }
        
        /**
         * Return report ID for table
         * @return report ID
         */
        public IntegerProperty reportIdProperty(){
            return reportId;
        }
        
        /**
         * Get report type
         * @return report type
         */
        public String getReportType(){
            return reportType.get();
        }
        
        /**
         * Set report type
         * @param ReportType 
         */
        public void setReportType(String ReportType){
            this.reportType.set(ReportType);            
        }
        
        /**
         * Get report type for talbe
         * @return report type
         */
        public StringProperty reportTypeProperty(){
            return reportType;
        } 
        
        /**
         * Get report date
         * @return report date
         */
        public Date getReportDate(){
            return reportDate.get();
        }
        
        /**
         * Set report date
         * @param ReportDate 
         */
        public void setReportDate(Date ReportDate){
            this.reportDate.set(ReportDate);            
        }
        
        /**
         * Get report date for table
         * @return report date
         */
        public SimpleObjectProperty<Date> reportDateProperty(){
            return reportDate;
        }
        
        /**
         * Get report author
         * @return report author
         */
        public String getReportAuthor(){
            return reportAuthor.get();
        }
        
        /**
         * Set report author
         * @param ReportAuthor 
         */
        public void setReportAuthor(String ReportAuthor){
            this.reportAuthor.set(ReportAuthor);            
        }
        
        /**
         * Get report author for table
         * @return report author
         */
        public StringProperty reportAuthorProperty(){
            return reportAuthor;
        }
        
        /**
         * Get report path
         * @return report path
         */
        public String getReportPath(){
            return reportPath.get();
        }
        
        /**
         * Set report path
         * @param ReportComment 
         */
        public void setReportPath(String ReportComment){
            this.reportPath.set(ReportComment);            
        }
        
        /**
         * Get report path for table
         * @return report path
         */
        public StringProperty reportPathProperty(){
            return reportPath;
        }       
        
}
