/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import com.unisa.diem.SE.tool.*;
import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
//import javafx.scene.shape.Shape;

/**
 *
 * @author GBCuomo
 */
public class FileManager {
    private ArrayList<Shapes> myArray;
    public FileManager(){
        this.myArray = new ArrayList<>();
    }

    /*
    public void saveFile(String file, ArrayList<Shapes> ShapeList) {
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)))){
            for(Shapes s : ShapeList){
                out.print(s.toString());
            }
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }

    public void loadFile(String file, Pane pane){
        pane.getChildren().clear();
        myArray.clear();
        try(Scanner in = new Scanner(new BufferedInputStream(new FileInputStream(file)))){
            in.useLocale(Locale.US);
            in.useDelimiter("\n");
            while(in.hasNext()){
                
                Scanner stringScanner = new Scanner(new BufferedReader(new StringReader(in.nextLine())));
                //String shapeString = in.next();
                //System.out.println(shapeString);
                stringScanner.useLocale(Locale.US);
                stringScanner.useDelimiter(",");
                
                Shapes s;
                

                 
                double startX = stringScanner.nextDouble();
                System.out.println(startX);
                double startY = stringScanner.nextDouble();
                System.out.println(startY);
                Point2D start = new Point2D(startX, startY);
                double endX = stringScanner.nextDouble();
                double endY = stringScanner.nextDouble();
                Point2D end = new Point2D(endX, endY);
                //Color stroke = Color.decode(stringScanner.next());
                //Color fill = Color.decode(stringScanner.next());
                String strockColor = stringScanner.next();
                String fillColor = stringScanner.next();
                String s1 = strockColor.substring(2,3);
                String s2 = strockColor.substring()
                if(stringScanner.next().equals("Line"))
                    s = new LineSegment(start, end, Color.rgb(0, 0, 0), fillColor );
                else if(stringScanner.next().equals("Rectangle"))
                    s = new Rect();
                else 
                    s = new dEllipse();

                s.setPosition(start);
                s.setEndPosition(end);
                
                s.setFillColor(Color.BLACK);
                //s.setColor(stroke);
                
                myArray.add(s);
                //pane.getChildren().add(s);
                s.draw(pane);
                //Shape s = new ShapeFactory().createShape(shapeString);
                //pane.getChildren().add(s);

            }
            //pane.getChildren().add(s)
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    */
    
    
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