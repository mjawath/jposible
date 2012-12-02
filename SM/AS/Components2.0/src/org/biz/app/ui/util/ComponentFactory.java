/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.app.ui.util;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author mjawath
 */
public class ComponentFactory extends org.components.util.ComponentFactory{
    static int x=0;
    
    public static void setKeyActxxion(JComponent component,Action escpli,int keycode){

        String xx="act"+ ++x;
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke( keycode,0), xx);

        component.getActionMap().put(xx, escpli);
    }

    public static void setKeyAction(JComponent component,Action escpli,String keycode){
        String xx="act"+ ++x;
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke( keycode), xx);

        component.getActionMap().put(xx, escpli);
    }

}
