//GEN-FIRST:event_cdeleteActionPerformed
/*//GEN-LAST:event_cdeleteActionPerformed
 * InvoiceMasterUi.java
 *
 * Created on Dec 2, 2011, 10:27:21 AM
 */
package invoicingsystem;

import com.components.custom.PagedPopUpPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import org.components.parent.controls.editors.TablePopUpCellEditor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellEditor;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.UIEty;
import org.biz.dao.service.GenericDAO;
import org.biz.dao.util.EntityService;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Staff;
import org.biz.invoicesystem.entity.master.Supplier;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.biz.invoicesystem.service.master.CustomerService;
import org.biz.invoicesystem.service.master.ItemService;
import org.biz.invoicesystem.service.master.StaffService;
import org.biz.invoicesystem.service.transactions.SalesInvoiceService;
import org.components.parent.controls.editors.ComboBoxCellEditor;
import org.components.parent.controls.editors.DoubleCellEditor;
import org.components.parent.controls.editors.StringCellEditor;
import org.components.parent.controls.editors.TableActions;
import org.components.windows.TabPanelUI;

/*
 * @2011/12/08 zumri -  GUI creation  of invoice
 * @2011/12/09 jawath-  creation  of invoice logic 
 * @2011/12/12 jawath-  table editing popup poanel  
 * @2011/12/15 jawath-  table editing cell ediotr
 * @2011/12/18 jawath-  table editing cell ediotr document listner
 */
public class InvoiceMasterUi extends TabPanelUI {

    SalesInvoice invoice;
    List<SalesInvoiceLineItem> lineItems;
    SalesInvoiceService servicedao;
    PagedPopUpPanel popUpComponent;
    ComboBoxCellEditor ce;
    /** Creates new form InvoiceMasterUi */
    public InvoiceMasterUi() {
        initComponents();

        init();
    }

    public static void main(String[] args) {
//        Object c=null;
//        Object c2=null;
//        System.out.println("r "+c+25+" ff "+c2);
//        List lst = new ArrayList();
//        for (int i = 0; i < 1500; i++) {
//            Item cus = new Item();
//            cus.setId( EntityService.getKeyStr());
//            cus.setCode( EntityService.getKeyStr());
//            cus.setDescription(EntityService.getKeyStr());
//            lst.add(cus);
//        }
//        new GenericDAO<Supplier>().saveList(lst);
//        List lst = new ArrayList();
//        for (int i = 0; i < 1500; i++) {
//            Customer cus = new Customer();
//            cus.setId("" + i + "" + System.currentTimeMillis());
//            cus.setCode("" + i + "" + System.currentTimeMillis() + "" + i);
//            cus.setCustomerName("" + i + "" + System.currentTimeMillis() + "" + i);
//            lst.add(cus);
//        }
//        new GenericDAO<Customer>().saveList(lst);
//        List lst = new ArrayList();
//        for (int i = 0; i < 1500; i++) {
//            Item cus = new Item();
//            cus.setId("" + i + "" + System.currentTimeMillis());
//            cus.setCode("" + i + "" + System.currentTimeMillis() + "" + i);
//            cus.setDescription("" + i + "" + System.currentTimeMillis() + "" + i);
//            lst.add(cus);
//        }
//        new GenericDAO<Item>().saveList(lst);
        List lst = new ArrayList();
        for (int i = 0; i < 1500; i++) {
            Staff cus = new Staff();
            cus.setId("" + i + "" + System.currentTimeMillis());
            cus.setCode("" + i + "" + System.currentTimeMillis() + "" + i);
            cus.setName("" + i + "" + System.currentTimeMillis() + "" + i);
            lst.add(cus);
        }
        new GenericDAO<Staff>().saveList(lst);
    }

