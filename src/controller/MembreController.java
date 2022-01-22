/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import static controller.ProgrammeController.test;
import datta.Affectation;
import datta.Membre;
import datta.User;
import dbase.Tools;
import dbase.Utility;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Alilo
 */
public class MembreController implements Initializable {
       @FXML
    private Label lblid;
       @FXML
       private JFXTextField txt_recherch;
       static String nom;
       static String prenom;
       static String id;
       static String tel;
       static String age;
       

    @FXML
    private Label lblnom;
    @FXML 
    private JFXButton btncarte;

    @FXML
    private Label lblprenom;

    @FXML
    private Label lblage;

    @FXML
    private Label lbltel;

    @FXML
    private Label lbldebut;

    @FXML
    private Label lblfin;

    @FXML
    private ImageView img_detail;

    @FXML
    private Label lblimg;
     @FXML
    private ComboBox<String> cbxabonement;

    @FXML
    private TextField prix_abonement;

    @FXML
    private TextField dure_abonemnt;

    @FXML
    private JFXDatePicker startdate;

    @FXML
    private JFXDatePicker enddate;
    
     @FXML
    
    Image img;
     TextField  txtdure;
     @FXML
    private TextField txtage;

    @FXML
    private Button btnbrowse;
    @FXML
    private JFXTextField searchnum_inscr;

    @FXML
    private TextField txtprenom;

    @FXML
    private TextField txttel;

    @FXML
    private TextField txtimage;

    @FXML
    private TextField txtnom;

    @FXML
    private ImageView image;

    @FXML
    private TextField txtid;
     @FXML
    private TextField id_abonement;
     @FXML
    private TableView<Membre> list_membre;

    @FXML
    private TableColumn<Membre, Integer> num_membre;

    @FXML
    private TableColumn<Membre, String> nom_membre;

    @FXML
    private TableColumn<Membre, String> prenom_membre;

    @FXML
    private TableColumn<Membre, Integer> age_membre;

    @FXML
    private TableColumn<Membre, String> tel_membre;
    @FXML 
     private JFXButton btnsupp;
    @FXML 
     private JFXButton btnrenouv;
    
    
    
     Membre m=new Membre();
     Affectation f=new Affectation();
             int supmembre;
             static String carte_membre_id;
     Connection con;
    Statement stmt;
    ResultSet rs;
    String url,s;
    PreparedStatement pst;
    ObservableList<Membre>data=FXCollections.observableArrayList();
    ObservableList<String>listabonemt=FXCollections.observableArrayList();
    static String idmembre;
    static String nom_et_prenom;
    
    
    
