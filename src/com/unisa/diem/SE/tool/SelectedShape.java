
package com.unisa.diem.SE.tool;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.shape.Shape;

/**
 *
 * @author Francesco
 */
class SelectedShape {
    
    static private SelectedShape instance = null;
    public static SelectedShape getInstance(){
        if(instance == null)
            instance = new SelectedShape();
        return instance;
    }
    
    private Shape shapeSel =null;
    private SimpleBooleanProperty selectedProperty = new SimpleBooleanProperty();
    
    private SelectedShape(){
        
    }
    
    public Shape getShapeSel(){
        return shapeSel;
    }
    
    public void setShapeSel(Shape shapeSel){
        this.shapeSel = shapeSel;
        if(shapeSel == null)
            selectedProperty.set(false);
        else
            selectedProperty.set(true);
        
    }
    
    public SimpleBooleanProperty getSelectedProperty(){
        return selectedProperty;
    }
    
    
    
    
}