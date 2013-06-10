/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.util;

import java.util.ArrayList;

public class Command {

    private ICommand command;
    public ArrayList objs;
    public ArrayList result;

    public Command(ICommand command) {
        this.command = command;
        objs = new ArrayList();
        result = new ArrayList();
    }

    public void invoke() {
       CommandTask com= new CommandTask();
       com.command = command;       
       com.execute();
    }

    public void setParam(Object object) {
        objs.add(object);
    }

    public void setView(Object object) {
        result.add(object);
    }

}