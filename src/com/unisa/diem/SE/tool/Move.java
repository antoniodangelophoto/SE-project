/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool;
import javafx.scene.Node;
/**
 *
 * @author GBCuomo
 */
public class Move {
    private double pointX;
    private double pointY;
    private double anchorX;
    private double anchorY;
    public void moveShape(Node node, Double height){

        node.setOnMousePressed(mouseEvent ->{
            anchorX = mouseEvent.getSceneX();
            anchorY = mouseEvent.getSceneY();
            pointX = mouseEvent.getX() + 0;
            pointY = mouseEvent.getY() + height + 30; //111
        });
        node.setOnMouseReleased(mouseEvent ->{
            node.setLayoutX((mouseEvent.getSceneX() - pointX));
            node.setLayoutY((mouseEvent.getSceneY() - pointY));
            node.setTranslateX(0);
            node.setTranslateY(0);
        });
        node.setOnMouseDragged(mouseEvent ->{
            node.setTranslateX((mouseEvent.getSceneX() - anchorX));
            node.setTranslateY((mouseEvent.getSceneY() - anchorY));
            
        });
        
    }
    

}
