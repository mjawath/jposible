/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ItemInventorySummary.java
 *
 * Created on Jun 28, 2011, 4:50:00 PM
 */
package org.biz.erp.inventory.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.biz.app.ui.event.ButtonAction;
import org.biz.app.ui.util.TableUtil;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.entity.inventory.InventoryMonthlySummery;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.entity.transactions.PurchaseInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.service.inventory.InventoryJournalService;
import org.biz.invoicesystem.service.inventory.InventoryMonthlySummeryService;
import org.biz.invoicesystem.service.transactions.PurchaseInvoiceService;
import org.biz.invoicesystem.service.transactions.SalesInvoiceService;
import org.components.windows.ListViewPanel;

/**
 *
 * @author Administrator
 */
public class ItemInventorySummary extends ListViewPanel<Object> {

    private InventoryJournalService service;
    
    /** Creates new form ItemInventorySummary */
    public ItemInventorySummary() {
        super();
        
    }


    @Override
    public void init() {
        super.init();        
        initComponents();
//        selectitem();
        tbtnFind.addActionListener(findAction);
    }
    
    private ButtonAction  findAction=new ButtonAction(){
    
        @Override
        public Object executeTask(Object objs  ) {
            return findItemForQuery();
        }

        @Override
        public void resultTask(Object objs) {
            
        }  
    };
   
    
    private List findItemForQuery(){
    //find the item for 
        // provided warehoue
        //provoided shop 
        //provided time period  ???!!??
        //normally current date is used
        service.getDao().getForLastMonthsummery(new Date());
    return null;
    }
            
    public void selectitem() {

        //select this month purchases
        SalesInvoiceService salesinvService = new SalesInvoiceService();
        List<PurchaseInvoice> purchaseInvoices = new ArrayList<PurchaseInvoice>();
        PurchaseInvoiceService purinvService = new PurchaseInvoiceService();
        purchaseInvoices = purinvService.getDao().getAll();
        //select this month sales
        List<SalesInvoice> salesInvoices = new ArrayList<SalesInvoice>();
        salesInvoices = salesinvService.getDao().getAll();

        HashMap<String, Item> hashMap = new HashMap<String, Item>();
        //get all item from transaction to  hash map from db and get the summery.....

        //get last month inventorymonthly summery  journal adjestment / balance
        summeryService = new InventoryMonthlySummeryService();
        List<InventoryMonthlySummery> monthlySummerys = summeryService.getDao().getAll();//get last monthly
        //

    }
    
    
    InventoryMonthlySummeryService summeryService;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        cxTable1 = new org.components.controls.CxTable();
        cDatePicker1 = new org.components.controls.CDatePicker();
        tbtnFind = new org.components.controls.CButton();
        twarehouse = new com.components.custom.TextFieldWithPopUP<Warehouse>();
        tshop = new com.components.custom.TextFieldWithPopUP<Shop>();

        cxTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Item Code", "Item Name", "In hand", "On Sales Order", "On Sales Invoice", "On Purchase Order", "On Purchase Invoice"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.Long.class, java.lang.Long.class, java.lang.Long.class, java.lang.Long.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(cxTable1);

        tbtnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtnFindActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(cDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tbtnFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(twarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tshop, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbtnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(twarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tshop, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 316, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbtnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnFindActionPerformed

        //get last month
        //int lastmonth =summeryService.getDao().getLastMonth();
//        List<InventoryMonthlySummery > monthlySummerys= summeryService.getDao().getForLastMonth(new Date());//get last monthly
        List is = new InventoryJournalService().getDao().getForLastMonthsummery(new Date());

        TableUtil.cleardata(cxTable1);
        for (Object obj : is) {
            Object[] objx = (Object[]) obj;
            Item item = (Item) objx[0];
            TableUtil.addrow(cxTable1, new Object[]{item.getCode(), objx[1]});
        }
    }//GEN-LAST:event_tbtnFindActionPerformed

    @Override
    public void setService(Service service) {
    this.service=(InventoryJournalService) service;
    }

    
    
    public void addToTable(List<InventoryJournal> ijs) {


        for (InventoryJournal inventoryJournal : ijs) {
            List<InventoryJournalLine> ijls = inventoryJournal.getLines();
            if (ijls != null) {
                for (InventoryJournalLine inventoryJournalLine : ijls) {
                }
            }
        }

    }

    public void addToTable(InventoryJournalLine item) {
        TableUtil.addrow(cxTable1, new Object[]{item.getItem().getCode(), item.getQty()});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CDatePicker cDatePicker1;
    private org.components.controls.CxTable cxTable1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CButton tbtnFind;
    private com.components.custom.TextFieldWithPopUP<Shop> tshop;
    private com.components.custom.TextFieldWithPopUP<Warehouse> twarehouse;
    // End of variables declaration//GEN-END:variables
}
