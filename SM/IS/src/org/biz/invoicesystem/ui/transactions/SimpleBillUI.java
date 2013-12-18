/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.ui.transactions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.biz.app.ui.util.UIEty;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.biz.invoicesystem.service.transactions.SalesInvoiceService;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.windows.DetailPanel;

/**
 *
 * @author yy
 */
public class SimpleBillUI extends DetailPanel<SalesInvoice> {

    
    private SalesInvoiceService salesService;
    private TableInteractionListner tableInteractionListner=new TableInteractionListner(){

        @Override
        public void onCellEditing(Object cellObj, int column) {
         if(column==1 ||column==2   ){
             SalesInvoice si = getBusObject();
             ((SalesInvoiceLineItem) cellObj).calculateLineItem();
             tblInviceLine.replaceSelectedModel(cellObj);
             //update invoice ui
             si.setTotal();
         }   
        }    
        
    };
    
    /**
     * Creates new form SimpleBillUI
     */
    public SimpleBillUI() {
        initComponents();
        init();
    }

    @Override
    public void init() {
        
        addToFocus(titem);
        addToFocus(tqty);
        addToFocus(tprice);
        
        //Line Item Table settup
        tblInviceLine.initTable(SalesInvoiceLineItem.class, new String[]{"item","qty","price","lineAmount"},new String[]{"Item","QTY","Amount","Line Totel"});
        
        
        tprice.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addItemToTable();
                tqty.requestFocus();
            }
        });
        
        //List Item Table Listens to selection changes
        // and selected object is set to details panel (which is responsible for details of selected object)
        tblInviceLine.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                //get selected object
                // set to detail panel                
                setLineItemDetail((SalesInvoiceLineItem)tblInviceLine.getSelectedObject());
            }
        });
        
        tblInviceLine.setTableInteractionListner(tableInteractionListner);
        
    }
    
    private void addItemToTable(){
        //validate 

        //get item
        //get price
        // get qty
        //create line object

        SalesInvoiceLineItem lineItem = new SalesInvoiceLineItem();
        lineItem.setItem(titem.getSelectedObject());
        lineItem.setQty(UIEty.tcToDouble(tqty));
        lineItem.setPrice(UIEty.tcToDouble(tprice));
        lineItem.calculateLineItem();
        // validate
        // add to table
        tblInviceLine.addModelToTable(lineItem);

    }
    
    public void setLineItemDetail(SalesInvoiceLineItem obj){
        titem.setSelectedObject(obj.getItem());
        UIEty.objToUi(tqty,obj.getQty());
        UIEty.objToUi(tprice,obj.getPrice());       
        
    }
    
    

    @Override
    public void setService(Service service) {
        super.setService(service);
        salesService =(SalesInvoiceService) service;
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblInviceLine = new org.components.controls.ModelEditableTable<SalesInvoiceLineItem>();
        jPanel1 = new javax.swing.JPanel();
        tqty = new org.components.controls.CTextField();
        tprice = new org.components.controls.CTextField();
        titem = new com.components.custom.TextFieldWithPopUP<Item>();
        gridControllerPanel1 = new com.components.custom.GridControllerPanel();

        tblInviceLine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Item #", "Qty", "Price", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblInviceLine);
        tblInviceLine.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        tqty.setText("Qty");

        tprice.setText("Price");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(titem, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tqty, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tprice, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE))
                .addGap(127, 127, 127))
            .addGroup(layout.createSequentialGroup()
                .addGap(346, 346, 346)
                .addComponent(gridControllerPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(gridControllerPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.components.custom.GridControllerPanel gridControllerPanel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.ModelEditableTable<SalesInvoiceLineItem> tblInviceLine;
    private com.components.custom.TextFieldWithPopUP<Item> titem;
    private org.components.controls.CTextField tprice;
    private org.components.controls.CTextField tqty;
    // End of variables declaration//GEN-END:variables
}
