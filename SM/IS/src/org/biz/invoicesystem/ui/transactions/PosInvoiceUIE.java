
/*
 * InvoiceMasterUi.java
 *
 * Created on Dec 2, 2011, 10:27:21 AM
 */
package org.biz.invoicesystem.ui.transactions;

import com.components.custom.PagedPopUpPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
import org.biz.invoicesystem.system.SystemEntityUtil;
import org.components.util.Sessions;
import org.components.windows.TabPanelUI;

/*
 * @2011/12/08 Jawath - GUI creation of invoice @2011/12/09 jawath- creation of
 * invoice logic @2011/12/12 jawath- table editing popup poanel @2011/12/15
 * jawath- table editing cell ediotr @2011/12/18 jawath- table editing cell
 * ediotr document listner
 */
public class PosInvoiceUIE extends TabPanelUI {

    SalesInvoice invoice;
    List<SalesInvoiceLineItem> lineItems;
    SalesInvoiceService servicedao;
    PagedPopUpPanel<Item> itemSelectionPopup;


    /**
     * Creates new form InvoiceMasterUi
     */
    public PosInvoiceUIE() {
        initComponents();

        init();
    }

    @Override
    public void init() {
        seil = new SalesInvoiceLineItem();
        controlPanel1.setCrudController(this);
        JFrame jf = (JFrame) Sessions.getObj("mainui");

  


//        tblInvoice.setColumnHeader(new String[]{"id","Item Code","Description","Qty","Unit","Price","Line Amount"});
        tblInvoice.setPropertiesEL(new String[]{"id", "item.code", "description", "qty", "price", "lineAmount"});

        itemSelectionPopup.setPropertiesEL(new String[]{"id", "code", "description"});
        itemSelectionPopup.setTitle(new String[]{"id", "Code", "Description"});
        itemSelectionPopup.setSelectedProperty("code");

        UIEty.setKeyAction(tblInvoice, new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
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

                UIEty.setKeyAction(this, new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                int sr = tblInvoice.getSelectedRow();
//                save();
                System.out.println("saved");
            }
        }, KeyEvent.VK_F5);


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

        addToFocus(tblInvoice);
        addToFocus(tsubtotal);
        addToFocus(ttax);
        addToFocus(tdis);


        tblInvoice.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (KeyEvent.VK_ESCAPE == e.getKeyCode()) {
                    tsubtotal.requestFocus();
                }
            }
        });

    }

    private void etyToRow(SalesInvoiceLineItem line) {

        tblInvoice.replaceSelectedModel(line);
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

    private PagedPopUpPanel salesPopup;
    private CustomerService custService;
    private ItemService itemService;
    private List<Customer> listCust;
    private List<Item> listItem;
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
            if ((lineItem.getId() == null && sil.getId() == null) || lineItem.getId() != null
                    && sil.getId() != null && lineItem.getId().equals(sil.getId())) {
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
        lineItem.setId(UIEty.colToStrE(tblInvoice, 0));
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

    public void setnewrow() {
        TableUtil.addrow(tblInvoice, new Object[]{});
        SalesInvoiceLineItem si = new SalesInvoiceLineItem();
        lineItems.add(si);
    }

    public void addToTable(List<SalesInvoiceLineItem> items) {

        tblInvoice.modelToTable(items);
        tblInvoice.addrow(new Object[]{});
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
        controlPanel1 = new com.components.custom.ControlPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
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
        cPanel1.setBounds(580, 350, 260, 160);
        add(controlPanel1);
        controlPanel1.setBounds(60, 430, 340, 40);

        jLayeredPane1.setBackground(new java.awt.Color(153, 255, 153));
        jPanel1.setBounds(80, 160, 700, 80);
        jLayeredPane1.add(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.remove(jPanel1);
        jLayeredPane1.revalidate();
        jLayeredPane1.add(jPanel1, javax.swing.JLayeredPane.MODAL_LAYER);

        tblInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Item Code", "Description", "Qty", "Price", "Line Amount"
            }
        ));
        jScrollPane1.setViewportView(tblInvoice);

        jScrollPane1.setBounds(10, 0, 880, 200);
        jLayeredPane1.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        add(jLayeredPane1);
        jLayeredPane1.setBounds(-10, 20, 930, 300);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * using a pos inventory for tracking pos stock i would be needed
     */
    public void save() {

        for (Iterator<SalesInvoiceLineItem> it = lineItems.iterator(); it.hasNext();) {
            SalesInvoiceLineItem si = it.next();
            if (si.getId() == null) {
                it.remove();
            }
        }

        //pos inventory will be updated
        servicedao.createInventoryJournalForPos(invoice);
        invoice = SalesInvoice.createNewInvoice();
        lineItems = invoice.getLineItems();
        addToTable(lineItems);

        clear();
    }

    public void clear() {
        System.out.println("comp  " + tsubtotal.toString());
        invoice = SalesInvoice.createNewInvoice();
        lineItems = invoice.getLineItems();
        addToTable(lineItems);


        setnewrow();
//        uiEty.setcombomodel(new String[]{}, lineItemPanel.getUnitCombo());

    }


    public void updatePosInvoice() {
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
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CTableMaster tblInvoice;
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
/*
 * make it simple 
 * pos without unit -UOM
 *
 * pos invoice invoice which can be used with the
 *
 * note : - popis shown several times becas of when user press enter when seleccting
 * item item code is set to texttield so thet textfieds
 * viered sinario :- user selects a item press enter tiem is set to text field this trigers the
 * documant listner for each chars which set to the textfeild ..
 * fix:- disable the popup when setting the text to textfield
 *
 */