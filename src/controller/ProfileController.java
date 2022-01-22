/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import datta.User;
import dbase.Utility;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 *
 * @author Alilo
 */
public class ProfileController implements Initializable {
    @FXML
    private Label nom_et_prenom;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_prenom;

    @FXML
    private PasswordField currentpass;

    @FXML
    private PasswordField newpass;

    @FXML
    private PasswordField confirmpass;
    
    static String nom;
	static String prenom;
	
	String user;
	String pass;
	Connection con;
    Statement stmt;
    ResultSet rs;
    String url,s;
    User userr=new User();
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
    
     void afficher_detaille()
	  {
		  try{
                      
                  
			
              String u=LoginController.user;
				String p=LoginController.pass;
	            Setconnection();
	            String select="select * from utilisateur where nom_utilisateur ='"+u+"' and mot_pass ='"+p+"'";
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            if(rs.next())
	            {
	            	txt_email.setText(rs.getString(6));
	            	txt_nom.setText(rs.getString(1));
	            	txt_prenom.setText(rs.getString(2));
	            	nom_et_prenom.setText((rs.getString(1)+" "+rs.getString(2)).toUpperCase());
                      }
                  }catch(Exception ex)
                  {
                      ex.printStackTrace();
                  }
                   
                            
                            
	  }
     
       void setval() throws Utility
    {
    	userr.setEmail(txt_email.getText());
    	//bib.setPass(LoginController.pass);
    	userr.setUsername(LoginController.user);
        userr.setNom(txt_nom.getText());
        userr.setPrenom(txt_prenom.getText());
        if(currentpass.getText().equals(LoginController.pass))
    	{
    		if(confirmpass.getText().equals(newpass.getText()))
    				{
    					userr.setPassword(confirmpass.getText());
    				}
    		else{
    			throw new Utility("Les deux champs ne sont pas les meme ");
    		}
    	}
    	else
    	{
    		throw new Utility("Le Mot passe est incorrect! ");
    	}
    	
    }
        @FXML 
    void update_profile(ActionEvent event) throws Utility
    {
    	setval();
    	userr.update();
    	confirmpass.setText("");
    	newpass.setText("");
    	currentpass.setText("");
    	
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
  
    }
    
    
    
    
    
    
    
    
    
    
    
    
    

   
  
    
}
