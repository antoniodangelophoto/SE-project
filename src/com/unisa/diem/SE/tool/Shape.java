/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.*;
/**
 *
 * @author Giovanni Casella
 */
public class Shape {
    private Point2D startPosition;
    private Point2D endPosition;
    private Point2D topLeft;
    private Color color;
    private Color fillColor;
    
    public Shape(){
    }
    
    public Shape(Point2D startPos, Point2D endPos , Color strockColor){
        this.color = strockColor;
        this.startPosition = startPos;
        this.endPosition = endPos;
        this.fillColor = Color.BLUE;
        this.topLeft = calculateTopLeft();
    }

    public Point2D getPosition() {
        return startPosition;
    }

    public Point2D getEndPosition() {
        return endPosition;
    }

    public Color getColor() {
        return color;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setPosition(Point2D startPosition) {
        this.startPosition = startPosition;
    }

    public void setEndPosition(Point2D endPosition) {
        this.endPosition = endPosition;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
    
    public Point2D calculateTopLeft(){
        double x = Math.min(this.getPosition().getX(), this.getEndPosition().getX());
        double y = Math.min(this.getPosition().getY(), this.getEndPosition().getY());
        return new Point2D(x,y);
    }
    
    public Point2D getTopLeft(){
        return topLeft;
    }
    
    public void setTopLeft(Point2D pos){
        this.topLeft = pos;
    }
    
    //public void draw(Canvas canvas) {
        
    //}
    
    
    
}

