/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.windows;

import com.components.custom.ActionTask;
import java.awt.Component;
import java.awt.Container;
import java.awt.DefaultFocusTraversalPolicy;
import org.components.containers.CPanel;
import org.components.controls.CTextField;
import org.components.parent.controls.PFormattedTextField;

/**
 *
 * @author jawa
 */

public class MyFocusPolicy extends DefaultFocusTraversalPolicy {

    @Override
    public Component getComponentAfter(Container aContainer, Component aComponent) {
    
        //if component has its action listener 
        // then fire that
        // get the result from that and got to that
       // other vise 
        //if container cpanel 
        //then if it has next component then move to that 
        // or else goto parent
        // and try focusing to next        
        
//        if(aComponent instanceof PTextField ){
//            
//            ActionTask at = ((PTextField)aComponent).getActionTask();
//            if(at!=null){
//                Object com = at.actionFired(aComponent);
//                if(com!=null && com instanceof Component){
//                    return (Component)com;
//                }
//            }            
//            
//        }else
//        
//        if (aComponent instanceof PFormattedTextField) {
//
//            ActionTask at = ((PFormattedTextField) aComponent).getActionTask();
//            if (at != null) {
//                Object com = at.actionFired(aComponent);
//                if (com != null && com instanceof Component) {
//                    return (Component) com;
//                }
//            }
//
//        }
        
        
        return gotToNextComponent(aComponent);
        
//         super.getComponentAfter(aContainer, aComponent); //To change body of generated methods, choose Tools | Templates.
    } 
    
    
    
    @Override
    public Component getComponentBefore(Container aContainer, Component aComponent) {

        //if component has its action listener 
        // then fire that
        // get the result from that and got to that
        // other vise 
        //if container cpanel 
        //then if it has next component then move to that 
        // or else goto parent
        // and try focusing to next        
        if (aComponent instanceof CTextField) {

            ActionTask at = ((CTextField) aComponent).getActionTask();
            if (at != null) {
                Object com = at.actionFired(aComponent);
                if (com != null && com instanceof Component) {
                    return (Component) com;
                }
            }

        }

        if (aComponent instanceof PFormattedTextField) {

            ActionTask at = ((PFormattedTextField) aComponent).getActionTask();
            if (at != null) {
                Object com = at.actionFired(aComponent);
                if (com != null && com instanceof Component) {
                    return (Component) com;
                }
            }

        }

        return gotToPreComponent(aComponent);

//         super.getComponentAfter(aContainer, aComponent); //To change body of generated methods, choose Tools | Templates.
    }

    
//    public void goToNextFocusableComponent(Component aComponent){
//        Component parent = aComponent.getParent();
//        while (parent != null) {
//            if ((parent instanceof CPanel) && ((CPanel) parent).hasFocusableComponents()) {
//                break;
//            }
//            parent = parent.getParent();
//        }  
//    }
    
    

    public Component gotToNextComponent(Component aComponent) {
        CPanel parent = getFocusableParent(aComponent);
        if (parent == null) {
            //frame first
            if (aComponent instanceof CPanel) {
                return ((CPanel) aComponent).gotoFirstComponent();
            }
            return null;
        }
        
        
        if (parent != null) {

            final Component gotoNextComponentNew = ((CPanel) parent).gotoNextComponentInThisPanel(aComponent);
            if (gotoNextComponentNew != null) {
                if (gotoNextComponentNew instanceof CPanel) {
                    return ((CPanel) gotoNextComponentNew).gotoFirstComponent();
                } else {
                    return gotoNextComponentNew;

                }
            } else {                
                return gotToNextComponent(parent);

            }

        }
        
        
        return null;
    }
    //get the parent
    //check whther the parent has any  focusable

    public CPanel getFocusableParent(Component com) {
        //get Focuable parent container
        Component parent = com.getParent();
        if (parent == null) {
            return null;
        }
        if (!(parent instanceof CPanel)) {
            return getFocusableParent(parent);
        }
        while (parent instanceof CPanel) {
            if (((CPanel) parent).hasFocusableComponents()) {
                return (CPanel) parent;
            }
            parent = parent.getParent();
        }
        return null;

    }

    
 
    
    
    public Component gotToPreComponent(Component aComponent) {
        CPanel parent = getFocusableParent(aComponent);
        if (parent == null) {
            //frame first
            if (aComponent instanceof CPanel) {
                return ((CPanel) aComponent).gotoFirstComponent();
            }
            return null;
        }

        if (parent != null) {

            final Component gotoNextComponentNew = ((CPanel) parent).gotoPreComponentInThisPanel(aComponent);
            if (gotoNextComponentNew != null) {
                if (gotoNextComponentNew instanceof CPanel) {
                    return ((CPanel) gotoNextComponentNew).gotoFirstComponent();
                } else {
                    return gotoNextComponentNew;

                }
            } else {
                return gotToPreComponent(parent);

            }

        }

        return null;

    }
   

}
