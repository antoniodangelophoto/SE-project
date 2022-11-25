/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.layout.Pane;

/**
 *
 * @author antonio.dangelo.photo
 */
public class dEllipse extends Shapes {
    
    private double hRadius;
    private double vRadius;
    
    public dEllipse(){
        
    }

    public double gethRadius() {
        return hRadius;
    }

    public void sethRadius(double hRadius) {
        this.hRadius = hRadius;
    }

    public double getvRadius() {
        return vRadius;
    }

    public void setvRadius(double vRadius) {
        this.vRadius = vRadius;
    }
    
    public dEllipse(Point2D startPos, Point2D endPos, Color strockColor){
        super(startPos, endPos, strockColor);
        hRadius = Math.abs(startPos.getX() - endPos.getX())/2;
        vRadius = Math.abs(startPos.getY() - endPos.getY())/2;
    }
    @Override
    public void draw(Pane Pane){
        Ellipse ellipse=new Ellipse();
        
        
        GraphicsContext gc = Pane.getGraphicsContext2D();
        gc.setStroke(super.getColor());
        gc.strokeOval(super.getTopLeft().getX(), super.getTopLeft().getY(), hRadius*2, vRadius*2);
        gc.setFill(super.getFillColor());
        gc.fillOval(super.getTopLeft().getX(), super.getTopLeft().getY(), hRadius*2, vRadius*2);
    }
}
