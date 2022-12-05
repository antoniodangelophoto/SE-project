package com.unisa.diem.SE.tool;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author GBCuomo
 */
import com.unisa.diem.SE.tool.Rect;
import com.unisa.diem.SE.tool.Shapes;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import org.junit.*;
import static org.junit.Assert.*;


public class RectTest {

    @Test
    public void testToString(){

        Point2D start = new Point2D(75.0, 80.0);
        Point2D end = new Point2D(80.0, 92.0);
        Rect r = new Rect(start, end, Color.valueOf("0xffff0000"),Color.valueOf("0x00ffff00"));
        assertEquals(r.toString(),"Rectangle,75.0,80.0,80.0,92.0,0xffff0000,0x00ffff00\n");

    }

}
