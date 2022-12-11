/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.control.Alert;
import javafx.scene.shape.Polygon;

/**
 *
 * @author GBCuomo
 */
public class FileManager {
    private ArrayList<Shapes> myArray;
    public FileManager(){
        this.myArray = new ArrayList<>();
    }

    
    public void saveFile(String file, Pane pane) throws IOException {
        try (XMLEncoder encoder = new XMLEncoder(
        new BufferedOutputStream(
            Files.newOutputStream(Paths.get(file))))) {

        encoder.setExceptionListener(e -> {
            throw new RuntimeException(e);
        });
        
        boolean containsPolygon = false;
        for (Node node : pane.getChildren()) {
            if (node instanceof Polygon) {
                containsPolygon = true;
                break;
            }
        }
        
        //if it contains a polygon, launch an alert
        if (containsPolygon) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("The file contains a polygon, it will not be saved");
            a.show();
            return;
        }
        
        encoder.setPersistenceDelegate(Color.class, new DefaultPersistenceDelegate(new String[]{"red", "green", "blue", "opacity"}));
        encoder.writeObject(pane.getChildren().toArray(new Node[0]));
        }
                
    }
    
    
    public void loadFile(String file, Pane pane) throws IOException {
        try (XMLDecoder decoder = new XMLDecoder(
        new BufferedInputStream(
            Files.newInputStream(Paths.get(file))))) {

        decoder.setExceptionListener(e -> {
            throw new RuntimeException(e);
        });

        pane.getChildren().setAll((Node[]) decoder.readObject());
    }
    }
    
    
}
