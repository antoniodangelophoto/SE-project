
package com.unisa.diem.SE;

import com.unisa.diem.SE.tool.Pattern.CommandExecutor;
import com.unisa.diem.SE.tool.Pattern.Command;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
    private Button PolyMode;
    @FXML
    private ScrollPane scrollPane;
    
    private double resizeWindow=1;
    @FXML
    private Button UndoBtn;
    @FXML
    private GridPane Grid;
    @FXML
    private CheckBox gridBtn;
    @FXML
    private Button resizeBtn;
    @FXML
    private HBox resizeHBox;
    @FXML
    private Button lessWidthButton;
    @FXML
    private Button moreWidthButton;
    @FXML
    private CheckBox blockAspectRatio;
    @FXML
    private HBox HeightHBox;
    @FXML
    private Button lessHeightButton;
    @FXML
    private Button moreHeightButton;
    @FXML
    private HBox sizeGridHBox;
    @FXML
    private Button lessSizeGridButton;
    @FXML
    private Button moreSizeGridButton;

    private ContextMenu selMenu;
       
    private Point2D selPosition;
    private Point2D start;
    private Point2D end;
   
    Shapes shape = new Shapes();
    Move move=new Move();
    ChangeColorFill changeColorFill;
    ChangeColorStroke changeColorStroke;
    

    //Mode's Flag
    private boolean cutMode=false;
    private boolean rectangleMod=false;
    private boolean ellipseMod=false;
    private boolean lineMod=false;
    private boolean polygonMode;
    private boolean resize=false;
    
    private boolean aspectRatio=true;
    
    //PATTERN COMMAND
    CommandExecutor commExe= new CommandExecutor();
    
    // DEFINIZIONE PATTERN SINGLETON
    MoveSingleton moveProp=MoveSingleton.getInstance();
    SelectionSingleton selSing=SelectionSingleton.getInstance();
    CopySingleton copySing=CopySingleton.getInstance();  
    
    //Polygon Points
    ArrayList<Circle> points= new ArrayList<Circle>();
    
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
        
        //listener for scrollPane zoomable and pannable
        Group paneGroup = new Group(Pane);
        scrollPane.setContent(paneGroup);
        scrollPane.fitToWidthProperty().set(true);
        scrollPane.fitToHeightProperty().set(true);
        scrollPane.viewportBoundsProperty().addListener((observable, oldValue, newValue)->{
            Pane.setPrefSize(newValue.getWidth(), newValue.getHeight());
        });
        
        resizeHBox.setDisable(true);
        HeightHBox.setDisable(true);
        sizeGridHBox.setDisable(true);
        
    }    

    @FXML
    private void rectangleMode(ActionEvent event) {
        ellipseMod=false;
        rectangleMod=true;
        lineMod=false;
        moveProp.moveSetFalse();
        polygonMode=false;
        resize=false;
        resizeHBox.setDisable(true);
    }

    @FXML
    private void ellipseMode(ActionEvent event) {
        ellipseMod=true;
        rectangleMod=false;
        lineMod=false;
        moveProp.moveSetFalse();
        polygonMode=false;
        resize=false;
        resizeHBox.setDisable(true);
    }

    @FXML
    private void lineMode(ActionEvent event) {
        ellipseMod=false;
        rectangleMod=false;
        lineMod=true;
        moveProp.moveSetFalse();
        polygonMode=false;
        resize=false;
        resizeHBox.setDisable(true);
    }

    @FXML
    private void moveMode(ActionEvent event) {
        moveProp.moveSetTrue();
        ellipseMod=false;
        rectangleMod=false;
        lineMod=false;
        polygonMode=false;
        resize=false;
        resizeHBox.setDisable(true);
    }
    @FXML
    private void SetPolymode(ActionEvent event) {
        ellipseMod=false;
        rectangleMod=false;
        lineMod=false;
        moveProp.moveSetFalse();
        polygonMode=true;
        resize=false;
        resizeHBox.setDisable(true);
        if(points.size()>1)
            drawFunction();
        else {
            
            Pane.getChildren().removeAll(points);
            points.clear();
        }
    }
    
    @FXML
    private void UndoMode(ActionEvent event) {
        ellipseMod=false;
        rectangleMod=false;
        lineMod=false;
        moveProp.moveSetFalse();
        polygonMode=false;
        resize=false;
        commExe.undoLast();
        resizeHBox.setDisable(true);
    }
    
    @FXML
    private void resizeButton(ActionEvent event) {
        resize=true;
        ellipseMod=false;
        rectangleMod=false;
        lineMod=false;
        moveProp.moveSetFalse();
        polygonMode=false;
        resizeHBox.setDisable(false);
    }
    
    public void drawFunction(){
        if(ellipseMod){
            sh=new dEllipse(start,end,StrokeColor.getValue(),FillColor.getValue());
            sh.setType("Ellipse");
            sh.draw(Pane);

        }if(rectangleMod){
            sh=new Rect(start,end,StrokeColor.getValue(),FillColor.getValue());
            sh.setType("Rectangle");
            sh.draw(Pane);
            
        }if(lineMod){
            sh=new LineSegment(start,end,StrokeColor.getValue());
            sh.setType("Line");
            sh.draw(Pane);

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
            
            if(!(event.getTarget().equals(Pane))){
                
                shSel = (Shape)event.getTarget();
                
                if(selSing.getList().contains(shSel) & shSel!=null ){       //se la shape è già selezionata, la vado a delezionare

                    shSel.getStrokeDashArray().clear(); //rimuovo tratteggio
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

                    shSel.getStrokeDashArray().addAll(5.0,5.0,5.0);
                    selPosition= new Point2D(event.getX(),event.getY());
                    selMenu.show(shSel,Side.RIGHT,0 ,0);
                }
                
            }else{
                
                System.out.println("PANE moveMODE");
                for(Shape s: selSing.getList()){
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
                  Command group= new pattGroup(Pane,selPosition);
                  commExe.execute(group);
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
        
        selMenu.getItems().addAll(cut,copy,delete,paste,changeFillColor,changeStrokeColor,group,selectOther);
            
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
    private void zoomOutOnAction(ActionEvent event) {
        if(this.resizeWindow>0.6){
            this.resizeWindow-=0.1;
        }
        this.Pane.setScaleX(this.resizeWindow);
        this.Pane.setScaleY(this.resizeWindow);
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

    @FXML
    private void scrollPaneMove(KeyEvent event) {
    }

    @FXML
    private void gridVisible(ActionEvent event) {
        if(gridBtn.isSelected()){
            Grid.setGridLinesVisible(true);
            sizeGridHBox.setDisable(false);
        }else{
            Grid.setGridLinesVisible(false);
            sizeGridHBox.setDisable(true);
        }
    }

    private void gridSliderDrag(MouseEvent event) {
    }

    @FXML
    private void lessWidthAction(ActionEvent event) {
        if(resize){
            if(aspectRatio){
                shSel.setScaleY(shSel.getScaleY()-0.1);
            }
            shSel.setScaleX(shSel.getScaleX()-0.1); 
        }
    }

    @FXML
    private void moreWidthAction(ActionEvent event) {
        if(resize){
            if(aspectRatio){
                shSel.setScaleY(shSel.getScaleY()+0.1);
            }
            shSel.setScaleX(shSel.getScaleX()+0.1); 
        }
    }

    @FXML
    private void blockAspectRatioAction(ActionEvent event) {
        if(blockAspectRatio.isSelected()){
            HeightHBox.setDisable(true);
            aspectRatio=true;
        }else{
            HeightHBox.setDisable(false);
            aspectRatio=false;
        }
    }

    @FXML
    private void lessHeightAction(ActionEvent event) {
        shSel.setScaleY(shSel.getScaleY()-0.1);
    }

    @FXML
    private void moreHeightAction(ActionEvent event) {
        shSel.setScaleY(shSel.getScaleY()+0.1);
    }

    @FXML
    private void moreSizeGridAction(ActionEvent event) {
        double width = Grid.getWidth();
        double height = Grid.getHeight();

        int columns = getColCount(Grid)+1;
        int rows = getRowCount(Grid)+1;
        
        double newWidth = width / columns;
        double newHeight = height / rows;
        
        Grid.getColumnConstraints().clear();
        Grid.getRowConstraints().clear();

        for (int i = 0; i < columns; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPrefWidth(newWidth);
            Grid.getColumnConstraints().add(cc);
        }
        for (int i = 0; i < rows; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setPrefHeight(newHeight);
            Grid.getRowConstraints().add(rc);
        }
        
    }

    @FXML
    private void lessSizeGridAction(ActionEvent event) {
        double width = Grid.getWidth();
        double height = Grid.getHeight();
        
        int columns = getColCount(Grid)-1;
        int rows = getRowCount(Grid)-1;
        
        double newWidth = width / columns;
        double newHeight = height / rows;
        
        Grid.getColumnConstraints().clear();
        Grid.getRowConstraints().clear();

        for (int i = 0; i < columns; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPrefWidth(newWidth);
            Grid.getColumnConstraints().add(cc);
        }
        for (int i = 0; i < rows; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setPrefHeight(newHeight);
            Grid.getRowConstraints().add(rc);
        }
    }

    
    
    /**
     * This method count Row on the gridPane
     * @param pane
     * @return 
     */
    private int getRowCount(GridPane pane) {
        int numRows = pane.getRowConstraints().size();
        for (int i = 0; i < pane.getChildren().size(); i++) {
            Node child = pane.getChildren().get(i);
            if (child.isManaged()) {
                Integer rowIndex = GridPane.getRowIndex(child);
                if(rowIndex != null){
                    numRows = Math.max(numRows,rowIndex+1);
                }
            }
        }
        System.out.println("numRows "+numRows);
        return numRows;
    }
    
    private int getColCount(GridPane pane) {
        int numCol = pane.getColumnConstraints().size();
        for (int i = 0; i < pane.getChildren().size(); i++) {
            Node child = pane.getChildren().get(i);
            if (child.isManaged()) {
                Integer colIndex = GridPane.getColumnIndex(child);
                if(colIndex != null){
                    numCol = Math.max(numCol,colIndex+1);
                }
            }
        }
        System.out.println("numCol "+numCol);
        return numCol;
    }
    
    
    
}
