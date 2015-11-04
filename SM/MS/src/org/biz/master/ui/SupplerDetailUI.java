package org.biz.master.ui;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.app.ui.util.UIEty;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.master.Supplier;
import org.biz.invoicesystem.service.master.SupplierService;
import org.components.windows.DetailPanel;

public class SupplerDetailUI extends DetailPanel<Supplier> {

    SupplierService supplierService;

    public SupplerDetailUI() {

        super();

    }

    public void init() {
        try {
            super.init();
            initComponents();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

///////////////////////////////////////////////////////
    public void loadComboBoxData() {

        try {
            List<Object[]> lstOfArray = supplierService.getDao().loadComboItems();

            //list of array retuns String array  
            //object is String array....

            Set<String> types = new TreeSet<String>();
            Set<String> titles = new TreeSet<String>();

            for (Object[] ss : lstOfArray) {
                String type = (String) ss[0];
                types.add(type);

                String title = (String) ss[1];
                titles.add(title);
            }
            UIEty.loadcombo(tSuppType, types);
            UIEty.loadcombo(tSuppTitle, titles);
//     

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
///////////////////////////////////////////////////

    public Supplier uiToEntity(Supplier s) throws Exception {
        try {


//            s.setId(EntityService.getEntityService().getKey());
            s.setCode(UIEty.tcToStr(tSuppId));
            s.setTitle(UIEty.cmbtostr(tSuppTitle));
            s.setName(UIEty.tcToStr(tSuppName));
            s.setDob(cSuppDob.getDate());
            s.setCurDate(new Date());
            s.setCompany(UIEty.tcToStr(tSuppCompanyName));
            s.setReligion(tSuppReligion.getSelectedItem() == null ? "" : tSuppReligion.getSelectedItem().toString());
            s.setType(UIEty.cmbtostr(tSuppType));

            s.setAddress1(UIEty.tcToStr(tSuppAddress1));
            s.setAddress2(UIEty.tcToStr(tSuppAddress2));
            s.setCity(UIEty.tcToStr(tSuppCity));

            s.setPhone(UIEty.tcToStr(tSuppPhone));
            s.setMobilePhone(UIEty.tcToStr(tSuppMobile));


            s.setEmail(UIEty.tcToStr(tSuppEmail));



        } catch (Exception x) {

            x.printStackTrace();
            throw x;
        }
        return s;
    }

    public void entityToUi(Supplier s) throws Exception {
        try {
//   s.setId(EntityService.getEntityService().getKey(""));     
            UIEty.objToUi(tSuppId, s.getCode());//  s.setCode(uiEty.tcToStr(tSuppId));
            UIEty.objToUi(tSuppTitle, s.getTitle());    //  s.setTitle(uiEty.cmbtostr(tSuppTitle));
            UIEty.objToUi(tSuppTitle, s.getTitle());//  s.setName(uiEty.tcToStr(tSuppName));
            cSuppDob.setDate(s.getDob());//  s.setDob(cSuppDob.getDate());
//  s.setCurDate(new Date());
            UIEty.objToUi(tSuppCompanyName, s.getCompany());//  s.setCompany(uiEty.tcToStr(tSuppCompanyName));
            UIEty.objToUi(tSuppReligion, s.getReligion());//  s.setReligion(tSuppReligion.getSelectedItem()==null?"":tSuppReligion.getSelectedItem().toString());
            UIEty.objToUi(tSuppType, s.getType());//  s.setType(uiEty.cmbtostr(tSuppType));
//
            UIEty.objToUi(tSuppAddress1, s.getAddress1());//  s.setAddress1(uiEty.tcToStr(tSuppAddress1));
            UIEty.objToUi(tSuppAddress2, s.getAddress2());//  s.setAddress2(uiEty.tcToStr(tSuppAddress2));
            UIEty.objToUi(tSuppCity, s.getCity());//  s.setCity(uiEty.tcToStr(tSuppCity));
//  
            UIEty.objToUi(tSuppPhone, s.getPhone());//  s.setPhone(uiEty.tcToStr(tSuppPhone));
            UIEty.objToUi(tSuppMobile, s.getMobilePhone());//  s.setMobilePhone(uiEty.tcToStr(tSuppMobile));
//  
//  
            UIEty.objToUi(tSuppEmail, s.getEmail());//  s.setEmail(uiEty.tcToStr(tSuppEmail));
//    

        } catch (Exception e) {
            throw e;
        }
    }

    public void clear() {
        try {
            entityToUi(new Supplier());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cLabel6 = new org.components.controls.CLabel();
        tSuppType = new org.components.controls.CComboBox();
        cLabel7 = new org.components.controls.CLabel();
        cLabel2 = new org.components.controls.CLabel();
        cLabel8 = new org.components.controls.CLabel();
        cLabel4 = new org.components.controls.CLabel();
        tSuppName = new org.components.controls.CTextField();
        cLabel3 = new org.components.controls.CLabel();
        tSuppTitle = new org.components.controls.CComboBox();
        cSuppDob = new org.components.controls.CDatePicker();
        cLabel5 = new org.components.controls.CLabel();
        cClose = new org.components.controls.CButton();
        cSave = new org.components.controls.CButton();
        cClear = new org.components.controls.CButton();
        cDelete = new org.components.controls.CButton();
        tSuppAddress1 = new org.components.controls.CTextField();
        tSuppAddress2 = new org.components.controls.CTextField();
        tSuppCity = new org.components.controls.CTextField();
        tSuppPhone = new org.components.controls.CTextField();
        cLabel9 = new org.components.controls.CLabel();
        tSuppMobile = new org.components.controls.CTextField();
        cLabel10 = new org.components.controls.CLabel();
        tSuppEmail = new org.components.controls.CTextField();
        cLabel12 = new org.components.controls.CLabel();
        tSuppCompanyName = new org.components.controls.CTextField();
        cLabel13 = new org.components.controls.CLabel();
        tSuppReligion = new org.components.controls.CComboBox();
        tSuppId = new org.components.controls.CTextField();
        cLabel14 = new org.components.controls.CLabel();

        setLayout(null);

        cLabel6.setText("Address");
        add(cLabel6);
        cLabel6.setBounds(360, 80, 63, 25);

        tSuppType.setEditable(true);
        add(tSuppType);
        tSuppType.setBounds(140, 160, 160, 23);

        cLabel7.setText("City");
        add(cLabel7);
        cLabel7.setBounds(360, 150, 31, 25);

        cLabel2.setText("Supplier Code");
        add(cLabel2);
        cLabel2.setBounds(10, 10, 239, 25);

        cLabel8.setText("Phone");
        add(cLabel8);
        cLabel8.setBounds(360, 190, 48, 25);

        cLabel4.setText("Name ");
        add(cLabel4);
        cLabel4.setBounds(330, 10, 119, 25);

        tSuppName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tSuppNameActionPerformed(evt);
            }
        });
        add(tSuppName);
        tSuppName.setBounds(330, 40, 275, 25);

        cLabel3.setText("Title");
        add(cLabel3);
        cLabel3.setBounds(260, 10, 40, 25);

        tSuppTitle.setEditable(true);
        add(tSuppTitle);
        tSuppTitle.setBounds(260, 40, 63, 23);
        add(cSuppDob);
        cSuppDob.setBounds(610, 40, 112, 22);

        cLabel5.setText("DOB");
        add(cLabel5);
        cLabel5.setBounds(610, 10, 110, 25);

        cClose.setText("Go to List");
        cClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cCloseActionPerformed(evt);
            }
        });
        add(cClose);
        cClose.setBounds(230, 210, 80, 40);

