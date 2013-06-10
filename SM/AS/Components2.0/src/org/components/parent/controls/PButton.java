/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * orgbutton.java
 *
 * Created on May 11, 2010, 9:51:09 PM
 */

package org.components.parent.controls;

import app.utils.SystemUtil;
import javax.swing.JButton;
import org.biz.app.ui.util.ICommand;

/**
 *
 * @author nano
 */
public class PButton extends JButton {

    private String id;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    /** Creates new form BeanForm */
    public PButton() {
        initComponents();
        id=SystemUtil.getKeyStr();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setText("Button");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
