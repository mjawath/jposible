/*
 * ControlPanel.java
 *
 * Created on Apr 2, 2012, 7:41:26 PM
 */
package com.components.custom;

import org.biz.app.ui.event.ButtonAction;

/**
 *
 * @author Jawath
 */
public class ControlPanel extends javax.swing.JPanel {

    /** Creates new form ControlPanel */
    public ControlPanel() {
        initComponents();
        btsave.addActionListener(comSave);
        tdelete.addActionListener(comDelete);
        tprint.addActionListener(comPrint);
    }
    
    private CrudControl control;
    private ButtonAction comSave=new ButtonAction(){

        @Override
        public Object executeTask(Object objs  ) {
            return control.saveX();
        }

        @Override
        public void resultTask(Object objs) {
            control.onSaveComplete(objs);
        }  
    };
    
    private ButtonAction comDelete=new ButtonAction(){

        @Override
        public Object executeTask(Object objs) {
             control.delete();
            return super.executeTask(objs);
        }

        @Override
        public void resultTask(Object objs) {
            control.onDeleteComplete(objs);
        }  
    };
    
    
    private ButtonAction comPrint=new ButtonAction(){

        @Override
        public Object executeTask(Object objs) {
             control.printPage();
            return super.executeTask(objs);
        }

    };
    

    public void setCrudController(CrudControl control) {
        this.control = control;
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

        setLayout(null);

        btsave.setText("Save");
        btsave.setPreferredSize(new java.awt.Dimension(30, 20));
        add(btsave);
        btsave.setBounds(0, 0, 50, 30);

        tclear.setText("Clear");
        tclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tclearActionPerformed(evt);
            }
        });
        add(tclear);
        tclear.setBounds(100, 0, 50, 30);

        btclear.setText("Copy");
        btclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btclearActionPerformed(evt);
            }
        });
        add(btclear);
        btclear.setBounds(150, 0, 50, 30);

        tprint.setText("Print");
        tprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tprintActionPerformed(evt);
            }
        });
        add(tprint);
        tprint.setBounds(200, 0, 50, 30);

        btgotoGrid.setText("Goto Grid >");
        add(btgotoGrid);
        btgotoGrid.setBounds(250, 0, 80, 30);

        tdelete.setText("Delete");
        add(tdelete);
        tdelete.setBounds(50, 0, 50, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void btclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btclearActionPerformed
       
    }//GEN-LAST:event_btclearActionPerformed

    private void tclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tclearActionPerformed
        control.clear();// TODO add your handling code here:
    }//GEN-LAST:event_tclearActionPerformed

    private void tprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tprintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tprintActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton btclear;
    private org.components.controls.CButton btgotoGrid;
    private org.components.controls.CButton btsave;
    private org.components.controls.CButton tclear;
    private org.components.controls.CButton tdelete;
    private org.components.controls.CButton tprint;
    // End of variables declaration//GEN-END:variables
}
