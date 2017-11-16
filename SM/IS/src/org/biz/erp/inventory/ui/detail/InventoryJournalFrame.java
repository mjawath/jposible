/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.erp.inventory.ui.detail;

import org.components.windows.DetailPanel;
import org.components.windows.MasterViewUI;
import org.components.windows.UIController;
import org.components.windows.UIFrame;

/**
 *
 * @author jawa
 */
public class InventoryJournalFrame extends  UIFrame {

    /**
     * Creates new form SupplierFrame
     */
    public InventoryJournalFrame() {
        super();
        init();
    }
    
    public void init(){
        initComponents();
        setTabbedPane(jTabbedPane1);
        super.init();
    
      
    }
    //todo
    public void initPAging(UIController con){
        itemInventorySummary1.setController(con);
        itemInventorySummary1.init();
//        itemInventorySummary1.setQueryMananger(((InventoryJournalController) con).getQuerySummer());

    }
    
    public MasterViewUI getMaster() {
        return inventoryJournalListViewUI1;
    }

    public DetailPanel getDetail() {
        return inventoryJournalUI1;
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        inventoryJournalUI1 = new org.biz.erp.inventory.ui.detail.InventoryJournalUI();
        inventoryJournalListViewUI1 = new org.biz.erp.inventory.ui.list.InventoryJournalListViewUI();
        itemInventorySummary1 = new org.biz.erp.inventory.ui.ItemInventorySummary();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout inventoryJournalUI1Layout = new javax.swing.GroupLayout(inventoryJournalUI1);
        inventoryJournalUI1.setLayout(inventoryJournalUI1Layout);
        inventoryJournalUI1Layout.setHorizontalGroup(
            inventoryJournalUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 808, Short.MAX_VALUE)
        );
        inventoryJournalUI1Layout.setVerticalGroup(
            inventoryJournalUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab3", inventoryJournalUI1);

        javax.swing.GroupLayout inventoryJournalListViewUI1Layout = new javax.swing.GroupLayout(inventoryJournalListViewUI1);
        inventoryJournalListViewUI1.setLayout(inventoryJournalListViewUI1Layout);
        inventoryJournalListViewUI1Layout.setHorizontalGroup(
            inventoryJournalListViewUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 808, Short.MAX_VALUE)
        );
        inventoryJournalListViewUI1Layout.setVerticalGroup(
            inventoryJournalListViewUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab4", inventoryJournalListViewUI1);

        javax.swing.GroupLayout itemInventorySummary1Layout = new javax.swing.GroupLayout(itemInventorySummary1);
        itemInventorySummary1.setLayout(itemInventorySummary1Layout);
        itemInventorySummary1Layout.setHorizontalGroup(
            itemInventorySummary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 808, Short.MAX_VALUE)
        );
        itemInventorySummary1Layout.setVerticalGroup(
            itemInventorySummary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab3", itemInventorySummary1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InventoryJournalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InventoryJournalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InventoryJournalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InventoryJournalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InventoryJournalFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.biz.erp.inventory.ui.list.InventoryJournalListViewUI inventoryJournalListViewUI1;
    private org.biz.erp.inventory.ui.detail.InventoryJournalUI inventoryJournalUI1;
    private org.biz.erp.inventory.ui.ItemInventorySummary itemInventorySummary1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
