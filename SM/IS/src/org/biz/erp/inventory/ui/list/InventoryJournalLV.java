/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.erp.inventory.ui.list;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.components.parent.controls.PTableColumn;
import org.components.windows.ListViewUI;

/**
 *
 * @author jawath
 */
public class InventoryJournalLV extends ListViewUI {

    /**
     * Creates new form InventoryJournalLV
     */
    public InventoryJournalLV() {
        super();
        
        List<PTableColumn> tblCols = new ArrayList();
        tblCols.add(new PTableColumn(String.class, "ID"));
        tblCols.add(new PTableColumn(String.class, "Code"));
        tblCols.add(new PTableColumn(Date.class, "Creation time"));
        tblCols.add(new PTableColumn(Date.class, "update time"));

        getTable().init(InventoryJournal.class, tblCols);
        
    }

    
      @Override
        public Object[] getTableData(Object row) {
            InventoryJournal item = (InventoryJournal) row;
            return new Object[]{item, item.getId(), item.getCode(), item.getSavedDate(), item.getEditedDate()};
        }
        
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
