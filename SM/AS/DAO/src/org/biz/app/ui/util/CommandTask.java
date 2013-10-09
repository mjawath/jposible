/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.util;

import java.util.ArrayList;
import javax.swing.SwingWorker;


public class CommandTask extends SwingWorker<Object, Object>{

    public ICommand command;
    private int state= NOT_STARTED;
    
    
    public static int EXCEPTION=0;
    public static int EXECUTION=1;
    public static int SUCCESS=2;
    public static int NOT_STARTED=-1;
   
    public CommandTask() {
    }
    
    public CommandTask(ICommand com) {
    command =com;
    execute();
    }
    
    @Override
    protected Object doInBackground() throws Exception {
        state = EXECUTION;
       return command.executeTask();
    }
    
    @Override
    protected void done() {
        Object obj=null;
        try {
            obj=get();
        }
        catch (Exception ex) {
          state= EXCEPTION;
            ex.printStackTrace();//if anything goes wrong an state should be set 
        }
        state=state==EXCEPTION?EXCEPTION:SUCCESS;
        command.resultTask(obj);
    }

}
