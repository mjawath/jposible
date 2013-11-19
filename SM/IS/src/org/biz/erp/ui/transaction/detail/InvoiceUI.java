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
import javax.swing.AbstractAction;
import org.biz.app.ui.util.Tracer;
import org.biz.app.ui.util.UIEty;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.biz.invoicesystem.service.transactions.SalesInvoiceService;
import org.com.print.PrintService;
import org.components.parent.controls.editors.DoubleCellEditor;
import org.components.parent.controls.editors.ObjectCellEditor;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.util.ComponentFactory;
import org.components.windows.DetailPanel;

/**
 *
 * @author d
 */
  public class InvoiceUI extends DetailPanel<SalesInvoice> {

    
    private List<Item> items;
     private SalesInvoiceService salesService;
    //finalised swing worker task executer
//    private int rowCount;
    
    /**

* builder pattern to ange the way the  table columns are created and edited  

    /**......
     * Creates new form InvoiceUI
     */
    public InvoiceUI() {
        super();
       
    }
    
    /*
     * first overrit the init 
     * to add init logics
     */
    public void init(){
        
        initComponents();
        super.init();

        tprice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalesInvoiceLineItem lit = (SalesInvoiceLineItem) tblInvoiceLine1.getSelectedObject();
                updateRow(lit);
                focusManager.setTemCom(titem);
            }
        });
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
                UIEty.objToUi(tsubTotal, si.getTotal());
            }

            @Override
            public void selectionChanged(Object newRowObject) {
                //get selected object
                // set to detail panel                
                setLineItemDetail((SalesInvoiceLineItem) newRowObject); 
            }
            
        });
        tblInvoiceLine1.addNewToLast();
        gridControllerPanel1.setTable(tblInvoiceLine1);
        
        tpnlLineDetail.addToFocus(titem);
        tpnlLineDetail.addToFocus(tqty);
        tpnlLineDetail.addToFocus(tprice);
        addToFocus(tpnlLineDetail);
        addToFocus(tpaid);

        tpnlLineDetail.setContainer(this);
