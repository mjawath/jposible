/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.ui.transactions;

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
 * @author Jawad
 */
public class GDNV1 extends DetailPanel<InventoryJournal> {

    /**
     * Creates new form GDNV1
     */
    public GDNV1() {
        super();
        
    }

    public void init(){
        super.init();
        initComponents();
        crudcontrolPanel.setCrudController(this);
        
          setTabOrder();
        //controll pressed

     
      initLineItemTablePanel();
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
                        return ItemService.getItemForPopup(tuom.getText());

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
        clearTask.invoke();
    }

     Command clearTask=new Command();
    
    @Override
    public void setService(Service service) {
//        itemser = new ItemService();
//        wser = new WareHouseService();
//        shopservice=new ShopService();
        //set ui data       
        super.setService(service);        
    }

    @Override
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
      
  
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        ttransactionType = new org.components.controls.CComboBox();
        cLabel1 = new org.components.controls.CLabel();
        cLabel2 = new org.components.controls.CLabel();
        cLabel3 = new org.components.controls.CLabel();
        cLabel4 = new org.components.controls.CLabel();
        tdate = new org.components.controls.CDatePicker();
        twarehouse = new research.prototype.transaction.WareHousePopup();
        crudcontrolPanel = new com.components.custom.ControlPanel();
        tshop = new research.prototype.transaction.ShopPopup();

        lineDetailPanel.setBackground(new java.awt.Color(125, 222, 250));
        lineDetailPanel.setLayout(null);
        lineDetailPanel.add(tqty);
        tqty.setBounds(330, 20, 130, 25);
        lineDetailPanel.add(tuom);
        tuom.setBounds(190, 20, 130, 25);

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

        cLabel4.setText("Doc");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(crudcontrolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lineDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gridControllerPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(cLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(tcode, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tdocref, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tdate, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(twarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tshop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(226, 226, 226)
                        .addComponent(ttransactionType, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(crudcontrolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineDetailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gridControllerPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tdocref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(twarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tshop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ttransactionType, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68))
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
    private com.components.custom.TextFieldWithPopUP<UOM> tuom;
    private research.prototype.transaction.WareHousePopup twarehouse;
    // End of variables declaration//GEN-END:variables
}
