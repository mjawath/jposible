/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.util;

import javax.swing.SwingWorker;


public class CommandTask extends SwingWorker<Object, Object>{

    public ICommand command;
   
    public CommandTask() {
    }
    
    
    @Override
    protected Object doInBackground() throws Exception {
        return command.executeTask();
    }
    
    @Override
    protected void done() {
        Object obj=null;
        try {
            obj=get();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Getting result ");
        command.resultTask(obj);
    }

}
