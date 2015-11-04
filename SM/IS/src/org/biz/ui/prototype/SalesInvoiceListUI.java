/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.ui.prototype;

import java.util.ArrayList;
import java.util.List;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.components.parent.controls.PTableColumn;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.windows.ListViewUI;

/**
 *
 * @author jawa
 */
public class SalesInvoiceListUI extends ListViewUI {
    
    /**
     * Creates new form SalesInvoiceListUI
     */
    public SalesInvoiceListUI() {
        super();

        getTable().setTableInteractionListner(tableInteractionListner);
        List<PTableColumn> tblCols = new ArrayList();
        tblCols.add(new PTableColumn(String.class, "ID"));
        tblCols.add(new PTableColumn(String.class, "Code"));
        tblCols.add(new PTableColumn(String.class, "Customer Name"));

        getTable().init(SalesInvoice.class, tblCols);
    }

   
        
    
    private TableInteractionListner tableInteractionListner = new TableInteractionListner() {

        @Override
        public Object[] getTableData(Object row) {
            SalesInvoice item = (SalesInvoice) row;
            String custName =  null;
            try {                
                custName = item.getCustomer().getCustomerName()  +item.getCustomer().getCompanyName();
            } catch (Exception e) {
                custName = "";
            }
            return new Object[]{item, item.getId(), item.getCode(),custName};
        }

        @Override
        public void selectionChanged(Object newRowObject) {
//            showDetailView(newRowObject);
        }
        
        
    };
    

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
            .addGap(0, 685, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 187, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
