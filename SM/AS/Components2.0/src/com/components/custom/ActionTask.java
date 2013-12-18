/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.components.custom;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;

/**
 *
 * @author nnjj
 */
/*
 * A custom Action task which will execute some actions before 
 * pass the focus to next component
 */
public class ActionTask extends AbstractAction{
    
    Object param;
    JComponent nextToFocus;

    public ActionTask(JComponent nextToFocus) {
        this.nextToFocus = nextToFocus;
    }

    public ActionTask() {
    }
    
    public ActionTask(Object obj) {
this.param=obj;
    }

    public boolean validate(){
    return true;
    }
    public boolean action(){
    
    return true;
    }

    public void actionCall(Object obj){

    }
    
    public void actionCall(){
        actionCall(null);
    }
    
    public void actionPerformed(ActionEvent e) {
        if(! validate()){
        return;
        };
        
        if(! action()){
            
        return;
        };
                     
    }
    
}
