/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import datta.Programme;
import dbase.Tools;
import dbase.Utility;
import java.io.IOException;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
   

/**
 *
 * @author Alilo
 */
public class ProgrammeController implements Initializable{
     @FXML
    private JFXButton btnsupp;
     static String des_prog;
     static String prix;

    @FXML
    private JFXButton btnmodif;
     @FXML
    private TextField txtprix;
     @FXML
     private JFXTextField txt_recherch;

    @FXML
    private TextField txtdesignation;

    @FXML
    private TextField txtid;
    
    @FXML
    private TableView<Programme> list_prg;
     
    static TableView<Programme> list_programme;

    @FXML
    private TableColumn<Programme, Integer> num_prg;

    @FXML
    private TableColumn<Programme, String> design_prg;

    @FXML
    private TableColumn<Programme, Double> prix_prg;
    
    Programme p=new Programme();
    Connection con;
    Statement stmt;
    ResultSet rs;
    String url,s;
    PreparedStatement pst;
    static String test;
    
     ObservableList<Programme>data=FXCollections.observableArrayList();
    
    
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
    
    
    void setvalue() throws Utility
    {
        p.setId(Integer.parseInt(txtid.getText()));
        p.setDesignation(txtdesignation.getText());
        p.setPrix(Double.parseDouble(txtprix.getText()));
       
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnmodif.setDisable(true);
        btnsupp.setDisable(true);
       txtid.setText(dbase.Go.GetAutoNumber("programme", "id_programme"));
       getAllrow();
       setinformation();
    }
    
    
    @FXML
    void ajouter(ActionEvent e) throws Utility
    {
        setvalue();
        p.add();
       
        getAllrow();
        txtdesignation.setText("");
        txtprix.setText("");
        txtid.setText(dbase.Go.GetAutoNumber("programme", "id_programme"));
    }
    
    void getAllrow()
    {
        try
		{
                    data.clear();
			Setconnection();
			 String select="select id_programme,nom_programme,prix_programme from programme ";
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            while(rs.next())
	            {
	            	data.add(new Programme(Integer.parseInt(rs.getString(1)), rs.getString(2), Double.parseDouble(rs.getString(3))));
	            }
			con.close();
		}catch(SQLException ex)
		{
			new Tools().alertmsgbox(ex.getMessage());
		}
		num_prg.setCellValueFactory(new PropertyValueFactory<Programme,Integer>("id"));
		design_prg.setCellValueFactory(new PropertyValueFactory<Programme,String>("designation"));
		prix_prg.setCellValueFactory(new PropertyValueFactory<Programme,Double>("prix"));
		
		
		list_prg.setItems(data);
    }
    
    void setinformation()
	{
		list_prg.setOnMouseClicked(new EventHandler<MouseEvent>() {

			
			@Override
			public void handle(MouseEvent arg0) {
				p=list_prg.getItems().get(list_prg.getSelectionModel().getSelectedIndex());
				
				test=String.valueOf(p.getId());
                                des_prog=p.getDesignation();
                                prix=String.valueOf(p.getPrix());
				btnmodif.setDisable(false);
				btnsupp.setDisable(false);
			}
		});
	}
    @FXML
    void openmodif(ActionEvent e) throws IOException
    {
        new Tools().openForm("Modifier_programme.fxml");
    }
    @FXML
    void delete(ActionEvent e)
    {
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("حذف برنامج ");
	        alert.setContentText("Voulez-vous vraiment supprimer cette programme" + " ?");
	        Optional<ButtonType> answer = alert.showAndWait();
	        if (answer.get() == ButtonType.OK) {
                      p.setId(Integer.parseInt(test));
	        	p.delete();
	        	txt_recherch.setText("");
                        txtid.setText(dbase.Go.GetAutoNumber("programme", "id_programme"));
	        	
	        }else {
	           
	            txt_recherch.setText("");
	        }
	        
			getAllrow();
			btnmodif.setDisable(true);
			btnsupp.setDisable(true);
	        }
    
    
    @FXML
	void search(KeyEvent event)
	{
		try
		{
			data.clear();
			Setconnection();
			 String select="select id_programme,nom_programme,prix_programme from programme where nom_programme like '%"+txt_recherch.getText()+"%'"
		 		+ " or prix_programme  like '%"+txt_recherch.getText()+"%'"; 
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            while(rs.next())
	            {
	            	data.add(new Programme(Integer.parseInt(rs.getString(1)), rs.getString(2), Double.parseDouble(rs.getString(3))));
	            }
                    con.close();
		}catch(SQLException ex)
		{
			new Tools().alertmsgbox(ex.getMessage());
		}
		num_prg.setCellValueFactory(new PropertyValueFactory<Programme,Integer>("id"));
		design_prg.setCellValueFactory(new PropertyValueFactory<Programme,String>("designation"));
		prix_prg.setCellValueFactory(new PropertyValueFactory<Programme,Double>("prix"));
		
		
		list_prg.setItems(data);
	}
       
    }

    
    
