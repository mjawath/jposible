package org.biz.invoicesystem.master.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.app.ui.util.UIEty;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.service.master.CustomerService;
import org.components.windows.DetailPanel;

public class CustomerMasterUI2 extends DetailPanel<Customer> {

    private CustomerService cService;
    // List<Customer> customers;
    private Customer selectedCus;

    @Override
    public void init() {
        crudcontrolPanel.setCrudController(this);
        try {

            cService = new CustomerService();
            //customers=new ArrayList<Customer>();
            // customer=new Customer();

            loadComboItems();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /////////////////////////////////////
    ///////////////////////////////////////////////
    public void keyListeners() {
        try {
            tCusCode.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    try {
                        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                            tCusTitle.getEditor().getEditorComponent().requestFocus();
                        }
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                }
            });


            tCusTitle.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    try {
                        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                        }
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                }
            });






        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////  
    ///////////////////////////////////////////
    public void loadComboItems() {
        try {
            //c.type , c.religion , c.title
            List<Object[]> lstOfArray = cService.getDao().loadComboItems();

            //list of array retuns String array  
            //object is String array....

            Set<String> types = new TreeSet<String>();
            Set<String> titles = new TreeSet<String>();
            for (Object[] ss : lstOfArray) {
                String type = (String) ss[0];
                types.add(type);

                String title = (String) ss[0];
                titles.add(title);
            }
            UIEty.loadcombo(tCusType, types);
            UIEty.loadcombo(tCusTitle, titles);
//     
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates new form cust
     */
    public CustomerMasterUI2() {
        initComponents();
        init();
    }

    public Customer uiToEntity(Customer c) throws Exception {
        try {
            

            c.setCode(UIEty.tcToStr(tCusCode));
            c.setTitle(UIEty.cmbtostr(tCusTitle));
            c.setCustomerName(UIEty.tcToStr(tCusName));
            c.setDob(tCusDOB.getDate());
            c.setCompanyName(UIEty.tcToStr(tCusCompany));
            c.setReligion(UIEty.cmbtostr(tCusReligion));
            c.setType(UIEty.cmbtostr(tCusType));
            c.setDiscount(UIEty.tcToDble0(tCusDiscount));
            c.setCreditLimit(UIEty.tcToDble0(tCusCreditLimit));
            c.setSalesRep(UIEty.cmbtostr(tCusSalesRep));
            c.setLoyaltyCardNo(UIEty.tcToStr(tCusLoyalty));
            c.setNicno(UIEty.tcToStr(tCusNIC));
            c.setAddress(UIEty.tcToStr(tCusAdd1));
            c.setAddress2(UIEty.tcToStr(tCusAdd2));
            c.setCity(UIEty.tcToStr(tCusCity));
            c.setPhone(UIEty.tcToStr(tCusPhone));
            c.setMobile(UIEty.tcToStr(tCusMobile));
            c.setEmail(UIEty.tcToStr(tCusEmail));

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return c;
    }

    public void entity2Ui(Customer c) throws Exception {
        try {
            UIEty.objToUi(tCusCode, c.getCustomerID());//    c.setCode(uiEty.tcToStr(tCusId));
            UIEty.objToUi(tCusTitle, c.getTitle());//    c.setTitle(uiEty.cmbtostr(tCusTitle));
            UIEty.objToUi(tCusName, c.getCustomerName());//    c.setCustomerName(uiEty.tcToStr(tCusName));
            tCusDOB.setDate(c.getDob());
            UIEty.objToUi(tCusCompany, c.getCompanyName());//    c.setCompanyName(uiEty.tcToStr(tCusCompany));
            UIEty.objToUi(tCusReligion, c.getReligion());//    c.setReligion(uiEty.cmbtostr(tCusReligion));
            UIEty.objToUi(tCusType, c.getType());//    c.setType(uiEty.cmbtostr(tCusType));
            UIEty.objToUi(tCusDiscount, c.getDiscount());//    c.setDiscount(uiEty.tcToDble0(tCusDiscount));
            UIEty.objToUi(tCusCreditLimit, c.getCreditLimit());//    c.setCreditLimit(uiEty.tcToDble0(tCusCreditLimit));
            UIEty.objToUi(tCusSalesRep, c.getSalesRep());//    c.setSalesRep(uiEty.cmbtostr(tCusSalesRep));
            UIEty.objToUi(tCusLoyalty, c.getLoyaltyCardNo());//    c.setLoyaltyCardNo(uiEty.tcToStr(tCusLoyalty));
            UIEty.objToUi(tCusNIC, c.getNicno());//    c.setNicno(uiEty.tcToStr(tCusNIC));
            UIEty.objToUi(tCusAdd1, c.getAddress());//    c.setAddress(uiEty.tcToStr(tCusAdd1));
            UIEty.objToUi(tCusAdd2, c.getAddress2());//    c.setAddress2(uiEty.tcToStr(tCusAdd2));
            UIEty.objToUi(tCusCity, c.getCity());//    c.setCity(uiEty.tcToStr(tCusCity));
            UIEty.objToUi(tCusPhone, c.getPicLocation());//    c.setPhone(uiEty.tcToStr(tCusPhone));
            UIEty.objToUi(tCusMobile, c.getMobile());//    c.setMobile(uiEty.tcToStr(tCusMobile));
            UIEty.objToUi(tCusEmail, c.getEmail());//    c.setEmail(uiEty.tcToStr(tCusEmail));


        } catch (Exception e) {
            throw e;
        }
    }

    public void clearForm() {
        try {
            entity2Ui(new Customer());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cLabel6 = new org.components.controls.CLabel();
        tCusType = new org.components.controls.CComboBox();
        cLabel7 = new org.components.controls.CLabel();
        cLabel2 = new org.components.controls.CLabel();
        cLabel8 = new org.components.controls.CLabel();
        cLabel1 = new org.components.controls.CLabel();
        cLabel4 = new org.components.controls.CLabel();
        tCusName = new org.components.controls.CTextField();
        tCusDiscount = new org.components.controls.CTextField();
        cLabel3 = new org.components.controls.CLabel();
        tCusTitle = new org.components.controls.CComboBox();
        tCusDOB = new org.components.controls.CDatePicker();
        cLabel5 = new org.components.controls.CLabel();
        tCusAdd1 = new org.components.controls.CTextField();
        tCusAdd2 = new org.components.controls.CTextField();
        tCusCity = new org.components.controls.CTextField();
        tCusPhone = new org.components.controls.CTextField();
        cLabel9 = new org.components.controls.CLabel();
        tCusMobile = new org.components.controls.CTextField();
        cLabel10 = new org.components.controls.CLabel();
        tCusEmail = new org.components.controls.CTextField();
        cLabel11 = new org.components.controls.CLabel();
        cLabel12 = new org.components.controls.CLabel();
        tCusSalesRep = new org.components.controls.CComboBox();
        tCusCompany = new org.components.controls.CTextField();
        cLabel13 = new org.components.controls.CLabel();
        tCusReligion = new org.components.controls.CComboBox();
        cLabel14 = new org.components.controls.CLabel();
        tCusLoyalty = new org.components.controls.CTextField();
        cLabel15 = new org.components.controls.CLabel();
        tCusCreditLimit = new org.components.controls.CTextField();
        tCusCode = new org.components.controls.CTextField();
        cLabel16 = new org.components.controls.CLabel();
        cLabel17 = new org.components.controls.CLabel();
        tCusNIC = new org.components.controls.CTextField();
        crudcontrolPanel = new com.components.custom.ControlPanel();

        setLayout(null);

        cLabel6.setText("Address");
        add(cLabel6);
        cLabel6.setBounds(410, 110, 63, 25);

        tCusType.setEditable(true);
        add(tCusType);
        tCusType.setBounds(140, 140, 180, 23);

        cLabel7.setText("City");
        add(cLabel7);
        cLabel7.setBounds(410, 180, 31, 25);

        cLabel2.setText("Custmer Code");
        add(cLabel2);
        cLabel2.setBounds(10, 10, 239, 25);

        cLabel8.setText("Phone");
        add(cLabel8);
        cLabel8.setBounds(410, 220, 48, 25);

        cLabel1.setText("Discount (%)");
        add(cLabel1);
        cLabel1.setBounds(10, 180, 119, 25);

        cLabel4.setText("Custmer Name ");
        add(cLabel4);
        cLabel4.setBounds(330, 10, 119, 25);

        tCusName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusNameActionPerformed(evt);
            }
        });
        add(tCusName);
        tCusName.setBounds(330, 40, 275, 25);

        tCusDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusDiscountActionPerformed(evt);
            }
        });
        add(tCusDiscount);
        tCusDiscount.setBounds(140, 180, 180, 25);

        cLabel3.setText("Title");
        add(cLabel3);
        cLabel3.setBounds(260, 10, 40, 25);

        tCusTitle.setEditable(true);
        add(tCusTitle);
        tCusTitle.setBounds(260, 40, 63, 23);
        add(tCusDOB);
        tCusDOB.setBounds(610, 40, 108, 22);

        cLabel5.setText("DOB");
        add(cLabel5);
        cLabel5.setBounds(610, 10, 110, 25);

        tCusAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusAdd1ActionPerformed(evt);
            }
        });
        add(tCusAdd1);
        tCusAdd1.setBounds(480, 110, 284, 25);

        tCusAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusAdd2ActionPerformed(evt);
            }
        });
        add(tCusAdd2);
        tCusAdd2.setBounds(480, 140, 284, 25);

        tCusCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusCityActionPerformed(evt);
            }
        });
        add(tCusCity);
        tCusCity.setBounds(480, 180, 284, 25);

        tCusPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusPhoneActionPerformed(evt);
            }
        });
        add(tCusPhone);
        tCusPhone.setBounds(480, 220, 284, 25);

        cLabel9.setText("Mobile");
        add(cLabel9);
        cLabel9.setBounds(410, 260, 48, 25);

        tCusMobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusMobileActionPerformed(evt);
            }
        });
        add(tCusMobile);
        tCusMobile.setBounds(480, 260, 284, 25);

        cLabel10.setText("Email");
        add(cLabel10);
        cLabel10.setBounds(410, 300, 48, 25);

        tCusEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusEmailActionPerformed(evt);
            }
        });
        add(tCusEmail);
        tCusEmail.setBounds(480, 300, 284, 25);

        cLabel11.setText("S.Rep");
        add(cLabel11);
        cLabel11.setBounds(10, 240, 130, 25);

        cLabel12.setText("Company Name");
        add(cLabel12);
        cLabel12.setBounds(10, 80, 130, 25);

        tCusSalesRep.setEditable(true);
        add(tCusSalesRep);
        tCusSalesRep.setBounds(140, 240, 180, 23);

        tCusCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusCompanyActionPerformed(evt);
            }
        });
        add(tCusCompany);
        tCusCompany.setBounds(140, 80, 239, 25);

        cLabel13.setText("Type");
        add(cLabel13);
        cLabel13.setBounds(10, 140, 119, 25);

        tCusReligion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...", "Buddhist", "Hindu", "Muslim", "Christian" }));
        add(tCusReligion);
        tCusReligion.setBounds(140, 110, 180, 23);

        cLabel14.setText("Loyalty Card");
        add(cLabel14);
        cLabel14.setBounds(10, 270, 119, 25);

        tCusLoyalty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusLoyaltyActionPerformed(evt);
            }
        });
        add(tCusLoyalty);
        tCusLoyalty.setBounds(140, 270, 230, 25);

        cLabel15.setText("Credit Limit");
        add(cLabel15);
        cLabel15.setBounds(10, 210, 119, 25);

        tCusCreditLimit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusCreditLimitActionPerformed(evt);
            }
        });
        add(tCusCreditLimit);
        tCusCreditLimit.setBounds(140, 210, 180, 25);

        tCusCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusCodeActionPerformed(evt);
            }
        });
        add(tCusCode);
        tCusCode.setBounds(10, 40, 239, 25);

        cLabel16.setText("Religion");
        add(cLabel16);
        cLabel16.setBounds(10, 110, 119, 25);

        cLabel17.setText("NIC");
        add(cLabel17);
        cLabel17.setBounds(410, 80, 48, 25);

        tCusNIC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusNICActionPerformed(evt);
            }
        });
        add(tCusNIC);
        tCusNIC.setBounds(480, 80, 284, 25);
        add(crudcontrolPanel);
        crudcontrolPanel.setBounds(30, 310, 340, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void tCusNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCusNameActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_tCusNameActionPerformed

    private void tCusDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCusDiscountActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_tCusDiscountActionPerformed

    @Override
    public void save() {
        try {
            if (UIEty.tcToStr(tCusCode) == null || UIEty.tcToStr(tCusCode).equals("")) {
                MessageBoxes.wrnmsg(null, "Please Type Customer Code", "Empty Customer Code");
                return;
            }
            //saving customer             
            Customer c = uiToEntity(new Customer());//from ui....
            Customer exist = cService.getDao().findCustomerByCode(c.getCode());
            if (exist == null) {

                cService.getDao().save(c);


            } else {

                String[] ObjButtons = {"Yes", "No"};
                int PromptResult = JOptionPane.showOptionDialog(null, "Customer Exist Do You Want to Update it?", getTabName(), -1, 2, null, ObjButtons, ObjButtons[1]);

                if (PromptResult == 0) {
                    c.setId(exist.getId());
                    cService.getDao().update(c);

                } else {
                    return;
                }
            }
            //updating customers       
            clearForm();

            tCusCode.requestFocus();

        } catch (Exception e) {
            e.printStackTrace();
            MessageBoxes.errormsg(null, e.getMessage(), "Error");
        }


        super.save();
    }

    @Override
    public void delete() {
            try {
            if (UIEty.tcToStr(tCusCode) == null || UIEty.tcToStr(tCusCode).equals("")) {
                MessageBoxes.wrnmsg(null, "Please Type Customer Code", "Empty Customer Code");
                return;
            }
            //delete the selected customer...     
            Customer c = uiToEntity(new Customer());//from ui....
            Customer exist = cService.getDao().findCustomerByCode(c.getCode());
            if (exist != null) {

                cService.getDao().delete(exist);


            } else {
                MessageBoxes.warn(null, "No Customer Found.", getTabName());
                return;
            }
            clearForm();

            tCusCode.requestFocus();

        } catch (Exception e) {
            e.printStackTrace();
            MessageBoxes.errormsg(null, e.getMessage(), "Error");
        }
        super.delete();
    }

    @Override
    public void clear() {

        clearForm();

    }
    
    
    private void tCusAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCusAdd1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCusAdd1ActionPerformed

    private void tCusAdd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCusAdd2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCusAdd2ActionPerformed

    private void tCusCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCusCityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCusCityActionPerformed

    private void tCusPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCusPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCusPhoneActionPerformed

    private void tCusMobileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCusMobileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCusMobileActionPerformed

    private void tCusEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCusEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCusEmailActionPerformed

    private void tCusCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCusCompanyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCusCompanyActionPerformed

    private void tCusLoyaltyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCusLoyaltyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCusLoyaltyActionPerformed

    private void tCusCreditLimitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCusCreditLimitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCusCreditLimitActionPerformed

    private void tCusCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCusCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCusCodeActionPerformed

    private void tCusNICActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCusNICActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCusNICActionPerformed

     /**
      * @param cService the cService to set
      */
     public void setcService(CustomerService cService) {
         this.cService = cService;
     }

     @Override
     public String getTabName() {
         return "Customer Form";
     }

     @Override
     public JPanel getJPanel() {

         return this;
     }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cLabel1;
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
    private com.components.custom.ControlPanel crudcontrolPanel;
    private org.components.controls.CTextField tCusAdd1;
    private org.components.controls.CTextField tCusAdd2;
    private org.components.controls.CTextField tCusCity;
    private org.components.controls.CTextField tCusCode;
    private org.components.controls.CTextField tCusCompany;
    private org.components.controls.CTextField tCusCreditLimit;
    private org.components.controls.CDatePicker tCusDOB;
    private org.components.controls.CTextField tCusDiscount;
    private org.components.controls.CTextField tCusEmail;
    private org.components.controls.CTextField tCusLoyalty;
    private org.components.controls.CTextField tCusMobile;
    private org.components.controls.CTextField tCusNIC;
    private org.components.controls.CTextField tCusName;
    private org.components.controls.CTextField tCusPhone;
    private org.components.controls.CComboBox tCusReligion;
    private org.components.controls.CComboBox tCusSalesRep;
    private org.components.controls.CComboBox tCusTitle;
    private org.components.controls.CComboBox tCusType;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the selectedCus
     */
    public Customer getSelectedCus() {
        return selectedCus;
    }

    /**
     * @param selectedCus the selectedCus to set
     */
    public void setSelectedCus(Customer selectedCus) {
        this.selectedCus = selectedCus;
    }
}
