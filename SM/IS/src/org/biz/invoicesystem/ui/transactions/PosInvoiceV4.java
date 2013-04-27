/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.ui.transactions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.UIEty;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Staff;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.biz.invoicesystem.service.master.CustomerService;
import org.biz.invoicesystem.service.master.ItemService;
import org.biz.invoicesystem.service.master.StaffService;
import org.biz.invoicesystem.service.transactions.SalesInvoiceService;
import org.biz.invoicesystem.system.SystemEntityUtil;
import org.biz.invoicesystem.ui.transactions.components.PosSalesInvoiceLineItemV31;
import org.components.util.ComponentFactory;
import org.components.windows.TabPanelUI;

/**
 *
 * @author d
 */
public class PosInvoiceV4 extends TabPanelUI {

    SalesInvoice invoice;
    
    SalesInvoiceService servicedao;
    PosSalesInvoiceLineItemV31 lineItemPanel;
    private CustomerService custService;
    private ItemService itemService;
    List<Staff> listStaff;
    StaffService staffService;
    Item currentItem;
    SalesInvoiceLineItem invoiceLine;

    /**
     * Creates new form PosInvoiceV3
     */
    public PosInvoiceV4() {
        initComponents();
        init();
    }

    @Override
    public void init() {

        crudcontrolPanel.setCrudController(this);
        lineItemPanel = new PosSalesInvoiceLineItemV31() {
            @Override
            public List itemSearch(String qry) {
                try {
                    return itemService.getDao().byCode(qry);                    //how about searching the pos invnetory for the items
                                        //pos warehouse only
                } catch (Exception e) {

                    e.printStackTrace();
                }
                return null;
            }

            @Override

            public void lineAddAction() {
//                salesline=tblInvoice.getSelectedObject();
                panelToEty(salesline);//get the object from panel
                Object id = salesline.getId();
                if (id == null) {
                    salesline.setId(System.currentTimeMillis() + "tt");
                }
                //validate before committing the data
                //then add it to the table 
                etyToRow(salesline);
                //get the invoice object from the ui
                uiety(invoice);//to set invoice properties
                invoice.setTotal();// do the calculation 
                sTotalToUI();// set the value to the GUI

                //check lineittem id is null then if its need a new row insert a new row
                //or move to next row
                lineItemPanel.clear();
                if (id == null) {
                    setnewrow();
                    int row = tblInvoice.getRowCount();
                    tblInvoice.getSelectionModel().setSelectionInterval(row - 1, row - 1);

                }

            }
        };

        lineItemPanel.setTable(tblInvoice);
        lineItemPanel.setScrollpane(jScrollPane1);
        lineItemPanel.setLayer(jLayeredPane1);
        tblInvoice.setColumnHeader(new String[]{"id","Item", "Item Code", "Description", "Qty", "Price", "Line Amount"});
        tblInvoice.setPropertiesEL(new String[]{"id","item" ,"item.code", "description", "qty", "price", "lineAmount"});

////////////seeking 
        //1 apply u -fullly into it
        //2 primary activity(desire) wanted otherthen anything else
        //3 patient
        //4 see things throu dont give up,dont be a quiter

        //5 direction of teacher
        // 6 sacrifise

        invoice = new SalesInvoice();
//        invoice=invoice.createNewInvoice();
        
        invoiceLine = new SalesInvoiceLineItem();
        tblInvoice.setModelCollection(invoice.getLineItems());
        itemService = new ItemService();

        servicedao = new SalesInvoiceService();
        itemService = new ItemService();

        custService = new CustomerService();

        staffService = new StaffService();
        listStaff = new ArrayList<Staff>();
        
        Action act=new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRow();
            }
        };
                
        UIEty.setKeyAction(lineItemPanel, act,KeyEvent.VK_F3 ,KeyEvent.CTRL_DOWN_MASK);


        setnewrow();
        
        

    }

   
    public void uiety(SalesInvoice invoice) {

//        invoice.setDocdate(tdate.getDate());
//        invoice.setInvNo(uiEty.tcToStr(tinvoiceManualNo));
        invoice.setShop(Shop.getDefaultShop());
//        invoice.setDocRefNo(uiEty.tcToStr(tdocref));
        invoice.setSubTotal(UIEty.tcToDouble(tsubtotal));
        invoice.setTexAmount(UIEty.tcToDouble(ttax));
        invoice.setDiscount(UIEty.tcToDouble(tdis));
        invoice.setCashRecieveds(UIEty.tcToDouble(tcashrecieved));
//        invoice.setRemarks(uiEty.tcToStr(tremark));
//        invoice.setEditeddate(SystemEntityUtil.getSystemDate());
//        invoice.setSaveddate(SystemEntityUtil.getSystemDate());
//        invoice.setLineItems(lineItems);
        //get the line items from  the table

        List<SalesInvoiceLineItem>  list=populateBusFromTable();
        invoice.setLineItems(list);
    }
    
    public List<SalesInvoiceLineItem> populateBusFromTable(){
        List<SalesInvoiceLineItem> list= tblInvoice.getObjects(SalesInvoiceLineItem.class);
        
        return list; 
    }

    private void etyToRow(SalesInvoiceLineItem line) {

        TableUtil.replaceModel(tblInvoice, line, tblInvoice.getSelectedRow());
    }

    public void sTotalToUI() {
        UIEty.objToUi(tsubtotal, invoice.getSubTotal());
        UIEty.objToUi(tfinaltotle, invoice.getTotal());
//        uiEty.objToUi(tbal, invoice.setTotal());
    }

    public void setnewrow() {
//        TableUtil.addrow(tblInvoice, new Object[]{});
        SalesInvoiceLineItem si = new SalesInvoiceLineItem();
//        invoice.getLineItems().add(si);
//        addToTable(invoice.getLineItems());
        tblInvoice.addModelToTable(si);
    }

    public void addToTable(List<SalesInvoiceLineItem> items) {
        tblInvoice.modelToTable(items);
    }

    public void save() {
        
        uiety(invoice);
        populateBusFromTable();
        //init object
        //validate ui when user enters values
        //validate bus obj
        //validate database
        
        for (Iterator<SalesInvoiceLineItem> it = invoice.getLineItems().iterator(); it.hasNext();) {
            SalesInvoiceLineItem si = it.next();
            if (si.getId() == null) {
                it.remove();
            }
        }

        //do we have worry about journal posting
        servicedao.createInventoryJournal(invoice);
        
        clear();
    }

    public void deleteRow(){
    //get selected line id
        //remove it fro the table 
        // re calculate totel 
        // 
        //  move to next line
        
      tblInvoice.removeSelectedRow();
      uiety(invoice);
      invoice.setTotal();
        sTotalToUI();

    }
    

    public void clear() {

        tblInvoice.clear();
        invoice=invoice .createNewInvoicex();
//        invoice.setLineItems(tblInvoice.getModelCollection());
        SalesInvoiceLineItem salesInvoiceLineItem = new SalesInvoiceLineItem();
//        invoice.getLineItems().add(salesInvoiceLineItem);
        tblInvoice.addModelToTable(salesInvoiceLineItem);
//        addToTable(invoice.getLineItems());
        tblInvoice.refreshModel();
        ttax.setText("");
        tsubtotal.setText("");
        tcashrecieved.setText("");
        tfinaltotle.setText("");
        tdis.setText("");
        
//        lineItemPanel.getUnit().setModel(new String[]{});

    }
    
    public void print(){
    printReport();//??
    }

    public void printReport(){
    //jasper.print("reportName")
    //if print dialog has to show ?
        //or hidend print
        //cofirmation
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cPanel1 = new org.components.containers.CPanel();
        cLabel5 = new org.components.controls.CLabel();
        cLabel7 = new org.components.controls.CLabel();
        cLabel8 = new org.components.controls.CLabel();
        cLabel9 = new org.components.controls.CLabel();
        cLabel10 = new org.components.controls.CLabel();
        tfinaltotle = new org.components.controls.CTextField();
        tsubtotal = new org.components.controls.CTextField();
        ttax = new org.components.controls.CTextField();
        tdis = new org.components.controls.CTextField();
        tcashrecieved = new org.components.controls.CTextField();
        cLabel15 = new org.components.controls.CLabel();
        crudcontrolPanel = new com.components.custom.ControlPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInvoice = new org.components.controls.CTableMaster();

        setLayout(null);

        cPanel1.setLayout(null);

        cLabel5.setText("Salesman");
        cLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cPanel1.add(cLabel5);
        cLabel5.setBounds(800, 70, 60, 25);

        cLabel7.setText("Total");
        cLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cPanel1.add(cLabel7);
        cLabel7.setBounds(10, 10, 70, 20);

        cLabel8.setText("Tax");
        cLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cPanel1.add(cLabel8);
        cLabel8.setBounds(10, 40, 70, 20);

        cLabel9.setText("Discount");
        cLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cPanel1.add(cLabel9);
        cLabel9.setBounds(10, 60, 70, 20);

        cLabel10.setText("Final Total");
        cLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cPanel1.add(cLabel10);
        cLabel10.setBounds(10, 90, 70, 20);

        tfinaltotle.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel1.add(tfinaltotle);
        tfinaltotle.setBounds(90, 90, 150, 20);

        tsubtotal.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel1.add(tsubtotal);
        tsubtotal.setBounds(90, 10, 150, 20);

        ttax.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel1.add(ttax);
        ttax.setBounds(90, 40, 150, 20);

        tdis.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel1.add(tdis);
        tdis.setBounds(90, 60, 150, 20);

        tcashrecieved.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel1.add(tcashrecieved);
        tcashrecieved.setBounds(90, 120, 150, 20);

        cLabel15.setText("Recieved");
        cLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cPanel1.add(cLabel15);
        cLabel15.setBounds(10, 120, 70, 20);

        add(cPanel1);
        cPanel1.setBounds(670, 282, 260, 160);
        add(crudcontrolPanel);
        crudcontrolPanel.setBounds(10, 388, 340, 30);

        tblInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Item Code", "Description", "Qty", "Price", "Line Amount"
            }
        ));
        tblInvoice.setRowHeight(50);
        jScrollPane1.setViewportView(tblInvoice);

        jScrollPane1.setBounds(0, 10, 950, 240);
        jLayeredPane1.add(jScrollPane1, javax.swing.JLayeredPane.POPUP_LAYER);

        add(jLayeredPane1);
        jLayeredPane1.setBounds(10, 10, 1030, 270);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cLabel10;
    private org.components.controls.CLabel cLabel15;
    private org.components.controls.CLabel cLabel5;
    private org.components.controls.CLabel cLabel7;
    private org.components.controls.CLabel cLabel8;
    private org.components.controls.CLabel cLabel9;
    private org.components.containers.CPanel cPanel1;
    private com.components.custom.ControlPanel crudcontrolPanel;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CTableMaster tblInvoice;
    private org.components.controls.CTextField tcashrecieved;
    private org.components.controls.CTextField tdis;
    private org.components.controls.CTextField tfinaltotle;
    private org.components.controls.CTextField tsubtotal;
    private org.components.controls.CTextField ttax;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return "Pos invoice V4";
    }
}
/**
 pos inventory where we keep the pos related items
 * from warehouse to pos inventory 
managing the pos inventory -with a pos journal / pos inventory warehouse to keep track the inventory
* so thet we can maintain a barcode system to track the age of the inventory
*
*
* we posting a there will be two entries to make sure the accuracy of the inventory
* 1 - journal
* 2- pos inventory (ware house)

* client may choose between item selection and barcoded  item mark selection
* when user selects a item selection method pos should [ersost this as a default entry in the
* inventory space ie the pos inventory
*
*
*
* what is the conflict when we implement this technology 
 */


/*
 what is the funtionality of this UI
 *
 * what are the actions on the level of top most compnent
 * each child or sub component will have its own behaviour encapsulated in it
 * user experiense 
 *  print
 *  save
 *  delete
 *  view as list
 *  cancel
 *  clear
 *  
 * 
 * mothods 
 * 
 * variables
 * 
 * constructors
 * 
 * object representation and manuplation 
 * invoice 
 *      created 
 *      modified
 *      printed 
 *      viewed 
 *      state changed into another object
 * 
 * 
 * invoice line item  panel
 * 
 * 
 * 
 * 
 */