//        addToFocus(tpnlLineDetail);
        
      
        ActionListener act=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcualteTotal();
            }
        };
        tqty.setDocAction(act);
        tprice.setDocAction(act);       
        
     
        
        setNavForTableEditor(tblInvoiceLine1, tpnlLineDetail);
        
        tpaid.setDocAction(act);
        
        
        ComponentFactory.setKeyAction(this, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed Ctrl , P");
                //open payment window
            }
        }, KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK);
      
    }
        
    private void calcualteTotal(){
        //get the qty price item lineitem
        //get 
         SalesInvoiceLineItem sil = getSalesLine();
         sil.calculateLineItem();
         UIEty.objToUi(tline, sil.getLineAmount());
         tblInvoiceLine1.replaceModel(sil);
         SalesInvoice si=getBusObject();
         si.setTotal();
         UIEty.objToUi(tbal, si.calculateBalance());
         UIEty.objToUi(tsubTotal, si.getTotal());
         //update the table row 
                  
    }
    
    public SalesInvoiceLineItem getSalesLine() {
        SalesInvoiceLineItem lineItem=(SalesInvoiceLineItem)tblInvoiceLine1.getSelectedObject();
        if(lineItem==null)return null;
        lineItem.setItem(titem.getSelectedObject());
        lineItem.setQty(UIEty.tcToDouble(tqty));
        lineItem.setPrice(UIEty.tcToDouble(tprice));
        lineItem.calculateLineItem();
        return lineItem;
    }
    
    private void addItemToTable(){ 
        
        SalesInvoiceLineItem lineItem = new SalesInvoiceLineItem();
        lineItem.setItem(titem.getSelectedObject());
        lineItem.setQty(UIEty.tcToDouble(tqty));
        lineItem.setPrice(tprice.getDoubleValue());
        lineItem.calculateLineItem();
        tblInvoiceLine1.addModelToTable(lineItem);
//        addNew();
    }
    
    private void updateRow(SalesInvoiceLineItem lineItem ){
        if(lineItem==null)return;
        lineItem.setItem(titem.getSelectedObject());
        lineItem.setQty(UIEty.tcToDouble(tqty));
        lineItem.setPrice(UIEty.tcToDouble(tprice));
        lineItem.calculateLineItem();
        tblInvoiceLine1.replaceModelSele(lineItem);    
     
    }
    
    public void setLineItemDetail(SalesInvoiceLineItem obj){
        Tracer.printToOut("Table selecteion changed");
        if(obj==null)return;
        titem.setSelectedObject(obj.getItem());
        UIEty.objToUi(tqty,obj.getQty());
        UIEty.objToUi(tprice,obj.getPrice());
        UIEty.objToUi(tline,obj.getLineAmount());
        titem.requestFocus();
        
    }
    
    @Override
    public void preSave() {
//        toSave.add(getBusObject()); dont need this line,handled in save method        
        super.preSave();
    }

    @Override
    public void clear() {
        tblInvoiceLine1.clear(); 
        setLineItemDetail(new SalesInvoiceLineItem());
        tdiscount.clear();
        tTax.clear();
        tpaid.clear();
        tsubTotal.clear();        
        tbal.clear();
        tblInvoiceLine1.addNewToLast();        
        super.clear();
    }
    
    
       @Override
    public void setService(Service service) {
        super.setService(service);
        salesService = (SalesInvoiceService) service;        
//        commandGUI.invoke();
    }

    @Override
    public void printPage() {
        PrintService.print();
        //get the printer 
        // set the print template 
        // set the settings 
        //print 
    }


       
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblInvoiceLine1 = new org.components.controls.ModelEditableTable();
        cButton1 = new org.components.controls.CButton();
        tinv = new org.components.controls.CTextField();
        tsubTotal = new org.components.controls.CLabel();
        tlbldiscount = new org.components.controls.CLabel();
        cLabel1 = new org.components.controls.CLabel();
        cLabel2 = new org.components.controls.CLabel();
        cLabel3 = new org.components.controls.CLabel();
        tpnlLineDetail = new org.components.containers.CPanel();
        tqty = new org.components.controls.CTextField();
        tprice = new org.components.controls.CTextField();
        titem = new com.components.custom.TextFieldWithPopUP<Item>();
        tline = new org.components.controls.CLabel();
        gridControllerPanel1 = new com.components.custom.GridControllerPanel();
        tpaid = new org.components.controls.CTextField();
        tbal = new org.components.controls.CLabel();
        cLabel4 = new org.components.controls.CLabel();
        tdiscount = new org.components.controls.CTextField();
        tTax = new org.components.controls.CTextField();
        cPanel1 = new org.components.containers.CPanel();

        tblInvoiceLine1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblInvoiceLine1);
        tblInvoiceLine1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        add(jScrollPane2);
        jScrollPane2.setBounds(30, 100, 720, 200);

        cButton1.setText("Find");
        cButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton1ActionPerformed(evt);
            }
        });
        add(cButton1);
        cButton1.setBounds(110, 10, 25, 25);
        add(tinv);
        tinv.setBounds(10, 10, 93, 25);

        tsubTotal.setText("SubTotal");
        add(tsubTotal);
        tsubTotal.setBounds(620, 320, 130, 20);

        tlbldiscount.setText("Discount");
        add(tlbldiscount);
        tlbldiscount.setBounds(520, 350, 80, 20);

        cLabel1.setText("Paid");
        add(cLabel1);
        cLabel1.setBounds(520, 440, 50, 20);

        cLabel2.setText("Tax");
        add(cLabel2);
        cLabel2.setBounds(520, 380, 104, 25);

        cLabel3.setText("Sub Total");
        add(cLabel3);
        cLabel3.setBounds(520, 320, 80, 20);

        tpnlLineDetail.setBackground(new java.awt.Color(153, 255, 0));
        tpnlLineDetail.setLayout(null);

        tqty.setText("Qty");
        tpnlLineDetail.add(tqty);
        tqty.setBounds(200, 10, 160, 30);

        tprice.setText("Price");
        tpnlLineDetail.add(tprice);
        tprice.setBounds(370, 10, 130, 30);

        titem.setText("Item");
        tpnlLineDetail.add(titem);
        titem.setBounds(10, 10, 180, 30);

        tline.setText("Line Amount");
        tpnlLineDetail.add(tline);
        tline.setBounds(510, 10, 104, 30);

        add(tpnlLineDetail);
        tpnlLineDetail.setBounds(50, 50, 650, 50);
        add(gridControllerPanel1);
        gridControllerPanel1.setBounds(750, 100, 90, 230);
        add(tpaid);
        tpaid.setBounds(620, 440, 170, 25);

        tbal.setText("Balance");
        add(tbal);
        tbal.setBounds(620, 480, 170, 25);

        cLabel4.setText("Total");
        add(cLabel4);
        cLabel4.setBounds(520, 410, 104, 25);

        tdiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tdiscountActionPerformed(evt);
            }
        });
        add(tdiscount);
        tdiscount.setBounds(620, 350, 170, 25);
        add(tTax);
        tTax.setBounds(620, 380, 170, 25);
        add(cPanel1);
        cPanel1.setBounds(40, 180, 340, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton1ActionPerformed
        System.out.println("finding sinister value...........");
        //command pettern
        //long exausting task
        // get business objects
        //pass uis
        //update uis
//        tblInvoiceLine1.changeSelection(3);
//        invoker(commandFind);
        System.out.println("finding sinister value...........");        
    }//GEN-LAST:event_cButton1ActionPerformed

    
    private void tdiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tdiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tdiscountActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel3;
    private org.components.controls.CLabel cLabel4;
    private org.components.containers.CPanel cPanel1;
    private com.components.custom.GridControllerPanel gridControllerPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.components.controls.CTextField tTax;
    private org.components.controls.CLabel tbal;
    private org.components.controls.ModelEditableTable tblInvoiceLine1;
    private org.components.controls.CTextField tdiscount;
    private org.components.controls.CTextField tinv;
    private com.components.custom.TextFieldWithPopUP<Item> titem;
    private org.components.controls.CLabel tlbldiscount;
    private org.components.controls.CLabel tline;
    private org.components.controls.CTextField tpaid;
    private org.components.containers.CPanel tpnlLineDetail;
    private org.components.controls.CTextField tprice;
    private org.components.controls.CTextField tqty;
    private org.components.controls.CLabel tsubTotal;
    // End of variables declaration//GEN-END:variables

    
    public SalesInvoice getBusObject() {
        
        SalesInvoice si= new SalesInvoice();
        si.setInvNo(tinv.getText());//should implement invoice number generation l;oic
        //if empty  if number , if sed from number , if auto generate number, if string ,auto generate string
        si.setCashRecieveds(UIEty.tcToDouble(tpaid));
        List<SalesInvoiceLineItem> salesInvoiceLineItems= tblInvoiceLine1.getModelCollection();
        si.setLineItems(salesInvoiceLineItems);
        //set id for bus obj
        String uk= service.getUniqueKey();
        si.setId(uk);
        int x=1000;// resonable number to iterate 
        for (SalesInvoiceLineItem sl : salesInvoiceLineItems) {
            sl.setId(uk+ x++);
        }
        return si;
    }


}