    @Override
    public void init() {
            
        TablePopUpCellEditor ed;

        UIEty.setKeyAction(tblInvoice,new AbstractAction() {
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
                    if (id.equals( salesInvoiceLineItem.getId())) {
                        it.remove();
                    }
                }
            }
        },KeyEvent.VK_DELETE);
        
        invoice = new SalesInvoice();
        lineItems = new ArrayList<SalesInvoiceLineItem>();
        invoice.setLineItems(lineItems);
        itemService = new ItemService();
        listItem = itemService.getDao().getAll();

        servicedao = new SalesInvoiceService();
        itemService = new ItemService();


        TablePopUpCellEditor tb = new TablePopUpCellEditor(null) {

            public boolean action() {
                return false;
            }
        };
        TableActions taacti = new TableActions() {

            @Override
            public boolean rowValid() {
                 rowToEty();
                return true;
            }

            @Override
            public void newRowAdded() {
                System.out.println("new row added");
                SalesInvoiceLineItem si = new SalesInvoiceLineItem();
                si.setId(TableUtil.newRowID);
                lineItems.add(si);
            }
//            
//            public int actionPerformed() {
//                validateAndRow();
//                return TableActions.newrow;
//            }
        };
        taacti.setUnSelectableColumns(new Integer[]{3,4});
        taacti.setActioncol(5);
        
        tblInvoice.setTableAction(taacti);
      
        popUpComponent = new PagedPopUpPanel(tblInvoice, tb) {
            @Override
            public void search(String qry) {
                try {

                    popUpComponent.setList(itemService.getDao().byCode(qry));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void action() {

                String ob = popUpComponent.getSelectedID();
                Item item = null;
                //find Item
                for (Item it : listItem) {
                    if (ob.equals(it.getId())) {
                        popUpComponent.setSelectedObject(it);
                        item = it;
                        break;
                    }
                }
                int sr = tblInvoice.getSelectedRow();
//                loadUnit(item);
                SalesInvoiceLineItem lineItem = rowToEty();
                //if current row valid 
                lineItem.setItem(item);
                //replace row
                addsales(lineItem);

            }

            @Override
            public Object[] data(Object item) {
                Item it = (Item) item;
                return new Object[]{it.getId(), it.getCode(), it.getDescription()};
            }
        };
        popUpComponent.setSelectedColumn(1);

        tblInvoice.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    System.out.println("delete");
                }
            }
        });
    /*    ce=new ComboBoxCellEditor(tblInvoice);
//        tblInvoice.getColumnModel().getColumn(2).setCellEditor(tb);      
        TableUtil.setColumnEditor(tblInvoice, 1, tb);
        TableUtil.setColumnEditor(tblInvoice, 2, new StringCellEditor(tblInvoice));
        TableUtil.setColumnEditor(tblInvoice, 3, new DoubleCellEditor(tblInvoice));
        TableUtil.setColumnEditor(tblInvoice, 4, ce);
        TableUtil.setColumnEditor(tblInvoice, 5, new DoubleCellEditor(tblInvoice) {

            public boolean isCellValid() {
                int sr = tblInvoice.getSelectedRow();

                SalesInvoiceLineItem lineItem = rowToEty();

                if (lineItem != null && lineItem.getPrice() != null && lineItem.getPrice() >= 150) {
                    System.out.println("row is valid so move to next if  e");
                    //if ot last row row is valid so move
                    // to next if  exist or or creat new row                                                             
                    //tblInvoice.setCurrentRowValid(true);
                    return true;
                } else {
//                    tblInvoice.setCurrentRowValid(false);
                    return false;
                }
            }
        });

        DoubleCellEditor dce = new DoubleCellEditor(tblInvoice) {
        };

        TableUtil.setColumnEditor(tblInvoice, 6, dce);
*/


        custService = new CustomerService();
        listCust = custService.getDao().getAll();
