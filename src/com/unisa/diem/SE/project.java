package com.unisa.diem.SE;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author antonio.dangelo.photo
 */
public class project extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setHeight(800);
        stage.setWidth(1000);
        stage.setMaxHeight(1080);
        stage.setMaxWidth(1920);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        System.out.print("ciao");
    }
    
}
