/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.biz.app.ui.util.Command;
import org.components.controls.CButton;

/**
 *
 * @author d
 */
public class ButtonAction implements ActionListener {

    
    protected CButton btn;

    public ButtonAction() {
    
    }
    
        @Override
    public void actionPerformed(ActionEvent e) {
       final CButton btn = (CButton) e.getSource();
       final Command com= new Command() {
            @Override
            public Object executeTask() {
                getParams().add(btn);
                return ButtonAction.this.executeTask(getParams());
            }

            @Override
            public void resultTask(Object objs) {
                ButtonAction.this.resultTask(objs);
            }
        };
        com.invoke();
    }

    public Object executeTask(Object objs) {
     
        return null;
    }

    public void resultTask(Object objs) {
    }
    
    
}
