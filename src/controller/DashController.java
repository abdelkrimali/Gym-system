/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.dateTime;
import dbase.Tools;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Alilo
 */
public class DashController implements Initializable {
    @FXML
    AnchorPane home;
    @FXML
     Label user;
    @FXML
     Label mtime;
    @FXML
     Label mdate;
    
    
     Connection con;
	    Statement stmt;
	    ResultSet rs;
	    String url,s;
	    private	 void SetUrl(){
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
            void afficher_detaille() throws SQLException 
		  {
			  
					String u=LoginController.user;
					String p=LoginController.pass;
                                        System.out.println(u+p);
		            Setconnection();
		            String select="select nom,prenom from utilisateur where nom_utilisateur ='"+u+"' and mot_pass ='"+p+"'";
		            stmt=con.createStatement();
		            rs=stmt.executeQuery(select);
		            if(rs.next())
		            {
		            	
		            	user.setText((rs.getString(1)+" "+rs.getString(2)).toUpperCase());
                              
		            }
		  }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane pane;
        try
        {
            pane= FXMLLoader.load(getClass().getResource("/forms/Home.fxml"));
            home.getChildren().setAll(pane);
            afficher_detaille();
            affiche_horloge();
            
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void openuser(ActionEvent evt) throws Exception
    {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/forms/User.fxml"));
		home.getChildren().setAll(pane);
    }
     @FXML
    public void openhome(ActionEvent evt) throws Exception
    {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/forms/Home.fxml"));
		home.getChildren().setAll(pane);
    }
    
      @FXML
    public void openmembre(ActionEvent evt) throws Exception
    {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/forms/Lecteur.fxml"));
		home.getChildren().setAll(pane);
    }
    @FXML
    public void openprogramme(ActionEvent evt) throws Exception
    {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/forms/Programme.fxml"));
		home.getChildren().setAll(pane);
    }
    
     @FXML
    public void openprofile(ActionEvent evt) throws Exception
    {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/forms/Profile.fxml"));
		home.getChildren().setAll(pane);
    }
     @FXML
    public void openabonement(ActionEvent evt) throws Exception
    {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/forms/Abonement.fxml"));
		home.getChildren().setAll(pane);
    }
    
    void affiche_horloge()
    {
       
        Thread clock;
          clock = new Thread(){
              
          public void run(){
          for(;;){
                 //Set Current Date
                Calendar cal=new GregorianCalendar();
                int month=cal.get(Calendar.MONTH);
                int year=cal.get(Calendar.YEAR);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                if(month<10)
            {
                mdate.setText(day+"/0"+(month+1)+"/"+year);
            }
                else
            {
                 mdate.setText(day+"/"+(month+1)+"/"+year);
            }
                //Set Current Time
                int hour=cal.get(Calendar.HOUR);
                int min=cal.get(Calendar.MINUTE);
                int sec=cal.get(Calendar.SECOND);
                int am_pm=cal.get(Calendar.AM_PM);
                String pa;
                if(am_pm==1){
                    pa="PM";
                }
                else
            {
                pa="AM";
            }
                
         // System.out.println(hour+":"+min+":"+sec+" "+pa);
        
                try{
                    sleep(1000);
                }
                catch(InterruptedException ex){
                  //  Logger.getLogger(frmmain.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
                
            }
            
        }
        
    };
       clock.start();
    }
    
    
    
 }
   
    

