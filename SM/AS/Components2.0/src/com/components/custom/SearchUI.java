/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.components.custom;

import org.components.windows.SearchQueryUIPanel;

/**
 *
 * @author Jawad
 */
public class SearchUI<T> extends  SearchQueryUIPanel {

    
//    private ItemQueryManger iqm= new ItemQueryManger(this);
    
    /**
     * Creates new form ItemSUI
     */
    public SearchUI() {
//        initComponents();
//        init();
//        qm = new myQM();
//        qms = qm;
//        initSearchTextField();
        
//        removeAll();
//        setLayout(BorderLayout);
    }
    
   
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ttxtsearch = new org.components.controls.CTextField();
        tbtnSearch = new javax.swing.JButton();

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
                .addContainerGap()
                .addComponent(ttxtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbtnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ttxtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbtnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnSearchActionPerformed

        
//
        //        command.start(0);
    }//GEN-LAST:event_tbtnSearchActionPerformed

     
    
     
   public void gotoPos(int on){}
      
    
           
    
     
     public void doSearch(){
     }
     
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton tbtnSearch;
    private org.components.controls.CTextField ttxtsearch;
    // End of variables declaration//GEN-END:variables
}
