/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PLabel.java
 *
 * Created on Dec 27, 2010, 10:33:04 PM
 */

package org.components.parent.controls;

import javax.swing.JLabel;

/**
 *
 * @author mjawath
 */
public class PLabel extends JLabel {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    /** Creates new form BeanForm */
    public PLabel() {
        initComponents();
    }

    private  String valueOfText;

    public String getValueOfText() {
        return valueOfText;
    }

    public void setValueOfText(String valueOfText) {
        this.valueOfText = valueOfText;
        setText(valueOfText);
    }

    public void clear(){
    setText("");
    setToolTipText(null);
    } 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setText("Custom Text");
    }// </editor-fold>//GEN-END:initComponents




    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
