
package com.unisa.diem.SE;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;   
import javafx.scene.input.MouseEvent;
import javafx.geometry.Point2D;
import com.unisa.diem.SE.tool.*;
import com.unisa.diem.SE.tool.Pattern.ColorSingle;
import com.unisa.diem.SE.tool.Pattern.CopySingleton;
import com.unisa.diem.SE.tool.Pattern.SelectionSingleton;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
    private ColorPicker StrokeColor;
    @FXML
    private ColorPicker FillColor;
    @FXML
    private Button selectionMode;
    @FXML
    private MenuItem Import;
    @FXML
    private MenuItem Export;
    @FXML
    private Pane Pane;


    //private Stack primary = new Stack<ArrayList<Shapes>>();
    //private Stack secondary = new Stack<ArrayList<Shapes>>();
    
    private ContextMenu selMenu;
    
    
    private Point2D selPosition;
    private Point2D start;
    private Point2D end;
    
    private ArrayList<Shapes> ShapeList = new ArrayList<Shapes>();

    Shapes shape = new Shapes();
    Move move = new Move();
    ChangeColorFill changeColorFill;
    ChangeColorStroke changeColorStroke;
    //DropShadow dropShadow = new DropShadow();

    private boolean rectangle=false;
    private boolean ellipse=false;
    private boolean line=false;
    private boolean selection=false;
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contextMenuInitialize();
        
    }    


    @FXML
    private void rectangleMode(ActionEvent event) {
        ellipse=false;
        rectangle=true;
        line=false;
        selection=false;
    }

    @FXML
    private void ellipseMode(ActionEvent event) {
        ellipse=true;
        rectangle=false;
        line=false;
        selection=false;
    }

    @FXML
    private void lineMode(ActionEvent event) {
        ellipse=false;
        rectangle=false;
        line=true;
        selection=false;
    }

    @FXML
    private void selectionMode(ActionEvent event) {
        selection=true;
        ellipse=false;
        rectangle=false;
        line=false;
    }

    public void drawFunction(){
        Shapes sh;
        if(ellipse){
            sh=new dEllipse(start,end,StrokeColor.getValue(),FillColor.getValue());
            sh.setType("Ellipse");
            sh.draw(Pane);
            ShapeList.add(sh);

        }if(rectangle){
            sh=new Rect(start,end,StrokeColor.getValue(),FillColor.getValue());
            sh.setType("Rectangle");
            sh.draw(Pane);
            ShapeList.add(sh);
            
        }if(line){
            sh=new LineSegment(start,end,StrokeColor.getValue());
            sh.setType("Line");
            sh.draw(Pane);
            ShapeList.add(sh);
        } 
    }

    @FXML
    private void mouseUp(MouseEvent event) {
        end = new Point2D(event.getX(), event.getY());
        
        //controllo con quale tasto stiamo pigiando, principale per disegnare e secondario per il menu a nuvola
        drawFunction();
    }

    @FXML
    private void setLine(MouseEvent event) {
        shape = (Shapes)event.getTarget();
    }
    

    @FXML
    private void mouseDown(MouseEvent event) {
         start = new Point2D(event.getX(),event.getY());
    }

    @FXML
    private void select(MouseEvent event) {
        SelectionSingleton selSing=SelectionSingleton.getInstance();            //Inizialize pattern singleton
        CopySingleton copySing=CopySingleton.getInstance();                     //Inizialize pattern singleton
        Shape shSel;
        
        
        if(selection){
            if(!(event.getTarget().equals(Pane))){
                // sto cliccando una shape
                
                shSel = (Shape)event.getTarget();                               //nella variabile singleton inserisco la shape di interesse
                ColorSingle colorTemp=ColorSingle.getInstance((Color) shSel.getStroke());   //Inizialize pattern singleton
                
                selPosition= new Point2D(event.getX(),event.getY());

                    if(selSing.getList().contains(shSel) & shSel!=null ){       //se nella selezione c'è la shape di interesse

                        shSel.setStrokeDashOffset(0);
                        shSel.setStroke(colorTemp.getColor());
                        selSing.remove(shSel);
                        shSel=null;
                        
                    }else{
                        menuEnableSetitem();
                        selSing.set(shSel);

                        if(copySing.getList().isEmpty())selMenu.getItems().get(3).disableProperty().set(true);
                        else selMenu.getItems().get(3).disableProperty().set(false);
                        
                        /*dropShadow.setRadius(5.0);
                        dropShadow.setOffsetX(3.0);
                        dropShadow.setOffsetY(3.0);
                        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
                        shSel.setEffect(dropShadow);*/

                        //shSel.setStroke(Color.RED);
                        shSel.getStrokeDashArray().addAll(5.0,5.0,5.0);
                        selMenu.show(shSel,Side.RIGHT,0 ,0);
                    }
                    
            }else{
                
                ColorSingle colorTemp=ColorSingle.getInstance();
                for(Shape s: selSing.getList()){
                    s.setStroke(colorTemp.getColor());
                    s.setStrokeDashOffset(0);;
                }
                
                shSel=null;
                selSing.clear();
                if(selSing.getList().isEmpty())
                    menuEnableSetitem();
                else 
                    menuDisableSetitem();

                if(copySing.getList().isEmpty())
                    selMenu.getItems().get(3).disableProperty().set(true);
                else 
                    selMenu.getItems().get(3).disableProperty().set(false);
                    
                selPosition= new Point2D(event.getX(),event.getY());
                selMenu.show(Pane, event.getScreenX(),event.getScreenY());
                return;    
            }
            //changeColorFill.execute();
            //changeColorStroke.execute();
            
            
        }else{
            selSing.clear();
            shSel=null;
            
        }

        move.moveShape(shSel);
    
    }
    
    @FXML
    public void importFile(ActionEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open resource file");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.xml"));
        String file = fileChooser.showOpenDialog(null).getPath();
        if(file!=null){
            FileManager fm = new FileManager();
            fm.loadFile(file, Pane);

        }
    }
    @FXML
    public void exportFile(ActionEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open resource file");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.xml"));
        String file = fileChooser.showSaveDialog(null).getPath();
        if(file!=null){
            FileManager fm = new FileManager();
            fm.saveFile(file, ShapeList, Pane);

        }
    }
    public void contextMenuInitialize() {
        selMenu=new ContextMenu();
        MenuItem cut=new MenuItem("cut");
        MenuItem copy=new MenuItem("copy");
        MenuItem delete=new MenuItem("delete");
        MenuItem paste=new MenuItem("paste");
        MenuItem selectOther=new MenuItem("Select Other..");
        
        cut.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                cop();
                del();
                
            }
                    
        });
        
        copy.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                cop();
            }
                    
        });
        
        delete.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                del();
            }
                    
        });
        
        paste.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                Shape Copy;
                CopySingleton copySing=CopySingleton.getInstance();
                for(Shape s: copySing.getList()){
                    Copy = s;
                    
                    Point2D tempPos= new Point2D(s.getLayoutX(),s.getLayoutY());
                    Shape temp=Shape.union(s, Copy);
                    
                    temp.setFill(s.getFill());
                    temp.setStroke(s.getStroke());
                    temp.relocate(selPosition.getX(), selPosition.getY());
                    System.out.println(temp.getFill());
                    Pane.getChildren().add(temp);
                    System.out.println(Pane.getChildren().toString());
                }
                copySing.clear();
                    
            }        
        });
        
        selMenu.getItems().addAll(cut,copy,delete,paste,selectOther);
            
        }
        
        public void del(){
             SelectionSingleton selectedShape = SelectionSingleton.getInstance();
             Pane.getChildren().removeAll(selectedShape.getList());
             selectedShape.clear();
             
        }
        
        public void cop(){
             CopySingleton copySing=CopySingleton.getInstance();
             SelectionSingleton selectedShape= SelectionSingleton.getInstance();
             copySing.setAll(selectedShape.getList());
             
        }

    private void menuEnableSetitem() {
        selMenu.getItems().get(0).disableProperty().set(false);
        selMenu.getItems().get(1).disableProperty().set(false);
        selMenu.getItems().get(2).disableProperty().set(false);
    }
    
    private void menuDisableSetitem() {
        selMenu.getItems().get(0).disableProperty().set(true);
        selMenu.getItems().get(1).disableProperty().set(true);
        selMenu.getItems().get(2).disableProperty().set(true);
    }


}
