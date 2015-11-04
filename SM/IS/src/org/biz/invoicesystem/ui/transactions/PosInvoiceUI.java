
/*
 * InvoiceMasterUi.java
 *
 * Created on Dec 2, 2011, 10:27:21 AM
 */
package org.biz.invoicesystem.ui.transactions;

import com.components.custom.PagedPopUpPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.UIEty;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Staff;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.biz.invoicesystem.service.master.CustomerService;
import org.biz.invoicesystem.service.master.ItemService;
import org.biz.invoicesystem.service.master.StaffService;
import org.biz.invoicesystem.service.transactions.SalesInvoiceService;
import org.components.util.Sessions;
import org.components.windows.TabPanelUI;

/*
 * @2011/12/08 zumri -  GUI creation  of invoice
 * @2011/12/09 jawath-  creation  of invoice logic 
 * @2011/12/12 jawath-  table editing popup poanel  
 * @2011/12/15 jawath-  table editing cell ediotr
 * @2011/12/18 jawath-  table editing cell ediotr document listner
 */
public class PosInvoiceUI extends TabPanelUI {

    SalesInvoice invoice;
    List<SalesInvoiceLineItem> lineItems;
    SalesInvoiceService servicedao;
    PagedPopUpPanel itemSelectionPopup;

    /** Creates new form InvoiceMasterUi */
    public PosInvoiceUI() {
        initComponents();
JTable kk=new JTable(){

            @Override
            public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
                super.changeSelection(rowIndex, columnIndex, toggle, extend);
            
            }

}; 
        init();
    }

    @Override
    public void init() {

        controlPanel1.setCrudController(this);
        JFrame jf = (JFrame) Sessions.getObj("mainui");

    /*    lineItemPanel = new SalesLineItemPanel(jf) {

            public SalesInvoiceLineItem panelToEty() {
                SalesInvoiceLineItem sl = super.panelToEty();
                //validated line
                //update table row
                //add to list
                // TODO get uom ????/done using the generic 
                //what should be done to the uom  comparison
                //it also done 
                addsales(sl);
                //replace selected row
                //                addToTable(lineItems);

                return sl;
            }

            public void selectEty() {
                //get selected row 
                //get line item from list 
                //set seleceted object to lineitempanel
                SalesInvoiceLineItem sl = PosInvoiceUI.this.getSelectedLine();
                if (sl == null) {
                    return;
                }
                salesline = sl;
                loadUnit(sl.getItem());
                itemSelectionPopup.setPopDesable(true);
                etyToPanel();
                itemSelectionPopup.setPopDesable(false);

            }

            public void action() {
                SalesInvoiceLineItem sl = super.panelToEty();
                Object id = sl.getId();
                if (id == null) {
                    sl.setId(System.currentTimeMillis() + "tt");
                }


                etyToRow(sl);
                uiety();
                invoice.setTotal();
                sTotalToUI();

                //check lineittem id is null then if its need a new row insert a new row  
                //or move to next row

                if (id == null) {
                    setnewrow();
                    int row = tblInvoice.getRowCount();
                    tblInvoice.getSelectionModel().setSelectionInterval(row - 1, row - 1);

                }

            }
        };
       lineItemPanel.setTable(tblInvoice);
        lineItemPanel.setTextField(lineItemPanel.getItemFiled());

*/
 

//        tblInvoice.setColumnHeader(new String[]{"id","Item Code","Description","Qty","Unit","Price","Line Amount"});
        tblInvoice.setPropertiesEL(new String[]{"id", "item.Code", "description", "qty", "Unit", "price", "lineAmount"});

        itemSelectionPopup.setPropertiesEL(new String[]{"id", "code", "description"});
        itemSelectionPopup.setTitle(new String[]{"id", "Code", "Description"});


        UIEty.setKeyAction(tblInvoice, new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("delete action .......");
                int sr = tblInvoice.getSelectedRow();
                String id = (String) TableUtil.getSelectedValue(tblInvoice, 0);
                if (id.equals(TableUtil.newRowID)) {
                    return;
                }
                TableUtil.removerow(tblInvoice, sr);
                for (Iterator<SalesInvoiceLineItem> it = lineItems.iterator(); it.hasNext();) {
                    SalesInvoiceLineItem salesInvoiceLineItem = it.next();
                    if (salesInvoiceLineItem.getId().equals(id)) {
                        it.remove();
                    }
                }
            }
        }, KeyEvent.VK_DELETE);

        invoice = new SalesInvoice();
        lineItems = new ArrayList<SalesInvoiceLineItem>();
        invoice.setLineItems(lineItems);
        itemService = new ItemService();
        listItem = itemService.getDao().getAll();

        servicedao = new SalesInvoiceService();
        itemService = new ItemService();

        custService = new CustomerService();
        listCust = custService.getDao().getAll();

        staffService = new StaffService();
        listStaff = new ArrayList<Staff>();


        setnewrow();


    }

    private void etyToRow(SalesInvoiceLineItem line) {

        String it = line.getItem() == null ? null : line.getItem().getCode();
        String itdes = line.getItem() == null ? null : line.getItem().getDescription();
        TableUtil.replacerowValues(tblInvoice, new Object[]{line.getId(), it, itdes, line.getQty(), line.getUnit(), line.getPrice(), line.getLineAmount()}, tblInvoice.getSelectedRow());

    }

    public SalesInvoiceLineItem getSelectedLine() {

        String bt = UIEty.colToStrE(tblInvoice, 0);
        SalesInvoiceLineItem lineItem = new SalesInvoiceLineItem();

        for (SalesInvoiceLineItem sli : lineItems) {
            //
            if ((bt == null && sli.getId() == null) || (bt != null && sli.getId() != null) && bt.equals(sli.getId())) {
                return sli;

            }
        }
        return null;
    }
