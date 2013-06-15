/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.biz.app.ui.util.Command;
import org.biz.app.ui.util.ICommand;

/**
 *
 * @author d
 */
public class ButtonAction implements ActionListener ,ICommand{

    Command command=new Command(this); 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        command.invoke();
    }

    @Override
    public Object executeTask() {
        return null;
    }

    @Override
    public void resultTask(Object objs) {
    }
    
}
