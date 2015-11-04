 
package org.biz.invoicesystem.master.ui;

import javax.swing.JPanel;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.service.master.CustomerService;
import org.components.windows.TabPanelUI;

/**
 *
 * @author Administrator
 */
public class CustomerMasterTab extends TabPanelUI {

     
        private CustomerService cService;
  
 // List<Customer> customers;
  
  private Customer selectedCus;

    @Override
    public void init() {
  
        try {
           
   cService=new CustomerService();
   //customers=new ArrayList<Customer>();
  // customer=new Customer();
   
   
        } catch (Exception e) {
        e.printStackTrace();
        }
        
    }
      public CustomerMasterTab() {
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
        customerMasterUI21 = new org.biz.invoicesystem.master.ui.CustomerMasterUI2();
        customerListUi1 = new org.biz.invoicesystem.ui.list.master.CustomerListUi();

        jTabbedPane1.addTab("Form", customerMasterUI21);
        jTabbedPane1.addTab("List", customerListUi1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.biz.invoicesystem.ui.list.master.CustomerListUi customerListUi1;
    private org.biz.invoicesystem.master.ui.CustomerMasterUI2 customerMasterUI21;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.components.controls.CTextField tItemDescription;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return "Item Master";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }
}
