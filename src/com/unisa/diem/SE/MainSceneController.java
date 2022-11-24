/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.unisa.diem.SE;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;   
import javafx.scene.input.MouseEvent;
import javafx.geometry.Point2D;
import com.unisa.diem.SE.tool.Shape;
import com.unisa.diem.SE.tool.Ellipse;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
/**
 * FXML Controller class
 *
 * @author giova
 */
public class MainSceneController implements Initializable {

    @FXML
    private Button rectangleMode;
    @FXML
    private Button ellipseMode;
    @FXML
    private Button segmentMode;
    @FXML
    private ComboBox<?> customShape;
    @FXML
    private ComboBox<String> ColorBox;
    @FXML
    private Button selectionMode;
    @FXML
    private MenuItem Import;
    @FXML
    private MenuItem Export;
    @FXML
    private Canvas Canvas;
    
    private Point2D start;
    private Point2D end;
    
    ArrayList<Color> colorList;
    private boolean rectangle=false;
    private boolean ellipse=false;
    private boolean line=false;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*ObservableList colorOList= FXCollections.observableList(colorList);
        colorOList.add(Color.BLACK);
        ColorBox.setItems(colorOList);*/
        //ColorBox.setValue(Color.BLACK);
    }    

    @FXML
    private void loadFile(ActionEvent event) {
    }

    @FXML
    private void exportFile(ActionEvent event) {
    }

    @FXML
    private void rectangleMode(ActionEvent event) {
        ellipse=false;
        rectangle=true;
        line=false;
    }

    @FXML
    private void ellipseMode(ActionEvent event) {
        ellipse=true;
        rectangle=false;
        line=false;
    }

    @FXML
    private void lineMode(ActionEvent event) {
        ellipse=true;
        rectangle=false;
        line=false;
    }

    @FXML
    private void selectionMode(ActionEvent event) {
    }
    @FXML
    public void startDrag(MouseEvent event){
        start = new Point2D(event.getX(),event.getY());
       
    }
    @FXML
    public void endDrag(MouseEvent event) {
        end = new Point2D(event.getX(), event.getY());
        if(end.equals(start)){
            
        }
        drawFunction();
    }
    public void drawFunction(){
        Shape sh;
        if(ellipse){
            sh=new Ellipse(start,end,Color.BLACK);
            sh.draw(Canvas);
        }if(rectangle){
            
        }if(line){
            
        } else {
            
        }
        
        
    }
    
}
