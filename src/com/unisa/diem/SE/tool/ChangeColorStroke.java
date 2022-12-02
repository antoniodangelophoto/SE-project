package com.unisa.diem.SE.tool;

import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 *
 * @author Francesco
 */
public class ChangeColorStroke implements Command{
    
    private ObjectProperty strokeColPick;
    private Color previousColor;
    private SelectedShape selShape = SelectedShape.getInstance();
    private Shape shape;
    
    public ChangeColorStroke(ObjectProperty strokeColPick){
        this.strokeColPick = strokeColPick;
    }
    
    @Override
    public void execute(){
        shape = selShape.getShapeSel();
        if(shape!=null){
            shape.getStyleClass().remove("selectBorder");
            previousColor = (Color) shape.getStroke();
            shape.setStroke((Color) strokeColPick.getValue());
            selShape.setShapeSel(null);
            
        }
    }
    
    @Override
    public void undo(){
        shape.setStroke(previousColor);
    }
    
    
    
}
