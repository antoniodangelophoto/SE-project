
package com.unisa.diem.SE;

import com.unisa.diem.SE.tool.Pattern.CommandExecutor;
import com.unisa.diem.SE.tool.Pattern.Command;
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
import com.unisa.diem.SE.tool.Pattern.MoveSingleton;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    private Button moveMode;
    @FXML
    private ComboBox<?> customShape;
    @FXML
    private ColorPicker StrokeColor;
    @FXML
    private ColorPicker FillColor;
    @FXML
    private MenuItem Import;
    @FXML
    private MenuItem Export;
    @FXML
    private Pane Pane;
    @FXML
    private VBox menuSx;
    @FXML
    private ToolBar ToolBarMenu;
    @FXML
    private ScrollPane scrollPane;



    //private Stack primary = new Stack<ArrayList<Shapes>>();
    //private Stack secondary = new Stack<ArrayList<Shapes>>();
    
    private ContextMenu selMenu;
    
    
    private Point2D selPosition;
    private Point2D start;
    private Point2D end;
    
    private ArrayList<Shapes> ShapeList = new ArrayList<Shapes>();

    Shapes shape = new Shapes();
    Move move=new Move();
    ChangeColorFill changeColorFill;
    ChangeColorStroke changeColorStroke;
    //DropShadow dropShadow = new DropShadow();

    private boolean rectangleMod=false;
    private boolean ellipseMod=false;
    private boolean lineMod=false;
    
    //PATTERN COMMAND
    CommandExecutor commExe= new CommandExecutor();
    
    // DEFINIZIONE PATTERN SINGLETON
    MoveSingleton moveProp=MoveSingleton.getInstance();
    SelectionSingleton selSing=SelectionSingleton.getInstance();            //Inizialize pattern singleton
    CopySingleton copySing=CopySingleton.getInstance();                     //Inizialize pattern singleton
    ColorSingle colorTemp;
    
    Shape shSel;
    Shapes sh; 
     
   
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
        ellipseMod=false;
        rectangleMod=true;
        lineMod=false;
        moveProp.moveSetFalse();
    }

    @FXML
    private void ellipseMode(ActionEvent event) {
        ellipseMod=true;
        rectangleMod=false;
        lineMod=false;
        moveProp.moveSetFalse();
    }

    @FXML
    private void lineMode(ActionEvent event) {
        ellipseMod=false;
        rectangleMod=false;
        lineMod=true;
        moveProp.moveSetFalse();
    }

    @FXML
    private void moveMode(ActionEvent event) {
        moveProp.moveSetTrue();
        ellipseMod=false;
        rectangleMod=false;
        lineMod=false;
    }

    public void drawFunction(){
        if(ellipseMod){
            sh=new dEllipse(start,end,StrokeColor.getValue(),FillColor.getValue());
            sh.setType("Ellipse");
            sh.draw(Pane);
            ShapeList.add(sh);

        }if(rectangleMod){
            sh=new Rect(start,end,StrokeColor.getValue(),FillColor.getValue());
            sh.setType("Rectangle");
            sh.draw(Pane);
            ShapeList.add(sh);
            
        }if(lineMod){
            sh=new LineSegment(start,end,StrokeColor.getValue());
            sh.setType("Line");
            sh.draw(Pane);
            ShapeList.add(sh);
        } 
    }

    @FXML
    private void mouseUp(MouseEvent event) {
        end = new Point2D(event.getX(), event.getY());
        if(event.getButton()==MouseButton.PRIMARY){
            drawFunction();
        }
    }

    @FXML
    private void mouseDrag(MouseEvent event) {
        /*if(this.sh != null){
            sh.resize(Pane, event.getX(), event.getY());
            sh.stretch(Pane, event.getX(), event.getY());
        }*/
    }

    

    @FXML
    private void mouseDown(MouseEvent event) {
        
        if(event.getButton()==MouseButton.PRIMARY){
            
            if(moveProp.getMoveProp()){
                shSel = (Shape)event.getTarget();
            }else{
                start = new Point2D(event.getX(),event.getY());
            }
         
        } else if(event.getButton()==MouseButton.SECONDARY){
            
            //Shape shSel;
            
            if(!(event.getTarget().equals(Pane))){
                
                shSel = (Shape)event.getTarget();
                colorTemp=ColorSingle.getInstance((Color) shSel.getStroke()); 
                
                if(selSing.getList().contains(shSel) & shSel!=null ){       //se la shape è già selezionata, la vado a delezionare

                    shSel.getStrokeDashArray().clear(); //rimuovo tratteggio
                    shSel.setStroke(colorTemp.getColor());
                    selMenu.hide();
                    selSing.remove(shSel);
                    shSel=null;

                }else{
                    menuEnableSetitem();
                    selSing.set(shSel);

                    if(copySing.getList().isEmpty()){
                        selMenu.getItems().get(3).disableProperty().set(true);
                    }else{
                        selMenu.getItems().get(3).disableProperty().set(false);
                    }

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
                
                System.out.println("PANE moveMODE");
                ColorSingle colorTemp=ColorSingle.getInstance();
                for(Shape s: selSing.getList()){
                    s.setStroke(colorTemp.getColor());
                    s.getStrokeDashArray().clear();
                }
                
                shSel=null;
                selSing.clear();
                if(selSing.getList().isEmpty()){
                    menuDisableSetitem();
                }else{ 
                    menuEnableSetitem();
                }
                
                if(copySing.getList().isEmpty())
                    selMenu.getItems().get(3).disableProperty().set(true);
                else 
                    selMenu.getItems().get(3).disableProperty().set(false);
                    
                selPosition= new Point2D(event.getX(),event.getY());
                selMenu.show(Pane, event.getScreenX(),event.getScreenY());
                return;
            }
            
        }
    }

    @FXML
    private void selectMove(MouseEvent event) {
        
            if(moveProp.getMoveProp()){
                move.moveShape(shSel,menuSx.getWidth(), scrollPane);
            
            }
        
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
        MenuItem changeFillColor= new MenuItem("Change Fill Color");
        MenuItem changeStrokeColor= new MenuItem("Change Stroke Color");
        MenuItem selectOther=new MenuItem("Select Other..");
        
        cut.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                cop();
                del();
            }
                    
        });
        
        copy.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                cop();
            }
                    
        });
        
        delete.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                del();
            }
                    
        });
        
        paste.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                Command pas=new Paste(selPosition,Pane);
                commExe.execute(pas);
                
                    
            }        
        });
        changeFillColor.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                Command fillCommand= new ChangeColorFill(FillColor.valueProperty());
                commExe.execute(fillCommand);
            }
        });
        
        changeStrokeColor.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                Command strokeCommand = new ChangeColorStroke(StrokeColor.valueProperty());
                commExe.execute(strokeCommand);
            }
        });
        
        selMenu.getItems().addAll(cut,copy,delete,paste,changeFillColor,changeStrokeColor,selectOther);
            
        }
        
        public void del(){
             Command del=new Delete(Pane);
             commExe.execute(del);
             
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

    @FXML
    private void customShapeAction(ActionEvent event) {
    }


}
