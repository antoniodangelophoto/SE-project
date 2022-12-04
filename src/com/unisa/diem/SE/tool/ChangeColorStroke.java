package com.unisa.diem.SE.tool;

import com.unisa.diem.SE.tool.Pattern.Command;
import com.unisa.diem.SE.tool.Pattern.SelectionSingleton;
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
    private SelectionSingleton selSing = SelectionSingleton.getInstance();
    
    public ChangeColorStroke(ObjectProperty strokeColPick){
        this.strokeColPick = strokeColPick;
    }
    
    @Override
    public void execute(){
        for(Shape s:selSing.getList()){
          if(s!=null){
             s.getStrokeDashArray().clear();
             previousColor = (Color) s.getStroke();
             s.setStroke((Color) strokeColPick.getValue());
            
          }
        }
        selSing.clear();
    }
    @Override
    public void undo(){
        for(Shape s:selSing.getList())
            s.setStroke(previousColor);
    }
    
    
    
}
