/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.*;
import datta.Affectation;
import datta.Membre;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author Alilo
 */
public class RenouvlementController implements Initializable {
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
    private TextField id_abonement;
    @FXML
            private Label nom_prenom;

    /**
     * Initializes the controller class.
     */
    
    
    Connection con;
    Statement stmt;
    ResultSet rs;
    String url,s;
    PreparedStatement pst;
    ObservableList<Membre>data=FXCollections.observableArrayList();
    ObservableList<String>listabonemt=FXCollections.observableArrayList();
    
    Affectation f=new Affectation();
    Membre m=new Membre();
    
    
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
                    con.close();
            
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
          
          void setvalue()
          {
              m.setId(Integer.parseInt(MembreController.idmembre));
                f.setId_affectation(Integer.parseInt(dbase.Go.GetAutoNumber("affectation", "id_affectation")));
             f.setMembre((Integer.parseInt(MembreController.idmembre)));
       
          f.setAbonement(Integer.parseInt(id_abonement.getText()));
     
     
     java.sql.Date dated=Date.valueOf(startdate.getValue());
     java.sql.Date datef=Date.valueOf(enddate.getValue());
     f.setDate_debut(dated);
     f.setDate_fin(datef);
     m.setDate_debut(dated);
     m.setDate_fin(datef);
          }
          
          @FXML 
          void renouvler()
          {
              setvalue();
              m.update_date();
              f.add_renouvlement();
          }
          
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.print(MembreController.idmembre);
        nom_prenom.setText(MembreController.nom_et_prenom);
        detaille_abonemnt();
       cbxabonement.setItems(listabonemt);
       handleselection();
    }    
    
}
