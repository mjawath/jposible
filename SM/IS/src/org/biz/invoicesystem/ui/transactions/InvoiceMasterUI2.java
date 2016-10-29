
/*
 * InvoiceMasterUi.java
 *
 * Created on Dec 2, 2011, 10:27:21 AM
 */
package org.biz.invoicesystem.ui.transactions;

import com.components.custom.PagedPopUpPanel;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.entity.master.Item;
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
 * @2011/12/08 jawth -  GUIcreation  of invoice
 * @2011/12/09 jawath-  creation  of invoice logic 
 * @2011/12/12 jawath-  table editing popup poanel  
 * @2011/12/15 jawath-  table editing cell ediotr
 * @2011/12/18 jawath-  table editing cell ediotr document listner
 */
public class InvoiceMasterUI2 extends TabPanelUI {

    SalesInvoice invoice;
    List<SalesInvoiceLineItem> lineItems;
    SalesInvoiceService servicedao;
    PagedPopUpPanel popUpComponent;
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
    SalesInvoiceLineItem seil;
    PagedPopUpPanel itemSelectionPopup;

    /** Creates new form InvoiceMasterUi */
    public InvoiceMasterUI2() {
        initComponents();

        init();
//        tblInvoice.setPropertiesEL(new String[]{"id", "item.code", "item.description","qty", "unit", "price", "lineAmount"});
    }
    
    public void mm(){
    
    }

    public void init() {
        initPopups();
        
        super.init();

    }

