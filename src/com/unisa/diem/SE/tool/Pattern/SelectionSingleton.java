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
public class SelectionSingleton{
    
    static private SelectionSingleton selectionInstance;
    public static SelectionSingleton getInstance(){
        
        if (selectionInstance == null)
            selectionInstance= new SelectionSingleton();
        return selectionInstance;
    }
    private ArrayList<Shape> list;
    private SelectionSingleton(){
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
    public void remove(Shape e){
        list.remove(e);
    }
    public void clear(){
        list.clear();
    }
}
