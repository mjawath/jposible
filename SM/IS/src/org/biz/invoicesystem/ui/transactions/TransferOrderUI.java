
/*
 * TransferOrderUI.java
 *
 * Created on Apr 14, 2012, 3:25:49 PM
 */
package org.biz.invoicesystem.ui.transactions;

import com.components.custom.ActionTask;
import java.util.ArrayList;
import java.util.List;
import org.biz.app.ui.util.UIEty;
import org.biz.invoicesystem.entity.inventory.TransferOrder;
import org.biz.invoicesystem.entity.inventory.TransferOrderLineItem;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.UOM;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.service.inventory.TransferOrderService;
import org.biz.invoicesystem.service.master.ItemService;
import org.biz.invoicesystem.service.master.ShopService;
import org.biz.invoicesystem.service.master.WareHouseService;
import org.components.windows.TabPanelUI;

/**
 *
 * @author nnjj
 */
public class TransferOrderUI extends TabPanelUI {

    ShopService shopService;
    TransferOrderService orderService;
    ItemService itemService;
    WareHouseService wareHouseService;
    List<Item> items;
    List<Shop> shops;
    List<Warehouse> warehouses;
    TransferOrder transferOrder;
    TransferOrderLineItem transferOrderLineItem;
    List<TransferOrder> transferOrders;

    /**
     * Creates new form TransferOrderUI
     */
    public TransferOrderUI() {
        initComponents();
        init();
    }

