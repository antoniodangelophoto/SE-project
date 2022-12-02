/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool.Pattern;

import java.util.ArrayList;
import javafx.scene.shape.Shape;

/**
 *
 * @author giova
 */
public class CopySingleton {
    static private CopySingleton copyInstance=null;
    public static CopySingleton getInstance(){
        
        if (copyInstance == null)
            copyInstance= new CopySingleton();
        return copyInstance;
    }
    private ArrayList<Shape> list;
    public CopySingleton(){
        list=new ArrayList<Shape>();
    }
    public ArrayList<Shape> getList(){
        return list;
    }
    public Shape get(int index){
        return list.get(index);
    }
    public void set(Shape e){
        list.add(e);
    }
    public void setAll(ArrayList l){
        list.addAll(l);
    }
    
    public void remove(Shape e){
        list.remove(e);
    }
    public void clear(){
        list.clear();
    }
}
