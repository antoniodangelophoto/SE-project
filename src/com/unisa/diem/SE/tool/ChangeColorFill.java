package com.unisa.diem.SE.tool;

import com.unisa.diem.SE.tool.Pattern.Command;
import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import com.unisa.diem.SE.tool.Pattern.SelectionSingleton;
/**
 *
 * @author Francesco
 */
public class ChangeColorFill implements Command{
    
    private ObjectProperty fillColPick;
    private Color previousColor;
    private SelectionSingleton selSing = SelectionSingleton.getInstance();
    
    public ChangeColorFill(ObjectProperty fillColPick){
        this.fillColPick = fillColPick;
    }
    
    @Override
    public void execute(){
        for(Shape s:selSing.getList()){
            if(s!=null){
                s.getStrokeDashArray().clear();
                previousColor = (Color) s.getFill();
                s.setFill((Color) fillColPick.getValue());
           
            }
        }
        selSing.clear();
    }
    
    @Override
    public void undo(){
        for(Shape s:selSing.getList()){
        s.setFill(previousColor);
        }
    }
    
    
    
}
