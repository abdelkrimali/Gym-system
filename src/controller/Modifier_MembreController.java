/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import datta.Membre;
import dbase.Tools;
import dbase.Utility;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Alilo
 */
public class Modifier_MembreController implements Initializable {
    @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_prenom;

    @FXML
    private TextField txt_age;

    @FXML
    private TextField txt_tel;

    @FXML
    private TextField txt_image;

    @FXML
    private ImageView image;
     Image img;
     Membre m=new Membre();

    /**
     * Initializes the controller class.
     */
     @FXML
    public void btnbrowseaction(ActionEvent event)
	{	
		new Tools().btnbrowseaction(event, txt_image, img, image);
	}
    
    void setvalue() throws Utility
    {
        m.setId(Integer.parseInt(MembreController.id));
        m.setNom(txt_nom.getText());
        m.setPrenom(txt_prenom.getText());
        m.setTelephone(txt_tel.getText());
        m.setAge(Integer.parseInt(txt_age.getText()));
        m.setImage(txt_image.getText());
    }
    @FXML
    void save_modif(ActionEvent e) throws Utility
    {
        setvalue();
        m.update();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_nom.setText(MembreController.nom);
        txt_prenom.setText(MembreController.prenom);
        txt_age.setText(MembreController.age);
        txt_tel.setText(MembreController.tel);
    }  
    
    
}
