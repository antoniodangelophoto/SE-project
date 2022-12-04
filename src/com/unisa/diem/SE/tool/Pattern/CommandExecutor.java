/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.diem.SE.tool.Pattern;

import com.unisa.diem.SE.tool.Pattern.Command;
import java.util.*;

/**
 *
 * @author Francesco
 */
public class CommandExecutor {
    
    private Deque<Command> stack;
    public CommandExecutor(){
        stack = new ArrayDeque<>();
    }
    public void execute(Command command){
        stack.addLast(command);
        command.execute();
    }
    public void undoLast(){
        Command last = stack.removeLast();
        last.undo();
    }
    
}
