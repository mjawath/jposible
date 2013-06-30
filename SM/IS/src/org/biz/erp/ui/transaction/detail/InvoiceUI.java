/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.erp.ui.transaction.detail;

import com.components.custom.PopupListner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import org.biz.app.ui.util.CKeyAdapter;
import org.biz.app.ui.util.UIEty;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.biz.invoicesystem.service.transactions.SalesInvoiceService;
import org.components.parent.controls.editors.DoubleCellEditor;
import org.components.parent.controls.editors.ObjectCellEditor;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.windows.DetailPanel;

/**
 *
 * @author d
 */
public class InvoiceUI extends DetailPanel<SalesInvoice> {

    
    List<Item> items;
    SalesInvoiceService salesService;
    //finalised swing worker task executer
    
    /**

* builder pattern to ange the way the  table columns are created and edited  

    /**......
     * Creates new form InvoiceUI
     */
    public InvoiceUI() {
        initComponents();
        init();
//        cButton1.addActionListener(commandFind);
        tinv.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
//               commandFind.execute();
            }
        
        });
       
    }
    
    public void init(){
        
        
        tprice.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SalesInvoiceLineItem lit=(SalesInvoiceLineItem) tblInvoiceLine1.getSelectedObject();
                if(lit==null){
                    addItemToTable();
                }else{
                //update the row
                 updateRow(lit);   
                }
                tqty.requestFocus();
            }
        });
