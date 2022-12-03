/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool.Pattern;

import javafx.scene.paint.Color;

/**
 *
 * @author giova
 */
public class ColorSingle {
    static private ColorSingle Instance=null;
    public static ColorSingle getInstance(){
        
        if (Instance == null)
            Instance= new ColorSingle();
        return Instance;
    }
    
    public static ColorSingle getInstance(Color c){
        
        if (Instance == null)
            Instance= new ColorSingle(c);
        return Instance;
    }
    private Color color;
    private ColorSingle(){
    }
    private ColorSingle(Color c){
        color = c;
    }
    public void setColor(Color c){
        color= c;
    }
    public Color getColor(){
        return color;
    }
}