    @Override
    public void init() {

        tblTransfer.setPropertiesEL(new String[]{"id", "item.code", "shopFrom.code", "shopTo.code", "wareHouseFrom.code", "wareHouseTo.code", "qty", "itemMark"});
        tblTransfer.setColumnHeader(new String[]{"id", "Item code", "description", "Shop To", "Shop From", "WareHouse From", "WareHouse To", "Qty", "Item Mark"});
        transferOrder = new TransferOrder();
        transferOrderLineItem = new TransferOrderLineItem();
        transferOrder.initialiseList();
        orderService = new TransferOrderService();
        controlPanel1.setCrudController(this);
        shopService = new ShopService();
        shops = new ArrayList<Shop>();
        itemService = new ItemService();
        wareHouseService = new WareHouseService();

        items = new ArrayList<Item>();

        warehouses = new ArrayList<Warehouse>();


        tfromshop.setActionActionTask(new ActionTask() {
            @Override
            public boolean action() {
                return false;
            }
        });

        tfromshop.setSearchActionTask(new ActionTask() {
            @Override
            public void actionCall(Object qry) {
                tfromshop.setObjectToTable(shopService.getDao().getItemByCode((String) qry));
            }
        });

        tfromshop.setPropertiesEL(new String[]{"id", "code"});
        tfromshop.setTitle(new String[]{"id", "code"});
        tfromshop.setSelectedProperty("code");

        titem.setSearchActionTask(new ActionTask() {
            @Override
            public void actionCall(Object obj) {
                try {
                    titem.setObjectToTable(itemService.getDao().findItemListByCode((String) obj));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                super.actionCall(obj);
            }
        });
        titem.setPropertiesEL(new String[]{"id", "code"});
        titem.setTitle(new String[]{"id", "code"});
        titem.setSelectedProperty("code");



        ttoshop.setPropertiesEL(new String[]{"id", "code"});
        ttoshop.setTitle(new String[]{"id", "code"});

        ttoshop.setSelectedProperty("code");
        ttoshop.setSearchActionTask(new ActionTask() {
            @Override
            public void actionCall(Object obj) {
                try {
                    ttoshop.setObjectToTable(shopService.getDao().getItemByCode((String) obj));


                } catch (Exception e) {
                    e.printStackTrace();
                }
             
            }
        });


        ttowarehouse.setTitle(new String[]{"id", "code"});
        ttowarehouse.setPropertiesEL(new String[]{"id", "code"});
        ttowarehouse.setSearchActionTask(new ActionTask() {
            @Override
            public void actionCall(Object obj) {
                ttowarehouse.setObjectToTable(wareHouseService.getDao().byCode((String)obj));

            }
        });
        ttowarehouse.setSelectedProperty("code");


        tfromware.setSearchActionTask(new ActionTask() {
            @Override
            public void actionCall(Object obj) {
                tfromware.setObjectToTable(wareHouseService.getDao().byCode((String)obj));

            }
        });
        tfromware.setTitle(new String[]{"id", "code"});
        tfromware.setPropertiesEL(new String[]{"id", "code"});
        tfromware.setSelectedProperty("code");

        super.init();

        addToFocus(tfromshop);
        addToFocus(tfromware);
        addToFocus(ttoshop);
        addToFocus(ttowarehouse);
        addToFocus(titem);
        addToFocus(tqty);
//        addToFocus(tunit);
        addToFocus(titemmark);
    }

    @Override
    public void events() {
        titemmark.addaction(0, new ActionTask() {
            @Override
            public boolean action() {
//                transferOrder.initialiseList();
//                transferOrder.getLastItem();
                //to test the generic behaviour
                // TODO---make use of generic method implemnetation
                uiety(transferOrderLineItem);
                //validate the transfer order line item 
                //add to the list
//                transferOrder.setId(EntityService.getKeyStr());
                transferOrder.addToList(transferOrderLineItem);
               transferOrderLineItem=new TransferOrderLineItem();
                addToTable(transferOrder.getLineItemList());

                return true;
            }
        });
    }

    TransferOrderLineItem uiety(TransferOrderLineItem to) {
//        TransferOrderLineItem to = new TransferOrderLineItem();
        to.setItem(titem.getSelectedObject());
        to.setItemMark(UIEty.tcToStr(titemmark));
        to.setQty(UIEty.tcToDouble(tqty));
        to.setShopFrom(tfromshop.getSelectedObject());
        to.setShopTo(ttoshop.getSelectedObject());
        to.setWareHouseFrom(tfromware.getSelectedObject());
        to.setWareHouseTo(ttowarehouse.getSelectedObject());
        return to;
    }

    @Override
    public void save() {
        //must not be empty or null
        if (transferOrder.getLastItem() != null || !transferOrder.getLineItemList().isEmpty()) {
            orderService.getDao().save(transferOrder);
        }
        clear();

    }

    public void addToTable(List<TransferOrderLineItem> items) {
        tblTransfer.modelToTable(items);
        tblTransfer.addrow(new Object[]{});
    }

    @Override
    public void clear() {

        tfromshop.setText("");
        tfromware.setText("");
        titem.setText("");
        titemmark.setText("");
        tqty.setText("");
        ttoshop.setText("");
        ttowarehouse.setText("");
        ttowarehouse.setText("");
        super.clear();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cLabel1 = new org.components.controls.CLabel();
        cLabel2 = new org.components.controls.CLabel();
        cLabel3 = new org.components.controls.CLabel();
        tqty = new org.components.controls.CTextField();
        cLabel4 = new org.components.controls.CLabel();
        cLabel5 = new org.components.controls.CLabel();
        titemmark = new org.components.controls.CTextField();
        cLabel6 = new org.components.controls.CLabel();
        cLabel7 = new org.components.controls.CLabel();
        cLabel8 = new org.components.controls.CLabel();
        tunit = new com.components.custom.DropDownWithButton<UOM>();
        controlPanel1 = new com.components.custom.ControlPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransfer = new org.components.controls.CTableMaster();
        tfromshop = new com.components.custom.TextFieldWithPopUP<Shop>();
        ttoshop = new com.components.custom.TextFieldWithPopUP<Shop>();
        titem = new com.components.custom.TextFieldWithPopUP<Item>();
        ttowarehouse = new com.components.custom.TextFieldWithPopUP<Warehouse>();
        tfromware = new com.components.custom.TextFieldWithPopUP<Warehouse>();

        setLayout(null);

        cLabel1.setText("From warehouse");
        add(cLabel1);
        cLabel1.setBounds(20, 90, 130, 25);

        cLabel2.setText("To shop");
        add(cLabel2);
        cLabel2.setBounds(180, 37, 104, 25);

        cLabel3.setText("Item");
        add(cLabel3);
        cLabel3.setBounds(320, 30, 104, 25);
        add(tqty);
        tqty.setBounds(80, 200, 140, 30);

        cLabel4.setText("Qty");
        add(cLabel4);
        cLabel4.setBounds(80, 160, 104, 30);

        cLabel5.setText("Unit");
        add(cLabel5);
        cLabel5.setBounds(250, 150, 80, 30);
        add(titemmark);
        titemmark.setBounds(400, 200, 136, 30);

        cLabel6.setText("Item Mark");
        add(cLabel6);
        cLabel6.setBounds(360, 150, 104, 30);

        cLabel7.setText("From Shop");
        add(cLabel7);
        cLabel7.setBounds(10, 37, 104, 25);

        cLabel8.setText("To warehouse");
        add(cLabel8);
        cLabel8.setBounds(170, 90, 104, 25);
        add(tunit);
        tunit.setBounds(230, 200, 160, 30);
        add(controlPanel1);
        controlPanel1.setBounds(430, 10, 340, 30);

        tblTransfer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Item Code", "Item Mark", "Qty", "From Shop", "To Shop", "From Warehouse", "To Warehouse"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblTransfer);

        add(jScrollPane1);
        jScrollPane1.setBounds(30, 242, 600, 270);

        tfromshop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfromshopActionPerformed(evt);
            }
        });
        add(tfromshop);
        tfromshop.setBounds(30, 60, 130, 25);

        ttoshop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttoshopActionPerformed(evt);
            }
        });
        add(ttoshop);
        ttoshop.setBounds(180, 60, 130, 25);

        titem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titemActionPerformed(evt);
            }
        });
        add(titem);
        titem.setBounds(350, 60, 130, 25);

        ttowarehouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttowarehouseActionPerformed(evt);
            }
        });
        add(ttowarehouse);
        ttowarehouse.setBounds(190, 120, 130, 25);

        tfromware.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfromwareActionPerformed(evt);
            }
        });
        add(tfromware);
        tfromware.setBounds(40, 120, 130, 25);
    }// </editor-fold>//GEN-END:initComponents

    private void tfromshopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfromshopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfromshopActionPerformed

    private void ttoshopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttoshopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttoshopActionPerformed

    private void titemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_titemActionPerformed

    private void ttowarehouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttowarehouseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttowarehouseActionPerformed

    private void tfromwareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfromwareActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfromwareActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel3;
    private org.components.controls.CLabel cLabel4;
    private org.components.controls.CLabel cLabel5;
    private org.components.controls.CLabel cLabel6;
    private org.components.controls.CLabel cLabel7;
    private org.components.controls.CLabel cLabel8;
    private com.components.custom.ControlPanel controlPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CTableMaster tblTransfer;
    private com.components.custom.TextFieldWithPopUP<Shop> tfromshop;
    private com.components.custom.TextFieldWithPopUP<Warehouse> tfromware;
    private com.components.custom.TextFieldWithPopUP<Item> titem;
    private org.components.controls.CTextField titemmark;
    private org.components.controls.CTextField tqty;
    private com.components.custom.TextFieldWithPopUP<Shop> ttoshop;
    private com.components.custom.TextFieldWithPopUP<Warehouse> ttowarehouse;
    private com.components.custom.DropDownWithButton<UOM> tunit;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return "Transfer Order UI ";
    }
    /**
     * title
     *
     * Spring MVC 3.1, jqGrid, and Spring Data JPA Integration Guide (Part 1)
     * http://krams915.blogspot.com/2012/01/spring-mvc-31-jqgrid-and-spring-data_1887.html
     *
     * keys
     */
}
//added support for generic method behaviour
//sub classed the documents to be used as generic implementation



/*
 transfer order modleing
 * scnario 1)
 * warehouse to warehouse
 *
 * shop may have many warehouses
 * purchase ----> warehouse
 *
 * warehouse may aslo have many shops linked to it 
 *
 * snario 2)
 * shop to shop
 * here there should be document of proof ..transfer order between shop is used
 *
 * we should be able to elemenate
 * shop item for each line item but
 * transfer order document should be divided into two
 * one for item out , otherone is for item in
 *  no no ...only one transfer order per two shop at one time there cant be more then
 * one shops get involved
 *      this turns into GRN case GDN ??
 * yes indeed
 *  this can be used to
 * transferitem between shop's WH's
 *  a transfer order create two inventory journal entry  for  out --in
 *
 * documenting
 * shopx --- po-->WH a -----tranferorder--> WH b ---sales/grn-->
 *
 *
 *

 */