/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbase.Tools;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Alilo
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField txtuser;
    @FXML
    private PasswordField txtpassword;
       @FXML
    private Label wrongpass;
       
     static String pass="";
     static String user="";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    @FXML
    public void close(MouseEvent event)
	{
		//((Node)event.getSource()).getScene().getWindow().hide();
		Platform.exit();
		//System.exit0);
	}

    
    
    @FXML
    public void loginn(ActionEvent evt) throws IOException
{
    Tools t=new Tools();
           
        String user=txtuser.getText();
        String password=txtpassword.getText();
        System.out.println(user + password);
       
        boolean islog=dbase.Go.Chekuserandpass(user, password);
        if(islog)
        {
                        this.pass=password;
                        this.user=user;
                        ((Node)evt.getSource()).getScene().getWindow().hide();
			t.openForm("Dash.fxml");
            
        }else
        {
            new Tools().msgbox("Invalid User and Pass");
                    
            txtuser.setText("");
            txtpassword.setText("");
            txtuser.requestFocus();
        }
        
        
    }
    
}
