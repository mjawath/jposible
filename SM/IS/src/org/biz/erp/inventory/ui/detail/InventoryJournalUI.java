/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.erp.inventory.ui.detail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.AbstractAction;
import org.biz.app.ui.util.Command;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.app.ui.util.UIEty;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.service.master.ItemService;
import org.biz.invoicesystem.service.master.ShopService;
import org.biz.invoicesystem.service.master.WareHouseService;
import org.components.util.ComponentFactory;
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
        initLineItemTablePanel();
        inventoryJournalLineItemUI1.setTableContainer(tableContainer1);
        tableContainer1.addNewLineGotoNewLine();
        
        UIEty.cmbModelWithoutNull(tTransaction,new String[]{InventoryJournal.SALES,
            InventoryJournal.PURCHASE,InventoryJournal.ADJUSTMENT} );

        setTabOrder();       

    }
     public void setVisualDataToUI(InventoryJournal object) {
         busObject = (InventoryJournal) object;
        tcode.setValueIfNotFocused(object.getCode());
        tdocumentname.setValueOfText(object.getDocumentClass());
        tdoctype.setValueOfText(object.getDocType());
        twarehouse.setValue(object.getWarehouse());
        tshop.setValue(object.getShop());
        tdate.setDate(object.getEntryDate());
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
        InventoryJournal busObject=null;
        if(selectedObject == null){
             busObject = new InventoryJournal();
        }else{
             busObject =selectedObject;
        }
        busObject.setCode(UIEty.tcToStr(tcode));
        busObject.setRefCode(UIEty.tcToStr(tdocref1));
        busObject.setLines(tableContainer1.getlistBusObject());
        return busObject;
    }

     
    /*
     Line item entry logic 
     * Detail panel, Table Panel
     * 
     */
    public void initLineItemTablePanel() {
        //Table init
        //table events
//        tblLine.init(InventoryJournalLine.class, new Class[]{Item.class, String.class, String.class, String.class},
//                new String[]{"Item", "Item desc", "UOM", "QTY"});

//        tblLine.setTableInteractionListner(new TableInteractionListner() {
//
//
//            @Override
//            public Object[] getTableData(Object row) {
//                InventoryJournalLine sil = (InventoryJournalLine) row;
//                if(sil ==null)return new Object[2];
//                Item itm = sil.getItem();
//                UOM uom = sil.getUom();
//                return new Object[]{sil, itm, itm != null ? itm.getCode() : null,
//                            uom != null ? uom.getCode() : "", sil.getQty()};
//            }
//
//            @Override
//            public void selectionChanged(Object newRowObject) {
//                setLineItemDetail((InventoryJournalLine) newRowObject);
//            }
//        });
//
//        gridControllerPanel1.setTable(tblLine);



        
        
//        tblLine.addNewToLast();


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
    public void events() {
        super.events();
        ActionListener act = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        };
//        titem.setDocAction(act);
//        tqty.setDocAction(act);
//        ComponentFactory.createDoubleTextField(tqty);
//        titem.setActionActionTask(new ActionTask() {
//            @Override
//            public void actionCall(Object e) {
//                //set the uom of the item
//                final Item so = titem.getSelectedObject();
//                List uoms = so != null ? so.getUoms() : new ArrayList();
//                tuom.getPagedPopUpPanel().setList(uoms);
//                tuom.setSelectedObject(so.getCartonUOM());
//            }
//        });
//        setLineAction(null);


        AbstractAction actx = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                tblLine.selectNextRow();
            }
        };


        ComponentFactory.setKeyAction(this, actx, KeyEvent.VK_DOWN, KeyEvent.SHIFT_DOWN_MASK);


        AbstractAction actpre = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                tblLine.selectPreRow();
            }
        };
        ComponentFactory.setKeyAction(this, actpre, KeyEvent.VK_UP, KeyEvent.SHIFT_DOWN_MASK);

