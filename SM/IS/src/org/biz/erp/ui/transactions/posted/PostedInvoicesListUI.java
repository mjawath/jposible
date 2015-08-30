/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PostedInvoicesListUI.java
 *
 * Created on Feb 8, 2012, 10:57:40 PM
 */
package org.biz.erp.ui.transactions.posted;

import java.util.Date;
import java.util.List;
import javax.swing.JPanel;
import org.biz.app.ui.util.TableUtil;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.service.transactions.SalesInvoiceService;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.windows.MasterViewUI;

/**
 *
 * @author nnjj
 */
public class PostedInvoicesListUI extends MasterViewUI<SalesInvoice> {

    private List<SalesInvoice> invoices;
    private SalesInvoiceService salesService;   
    
    private TableInteractionListner tableInteractionListner = new TableInteractionListner() {
        @Override
        public Object[] getTableData(Object row) {
            SalesInvoice item = (SalesInvoice) row;
            return new Object[]{item, item.getId(), item.getInvNo(), item.getTotal(),item.getSavedDate(),item.getEditedDate()};
        }
    };
    /** Creates new form PostedInvoicesListUI */
    public PostedInvoicesListUI() {
        super();    
    }

    @Override
    public void init() {
         initComponents();
        super.init();
        tblInvoice.init(SalesInvoice.class, new Class[]{String.class,String.class,Double.class,Date.class,Date.class}, new String[]{"id","inv no","Saved","Edited"});
        tblInvoice.setTableInteractionListner(tableInteractionListner);
        
    }

    @Override
    public void setService(Service service) {
        this.service=service;
        salesService =(SalesInvoiceService)service;
    }
    
    
    

    public void getData(){
        invoices = service.getDao().getAll();
    }
    
    public void getData(String qry){
        invoices = getService().getDao().getAll();
    }
    
    public void addToTable(List<SalesInvoice> items) {
        TableUtil.cleardata(tblInvoice);
        if (items == null || items.isEmpty()) {
            return;
        }
        tblInvoice.setModelCollection(items);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblInvoice = new org.components.controls.CxTable();
        cButton1 = new org.components.controls.CButton();
        cDatePicker1 = new org.components.controls.CDatePicker();
        cTextField1 = new org.components.controls.CTextField();

        tblInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "code", "Invoice No", "Customer"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblInvoice);

        cButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(cDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(cTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(267, 267, 267))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton1ActionPerformed

        getData();
        addToTable(invoices);
    }//GEN-LAST:event_cButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CDatePicker cDatePicker1;
    private org.components.controls.CTextField cTextField1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CxTable tblInvoice;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return " psoterd dfd ";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }
}
