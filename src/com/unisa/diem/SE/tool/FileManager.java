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

/**
 *
 * @author GBCuomo
 */
public class FileManager {
    private ArrayList<Shapes> myArray;
    public FileManager(){
        this.myArray = new ArrayList<>();
    }

    
    public void saveFile(String file, ArrayList<Shapes> ShapeList, Pane pane) throws IOException {
        try (XMLEncoder encoder = new XMLEncoder(
        new BufferedOutputStream(
            Files.newOutputStream(Paths.get(file))))) {

        encoder.setExceptionListener(e -> {
            throw new RuntimeException(e);
        });
        
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
