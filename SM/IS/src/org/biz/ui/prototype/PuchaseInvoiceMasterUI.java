/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.ui.prototype;

import org.biz.app.ui.util.UIListener;
import org.biz.invoicesystem.entity.transactions.PurchaseInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.components.windows.MasterViewUI;

/**
 *
 * @author user
 */
public class PuchaseInvoiceMasterUI extends MasterViewUI<PurchaseInvoice> implements UIListener{

 
      
   
    /**
     * Creates new form SalesOverviewPanel
     */
    public PuchaseInvoiceMasterUI() {
        initComponents();

        init();

    }

       
    @Override
    public void init() {
        listUI = invoiceListUI1;
        searchQueryUI = SearchUI1;
        super.init();

    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        invoiceListUI1 = new org.biz.ui.prototype.PurchaseInvoiceListUI();
        SearchUI1 = new org.biz.ui.prototype.PurchaseSearchUI();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SearchUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(invoiceListUI1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(SearchUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invoiceListUI1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
     
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.biz.ui.prototype.PurchaseSearchUI SearchUI1;
    private org.biz.ui.prototype.PurchaseInvoiceListUI invoiceListUI1;
    // End of variables declaration//GEN-END:variables
    
  
    

        
    public void showDetailView(){
        Object si = listUI.getTable().getSelectedObject();
//        sc.showDetailView(si);
    }

}
