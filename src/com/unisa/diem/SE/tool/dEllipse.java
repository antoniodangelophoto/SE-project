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
    
    private double xRadius;
    private double yRadius;
    
    private double xCenter;
    private double yCenter;
    

    public double getxCenter() {
        return xCenter;
    }

    public void setxCenter(double xCenter) {
        this.xCenter = xCenter;
    }

    public double getyCenter() {
        return yCenter;
    }

    public void setyCenter(double yCenter) {
        this.yCenter = yCenter;
    }
    
    
    public dEllipse(){
        
    }

    public double getxRadius() {
        return xRadius;
    }

    public void setxRadius(double xRadius) {
        this.xRadius = xRadius;
    }

    public double getyRadius() {
        return yRadius;
    }

    public void setyRadius(double yRadius) {
        this.yRadius = yRadius;
    }
    
    public dEllipse(Point2D startPos, Point2D endPos, Color strockColor, Color fillColor){
        super(startPos, endPos, strockColor, fillColor);
        xRadius = Math.abs(startPos.getX() - endPos.getX())/2;
        yRadius = Math.abs(startPos.getY() - endPos.getY())/2;
        xCenter = Math.abs(endPos.getX()-startPos.getX());
        yCenter = Math.abs(endPos.getY()-startPos.getY());
        
    }
    
    @Override
    public void draw(Pane pane){
        Ellipse ellipse=new Ellipse();
        ellipse.setCenterX(this.xCenter);
        ellipse.setCenterY(this.yCenter);
        ellipse.setRadiusX(this.xRadius);
        ellipse.setRadiusY(this.yRadius);
        ellipse.setStroke(Color.BLACK);
        ellipse.setFill(Color.TRANSPARENT);
        pane.getChildren().add(ellipse);
        
        
       
        //GraphicsContext gc = Pane.getGraphicsContext2D();
        //gc.setStroke(super.getColor());
        //gc.strokeOval(super.getTopLeft().getX(), super.getTopLeft().getY(), hRadius*2, vRadius*2);
        //gc.setFill(super.getFillColor());
        //gc.fillOval(super.getTopLeft().getX(), super.getTopLeft().getY(), hRadius*2, vRadius*2);
    }
}
