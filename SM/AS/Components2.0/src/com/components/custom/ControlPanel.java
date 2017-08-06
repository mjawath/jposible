/*
 * ControlPanel.java
 *
 * Created on Apr 2, 2012, 7:41:26 PM
 */
package com.components.custom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author Jawath
 */
public class ControlPanel extends javax.swing.JPanel {

    int x=0;
    static int xx=0;
    /** Creates new form ControlPanel */
    public ControlPanel() {
        initComponents();
//        tdelete.addActionListener(comDelete);
//        tprint.addActionListener(comPrint);
        
          AbstractAction actxx=new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("F%%%%%%%%%%%%%%%%%%%%%%%%5555555555"+x);
            }
        };
        x=xx++;        
//        ComponentFactory.setKeyAction(btsave, comSave, KeyEvent.VK_F5);
    }
    
    private CrudControl control;

    
    public void setSaveAction(Action act){
        btsave.setAction(act);    
    }
    
    
    public void setClearAction(Action act){
        btnClear.setAction(act);
    
    }
    public void setNewAction(Action act){
        btnNew.setAction(act);
    
    }
    public void setCopyAction(Action act){
        btnCopy.setAction(act);    
    }
    
    public void setDeleteAction(Action act){
        tdelete.setAction(act);
    
    }
    
    public void setPrintAction(Action act){
        tprint.setAction(act);    
    }
    
    public void setGotoAction(Action act) {
        btgotoGrid.setAction(act);
    }

   
    
    public void setCrudController(CrudControl control) {
        this.control = control;
    }

    
    public void addOnlyActionListener(ActionListener l) {
        listenerList.add(ActionListener.class, l);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btsave = new org.components.controls.CButton();
        btnNew = new org.components.controls.CButton();
        btnCopy = new org.components.controls.CButton();
        tprint = new org.components.controls.CButton();
        btgotoGrid = new org.components.controls.CButton();
        tdelete = new org.components.controls.CButton();
        btnClear = new org.components.controls.CButton();

        setLayout(null);

        btsave.setText("Save");
        btsave.setPreferredSize(new java.awt.Dimension(30, 20));
        btsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsaveActionPerformed(evt);
            }
        });
        add(btsave);
        btsave.setBounds(480, 0, 60, 30);

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        add(btnNew);
        btnNew.setBounds(410, 0, 70, 30);

        btnCopy.setText("Copy");
        btnCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopyActionPerformed(evt);
            }
        });
        add(btnCopy);
        btnCopy.setBounds(10, 0, 70, 30);

        tprint.setText("Print");
        tprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tprintActionPerformed(evt);
            }
        });
        add(tprint);
        tprint.setBounds(340, 0, 70, 30);

        btgotoGrid.setText("Goto Grid >");
        add(btgotoGrid);
        btgotoGrid.setBounds(240, 0, 100, 30);

        tdelete.setText("Delete");
        add(tdelete);
        tdelete.setBounds(80, 0, 80, 30);

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        add(btnClear);
        btnClear.setBounds(160, 0, 70, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopyActionPerformed
        System.out.println("coppy");
        Thread.dumpStack();
    }//GEN-LAST:event_btnCopyActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        control.clear();// TODO add your handling code here:
    }//GEN-LAST:event_btnNewActionPerformed

    private void tprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tprintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tprintActionPerformed

    private void btsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btsaveActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton btgotoGrid;
    private org.components.controls.CButton btnClear;
    private org.components.controls.CButton btnCopy;
    private org.components.controls.CButton btnNew;
    private org.components.controls.CButton btsave;
    private org.components.controls.CButton tdelete;
    private org.components.controls.CButton tprint;
    // End of variables declaration//GEN-END:variables
}
