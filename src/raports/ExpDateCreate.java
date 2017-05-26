/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raports;

import java.io.FileOutputStream;
import java.util.Date;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import hurtownia.Polaczenie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Class to generate Expiration Date Reports
 * @author Mirti 
 */
public class ExpDateCreate {

    static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    static Date date = new Date();
    static String currentDate = dateFormat.format(date);
    private static final String FILE = "reports\\" + "RaportWaznosci" + currentDate + ".pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    private static Document document;
    private static String date1; 
    private static String date2;

    /**
     * Creates PDF file with report.
     * 
     * @param dateA - Strart date
     * @param dateB - Finish date
     */
    public static void create(String dateA, String dateB) {
        try {
            date1 = dateA;
            date2 = dateB;
            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document);
            addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set meta dates to document (eg. tittle)
     * 
     * @param document - document to add meta data
     */
    private static void addMetaData(Document document) {
        document.addTitle("Raport dat ważności " + currentDate);
    }
    
    /**
     * Add first part to document. For exaple tittle, author name or headers.
     * 
     * @param document - document to add tittle page
     * @throws DocumentException - Throws when occurs problem with document
     */

    private static void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Raport dat wazności " + currentDate, catFont));

        addEmptyLine(preface, 1);
        preface.add(new Paragraph(
                "Raport wygenerowany przez: " + Polaczenie.getCurrentUser()[1]+" "+Polaczenie.getCurrentUser()[2]));
        addEmptyLine(preface, 3);
        preface.add(new Paragraph("Wykaz produktow z konczacymi sie datami waznosci", smallBold));
        addEmptyLine(preface,2);
        document.add(preface);
    }
    
    /**
     * Add content to document. In this case it is use to add Table
     * 
     * @param document - document do add content
     * @throws DocumentException - Throws when occurs problem with document
     * @throws BadElementException - Throws when occurs problem with element to addition
     * @throws SQLException - Throws when occurs problem with SQL query
     */
    private static void addContent(Document document) throws DocumentException, BadElementException, SQLException {
        createTable(date1, date2);

    }

    /**
     * Method to create table with data from SQL query
     * 
     * @param date1 - Start date
     * @param date2 = Finish date
     * @throws BadElementException - Throws when occurs problem with element to addition
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws DocumentException - Throws when occurs proble with document
     */
    private static void createTable(String date1, String date2) throws BadElementException, SQLException, DocumentException {
        ResultSet rs = Polaczenie.getData("SELECT p.nazwa,p.data_waznosci,p.ilosc,di.nazwa FROM"
                + " produkt p, dostawca_importer di WHERE "
                + "p.dostawca_importer_id = di.dostawca_importer_id AND "
                + "data_waznosci BETWEEN '" + date1 + "' AND '" + date2 + "' ORDER BY data_waznosci ASC");
        PdfPTable table = new PdfPTable(4);

        PdfPCell c1 = new PdfPCell(new Phrase("Nazwa"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Data waznosci"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Dostawca"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Ilosc"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        while (rs.next()) {
            table.addCell(rs.getString("nazwa"));
            table.addCell(rs.getDate("data_waznosci").toString());
            table.addCell(rs.getString("di.nazwa"));
            table.addCell(String.valueOf(rs.getInt("ilosc")));
        }

        document.add(table);
    }
    
    /**
     * Creates empty line in document
     * 
     * @param paragraph - Paragraph in document where method creates empty line
     * @param number - Number of empty lines added to paragraph
     */

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
