/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 *
 * @author giova
 */
public class Rect extends Shapes{
    private double Base;
    private double Heigth;
    
    private Color strokeColor;
    private Color fillColor;
    
    private Rectangle rect;

    public Rect(){

    }
    public Rect(Point2D startPos, Point2D endPos, Color strockColor, Color fillColor) {
        super(startPos, endPos, strockColor, fillColor);
        this.Base=calculateBase();
        this.Heigth=calculateHeigth();
        setStrokeColor(strockColor);
        setFillColor(fillColor);
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
    
    

    private Double calculateBase() {
        double base=this.getPosition().getX()-this.getEndPosition().getX();
        if(base>0)
            return base;
        else {
           base=this.getEndPosition().getX()-this.getPosition().getX();
           return base;
        }
    }
    private Double calculateHeigth() {
        double heigth=this.getPosition().getY()-this.getEndPosition().getY();
        if(heigth>0)
            return heigth;
        else {
           heigth=this.getEndPosition().getY()-this.getPosition().getY();
           return heigth;
        }
        
    }

    public Double getBase() {
        return Base;
    }

    public Double getHeigth() {
        return Heigth;
    }

    public void setBase(Double Base) {
        this.Base = Base;
    }

    public void setHeigth(Double Heigth) {
        this.Heigth = Heigth;
    }

    @Override
    public void draw(Pane pane) {
        rect=new Rectangle(this.Base,this.Heigth,Color.TRANSPARENT);
        rect.relocate(this.getPosition().getX(),this.getPosition().getY());
        
        rect.setStroke(getStrokeColor());
        rect.setFill(getFillColor());
        
        pane.getChildren().add(rect);
    }

    @Override
    public String toString() {
        return "Rectangle,"+this.getPosition().getX()+","+this.getPosition().getY() + "," + this.getEndPosition().getX() + "," + this.getEndPosition().getY() + ","+ this.getColor() + ","+ this.getFillColor() +"\n";
    }
      
}