//        tuom.setInputVerifier(new InputVerifier() {
//
//            @Override
//            public boolean verify(JComponent input) {
//                if(tuom.getSelectedObject()==null){
//                    Item item= titem.getSelectedObject();
//                    if(item!=null)
//                    tuom.setSelectedObject((UOM)item.getPrimaryUOM());
//                }
//                return true;
//            }
//        });
       
              
    }
    
    

    private void setLineItemDetail(InventoryJournalLine obj) {
        if (obj == null) {
            return;
        }
//        titem.setSelectedObject(obj.getItem());
//        tqty.setValue(obj.getQty());
//        tuom.setSelectedObject(obj.getUom() );
//        tuom.setValue(obj.getUOMCode() );
//        titem.requestFocus();

    }

//    public void setLineAction(ActionListener act) {
//        tqty.setDocAction(new ActionListener() {
////            @Override
//            public void actionPerformed(ActionEvent e) {
////                addtoLine();
//            }
//        });
//        tqty.addaction(0, new ActionTask() {
//            @Override
//            public void actionCall(Object obj) {
//                addtoLine();
//            }
//        });
//    }

//    private void addtoLine() {
//        
//        
//        InventoryJournalLine li=createLine();
//        if(li==null)return;
//        if(li.isValidLine()){
//        //add this to bus object 
//            //        // get top bus object / create top bus object
//            // validate on         // validate line item on  / top bus obj
//            InventoryJournal ij=uiToData();
//            ij.addIJLine(li);            
//            tblLine.addNewOrModifySelectedRow(li);
//  
////        }
//        // add to top bus obj
//        
//        }
//        focusManager.setTemCom(titem);
//    }

//    public InventoryJournalLine createLine() {
//        InventoryJournalLine lineItem = new InventoryJournalLine();
//        lineItem.setItem(titem.getSelectedObject());
//        lineItem.setQty(tqty.getDoubleValue());
//        lineItem.setUom(tuom.getSelectedObject());
//        lineItem.calculateLineItem();
//        return lineItem;
//    }
//
//    public InventoryJournalLine getSalesLine() {
//        InventoryJournalLine lineItem = (InventoryJournalLine) tblLine.getSelectedObject();
//        if (lineItem == null) {
//            return new InventoryJournalLine();
//        }
//        lineItem.setItem(titem.getSelectedObject());
//        lineItem.setQty(tqty.getDoubleValue());
//        lineItem.calculateLineItem();
//        return lineItem;
//    }

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
        tdate.setDate(null);
        
        if (tshop.getSelectedObject() == null) {
            Shop shop = shopservice.getDAO().find("123");
            tshop.setSelectedObject(shop);
        }
        if (twarehouse.getSelectedObject() == null) {
            Warehouse wh = wser.getDao().find("123");
            twarehouse.setSelectedObject(wh);
        }

//        tblLine.addNewToLast();
        selectedObject=null;
//        clearTask.start();
    }

//     Command clearTask=new Command();
    
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
        tdate = new org.components.controls.CDatePicker();
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
        tcode.setBounds(40, 530, 150, 23);
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
        add(tdate);
        tdate.setBounds(840, 140, 140, 23);
        add(twarehouse);
        twarehouse.setBounds(50, 590, 270, 25);
        add(tableContainer1);
        tableContainer1.setBounds(40, 190, 750, 302);
        add(inventoryJournalLineItemUI1);
        inventoryJournalLineItemUI1.setBounds(30, 120, 662, 72);

        tdocref.setText("Ref");
        add(tdocref);
        tdocref.setBounds(210, 500, 104, 25);
        add(tdocref1);
        tdocref1.setBounds(200, 530, 150, 23);

        tdocumentname.setText("Date");
        add(tdocumentname);
        tdocumentname.setBounds(840, 110, 120, 20);

        tTransaction.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Adjustments", "SalesInvoice", "PurchaseInvoice", " " }));
        tTransaction.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        add(tTransaction);
        tTransaction.setBounds(560, 580, 220, 40);

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
    private org.components.controls.CDatePicker tdate;
    private org.components.controls.CLabel tdocref;
    private org.components.controls.CTextField tdocref1;
    private org.components.controls.CLabel tdocref2;
    private org.components.controls.CLabel tdoctype;
    private org.components.controls.CLabel tdocumentname;
    private org.components.controls.CLabel tdocumentname1;
    private com.components.custom.TextFieldWithPopUP<Shop> tshop;
    private research.prototype.transaction.WareHousePopup twarehouse;
    // End of variables declaration//GEN-END:variables
}