//    DoubleCellEditor de;
    PagedPopUpPanel salesPopup;
    CustomerService custService;
    ItemService itemService;
    List<Customer> listCust;
    List<Item> listItem;
    List<Staff> listStaff;
    StaffService staffService;
    Item currentItem;
    SalesInvoiceLineItem seil;

    private SalesInvoiceLineItem addsales(SalesInvoiceLineItem lineItem) {
        SalesInvoiceLineItem sl = null;
        int sx = -1;
        int x = -1;
        for (SalesInvoiceLineItem sil : lineItems) {
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

    public SalesInvoiceLineItem rowToEty() {

        String bt = UIEty.colToStrE(tblInvoice, 0);
        SalesInvoiceLineItem lineItem = new SalesInvoiceLineItem();
//        lineItem.setId(UIEty.colToStrE(tblInvoice, 0));
//        lineItem.setItem(currentItem);
        lineItem.setDescription(UIEty.colToStrE(tblInvoice, 2));
        lineItem.setUnit(UIEty.colToStr(tblInvoice, 4));
        lineItem.setQty(UIEty.colToDbl(tblInvoice, 3));
        lineItem.setPrice(UIEty.colToDbl(tblInvoice, 5));
        lineItem.setLineAmount(UIEty.colToDbl(tblInvoice, 6));
        for (SalesInvoiceLineItem sli : lineItems) {
            if (bt.equals(sli.getId())) {
                lineItem.setItem(sli.getItem());
                break;
            }
        }


        return lineItem;

    }

    public void uiety() {

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

    }

    public void sTotalToUI() {
        UIEty.objToUi(tsubtotal, invoice.getSubTotal());
        UIEty.objToUi(tfinaltotle, invoice.getTotal());
//        uiEty.objToUi(tbal, invoice.setTotal());
    }

    private void loadUnit(Item it) {
        if (it != null) {

        }

    }

    public void setnewrow() {
        TableUtil.addrow(tblInvoice, new Object[]{});
        SalesInvoiceLineItem si = new SalesInvoiceLineItem();
        lineItems.add(si);
    }

    public void addToTable(List<SalesInvoiceLineItem> items) {
        TableUtil.cleardata(tblInvoice);
        if (items == null || items.isEmpty()) {
            return;
        }
        for (SalesInvoiceLineItem item : items) {
            TableUtil.addrow(tblInvoice, new Object[]{item.getId(), item.getItem().getCode(), item.getQty()
                    });
        }
        TableUtil.addrow(tblInvoice, new Object[]{TableUtil.newRowID});
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblInvoice = new org.components.controls.TableEditable();
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
        controlPanel1 = new com.components.custom.ControlPanel();

        setLayout(null);

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

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblInvoice);

        add(jScrollPane2);
        jScrollPane2.setBounds(20, 20, 900, 300);

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

        ttax.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(ttax);
        ttax.setBounds(90, 40, 150, 20);

        tdis.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(tdis);
        tdis.setBounds(90, 60, 150, 20);

        tcashrecieved.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(tcashrecieved);
        tcashrecieved.setBounds(90, 120, 150, 20);

        cLabel15.setText("Recieved");
        cLabel15.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel15);
        cLabel15.setBounds(10, 120, 70, 20);

        add(cPanel1);
        cPanel1.setBounds(680, 360, 260, 160);
        add(controlPanel1);
        controlPanel1.setBounds(60, 430, 340, 40);
    }// </editor-fold>//GEN-END:initComponents

    
        public  void save() {                                         

        for (Iterator<SalesInvoiceLineItem> it = lineItems.iterator(); it.hasNext();) {
            SalesInvoiceLineItem si = it.next();
            if (si.getId() == null) {
                it.remove();
            }
        }


        servicedao.createInventoryJournal(invoice);
        invoice = SalesInvoice.createNewInvoice();
        lineItems = invoice.getLineItems();
        addToTable(lineItems);

        clear();



    }                                        


    
    public void clear() {

        invoice = SalesInvoice.createNewInvoice();
        lineItems = invoice.getLineItems();
        addToTable(lineItems);


        setnewrow();
//        uiEty.setcombomodel(new String[]{}, lineItemPanel.getUnitCombo());

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cLabel10;
    private org.components.controls.CLabel cLabel15;
    private org.components.controls.CLabel cLabel5;
    private org.components.controls.CLabel cLabel7;
    private org.components.controls.CLabel cLabel8;
    private org.components.controls.CLabel cLabel9;
    private org.components.containers.CPanel cPanel1;
    private com.components.custom.ControlPanel controlPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.components.controls.TableEditable tblInvoice;
    private org.components.controls.CTextField tcashrecieved;
    private org.components.controls.CTextField tdis;
    private org.components.controls.CTextField tfinaltotle;
    private org.components.controls.CTextField tsubtotal;
    private org.components.controls.CTextField ttax;
    // End of variables declaration//GEN-END:variables

    @Override
    public void updateEntityUI() {
    }

    @Override
    public String getTabName() {
        return "Invoice Master";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }
}
//        cxTable2.getColumnModel().getColumn(2).setCellEditor(new editor(popUpComponent, tblInvoice));
//        dialog = new ItemPopUp(cTextField1, lineItems)
//        /*     KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
//KeyStroke ob = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
////         tblInvoice.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),ob);
//ActionListener ac = tblInvoice.getActionForKeyStroke(ob);
//tblInvoice.registerKeyboardAction(ac,enter,JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);*/
/*
 * make it simple 
 * 
 * 
 */