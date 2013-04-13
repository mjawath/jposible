/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InvoiceMasterUi.java
 *
 * Created on Dec 2, 2011, 10:27:21 AM
 */
package org.biz.invoicesystem.ui.transactions;

import com.components.custom.CrudControl;
import com.components.custom.PagedPopUpPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.UIEty;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Supplier;
import org.biz.invoicesystem.entity.transactions.PurchaseInvoice;
import org.biz.invoicesystem.entity.transactions.PurchaseInvoiceLineItem;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.biz.invoicesystem.service.master.ItemService;
import org.biz.invoicesystem.service.master.SupplierService;
import org.biz.invoicesystem.service.transactions.PurchaseInvoiceService;
import org.biz.invoicesystem.system.SystemEntityUtil;
import org.components.parent.controls.editors.ComboBoxCellEditor;
import org.components.util.Sessions;
import org.components.util.orgFocusTraversalPolicy;
import org.components.windows.TabPanelUI;

/**
 *
 * @author Administrator
 */
public class PurchaseMasterUi extends TabPanelUI implements CrudControl{

    PurchaseInvoice invoice;
    List<PurchaseInvoiceLineItem> lineItems;
    PurchaseInvoiceService purService;
    List<Supplier> supplierlst;
    PagedPopUpPanel popUpComponent;
    SupplierService supplierService;
    ItemService itemService;
    PagedPopUpPanel suplierPopUpPanel;
    PurchaseInvoiceLineItem seil;
    ComboBoxCellEditor ce;
//    PurchaseLineItemPanel lineItemPanel;
    PagedPopUpPanel itemSelectionPopup;
    List<Item> listItem;

    /** Creates new form InvoiceMasterUi */
    public PurchaseMasterUi() {
        initComponents();
        init();
    }

