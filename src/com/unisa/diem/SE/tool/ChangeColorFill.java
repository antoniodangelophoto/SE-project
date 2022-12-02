package com.unisa.diem.SE.tool;

import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 *
 * @author Francesco
 */
public class ChangeColorFill implements Command{
    
    private ObjectProperty fillColPick;
    private Color previousColor;
    private SelectedShape selShape = SelectedShape.getInstance();
    private Shape shape;
    
    public ChangeColorFill(ObjectProperty fillColPick){
        this.fillColPick = fillColPick;
    }
    
    @Override
    public void execute(){
        shape = selShape.getShapeSel();
        if(shape!=null){
            shape.getStyleClass().remove("selectBorder");
            previousColor = (Color) shape.getFill();
            shape.setFill((Color) fillColPick.getValue());
            selShape.setShapeSel(null);
            
        }
    }
    
    @Override
    public void undo(){
        shape.setFill(previousColor);
    }
    
    
    
}
