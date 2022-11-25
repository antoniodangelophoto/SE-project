/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import com.unisa.diem.SE.tool.Shapes;

public class Line extends Shapes{
    
    private double length;
    
    public Line(Point2D start, Point2D end, Color color){
        super(start,end,color);
        length = start.distance(end);
    }

    public Line() {
        
    }
    
    /*@Override
    public void setFillColor(Color color){
        setColor(color);
    }
    */
    @Override
    public void setTopLeft(Point2D x){
        Point2D temp = x.subtract(this.getPosition());
        this.setPosition(x);
        this.setEndPosition(this.getEndPosition().add(temp));
        super.setTopLeft(x);
    }


    
}