//                //ADD this list to popup

        
        

        cuspop = new PagedPopUpPanel(tcus) {

            @Override
            public Object[] data(Object item) {
                Customer customer = (Customer) item;
                return new Object[]{customer.getId(), customer.getCode(), customer.getCustomerName()};
            }

            @Override
            public void action() {

                String ob = cuspop.getSelectedID();
                Customer item = null;
                //find Item
                for (Customer it : listCust) {
                    if (ob.equals(it.getId())) {
                        cuspop.setSelectedObject(it);
                        item = it;
                        invoice.setCustomer(item);
                        UIEty.objToUi(cTextArea2, item.getAddress());
                        break;
                    }
                }

            }

            @Override
            public void search(String qry) {
                try {
                    listCust = custService.getDao().byCode(qry);
                    cuspop.setObjectToTable(listCust);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        
        cuspop.setObjectToTable(listCust);
        cuspop.setSelectedColumn(1);
        
        
        staffService =new StaffService();
        listStaff=new ArrayList<Staff>();
        salesPopup =new PagedPopUpPanel(tsalesman) {
        
            @Override
            public Object[] data(Object item) {
                Staff st = (Staff) item;
                return new Object[]{st.getId(), st.getCode(), st.getName()};
            }

            @Override
            public void action() {

                String ob = salesPopup.getSelectedID();
                Staff item = null;
                //find Item
                for (Staff it : listStaff) {
                    if (ob.equals(it.getId())) {
                        salesPopup.setSelectedObject(it);

                        item = it;
                        invoice.setStaff(it);
//                        uiEty.objToUi(cTextArea2, item.getMobile());
                        break;
                    }
                }

            }

            @Override
            public void search(String qry) {
                try {
                    listStaff = staffService.getDao().byCode(qry);
                    salesPopup.setObjectToTable(listStaff);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        
        
        popUpComponent.setObjectToTable(listItem);
        tblInvoice.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    try {
                        int sr = tblInvoice.getSelectedRow();
                        SalesInvoiceLineItem seil = rowToEty();
                        System.out.println("sales + " + seil.getId());
                        //if new row create new line item add to 
                        //or selecet the correct model frfom list and select current 
                    } catch (Exception ee) {
                    
                    }
                }
            }
        });
        setnewrow();
        
        
    }
    TableCellEditor de;
//    DoubleCellEditor de;
    PagedPopUpPanel cuspop;
    PagedPopUpPanel salesPopup;
    CustomerService custService;
    ItemService itemService;
    List<Customer> listCust;
    List<Item> listItem;
    List<Staff> listStaff;
    StaffService staffService;
    Item currentItem;
    SalesInvoiceLineItem seil ;

    private boolean validateAndRow() {


        int sr = tblInvoice.getSelectedRow();

        seil= rowToEty();
        //validate the lineitem
        addsales(seil);
        if (seil != null && TableUtil.newRowID.equals(seil.getId())) {
            String va = TableUtil.getNewRowId();
            tblInvoice.setValueAt(va, sr, 0);
            seil.setId(va);
        }
        Double qty=seil.getQty()==null?0:seil.getQty();
        Double price=seil.getPrice()==null? 0:seil.getPrice();
        seil.setLineAmount(qty* price);        
        tblInvoice.setValueAt(UIEty.getPlainDouble(seil.getLineAmount()) , sr, 6);
        tblInvoice.setValueAt(seil.getPrice(), sr, 5);
        for (SalesInvoiceLineItem Item : lineItems) {
            Item it = Item.getItem();
            System.out.println("ite " + Item.getId() + " item " + (it == null ? " - print null" : Item.getItem().getId()));
        }
        return true;
    }

    private SalesInvoiceLineItem addsales(SalesInvoiceLineItem lineItem) {
        SalesInvoiceLineItem sl = null;
        int sx = -1;
        int x = -1;
        for (SalesInvoiceLineItem sil : lineItems) {
            x++;
            if (lineItem.getId().equals(sil.getId())) {
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

    
    private void loadUnit(Item it){        
         String st=it.getUnitOne();
         String st2=it.getUnitOne();
         String [] stx= st2==null?new String[]{st}:new String[]{st,st2};         
//         UIEty.setcombomodel(stx, (JComboBox)ce.getComponent());
        
    }
    
    private void calculateAndSetValues(){
    
    }
    
    
    public void setnewrow() {
        TableUtil.addrow(tblInvoice, new Object[]{TableUtil.newRowID});
        System.out.println("new row added");
        SalesInvoiceLineItem si = new SalesInvoiceLineItem();
        si.setId(TableUtil.newRowID);
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        cPanel1 = new org.components.containers.CPanel();
        cLabel5 = new org.components.controls.CLabel();
        cLabel7 = new org.components.controls.CLabel();
        cLabel8 = new org.components.controls.CLabel();
        cLabel9 = new org.components.controls.CLabel();
        cLabel10 = new org.components.controls.CLabel();
        cTextField1 = new org.components.controls.CTextField();
        cTextField4 = new org.components.controls.CTextField();
        cTextField5 = new org.components.controls.CTextField();
        cTextField6 = new org.components.controls.CTextField();
        cTextField11 = new org.components.controls.CTextField();
        cLabel15 = new org.components.controls.CLabel();
        cLabel2 = new org.components.controls.CLabel();
        cComboBox2 = new org.components.controls.CComboBox();
        cTextField2 = new org.components.controls.CTextField();
        cPanel2 = new org.components.containers.CPanel();
        cLabel3 = new org.components.controls.CLabel();
        tcus = new org.components.controls.CTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        cTextArea2 = new org.components.controls.CTextArea();
        cCheckBox2 = new org.components.controls.CCheckBox();
        cDatePicker1 = new org.components.controls.CDatePicker();
        cPanel4 = new org.components.containers.CPanel();
        cLabel4 = new org.components.controls.CLabel();
        cPanel3 = new org.components.containers.CPanel();
        cLabel6 = new org.components.controls.CLabel();
        cLabel11 = new org.components.controls.CLabel();
        cLabel12 = new org.components.controls.CLabel();
        cLabel13 = new org.components.controls.CLabel();
        cTextField9 = new org.components.controls.CTextField();
        cTextField10 = new org.components.controls.CTextField();
        cPanel5 = new org.components.containers.CPanel();
        cLabel16 = new org.components.controls.CLabel();
        cLabel17 = new org.components.controls.CLabel();
        cLabel18 = new org.components.controls.CLabel();
        cLabel19 = new org.components.controls.CLabel();
        cLabel20 = new org.components.controls.CLabel();
        cTextField12 = new org.components.controls.CTextField();
        cTextField13 = new org.components.controls.CTextField();
        cTextField14 = new org.components.controls.CTextField();
        cTextField15 = new org.components.controls.CTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblInvoice = new org.components.controls.TableEditable();
        cButton1 = new org.components.controls.CButton();
        cdelete = new org.components.controls.CButton();
        cclear = new org.components.controls.CButton();
        tsalesman = new org.components.controls.CTextField();

        setLayout(null);

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

        cTextField1.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(cTextField1);
        cTextField1.setBounds(90, 90, 150, 20);

        cTextField4.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(cTextField4);
        cTextField4.setBounds(90, 10, 150, 20);

        cTextField5.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(cTextField5);
        cTextField5.setBounds(90, 40, 150, 20);

        cTextField6.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(cTextField6);
        cTextField6.setBounds(90, 60, 150, 20);

        cTextField11.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(cTextField11);
        cTextField11.setBounds(90, 120, 150, 20);

        cLabel15.setText("Recieved");
        cLabel15.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel15);
        cLabel15.setBounds(10, 120, 70, 20);

        add(cPanel1);
        cPanel1.setBounds(680, 360, 260, 160);

        cLabel2.setText("Salesman");
        cLabel2.setFont(new java.awt.Font("Tahoma", 0, 12));
        add(cLabel2);
        cLabel2.setBounds(720, 70, 60, 25);

        cComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Invoice", "Quotation", "Credit Note", "Consignment Out" }));
        add(cComboBox2);
        cComboBox2.setBounds(680, 10, 150, 23);

        cTextField2.setText("Inv No manually");
        add(cTextField2);
        cTextField2.setBounds(830, 10, 110, 25);

        cPanel2.setLayout(null);

        cLabel3.setText("Customer");
        cLabel3.setFont(new java.awt.Font("Tahoma", 1, 10));
        cPanel2.add(cLabel3);
        cLabel3.setBounds(10, 0, 60, 20);
        cPanel2.add(tcus);
        tcus.setBounds(60, 0, 150, 20);

        cTextArea2.setColumns(20);
        cTextArea2.setRows(10);
        jScrollPane3.setViewportView(cTextArea2);

        cPanel2.add(jScrollPane3);
        jScrollPane3.setBounds(10, 30, 230, 60);

        cCheckBox2.setText("Type");
        cPanel2.add(cCheckBox2);
        cCheckBox2.setBounds(10, 93, 60, 20);

        add(cPanel2);
        cPanel2.setBounds(0, 11, 240, 120);
        add(cDatePicker1);
        cDatePicker1.setBounds(830, 40, 110, 22);

        cPanel4.setLayout(null);
        add(cPanel4);
        cPanel4.setBounds(10, 380, 230, 120);

        cLabel4.setText("Date");
        cLabel4.setFont(new java.awt.Font("Tahoma", 0, 12));
        add(cLabel4);
        cLabel4.setBounds(780, 40, 60, 25);

        cPanel3.setLayout(null);

        cLabel6.setText("Salesman");
        cLabel6.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel3.add(cLabel6);
        cLabel6.setBounds(800, 70, 60, 25);

        cLabel11.setText("Last Invoice");
        cLabel11.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cLabel11);
        cLabel11.setBounds(60, 0, 70, 20);

        cLabel12.setText("Invoice Date");
        cLabel12.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cLabel12);
        cLabel12.setBounds(10, 20, 70, 20);

        cLabel13.setText("Invoice Total");
        cLabel13.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cLabel13);
        cLabel13.setBounds(10, 60, 70, 20);

        cTextField9.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cTextField9);
        cTextField9.setBounds(10, 40, 150, 20);

        cTextField10.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cTextField10);
        cTextField10.setBounds(10, 80, 150, 20);

        add(cPanel3);
        cPanel3.setBounds(500, 10, 170, 110);

        cPanel5.setLayout(null);

        cLabel16.setText("Salesman");
        cLabel16.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel5.add(cLabel16);
        cLabel16.setBounds(800, 70, 60, 25);

        cLabel17.setText("Credit Limit");
        cLabel17.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cLabel17);
        cLabel17.setBounds(10, 10, 70, 20);

        cLabel18.setText("Total Dues");
        cLabel18.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cLabel18);
        cLabel18.setBounds(10, 40, 70, 20);

        cLabel19.setText("PD Chqs");
        cLabel19.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cLabel19);
        cLabel19.setBounds(10, 60, 70, 20);

        cLabel20.setText("Bounced Chqs");
        cLabel20.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cLabel20);
        cLabel20.setBounds(10, 80, 70, 20);

        cTextField12.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cTextField12);
        cTextField12.setBounds(90, 80, 150, 20);

        cTextField13.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cTextField13);
        cTextField13.setBounds(90, 10, 150, 20);

        cTextField14.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cTextField14);
        cTextField14.setBounds(90, 40, 150, 20);

        cTextField15.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cTextField15);
        cTextField15.setBounds(90, 60, 150, 20);

        add(cPanel5);
        cPanel5.setBounds(240, 10, 250, 110);

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
        jScrollPane2.setBounds(10, 170, 930, 180);

        cButton1.setText("Save");
        cButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton1ActionPerformed(evt);
            }
        });
        add(cButton1);
        cButton1.setBounds(410, 400, 57, 30);

        cdelete.setText("Delete");
        cdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cdeleteActionPerformed(evt);
            }
        });
        add(cdelete);
        cdelete.setBounds(540, 400, 70, 30);

        cclear.setText("Clear");
        cclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cclearActionPerformed(evt);
            }
        });
        add(cclear);
        cclear.setBounds(470, 400, 70, 30);
        add(tsalesman);
        tsalesman.setBounds(780, 70, 130, 25);
    }// </editor-fold>                        

    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         

        invoice.setId(EntityService.getKeys());
        for (Iterator<SalesInvoiceLineItem> it = lineItems.iterator(); it.hasNext();) {
            SalesInvoiceLineItem si = it.next();
            if(si.getId().equals(TableUtil.newRowID)){
            it.remove();
            }
        }
        servicedao.getDao().save(invoice);
        invoice = SalesInvoice.createNewInvoice();
        lineItems = invoice.getLineItems();
        addToTable(lineItems);
        System.out.println("saved.....");


    }                                        

    private void cdeleteActionPerformed(java.awt.event.ActionEvent evt) {                                        
    }                                       

    private void cclearActionPerformed(java.awt.event.ActionEvent evt) {                                       

        
        
    }                                      

    
    
    public void clear() {
        //clear the gui
    }
    
    // Variables declaration - do not modify                     
    private org.components.controls.CButton cButton1;
    private org.components.controls.CCheckBox cCheckBox2;
    private org.components.controls.CComboBox cComboBox2;
    private org.components.controls.CDatePicker cDatePicker1;
    private org.components.controls.CLabel cLabel10;
    private org.components.controls.CLabel cLabel11;
    private org.components.controls.CLabel cLabel12;
    private org.components.controls.CLabel cLabel13;
    private org.components.controls.CLabel cLabel15;
    private org.components.controls.CLabel cLabel16;
    private org.components.controls.CLabel cLabel17;
    private org.components.controls.CLabel cLabel18;
    private org.components.controls.CLabel cLabel19;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel20;
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
    private org.components.containers.CPanel cPanel4;
    private org.components.containers.CPanel cPanel5;
    private org.components.controls.CTextArea cTextArea2;
    private org.components.controls.CTextField cTextField1;
    private org.components.controls.CTextField cTextField10;
    private org.components.controls.CTextField cTextField11;
    private org.components.controls.CTextField cTextField12;
    private org.components.controls.CTextField cTextField13;
    private org.components.controls.CTextField cTextField14;
    private org.components.controls.CTextField cTextField15;
    private org.components.controls.CTextField cTextField2;
    private org.components.controls.CTextField cTextField4;
    private org.components.controls.CTextField cTextField5;
    private org.components.controls.CTextField cTextField6;
    private org.components.controls.CTextField cTextField9;
    private org.components.controls.CButton cclear;
    private org.components.controls.CButton cdelete;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private org.components.controls.TableEditable tblInvoice;
    private org.components.controls.CTextField tcus;
    private org.components.controls.CTextField tsalesman;
    // End of variables declaration                   

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
