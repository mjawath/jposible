package org.biz.invoicesystem.master.ui;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.app.ui.util.UIEty;
import org.biz.invoicesystem.service.master.RolesService;
import org.components.windows.TabPanelUI;

public class FormSecUI extends TabPanelUI {

    RolesService rService;

    public FormSecUI() {
        initComponents();
    }

    @Override
    public void init() {

        try {

            rService = new RolesService();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    ///////////////////////////////////////////////////////
    public void loadComboBoxData() {

        try {
            List<Object[]> lstOfArray = rService.getDao().loadComboItems();

            //list of array retuns String array  
            //object is String array....

            Set<String> roles = new TreeSet<String>();

            for (Object[] ss : lstOfArray) {
                String type = (String) ss[0];
                roles.add(type);

            }
            loadcombo(cRoleNameCombo, roles);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
///////////////////////////////////////////////////
    public void loadcombo(JComboBox cmb, Set<String> s) {
        try {
            cmb.removeAllItems();
            cmb.setSelectedItem("");
            for (String role : s) {
                cmb.setSelectedItem(role);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /////////////////////////////////////////////////

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tDefaultSettingGrp = new javax.swing.ButtonGroup();
        cRoleNameCombo = new org.components.controls.CComboBox();
        cLabel1 = new org.components.controls.CLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        cPanel1 = new org.components.containers.CPanel();
        cLabel2 = new org.components.controls.CLabel();
        cLabel10 = new org.components.controls.CLabel();
        formItemMaster = new org.components.controls.CTextField();
        cLabel11 = new org.components.controls.CLabel();
        formCustomer = new org.components.controls.CTextField();
        cLabel12 = new org.components.controls.CLabel();
        formStaffForm = new org.components.controls.CTextField();
        cLabel13 = new org.components.controls.CLabel();
        formSupplierForm = new org.components.controls.CTextField();
        cLabel14 = new org.components.controls.CLabel();
        formPurchase = new org.components.controls.CTextField();
        cLabel15 = new org.components.controls.CLabel();
        formBegCustomer = new org.components.controls.CTextField();
        cLabel16 = new org.components.controls.CLabel();
        formInvoice = new org.components.controls.CTextField();
        cLabel17 = new org.components.controls.CLabel();
        formBeginningItem = new org.components.controls.CTextField();
        cLabel18 = new org.components.controls.CLabel();
        formBegSupplier = new org.components.controls.CTextField();
        cLabel20 = new org.components.controls.CLabel();
        formVehicleSalesForm = new org.components.controls.CTextField();
        cLabel21 = new org.components.controls.CLabel();
        cTextField13 = new org.components.controls.CTextField();
        cLabel22 = new org.components.controls.CLabel();
        formVehicleForm = new org.components.controls.CTextField();
        cLabel23 = new org.components.controls.CLabel();
        cTextField15 = new org.components.controls.CTextField();
        cLabel24 = new org.components.controls.CLabel();
        cTextField16 = new org.components.controls.CTextField();
        cPanel3 = new org.components.containers.CPanel();
        cLabel6 = new org.components.controls.CLabel();
        cPanel4 = new org.components.containers.CPanel();
        cLabel7 = new org.components.controls.CLabel();
        cPanel5 = new org.components.containers.CPanel();
        oxComboPorts = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        oxTxtSmscNumber = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        oxTxtSmscNumber1 = new javax.swing.JTextField();
        oxTxtSmscNumber2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        oxTxtSmscNumber3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cButton1 = new org.components.controls.CButton();
        cButton2 = new org.components.controls.CButton();
        cButton3 = new org.components.controls.CButton();
        jLabel10 = new javax.swing.JLabel();
        pCheckBox1 = new org.components.parent.controls.PCheckBox();
        pCheckBox2 = new org.components.parent.controls.PCheckBox();
        cPanel2 = new org.components.containers.CPanel();
        cLabel3 = new org.components.controls.CLabel();
        cComboBox2 = new org.components.controls.CComboBox();
        cLabel4 = new org.components.controls.CLabel();
        cComboBox3 = new org.components.controls.CComboBox();
        cLabel5 = new org.components.controls.CLabel();
        cTextField1 = new org.components.controls.CTextField();
        cLabel8 = new org.components.controls.CLabel();
        cPanel6 = new org.components.containers.CPanel();
        cLabel9 = new org.components.controls.CLabel();
        cClose = new org.components.controls.CButton();
        cSaveBtn = new org.components.controls.CButton();
        cClear = new org.components.controls.CButton();
        cDeleteBtn = new org.components.controls.CButton();

        setLayout(null);

        cRoleNameCombo.setEditable(true);
        cRoleNameCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cRoleNameComboActionPerformed(evt);
            }
        });
        add(cRoleNameCombo);
        cRoleNameCombo.setBounds(180, 10, 315, 23);

        cLabel1.setText("Security Role");
        add(cLabel1);
        cLabel1.setBounds(10, 11, 172, 20);

        cPanel1.setLayout(null);

        cLabel2.setText("0-No Access,  1-Read Only,  2-Read/Write, 3-Full Access");
        cPanel1.add(cLabel2);
        cLabel2.setBounds(0, 350, 440, 20);

        cLabel10.setText("Item Master Form");
        cPanel1.add(cLabel10);
        cLabel10.setBounds(10, 10, 160, 20);
        cPanel1.add(formItemMaster);
        formItemMaster.setBounds(170, 10, 73, 20);

        cLabel11.setText("Customer  Form");
        cPanel1.add(cLabel11);
        cLabel11.setBounds(10, 30, 160, 20);
        cPanel1.add(formCustomer);
        formCustomer.setBounds(170, 30, 73, 20);

        cLabel12.setText("Staff Form");
        cPanel1.add(cLabel12);
        cLabel12.setBounds(10, 50, 160, 20);
        cPanel1.add(formStaffForm);
        formStaffForm.setBounds(170, 50, 73, 20);

        cLabel13.setText("Supplier Form");
        cPanel1.add(cLabel13);
        cLabel13.setBounds(10, 70, 160, 20);
        cPanel1.add(formSupplierForm);
        formSupplierForm.setBounds(170, 70, 73, 20);

        cLabel14.setText("Purchase Form");
        cPanel1.add(cLabel14);
        cLabel14.setBounds(10, 110, 160, 20);
        cPanel1.add(formPurchase);
        formPurchase.setBounds(170, 110, 73, 20);

        cLabel15.setText("Beg.Customer Form");
        cPanel1.add(cLabel15);
        cLabel15.setBounds(10, 150, 160, 20);
        cPanel1.add(formBegCustomer);
        formBegCustomer.setBounds(170, 150, 73, 20);

        cLabel16.setText("Invoice Form");
        cPanel1.add(cLabel16);
        cLabel16.setBounds(10, 90, 160, 20);
        cPanel1.add(formInvoice);
        formInvoice.setBounds(170, 90, 73, 20);

        cLabel17.setText("Beginning Item Form");
        cPanel1.add(cLabel17);
        cLabel17.setBounds(10, 130, 160, 20);
        cPanel1.add(formBeginningItem);
        formBeginningItem.setBounds(170, 130, 73, 20);

        cLabel18.setText("Beg.Supplier  Form");
        cPanel1.add(cLabel18);
        cLabel18.setBounds(10, 170, 160, 20);
        cPanel1.add(formBegSupplier);
        formBegSupplier.setBounds(170, 170, 73, 20);

        cLabel20.setText("Vehicle Sale Form");
        cPanel1.add(cLabel20);
        cLabel20.setBounds(10, 210, 160, 20);
        cPanel1.add(formVehicleSalesForm);
        formVehicleSalesForm.setBounds(170, 210, 73, 20);

        cLabel21.setText("Form");
        cPanel1.add(cLabel21);
        cLabel21.setBounds(10, 250, 160, 20);
        cPanel1.add(cTextField13);
        cTextField13.setBounds(170, 250, 73, 20);

        cLabel22.setText("Vehicle Form");
        cPanel1.add(cLabel22);
        cLabel22.setBounds(10, 190, 160, 20);
        cPanel1.add(formVehicleForm);
        formVehicleForm.setBounds(170, 190, 73, 20);

        cLabel23.setText(" Form");
        cPanel1.add(cLabel23);
        cLabel23.setBounds(10, 230, 160, 20);
        cPanel1.add(cTextField15);
        cTextField15.setBounds(170, 230, 73, 20);

        cLabel24.setText("  Form");
        cPanel1.add(cLabel24);
        cLabel24.setBounds(10, 270, 160, 20);
        cPanel1.add(cTextField16);
        cTextField16.setBounds(170, 270, 73, 20);

        jTabbedPane1.addTab("Forms", cPanel1);

        cPanel3.setLayout(null);

        cLabel6.setText("0-No Access,  1-Read Only,  2-Read/Write, 3-Full Access");
        cPanel3.add(cLabel6);
        cLabel6.setBounds(10, 350, 430, 20);

        jTabbedPane1.addTab("Purchase", cPanel3);

        cPanel4.setLayout(null);

        cLabel7.setText("0-No Access,  1-Read Only,  2-Read/Write, 3-Full Access");
        cPanel4.add(cLabel7);
        cLabel7.setBounds(0, 350, 440, 20);

        jTabbedPane1.addTab("Dues", cPanel4);

        cPanel5.setLayout(null);

        cPanel5.add(oxComboPorts);
        oxComboPorts.setBounds(130, 150, 152, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma 11", 1, 12));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("OR ");
        cPanel5.add(jLabel2);
        jLabel2.setBounds(242, 11, 53, 23);
        cPanel5.add(oxTxtSmscNumber);
        oxTxtSmscNumber.setBounds(130, 180, 150, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma 11", 1, 12));
        jLabel3.setText("Smsc Number");
        cPanel5.add(jLabel3);
        jLabel3.setBounds(30, 180, 100, 23);

        jLabel5.setFont(new java.awt.Font("Tahoma 11", 1, 14));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("HTTP Setting");
        cPanel5.add(jLabel5);
        jLabel5.setBounds(301, 11, 239, 23);

        jLabel6.setFont(new java.awt.Font("Tahoma 11", 1, 14));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("GSM Modem Setting");
        cPanel5.add(jLabel6);
        jLabel6.setBounds(10, 11, 226, 23);

        jLabel7.setFont(new java.awt.Font("Tahoma 11", 1, 12));
        jLabel7.setText("Default");
        cPanel5.add(jLabel7);
        jLabel7.setBounds(20, 50, 80, 23);

        jLabel4.setFont(new java.awt.Font("Tahoma 11", 1, 12));
        jLabel4.setText("URL ");
        cPanel5.add(jLabel4);
        jLabel4.setBounds(330, 150, 57, 23);
        cPanel5.add(oxTxtSmscNumber1);
        oxTxtSmscNumber1.setBounds(390, 150, 296, 20);
        cPanel5.add(oxTxtSmscNumber2);
        oxTxtSmscNumber2.setBounds(390, 170, 170, 20);

        jLabel8.setFont(new java.awt.Font("Tahoma 11", 1, 12));
        jLabel8.setText("Username");
        cPanel5.add(jLabel8);
        jLabel8.setBounds(330, 170, 70, 23);
        cPanel5.add(oxTxtSmscNumber3);
        oxTxtSmscNumber3.setBounds(390, 210, 170, 20);

        jLabel9.setFont(new java.awt.Font("Tahoma 11", 1, 12));
        jLabel9.setText("Password");
        cPanel5.add(jLabel9);
        jLabel9.setBounds(330, 210, 70, 23);

        cButton1.setText("Next");
        cPanel5.add(cButton1);
        cButton1.setBounds(360, 250, 80, 40);

        cButton2.setText("Save");
        cButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton2ActionPerformed(evt);
            }
        });
        cPanel5.add(cButton2);
        cButton2.setBounds(180, 250, 80, 40);

        cButton3.setText("Clear");
        cPanel5.add(cButton3);
        cButton3.setBounds(270, 250, 80, 40);

        jLabel10.setFont(new java.awt.Font("Tahoma 11", 1, 12));
        jLabel10.setText("Port");
        cPanel5.add(jLabel10);
        jLabel10.setBounds(30, 150, 77, 23);

        tDefaultSettingGrp.add(pCheckBox1);
        pCheckBox1.setText("GSM MODEM");
        cPanel5.add(pCheckBox1);
        pCheckBox1.setBounds(120, 50, 140, 23);

        tDefaultSettingGrp.add(pCheckBox2);
        pCheckBox2.setText("HTTP");
        cPanel5.add(pCheckBox2);
        pCheckBox2.setBounds(320, 50, 140, 23);

        jTabbedPane1.addTab("Sms", cPanel5);

        cPanel2.setLayout(null);

        cLabel3.setText("Printer For Invoice");
        cPanel2.add(cLabel3);
        cLabel3.setBounds(10, 40, 137, 25);
        cPanel2.add(cComboBox2);
        cComboBox2.setBounds(222, 41, 206, 23);

        cLabel4.setText("Report Format For Invoice");
        cPanel2.add(cLabel4);
        cLabel4.setBounds(10, 71, 191, 25);
        cPanel2.add(cComboBox3);
        cComboBox3.setBounds(222, 72, 206, 23);

        cLabel5.setText("View Customer Credit Details");
        cPanel2.add(cLabel5);
        cLabel5.setBounds(10, 102, 208, 25);
        cPanel2.add(cTextField1);
        cTextField1.setBounds(222, 102, 73, 25);

        cLabel8.setText("0-No Access,   1-View Only,   2-Save/Update,  3-Full Access(Include Delete)");
        cPanel2.add(cLabel8);
        cLabel8.setBounds(0, 350, 540, 20);

        jTabbedPane1.addTab("Invoice ", cPanel2);

        cPanel6.setLayout(null);

        cLabel9.setText("0-No Access,  1-Read Only,  2-Read/Write, 3-Full Access");
        cPanel6.add(cLabel9);
        cLabel9.setBounds(0, 350, 440, 20);

        jTabbedPane1.addTab("Reports", cPanel6);

        add(jTabbedPane1);
        jTabbedPane1.setBounds(10, 42, 820, 410);

        cClose.setText("Goto List ");
        add(cClose);
        cClose.setBounds(720, 460, 90, 50);

        cSaveBtn.setText("Save");
        cSaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cSaveBtnActionPerformed(evt);
            }
        });
        add(cSaveBtn);
        cSaveBtn.setBounds(450, 460, 80, 50);

