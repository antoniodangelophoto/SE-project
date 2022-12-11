/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool;

import com.unisa.diem.SE.tool.Pattern.Command;
import com.unisa.diem.SE.tool.Pattern.CopySingleton;
import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/**
 *
 * @author giova
 */
public class Paste implements Command{
    private CopySingleton copySing=CopySingleton.getInstance();
    private Point2D selPosition;
    private Pane pane;
    private Shape temp;
    private ArrayList<Shape> pasteList= new ArrayList();

    public Paste(Point2D selpos, Pane p) {
        this.selPosition = selpos;
        this.pane=p;
    }
    
    @Override
    public void execute() {
        Shape Copy;
        double a=0;
            for(Shape s: copySing.getList()){
                s.getStrokeDashArray().clear();
                Copy = s;
                //Point2D tempPos= new Point2D(s.getLayoutX(),s.getLayoutY());
                temp=Shape.union(s, Copy);

                temp.setFill(s.getFill());
                temp.setStroke(s.getStroke());
                //temp.relocate(selPosition.getX(), selPosition.getY());
                //System.out.println(temp.getFill());
                temp.relocate(selPosition.getX()+a, selPosition.getY()+a);
                a=a+20; 
                pasteList.add(temp);
                pane.getChildren().add(temp);
                //System.out.println(pane.getChildren().toString());
                
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
            }
            //copySing.clear();
    }

    @Override
    public void undo() {
         for(Shape s:pasteList)
            pane.getChildren().remove(temp);
        
    }
    
}
