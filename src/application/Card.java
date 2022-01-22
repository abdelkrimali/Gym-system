/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import datta.Membre;
import dbase.Tools;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Alilo
 */

public class Card {
     
   
   
    

    public static void main(String[]args) throws Exception
    {
         try{
            JFXPanel p=new JFXPanel();
            String uri=new File("alertj.mp3").toURI().toString();
            new MediaPlayer(new Media(uri)).play();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
}
