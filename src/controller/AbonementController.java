/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.*;
import datta.Abonement;
import datta.Affectation;
import datta.User;
import dbase.Tools;
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
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Alilo
 */
public class AbonementController implements Initializable{
    
      @FXML
    private TextField dureabon;
       @FXML
    private TextField designation_prog;
       static String designation_abonement;
       static double prixx_abonement;
      
      

    @FXML
    private TextField designabon;

    @FXML
    private TextField nouvprix;

    @FXML
    private ComboBox<String> progabon;

    @FXML
    private TextField idabon;

    @FXML
    private TextField ancienprix;

   @FXML
    private TableView<Abonement> list_abonement;

    @FXML
    private TableColumn<Abonement, String> designation_abon;

    @FXML
    private TableColumn<Abonement, String> programme_abon;

    @FXML
    private TableColumn<Abonement, Integer> dure_abonemnt;
 @FXML
    private TableColumn<Abonement, Integer> idabonm;
    @FXML
    private TableColumn<Abonement, Double> prix_abonement;

    
    @FXML
    private JFXButton btnsupp;

    @FXML
    private JFXButton btnmodif;

    @FXML
    private JFXTextField txt_recherch;

    @FXML
    private JFXButton btnmodif1;
     ObservableList<String>listprogram=FXCollections.observableArrayList();
      ObservableList<Abonement>data=FXCollections.observableArrayList();
     Connection con;
    Statement stmt;
    ResultSet rs;
    String url,s;
    PreparedStatement pst;
    Abonement a=new  Abonement();
    Affectation f=new Affectation();
            
    static String test;
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
    public void initialize(URL location, ResourceBundle resources) {
        getAllrow();
        idabon.setText(dbase.Go.GetAutoNumber("abonement", "id_abonement"));
        get_list_program();
        progabon.setItems(listprogram);
        btnsupp.setDisable(true);
        btnmodif.setDisable(true);
        setinformation();
        handleselection();
        
    }
    
    
    
    
    