        cSave.setText("Save");
        cSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cSaveActionPerformed(evt);
            }
        });
        add(cSave);
        cSave.setBounds(40, 210, 29, 40);

        cClear.setText("Clear");
        cClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cClearActionPerformed(evt);
            }
        });
        add(cClear);
        cClear.setBounds(100, 210, 29, 40);

        cDelete.setText("Delete");
        cDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cDeleteActionPerformed(evt);
            }
        });
        add(cDelete);
        cDelete.setBounds(160, 210, 35, 40);

        tSuppAddress1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tSuppAddress1ActionPerformed(evt);
            }
        });
        add(tSuppAddress1);
        tSuppAddress1.setBounds(430, 80, 284, 25);

        tSuppAddress2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tSuppAddress2ActionPerformed(evt);
            }
        });
        add(tSuppAddress2);
        tSuppAddress2.setBounds(430, 110, 284, 25);

        tSuppCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tSuppCityActionPerformed(evt);
            }
        });
        add(tSuppCity);
        tSuppCity.setBounds(430, 150, 284, 25);

        tSuppPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tSuppPhoneActionPerformed(evt);
            }
        });
        add(tSuppPhone);
        tSuppPhone.setBounds(430, 190, 284, 25);

        cLabel9.setText("Mobile");
        add(cLabel9);
        cLabel9.setBounds(360, 230, 48, 25);

        tSuppMobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tSuppMobileActionPerformed(evt);
            }
        });
        add(tSuppMobile);
        tSuppMobile.setBounds(430, 230, 284, 25);

        cLabel10.setText("Email");
        add(cLabel10);
        cLabel10.setBounds(360, 260, 48, 25);

        tSuppEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tSuppEmailActionPerformed(evt);
            }
        });
        add(tSuppEmail);
        tSuppEmail.setBounds(430, 260, 284, 25);

        cLabel12.setText("Company Name");
        add(cLabel12);
        cLabel12.setBounds(20, 90, 119, 25);

        tSuppCompanyName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tSuppCompanyNameActionPerformed(evt);
            }
        });
        add(tSuppCompanyName);
        tSuppCompanyName.setBounds(139, 90, 190, 25);

        cLabel13.setText("Type");
        add(cLabel13);
        cLabel13.setBounds(20, 160, 119, 25);

        tSuppReligion.setEditable(true);
        add(tSuppReligion);
        tSuppReligion.setBounds(140, 130, 160, 23);

        tSuppId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tSuppIdActionPerformed(evt);
            }
        });
        add(tSuppId);
        tSuppId.setBounds(10, 40, 240, 25);

        cLabel14.setText("Religion");
        add(cLabel14);
        cLabel14.setBounds(20, 130, 119, 25);
    }// </editor-fold>//GEN-END:initComponents

    private void tSuppNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tSuppNameActionPerformed
}//GEN-LAST:event_tSuppNameActionPerformed

    private void cSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cSaveActionPerformed
        try {
            if (UIEty.tcToStr(tSuppId) == null || UIEty.tcToStr(tSuppId).equals("")) {
                MessageBoxes.wrnmsg(null, "Please Type Customer Code", "Empty Customer Code");
                return;
            }
            //saving customer             
            Supplier s = uiToEntity(new Supplier());//from ui....
            Supplier exist = supplierService.getDao().findSupplierByCode(s.getCode());
            if (exist == null) {

                supplierService.getDao().save(s);


            } else {

                String[] ObjButtons = {"Yes", "No"};
                int PromptResult = JOptionPane.showOptionDialog(null, "Supplier Exist Do You Want to Update it?", getTabName(), -1, 2, null, ObjButtons, ObjButtons[1]);

                if (PromptResult == 0) {
                    s.setId(exist.getId());
                    supplierService.getDao().update(s);

                } else {
                    return;
                }
            }
            //updating customers       
            clear();

            tSuppId.requestFocus();

        } catch (Exception e) {
            e.printStackTrace();
            MessageBoxes.errormsg(null, e.getMessage(), "Error");
        }
}//GEN-LAST:event_cSaveActionPerformed

    private void tSuppAddress1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tSuppAddress1ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_tSuppAddress1ActionPerformed

    private void tSuppAddress2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tSuppAddress2ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_tSuppAddress2ActionPerformed

    private void tSuppCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tSuppCityActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_tSuppCityActionPerformed

    private void tSuppPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tSuppPhoneActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_tSuppPhoneActionPerformed

    private void tSuppMobileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tSuppMobileActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_tSuppMobileActionPerformed

    private void tSuppEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tSuppEmailActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_tSuppEmailActionPerformed

    private void tSuppCompanyNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tSuppCompanyNameActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_tSuppCompanyNameActionPerformed

    private void tSuppIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tSuppIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tSuppIdActionPerformed

    private void cDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cDeleteActionPerformed
        try {

            if (UIEty.tcToStr(tSuppId) == null || UIEty.tcToStr(tSuppId).equals("")) {
                MessageBoxes.wrnmsg(null, "Please Type Customer Code", "Empty Customer Code");
                return;
            }
            //delete the selected customer...     
            Supplier s = uiToEntity(new Supplier());//from ui....
            Supplier exist = supplierService.getDao().findSupplierByCode(s.getCode());
            if (exist != null) {

                supplierService.getDao().delete(exist);

            } else {

                MessageBoxes.warn(null, "No Supplier Found.", getTabName());
                return;

            }
            clear();

            tSuppId.requestFocus();

        } catch (Exception e) {
            e.printStackTrace();
            MessageBoxes.errormsg(null, e.getMessage(), "Error");
        }
    }//GEN-LAST:event_cDeleteActionPerformed

    private void cClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cClearActionPerformed
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cClearActionPerformed

    private void cCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cCloseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cCloseActionPerformed
    public void setService(Service service) {
        this.service = service;
        supplierService = (SupplierService) service;
//        init(); 
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cClear;
    private org.components.controls.CButton cClose;
    private org.components.controls.CButton cDelete;
    private org.components.controls.CLabel cLabel10;
    private org.components.controls.CLabel cLabel12;
    private org.components.controls.CLabel cLabel13;
    private org.components.controls.CLabel cLabel14;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel3;
    private org.components.controls.CLabel cLabel4;
    private org.components.controls.CLabel cLabel5;
    private org.components.controls.CLabel cLabel6;
    private org.components.controls.CLabel cLabel7;
    private org.components.controls.CLabel cLabel8;
    private org.components.controls.CLabel cLabel9;
    private org.components.controls.CButton cSave;
    private org.components.controls.CDatePicker cSuppDob;
    private org.components.controls.CTextField tSuppAddress1;
    private org.components.controls.CTextField tSuppAddress2;
    private org.components.controls.CTextField tSuppCity;
    private org.components.controls.CTextField tSuppCompanyName;
    private org.components.controls.CTextField tSuppEmail;
    private org.components.controls.CTextField tSuppId;
    private org.components.controls.CTextField tSuppMobile;
    private org.components.controls.CTextField tSuppName;
    private org.components.controls.CTextField tSuppPhone;
    private org.components.controls.CComboBox tSuppReligion;
    private org.components.controls.CComboBox tSuppTitle;
    private org.components.controls.CComboBox tSuppType;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return "Suppler Master";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }
}