        cClear.setText("Clear");
        cClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cClearActionPerformed(evt);
            }
        });
        add(cClear);
        cClear.setBounds(540, 460, 80, 50);

        cDeleteBtn.setText("Delete");
        cDeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cDeleteBtnActionPerformed(evt);
            }
        });
        add(cDeleteBtn);
        cDeleteBtn.setBounds(630, 460, 80, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void cSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cSaveBtnActionPerformed


        try {
        } catch (Exception e) {
            e.printStackTrace();
            MessageBoxes.errormsg(null, e.getMessage(), "Error");
        }

    }//GEN-LAST:event_cSaveBtnActionPerformed

    private void cClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cClearActionPerformed
}//GEN-LAST:event_cClearActionPerformed

    private void cDeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cDeleteBtnActionPerformed
        try {
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cDeleteBtnActionPerformed

    private void cRoleNameComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cRoleNameComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cRoleNameComboActionPerformed

    private void cButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton2ActionPerformed
//       tDefaultSettingGrp.
    }//GEN-LAST:event_cButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CButton cButton2;
    private org.components.controls.CButton cButton3;
    private org.components.controls.CButton cClear;
    private org.components.controls.CButton cClose;
    private org.components.controls.CComboBox cComboBox2;
    private org.components.controls.CComboBox cComboBox3;
    private org.components.controls.CButton cDeleteBtn;
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel10;
    private org.components.controls.CLabel cLabel11;
    private org.components.controls.CLabel cLabel12;
    private org.components.controls.CLabel cLabel13;
    private org.components.controls.CLabel cLabel14;
    private org.components.controls.CLabel cLabel15;
    private org.components.controls.CLabel cLabel16;
    private org.components.controls.CLabel cLabel17;
    private org.components.controls.CLabel cLabel18;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel20;
    private org.components.controls.CLabel cLabel21;
    private org.components.controls.CLabel cLabel22;
    private org.components.controls.CLabel cLabel23;
    private org.components.controls.CLabel cLabel24;
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
    private org.components.containers.CPanel cPanel6;
    private org.components.controls.CComboBox cRoleNameCombo;
    private org.components.controls.CButton cSaveBtn;
    private org.components.controls.CTextField cTextField1;
    private org.components.controls.CTextField cTextField13;
    private org.components.controls.CTextField cTextField15;
    private org.components.controls.CTextField cTextField16;
    private org.components.controls.CTextField formBegCustomer;
    private org.components.controls.CTextField formBegSupplier;
    private org.components.controls.CTextField formBeginningItem;
    private org.components.controls.CTextField formCustomer;
    private org.components.controls.CTextField formInvoice;
    private org.components.controls.CTextField formItemMaster;
    private org.components.controls.CTextField formPurchase;
    private org.components.controls.CTextField formStaffForm;
    private org.components.controls.CTextField formSupplierForm;
    private org.components.controls.CTextField formVehicleForm;
    private org.components.controls.CTextField formVehicleSalesForm;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox oxComboPorts;
    private javax.swing.JTextField oxTxtSmscNumber;
    private javax.swing.JTextField oxTxtSmscNumber1;
    private javax.swing.JTextField oxTxtSmscNumber2;
    private javax.swing.JTextField oxTxtSmscNumber3;
    private org.components.parent.controls.PCheckBox pCheckBox1;
    private org.components.parent.controls.PCheckBox pCheckBox2;
    private javax.swing.ButtonGroup tDefaultSettingGrp;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {

        return "Security Form";
    }

    @Override
    public JPanel getJPanel() {

        return this;
    }
}
