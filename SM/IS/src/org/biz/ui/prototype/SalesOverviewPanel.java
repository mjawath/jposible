/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.ui.prototype;

import java.util.List;
import org.biz.app.ui.util.QueryManager;
import org.biz.app.ui.util.UIListener;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.components.test.ResultPage;
import org.components.windows.MasterViewUI;
import org.components.windows.UIController;

/**
 *
 * @author user
 */
public class SalesOverviewPanel extends MasterViewUI<SalesInvoice> implements UIListener{

    private SalesInvoiceControler sc ;    
      
    private class myQM extends QueryManager {

        public Long executeCountQuery() {
            return ((SalesInvoiceControler)controller).getCountByCodeLike(tSearch.getText());
        }

        public List executeQuery(int page) {
            return ((SalesInvoiceControler)controller).getByCodeLike(page, tSearch.getText());
        }

    };
    private myQM qm;

    /**
     * Creates new form SalesOverviewPanel
     */
    public SalesOverviewPanel() {
        initComponents();

       listUI = salesInvoiceListUI1;

        qm = new myQM();
//        qm.setService(sc.getSalesService());

//        qm.addUIListener(this);
        init();
//        config();
    }

    @Override
    public void setController(UIController controller) {
        super.setController(controller); //To change body of generated methods, choose Tools | Templates.
        sc = (SalesInvoiceControler)controller;
    }

    
   
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnind = new org.components.controls.CButton();
        btnPre = new javax.swing.JButton();
        tSearch = new org.components.controls.CTextField();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        salesInvoiceListUI1 = new org.biz.ui.prototype.SalesInvoiceListUI();

        btnind.setText("Find");
        btnind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnindActionPerformed(evt);
            }
        });

        btnPre.setText("<");

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setText(">>");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnind, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLast)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLast, btnNext, btnPre});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLast)
                    .addComponent(btnPre)
                    .addComponent(btnNext))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnLast, btnNext, btnPre, btnind});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(salesInvoiceListUI1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(381, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(70, Short.MAX_VALUE)
                    .addComponent(salesInvoiceListUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnindActionPerformed

        String qryText = tSearch.getText();
//        create the count query
//        create the search query
        // set the page
        // set current page 


//        sc.findItem(qryText);
//        sc.executeToFirstPageTask();
        
        
    }//GEN-LAST:event_btnindActionPerformed

    
    
     
    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        qm.getNextPage();        
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        qm.getLastPage();
    }//GEN-LAST:event_btnLastActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private org.components.controls.CButton btnind;
    private javax.swing.JPanel jPanel1;
    private org.biz.ui.prototype.SalesInvoiceListUI salesInvoiceListUI1;
    private org.components.controls.CTextField tSearch;
    // End of variables declaration//GEN-END:variables
    
    public void updateUI(ResultPage ui) {        
    
        if(ui==null){
            //should clear ui
            return;
        }
        
       Object resultObj = ui.getResult();
       
       if(resultObj instanceof List){
           //overview set result /
           salesInvoiceListUI1.getTable().setModelCollection((List)resultObj);
           
       }
        
        
    }
    

        
    public void showDetailView(){
        Object si = salesInvoiceListUI1.getTable().getSelectedObject();
        sc.showDetailView(si);
    }

}
