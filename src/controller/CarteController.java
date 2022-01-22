/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import dbase.Tools;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author Alilo
 */
public class CarteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
      @FXML
    private TextField txtnom;

    @FXML
    private TextField txtprenom;

    @FXML
    private TextField txtid;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      txtid.setText(MembreController.carte_membre_id);
    }    
     void create_carte(String filename,String code,String nom,String prenom)
	{

        try
        {
           
            Document document=new Document();
            Rectangle rec=new Rectangle(Utilities.millimetersToPoints(100),Utilities.millimetersToPoints(100));
            // Rectangle rec=new Rectangle(850,850);
            document.setPageSize(rec);
            PdfWriter writer=PdfWriter.getInstance(document,new FileOutputStream("D:\\gym_membre\\"+filename+".pdf"));
            document.open();
          
             
           
            document.add(new Paragraph("_______________________________"));
            document.add(new Paragraph("\t           \t\t BODY KASS \n\n" ,FontFactory.getFont(FontFactory.TIMES_BOLD,13,Font.BOLD,BaseColor.BLUE)));
            document.add(new Paragraph("Nom :\t "+nom+"\n",FontFactory.getFont(FontFactory.TIMES_BOLD) ));
            document.add(new Paragraph("Prenom :\t"+prenom+"\n",FontFactory.getFont(FontFactory.TIMES_BOLD)));
            com.itextpdf.text.Image  imagge=com.itextpdf.text.Image.getInstance("dumble.png");
          imagge.setAbsolutePosition(180, 150);
           document.add(imagge);
           document.add(new Paragraph("\n"));
        /*    Barcode128 b=new Barcode128();
            b.setCode(code);
            b.setCodeType(Barcode128.CODE128);
            
            document.add(b.createImageWithBarcode(writer.getDirectContent(), null,null));*/
        PdfContentByte cb = writer.getDirectContent();
            Barcode128 code128 = new Barcode128();
	        code128.setCode(code);
	        code128.setCodeType(Barcode128.CODE128);
	       com.itextpdf.text.Image  code128Image = code128.createImageWithBarcode(cb, null, null);
	        
	        code128Image.setAbsolutePosition(100, 95);
	        code128Image.scalePercent(125);
	        document.add(code128Image);
           
           
          /*  PdfContentByte cb = writer.getDirectContent();
            Barcode128 code128 = new Barcode128();
	        code128.setCode(code);
	        code128.setCodeType(Barcode128.CODE128);
	         com.itextpdf.text.Image code128Image = code128.createImageWithBarcode(cb, null, null);
	        
	        code128Image.setAbsolutePosition(90, 680);
	        code128Image.scalePercent(125);
	        document.add(code128Image);
    */
       
           
	     document.add(new Paragraph("\n"
	        		+ "\n_______________________________"));
            document.close();
            new Tools().msgbox("Carte Enregistré");
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
		
	}
     @FXML
     void create_carte(ActionEvent e){
         create_carte(txtnom.getText()+txtprenom.getText(), txtid.getText(), txtnom.getText(), txtprenom.getText());
     }
    
}
