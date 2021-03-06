/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.ui.list.master;

import java.util.ArrayList;
import java.util.List;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.entity.master.Supplier;
import org.components.parent.controls.PTableColumn;
import org.components.windows.ListViewUI;

/**
 *
 * @author Jawad
 */
public class SupplierLV extends ListViewUI  {

    /**
     * Creates new form ItemLV
     */
    public SupplierLV() {
        super();
        List<PTableColumn> tblCols = new ArrayList();
        tblCols.add(new PTableColumn(String.class, "ID"));
        PTableColumn colcode = new PTableColumn(String.class, "Code");
        colcode.setMinWidth(80);
        tblCols.add(colcode);
        PTableColumn colname = new PTableColumn(String.class, "Name");
        colname.setWidth(250);
        tblCols.add(colname);
        getTable().init(Customer.class, tblCols);
                
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    
        
   
    
    public Object[] getTableData(Object row) {
        Supplier item = (Supplier) row;
        return new Object[]{item, item.getId(), item.getCode()};
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
