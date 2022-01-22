package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import dbase.Tools;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MainController implements Initializable{
	@FXML
	private Label lblfin;
        @FXML
	private Label lbljr;
        
	@FXML
	private Label lblnonpaye;
	@FXML
	private Label lblnew;
	@FXML
	private ImageView image;
	@FXML
	private Label nometprenom;
	@FXML
	private Label nbseance;
	@FXML
	private Label jrrestant;
	@FXML
	private TextField txtbarcode;
	String url;
            @FXML
    private Pane p;
    static Connection con=null;
    static   Statement stmt;
    static  ResultSet rs;
   
 
  
	Image img;
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtbarcode.requestFocus();
                nbr_abonee_new();
                nbr_abonee_fin();
               
		
	}
	
	@FXML
    void afficher_detaill(KeyEvent event) 
	  {
		try{
		if(event.getCode()==KeyCode.ENTER)
		{
                    Calendar cal=Calendar.getInstance();
                    java.util.Date currentDate = cal.getTime();
                    java.sql.Date date_now = new java.sql.Date(currentDate.getTime());
                  //  new Tools().msgbox(date.toString());
                    
                
               
				
                    Setconnection();
                    int id=Integer.parseInt(txtbarcode.getText());
	            String select="select nom_membre,prenom_membre,image,date_debut,date_fin from membre where id_membre= "+id;
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            if(rs.next())
	            {
                       
	            	String nom=rs.getString(1);
	            	String prenom=rs.getString(2);
	            	nometprenom.setText(nom+" "+prenom);
                        nbseance.setText(rs.getString(4));
                        jrrestant.setText(rs.getString(5));
                        lbljr.setText(String.valueOf(daysBetween(date_now, rs.getDate(5))));
	            	
	            	
	            	if(rs.getString("image")!=null){
	            		if(rs.getString("image").length()==0)
	            		{
	            			image.setImage(null);
	            		}
	            	img=new Image(rs.getString("image"));
	            	image.setImage(img);
                       // System.out.println(day);
                        String dd=rs.getString(5);
                      /* 
                        Calendar startdate=Calendar.getInstance();
                        java.sql.Date d=rs.getDate(5);
                       startdate.setTime(d);
                              System.out.println(startdate.get(Calendar.MONTH));
                   */
                 System.out.println(daysBetween(date_now, rs.getDate(5)));
                
                 
                 
                 if(daysBetween(date_now, rs.getDate(5))>3){
                    
                   
                     p.setStyle("-fx-background-color: #38f90c" );
                    
                     }
                 else if(daysBetween(date_now, rs.getDate(5))<=0)
                    {
                     p.setStyle("-fx-background-color: #f80415" );
                    
                      
                     }
                 else{
                     p.setStyle("-fx-background-color: #f88b0a" ); 
                      
                 }
	            	
	            	}
	            }
	            else
	            {
	            	new Tools().msgbox("Aucune resultat");
	            	nometprenom.setText("");
	            	nbseance.setText("");
	            	jrrestant.setText("");
	            	txtbarcode.setText("");
	            	image.setImage(null);
                        lbljr.setText("");
                         p.setStyle("-fx-background-color: #d7d0d0" );
                         
	            }   
		}
                con.close();
	  }catch(Exception ex)
	  {
		 new Tools().alertmsgbox("Ce champ accept seulment des Chiffres");
             // ex.printStackTrace();
	  }
                
	  
	  }
    
    @FXML
    void show_detaill(KeyEvent event) 
	  {
		try{
		
				
                    Setconnection();
                    int id=Integer.parseInt(txtbarcode.getText());
	            String select="select nom_membre,prenom_membre,image from membre where id_membre= "+id;
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            if(rs.next())
	            {
	            	String nom=rs.getString(1);
	            	String prenom=rs.getString(2);
	            	nometprenom.setText(nom+" "+prenom);
	            	
	            	
	            	if(rs.getString("image")!=null){
	            		if(rs.getString("image").length()==0)
	            		{
	            			image.setImage(null);
	            		}
	            	img=new Image(rs.getString("image"));
	            	image.setImage(img);
	            	
	            	}
	            }
	            else
	            {
	            	new Tools().msgbox("Aucune resultat");
	            	nometprenom.setText("");
	            	nbseance.setText("");
	            	jrrestant.setText("");
	            	txtbarcode.setText("");
	            	image.setImage(null);
	            }   
		con.close();
	  }catch(Exception ex)
	  {
		  new Tools().alertmsgbox("Ce champ accept seulment des Chiffres");
	  }
	

          }
    public  long daysBetween(Date sd, Date ed) {
    Calendar startDate = Calendar.getInstance();
    startDate.setTime(sd);
    Calendar endDate = Calendar.getInstance();
    endDate.setTime(ed);
    Calendar date = (Calendar) startDate.clone();
    long daysBetween = 0;
    while (date.before(endDate)) {
        date.add(Calendar.DAY_OF_MONTH, 1);
        daysBetween++;
    }
    return daysBetween;
}
    
    
    
    
    void nbr_abonee_new()
    {
        try
        {
             Calendar cal=Calendar.getInstance();
                    java.util.Date currentDate = cal.getTime();
                    java.sql.Date date_now = new java.sql.Date(currentDate.getTime());
            Setconnection();
            String select="select count(*) from membre where date_debut= '"+date_now+"' ";
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            while(rs.next())
	            {
	            	
	            	lblnew.setText(rs.getString(1));
	            	
	            }
                    con.close();
        }catch(Exception ex)
        {
            
        }
    }
     void nbr_abonee_fin()
    {
        try
        {
             Calendar cal=Calendar.getInstance();
                    java.util.Date currentDate = cal.getTime();
                    java.sql.Date date_now = new java.sql.Date(currentDate.getTime());
            Setconnection();
            String select="select count(*) from membre where date_fin= '"+date_now+"' ";
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            while(rs.next())
	            {
	            	
	            	lblfin.setText(rs.getString(1));
	            	
	            }
                    con.close();
        }catch(Exception ex)
        {
            
        }
    }
}
