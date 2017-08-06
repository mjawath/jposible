/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CustomerListUi.java
 *
 * Created on Dec 3, 2011, 4:54:01 PM
 */
package org.biz.invoicesystem.ui.list.master;

import org.biz.invoicesystem.entity.master.Customer;
import org.components.windows.MasterViewUI;

public class CustomerListUi extends MasterViewUI<Customer> {

    
    public CustomerListUi() {
//        init();
    }
    
    @Override
    public void init() {
        initComponents();
        listUI = customerLV1;
        searchQueryUI = customerSUI1;
        super.init();

    }

    

    ///////////////////////////////////////////////////// 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        customerLV1 = new org.biz.invoicesystem.ui.list.master.CustomerLV();
        customerSUI1 = new org.biz.invoicesystem.ui.list.master.CustomerSUI();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(customerLV1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(customerSUI1, javax.swing.GroupLayout.DEFAULT_SIZE, 1128, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(customerSUI1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(customerLV1, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.biz.invoicesystem.ui.list.master.CustomerLV customerLV1;
    private org.biz.invoicesystem.ui.list.master.CustomerSUI customerSUI1;
    // End of variables declaration//GEN-END:variables
}
