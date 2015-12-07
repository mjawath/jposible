/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.ui.list.master;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.biz.invoicesystem.entity.master.Item;
import org.components.parent.controls.PTableColumn;
import org.components.windows.ListViewUI;

/**
 *
 * @author Jawad
 */
@Deprecated
public class ItemListViewUI extends ListViewUI {

    /**
     * Creates new form ItemListViewUI
     */
    public ItemListViewUI() {
        super();
        
        List<PTableColumn> tblCols = new ArrayList();
        tblCols.add(new PTableColumn(String.class, "ID"));
        tblCols.add(new PTableColumn(String.class, "Code"));
        tblCols.add(new PTableColumn(String.class, "Description"));
        tblCols.add(new PTableColumn(Date.class, "savedDate"));
        tblCols.add(new PTableColumn(Date.class, "editedDate"));
        

        getTable().init(Item.class, tblCols);
    }
    
        
    @Override
    public Object[] getTableData(Object row) {
        Item item = (Item) row;
        return new Object[]{item, item.getCode(), item.getDescription(), item.getSavedDate(), item.getEditedDate()};
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 914, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
