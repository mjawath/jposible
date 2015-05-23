/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.ui.transactions;

import com.components.custom.ActionTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.UOM;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.service.master.ShopService;
import org.biz.invoicesystem.service.master.WareHouseService;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.util.ComponentFactory;
import org.components.windows.DetailPanel;

/**
 *
 * @author Jawad
 */
public class GDNV1 extends DetailPanel<InventoryJournal> {

    /**
     * Creates new form GDNV1
     */
    public GDNV1() {
        super();
        
    }

    public void init() {
        super.init();
        initComponents();
        crudcontrolPanel.setCrudController(this);

        //controll pressed
        initLineItemTablePanel();
        setTabOrder();
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

        tblLine.setTableInteractionListner(new TIL());
        gridControllerPanel1.setTable(tblLine);

        tblLine.addNewToLast();


    }
    
    

       
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
    
    class  TIL extends TableInteractionListner {
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
        };

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
        tqty.setActionTask(new ActionTask() {
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
            ij.addIJLine(li);  //if ij valid proceed          
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
        ij.setWarehouse(twarehouse.getSelectedObject());
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
            Shop shop = ShopService.getDAO().find("123");
            tshop.setSelectedObject(shop);
        }
        if (twarehouse.getSelectedObject() == null) {
            Warehouse wh = WareHouseService.getDAO().find("123");
            twarehouse.setSelectedObject(wh);
        }

        tblLine.addNewToLast();
        selectedObject=null;
    }

    
    public Object[] loadAfterService() {
        //get shop from properties /db
        //get warehouse from properties /db
        Shop shop = ShopService.getDAO().find("123");
        Warehouse wh = WareHouseService.getDAO().find("123");        
        
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
        
        
        if (busObject.getWarehouse() == null) {
            MessageBoxes.errormsg(this, "Please provide a valid store room", "Invalid data");
            twarehouse.requestFocus();
            return false;
        }
        
         if (busObject.getShop()== null) {
            MessageBoxes.errormsg(this, "Please provide a valid Shop room", "Invalid data");
            tshop.requestFocus();
            return false;
        }
        for (Iterator<InventoryJournalLine> it = busObject.getLines().iterator(); it.hasNext();) {
            InventoryJournalLine inventoryJournalLine = it.next();
            if(!inventoryJournalLine.isValidLine())it.remove();

        }
        if (busObject.getLines() == null || busObject.getLines().isEmpty()) {
            titem.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void preCreate(ArrayList objCreates, ArrayList objUpdates, ArrayList objDeletes) {        
       
        if (ttransactionType.getSelectedIndex() == 0) {
            busObject.setTransactionOutType();
        }
    }  
  
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lineDetailPanel = new org.components.containers.CPanel();
        tqty = new org.components.controls.CTextField();
        cLabel5 = new org.components.controls.CLabel();
        cLabel6 = new org.components.controls.CLabel();
        cLabel7 = new org.components.controls.CLabel();
        titem = new research.prototype.transaction.ItemPopup();
        tuom = new research.prototype.transaction.UOMPopup();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLine = new org.components.controls.ModelEditableTable();
        gridControllerPanel1 = new com.components.custom.GridControllerPanel();
        tcode = new org.components.controls.CTextField();
        tdocref = new org.components.controls.CTextField();
        ttransactionType = new org.components.controls.CComboBox();
        cLabel1 = new org.components.controls.CLabel();
        cLabel2 = new org.components.controls.CLabel();
        cLabel3 = new org.components.controls.CLabel();
        cLabel4 = new org.components.controls.CLabel();
        tdate = new org.components.controls.CDatePicker();
        twarehouse = new research.prototype.transaction.WareHousePopup();
        crudcontrolPanel = new com.components.custom.ControlPanel();
        tshop = new research.prototype.transaction.ShopPopup();
        cLabel8 = new org.components.controls.CLabel();

        lineDetailPanel.setBackground(new java.awt.Color(125, 222, 250));
        lineDetailPanel.setLayout(null);
        lineDetailPanel.add(tqty);
        tqty.setBounds(330, 20, 130, 25);

        cLabel5.setText("Item");
        lineDetailPanel.add(cLabel5);
        cLabel5.setBounds(10, 0, 70, 20);

        cLabel6.setText("UOM");
        lineDetailPanel.add(cLabel6);
        cLabel6.setBounds(190, 0, 104, 20);

        cLabel7.setText("Qty");
        lineDetailPanel.add(cLabel7);
        cLabel7.setBounds(330, 0, 80, 20);
        lineDetailPanel.add(titem);
        titem.setBounds(40, 20, 134, 25);
        lineDetailPanel.add(tuom);
        tuom.setBounds(180, 20, 134, 25);

        tblLine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblLine);

        ttransactionType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "OUT", "IN" }));
        ttransactionType.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N

        cLabel1.setText("Shop");

        cLabel2.setText("Warehouse");

        cLabel3.setText("Ref");

        cLabel4.setText("Doc No");

        cLabel8.setText("Doc Date");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lineDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gridControllerPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tshop, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(twarehouse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tcode, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tdocref, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addComponent(cLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tdate, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43))
                            .addComponent(ttransactionType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(crudcontrolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(crudcontrolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineDetailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gridControllerPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tdocref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(twarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tshop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(ttransactionType, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel3;
    private org.components.controls.CLabel cLabel4;
    private org.components.controls.CLabel cLabel5;
    private org.components.controls.CLabel cLabel6;
    private org.components.controls.CLabel cLabel7;
    private org.components.controls.CLabel cLabel8;
    protected com.components.custom.ControlPanel crudcontrolPanel;
    private com.components.custom.GridControllerPanel gridControllerPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.components.containers.CPanel lineDetailPanel;
    private org.components.controls.ModelEditableTable tblLine;
    private org.components.controls.CTextField tcode;
    private org.components.controls.CDatePicker tdate;
    private org.components.controls.CTextField tdocref;
    private research.prototype.transaction.ItemPopup titem;
    private org.components.controls.CTextField tqty;
    private research.prototype.transaction.ShopPopup tshop;
    private org.components.controls.CComboBox ttransactionType;
    private research.prototype.transaction.UOMPopup tuom;
    private research.prototype.transaction.WareHousePopup twarehouse;
    // End of variables declaration//GEN-END:variables
}
