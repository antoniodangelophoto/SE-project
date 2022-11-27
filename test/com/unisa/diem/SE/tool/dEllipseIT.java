/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.unisa.diem.SE.tool;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Giova
 */
public class dEllipseIT {
    
    Pane pane;
    Point2D points;
    Point2D pointe;
    @Test
    void testEllipse(){
        dEllipse ellipse = new dEllipse();
       
        assertInstanceOf(Shape.class, ellipse.draw(pane, points.add(150.0,150.0),pointe.add(80.0,220.0), Color.BLACK, Color.TRANSPARENT));
    }
    
}
