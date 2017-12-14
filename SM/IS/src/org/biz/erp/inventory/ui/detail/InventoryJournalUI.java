/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.erp.inventory.ui.detail;

import com.biz.system.ISProperties;
import java.util.ArrayList;
import java.util.Iterator;
import org.biz.MS_Static;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.app.ui.util.UIEty;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.master.ui.WareHoueLV;
import org.biz.invoicesystem.master.ui.WareHouseController;
import org.biz.invoicesystem.service.master.ItemService;
import org.biz.invoicesystem.service.master.ShopService;
import org.biz.invoicesystem.service.master.WareHouseService;
import org.components.windows.DetailPanel;
import org.components.windows.UIController;

/**
 *
 * @author d
 */
 public class InventoryJournalUI extends DetailPanel<InventoryJournal> {

    private ItemService itemser;
    private WareHouseService wser;
    private ShopService shopservice;
    private InventoryJournalController journalController;
    private WareHouseController wareHouseController;
    
    /**
     * Creates new form InventoryJournalUI
     */
    public InventoryJournalUI() {
//        super();
    }

    @Override
    public void init() {
        initComponents();
        super.init();
        inventoryJournalLineItemUI1.setTableContainer(tableContainer1);
        tableContainer1.addNewLineGotoNewLine();
        
        UIEty.cmbModelWithoutNull(tTransaction,new String[]{InventoryJournal.ADJUSTMENT,
            InventoryJournal.PURCHASE,InventoryJournal.SALES} );
        wareHouseController = new WareHouseController();

        twarehouse.setListViewQueryManger(wareHouseController.getPopupQueryManger(), new WareHoueLV());
        twarehouse.setSelectedProperty("code");
        setTabOrder();       

    }
     public void setVisualDataToUI(InventoryJournal object) {
         busObject = (InventoryJournal) object;
        tcode.setValueIfNotFocused(object.getCode());
        tdocumentname.setValueOfText(object.getDocumentClass());
        tdoctype.setValueOfText(object.getDocType());
        twarehouse.setSelectedObject(object.getWarehouse());
        tshop.setValue(object.getShop());
        tentrydate.setDate(object.getEntryDate());
//        tDiscountPer.setValueIfNotFocused(object.getDiscountPer());
//        tdiscAmount.setValueIfNotFocused(object.getDiscount());
//        UIEty.objToUi(tlblTotal, object.getTotal());
         tableContainer1.setCollection(object.getLines());

//        super.setDataToUI(object);
     }

     public void setDataToUI(InventoryJournal object) {
         setVisualDataToUI(object);
//        gridDataContainerUI1.setCollection(object.getLineItems());
//        super.setDataToUI(object);
     }

     
    public InventoryJournal uiToData() {

        //TODO- 
        InventoryJournal busObject=super.uiToData();
        busObject.setCode(UIEty.tcToStr(tcode));
        busObject.setWarehouse(twarehouse.getSelectedObject());
        busObject.setRefCode(UIEty.tcToStr(tdocref1));
        busObject.setLines(tableContainer1.getlistBusObject());
        busObject.setEntryDate(tentrydate.getDate());
        return busObject;
    }

     
    private void setTabOrder() {
//        addToFocus(titem);
//        addToFocus(tuom);
//        addToFocus(tqty);
        addToFocus(tcode);
        addToFocus(tdocref1);
        addToFocus(twarehouse);
        addToFocus(tshop);
    }

       

    @Override
    public void setController(UIController controller) {
        super.setController(controller);
        journalController = (InventoryJournalController) controller;
        busObject = journalController.getCurrentBusObject();
        inventoryJournalLineItemUI1.setSalesInvoiceController(journalController);

    }

    public void clear() {
        tcode.clear();
        tdocref.clear();
//        tblLine.clear();
//        tqty.clear();
//        tuom.clear();
//        tuom.clear();
        tentrydate.setDate(null);
        
        setDefaultData();

//        tblLine.addNewToLast();
        selectedObject=null;
//        clearTask.start();
    }

    @Override
    public void setService(Service service) {
        itemser = new ItemService();
        wser = new WareHouseService();
        shopservice=new ShopService();
        //set ui data       
        super.setService(service);        
    }

    @Override
    public Object[] loadAfterService() {
        //get shop from properties /db
        //get warehouse from properties /db
        Shop shop = shopservice.getDAO().find("123");
        Warehouse wh = wser.getDao().find("123");        
        
        return new Object[]{shop, wh};
    }

    @Override
    public void loadUIAfterService(Object[] objs) {
      tshop.setSelectedObject((Shop)objs[0]);  
      twarehouse.setSelectedObject((Warehouse)objs[1]);  
    }
      
    @Override
    public boolean isValideEntity() {

        if (busObject == null) {
            return false;
        }
        if (busObject.getLines() == null || busObject.getLines().isEmpty()) {
//            tuom.requestFocus();
            return false;
        }
        
        if (busObject.getWarehouse() == null) {
            MessageBoxes.errormsg(this, "Please provide a valid store room", "Invalid data");
            twarehouse.requestFocus();
            return false;
        }
            
        for (Iterator<InventoryJournalLine> it = busObject.getLines().iterator(); it.hasNext();) {
            InventoryJournalLine inventoryJournalLine = it.next();
            if(!inventoryJournalLine.isValidLine())it.remove();

        }
        return true;
    }

    @Override
    public void preCreate(ArrayList objCreates, ArrayList objUpdates, ArrayList objDeletes) {
               
        if (tInOrOut.getSelectedIndex() == 0) {
            busObject.setTransactionOutType();
        }
    }
    
    private void setDefaultData(){
        twarehouse.setSelectedObject(MS_Static.getDefaultWareHouse());
        tshop.setSelectedObject(MS_Static.getDefaultShop());
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tcode = new org.components.controls.CTextField();
        tshop = new com.components.custom.TextFieldWithPopUP<>();
        tInOrOut = new org.components.controls.CComboBox();
        cLabel1 = new org.components.controls.CLabel();
        cLabel2 = new org.components.controls.CLabel();
        tdoctype = new org.components.controls.CLabel();
        cLabel4 = new org.components.controls.CLabel();
        tentrydate = new org.components.controls.CDatePicker();
        twarehouse = new research.prototype.transaction.WareHousePopup();
        tableContainer1 = new org.biz.ui.prototype.TableContainer();
        inventoryJournalLineItemUI1 = new org.biz.erp.inventory.ui.detail.InventoryJournalLineItemUI();
        tdocref = new org.components.controls.CLabel();
        tdocref1 = new org.components.controls.CTextField();
        tdocumentname = new org.components.controls.CLabel();
        tTransaction = new org.components.controls.CComboBox();
        tdocumentname1 = new org.components.controls.CLabel();
        tdocref2 = new org.components.controls.CLabel();

        setLayout(null);
        add(tcode);
        tcode.setBounds(40, 523, 150, 30);
        add(tshop);
        tshop.setBounds(350, 590, 150, 30);

        tInOrOut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "OUT", "IN" }));
        tInOrOut.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        add(tInOrOut);
        tInOrOut.setBounds(840, 220, 160, 40);

        cLabel1.setText("Shop");
        add(cLabel1);
        cLabel1.setBounds(340, 560, 104, 25);

        cLabel2.setText("Warehouse");
        add(cLabel2);
        cLabel2.setBounds(30, 560, 104, 25);

        tdoctype.setText("Transaction Type");
        add(tdoctype);
        tdoctype.setBounds(570, 520, 210, 25);

        cLabel4.setText("Inventory Transaction");
        add(cLabel4);
        cLabel4.setBounds(840, 190, 190, 25);
        add(tentrydate);
        tentrydate.setBounds(840, 140, 140, 23);
        add(twarehouse);
        twarehouse.setBounds(50, 590, 270, 25);
        add(tableContainer1);
        tableContainer1.setBounds(40, 190, 750, 302);
        add(inventoryJournalLineItemUI1);
        inventoryJournalLineItemUI1.setBounds(40, 120, 720, 70);

        tdocref.setText("Ref");
        add(tdocref);
        tdocref.setBounds(210, 500, 104, 25);
        add(tdocref1);
        tdocref1.setBounds(200, 523, 150, 30);

        tdocumentname.setText("Document Name");
        add(tdocumentname);
        tdocumentname.setBounds(800, 340, 230, 20);

        tTransaction.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Adjustments", "SalesInvoice", "PurchaseInvoice", " " }));
        tTransaction.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        add(tTransaction);
        tTransaction.setBounds(560, 580, 250, 40);

        tdocumentname1.setText("Ref");
        add(tdocumentname1);
        tdocumentname1.setBounds(840, 280, 190, 30);

        tdocref2.setText("Code");
        add(tdocref2);
        tdocref2.setBounds(70, 500, 104, 25);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel4;
    private org.biz.erp.inventory.ui.detail.InventoryJournalLineItemUI inventoryJournalLineItemUI1;
    private org.components.controls.CComboBox tInOrOut;
    private org.components.controls.CComboBox tTransaction;
    private org.biz.ui.prototype.TableContainer tableContainer1;
    private org.components.controls.CTextField tcode;
    private org.components.controls.CLabel tdocref;
    private org.components.controls.CTextField tdocref1;
    private org.components.controls.CLabel tdocref2;
    private org.components.controls.CLabel tdoctype;
    private org.components.controls.CLabel tdocumentname;
    private org.components.controls.CLabel tdocumentname1;
    private org.components.controls.CDatePicker tentrydate;
    private com.components.custom.TextFieldWithPopUP<Shop> tshop;
    private research.prototype.transaction.WareHousePopup twarehouse;
    // End of variables declaration//GEN-END:variables
}
