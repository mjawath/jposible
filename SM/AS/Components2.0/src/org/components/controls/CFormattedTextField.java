/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.controls;

/**
 *
 * @author jawa
 */
public class CFormattedTextField extends CTextField {
    /**
     * Creates new form CFormattedTextField
     */
    public CFormattedTextField() {
        super();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents

    
    public void setAlwaysEnterEvent(boolean  isAlways){
        isAlwaysFireEventOnEnter =isAlways;  
    }
    boolean isAlwaysFireEventOnEnter = true;
     
    public boolean isValidPositiveDoubleValue() {
        return (getDoubleValue()!=null && getDoubleValue()> 0);
    }
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
