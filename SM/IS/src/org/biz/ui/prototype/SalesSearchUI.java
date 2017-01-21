/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.ui.prototype;

import com.components.custom.SearchUI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import static org.components.windows.SearchQueryUIPanel.QRY;

/**
 *
 * @author jawa
 */
public class SalesSearchUI extends SearchUI<SalesInvoice> {
    
    
    /**
     * Creates new form SalesSearchUI
     */
    public SalesSearchUI() {
        super();
    }
    
    protected void init() {
//        UIType = Listview_searchUIType;
        super.init();      
        initComponents();
        tSearchType.setCollection(Arrays.asList(new String[]{"code", "customername"}));
    }
    
    public Map<String, Object> getQueryParameterMap() {
        Map<String, Object> p = new HashMap<>();
        p.put(QRY, ttSearch.getText());
        return p;
    }


    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbtnSearch = new javax.swing.JButton();
        ttSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tSearchType = new org.components.controls.CComboBox();

        tbtnSearch.setText("Search");
        tbtnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtnSearchActionPerformed(evt);
            }
        });

        jLabel1.setText("Search by Customer Code /Name ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(ttSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(tSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbtnSearch)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ttSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(tbtnSearch)
                    .addComponent(tSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnSearchActionPerformed

        
        
//        ((SalesInvoiceControler) controller).searchForCustomerInvoice();


        //      qms.executeToFirstPageTask();

    }//GEN-LAST:event_tbtnSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private org.components.controls.CComboBox tSearchType;
    private javax.swing.JButton tbtnSearch;
    private javax.swing.JTextField ttSearch;
    // End of variables declaration//GEN-END:variables
}