    @Override
    public void init() {



        supplierService = new SupplierService();
        itemService = new ItemService();
        purService = new PurchaseInvoiceService();

        supplierlst = new ArrayList<Supplier>();
        supplierlst =supplierService.getDao().getAll();
        
        listItem = new ArrayList<Item>();           
        listItem = itemService.getDao().getAll();

        invoice = new PurchaseInvoice();
        lineItems = new ArrayList<PurchaseInvoiceLineItem>();
        invoice.setLineItems(lineItems);

        

        UIEty.setKeyAction(tblInvoice, new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("delete action .......");
                int sr = tblInvoice.getSelectedRow();
                String id = (String) TableUtil.getSelectedValue(tblInvoice, 0);
                if (id == null || id.isEmpty()) {
                    return;
                }
                TableUtil.removerow(tblInvoice, sr);
                for (Iterator<PurchaseInvoiceLineItem> it = lineItems.iterator(); it.hasNext();) {
                    PurchaseInvoiceLineItem salesInvoiceLineItem = it.next();
                    if (id.equals(salesInvoiceLineItem.getId())) {
                        it.remove();
                    }
                }
            }
        }, KeyEvent.VK_DELETE);

        setnewrow();

        initPopups();
        
        
             Vector vc= new Vector();
        vc.add(tbal);
        vc.add(tsubtotal);
        vc.add(ttex);
        vc.add(tfinaltotle);
       cPanel1.setFocusTraversalPolicy(new orgFocusTraversalPolicy(vc));
    }

    public void initPopups() {
        JFrame jf = (JFrame) Sessions.getObj("mainui");
        
   /*     lineItemPanel = new PurchaseLineItemPanel(jf) {

            @Override
            public PurchaseInvoiceLineItem panelToEty() {
                PurchaseInvoiceLineItem sl = super.panelToEty();
                addsales(sl);
                return sl;
            }

            public void selectEty() {
                PurchaseInvoiceLineItem sl = PurchaseMasterUi.this.getSelectedLine();
                if (sl == null) {
                    return;
                }
                salesline = sl;
//                loadUnit(sl.getItem());
                itemSelectionPopup.setPopDesable(true);
                etyToPanel();
                itemSelectionPopup.setPopDesable(false);

            }

            @Override
            public void action() {
                PurchaseInvoiceLineItem sl = super.panelToEty();
                Object id = sl.getId();
                if (id == null) {
                    sl.setId(System.currentTimeMillis() + "tt");
                }
                etyToRow(sl);
                uiety();
                invoice.setTotal();
                sTotalToUI();
                if (id == null) {
                    setnewrow();

                }

            }
        };
        lineItemPanel.setTable(tblInvoice);
        lineItemPanel.setTextField(lineItemPanel.getItemFiled());

        itemSelectionPopup = new PagedPopUpPanel(lineItemPanel.getItemFiled()) {

            public void search(String qry) {
                try {
                    itemSelectionPopup.setList(itemService.getDao().byCode(qry));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void action() {
                super.action();
                String ob = itemSelectionPopup.getSelectedID();
                Item item = null;
                //find Item
                for (Item it : listItem) {
                    if (ob.equals(it.getId())) {
                        itemSelectionPopup.setSelectedObject(it);
                        item = it;
                        break;
                    }
                }
//                loadUnit(item);
                PurchaseInvoiceLineItem lineItem = lineItemPanel.panelToEty();
                lineItem.setItem(item);
                addsales(lineItem);
//                lineItemPanel.lineItemLogic();
            }

            public Object[] data(Object item) {
                Item it = (Item) item;
                return new Object[]{it.getId(), it.getCode(), it.getDescription()};
            }
        };

     */   
        suplierPopUpPanel = new PagedPopUpPanel(tsup) {

            @Override
            public void action() {
                String ob = suplierPopUpPanel.getSelectedID();
                Supplier item = null;
                //find Item
                for (Supplier it : supplierlst) {
                    if (ob.equals(it.getId())) {
                        suplierPopUpPanel.setSelectedObject(it);
                        item = it;
                        invoice.setSupplier(item);
                        UIEty.objToUi(cTextArea2, item.getAddress1());
                        
                        return;
                    }
                }
            }

            @Override
            public void search(String qry) {
                try {
                    supplierlst = supplierService.getDao().byCode(qry);
                    suplierPopUpPanel.setObjectToTable(supplierlst);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public Object[] data(Object item) {
                Supplier it = (Supplier) item;
                return new Object[]{it.getId(), it.getCode(), it.getName()};
            }
        };
    }

    public void save() {
        for (Iterator<PurchaseInvoiceLineItem> it = lineItems.iterator(); it.hasNext();) {
            PurchaseInvoiceLineItem si = it.next();
            if (si.getId() == null) {
                it.remove();
            }
        }
        

        purService.createInventoryJournal(invoice);
        invoice = PurchaseInvoice.createNewInvoice();
        lineItems = invoice.getLineItems();
        addToTable(lineItems);

        clear();
    }

    private void etyToRow(PurchaseInvoiceLineItem line) {

        String it = line.getItem() == null ? null : line.getItem().getCode();
        String itdes = line.getItem() == null ? null : line.getItem().getDescription();
        TableUtil.replacerowValues(tblInvoice, new Object[]{line.getId(), it, itdes, line.getQty(), line.getUnit(), line.getPrice(), line.getLineAmount()}, tblInvoice.getSelectedRow());

    }

    
    
    public void uiety() {

//        invoice.setDocdate(tdate.getDate());
//        invoice.setInvNo(uiEty.tcToStr(tinvoiceManualNo));
        invoice.setShop(Shop.getDefaultShop());
//        invoice.setDocRefNo(uiEty.tcToStr(tdocref));
        invoice.setSubTotal(UIEty.tcToDouble(tsubtotal));
//        invoice.setTexAmount(uiEty.tcToDouble(ttax));
//        invoice.setDiscount(uiEty.tcToDouble(tdis));
//        invoice.setCashRecieveds(uiEty.tcToDouble(tcashrecieved));
//        invoice.setRemarks(uiEty.tcToStr(tremark));
        invoice.setEditeddate(SystemEntityUtil.getSystemDate());
        invoice.setSaveddate(SystemEntityUtil.getSystemDate());
//        invoice.setLineItems(lineItems);

    }
    public PurchaseInvoiceLineItem getSelectedLine() {

        String bt = UIEty.colToStrE(tblInvoice, 0);
        SalesInvoiceLineItem lineItem = new SalesInvoiceLineItem();

        for (PurchaseInvoiceLineItem sli : lineItems) {
            //
            if ((bt == null && sli.getId() == null) || (bt != null && sli.getId() != null) && bt.equals(sli.getId())) {
                return sli;

            }
        }
        return null;
    }

    public void setnewrow() {
        TableUtil.addrow(tblInvoice, new Object[]{});
        PurchaseInvoiceLineItem si = new PurchaseInvoiceLineItem();

        lineItems.add(si);
    }

    private void loadUnit(Item it) {
        String st = it.getUnitOne();
        String st2 = it.getUnitTwo();
        String[] stx = new String[]{st, st2, "d", "Y"};
//        UIEty.cmbModelWithoutNull(stx, (JComboBox) ce.getComponent());

    }

    private PurchaseInvoiceLineItem addsales(PurchaseInvoiceLineItem lineItem) {
        PurchaseInvoiceLineItem sl = null;
        int sx = -1;
        int x = -1;
        for (PurchaseInvoiceLineItem sil : lineItems) {
            x++;
            if ((lineItem.getId() == null && sil.getId() == null) || lineItem.getId() != null && sil.getId() != null && lineItem.getId().equals(sil.getId())) {
                sx = x;
                sl = sil;
                break;
            }
        }
        if (sx > -1) {
            lineItems.remove(sx);
            lineItems.add(sx, lineItem);
        }
        return sl;
    }

    public PurchaseInvoiceLineItem rowToEty() {

        String bt = UIEty.colToStrE(tblInvoice, 0);
        PurchaseInvoiceLineItem lineItem = new PurchaseInvoiceLineItem();
        lineItem.setId(UIEty.colToStrE(tblInvoice, 0));
//        lineItem.setItem(currentItem);
        lineItem.setDescription(UIEty.colToStrE(tblInvoice, 2));
        lineItem.setUnit(UIEty.colToStr(tblInvoice, 4));
        lineItem.setQty(UIEty.colToDbl(tblInvoice, 3));
        lineItem.setPrice(UIEty.colToDbl(tblInvoice, 5));
        lineItem.setLineAmount(UIEty.colToDbl(tblInvoice, 6));
        for (PurchaseInvoiceLineItem sli : lineItems) {
            if ((bt == null && sli.getId() == null) || (bt != null && sli.getId() != null && bt.equals(sli.getId()))) {
                lineItem.setItem(sli.getItem());
                break;
            }
        }
        return lineItem;

    }

    public void sTotalToUI() {
        UIEty.objToUi(tsubtotal, invoice.getSubTotal());
        UIEty.objToUi(tfinaltotle, invoice.getFinalTotal());
        UIEty.objToUi(tbal, invoice.setTotal());
    }
    
    
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
        ttex = new org.components.controls.CTextField();
        tdis = new org.components.controls.CTextField();
        tbal = new org.components.controls.CTextField();
        cLabel15 = new org.components.controls.CLabel();
        cComboBox1 = new org.components.controls.CComboBox();
        cLabel2 = new org.components.controls.CLabel();
        cComboBox2 = new org.components.controls.CComboBox();
        cTextField2 = new org.components.controls.CTextField();
        cDatePicker1 = new org.components.controls.CDatePicker();
        cLabel4 = new org.components.controls.CLabel();
        cLabel16 = new org.components.controls.CLabel();
        cTextField12 = new org.components.controls.CTextField();
        cTextField7 = new org.components.controls.CTextField();
        cLabel17 = new org.components.controls.CLabel();
        cPanel3 = new org.components.containers.CPanel();
        cLabel6 = new org.components.controls.CLabel();
        cLabel12 = new org.components.controls.CLabel();
        cLabel13 = new org.components.controls.CLabel();
        cLabel14 = new org.components.controls.CLabel();
        cTextField8 = new org.components.controls.CTextField();
        cTextField9 = new org.components.controls.CTextField();
        cTextField10 = new org.components.controls.CTextField();
        cLabel11 = new org.components.controls.CLabel();
        cPanel2 = new org.components.containers.CPanel();
        cLabel3 = new org.components.controls.CLabel();
        tsup = new org.components.controls.CTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        cTextArea2 = new org.components.controls.CTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblInvoice = new org.components.controls.TableEditable();

        setFocusTraversalPolicyProvider(true);
        setLayout(null);

        cPanel1.setFocusTraversalPolicyProvider(true);
        cPanel1.setLayout(null);

        cLabel5.setText("Salesman");
        cLabel5.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel5);
        cLabel5.setBounds(800, 70, 60, 25);

        cLabel7.setText("Total");
        cLabel7.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel7);
        cLabel7.setBounds(10, 10, 70, 20);

        cLabel8.setText("Tax");
        cLabel8.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel8);
        cLabel8.setBounds(10, 40, 70, 20);

        cLabel9.setText("Discount");
        cLabel9.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel9);
        cLabel9.setBounds(10, 60, 70, 20);

        cLabel10.setText("Final Total");
        cLabel10.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel10);
        cLabel10.setBounds(10, 90, 70, 20);

        tfinaltotle.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(tfinaltotle);
        tfinaltotle.setBounds(90, 90, 150, 20);

        tsubtotal.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(tsubtotal);
        tsubtotal.setBounds(90, 10, 150, 20);

        ttex.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(ttex);
        ttex.setBounds(90, 40, 150, 20);

        tdis.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(tdis);
        tdis.setBounds(90, 60, 150, 20);

        tbal.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(tbal);
        tbal.setBounds(90, 120, 150, 20);

        cLabel15.setText("Recieved");
        cLabel15.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel15);
        cLabel15.setBounds(10, 120, 70, 20);

        add(cPanel1);
        cPanel1.setBounds(630, 360, 330, 160);
        add(cComboBox1);
        cComboBox1.setBounds(830, 130, 130, 23);

        cLabel2.setText("Salesman");
        cLabel2.setFont(new java.awt.Font("Tahoma", 0, 12));
        add(cLabel2);
        cLabel2.setBounds(780, 130, 60, 25);

        cComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Purchase", "Purchase Order", "Cinsignment In" }));
        cComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cComboBox2ActionPerformed(evt);
            }
        });
        add(cComboBox2);
        cComboBox2.setBounds(650, 10, 160, 23);

        cTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cTextField2ActionPerformed(evt);
            }
        });
        add(cTextField2);
        cTextField2.setBounds(830, 40, 130, 25);
        add(cDatePicker1);
        cDatePicker1.setBounds(830, 100, 130, 22);

        cLabel4.setText("Ref No");
        cLabel4.setFont(new java.awt.Font("Tahoma", 0, 12));
        add(cLabel4);
        cLabel4.setBounds(780, 40, 60, 25);

        cLabel16.setText("Date");
        cLabel16.setFont(new java.awt.Font("Tahoma", 0, 12));
        add(cLabel16);
        cLabel16.setBounds(780, 100, 60, 25);

        cTextField12.setText("Purch no manually");
        cTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cTextField12ActionPerformed(evt);
            }
        });
        add(cTextField12);
        cTextField12.setBounds(830, 10, 130, 25);

        cTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cTextField7ActionPerformed(evt);
            }
        });
        add(cTextField7);
        cTextField7.setBounds(830, 70, 130, 25);

        cLabel17.setText("GRN No");
        cLabel17.setFont(new java.awt.Font("Tahoma", 0, 12));
        add(cLabel17);
        cLabel17.setBounds(780, 70, 50, 25);

        cPanel3.setLayout(null);

        cLabel6.setText("Salesman");
        cLabel6.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel3.add(cLabel6);
        cLabel6.setBounds(800, 70, 60, 25);

        cLabel12.setText("First Month");
        cLabel12.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cLabel12);
        cLabel12.setBounds(10, 20, 90, 20);

        cLabel13.setText("Third Mo th");
        cLabel13.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cLabel13);
        cLabel13.setBounds(220, 20, 80, 20);

        cLabel14.setText("Second month ");
        cLabel14.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cLabel14);
        cLabel14.setBounds(110, 20, 100, 20);

        cTextField8.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cTextField8);
        cTextField8.setBounds(10, 40, 90, 20);

        cTextField9.setFont(new java.awt.Font("Tahoma", 0, 10));
        cTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cTextField9ActionPerformed(evt);
            }
        });
        cPanel3.add(cTextField9);
        cTextField9.setBounds(110, 40, 100, 20);

        cTextField10.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cTextField10);
        cTextField10.setBounds(220, 40, 80, 20);

        cLabel11.setText("Sales Last 3 month of  selected supplier's product");
        cLabel11.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cLabel11);
        cLabel11.setBounds(10, 0, 240, 20);

        add(cPanel3);
        cPanel3.setBounds(40, 430, 320, 70);

        cPanel2.setLayout(null);

        cLabel3.setText("Supplier Details");
        cLabel3.setFont(new java.awt.Font("Tahoma", 1, 10));
        cPanel2.add(cLabel3);
        cLabel3.setBounds(10, 0, 130, 20);
        cPanel2.add(tsup);
        tsup.setBounds(10, 20, 210, 20);

        cTextArea2.setColumns(20);
        cTextArea2.setRows(10);
        jScrollPane3.setViewportView(cTextArea2);

        cPanel2.add(jScrollPane3);
        jScrollPane3.setBounds(10, 40, 210, 70);

        add(cPanel2);
        cPanel2.setBounds(10, 0, 380, 140);

        tblInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Item Code", "Desc", "Qty", "Unit", "Price", "Line Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblInvoice);
        tblInvoice.getColumnModel().getColumn(0).setResizable(false);
        tblInvoice.getColumnModel().getColumn(1).setResizable(false);
        tblInvoice.getColumnModel().getColumn(2).setResizable(false);
        tblInvoice.getColumnModel().getColumn(3).setResizable(false);
        tblInvoice.getColumnModel().getColumn(4).setResizable(false);
        tblInvoice.getColumnModel().getColumn(5).setResizable(false);
        tblInvoice.getColumnModel().getColumn(6).setResizable(false);

        add(jScrollPane2);
        jScrollPane2.setBounds(10, 160, 940, 180);
    }// </editor-fold>//GEN-END:initComponents

    private void cTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cTextField2ActionPerformed
    }//GEN-LAST:event_cTextField2ActionPerformed

    private void cTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cTextField12ActionPerformed
    }//GEN-LAST:event_cTextField12ActionPerformed

    private void cTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cTextField7ActionPerformed
    }//GEN-LAST:event_cTextField7ActionPerformed

    public void uiety(PurchaseInvoice pi) {
    }

    private void cComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cComboBox2ActionPerformed

    public void addToTable(List<PurchaseInvoiceLineItem> items) {
        TableUtil.cleardata(tblInvoice);
        if (items == null || items.isEmpty()) {
            return;
        }
        for (PurchaseInvoiceLineItem line : items) {


            String it = line.getItem() == null ? null : line.getItem().getCode();
            String itdes = line.getItem() == null ? null : line.getItem().getDescription();

            TableUtil.addrow(tblInvoice, new Object[]{new Object[]{line.getId(), it, itdes, line.getQty(), line.getUnit(), line.getPrice(), line.getLineAmount()
                        }});
        }
        TableUtil.addrow(tblInvoice, new Object[]{});


    }

    public void clear() {

        invoice = PurchaseInvoice.createNewInvoice();
        lineItems = invoice.getLineItems();
        addToTable(lineItems);


//        tcus.setText("");
//        tsalesman.setText("");
//        ttax.setText("");
//        tsubtotal.setText("");
//        tcashrecieved.setText("");
//        tfinaltotle.setText("");
//        tinvoiceManualNo.setText("");
//        tdis.setText("");
//        tsalesman.setText("");
        setnewrow();
//        uiEty.setcombomodel(new String[]{}, lineItemPanel.getUnitCombo());
        

    }
    
    private void cTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cTextField9ActionPerformed
}//GEN-LAST:event_cTextField9ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CComboBox cComboBox1;
    private org.components.controls.CComboBox cComboBox2;
    private org.components.controls.CDatePicker cDatePicker1;
    private org.components.controls.CLabel cLabel10;
    private org.components.controls.CLabel cLabel11;
    private org.components.controls.CLabel cLabel12;
    private org.components.controls.CLabel cLabel13;
    private org.components.controls.CLabel cLabel14;
    private org.components.controls.CLabel cLabel15;
    private org.components.controls.CLabel cLabel16;
    private org.components.controls.CLabel cLabel17;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel3;
    private org.components.controls.CLabel cLabel4;
    private org.components.controls.CLabel cLabel5;
    private org.components.controls.CLabel cLabel6;
    private org.components.controls.CLabel cLabel7;
    private org.components.controls.CLabel cLabel8;
    private org.components.controls.CLabel cLabel9;
    private org.components.containers.CPanel cPanel1;
    private org.components.containers.CPanel cPanel2;
    private org.components.containers.CPanel cPanel3;
    private org.components.controls.CTextArea cTextArea2;
    private org.components.controls.CTextField cTextField10;
    private org.components.controls.CTextField cTextField12;
    private org.components.controls.CTextField cTextField2;
    private org.components.controls.CTextField cTextField7;
    private org.components.controls.CTextField cTextField8;
    private org.components.controls.CTextField cTextField9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private org.components.controls.CTextField tbal;
    private org.components.controls.TableEditable tblInvoice;
    private org.components.controls.CTextField tdis;
    private org.components.controls.CTextField tfinaltotle;
    private org.components.controls.CTextField tsubtotal;
    private org.components.controls.CTextField tsup;
    private org.components.controls.CTextField ttex;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return "Invoice Master";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }
}
