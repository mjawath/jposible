/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.erp.inventory.ui.detail;

import com.components.custom.ActionTask;
import com.components.custom.PopupListner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import org.biz.app.ui.util.Command;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.UOM;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.service.master.ItemService;
import org.biz.invoicesystem.service.master.ShopService;
import org.biz.invoicesystem.service.master.WareHouseService;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.util.ComponentFactory;
import org.components.windows.DetailPanel;

/**
 *
 * @author d
 */
 public class InventoryJournalUI extends DetailPanel<InventoryJournal> {

    private ItemService itemser;
    private WareHouseService wser;
    private ShopService shopservice;
    
    
    /**
     * Creates new form InventoryJournalUI
     */
    public InventoryJournalUI() {
        super();
    }

    @Override
    public void init() {
        initComponents();
        super.init();
        initLineItemTablePanel();

        twarehouse.initPopup(Warehouse.class, new Class[]{String.class, String.class}, new String[]{"id", "code"}, "code",
                new PopupListner() {
            @Override
            public List searchItem(Object searchQry) {
                List items = wser.getDao().getAll();
                return items;
            }

            @Override
            public Object[] getTableData(Object obj) {
                Warehouse item = (Warehouse) obj;
                return new Object[]{item, item.getId(), item.getCode()};
            }
        });



        setTabOrder();
        //controll pressed

      tuom.setEditable(false);

    }

    /*
     Line item entry logic 
     * Detail panel, Table Panel
     * 
     */
    public void initLineItemTablePanel() {
        //Table init
        //table events
        tblLine.init(InventoryJournalLine.class, new Class[]{Item.class, String.class, String.class, String.class},
                new String[]{"Item", "Item desc", "UOM", "QTY"});

        tblLine.setTableInteractionListner(new TableInteractionListner() {
            @Override
            public boolean onBeforeRowSelectionChange() {
//                if(tblLine.getSelectedObject()==null)return true;
                return true;//isValidLine((InventoryJournalLine)tblLine.getSelectedObject());
            }

            @Override
            public Object[] getTableData(Object row) {
                InventoryJournalLine sil = (InventoryJournalLine) row;
                Item itm = sil.getItem();
                UOM uom = sil.getUom();
                return new Object[]{sil, itm, itm != null ? itm.getCode() : null,
                            uom != null ? uom.getCode() : "", sil.getQty()};
            }

            @Override
            public void selectionChanged(Object newRowObject) {
                setLineItemDetail((InventoryJournalLine) newRowObject);
            }
        });

        gridControllerPanel1.setTable(tblLine);



        //detailpanel init
        //detail panel events
        tuom.initPopup(Item.class, new Class[]{String.class, String.class},new String[]{"code", "desc"}, "code",
                new PopupListner() {
                    @Override
                    public List searchItem(Object searchQry) {
                        return itemser.getItemForPopup(tuom.getText());

                    }

                    @Override
                    public Object[] getTableData(Object obj) {
                        Item item = (Item) obj;
                        return new Object[]{item, item.getCode(), item.getDescription()};
                    }
                });

        tuom.initPopup(UOM.class, new Class[]{String.class}, new String[]{"code"}, "code", new PopupListner() {
            @Override
            public List searchItem(Object searchQry) {
                return null;

            }

            @Override
            public Object[] getTableData(Object obj) {
                UOM item = (UOM) obj;
                return new Object[]{item , item.getCode()};
            }
        });

        tshop.initPopup(Shop.class, new Class[]{String.class, String.class}, new String[]{"id", "code"}, "code", new PopupListner() {
            @Override
            public List searchItem(Object searchQry) {

                return shopservice.getDao().getAll();

            }

            @Override
            public Object[] getTableData(Object obj) {
                Shop item = (Shop) obj;
                return new Object[]{item, item.getId(), item.getCode()};
            }
        });
        
        
        tblLine.addNewToLast();


    }
    
    

    public Command itemCommand = new Command(){

        @Override
        public Object executeTask() {
            return super.executeTask(); //To change body of generated methods, choose Tools | Templates.
        }
    
    };
    
    private void setTabOrder() {
        addToFocus(titem);
//        addToFocus(tuom);
        addToFocus(tqty);
        addToFocus(tcode);
        addToFocus(tdocref);
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
        titem.setDocAction(act);
        tqty.setDocAction(act);
        ComponentFactory.createDoubleTextField(tqty);
        titem.setActionActionTask(new ActionTask() {
            @Override
            public void actionCall(Object e) {
                //set the uom of the item
                final Item so = titem.getSelectedObject();
                List uoms = so != null ? so.getUoms() : new ArrayList();
                tuom.getPagedPopUpPanel().setList(uoms);
                tuom.setSelectedObject(so.getCartonUOM());
            }
        });
        setLineAction(null);


        AbstractAction actx = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblLine.selectNextRow();
            }
        };


        ComponentFactory.setKeyAction(this, actx, KeyEvent.VK_DOWN, KeyEvent.SHIFT_DOWN_MASK);


        AbstractAction actpre = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblLine.selectPreRow();
            }
        };
        ComponentFactory.setKeyAction(this, actpre, KeyEvent.VK_UP, KeyEvent.SHIFT_DOWN_MASK);

        tuom.setInputVerifier(new InputVerifier() {

            @Override
            public boolean verify(JComponent input) {
                if(tuom.getSelectedObject()==null){
                    Item item= titem.getSelectedObject();
                    if(item!=null)
                    tuom.setSelectedObject((UOM)item.getPrimaryUOM());
                }
                return true;
            }
        });
       
              
    }
    
    

    private void setLineItemDetail(InventoryJournalLine obj) {
        if (obj == null) {
            return;
        }
        titem.setSelectedObject(obj.getItem());
        tqty.setValue(obj.getQty());
        tuom.setSelectedObject(obj.getUom() );
        tuom.setValue(obj.getUOMCode() );
        titem.requestFocus();

    }

    public void setLineAction(ActionListener act) {
        tqty.setDocAction(new ActionListener() {
//            @Override
            public void actionPerformed(ActionEvent e) {
//                addtoLine();
            }
        });
        tqty.addaction(0, new ActionTask() {
            @Override
            public void actionCall(Object obj) {
                addtoLine();
            }
        });
    }

    private void addtoLine() {
        
        
        InventoryJournalLine li=createLine();
        if(li==null)return;
        if(li.isValidLine()){
        //add this to bus object 
            //        // get top bus object / create top bus object
            // validate on         // validate line item on  / top bus obj
            InventoryJournal ij=getBusObject();
            ij.addIJLine(li);            
            tblLine.addNewOrModifySelectedRow(li);
  
//        }
        // add to top bus obj
        
        }
        focusManager.setTemCom(titem);
    }

    public InventoryJournalLine createLine() {
        InventoryJournalLine lineItem = new InventoryJournalLine();
        lineItem.setItem(titem.getSelectedObject());
        lineItem.setQty(tqty.getDoubleValue());
        lineItem.setUom(tuom.getSelectedObject());
        lineItem.calculateLineItem();
        return lineItem;
    }

    public InventoryJournalLine getSalesLine() {
        InventoryJournalLine lineItem = (InventoryJournalLine) tblLine.getSelectedObject();
        if (lineItem == null) {
            return new InventoryJournalLine();
        }
        lineItem.setItem(titem.getSelectedObject());
        lineItem.setQty(tqty.getDoubleValue());
        lineItem.calculateLineItem();
        return lineItem;
    }

    @Override
    public InventoryJournal getBusObject() {
        InventoryJournal ij = new InventoryJournal();
        ij.setCode(tcode.getValue());
        ij.setDocRefNo(tdocref.getValue());
        ij.setWarehouse(twarehouse.   getSelectedObject());
        ij.setShop(tshop.getSelectedObject());
        List list = tblLine.getModelCollection();
        ij.setLines(list);
        ij.setEntryDate(tdate.getDate());//todo 

        //set line values according to in/out
//        if (ttransactionType.getSelectedIndex() == 0) {
//            ij.setTransactionOutType();            
//        }
        return ij;
    }

     @Override
    public void setBusObject(InventoryJournal obj) {
        tcode.setValue(obj.getCode());
        tdocref.setValue(obj.getDocRefNo());
        twarehouse.setSelectedObject(obj.getWarehouse());
        obj.setTransactionLinePlus();
        tdate.setDate(obj.getEntryDate());//todo 
        tblLine.setModelCollection(obj.getLines());
        tblLine.addNewToLast();
        selectedObject=obj;
    }

    public void clear() {
        tcode.clear();
        tdocref.clear();
        tblLine.clear();
        tqty.clear();
        tuom.clear();
        tuom.clear();
        tdate.setDate(null);
        
        if (tshop.getSelectedObject() == null) {
            Shop shop = shopservice.getDAO().find("123");
            tshop.setSelectedObject(shop);
        }
        if (twarehouse.getSelectedObject() == null) {
            Warehouse wh = wser.getDao().find("123");
            twarehouse.setSelectedObject(wh);
        }

        tblLine.addNewToLast();
        selectedObject=null;
        clearTask.invoke();
    }

     Command clearTask=new Command();
    
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
            tuom.requestFocus();
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
        
       
        if (ttransactionType.getSelectedIndex() == 0) {
            busObject.setTransactionOutType();
        }
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lineDetailPanel = new org.components.containers.CPanel();
        tqty = new org.components.controls.CTextField();
        tuom = new com.components.custom.TextFieldWithPopUP<UOM>();
        cLabel5 = new org.components.controls.CLabel();
        cLabel6 = new org.components.controls.CLabel();
        cLabel7 = new org.components.controls.CLabel();
        titem = new research.prototype.transaction.ItemPopup();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLine = new org.components.controls.ModelEditableTable();
        gridControllerPanel1 = new com.components.custom.GridControllerPanel();
        tcode = new org.components.controls.CTextField();
        tdocref = new org.components.controls.CTextField();
        tshop = new com.components.custom.TextFieldWithPopUP<Shop>();
        ttransactionType = new org.components.controls.CComboBox();
        cLabel1 = new org.components.controls.CLabel();
        cLabel2 = new org.components.controls.CLabel();
        cLabel3 = new org.components.controls.CLabel();
        cLabel4 = new org.components.controls.CLabel();
        tdate = new org.components.controls.CDatePicker();
        twarehouse = new research.prototype.transaction.WareHousePopup();

        setLayout(null);

        lineDetailPanel.setBackground(new java.awt.Color(153, 255, 0));
        lineDetailPanel.setLayout(null);
        lineDetailPanel.add(tqty);
        tqty.setBounds(330, 20, 130, 25);
        lineDetailPanel.add(tuom);
        tuom.setBounds(190, 20, 130, 25);

        cLabel5.setText("Item");
        lineDetailPanel.add(cLabel5);
        cLabel5.setBounds(0, 0, 70, 25);

        cLabel6.setText("UOM");
        lineDetailPanel.add(cLabel6);
        cLabel6.setBounds(190, 0, 104, 20);

        cLabel7.setText("Qty");
        lineDetailPanel.add(cLabel7);
        cLabel7.setBounds(330, 0, 80, 20);
        lineDetailPanel.add(titem);
        titem.setBounds(40, 20, 134, 25);

        add(lineDetailPanel);
        lineDetailPanel.setBounds(10, 40, 650, 60);

        tblLine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblLine);

        add(jScrollPane2);
        jScrollPane2.setBounds(10, 110, 720, 230);
        add(gridControllerPanel1);
        gridControllerPanel1.setBounds(730, 110, 90, 230);
        add(tcode);
        tcode.setBounds(30, 380, 150, 25);
        add(tdocref);
        tdocref.setBounds(190, 380, 150, 25);
        add(tshop);
        tshop.setBounds(200, 440, 150, 30);

        ttransactionType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "OUT", "IN" }));
        ttransactionType.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        add(ttransactionType);
        ttransactionType.setBounds(560, 410, 230, 60);

        cLabel1.setText("Shop");
        add(cLabel1);
        cLabel1.setBounds(200, 410, 104, 25);

        cLabel2.setText("Warehouse");
        add(cLabel2);
        cLabel2.setBounds(20, 410, 104, 25);

        cLabel3.setText("Ref");
        add(cLabel3);
        cLabel3.setBounds(190, 350, 104, 25);

        cLabel4.setText("Doc");
        add(cLabel4);
        cLabel4.setBounds(10, 350, 104, 25);
        add(tdate);
        tdate.setBounds(350, 380, 140, 22);
        add(twarehouse);
        twarehouse.setBounds(40, 440, 134, 25);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel3;
    private org.components.controls.CLabel cLabel4;
    private org.components.controls.CLabel cLabel5;
    private org.components.controls.CLabel cLabel6;
    private org.components.controls.CLabel cLabel7;
    private com.components.custom.GridControllerPanel gridControllerPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.components.containers.CPanel lineDetailPanel;
    private org.components.controls.ModelEditableTable tblLine;
    private org.components.controls.CTextField tcode;
    private org.components.controls.CDatePicker tdate;
    private org.components.controls.CTextField tdocref;
    private research.prototype.transaction.ItemPopup titem;
    private org.components.controls.CTextField tqty;
    private com.components.custom.TextFieldWithPopUP<Shop> tshop;
    private org.components.controls.CComboBox ttransactionType;
    private com.components.custom.TextFieldWithPopUP<UOM> tuom;
    private research.prototype.transaction.WareHousePopup twarehouse;
    // End of variables declaration//GEN-END:variables
}
