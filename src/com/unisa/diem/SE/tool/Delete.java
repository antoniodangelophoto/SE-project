/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool;

import com.unisa.diem.SE.tool.Pattern.Command;
import com.unisa.diem.SE.tool.Pattern.SelectionSingleton;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/**
 *
 * @author giova
 */
public class Delete implements Command{
    private Pane pane;
    private SelectionSingleton selSing = SelectionSingleton.getInstance();
    private ArrayList<Shape> list=new ArrayList<Shape>();
    public Delete(Pane p){
        pane=p;
    }
    @Override
    public void execute(){
        pane.getChildren().removeAll(selSing.getList());
        list.addAll(list);
        selSing.clear();
    }

    @Override
    public void undo() {
        for(Shape s : list)
            pane.getChildren().addAll(list);
    }
}
