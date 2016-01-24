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
        btclear.setAction(act);
    
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
        tclear = new org.components.controls.CButton();
        btclear = new org.components.controls.CButton();
        tprint = new org.components.controls.CButton();
        btgotoGrid = new org.components.controls.CButton();
        tdelete = new org.components.controls.CButton();
        btclear1 = new org.components.controls.CButton();

        setLayout(null);

        btsave.setText("Save");
        btsave.setPreferredSize(new java.awt.Dimension(30, 20));
        btsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsaveActionPerformed(evt);
            }
        });
        add(btsave);
        btsave.setBounds(0, 0, 60, 30);

        tclear.setText("Clear");
        tclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tclearActionPerformed(evt);
            }
        });
        add(tclear);
        tclear.setBounds(140, 0, 70, 30);

        btclear.setText("Copy");
        btclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btclearActionPerformed(evt);
            }
        });
        add(btclear);
        btclear.setBounds(280, 0, 70, 30);

        tprint.setText("Print");
        tprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tprintActionPerformed(evt);
            }
        });
        add(tprint);
        tprint.setBounds(350, 0, 70, 30);

        btgotoGrid.setText("Goto Grid >");
        add(btgotoGrid);
        btgotoGrid.setBounds(430, 0, 80, 30);

        tdelete.setText("Delete");
        add(tdelete);
        tdelete.setBounds(60, 0, 80, 30);

        btclear1.setText("New");
        btclear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btclear1ActionPerformed(evt);
            }
        });
        add(btclear1);
        btclear1.setBounds(210, 0, 70, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void btclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btclearActionPerformed
       
    }//GEN-LAST:event_btclearActionPerformed

    private void tclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tclearActionPerformed
        control.clear();// TODO add your handling code here:
    }//GEN-LAST:event_tclearActionPerformed

    private void tprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tprintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tprintActionPerformed

    private void btsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btsaveActionPerformed

    private void btclear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btclear1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btclear1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton btclear;
    private org.components.controls.CButton btclear1;
    private org.components.controls.CButton btgotoGrid;
    private org.components.controls.CButton btsave;
    private org.components.controls.CButton tclear;
    private org.components.controls.CButton tdelete;
    private org.components.controls.CButton tprint;
    // End of variables declaration//GEN-END:variables
}
