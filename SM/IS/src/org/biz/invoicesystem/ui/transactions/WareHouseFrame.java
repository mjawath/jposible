/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.ui.transactions;

import org.biz.invoicesystem.service.master.WareHouseService;

/**
 *
 * @author Jawad
 */
public class WareHouseFrame extends javax.swing.JFrame {

    
    
    WareHouseService whs = new WareHouseService();
  
  
    /**
     * Creates new form WareHouseFrame
     */
    public WareHouseFrame() {
        initComponents();
        wareHouseUI1.config();
        wareHouseUI1.setService(whs);
//        crudcontrolPanel.setCrudController(wareHouseUI1);
        wareHouseListUI1.config();
        wareHouseListUI1.setService(whs);
        wareHouseListUI1.setDetailPanel(wareHouseUI1);

    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        wareHouseUI1 = new org.biz.erp.inventory.ui.WareHouseUI();
        wareHouseListUI1 = new org.biz.erp.inventory.ui.list.WareHouseListUI();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout wareHouseUI1Layout = new javax.swing.GroupLayout(wareHouseUI1);
        wareHouseUI1.setLayout(wareHouseUI1Layout);
        wareHouseUI1Layout.setHorizontalGroup(
            wareHouseUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 855, Short.MAX_VALUE)
        );
        wareHouseUI1Layout.setVerticalGroup(
            wareHouseUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout wareHouseListUI1Layout = new javax.swing.GroupLayout(wareHouseListUI1);
        wareHouseListUI1.setLayout(wareHouseListUI1Layout);
        wareHouseListUI1Layout.setHorizontalGroup(
            wareHouseListUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 352, Short.MAX_VALUE)
        );
        wareHouseListUI1Layout.setVerticalGroup(
            wareHouseListUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(wareHouseListUI1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(wareHouseUI1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(wareHouseUI1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(wareHouseListUI1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
           
            java.util.logging.Logger.getLogger(WareHouseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WareHouseFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.biz.erp.inventory.ui.list.WareHouseListUI wareHouseListUI1;
    private org.biz.erp.inventory.ui.WareHouseUI wareHouseUI1;
    // End of variables declaration//GEN-END:variables
}
