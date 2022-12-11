/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool;

import com.unisa.diem.SE.tool.Pattern.Command;
import com.unisa.diem.SE.tool.Pattern.SelectionSingleton;
import java.util.ArrayList;
import javafx.geometry.Point2D;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/**
 *
 * @author giova
 */
public class pattGroup implements Command{
    Pane pane;
    SelectionSingleton selSing=SelectionSingleton.getInstance();
    ArrayList<Shape> gList= new ArrayList<Shape>();
    Shape temp=null;
    Point2D selP;
    public pattGroup(Pane pane,Point2D sp) {
        this.pane = pane;
        this.selP=sp;
    }
    
    @Override
    public void execute() {
        
                Shape Copy;
                Point2D tempPos=new Point2D(0,0);
                
                
                
                for(Shape s: selSing.getList()){
                   
                    s.getStrokeDashArray().clear();
                    Copy=s;
                    
                    if(temp==null){
                        temp= Shape.union(Copy, s);                    
                    }    
                    else{
                        temp= Shape.union(s, temp);
                        
                    }
                    
                    temp.setFill(s.getFill());
                    temp.setStroke(s.getStroke());
                    
                    gList.add(s);
                    pane.getChildren().remove(s);
                }
                selSing.clear();
                temp.relocate(selP.getX()-tempPos.getX()/2, selP.getY()-tempPos.getY()/2);
                if(pane.getScaleX()>1){
                    double scaleProp= pane.getScaleX()-1;
                    temp.setScaleX(1-(scaleProp*(1-scaleProp)));
                    temp.setScaleY(1-(scaleProp*(1-scaleProp)));
                }
                if(pane.getScaleX()<1){
                    double scaleProp= 1-pane.getScaleX();
                    temp.setScaleX(1+(scaleProp/(1-scaleProp)));
                    temp.setScaleY(1+(scaleProp/(1-scaleProp)));
                }
               
                pane.getChildren().add(temp);
                
    }
    @Override
    public void undo() {
        pane.getChildren().remove(temp);
        for(Shape s:gList)
            pane.getChildren().add(s);
    }
    
}