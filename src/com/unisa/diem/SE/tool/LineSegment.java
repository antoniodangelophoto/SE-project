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
    
    private Color color;

    public Color getColor() {
        return this.color;
    }

    public void setStrokeColor(Color color) {
        this.color = color;
    }


    public LineSegment(Point2D start, Point2D end,Color color){
        super(start,end,color);
        setStrokeColor(color);
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
        line.setStrokeWidth(1);
        line.setStroke(getColor());
        pane.getChildren().add(line);
    }

    @Override
    public String toString() {
        return "Line,"+this.getPosition().getX()+","+this.getPosition().getY() + "," + this.getEndPosition().getX() + "," + this.getEndPosition().getY() + ","+ color + "\n";
    }

    
}


