/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool;

import com.unisa.diem.SE.tool.Pattern.Command;
import com.unisa.diem.SE.tool.Pattern.CopySingleton;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.Shape;

/**
 *
 * @author giova
 */
public class Paste implements Command{
    private CopySingleton copySing=CopySingleton.getInstance();
    private Point2D selPosition;
    private Pane pane;
    private Shape temp;

    public Paste(Point2D selpos, Pane p) {
        this.selPosition = selpos;
        this.pane=p;
    }
    
    @Override
    public void execute() {
       Shape Copy;
       double a=0;
                for(Shape s: copySing.getList()){
                    s.getStrokeDashArray().clear();
                    Copy = s;
                    temp=Shape.union(s, Copy);
                      
                    
                    temp.setFill(s.getFill());
                    temp.setStroke(s.getStroke());
                    temp.relocate(selPosition.getX()+a, selPosition.getY()+a);
                    a=a+20; 
                    pane.getChildren().add(temp);
                    
                    
                }
                
               
                
                
                
                
                
    }

    @Override
    public void undo() {
        pane.getChildren().remove(temp);
    }
    
}
