/*
 * ControlPanel.java
 *
 * Created on Apr 2, 2012, 7:41:26 PM
 */
package com.components.custom;

/**
 *
 * @author NUZAIR
 */
public class ControlPanel extends javax.swing.JPanel {

    /** Creates new form ControlPanel */
    public ControlPanel() {
        initComponents();
    }
    
    CrudControl control;

    public void setCrudController(CrudControl control) {
        this.control = control;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btsave = new javax.swing.JButton();
        btdelete = new javax.swing.JButton();
        btclear = new javax.swing.JButton();
        btgrid = new javax.swing.JButton();
        btclear1 = new javax.swing.JButton();

        setLayout(null);

        btsave.setText("Save");
        btsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsaveActionPerformed(evt);
            }
        });
        add(btsave);
        btsave.setBounds(0, 0, 57, 30);

        btdelete.setText("Delete");
        btdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdeleteActionPerformed(evt);
            }
        });
        add(btdelete);
        btdelete.setBounds(60, 0, 63, 30);

        btclear.setText("Copy");
        btclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btclearActionPerformed(evt);
            }
        });
        add(btclear);
        btclear.setBounds(200, 0, 70, 30);

        btgrid.setText("Goto Grid View >>");
        btgrid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btgridActionPerformed(evt);
            }
        });
        add(btgrid);
        btgrid.setBounds(280, 0, 130, 30);

        btclear1.setText("Clear");
        btclear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btclear1ActionPerformed(evt);
            }
        });
        add(btclear1);
        btclear1.setBounds(130, 0, 70, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void btsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsaveActionPerformed
    //run through Back Task not in EDT
        control.save();
    }//GEN-LAST:event_btsaveActionPerformed

    private void btdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdeleteActionPerformed
        control.delete();        // TODO add your handling code here:
    }//GEN-LAST:event_btdeleteActionPerformed

    private void btclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btclearActionPerformed
        control.clear();
    }//GEN-LAST:event_btclearActionPerformed

    private void btgridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btgridActionPerformed
        control.gotoList();        // TODO add your handling code here:
    }//GEN-LAST:event_btgridActionPerformed

    private void btclear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btclear1ActionPerformed

    }//GEN-LAST:event_btclear1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btclear;
    private javax.swing.JButton btclear1;
    private javax.swing.JButton btdelete;
    private javax.swing.JButton btgrid;
    private javax.swing.JButton btsave;
    // End of variables declaration//GEN-END:variables
}
