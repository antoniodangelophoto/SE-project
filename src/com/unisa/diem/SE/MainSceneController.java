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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;

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
    private ComboBox<?> Color;
    @FXML
    private Button selectionMode;
    @FXML
    private MenuItem Import;
    @FXML
    private MenuItem Export;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadFile(ActionEvent event) {
    }

    @FXML
    private void exportFile(ActionEvent event) {
    }

    @FXML
    private void rectangleMode(ActionEvent event) {
    }

    @FXML
    private void ellipseMode(ActionEvent event) {
    }

    @FXML
    private void lineMode(ActionEvent event) {
    }

    @FXML
    private void selectionMode(ActionEvent event) {
    }
    
}
