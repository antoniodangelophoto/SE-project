/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool;
import com.unisa.diem.SE.tool.Pattern.MoveSingleton;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
/**
 *
 * @author GBCuomo
 */

public class Move {
    private double pointX;
    private double pointY;
    private double anchorX;
    private double anchorY;
    public void moveShape(Node node, Double width, ScrollPane scrollPane){
        MoveSingleton moveProp=MoveSingleton.getInstance();
        
        node.setOnMousePressed(mouseEvent ->{
            if(mouseEvent.getButton()==MouseButton.PRIMARY & moveProp.getMoveProp()){
                anchorX = mouseEvent.getSceneX();
                anchorY = mouseEvent.getSceneY();
                pointX = mouseEvent.getX();
                pointY = mouseEvent.getY();
            }
        });
        
        node.setOnMouseDragged(mouseEvent ->{
            if(mouseEvent.getButton()==MouseButton.PRIMARY & moveProp.getMoveProp()){
                Point2D newLocation = node.localToParent(new Point2D(mouseEvent.getX(), mouseEvent.getY()));
                node.setLayoutX(newLocation.getX()-pointX);
                node.setLayoutY(newLocation.getY()-pointY);
            }
        });
      
        
    }
    
    

}
