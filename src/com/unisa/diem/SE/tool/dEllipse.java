/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/**
 *
 * @author antonio.dangelo.photo
 */
public class dEllipse extends Shapes {
    
    private double xRadius;
    private double yRadius;
    
    private double xCenter;
    private double yCenter;
    
    private Color strokeColor;
    private Color fillColor;

    Ellipse ellipse;
   
   
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

    
    public dEllipse(Point2D startPos, Point2D endPos, Color strokeColor, Color fillColor){
        super(startPos, endPos, strokeColor, fillColor);
        setxRadius(Math.abs(startPos.getX() - endPos.getX()));
        setyRadius(Math.abs(startPos.getY() - endPos.getY()));
        
        setxCenter(startPos.getX());
        setyCenter(startPos.getY());
        
        
        setStrokeColor(strokeColor);
        setFillColor(fillColor);
        
    }
    
    @Override
    public void draw(Pane pane){
        ellipse=new Ellipse();
        ellipse.setCenterX(this.xCenter);
        ellipse.setCenterY(this.yCenter);
        ellipse.setRadiusX(this.xRadius);
        ellipse.setRadiusY(this.yRadius);
        
        ellipse.setStroke(getStrokeColor());
        ellipse.setFill(getFillColor());
        
        pane.getChildren().add(ellipse);
    }
    
    
    @Override
    public void resize(Pane pane,Point2D start, Point2D end) {
        
    }
    
    /*
    public Shape draw(Pane pane, Point2D startPos, Point2D endPos, Color strokeColor, Color fillColor){
        ellipse=new Ellipse();
        setxRadius(Math.abs(startPos.getX() - endPos.getX()));
        setyRadius(Math.abs(startPos.getY() - endPos.getY()));
        
        setxCenter(startPos.getX());
        setyCenter(startPos.getY());
        
        
        setStrokeColor(strokeColor);
        setFillColor(fillColor);
        
        ellipse.setCenterX(this.xCenter);
        ellipse.setCenterY(this.yCenter);
        ellipse.setRadiusX(this.xRadius);
        ellipse.setRadiusY(this.yRadius);
        
        ellipse.setStroke(getStrokeColor());
        ellipse.setFill(getFillColor());
        
        //pane.getChildren().add(ellipse);
        return ellipse;
    }*/
    @Override
    public String toString() {
        return "Ellipse,"+this.getPosition().getX()+","+this.getPosition().getY() + "," + this.getEndPosition().getX() + "," + this.getEndPosition().getY() + ","+ this.getColor() + ","+ this.getFillColor() +"\n";
    }

}
