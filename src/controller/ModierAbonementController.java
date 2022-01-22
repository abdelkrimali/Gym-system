/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import datta.Abonement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 *
 * @author Alilo
 */
public class ModierAbonementController implements Initializable {
       @FXML
    private TextField txtprix;

    @FXML
    private TextField txtdesignation;

    @FXML
    private TextField txtid;
    Abonement a=new Abonement();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtid.setText(AbonementController.test);
        txtdesignation.setText(AbonementController.designation_abonement);
        txtprix.setText(String.valueOf(AbonementController.prixx_abonement));
    }
    
    void setvalue()
    {
        a.setIdabon(Integer.parseInt(txtid.getText()));
        a.setDesignation(txtdesignation.getText());
        a.setNouveau_prix(Double.parseDouble(txtprix.getText()));
        
    }
    @FXML
    void update()
    {
        setvalue();
        a.update();
    }
    
}