    public void initPopups() {
        JFrame jf = (JFrame) Sessions.getObj("mainui");
//       JDialog jd = new JDialog(jf,false); 
////       jd.setModal(false);
//       jd.setSize(300,300);
//       jd.setVisible(true);
        //
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
        ttax = new org.components.controls.CTextField();
        tdis = new org.components.controls.CTextField();
        tcashrecieved = new org.components.controls.CTextField();
        cLabel15 = new org.components.controls.CLabel();
        cLabel2 = new org.components.controls.CLabel();
        ttype = new org.components.controls.CComboBox();
        tinvoiceManualNo = new org.components.controls.CTextField();
        cPanel2 = new org.components.containers.CPanel();
        cLabel3 = new org.components.controls.CLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        taddress = new org.components.controls.CTextArea();
        tdate = new org.components.controls.CDatePicker();
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
        tdocref = new org.components.controls.CTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tremark = new org.components.controls.CTextArea();
        cLabel1 = new org.components.controls.CLabel();
        tbal = new org.components.controls.CLabel();
        cLabel21 = new org.components.controls.CLabel();
        controlPanel1 = new com.components.custom.ControlPanel();

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
        cPanel1.setBounds(680, 360, 260, 160);

        cLabel2.setText("Salesman");
        cLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(cLabel2);
        cLabel2.setBounds(700, 90, 60, 25);

        ttype.setEditable(true);
        ttype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Invoice", "Quotation", "Credit Note", "Consignment Out" }));
        ttype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttypeActionPerformed(evt);
            }
        });
        add(ttype);
        ttype.setBounds(680, 10, 150, 23);

        tinvoiceManualNo.setText("Inv No manually");
        add(tinvoiceManualNo);
        tinvoiceManualNo.setBounds(830, 10, 110, 25);

        cPanel2.setLayout(null);

        cLabel3.setText("Customer");
        cLabel3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        cPanel2.add(cLabel3);
        cLabel3.setBounds(10, 0, 60, 20);

        taddress.setColumns(20);
        taddress.setRows(10);
        jScrollPane3.setViewportView(taddress);

        cPanel2.add(jScrollPane3);
        jScrollPane3.setBounds(10, 50, 230, 60);

        add(cPanel2);
        cPanel2.setBounds(0, 11, 240, 120);
        add(tdate);
        tdate.setBounds(830, 40, 110, 22);

        cLabel4.setText("Date");
        cLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(cLabel4);
        cLabel4.setBounds(780, 40, 60, 25);

        cPanel3.setLayout(null);

        cLabel6.setText("Salesman");
        cLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cPanel3.add(cLabel6);
        cLabel6.setBounds(800, 70, 60, 25);

        cLabel11.setText("Last Invoice");
        cLabel11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel3.add(cLabel11);
        cLabel11.setBounds(60, 0, 70, 20);

        cLabel12.setText("Invoice Date");
        cLabel12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel3.add(cLabel12);
        cLabel12.setBounds(10, 20, 70, 20);

        cLabel13.setText("Invoice Total");
        cLabel13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel3.add(cLabel13);
        cLabel13.setBounds(10, 60, 70, 20);

        cTextField9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel3.add(cTextField9);
        cTextField9.setBounds(10, 40, 150, 20);

        cTextField10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel3.add(cTextField10);
        cTextField10.setBounds(10, 80, 150, 20);

        add(cPanel3);
        cPanel3.setBounds(510, 10, 170, 110);

        cPanel5.setLayout(null);

        cLabel16.setText("Salesman");
        cLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cPanel5.add(cLabel16);
        cLabel16.setBounds(800, 70, 60, 25);

        cLabel17.setText("Credit Limit");
        cLabel17.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel5.add(cLabel17);
        cLabel17.setBounds(10, 10, 70, 20);

        cLabel18.setText("Total Dues");
        cLabel18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel5.add(cLabel18);
        cLabel18.setBounds(10, 40, 70, 20);

        cLabel19.setText("PD Chqs");
        cLabel19.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel5.add(cLabel19);
        cLabel19.setBounds(10, 60, 70, 20);

        cLabel20.setText("Bounced Chqs");
        cLabel20.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel5.add(cLabel20);
        cLabel20.setBounds(10, 80, 70, 20);

        cTextField12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel5.add(cTextField12);
        cTextField12.setBounds(90, 80, 150, 20);

        cTextField13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cTextField13ActionPerformed(evt);
            }
        });
        cPanel5.add(cTextField13);
        cTextField13.setBounds(90, 10, 150, 20);

        cTextField14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel5.add(cTextField14);
        cTextField14.setBounds(90, 40, 150, 20);

        cTextField15.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel5.add(cTextField15);
        cTextField15.setBounds(90, 60, 150, 20);

        add(cPanel5);
        cPanel5.setBounds(250, 10, 250, 110);

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
        tblInvoice.setRowHeight(30);
        jScrollPane2.setViewportView(tblInvoice);
        if (tblInvoice.getColumnModel().getColumnCount() > 0) {
            tblInvoice.getColumnModel().getColumn(0).setResizable(false);
            tblInvoice.getColumnModel().getColumn(1).setResizable(false);
            tblInvoice.getColumnModel().getColumn(2).setResizable(false);
            tblInvoice.getColumnModel().getColumn(3).setResizable(false);
            tblInvoice.getColumnModel().getColumn(4).setResizable(false);
            tblInvoice.getColumnModel().getColumn(5).setResizable(false);
            tblInvoice.getColumnModel().getColumn(6).setResizable(false);
        }

        add(jScrollPane2);
        jScrollPane2.setBounds(10, 170, 930, 180);
        add(tdocref);
        tdocref.setBounds(710, 60, 70, 25);

        tremark.setColumns(20);
        tremark.setRows(5);
        jScrollPane1.setViewportView(tremark);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 380, 190, 30);

        cLabel1.setText("Remarks");
        add(cLabel1);
        cLabel1.setBounds(110, 360, 104, 20);

        tbal.setText("");
        add(tbal);
        tbal.setBounds(160, 480, 190, 40);

        cLabel21.setText("Total :");
        add(cLabel21);
        cLabel21.setBounds(110, 480, 104, 25);
        add(controlPanel1);
        controlPanel1.setBounds(110, 420, 480, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void ttypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttypeActionPerformed

        System.out.println("evt " + evt);


    }//GEN-LAST:event_ttypeActionPerformed

    private void cTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cTextField13ActionPerformed

    public void clear() {
        invoice = SalesInvoice.createNewInvoice();
        lineItems = invoice.getLineItems();

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cLabel1;
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
    private org.components.controls.CLabel cLabel21;
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
    private org.components.containers.CPanel cPanel5;
    private org.components.controls.CTextField cTextField10;
    private org.components.controls.CTextField cTextField12;
    private org.components.controls.CTextField cTextField13;
    private org.components.controls.CTextField cTextField14;
    private org.components.controls.CTextField cTextField15;
    private org.components.controls.CTextField cTextField9;
    private com.components.custom.ControlPanel controlPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private org.components.controls.CTextArea taddress;
    private org.components.controls.CLabel tbal;
    private org.components.controls.TableEditable tblInvoice;
    private org.components.controls.CTextField tcashrecieved;
    private org.components.controls.CDatePicker tdate;
    private org.components.controls.CTextField tdis;
    private org.components.controls.CTextField tdocref;
    private org.components.controls.CTextField tfinaltotle;
    private org.components.controls.CTextField tinvoiceManualNo;
    private org.components.controls.CTextArea tremark;
    private org.components.controls.CTextField tsubtotal;
    private org.components.controls.CTextField ttax;
    private org.components.controls.CComboBox ttype;
    // End of variables declaration//GEN-END:variables

    public void updateEntityUI() {
    }

    public String getTabName() {
        return "Invoice Master";
    }

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
