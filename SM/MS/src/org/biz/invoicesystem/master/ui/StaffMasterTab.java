 
package org.biz.invoicesystem.master.ui;

import javax.swing.JPanel;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.service.master.CustomerService;
import org.components.windows.TabPanelUI;

/**
 *
 * @author Administrator
 */
public class StaffMasterTab extends TabPanelUI {

    
   
    @Override
    public void init() {
  
        try {
           
   staffListUi1.init();
   staffMasterUI1.init();
   
        } catch (Exception e) {
        e.printStackTrace();
        }
        
    }
      public StaffMasterTab() {
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
        staffListUi1 = new org.biz.invoicesystem.ui.list.master.StaffListUi();
        staffMasterUI1 = new org.biz.invoicesystem.master.ui.StaffMasterUI();

        jTabbedPane1.addTab("List", staffListUi1);
        jTabbedPane1.addTab("Form", staffMasterUI1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.biz.invoicesystem.ui.list.master.StaffListUi staffListUi1;
    private org.biz.invoicesystem.master.ui.StaffMasterUI staffMasterUI1;
    private org.components.controls.CTextField tItemDescription;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return "Staff Master";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }
}