    void get_list_program()
    {
        try
        {
            Setconnection();
            String select="select id_programme from programme";
             stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            while(rs.next())
	            {
	            	listprogram.add(rs.getString(1));
                                
	            }
                    
                    con.close();
                            
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    void setinformation()
	{
		list_abonement.setOnMouseClicked(new EventHandler<MouseEvent>() {

			
			@Override
			public void handle(MouseEvent arg0) {
				a=list_abonement.getItems().get(list_abonement.getSelectionModel().getSelectedIndex());
				
				test=String.valueOf(a.getIdabon());
                                designation_abonement=a.getDesignation();
                                prixx_abonement=a.getNouveau_prix();
				btnmodif.setDisable(false);
				btnsupp.setDisable(false);
			}
		});
	}
    @FXML
    void openmodif(ActionEvent e) throws IOException
    {
        new Tools().openForm("Modifier_Abonement.fxml");
    }
    
    void setvalue()
    {
        a.setIdabon(Integer.parseInt(idabon.getText()));
      a.setProgrammeabon(Integer.parseInt(progabon.getSelectionModel().getSelectedItem()));
      a.setDesignation(designabon.getText());
      a.setDure(Integer.parseInt(dureabon.getText()));
      a.setNouveau_prix(Double.parseDouble(nouvprix.getText()));
      a.setAncien_prix(Double.parseDouble(ancienprix.getText()));
        
    }
    void clear()
    {
        idabon.setText(dbase.Go.GetAutoNumber("abonement", "id_abonement"));
        designabon.setText("");
        dureabon.setText("");
       nouvprix.setText("");
       ancienprix.setText("");
       designation_prog.setText("");
    }
    @FXML
    void ajouter(ActionEvent e)
    {
        setvalue();
        a.add();
        clear();
        getAllrow();
                
    }
    
    
    void getAllrow()
    {
         try
		{
                    data.clear();
			Setconnection();
			 String select="select id_abonement,designation,nom_programme,dure_abonement,prix_abonement from abonement_datta ";
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            while(rs.next())
	            {
	            	data.add(new Abonement(Integer.parseInt(rs.getString(1)),rs.getString(2), rs.getString(3), Integer.parseInt(rs.getString(4)), Double.parseDouble(rs.getString(5))));
	            }
                    con.close();
			
		}catch(SQLException ex)
		{
			new Tools().alertmsgbox(ex.getMessage());
		}
              idabonm.setCellValueFactory(new PropertyValueFactory<>("idabon"));
		designation_abon.setCellValueFactory(new PropertyValueFactory<>("designation"));
		programme_abon.setCellValueFactory(new PropertyValueFactory<>("prg"));
		dure_abonemnt.setCellValueFactory(new PropertyValueFactory<>("dure"));
		prix_abonement.setCellValueFactory(new PropertyValueFactory<>("nouveau_prix"));
		
		list_abonement.setItems(data);
    }
    @FXML
    void search()
    {
          try
		{
                    data.clear();
			Setconnection();
			 String select="select * from abonement_datta where designation like '%"+txt_recherch.getText()+"%'"
		 		+ " or nom_programme  like '%"+txt_recherch.getText()+"%'";
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            while(rs.next())
	            {
	            	data.add(new Abonement(Integer.parseInt(rs.getString(1)),rs.getString(2), rs.getString(3), Integer.parseInt(rs.getString(4)), Double.parseDouble(rs.getString(5))));
	            }
			con.close();
		}catch(SQLException ex)
		{
			new Tools().alertmsgbox(ex.getMessage());
		}
                  idabonm.setCellValueFactory(new PropertyValueFactory<>("idabon"));
		designation_abon.setCellValueFactory(new PropertyValueFactory<>("designation"));
		programme_abon.setCellValueFactory(new PropertyValueFactory<>("prg"));
		dure_abonemnt.setCellValueFactory(new PropertyValueFactory<>("dure"));
		prix_abonement.setCellValueFactory(new PropertyValueFactory<>("nouveau_prix"));
		
		list_abonement.setItems(data);
    }
     @FXML
    void delete(ActionEvent e)
    {
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("حذف اشتراك ");
	        alert.setContentText("Voulez-vous vraiment supprimer cette Abonement" + " ?");
	        Optional<ButtonType> answer = alert.showAndWait();
	        if (answer.get() == ButtonType.OK) {
                      a.setIdabon(Integer.parseInt(test));
                      f.setAbonement(Integer.parseInt(test));
                      f.delete();
                     
	        	a.delete();
	        	txt_recherch.setText("");
                        idabon.setText(dbase.Go.GetAutoNumber("abonement", "id_abonement"));
	        	
	        }else {
	           
	            txt_recherch.setText("");
	        }
	        data.clear();
			getAllrow();
			btnmodif.setDisable(true);
			btnsupp.setDisable(true);
    }
    void handleselection()
    {
        progabon.setOnAction((event) -> {
            try{
                 String val=progabon.getSelectionModel().getSelectedItem().toString();
                 int id=Integer.parseInt(val);
                 System.out.println(val);
                 Setconnection();
                  String select="select nom_programme from programme where id_programme="+id;
                 stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            while(rs.next())
	            {
	            	designation_prog.setText(rs.getString(1));
                        designabon.setText(rs.getString(1));
                       
                                
	            }
                
            }catch(Exception e)
            {
                e.printStackTrace();
            }
           
            
        });
    }
    @FXML
    void calc_prix(ActionEvent e)
    {
         try{
             double prix=0;
             int dure=1;
                 String val=progabon.getSelectionModel().getSelectedItem().toString();
                 int id=Integer.parseInt(val);
                 System.out.println(val);
                 Setconnection();
                  String select="select prix_programme from programme where id_programme="+id;
                 stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            while(rs.next())
	            {
	            	prix=Double.parseDouble(rs.getString(1));
                       
                                
	            }
                    dure=Integer.parseInt(dureabon.getText());
                    prix =prix *dure;
                    ancienprix.setText(String.valueOf(prix));
                    
                
            }
         catch(Exception ex)
         {
             ex.printStackTrace();
         }
        
    }

}
