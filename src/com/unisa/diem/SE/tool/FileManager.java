/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GBCuomo
 */
public class FileManager {
    private ArrayList<Shapes> myArray;
    public FileManager(){
        this.myArray = new ArrayList<>();
    }

    public void saveFile(String file) {
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)))){
            for(Shapes s : myArray){
                out.print(s);
            }
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void loadFile(String file){

        try(Scanner in = new Scanner(new BufferedInputStream(new FileInputStream(file)))){
            in.useLocale(Locale.US);
            in.useDelimiter("\n");
            while(in.hasNext()){
                myArray.clear();
                Shapes s = new Shapes();
                myArray.add(s);

            }
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
