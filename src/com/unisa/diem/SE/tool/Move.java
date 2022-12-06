/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool;
import com.unisa.diem.SE.tool.Pattern.MoveSingleton;
import javafx.scene.Node;
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
    public void moveShape(Node node, Double width){
        MoveSingleton moveProp=MoveSingleton.getInstance();
        
        node.setOnMousePressed(mouseEvent ->{
            if(mouseEvent.getButton()==MouseButton.PRIMARY & moveProp.getMoveProp()){
                anchorX = mouseEvent.getSceneX();
                anchorY = mouseEvent.getSceneY();
                pointX = mouseEvent.getX() + width;
                pointY = mouseEvent.getY() ; //111
            }
        });
        node.setOnMouseReleased(mouseEvent ->{
            if(mouseEvent.getButton()==MouseButton.PRIMARY & moveProp.getMoveProp()){
                node.setLayoutX((mouseEvent.getSceneX() - pointX));
                node.setLayoutY((mouseEvent.getSceneY() - pointY));
                node.setTranslateX(0);
                node.setTranslateY(0);
            }
            
        });
        node.setOnMouseDragged(mouseEvent ->{
            if(mouseEvent.getButton()==MouseButton.PRIMARY & moveProp.getMoveProp()){
                node.setTranslateX((mouseEvent.getSceneX() - anchorX));
                node.setTranslateY((mouseEvent.getSceneY() - anchorY));
            }
        });
        
        
    }
    
    

}
