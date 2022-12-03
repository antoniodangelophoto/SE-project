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

    public void moveShape(Node node){

        node.setOnMousePressed(mouseEvent ->{
            pointX = mouseEvent.getX();
            pointY = mouseEvent.getY();
        });

        node.setOnMouseDragged(mouseEvent ->{
            node.setLayoutX((mouseEvent.getSceneX() - pointX));
            node.setLayoutY((mouseEvent.getSceneY() - pointY));
            
            //node.setLayoutX(mouseEvent.getSceneX());
            //node.setLayoutY(mouseEvent.getSceneY());
            
        });
    }

}
