
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
import com.unisa.diem.SE.tool.Pattern.CopySingleton;
import com.unisa.diem.SE.tool.Pattern.GoBackgroundCommand;
import com.unisa.diem.SE.tool.Pattern.GoFrontCommand;
import com.unisa.diem.SE.tool.Pattern.MoveSingleton;
import com.unisa.diem.SE.tool.Pattern.SelectionSingleton;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Screen;
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
    private ScrollPane scrollPane;
    //private Stack primary = new Stack<ArrayList<Shapes>>();
    //private Stack secondary = new Stack<ArrayList<Shapes>>();
    
    private ContextMenu selMenu;
    
    
    private Point2D selPosition;
    private Point2D start;
    private Point2D end;
    
    //private ArrayList<Shapes> ShapeList = new ArrayList<Shapes>();

    Shapes shape = new Shapes();
    Move move=new Move();
    ChangeColorFill changeColorFill;
    ChangeColorStroke changeColorStroke;
    //DropShadow dropShadow = new DropShadow();

    private boolean cutMode=false;
    private boolean rectangleMod=false;
    private boolean ellipseMod=false;
    private boolean lineMod=false;
    private boolean polygonMode;
    //PATTERN COMMAND
    CommandExecutor commExe= new CommandExecutor();
    
    //Polygon Points
    ArrayList<Circle> points= new ArrayList<Circle>();
    // DEFINIZIONE PATTERN SINGLETON
    MoveSingleton moveProp=MoveSingleton.getInstance();
    SelectionSingleton selSing=SelectionSingleton.getInstance();            //Inizialize pattern singleton
    CopySingleton copySing=CopySingleton.getInstance();                     //Inizialize pattern singleton
    //ColorSingle colorTemp;
    
    Shape shSel;
    Shapes sh; 
    @FXML
    private Button PolyMode;
    @FXML
    private ScrollPane ScrollPane;
    @FXML
    private Text zoomOutInButton;
    @FXML
    private Text zoomInInButton;
    private double resizeWindow=1;
   
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
        polygonMode=false;
    }

    @FXML
    private void ellipseMode(ActionEvent event) {
        ellipseMod=true;
        rectangleMod=false;
        lineMod=false;
        moveProp.moveSetFalse();
        polygonMode=false;
    }

    @FXML
    private void lineMode(ActionEvent event) {
        ellipseMod=false;
        rectangleMod=false;
        lineMod=true;
        moveProp.moveSetFalse();
        polygonMode=false;
    }

    @FXML
    private void moveMode(ActionEvent event) {
        moveProp.moveSetTrue();
        ellipseMod=false;
        rectangleMod=false;
        lineMod=false;
        polygonMode=false;
    }
    @FXML
    private void SetPolymode(ActionEvent event) {
        ellipseMod=false;
        rectangleMod=false;
        lineMod=false;
        moveProp.moveSetFalse();
        polygonMode=true;
        if(points.size()>1)
            drawFunction();
        else {
            
            Pane.getChildren().removeAll(points);
            points.clear();
        }
    }
    /*
    public void drawFunction(){
        if(ellipseMod){
            sh=new dEllipse(start,end,StrokeColor.getValue(),FillColor.getValue());
            sh.setType("Ellipse");
            sh.draw(Pane);
            //ShapeList.add(sh);

        }if(rectangleMod){
            sh=new Rect(start,end,StrokeColor.getValue(),FillColor.getValue());
            sh.setType("Rectangle");
            sh.draw(Pane);
            //ShapeList.add(sh);
            
        }if(lineMod){
            sh=new LineSegment(start,end,StrokeColor.getValue());
            sh.setType("Line");
            sh.draw(Pane);
            //ShapeList.add(sh);
        } 
    }
    */
    
    public void drawFunction(){
        if(ellipseMod){
            sh=new dEllipse(start,end,StrokeColor.getValue(),FillColor.getValue());
            sh.setType("Ellipse");
            sh.draw(Pane);
            //ShapeList.add(sh);

        }if(rectangleMod){
            sh=new Rect(start,end,StrokeColor.getValue(),FillColor.getValue());
            sh.setType("Rectangle");
            sh.draw(Pane);
            //ShapeList.add(sh);
            
        }if(lineMod){
            sh=new LineSegment(start,end,StrokeColor.getValue());
            sh.setType("Line");
            sh.draw(Pane);
            //ShapeList.add(sh);
        } if(polygonMode){
            sh=new Polygons(Point2D.ZERO, Point2D.ZERO, StrokeColor.getValue(),FillColor.getValue(),points);
            sh.setType("Polygon");
            sh.draw(Pane);
            points.clear();
        }
    }

    @FXML
    private void mouseUp(MouseEvent event) {
        if((ellipseMod || rectangleMod || lineMod )&& event.getButton()==MouseButton.PRIMARY){
            end = new Point2D(event.getX(), event.getY());
            sh.resize(start, end);
        }
    }

    @FXML
    private void mouseDrag(MouseEvent event) {
        /*if(this.sh != null){
            sh.resize(Pane, event.getX(), event.getY());
            sh.stretch(Pane, event.getX(), event.getY());
        }*/
        if((ellipseMod || rectangleMod || lineMod )&& event.getButton()==MouseButton.PRIMARY){
            end = new Point2D(event.getX(), event.getY());
            sh.resize(start, end);
        }
        
    }
    
  

    

    @FXML
    private void mouseDown(MouseEvent event) {
        
        if(event.getButton()==MouseButton.PRIMARY){
            
            if(moveProp.getMoveProp()){
                shSel = (Shape)event.getTarget();
                Pane.getChildren().removeAll(points);
                points.clear();
            }else if(polygonMode){
                Circle point= new Circle();
                point.setFill(Color.RED);
                point.setCenterX(event.getX());
                point.setCenterY(event.getY());
                point.setRadius(10);
                
                points.add(point);
                Pane.getChildren().add(point);
            }else{
                start = new Point2D(event.getX(),event.getY());
                end=start;
                drawFunction();
                Pane.getChildren().removeAll(points);
                points.clear();
            }
         
        } else if(event.getButton()==MouseButton.SECONDARY){
            Pane.getChildren().removeAll(points);
            points.clear();
            //Shape shSel;
            
            if(!(event.getTarget().equals(Pane))){
                
                shSel = (Shape)event.getTarget();
                //colorTemp=ColorSingle.getInstance((Color) shSel.getStroke()); 
                
                if(selSing.getList().contains(shSel) & shSel!=null ){       //se la shape è già selezionata, la vado a delezionare

                    shSel.getStrokeDashArray().clear(); //rimuovo tratteggio
                    //shSel.setStroke(colorTemp.getColor());
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
                    selPosition= new Point2D(event.getX(),event.getY());
                    selMenu.show(shSel,Side.RIGHT,0 ,0);
                }
                
            }else{
                
                System.out.println("PANE moveMODE");
                //ColorSingle colorTemp=ColorSingle.getInstance();
                for(Shape s: selSing.getList()){
                    //s.setStroke(colorTemp.getColor());
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
        Pane.getChildren().removeAll(points);
        points.clear();
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
        Pane.getChildren().removeAll(points);
        points.clear();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open resource file");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.xml"));
        String file = fileChooser.showSaveDialog(null).getPath();
        if(file!=null){
            FileManager fm = new FileManager();
            fm.saveFile(file, Pane);

        }
    }
    public void contextMenuInitialize() {
        selMenu=new ContextMenu();
        MenuItem cut=new MenuItem("cut");
        MenuItem copy=new MenuItem("copy");
        MenuItem delete=new MenuItem("delete");
        MenuItem paste=new MenuItem("paste");
        MenuItem group= new MenuItem("group");
        MenuItem changeFillColor= new MenuItem("Change Fill Color");
        MenuItem changeStrokeColor= new MenuItem("Change Stroke Color");
        MenuItem undo = new MenuItem("Undo");
        MenuItem selectOther=new MenuItem("Select Other..");
        
        cut.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                cop();
                del();
                cutMode=true;
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
        group.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                  Shape temp=null;
                Shape Copy;
                Point2D tempPos=new Point2D(0,0);
                int counter=0;
                for(Shape s: selSing.getList()){
                   
                    s.getStrokeDashArray().clear();
                    Copy=s;
                    
                    if(temp==null){
                        System.out.println(s.getLayoutX()+"ue"+s.getLayoutY());
                        temp=Shape.union(Copy, s);
                        temp.setLayoutX(s.getLayoutX());
                        temp.setLayoutY(s.getLayoutY());
                        tempPos=new Point2D(s.getLayoutX(),s.getLayoutY());
                        System.out.println(temp.getLayoutX()+"ue"+temp.getLayoutY());
                    }    
                    else{
                        temp=Shape.union(s, temp);
                        
                    }
                    temp.setFill(s.getFill());
                    temp.setStroke(s.getStroke());
                    
                    Pane.getChildren().remove(s);
                }
                selSing.clear();
                Pane.getChildren().add(temp);
            }        
        });
        paste.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                Command pas=new Paste(selPosition,Pane);
                commExe.execute(pas);
                if(cutMode)
                    copySing.clear();
                
                cutMode=false;    
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
        
        undo.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                commExe.undoLast();
            }
            
        });
        
        
        selMenu.getItems().addAll(cut,copy,delete,paste,changeFillColor,group,changeStrokeColor,undo,selectOther);
            
        }
        
        public void del(){
             Command del=new Delete(Pane);
             commExe.execute(del);
             
        }
        
        public void cop(){
             CopySingleton copySing=CopySingleton.getInstance();
             SelectionSingleton selectedShape= SelectionSingleton.getInstance();
             copySing.clear();
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

    @FXML
    private void endDrag(DragEvent event) {
    }

    @FXML
    private void zoomInOnAction(ActionEvent event) {
        if(this.resizeWindow<1.4){
            this.resizeWindow+=0.1;
        }
        this.Pane.setScaleX(this.resizeWindow);
        this.Pane.setScaleY(this.resizeWindow);   
    }
    @FXML
    private void zoomInOnMousePressed(MouseEvent event) {

    }
    @FXML
    private void zoomInOnMouseReleased(ActionEvent event) {

    }
    @FXML
    private void zoomOutOnAction(ActionEvent event) {
        if(this.resizeWindow>0.6){
            this.resizeWindow-=0.1;
        }
        this.Pane.setScaleX(this.resizeWindow);
        this.Pane.setScaleY(this.resizeWindow);
    }   
    @FXML
    private void zoomOutOnMousePressed(MouseEvent event) {

    }
    @FXML
    private void zoomOutOnMouseReleased(ActionEvent event) {

    }
    
    @FXML
    private void BringToFrontOnAction(ActionEvent event) {
        if (shSel != null) {
            commExe.execute(new GoFrontCommand(Pane, shSel));
        }
    }

    @FXML
    private void BringToBackOnAction(ActionEvent event) {
        if (shSel != null) {
            commExe.execute(new GoBackgroundCommand(Pane, shSel));
        }
    }

}
