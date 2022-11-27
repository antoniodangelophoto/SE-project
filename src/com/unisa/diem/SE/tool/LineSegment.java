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
/**
 *
 * @author GBCuomo
 */
public class LineSegment extends Shapes{
    
    private Color strokeColor;
    private Color fillColor;

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
    
    
    


    public LineSegment(Point2D start, Point2D end,Color strockColor, Color fillColor){
        super(start,end,strockColor,fillColor);
        setFillColor(fillColor);
        setStrokeColor(strockColor);
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
        
        line.setStroke(getStrokeColor());
        line.setFill(getFillColor());
        
        pane.getChildren().add(line);
    }

    
}


