/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.ui.prototype;

import org.biz.app.ui.util.UIListener;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.components.windows.MasterViewUI;

/**
 *
 * @author user
 */
public class SalesInvoiceMasterUI extends MasterViewUI<SalesInvoice> implements UIListener{

 
      
   
    /**
     * Creates new form SalesOverviewPanel
     */
    public SalesInvoiceMasterUI() {
        initComponents();

        init();

    }

       
    @Override
    public void init() {
        listUI = salesInvoiceListUI1;
        searchQueryUI = salesSearchUI1;
        super.init();

    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        salesInvoiceListUI1 = new org.biz.ui.prototype.SalesInvoiceListUI();
        salesSearchUI1 = new org.biz.ui.prototype.SalesSearchUI();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(salesSearchUI1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(salesInvoiceListUI1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(salesSearchUI1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salesInvoiceListUI1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
     
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.biz.ui.prototype.SalesInvoiceListUI salesInvoiceListUI1;
    private org.biz.ui.prototype.SalesSearchUI salesSearchUI1;
    // End of variables declaration//GEN-END:variables
    
  
    

        
    public void showDetailView(){
        Object si = listUI.getTable().getSelectedObject();
//        sc.showDetailView(si);
    }

}
