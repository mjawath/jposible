/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.erp.inventory.ui.list;

import org.biz.app.ui.util.QueryManager;
import org.biz.dao.service.CQuery;
import org.biz.invoicesystem.service.master.ItemService;
import org.components.windows.SearchQueryUIPanel;

/**
 *
 * @author Jawad
 */
public class ItemSUI extends  SearchQueryUIPanel {

    
    ItemService service= new ItemService();
    /**
     * Creates new form ItemSUI
     */
    public ItemSUI() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tsearch = new org.components.controls.CTextField();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(tsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(555, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(tsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

     private QueryManager searchListener = new QueryManager() {
        
        @Override
        public CQuery getCQuery() { 
            
            // 
            
//            String qry = "  c.code " + " like " + " ?1 ";//" where c."+myfield+" "+ myoperator +" ?1 ";
            return service.getQueryByCodeLike(tsearch.getText());
        }
        public CQuery getCountQuery() {

            return service.getCountQueryByCodeLike(tsearch.getText());
        }
        
        
    };
     
     public void doSearch(){
     }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CTextField tsearch;
    // End of variables declaration//GEN-END:variables
}
