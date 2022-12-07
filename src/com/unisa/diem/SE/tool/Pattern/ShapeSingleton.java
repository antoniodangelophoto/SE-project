/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool.Pattern;

import javafx.scene.shape.Shape;

/**
 *
 * @author antonio d'angelo
 */
public class ShapeSingleton{
    
    static private ShapeSingleton shapeInstance;
    public static ShapeSingleton getInstance(){
        
        if (shapeInstance == null)
            shapeInstance= new ShapeSingleton();
        return shapeInstance;
    }
    private Shape shape;
    private ShapeSingleton(){
        shape=new Shape() {
            @Override
            public com.sun.javafx.geom.Shape impl_configShape() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
    }
    
    public Shape get(){
        return shape;
    }
    public void set(Shape e){
        shape=e;
    }
    public void clear(){
        shape=null;
    }
}
