/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool.Pattern;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/**
 *
 * @author assun
 */
public class GoFrontCommand implements Command {
    
    private Pane pane; 
    private Shape shape; 
    private int index;   // Z-order of the shape 

    public GoFrontCommand(Pane pane, Shape shape) {
        this.pane = pane;
        this.shape = shape;
        this.index = pane.getChildren().indexOf(shape);  
    }
    
    @Override 
    public void execute(){
        shape.toFront();
    }
    
    @Override
    public void undo() {
        pane.getChildren().remove(shape);  // removes  the shape
        pane.getChildren().add(index, shape);  // Inserts the shape into the previously saved position
    }
}
