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
import org.biz.app.ui.util.Tracer;
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
                SalesInvoiceLineItem lit = (SalesInvoiceLineItem) tblInvoiceLine1.getSelectedObject();
//                if (lit == null) {
//                    addItemToTable();
//                } else {
                    //update the row
                    updateRow(lit);//if this is new row then modify this.                    
//                }
               titem.requestFocus(); 
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
        addNewToTable();
        gridControllerPanel1.setTable(tblInvoiceLine1);
       
        tpnlLineDetail.addToFocus(titem);
        tpnlLineDetail.addToFocus(tqty);
        tpnlLineDetail.addToFocus(tprice); 
        
      
        ActionListener act=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcualteTotal();
            }
        };
        tqty.setAction(act);
        tprice.setAction(act);       
       
        titem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    tblInvoiceLine1.selectNextRow();
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    tblInvoiceLine1.selectPreRow();
                }
            }
        });
        
        tpaid.setAction(act);
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
         UIEty.objToUi(ttotal, si.getTotal());
         //update the table row 
                  
    }
    
    public SalesInvoiceLineItem getSalesLine() {
        SalesInvoiceLineItem lineItem=(SalesInvoiceLineItem)tblInvoiceLine1.getSelectedObject();
        lineItem.setItem(titem.getSelectedObject());
        lineItem.setQty(UIEty.tcToDouble(tqty));
        lineItem.setPrice(UIEty.tcToDouble(tprice));
        lineItem.calculateLineItem();
        return lineItem;
    }
 
    private void addNewToTable(){
        int insertionPoint=tblInvoiceLine1.getRowCount();
        tblInvoiceLine1.addModelToTable(new SalesInvoiceLineItem());
        tblInvoiceLine1.changeSelection(insertionPoint);
    }
    
    private void addItemToTable(){    

        SalesInvoiceLineItem lineItem = new SalesInvoiceLineItem();
        lineItem.setItem(titem.getSelectedObject());
        lineItem.setQty(UIEty.tcToDouble(tqty));
        lineItem.setPrice(UIEty.tcToDouble(tprice));
        lineItem.calculateLineItem();
        tblInvoiceLine1.addModelToTable(lineItem);
        addNewToTable();
    }
    
    private void updateRow(SalesInvoiceLineItem lineItem ){        
        lineItem.setItem(titem.getSelectedObject());
        lineItem.setQty(UIEty.tcToDouble(tqty));
        lineItem.setPrice(UIEty.tcToDouble(tprice));
        lineItem.calculateLineItem();
        tblInvoiceLine1.replaceModel(lineItem);    
        //get row number 
        //if its the last row then add new row
        //
        int row=tblInvoiceLine1.getSelectedRow();
        if(row==tblInvoiceLine1.getRowCount()-1)
        addNewToTable();    
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
        toSave.add(getBusObject());        
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
        cButton1 = new org.components.controls.CButton();
        tinv = new org.components.controls.CTextField();
        cButton9 = new org.components.controls.CButton();
        ttotal = new org.components.controls.CLabel();
        tdiscount = new org.components.controls.CLabel();
        cLabel1 = new org.components.controls.CLabel();
        cLabel2 = new org.components.controls.CLabel();
        cLabel3 = new org.components.controls.CLabel();
        tpnlLineDetail = new org.components.containers.CPanel();
        tqty = new org.components.controls.CTextField();
        tprice = new org.components.controls.CTextField();
        titem = new com.components.custom.TextFieldWithPopUP<Item>();
        tline = new org.components.controls.CLabel();
        controlPanel1 = new com.components.custom.ControlPanel();
        gridControllerPanel1 = new com.components.custom.GridControllerPanel();
        tpaid = new org.components.controls.CTextField();
        tbal = new org.components.controls.CLabel();

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
        jScrollPane2.setBounds(30, 100, 720, 210);

        cButton1.setText("Find");
        cButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton1ActionPerformed(evt);
            }
        });
        add(cButton1);
        cButton1.setBounds(110, 10, 25, 19);
        add(tinv);
        tinv.setBounds(10, 10, 93, 25);

        cButton9.setText("Top");
        cButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton9ActionPerformed(evt);
            }
        });
        add(cButton9);
        cButton9.setBounds(760, 50, 54, 19);

        ttotal.setText("Total");
        add(ttotal);
        ttotal.setBounds(530, 420, 60, 20);

        tdiscount.setText("Discount");
        add(tdiscount);
        tdiscount.setBounds(530, 360, 80, 41);

        cLabel1.setText("Paid");
        add(cLabel1);
        cLabel1.setBounds(540, 460, 50, 20);

        cLabel2.setText("Tax");
        add(cLabel2);
        cLabel2.setBounds(530, 390, 104, 25);

        cLabel3.setText("Sub Total");
        add(cLabel3);
        cLabel3.setBounds(520, 340, 80, 20);

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
        add(controlPanel1);
        controlPanel1.setBounds(30, 320, 410, 30);
        add(gridControllerPanel1);
        gridControllerPanel1.setBounds(760, 100, 90, 230);
        add(tpaid);
        tpaid.setBounds(620, 450, 170, 25);
        add(tbal);
        tbal.setBounds(620, 490, 170, 25);
    }// </editor-fold>//GEN-END:initComponents

    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton1ActionPerformed
        System.out.println("finding sinister value...........");
        //command pettern
        //long exausting task
        // get business objects
        //pass uis
        //update uis
        tblInvoiceLine1.changeSelection(3);
//        invoker(commandFind);
        System.out.println("finding sinister value...........");        
    }//GEN-LAST:event_cButton1ActionPerformed

    
    private void cButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton9ActionPerformed

    }//GEN-LAST:event_cButton9ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CButton cButton9;
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel3;
    private com.components.custom.ControlPanel controlPanel1;
    private com.components.custom.GridControllerPanel gridControllerPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.components.controls.CLabel tbal;
    private org.components.controls.ModelEditableTable tblInvoiceLine1;
    private org.components.controls.CLabel tdiscount;
    private org.components.controls.CTextField tinv;
    private com.components.custom.TextFieldWithPopUP<Item> titem;
    private org.components.controls.CLabel tline;
    private org.components.controls.CTextField tpaid;
    private org.components.containers.CPanel tpnlLineDetail;
    private org.components.controls.CTextField tprice;
    private org.components.controls.CTextField tqty;
    private org.components.controls.CLabel ttotal;
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
        int x=1000;
        for (SalesInvoiceLineItem sl : salesInvoiceLineItems) {
            sl.setId(uk+ x++);
        }
        return si;
    }


}


