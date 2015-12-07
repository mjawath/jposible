/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.ui.list.master;

import java.util.HashMap;
import java.util.Map;
import org.components.windows.SearchQueryUIPanel;
import static org.components.windows.SearchQueryUIPanel.QRY;

/**
 *
 * @author Jawad
 */
public class CustomerSUI extends  SearchQueryUIPanel {

    
    public static final int Listview_searchUIType=0;
    public static final int POPUP_searchUIType=1;
    private int searchUIType= Listview_searchUIType;
//    private ItemQueryManger iqm;
    
    
      
//    private ItemQueryManger iqm= new ItemQueryManger(this);
    
    /**
     * Creates new form ItemSUI
     */
    public CustomerSUI() {
        initComponents();
    }

    
    public Map<String, Object> getQueryParameterMap() {

        Map<String, Object> p = new HashMap<>();
        p.put(QRY, ttSearch.getText());
        return p;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        ttSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tbtnSearch = new javax.swing.JButton();

        jButton1.setText("jButton1");

        jLabel1.setText("Search by Customer Code /Name ");

        tbtnSearch.setText("Search");
        tbtnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(ttSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tbtnSearch)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ttSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(tbtnSearch))
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnSearchActionPerformed
    
        ((CustomerController)controller).executeSearchForCustom();
      
//      qms.executeToFirstPageTask();
        
    }//GEN-LAST:event_tbtnSearchActionPerformed
    
     
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton tbtnSearch;
    private javax.swing.JTextField ttSearch;
    // End of variables declaration//GEN-END:variables
}
