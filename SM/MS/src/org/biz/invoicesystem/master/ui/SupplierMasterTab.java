 
package org.biz.invoicesystem.master.ui;

import javax.swing.JPanel;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.service.master.CustomerService;
import org.components.windows.TabPanelUI;

/**
 *
 * @author Administrator
 */
public class SupplierMasterTab extends TabPanelUI {

  
    @Override
    public void init() {
  
        try {
    supplerMasterUI1.init();
//    supplierListUi1.init();
        } catch (Exception e) {
        e.printStackTrace();
        }
        
    }
      public SupplierMasterTab() {
        initComponents();
   init();
      }

    @Override
    public void updateEntityUI() {
        jTabbedPane1.setSelectedIndex(jTabbedPane1.indexOfTab("List"));
        
    //    System.out.println("index zero addedd.....");
    }
     
      
      public void events() {
          System.out.println("running customer master tab..");
    }
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tItemDescription = new org.components.controls.CTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        supplerMasterUI1 = new org.biz.invoicesystem.master.ui.SupplerMasterUI();

        jTabbedPane1.addTab("Form", supplerMasterUI1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 792, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.biz.invoicesystem.master.ui.SupplerMasterUI supplerMasterUI1;
    private org.components.controls.CTextField tItemDescription;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return "Supplier Master";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }
}
