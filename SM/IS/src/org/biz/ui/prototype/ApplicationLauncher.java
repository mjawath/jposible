/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.ui.prototype;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.plaf.TabbedPaneUI;
import org.biz.invoicesystem.entity.master.Category;
import org.biz.invoicesystem.ui.list.master.CategoryController;
import org.biz.invoicesystem.ui.list.master.ItemController;
import org.components.windows.DetailPanel;
import org.components.windows.UIController;


/**
 *
 * @author user
 */
public class ApplicationLauncher extends javax.swing.JFrame {
    
    private ItemController ic ;
    private CategoryController cat;
    /**
     * Creates new form ApplicationLauncher
     */
    public ApplicationLauncher() {
        initComponents();

        ic= new ItemController();       
        ic.initUI();
        
        cat = new CategoryController();
        cat.initUI();

        
//        create menu for specific controller
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tMenuBar = new javax.swing.JMenuBar();
        tItem = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        tItemDetailMenuItem = new javax.swing.JMenuItem();
        tItemListMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        tMnueItemCategoryDetail = new javax.swing.JMenuItem();
        tMenuItemCategoryList = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tItem.setText("Master");

        jMenu4.setText("Item");

        tItemDetailMenuItem.setText("Item Detail");
        tItemDetailMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tItemDetailMenuItemActionPerformed(evt);
            }
        });
        jMenu4.add(tItemDetailMenuItem);

        tItemListMenuItem.setText("Item List");
        tItemListMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tItemListMenuItemActionPerformed(evt);
            }
        });
        jMenu4.add(tItemListMenuItem);

        tItem.add(jMenu4);

        jMenu1.setText("Category");

        tMnueItemCategoryDetail.setText("Category Detail");
        tMnueItemCategoryDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tMnueItemCategoryDetailActionPerformed(evt);
            }
        });
        jMenu1.add(tMnueItemCategoryDetail);

        tMenuItemCategoryList.setText("Category List");
        tMenuItemCategoryList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tMenuItemCategoryListActionPerformed(evt);
            }
        });
        jMenu1.add(tMenuItemCategoryList);

        tItem.add(jMenu1);

        tMenuBar.add(tItem);

        jMenu3.setText("Edit");
        tMenuBar.add(jMenu3);

        setJMenuBar(tMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1004, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 471, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void tItemDetailMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tItemDetailMenuItemActionPerformed
        
        
        showFrame("Item master", ic.getDetailView());
        
    }//GEN-LAST:event_tItemDetailMenuItemActionPerformed

    private void tItemListMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tItemListMenuItemActionPerformed
       
        showFrame("Item List", ic.getListView());
    }//GEN-LAST:event_tItemListMenuItemActionPerformed

    private void tMnueItemCategoryDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tMnueItemCategoryDetailActionPerformed
        showFrame("Category Detail", cat.getDetailView());
    }//GEN-LAST:event_tMnueItemCategoryDetailActionPerformed

    private void tMenuItemCategoryListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tMenuItemCategoryListActionPerformed
        showFrame("Category List", cat.getListView());
    }//GEN-LAST:event_tMenuItemCategoryListActionPerformed

    public void showFrame(String title,JComponent panel){
    
        JFrame fr = new JFrame(title);
        fr.setSize(1300,700);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.getContentPane().add(panel);
        fr.setVisible(true);
        fr.toFront();
    }
    
     
    private void addMenuItemToMenubar(UIController controller){
        DetailPanel tab =  controller.getDetailView();
        JMenuItem mi = new JMenuItem("my view");//get the
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
            }
        });
        
        
    }
    
    
    
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
            java.util.logging.Logger.getLogger(ApplicationLauncher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ApplicationLauncher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ApplicationLauncher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ApplicationLauncher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ApplicationLauncher().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu tItem;
    private javax.swing.JMenuItem tItemDetailMenuItem;
    private javax.swing.JMenuItem tItemListMenuItem;
    private javax.swing.JMenuBar tMenuBar;
    private javax.swing.JMenuItem tMenuItemCategoryList;
    private javax.swing.JMenuItem tMnueItemCategoryDetail;
    // End of variables declaration//GEN-END:variables
}
