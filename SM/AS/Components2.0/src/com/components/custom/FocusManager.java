/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.components.custom;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author yy
 */
public class FocusManager {
    
    ArrayList<IComponent> focus=new ArrayList<>();
    private IComponent temCom;
    
    public void gotoNextComponent() {
        //get current focused compnentt
//        Component jc=KeyboardFocusManager.getCurrentKeyboardFocusManager().getPermanentFocusOwner();      
        // check temfocus owner
        if (temCom != null) {
            ((Component) temCom).requestFocus();
            temCom = null;
            return;
        }
        //get current focused component
        
        
        for (Iterator<IComponent> it = focus.iterator(); it.hasNext();) {
            IComponent iComponent = it.next();
            Component com = (Component) iComponent;
//            if(com instanceof CPanel)
            if (com.hasFocus()) {
                if(!it.hasNext()){
                    ((Component)focus.get(0)).requestFocus();
                    temCom=null;//
                    return;
                }
                IComponent ncom = it.next();
                Component mycom = (Component) ncom;
                if (mycom != null) {
                    mycom.requestFocus();
                    temCom=null;
                }
            }
        }
    }
    
    public void gotoPreviousComponent() {
        //get current focused compnentt
//        Component jc=KeyboardFocusManager.getCurrentKeyboardFocusManager().getPermanentFocusOwner();      
        // check temfocus owner
        if (temCom != null) {
            ((Component) temCom).requestFocus();
            temCom = null;
            return;
        }
        if(focus.size() ==1)return;
        for (int i = 1; i < focus.size(); i++) {            
            IComponent iComponent = focus.get(i);
            Component com = (Component) iComponent;
            if (com.hasFocus()) {
                IComponent ncom = focus.get(i-1);
                Component mycom = (Component) ncom;
                if (mycom != null) {
                    mycom.requestFocus();
                    temCom=null;//
                } 
            }
        }       
    }

    public void setTemCom(IComponent com){
    temCom =com; 
    }
    
    public void addToFocus(IComponent com){
        if(com==null)return;
        focus.add(com);
    }
    
    private IComponent  curcom;
    public void setCurrentCom(){
    
    }
    
    public IComponent getCurrentCom(){
    return curcom;
    }
    
}
