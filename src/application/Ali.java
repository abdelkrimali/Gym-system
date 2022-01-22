/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Alilo
 */
public class Ali extends Application {
    
    @Override
	public void start(Stage primaryStage) {
		try {
			//BorderPane root = new BorderPane();
			Parent root=FXMLLoader.load(getClass().getResource("/forms/Login_1.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			//primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
	public static void main(String[] args) {
		launch(args);
		
	}
}
