/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CPanel.java
 *
 * Created on Oct 7, 2010, 1:21:25 PM
 */

package org.components.containers;

import com.components.custom.IComponent;
import com.components.custom.IContainer;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import org.components.parent.containers.PPanel;
import org.components.parent.controls.editors.NEWED;

/**
 *
 * @author nano
 */
public class CPanel extends PPanel implements IContainer  {

    /** Creates new form BeanForm */
    public CPanel() {
        initComponents();
        focus = new ArrayList<IComponent>();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void callBackAction() {
    }

  @Override
    public void gotoNextComponent(Component jc) {
        //get current focused compnentt
//        Component jc=KeyboardFocusManager.getCurrentKeyboardFocusManager().getPermanentFocusOwner();
        focus.indexOf(jc);
        //find from compnent list
        int x=0;
        for (IComponent com : focus) {
            if(  com==jc){
            if(x>=0 && x< focus.size()-1){
                IComponent yx=focus.get(x+1);
            ((Component)yx).requestFocus();
            return;
            }
            }
            x++;
        }

    }
  

    public void addToFocus(IComponent com) {
        focus.add(com);
        com.setContainer(this);
    }

    private List<IComponent> focus;
}
