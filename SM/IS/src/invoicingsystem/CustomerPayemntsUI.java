/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CustomerStatementUI.java
 *
 * Created on Aug 28, 2011, 8:13:50 PM
 */
package invoicingsystem;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.entity.transactions.CustomerStatement;
import org.biz.invoicesystem.service.transactions.CustomerStatementService;
import org.components.windows.TabPanelUI;

/**
 *
 * @author nnjj
 */
public class CustomerPayemntsUI extends TabPanelUI {

    int x;
    Customer cust;
    CustomerStatementService service;
    List<CustomerStatement> listStatements;

    /** Creates new form CustomerStatementUI */
    public CustomerPayemntsUI() {
        initComponents();
        init();
    }

    @Override
    public void init() {

        cust=new Customer();
        listStatements=new ArrayList<CustomerStatement>();
        service =new CustomerStatementService();
        controlPanel1.setCrudController(this);
        
        super.init();
    }

    @Override
    public void save() {

        service.getDao().save(null);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlPanel1 = new com.components.custom.ControlPanel();
        cTextField1 = new org.components.controls.CTextField();

        cTextField1.setText("cTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(cTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(controlPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(controlPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(429, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CTextField cTextField1;
    private com.components.custom.ControlPanel controlPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return "customer statement";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }
}
/*

 applying cash cheque bank and credit card functianlity
 * 
 */