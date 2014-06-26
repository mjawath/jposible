/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.util;

import java.util.ArrayList;

public class Command implements ICommand{

    private ICommand command;
    public ArrayList objs;
    public ArrayList result;

    public Command(ICommand command) {
        this.command = command;
        objs = new ArrayList();
        result = new ArrayList();
    } 
    
    public Command() {
        command = this;
        objs = new ArrayList();
        result = new ArrayList();
    }

    public void invoke() {
        CommandTask com = new CommandTask(command);
        objs.clear();
    }

    public void setParam(Object object) {
        objs.add(object);
    }

    public void setView(Object object) {
        result.add(object);
    }
    
    public ArrayList getParam(){
    return objs;
    }

    @Override
    public Object executeTask() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void resultTask(Object objs) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public  ArrayList getParams(){
    return objs;
    }
    
    
    
    public  ArrayList getResults(){
    return result;
    }

}