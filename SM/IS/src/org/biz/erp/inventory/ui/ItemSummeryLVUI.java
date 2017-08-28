/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.erp.inventory.ui;

import java.util.ArrayList;
import java.util.List;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.entity.master.SKU;
import org.biz.invoicesystem.entity.master.UOM;
import org.components.parent.controls.PTableColumn;
import org.components.windows.ListViewUI;

/**
 *
 * @author jawath
 */
public class ItemSummeryLVUI extends ListViewUI {

    /**
     * Creates new form ItemSummeryLVUI
     */
    public ItemSummeryLVUI() {
        super();
        List<PTableColumn> tblCols = new ArrayList();
        tblCols.add(new PTableColumn(String.class, "SKU"));
        tblCols.add(new PTableColumn(String.class, "Item "));
        tblCols.add(new PTableColumn(String.class, "UOM"));
        tblCols.add(new PTableColumn(String.class, "QTY"));

        getTable().init(InventoryJournalLine.class, tblCols);
        
    }

    @Override
    public Object[] getTableData(Object row) {
        Object [] sum =(Object[])row;
        SKU sumx = (SKU) sum[0];
        UOM uom = (UOM)sum[1];
        Double qty = (Double)sum[2];
        String[] arra= new String[sum.length];
        arra[0] = sumx !=null?sumx.getCode() :"";
        arra[1] = uom !=null?uom.getCode() :"";
        arra[2] = qty !=null? String.valueOf(qty) :"0";
        return arra;
    }
    
    
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 574, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