//        items=new ItemService().getDao().getAll();        
        tblInvoiceLine1.init(SalesInvoiceLineItem.class, new Class[]{Item.class,String.class,String.class,String.class},
                new String[]{"Item","QTY","Amount","Line Totel"});
        tblInvoiceLine1.setPropertiesEL(new String[]{"item","qty","price","lineAmount"});
        
        DoubleCellEditor dce=new DoubleCellEditor();                
        DoubleCellEditor dceA=new DoubleCellEditor();                
        ObjectCellEditor<Item> itce=new ObjectCellEditor(tblInvoiceLine1);
        
        titem.initPopup(Item.class,new Class[]{String.class,String.class,String.class}, new String[]{"code","id","desc"}, "code",new PopupListner() {

            @Override
            public List searchItem(Object searchQry) {
                items = salesService.getitemService().getDao().getAll();
                return items;
            }

            @Override
            public Object[] getTableData(Object obj) {
                Item item = (Item) obj;
                return new Object[]{item, item.getId(), item.getCode()};
            }
        } );
        
        itce.initPopup(new String[]{"id","code"}, new String[]{"ID","Code"},"id");
        itce.setPopListner(new PopupListner() {

            @Override
            public List searchItem(Object searchQry) {
                items=salesService.getitemService().getDao().getAll();
                return items;
            }

            @Override
            public Object[] getTableData(Object obj) {
                Item item=(Item)obj;
                return new Object[]{item,item.getId(),item.getCode()};
            }
        });
        
        tblInvoiceLine1.setCellEditors(itce,dce,dceA);    
        
        tblInvoiceLine1.modelToTable(new ArrayList());
        
       
        controlPanel1.setCrudController(this);
        tblInvoiceLine1.setTableInteractionListner(new TableInteractionListner() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Object[] getTableData(Object row) {
                SalesInvoiceLineItem sil=(SalesInvoiceLineItem)row;
                return new Object[]{sil,sil.getItem(),sil.getQty(),sil.getPrice(),sil.getLineAmount()};
            }
            
            

            @Override
            public void onCellEditing(Object cel, int col) {
                //get bus object
                //get table object
                //get editing row object 
                //get editing property
                //validate things   
                System.out.println(" Row obj " + cel);
                //set value for column property
                //get table object collection
                //get GUI object
                /**
                 * use the builder pattern to use the table to column is created
                 * with the option to create the column
                 *
                 * column option are
                 */
                SalesInvoice si = getBusObject();
                ((SalesInvoiceLineItem) cel).calculateLineItem();
                tblInvoiceLine1.replaceModel(cel);
                //set total to ui
                si.setTotal();
                UIEty.objToUi(ttotal, si.getTotal());
            }

            @Override
            public void selectionChanged(Object newRowObject) {
                //get selected object
                // set to detail panel                
                setLineItemDetail((SalesInvoiceLineItem) newRowObject); 
            }
            
        });
        tblInvoiceLine1.addModelToTable(new SalesInvoiceLineItem());

       
        tinv.addKeyListener(new CKeyAdapter());
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
        tblInvoiceLine1.addModelToTable(lineItem);

    }
    
    private void updateRow(SalesInvoiceLineItem lineItem ){        
        lineItem.setItem(titem.getSelectedObject());
        lineItem.setQty(UIEty.tcToDouble(tqty));
        lineItem.setPrice(UIEty.tcToDouble(tprice));
        lineItem.calculateLineItem();
        tblInvoiceLine1.replaceModel(lineItem);
    }
    
    public void setLineItemDetail(SalesInvoiceLineItem obj){
        if(obj==null)return;
        titem.setSelectedObject(obj.getItem());
        UIEty.objToUi(tqty,obj.getQty());
        UIEty.objToUi(tprice,obj.getPrice());       
        
    }
    
    @Override
    public void preSave() {
//        toSave.add(getBusObject());        
        super.preSave();
    }

    @Override
    public void clear() {
        tblInvoiceLine1.clear();      
        super.clear();
    }
    
       @Override
    public void setService(Service service) {
        super.setService(service);
        salesService = (SalesInvoiceService) service;        
//        commandGUI.invoke();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblInvoiceLine1 = new org.components.controls.ModelEditableTable();
        controlPanel1 = new com.components.custom.ControlPanel();
        cButton1 = new org.components.controls.CButton();
        tinv = new org.components.controls.CTextField();
        cButton9 = new org.components.controls.CButton();
        ttotal = new org.components.controls.CLabel();
        tdiscount = new org.components.controls.CLabel();
        cLabel1 = new org.components.controls.CLabel();
        cLabel2 = new org.components.controls.CLabel();
        cLabel3 = new org.components.controls.CLabel();
        cPanel1 = new org.components.containers.CPanel();
        cButton2 = new org.components.controls.CButton();
        cButton4 = new org.components.controls.CButton();
        cButton5 = new org.components.controls.CButton();
        cButton8 = new org.components.controls.CButton();
        cButton7 = new org.components.controls.CButton();
        cButton6 = new org.components.controls.CButton();
        cButton3 = new org.components.controls.CButton();
        jPanel1 = new javax.swing.JPanel();
        tqty = new org.components.controls.CTextField();
        tprice = new org.components.controls.CTextField();
        titem = new com.components.custom.TextFieldWithPopUP<Item>();

        setLayout(null);

        tblInvoiceLine1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblInvoiceLine1);
        tblInvoiceLine1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        add(jScrollPane2);
        jScrollPane2.setBounds(20, 170, 720, 210);
        add(controlPanel1);
        controlPanel1.setBounds(333, 578, 421, 32);

        cButton1.setText("Find");
        cButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton1ActionPerformed(evt);
            }
        });
        add(cButton1);
        cButton1.setBounds(389, 11, 53, 23);
        add(tinv);
        tinv.setBounds(100, 50, 93, 25);

        cButton9.setText("Top");
        cButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton9ActionPerformed(evt);
            }
        });
        add(cButton9);
        cButton9.setBounds(710, 115, 54, 23);

        ttotal.setText("Total");
        add(ttotal);
        ttotal.setBounds(445, 448, 235, 38);

        tdiscount.setText("Discount");
        add(tdiscount);
        tdiscount.setBounds(445, 497, 235, 41);

        cLabel1.setText("Paid");
        add(cLabel1);
        cLabel1.setBounds(248, 450, 171, 34);

        cLabel2.setText("Tax");
        add(cLabel2);
        cLabel2.setBounds(248, 505, 104, 25);

        cLabel3.setText("Sub Total");
        add(cLabel3);
        cLabel3.setBounds(445, 405, 235, 32);

        cPanel1.setLayout(null);

        cButton2.setText("Add");
        cButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton2ActionPerformed(evt);
            }
        });
        cPanel1.add(cButton2);
        cButton2.setBounds(10, 11, 51, 23);

        cButton4.setText("Copy");
        cButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton4ActionPerformed(evt);
            }
        });
        cPanel1.add(cButton4);
        cButton4.setBounds(144, 11, 57, 23);

        cButton5.setText("Up");
        cButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton5ActionPerformed(evt);
            }
        });
        cPanel1.add(cButton5);
        cButton5.setBounds(207, 11, 45, 23);

        cButton8.setText("Clear Line");
        cButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton8ActionPerformed(evt);
            }
        });
        cPanel1.add(cButton8);
        cButton8.setBounds(390, 11, 79, 23);

        cButton7.setText("Clear");
        cButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton7ActionPerformed(evt);
            }
        });
        cPanel1.add(cButton7);
        cButton7.setBounds(327, 11, 57, 23);

        cButton6.setText("Down");
        cButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton6ActionPerformed(evt);
            }
        });
        cPanel1.add(cButton6);
        cButton6.setBounds(262, 11, 59, 23);

        cButton3.setText("Remove");
        cButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton3ActionPerformed(evt);
            }
        });
        cPanel1.add(cButton3);
        cButton3.setBounds(67, 11, 71, 23);

        add(cPanel1);
        cPanel1.setBounds(270, 30, 510, 40);

        jPanel1.setLayout(null);

        tqty.setText("Qty");
        jPanel1.add(tqty);
        tqty.setBounds(181, 11, 83, 25);

        tprice.setText("Price");
        jPanel1.add(tprice);
        tprice.setBounds(270, 11, 116, 25);
        jPanel1.add(titem);
        titem.setBounds(40, 10, 127, 25);

        add(jPanel1);
        jPanel1.setBounds(80, 110, 600, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton1ActionPerformed
        System.out.println("finding sinister value...........");
        //command pettern
        //long exausting task
        // get business objects
        //pass uis
        //update uis
        
//        invoker(commandFind);
        System.out.println("finding sinister value...........");        
    }//GEN-LAST:event_cButton1ActionPerformed

    
    private void cButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton2ActionPerformed
        tblInvoiceLine1.addModelToTable(new SalesInvoiceLineItem());        
    }//GEN-LAST:event_cButton2ActionPerformed

    private void cButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton3ActionPerformed
        tblInvoiceLine1.removeSelectedRow();        
    }//GEN-LAST:event_cButton3ActionPerformed

    private void cButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton7ActionPerformed
        tblInvoiceLine1.clear();
    }//GEN-LAST:event_cButton7ActionPerformed

    private void cButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton4ActionPerformed
        tblInvoiceLine1.copySelectedElement();
    }//GEN-LAST:event_cButton4ActionPerformed

    private void cButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton5ActionPerformed
        tblInvoiceLine1.moveSelectedLineUp();
        
    }//GEN-LAST:event_cButton5ActionPerformed

    private void cButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton6ActionPerformed
        tblInvoiceLine1.moveSelectedLineDown();
    }//GEN-LAST:event_cButton6ActionPerformed

    private void cButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton8ActionPerformed
        //get selected line > get rows cell values > clear
        
        //get detail panel of the table if exists > call clear
        
        
        
        
    }//GEN-LAST:event_cButton8ActionPerformed

    private void cButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton9ActionPerformed

    }//GEN-LAST:event_cButton9ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CButton cButton2;
    private org.components.controls.CButton cButton3;
    private org.components.controls.CButton cButton4;
    private org.components.controls.CButton cButton5;
    private org.components.controls.CButton cButton6;
    private org.components.controls.CButton cButton7;
    private org.components.controls.CButton cButton8;
    private org.components.controls.CButton cButton9;
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel3;
    private org.components.containers.CPanel cPanel1;
    private com.components.custom.ControlPanel controlPanel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.components.controls.ModelEditableTable tblInvoiceLine1;
    private org.components.controls.CLabel tdiscount;
    private org.components.controls.CTextField tinv;
    private com.components.custom.TextFieldWithPopUP<Item> titem;
    private org.components.controls.CTextField tprice;
    private org.components.controls.CTextField tqty;
    private org.components.controls.CLabel ttotal;
    // End of variables declaration//GEN-END:variables

    public SalesInvoice getBusObject() {
        
        SalesInvoice si= new SalesInvoice();
        si.setInvNo(tinv.getText());
        List<SalesInvoiceLineItem> salesInvoiceLineItems= tblInvoiceLine1.getModelCollection();
        si.setLineItems(salesInvoiceLineItems);
        //set id for bus obj
        String uk= service.getUniqueKey();
        si.setId(uk);
        int x=1000;
        for (SalesInvoiceLineItem sl : salesInvoiceLineItems) {
            sl.setId(uk+x++);
        }
        return si;
    }


}


