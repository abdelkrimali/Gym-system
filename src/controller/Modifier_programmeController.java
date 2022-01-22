/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import datta.Programme;
import dbase.Utility;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author Alilo
 */
public class Modifier_programmeController implements Initializable {
      @FXML
    private TextField txtprix;

    @FXML
    private TextField txtdesignation;

    @FXML
    private TextField txtid;
    
    Programme p=new Programme();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        txtid.setText(ProgrammeController.test);
        txtdesignation.setText(ProgrammeController.des_prog);
        txtprix.setText(ProgrammeController.prix);
    }  
    
    void setvalue() throws Utility
    {
        p.setId(Integer.parseInt(txtid.getText()));
        p.setDesignation(txtdesignation.getText());
        p.setPrix(Double.parseDouble(txtprix.getText()));
                
    }
    
    @FXML
    void enregistrer_modification() throws Utility
    {
        setvalue();
        p.update();
        txtid.setText("");
        txtdesignation.setText("");
        txtprix.setText("");
    }
    
}
