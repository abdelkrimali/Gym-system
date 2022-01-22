package dbase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.FileChooser.ExtensionFilter;

public class Tools {

	public void openForm(String FormName) throws IOException
	{
		
		Stage stage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/forms/"+FormName));
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		//stage.initStyle(StageStyle.TRANSPARENT);
		stage.setScene(scene);
		stage.show();
	}
	public void msgbox(String message)
	{
		Alert aler=new Alert(Alert.AlertType.INFORMATION);
		aler.setHeaderText(null);
		aler.setContentText(message);
		aler.showAndWait();
	}
	public void alertmsgbox(String message)
	{
		Alert aler=new Alert(Alert.AlertType.WARNING);
		aler.setHeaderText(null);
		aler.setContentText(message);
		aler.showAndWait();
	}
	
	
	 public void btnbrowseaction(ActionEvent event,TextField txtimage,Image img,ImageView image)
		{	
			FileChooser fc=new FileChooser();
			fc.getExtensionFilters().addAll(new ExtensionFilter("Selctionner une image ","*.png","*.jpg","*.gif"));
			File selectedfile=fc.showOpenDialog(null);
			if(selectedfile !=null)
			{
				System.out.println(selectedfile.toURI().toString());
				txtimage.setText(selectedfile.toURI().toString());	
				img=new Image(selectedfile.toURI().toString());
				image.setImage(img);
			}else
			{
				System.out.println("Invalide Fichier");
			}
		}
	 public void export_topdf()
	 {
//		com.itextpdf.text.Document doc=new com.itextpdf.text.Document();
//		try{
//		PdfWriter.getInstance(doc,new FileOutputStream("C://Users//COMPUTER//Desktop//ali.pdf") );
//		doc.open();
//		doc.add(new Paragraph())
//		}catch(Exception ex)
//		{
//			ex.printStackTrace();
//		}
		 
	 }
 
	
}
