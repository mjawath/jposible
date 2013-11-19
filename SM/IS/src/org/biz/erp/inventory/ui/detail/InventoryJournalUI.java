/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.erp.inventory.ui.detail;

import com.components.custom.ActionTask;
import com.components.custom.PopupListner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.UOM;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.service.inventory.InventoryJournalService;
import org.biz.invoicesystem.service.master.ItemService;
import org.biz.invoicesystem.service.master.WareHouseService;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.windows.DetailPanel;

/**
 *
 * @author d
 */
public class InventoryJournalUI extends DetailPanel<InventoryJournal> {

    private InventoryJournalService iservice;
    private ItemService itemser;
    private WareHouseService wser;

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
        
         twarehouse.initPopup(Warehouse.class, new Class[]{String.class, String.class}, new String[]{"id","code"}, "code", new PopupListner() {
            @Override
            public List searchItem(Object searchQry) {                 
                List uoms=wser.getDao().getAll();
                return uoms;

            }

            @Override
            public Object[] getTableData(Object obj) {
                Warehouse item = (Warehouse) obj;
                return new Object[]{item, item.getId(), item.getCode()};
            }
        });
       
        
        
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
        tblLine.init(InventoryJournalLine.class, new Class[]{Item.class,String.class, String.class, String.class},
                     new String[]{"Item","Item desc","UOM", "QTY"});

        tblLine.setTableInteractionListner(new TableInteractionListner() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Object[] getTableData(Object row) {
                InventoryJournalLine sil = (InventoryJournalLine) row;
                Item itm=sil.getItem();
                UOM uom=sil.getUom(); 
                return new Object[]{sil, itm,itm!=null?itm.getDescription():null,uom!=null?uom.getSimbol():"", sil.getQty()};
            }

            @Override
            public void onCellEditing(Object cel, int col) {
            }

            @Override
            public void selectionChanged(Object newRowObject) {
            setLineItemDetail((InventoryJournalLine) newRowObject); 
            }
        });

        gridControllerPanel1.setTable(tblLine);



        //detailpanel init
        //detail panel events

        titem.initPopup(Item.class, new Class[]{String.class, String.class, String.class}, new String[]{"code", "id", "desc"}, "code", new PopupListner() {
            @Override
            public List searchItem(Object searchQry) {
                return itemser.getDao().getAll();

            }

            @Override
            public Object[] getTableData(Object obj) {
                Item item = (Item) obj;
                return new Object[]{item, item.getId(), item.getCode()};
            }
        });
        
         tuom.initPopup(UOM.class, new Class[]{String.class, String.class}, new String[]{"id","code"}, "code", new PopupListner() {
            @Override
            public List searchItem(Object searchQry) {
                

                return null;

            }

            @Override
            public Object[] getTableData(Object obj) {
                UOM item = (UOM) obj;
                return new Object[]{item, item.getId(), item.getSimbol()};
            }
        });
       
        tblLine.addNewToLast();


    }

    private void setTabOrder() {
        addToFocus(titem);
        addToFocus(tqty);
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
        titem.setActionActionTask(new ActionTask(){

            @Override
            public void actionPerformed(ActionEvent e) {
        //set the uom of the item
         final Item so = titem.getSelectedObject();                
         List uoms=so!=null?so.getUoms():new ArrayList();
                
           tuom.getPagedPopUpPanel().setList(uoms);     
        }
        
        });
        setLineAction(null);

    }
    
    private void setLineItemDetail(InventoryJournalLine obj){
        if(obj==null)return;
        titem.setSelectedObject(obj.getItem());
        tqty.setValue(obj.getQty());
        titem.requestFocus();
     
    }

    public void setLineAction(ActionListener act) {
        tqty.setDocAction(new ActionListener() {
//            @Override
            public void actionPerformed(ActionEvent e) {
//                addtoLine();
            }
        });
        tqty.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            addtoLine();            
            }
        });
    }

    private void addtoLine() {
        ///add or update 
        InventoryJournalLine lit = (InventoryJournalLine) tblLine.getSelectedObject();
        updateRow(lit);
        focusManager.setTemCom(titem);
    }
    
    private void updateRow(InventoryJournalLine lineItem ){
        if(lineItem==null)return;
        lineItem.setItem(titem.getSelectedObject());
        lineItem.setQty(tqty.getDoubleValue());
        lineItem.calculateLineItem();
        tblLine.replaceModelSele(lineItem);    
     
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
        InventoryJournal ij=new InventoryJournal();
        ij.setCode(tcode.getValue());
        ij.setDocRefNo(tdocref.getValue());
        ij.setWarehouse(twarehouse.getSelectedObject());
        List list=tblLine.getModelCollection();
        ij.setLines(list);       
        return ij;
    }

    public void clear(){
    tcode.clear();
    tdocref.clear();
    tblLine.clear();
    tqty.clear();
    titem.clear();
    tuom.clear();
    tblLine.addNewToLast();
    
    }
    
    @Override
    public void setService(Service service) {
        super.setService(service);
        iservice = (InventoryJournalService) service;
        itemser = new ItemService();
        wser=new WareHouseService();
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
        titem = new com.components.custom.TextFieldWithPopUP<Item>();
        tuom = new com.components.custom.TextFieldWithPopUP<UOM>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLine = new org.components.controls.ModelEditableTable();
        gridControllerPanel1 = new com.components.custom.GridControllerPanel();
        tcode = new org.components.controls.CTextField();
        tdocref = new org.components.controls.CTextField();
        twarehouse = new com.components.custom.TextFieldWithPopUP<Warehouse>();

        lineDetailPanel.setBackground(new java.awt.Color(153, 255, 0));
        lineDetailPanel.setLayout(null);

        tqty.setText("Qty");
        lineDetailPanel.add(tqty);
        tqty.setBounds(340, 10, 130, 30);

        titem.setText("Item");
        lineDetailPanel.add(titem);
        titem.setBounds(40, 10, 150, 30);

        tuom.setText("Unit");
        lineDetailPanel.add(tuom);
        tuom.setBounds(200, 10, 130, 30);

        add(lineDetailPanel);
        lineDetailPanel.setBounds(10, 80, 650, 50);

        tblLine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblLine);

        add(jScrollPane2);
        jScrollPane2.setBounds(10, 150, 720, 219);
        add(gridControllerPanel1);
        gridControllerPanel1.setBounds(740, 150, 90, 230);

        tcode.setText("Code");
        add(tcode);
        tcode.setBounds(30, 20, 130, 25);

        tdocref.setText("Doc Ref");
        add(tdocref);
        tdocref.setBounds(200, 20, 130, 25);

        twarehouse.setText("Item");
        add(twarehouse);
        twarehouse.setBounds(30, 430, 150, 30);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.components.custom.GridControllerPanel gridControllerPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.components.containers.CPanel lineDetailPanel;
    private org.components.controls.ModelEditableTable tblLine;
    private org.components.controls.CTextField tcode;
    private org.components.controls.CTextField tdocref;
    private com.components.custom.TextFieldWithPopUP<Item> titem;
    private org.components.controls.CTextField tqty;
    private com.components.custom.TextFieldWithPopUP<UOM> tuom;
    private com.components.custom.TextFieldWithPopUP<Warehouse> twarehouse;
    // End of variables declaration//GEN-END:variables
}
