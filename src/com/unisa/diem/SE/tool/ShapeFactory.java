/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author GBCuomo
 */
public class ShapeFactory {
    
    public ShapeFactory(){
        
    }
    /*
    public Shapes createShape(String type, Point2D start, Point2D end, Color strockColor, Color color){
        Shapes temp=null;
        switch(type){
            case"Ellipse": temp = new dEllipse(start,end,strockColor,color);break;
            case"Rectangle": temp = new Rect(start,end,strockColor,color);break;
            case"Line": temp = new LineSegment(start,end,strockColor,color);break;

        }
        return temp;
    }
    public Shapes createShape(String type){
        Shapes temp=null;
        switch(type){
            case"Ellipse": temp = new dEllipse();break;
            case"Rectangle": temp = new Rect();break;
            case"Line": temp = new LineSegment();break;
        }
        return temp;
    }
*/

    
    public Shape createShape(String type){
        Shape temp=null;
        switch(type){

            case"Ellipse": temp = new Ellipse();break;
            case"Rectangle": temp = new Rectangle();break;
            case"Line": temp = new Line();break;

        }
        return temp;
    }
}
