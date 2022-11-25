/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;

/**
 *
 * @author antonio.dangelo.photo
 */
public class Ellipse extends Shape {
    
    private double hRadius;
    private double vRadius;
    
    public Ellipse(){
        
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
    
    public Ellipse(Point2D startPos, Point2D endPos, Color strockColor){
        super(startPos, endPos, strockColor);
        hRadius = Math.abs(startPos.getX() - endPos.getX())/2;
        vRadius = Math.abs(startPos.getY() - endPos.getY())/2;
    }
    /*@Override
    public void draw(Pane Pane){
        GraphicsContext gc = Pane.getGraphicsContext2D();
        gc.setStroke(super.getColor());
        gc.strokeOval(super.getTopLeft().getX(), super.getTopLeft().getY(), hRadius*2, vRadius*2);
        gc.setFill(super.getFillColor());
        gc.fillOval(super.getTopLeft().getX(), super.getTopLeft().getY(), hRadius*2, vRadius*2);
    }*/
}
