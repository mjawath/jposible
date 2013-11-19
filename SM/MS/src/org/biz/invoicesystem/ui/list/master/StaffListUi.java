 
package org.biz.invoicesystem.ui.list.master;

import javax.swing.JPanel;
import org.biz.invoicesystem.entity.master.Staff;
import org.components.windows.ListViewPanel;

 
public class StaffListUi extends ListViewPanel<Staff>   {

     public StaffListUi() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cItmcode = new org.components.controls.CTextField();
        cButton1 = new org.components.controls.CButton();
        cButton3 = new org.components.controls.CButton();
        cButton4 = new org.components.controls.CButton();
        cButton5 = new org.components.controls.CButton();
        cButton6 = new org.components.controls.CButton();
        cButton7 = new org.components.controls.CButton();
        cCheckBox1 = new org.components.controls.CCheckBox();

        jLabel1.setText("Staff Name Search");
        add(jLabel1);
        jLabel1.setBounds(10, 10, 90, 20);

        cItmcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cItmcodeActionPerformed(evt);
            }
        });
        add(cItmcode);
        cItmcode.setBounds(140, 10, 470, 25);

        cButton1.setText("New ");
        cButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton1ActionPerformed(evt);
            }
        });
        add(cButton1);
        cButton1.setBounds(40, 460, 121, 49);

        cButton3.setText("History");
        cButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton3ActionPerformed(evt);
            }
        });
        add(cButton3);
        cButton3.setBounds(300, 460, 121, 49);

        cButton4.setText("Delete");
        cButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton4ActionPerformed(evt);
            }
        });
        add(cButton4);
        cButton4.setBounds(170, 460, 121, 49);

        cButton5.setText("Bulk Email");
        cButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton5ActionPerformed(evt);
            }
        });
        add(cButton5);
        cButton5.setBounds(560, 460, 121, 49);

        cButton6.setText("Close");
        cButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton6ActionPerformed(evt);
            }
        });
        add(cButton6);
        cButton6.setBounds(690, 460, 110, 49);

        cButton7.setText("Bulk Sms");
        cButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton7ActionPerformed(evt);
            }
        });
        add(cButton7);
        cButton7.setBounds(430, 460, 121, 49);

        cCheckBox1.setText("Remove Selection");
        add(cCheckBox1);
        cCheckBox1.setBounds(680, 440, 120, 23);
    }// </editor-fold>//GEN-END:initComponents

    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton1ActionPerformed
        
    }//GEN-LAST:event_cButton1ActionPerformed

    private void cButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cButton3ActionPerformed

    private void cButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cButton4ActionPerformed

    private void cButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cButton5ActionPerformed

    private void cButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cButton6ActionPerformed

    private void cItmcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cItmcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cItmcodeActionPerformed

    private void cButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cButton7ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CButton cButton3;
    private org.components.controls.CButton cButton4;
    private org.components.controls.CButton cButton5;
    private org.components.controls.CButton cButton6;
    private org.components.controls.CButton cButton7;
    private org.components.controls.CCheckBox cCheckBox1;
    private org.components.controls.CTextField cItmcode;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
       return "Customer List";
    }

    @Override
    public JPanel getJPanel() {
    
        return this;    
    }
}
