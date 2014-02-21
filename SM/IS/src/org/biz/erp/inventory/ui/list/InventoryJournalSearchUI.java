/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.erp.inventory.ui.list;

import org.biz.app.ui.util.QueryManager;
import org.biz.dao.service.CQuery;
import org.components.windows.SearchQueryUIPanel;

/**
 *
 * @author jawath
 */
public class InventoryJournalSearchUI  extends SearchQueryUIPanel {

    /**
     * Creates new form InventoryJournalSearchUI
     */
    public InventoryJournalSearchUI() {
        super();
        initComponents();
    }

    QueryManager qm=new QueryManager() {

            @Override
            public CQuery getCQuery() {
                if(getService()==null)return null;
                
                return getService().getQueryByCodeLike("");
            }
        
        };
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
