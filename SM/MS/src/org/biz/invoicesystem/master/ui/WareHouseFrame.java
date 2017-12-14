/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.master.ui;

import org.biz.invoicesystem.service.master.WareHouseService;
import org.components.windows.DetailPanel;
import org.components.windows.MasterViewUI;
import org.components.windows.UIFrame;

/**
 *
 * @author Jawad
 */
public class WareHouseFrame extends UIFrame {

    
    
//    WareHouseService whs = new WareHouseService();
  
  
    /**
     * Creates new form WareHouseFrame
     */
    public WareHouseFrame() {
        super();
        init();      
    }
    public void init(){
        initComponents();
//        setTabbedPane(tabbedPane1);
        super.init();

    }
    public MasterViewUI getMaster() {
        return wareHouseListUI1;
    }

    public DetailPanel getDetail() {
        return wareHouseUI1;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        wareHouseListUI1 = new org.biz.invoicesystem.master.ui.WareHouseListUI();
        wareHouseUI1 = new org.biz.invoicesystem.master.ui.WareHouseUI();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout wareHouseListUI1Layout = new javax.swing.GroupLayout(wareHouseListUI1);
        wareHouseListUI1.setLayout(wareHouseListUI1Layout);
        wareHouseListUI1Layout.setHorizontalGroup(
            wareHouseListUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );
        wareHouseListUI1Layout.setVerticalGroup(
            wareHouseListUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 504, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout wareHouseUI1Layout = new javax.swing.GroupLayout(wareHouseUI1);
        wareHouseUI1.setLayout(wareHouseUI1Layout);
        wareHouseUI1Layout.setHorizontalGroup(
            wareHouseUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 333, Short.MAX_VALUE)
        );
        wareHouseUI1Layout.setVerticalGroup(
            wareHouseUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(wareHouseListUI1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(wareHouseUI1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(wareHouseListUI1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(wareHouseUI1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private org.biz.invoicesystem.master.ui.WareHouseListUI wareHouseListUI1;
    private org.biz.invoicesystem.master.ui.WareHouseUI wareHouseUI1;
    // End of variables declaration//GEN-END:variables
}
