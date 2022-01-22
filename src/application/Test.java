/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

/**
 *
 * @author Alilo
 */
public class Test {
    
    
    public static void main(String[]args) throws Exception, DocumentException
    {
        Document d=new Document(new Rectangle(PageSize.A4));
       PdfWriter p=PdfWriter.getInstance(d, new FileOutputStream("D:\\gym_membre\\al1i124.pdf"));
       d.open();
     /*  Barcode128 b=new Barcode128();
       b.setCode("1");
       d.add(b.createImageWithBarcode(p.getDirectContent(), null,null));*/
       
       
       PdfContentByte cb = p.getDirectContent();
            Barcode128 code128 = new Barcode128();
	        code128.setCode("1234");
	        code128.setCodeType(Barcode128.CODE128);
	         com.itextpdf.text.Image code128Image = code128.createImageWithBarcode(cb, null, null);
	        
	        code128Image.setAbsolutePosition(90, 680);
	        code128Image.scalePercent(125);
	        d.add(code128Image);
       
       
       
       
       d.close();
       System.out.println("sucrsrs");
    }
    
}
