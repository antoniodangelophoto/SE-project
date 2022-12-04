/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool.Pattern;

/**
 *
 * @author giova
 */
public class MoveSingleton {
    static private MoveSingleton moveInstance=null;
    private boolean moveProp=false;
    public static MoveSingleton getInstance(){
        
        if (moveInstance == null)
            moveInstance= new MoveSingleton();
        return moveInstance;
    }
    private MoveSingleton(){
        
    }
    public void moveSetTrue(){
        moveProp=true;
    }
    public void moveSetFalse(){
        moveProp=false;
    }
    public boolean getMoveProp(){
        return moveProp;
    }
}
