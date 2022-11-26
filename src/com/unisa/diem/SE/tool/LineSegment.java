/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import com.unisa.diem.SE.tool.Shapes;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
/**
 *
 * @author GBCuomo
 */
public class LineSegment extends Shapes{
    
    

    public LineSegment(Point2D start, Point2D end,Color strockColor, Color color){
        super(start,end,strockColor,color);

    }

    public LineSegment() {
        
    }
    
    /*@Override
    public void setFillColor(Color color){
        setColor(color);
    }
    
    @Override
    public void setTopLeft(Point2D x){
        Point2D temp = x.subtract(this.getPosition());
        this.setPosition(x);
        this.setEndPosition(this.getEndPosition().add(temp));
        super.setTopLeft(x);
    }
    */
    @Override
    public void draw(Pane pane) {
        Line line = new Line();
        line.setStartX(this.getPosition().getX());
        line.setStartY(this.getPosition().getY());
        line.setEndX(this.getEndPosition().getX());
        line.setEndY(this.getEndPosition().getY());
        pane.getChildren().add(line);
    }

    
}