     private void SetUrl(){
	        url="jdbc:mysql://localhost:3306/gymkass"
	                +"?useUnicode=true&characterEncoding=UTF-8";
	    }
	void Setconnection()
	    {
	        try{
	        SetUrl();
	      con=DriverManager.getConnection(url,"root","");
	        }
	        catch(SQLException ex){
	            ex.printStackTrace();
	        }
	    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        getAllrow();
        setinformation();
      
       btnsupp.setDisable(true);
       btnrenouv.setDisable(true);
       btncarte.setDisable(true);
            lblid.setText("");
	            	lblnom.setText("");
	            	lblprenom.setText("");
	            	lblage.setText("");
	            	lbltel.setText("");
	            	lblimg.setText("");
	            	img_detail.setImage(null);
       txtid.setText(dbase.Go.GetAutoNumber("membre", "id_membre"));
       detaille_abonemnt();
       cbxabonement.setItems(listabonemt);
      
       handleselection();
    }
     void getAllrow()
        {
            try
		{
                    data.clear();
			Setconnection();
			 String select="select id_membre,nom_membre,prenom_membre,age,telephone from Membre ";
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            while(rs.next())
	            {
	            	data.add(new Membre(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),Integer.parseInt(rs.getString(4)),rs.getString(5)));
	            }
			
                     con.close();
		}catch(SQLException ex)
		{
			new Tools().alertmsgbox(ex.getMessage());
		}
                num_membre.setCellValueFactory(new PropertyValueFactory<>("id"));
		nom_membre.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenom_membre.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                age_membre.setCellValueFactory(new PropertyValueFactory<>("age"));
		tel_membre.setCellValueFactory(new PropertyValueFactory<>("telephone"));
		
		
		list_membre.setItems(data);
               
        }
     @FXML
    void openrenouv(ActionEvent e) throws IOException
    {
        new Tools().openForm("Renouvlement.fxml");
    }
     @FXML
    void openmodif(ActionEvent e) throws IOException
    {
        new Tools().openForm("Modifier_Membre.fxml");
    }
    @FXML
    void test()
    {
        LocalDate i=startdate.getValue();
        txtdure.setText("Date "+i);
        
    }
    
    void setvalue() throws Utility
    {
         
        m.setId(Integer.parseInt(txtid.getText()));
        m.setNom(txtnom.getText());
        m.setPrenom(txtprenom.getText());
       if(null != txtage.getText())
       {
            m.setAge(Integer.parseInt(txtage.getText()));
       }
        m.setTelephone(txttel.getText());
        m.setImage(txtimage.getText());
         java.sql.Date dated=Date.valueOf(startdate.getValue());
         java.sql.Date datef=Date.valueOf(enddate.getValue());
    System.out.println(dated);
          System.out.println(datef);
      m.setDate_debut(dated);
     m.setDate_fin(datef);
    }
    @FXML
    public void btnbrowseaction(ActionEvent event)
	{	
		new Tools().btnbrowseaction(event, txtimage, img, image);
	}
    @FXML
	void ajouter(ActionEvent e) throws Utility 
	{
            setvalue();
            
            setvalue_affectation();
            m.add();
           //  create_carte(m.getNom()+m.getPrenom(), String.valueOf(m.getId()), m.getNom(), m.getPrenom());
                    f.add();
                    m.update_date();
                    clear();
                   
		
		
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
    void afficher_detaille(ActionEvent event) throws Utility,Exception
	  {
		  
				String num=searchnum_inscr.getText();
	            Setconnection();
	            String select="select * from membre where id_membre ="+num ;
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            if(rs.next())
	            {
                        btnrenouv.setDisable(false);
                        idmembre=searchnum_inscr.getText();
                       
	            	lblid.setText(rs.getString("id_membre"));
	            	lblnom.setText(rs.getString("nom_membre"));
	            	lblprenom.setText(rs.getString("prenom_membre"));
                        id=lblid.getText();
                        nom=rs.getString("nom_membre");
                        prenom=rs.getString("prenom_membre");
                        age=rs.getString("age");
                        tel=rs.getString("telephone");
                        
                        nom_et_prenom=lblnom.getText()+"  "+lblprenom.getText();
	            	lblage.setText(rs.getString("age"));
	            	lbltel.setText(rs.getString("telephone"));
                        lbldebut.setText(rs.getString("date_debut"));
                        lblfin.setText(rs.getString("date_fin"));
                        id=lblid.getText();
                        nom=rs.getString("nom_membre");
                        prenom=rs.getString("prenom_membre");
                        age=rs.getString("age");
                        tel=rs.getString("telephone");
	            	if(rs.getString("image")!=null){
	            		if(rs.getString("image").length()==0)
	            		{
	            			img_detail.setImage(null);
	            			lblimg.setText("Aucune image enregisté ");
	            		}
	            	img=new Image(rs.getString("image"));
	            	img_detail.setImage(img);
	            	lblimg.setText("");
	            	}
	            }
	            else
	            {
	            	new Tools().msgbox("Aucune resultat");
	            	lblid.setText("");
	            	lblnom.setText("");
	            	lblprenom.setText("");
	            	lblage.setText("");
	            	lbltel.setText("");
	            	lblimg.setText("");
	            	img_detail.setImage(null);
                        
	            } 
                    con.close();
	  }
    
     void setinformation()
	{
		list_membre.setOnMouseClicked(new EventHandler<MouseEvent>() {

			
			@Override
			public void handle(MouseEvent arg0) {
				m=list_membre.getItems().get(list_membre.getSelectionModel().getSelectedIndex());
				
				//usernamesupp=user.getUsername();
                               System.out.println(m.getId());
                               supmembre=m.getId();
                               carte_membre_id=String.valueOf(m.getId());
				
				btnsupp.setDisable(false);
                                btncarte.setDisable(false);
                                
			}
		});
	}
    
    void clear_data()
    {
        lblid.setText("");
	            	lblnom.setText("");
	            	lblprenom.setText("");
	            	lblage.setText("");
	            	lbltel.setText("");
	            	lblimg.setText("");
	            	img_detail.setImage(null);
    }
    void clear()
    {
        txtid.setText(dbase.Go.GetAutoNumber("membre", "id_membre"));
        txtnom.setText("");
         txtprenom.setText("");
        // txtage.setText("");
         txtimage.setText("file:/D:/gym_image/membre.png");
         txttel.setText("");
         image.setImage(null);
    }
    
    
    void detaille_abonemnt()
    {
         
        try
        {
            Setconnection();
            String select=" select designation from abonement_datta";
             stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            while(rs.next())
	            {
                        System.out.println();
	            	listabonemt.add(rs.getString(1));
                                
	            }
            
        }catch(Exception ex)
        {
            System.out.println("Error");
        }
    }
    
    void handleselection()
    {
        cbxabonement.setOnAction((event) -> {
            try{
                 String val=cbxabonement.getSelectionModel().getSelectedItem().toString();
                 System.out.println(val);
                 Setconnection();
                  String select="select id_abonement,dure_abonement,nouveau_prix_abonement from abonement where designation='"+val+"'";
                 stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            while(rs.next())
	            {
	            	id_abonement.setText(rs.getString(1));
                        prix_abonement.setText(rs.getString(3));
                        dure_abonemnt.setText(rs.getString(2));
                                
	            }
                
            }catch(Exception e)
            {
                e.printStackTrace();
            }
           
            
        });
    }
     @FXML
     void search(KeyEvent e)
        {
            try
		{
                    data.clear();
			Setconnection();
			 String select="select id_membre,nom_membre,prenom_membre,age,telephone from Membre where nom_membre like '%"+txt_recherch.getText()+"%'"
		 		+ " or prenom_membre  like '%"+txt_recherch.getText()+"%'"
                                 + " or telephone  like '%"+txt_recherch.getText()+"%'";
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            while(rs.next())
	            {
	            	data.add(new Membre(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),Integer.parseInt(rs.getString(4)),rs.getString(5)));
	            }
                    con.close();
			
		}catch(SQLException ex)
		{
			new Tools().alertmsgbox(ex.getMessage());
		}
                num_membre.setCellValueFactory(new PropertyValueFactory<>("id"));
		nom_membre.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenom_membre.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                age_membre.setCellValueFactory(new PropertyValueFactory<>("age"));
		tel_membre.setCellValueFactory(new PropertyValueFactory<>("telephone"));
		
		
		list_membre.setItems(data);
        }
    void setvalue_affectation()
    {
        f.setId_affectation(Integer.parseInt(dbase.Go.GetAutoNumber("affectation", "id_affectation")));
      f.setMembre(Integer.parseInt(txtid.getText()));
       
       f.setAbonement(Integer.parseInt(id_abonement.getText()));
        
      /*   java.util.Date sdate =  java.util.Date.from(startdate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date start = new java.sql.Date(sdate.getTime());
      
        System.out.println(start);
        
         java.util.Date edate =  java.util.Date.from(startdate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date end = new java.sql.Date(edate.getTime());
      
     System.out.println(end);*/
     
     java.sql.Date dated=Date.valueOf(startdate.getValue());
     java.sql.Date datef=Date.valueOf(enddate.getValue());
     f.setDate_debut(dated);
     f.setDate_fin(datef);
        
      
    }
    @FXML
    void delete(ActionEvent e)
    {
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("حذف عضو ");
	        alert.setContentText("Voulez-vous vraiment supprimer cette Membre" + " ?");
	        Optional<ButtonType> answer = alert.showAndWait();
	        if (answer.get() == ButtonType.OK) {
                      m.setId(supmembre);
	        	m.delete();
	        	txt_recherch.setText("");
                        txtid.setText(dbase.Go.GetAutoNumber("membre", "id_membre"));
	        	
	        }else {
	           
	            txt_recherch.setText("");
	        }
	        
			getAllrow();
			
			btnsupp.setDisable(true);
	        }
    
    
    @FXML
    void open_carte(ActionEvent e) throws IOException
    {
        new Tools().openForm("Carte.fxml");
    }
    
}
