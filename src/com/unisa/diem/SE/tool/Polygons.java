/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
/**
 *
 * @author giova
 */
public class Polygons extends Shapes{
    ArrayList<Circle> Points=new ArrayList<Circle>();
    ArrayList<Point2D> Points2D= new ArrayList<Point2D>();
    public Polygons(){
        
    }
    public Polygons(Point2D startPos, Point2D endPos, Color strockColor, Color fillColor, ArrayList<Circle> p){
        super(startPos, endPos, strockColor, fillColor);
        Points.addAll(p);
    }

    public ArrayList<Circle> getPoints() {
        return Points;
    }

    public void setPoints(ArrayList<Circle> Points) {
        this.Points = Points;
    }
    public void getPointsPos(){
        for(Circle c: Points)
           Points2D.add(new Point2D(c.getCenterX(),c.getCenterY()));
       
    }
    @Override
    public void draw(Pane pane){
    Polygon poly= new Polygon();
    this.getPointsPos();
    poly.setFill(this.getFillColor());
    poly.setStroke(this.getColor());
    for(Point2D p: Points2D){
        poly.getPoints().addAll(p.getX(),p.getY());
    }
    System.out.println(poly.toString());
    pane.getChildren().removeAll(Points);
    pane.getChildren().add(poly);
    
}
}
