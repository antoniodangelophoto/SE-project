/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author GBCuomo
 */

import com.unisa.diem.SE.tool.Shapes;
import com.unisa.diem.SE.tool.dEllipse;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import org.junit.*;
import static org.junit.Assert.*;


public class ShapesTest {

    @Test
    public void testToString(){

        Point2D start = new Point2D(75.0, 80.0);
        Point2D end = new Point2D(80.0, 92.0);
        Shapes e = new dEllipse(start, end, Color.valueOf("0xffff0000"),Color.valueOf("0x00ffff00"));
        assertEquals(e.toString(),"Ellipse,75.0,80.0,80.0,92.0,0xffff0000,0x00ffff00\n");

    }

}