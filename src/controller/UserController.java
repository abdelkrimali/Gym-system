/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.*;
import datta.User;
import dbase.Tools;
import dbase.Utility;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Alilo
 */
public class UserController implements Initializable {
    @FXML
    private TextField txtimage;
    @FXML
	private ImageView image;
    Image img;
    
    @FXML
    private Tab tabajout_bib;

    @FXML
    private TextField txtuser;

    @FXML
    private Button btnbrowse;

    @FXML
    private TextField txtprenom;

    @FXML
    private TextField txtnum_tel;

    @FXML
    private TextField txtemail;

    

    @FXML
    private TextField txtnom;
@FXML
private JFXButton btnsuppuser;
   

    @FXML
    private PasswordField txtpass;

    @FXML
    private TableView<User> list_user;

    @FXML
    private TableColumn<User, String> nom_user;

    @FXML
    private TableColumn<User, String> prenom_user;

    @FXML
    private TableColumn<User, String>  tel_user;

    @FXML
    private TableColumn<User, String>  email_user;
     @FXML
    private TableColumn<User, String>  user_user;

    @FXML
    private JFXTextField txt_recherch;

    @FXML
    private JFXButton btnimp;
    User user=new User();
   
	Connection con;
    Statement stmt;
    ResultSet rs;
    String url,s;
    PreparedStatement pst;
    ObservableList<User>data=FXCollections.observableArrayList();
    String usernamesupp;
    
    
    
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
        
        void getAllrow()
        {
            try
		{
                    data.clear();
			Setconnection();
			 String select="select nom_utilisateur,nom,prenom,telephone,email from utilisateur ";
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            while(rs.next())
	            {
	            	data.add(new User(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
	            }
                    con.close();
			
		}catch(SQLException ex)
		{
			new Tools().alertmsgbox(ex.getMessage());
		}
		nom_user.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenom_user.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		tel_user.setCellValueFactory(new PropertyValueFactory<>("telephone"));
		email_user.setCellValueFactory(new PropertyValueFactory<>("email"));
               user_user.setCellValueFactory(new PropertyValueFactory<>("username"));
		
		list_user.setItems(data);
        }
        
        @FXML
	void search(KeyEvent event)
	{
		try
		{
			data.clear();
			Setconnection();
			 String select="select nom_utilisateur,nom,prenom,telephone,email from utilisateur where nom like '%"+txt_recherch.getText()+"%'"
		 		+ " or prenom  like '%"+txt_recherch.getText()+"%'";
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            while(rs.next())
	            {
	            	data.add(new User(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
	            }
                    con.close();
		}catch(SQLException ex)
		{
			new Tools().alertmsgbox(ex.getMessage());
		}
		nom_user.setCellValueFactory(new PropertyValueFactory<User,String>("nom"));
		prenom_user.setCellValueFactory(new PropertyValueFactory<User,String>("prenom"));
		tel_user.setCellValueFactory(new PropertyValueFactory<User,String>("telephone"));
		email_user.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
		list_user.setItems(data);
	}
        
         void setinformation()
	{
		list_user.setOnMouseClicked(new EventHandler<MouseEvent>() {

			
			@Override
			public void handle(MouseEvent arg0) {
				user=list_user.getItems().get(list_user.getSelectionModel().getSelectedIndex());
				System.out.println(user.getUsername());
				usernamesupp=user.getUsername();
                                
                                System.out.println(usernamesupp);
				
				btnsuppuser.setDisable(false);
			}
		});
	}
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       try
       {
           btnsuppuser.setDisable(true);
           getAllrow();
           setinformation();
         //  btnsuppuser.setDisable(true);
       }
       catch(Exception e)
       {
           System.out.println(e.getMessage());
       }
    } 
    
    void setvalue() throws Utility
    {
        user.setNom(txtnom.getText());
        user.setPrenom(txtprenom.getText());
        user.setUsername(txtuser.getText());
        user.setPassword(txtpass.getText());
        user.setTelephone(txtnum_tel.getText());
        user.setEmail(txtemail.getText());
        user.setImage(txtimage.getText());
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
		user.add();
              //  create_carte(txtuser.getText(), txtuser.getText(), txtnom.getText(), txtprenom.getText());
                clear();
               
                getAllrow();
		
	}
        
        
        void clear()
        {
            txtnom.setText("");
            txtprenom.setText("");
            txtuser.setText("");
            txtpass.setText("");
            txtnum_tel.setText("");
            txtemail.setText("");
            txtimage.setText("");
            image.setImage(null);
        }
        
        @FXML
        void delete(ActionEvent e)
        {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("حذف مستخدم ");
	        alert.setContentText("Voulez-vous vraiment supprimer cette Utilisateur" + " ?");
	        Optional<ButtonType> answer = alert.showAndWait();
	        if (answer.get() == ButtonType.OK) {
                      user.setUsername(usernamesupp);
                       user.delete();
           
                    getAllrow();
	        	txt_recherch.setText("");
                        
	        	
	        }else {
	           
	            txt_recherch.setText("");
	        }
	        
			getAllrow();
			
			btnsuppuser.setDisable(true);
	        }
           
        
        void create_carte(String filename,String code,String nom,String prenom)
	{

        try
        {
           
            Document document=new Document();
            Rectangle rec=new Rectangle(Utilities.millimetersToPoints(100),Utilities.millimetersToPoints(100));
            document.setPageSize(rec);
            PdfWriter writer=PdfWriter.getInstance(document,new FileOutputStream("D:\\gym_membre\\"+filename+".pdf"));
            document.open();
             
           
            document.add(new Paragraph("___________________________"));
            document.add(new Paragraph("\t           \t\t Carte abonnement \n" ,FontFactory.getFont(FontFactory.TIMES_BOLD,13,Font.BOLD,BaseColor.BLUE)));
            document.add(new Paragraph("Nom :\t "+nom+"\n" ));
            document.add(new Paragraph("Prenom :\t"+prenom+"\n",FontFactory.getFont(FontFactory.TIMES_BOLD)));
           
            Barcode128 b=new Barcode128();
            b.setCode(filename);
            b.setCodeType(Barcode128.CODE128);
            document.add(b.createImageWithBarcode(writer.getDirectContent(), null,null));
            
           
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
	        		+ "\n___________________________"));
            document.close();
            new Tools().msgbox("Carte Enregistré");
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
		
	}
    
}
