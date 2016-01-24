/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.ui.list.master;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.biz.app.ui.util.QueryManager;
import org.biz.invoicesystem.entity.master.SKU;
import org.components.parent.controls.PTableColumn;
import org.components.windows.ListViewUI;

/**
 *
 * @author jawath
 */
public class SKULVUI extends ListViewUI {

    /**
     * Creates new form CategoryLVUI
     */
    public SKULVUI() {
        super();
//        initComponents();
    }
    
    @Override
    public void initPaging(QueryManager qm) {

//        initComponents();      
        super.initPaging(qm);
//        super.initPaging(qm) ;    
//        tbl.init(Category.class, new Class[]{String.class, String.class, Date.class, Date.class},
//                 new String[]{ "code", "description","savedDate", "editedDate"});

        List<PTableColumn> tblCols = new ArrayList();
        tblCols.add(new PTableColumn(String.class, "ID"));
        tblCols.add(new PTableColumn(String.class, "Code"));
        tblCols.add(new PTableColumn(String.class, "Item Description"));
        tblCols.add(new PTableColumn(Date.class, "savedDate"));
        tblCols.add(new PTableColumn(Date.class, "editedDate"));

        getTable().init(SKU.class, tblCols);
    }

    


    @Override
    public Object[] getTableData(Object row) {
        SKU sku = (SKU) row;
        return new Object[]{sku, sku.getId(), sku.getCode(), sku.getItem().getDescription(), sku.getSavedDate(), sku.getEditedDate()};
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1055, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
