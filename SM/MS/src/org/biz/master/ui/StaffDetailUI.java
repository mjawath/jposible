 
package org.biz.master.ui;

import org.biz.invoicesystem.master.ui.*;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.app.ui.util.UIEty;
import org.biz.dao.util.EntityService;
import org.biz.invoicesystem.entity.master.Staff;
import org.biz.invoicesystem.service.master.StaffService;
import org.components.windows.DetailPanel;
import org.components.windows.TabPanelUI;

 
public class StaffDetailUI extends DetailPanel<Staff>  {

    
  private StaffService sService;
  Staff selectedStaff;
 // List<Customer> customers;
  
  
     @Override
    public void init() {
  
        try {
    super.init();
    initComponents();
    sService=new StaffService();
    selectedStaff=new Staff();
    
        } catch (Exception e) {
        e.printStackTrace();
        }
        
    }
     
     ///////////////////////////////////////////////
     
     public void loadCombo(){
     
         try {
    List<Object[]> lst= sService.getDao().loadComboItems();
      Set<String> secRoles=new TreeSet<String>();
      Set<String> initialz=new TreeSet<String>();
             
    for (Object[] obj : lst) {
         
       String secRole=(String) obj[0];
       String initial=(String) obj[1];
        
       secRoles.add(secRole);
       
       initialz.add(initial);
             
    }
    UIEty.loadcombo(tInitial, initialz);
    UIEty.loadcombo(tSecRole, secRoles);
                  
             
             
             
         } catch (Exception e) {
         e.printStackTrace();}
     
     }
     
    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
  public void keyListeners(){
      try {
          
      } catch (Exception e) {
     e.printStackTrace();
      }
  }
  
  ///////////////////////////////////////////////
    public StaffDetailUI() {
        super();
    }
    
////////////////////////////////
   public Staff uiToEntity(Staff s){
   
       try {
    s.setId(EntityService.getEntityService().getKey());             
    s.setCode(UIEty.tcToStr(tCode));
    s.setName(UIEty.tcToStr(tName));
    s.setGender(tGender.getSelectedItem()==null?"":tGender.getSelectedItem().toString());                  
   s.setInitial(UIEty.cmbtostr(tInitial));
   s.setReigion(tReligion.getSelectedItem()==null?"":tReligion.getSelectedItem().toString());             
   s.setSecurityRole(UIEty.cmbtostr(tSecRole));
     
   s.setShopName("Not yet implemented..");
   s.setUsername(UIEty.tcToStr(tUsername));
   s.setPassword(UIEty.tcToStr(tPassword));
   s.setRetypePasswod(UIEty.tcToStr(tRetypePassword));
   
   s.setDob(tDob.getDate());
   s.setJoinedDate(tWorkStarted.getDate());
   
   s.setAddress1(UIEty.tcToStr(tAddress1));
   s.setAddress2(UIEty.tcToStr(tAddress2));
   s.setCity(UIEty.tcToStr(tCity));
   
   s.setPhone(UIEty.tcToStr(tPhone));
   s.setMobile(UIEty.tcToStr(tMobile));
   s.setEmail(UIEty.tcToStr(tEmail));
   
       } catch (Exception e) {
     e.printStackTrace();  }
   return s;
   }
    
///////////////////////////////////////////////////////
///////////////////////////////////////////////////////
    
///////////////////////////////////////////////////////

///////////////////////////////////////////////////////
      public void entity2Ui(Staff s)throws Exception{
      try {
 UIEty.objToUi(tCode,s.getId());//    s.setCode(uiEty.tcToStr(tId));
UIEty.objToUi(tName,s.getName());//    s.setName(uiEty.tcToStr(tName));
tGender.setSelectedItem(s.getGender());//    s.setGender(tGender.getSelectedItem()==null?"":tGender.getSelectedItem().toString());                  
UIEty.objToUi(tInitial,s.getInitial());//   s.setInitial(uiEty.cmbtostr(tInitial));
tReligion.setSelectedItem(s.getReigion());//   s.setReigion(tReligion.getSelectedItem()==null?"":tReligion.getSelectedItem().toString());             
UIEty.objToUi(tSecRole,s.getSecurityRole());//   s.setSecurityRole(uiEty.cmbtostr(tSecRole));
//     
 //   s.setShopName("Not yet implemented..");
UIEty.objToUi(tUsername,s.getUsername());//   s.setUsername(uiEty.tcToStr(tUsername));
UIEty.objToUi(tPassword,s.getPassword());//   s.setPassword(uiEty.tcToStr(tPassword));
UIEty.objToUi(tRetypePassword,s.getRetypePasswod());//   s.setRetypePasswod(uiEty.tcToStr(tRetypePassword));
//   
//   s.setDob(tDob.getDate());
//   s.setJoinedDate(tWorkStarted.getDate());
//   
UIEty.objToUi(tAddress1,s.getAddress1());//   s.setAddress1(uiEty.tcToStr(tAddress1));
UIEty.objToUi(tAddress2,s.getAddress2());//   s.setAddress2(uiEty.tcToStr(tAddress2));
UIEty.objToUi(tCity,s.getCity());//   s.setCity(uiEty.tcToStr(tCity));
//   
UIEty.objToUi(tPhone,s.getPhone());//   s.setPhone(uiEty.tcToStr(tPhone));
UIEty.objToUi(tMobile,s.getMobile());//   s.setMobile(uiEty.tcToStr(tMobile));
UIEty.objToUi(tEmail,s.getEmail());//   s.setEmail(uiEty.tcToStr(tEmail));
       } catch (Exception e) {
     e.printStackTrace();  }
    
    
     }
 
   ///////////////////////////////////////////////////
   public void clear(){
       try {
           entity2Ui(new Staff());
       } catch (Exception e) {
    e.printStackTrace();   }
   }
   //////////////////////////////////////////
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cLabel6 = new org.components.controls.CLabel();
        cLabel7 = new org.components.controls.CLabel();
        cLabel2 = new org.components.controls.CLabel();
        cLabel8 = new org.components.controls.CLabel();
        cLabel4 = new org.components.controls.CLabel();
        tName = new org.components.controls.CTextField();
        tWorkStarted = new org.components.controls.CDatePicker();
        cLabel5 = new org.components.controls.CLabel();
        cClose = new org.components.controls.CButton();
        cSave = new org.components.controls.CButton();
        cClear = new org.components.controls.CButton();
        cDelete = new org.components.controls.CButton();
        tAddress1 = new org.components.controls.CTextField();
        tAddress2 = new org.components.controls.CTextField();
        tCity = new org.components.controls.CTextField();
        tPhone = new org.components.controls.CTextField();
        cLabel9 = new org.components.controls.CLabel();
        tMobile = new org.components.controls.CTextField();
        cLabel10 = new org.components.controls.CLabel();
        tEmail = new org.components.controls.CTextField();
        cLabel12 = new org.components.controls.CLabel();
        tCode = new org.components.controls.CTextField();
        tReligion = new org.components.controls.CComboBox();
        cLabel16 = new org.components.controls.CLabel();
        cLabel17 = new org.components.controls.CLabel();
        tSecRole = new org.components.controls.CComboBox();
        cLabel13 = new org.components.controls.CLabel();
        tGender = new org.components.controls.CComboBox();
        cLabel14 = new org.components.controls.CLabel();
        tInitial = new org.components.controls.CComboBox();
        cPanel1 = new org.components.containers.CPanel();
        cLabel11 = new org.components.controls.CLabel();
        tUsername = new org.components.controls.CTextField();
        cLabel15 = new org.components.controls.CLabel();
        cLabel18 = new org.components.controls.CLabel();
        tRetypePassword = new javax.swing.JPasswordField();
        tPassword = new javax.swing.JPasswordField();
        tDob = new org.components.parent.controls.PDatePicker();

        setLayout(null);

        cLabel6.setText("Address");
        add(cLabel6);
        cLabel6.setBounds(390, 100, 63, 25);

        cLabel7.setText("City");
        add(cLabel7);
        cLabel7.setBounds(390, 180, 31, 25);

        cLabel2.setText("Staff Code");
        add(cLabel2);
        cLabel2.setBounds(10, 10, 90, 25);

        cLabel8.setText("Phone");
        add(cLabel8);
        cLabel8.setBounds(390, 220, 48, 25);

        cLabel4.setText("Staff  Name ");
        add(cLabel4);
        cLabel4.setBounds(130, 10, 119, 25);

        tName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tNameActionPerformed(evt);
            }
        });
        add(tName);
        tName.setBounds(90, 50, 250, 25);
        add(tWorkStarted);
        tWorkStarted.setBounds(610, 50, 130, 22);

        cLabel5.setText("Work Started");
        add(cLabel5);
        cLabel5.setBounds(610, 10, 130, 25);

        cClose.setText("Go to List");
        cClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cCloseActionPerformed(evt);
            }
        });
        add(cClose);
        cClose.setBounds(660, 340, 80, 30);

        cSave.setText("Save");
        cSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cSaveActionPerformed(evt);
            }
        });
        add(cSave);
        cSave.setBounds(440, 340, 60, 30);

        cClear.setText("Clear");
        cClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cClearActionPerformed(evt);
            }
        });
        add(cClear);
        cClear.setBounds(510, 340, 60, 30);

        cDelete.setText("Delete");
        cDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cDeleteActionPerformed(evt);
            }
        });
        add(cDelete);
        cDelete.setBounds(580, 340, 70, 30);

        tAddress1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tAddress1ActionPerformed(evt);
            }
        });
        add(tAddress1);
        tAddress1.setBounds(460, 100, 280, 25);

        tAddress2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tAddress2ActionPerformed(evt);
            }
        });
        add(tAddress2);
        tAddress2.setBounds(460, 130, 280, 25);

        tCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCityActionPerformed(evt);
            }
        });
        add(tCity);
        tCity.setBounds(460, 180, 280, 25);

        tPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tPhoneActionPerformed(evt);
            }
        });
        add(tPhone);
        tPhone.setBounds(460, 220, 280, 25);

        cLabel9.setText("Mobile");
        add(cLabel9);
        cLabel9.setBounds(390, 260, 48, 25);

        tMobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tMobileActionPerformed(evt);
            }
        });
        add(tMobile);
        tMobile.setBounds(460, 260, 280, 25);

        cLabel10.setText("Email");
        add(cLabel10);
        cLabel10.setBounds(390, 300, 48, 25);

        tEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tEmailActionPerformed(evt);
            }
        });
        add(tEmail);
        tEmail.setBounds(460, 300, 280, 25);

        cLabel12.setText("Religion");
        add(cLabel12);
        cLabel12.setBounds(10, 170, 70, 25);

        tCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCodeActionPerformed(evt);
            }
        });
        add(tCode);
        tCode.setBounds(10, 50, 70, 25);

        tReligion.setEditable(true);
        tReligion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...", "Buddhist", "Hindu", "Muslims", "Christians" }));
        add(tReligion);
        tReligion.setBounds(110, 170, 240, 23);

        cLabel16.setText("DOB");
        add(cLabel16);
        cLabel16.setBounds(460, 10, 100, 25);

        cLabel17.setText("Security Role");
        add(cLabel17);
        cLabel17.setBounds(10, 210, 100, 25);

        tSecRole.setEditable(true);
        add(tSecRole);
        tSecRole.setBounds(110, 210, 240, 23);

        cLabel13.setText("Gender");
        add(cLabel13);
        cLabel13.setBounds(10, 90, 70, 25);

        tGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { ".....", "Male", "Female" }));
        add(tGender);
        tGender.setBounds(110, 90, 110, 23);

        cLabel14.setText("Initial");
        add(cLabel14);
        cLabel14.setBounds(10, 130, 70, 25);

        tInitial.setEditable(true);
        add(tInitial);
        tInitial.setBounds(110, 130, 110, 23);

        cPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cPanel1.setLayout(null);

        cLabel11.setText("Retype Password");
        cPanel1.add(cLabel11);
        cLabel11.setBounds(10, 90, 130, 25);

        tUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tUsernameActionPerformed(evt);
            }
        });
        cPanel1.add(tUsername);
        tUsername.setBounds(160, 10, 160, 25);

        cLabel15.setText("Password");
        cPanel1.add(cLabel15);
        cLabel15.setBounds(10, 50, 70, 25);

        cLabel18.setText("Username");
        cPanel1.add(cLabel18);
        cLabel18.setBounds(10, 10, 90, 25);

        tRetypePassword.setText("jPasswordField1");
        cPanel1.add(tRetypePassword);
        tRetypePassword.setBounds(160, 80, 160, 30);

        tPassword.setText("jPasswordField1");
        cPanel1.add(tPassword);
        tPassword.setBounds(160, 40, 160, 30);

        add(cPanel1);
        cPanel1.setBounds(10, 250, 340, 120);
        add(tDob);
        tDob.setBounds(460, 50, 140, 22);
    }// </editor-fold>//GEN-END:initComponents

    private void tNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNameActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_tNameActionPerformed

    private void cSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cSaveActionPerformed
           try {
     if(UIEty.tcToStr(tCode)==null || UIEty.tcToStr(tCode).equals("")){
           MessageBoxes.wrnmsg(null,"Please Type Staff Code","Empty Staff Code");                 
                return;
            }  
     if(UIEty.cmbtostr(tSecRole)==null || UIEty.cmbtostr(tSecRole).equals("")){
           MessageBoxes.wrnmsg(null,"Please Type Security Role","Empty");                 
                return;
            }  
     
       if(UIEty.tcToStr(tUsername)==null || UIEty.tcToStr(tUsername).equals("")){
           MessageBoxes.wrnmsg(null,"Please Type Username ","Empty");                 
                return;
            }
       
        if(UIEty.tcToStr(tPassword)==null ||UIEty.tcToStr(tRetypePassword)==null ||
          !UIEty.tcToStr(tPassword).equals(UIEty.tcToStr(tRetypePassword))
            ){
        
      MessageBoxes.errormsg(null, "Passwords Does not Match Or ", "Wrong ");            
   
    }
     
    //check username  and pwd...
    Staff usernameChk=sService.getDao().findStaffByUsername(UIEty.tcToStr(tUsername),"");
    
    Staff exist=sService.getDao().findStaffByCode(UIEty.tcToStr(tCode));
   
    if(usernameChk!=null){
  if(exist==null){
    MessageBoxes.errormsg(null, "Username Exist", "Exist");            
  return;
  }else{
  
  if(!exist.getUsername().equals(usernameChk.getUsername())){
   MessageBoxes.errormsg(null, "Username Exist", "Exist");            
  return;
  }
  
  }
    } 
     
    
     Staff s=uiToEntity(new Staff());
     
     
     if(exist==null){
       sService.getDao().save(s);            
     }else{
         String[] ObjButtons = { "Yes", "No" };
  int PromptResult = JOptionPane.showOptionDialog(null, "Staff Exist Do You Want to Update it?", getTabName(), -1, 2, null, ObjButtons, ObjButtons[1]);
     if(PromptResult==0){
         s.setId(exist.getId());
        sService.getDao().update(s);         
     }else{
     return;
     }
     }
     clear();
     
        } catch (Exception e) {
        
        MessageBoxes.errormsg(null,e.getMessage(), "Error");
        }
    }//GEN-LAST:event_cSaveActionPerformed

    private void tAddress1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tAddress1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tAddress1ActionPerformed

    private void tAddress2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tAddress2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tAddress2ActionPerformed

    private void tCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCityActionPerformed

    private void tPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tPhoneActionPerformed

    private void tMobileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tMobileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tMobileActionPerformed

    private void tEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tEmailActionPerformed

    private void tCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCodeActionPerformed

    private void tUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tUsernameActionPerformed

    private void cClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cClearActionPerformed
        try {
            clear();
        } catch (Exception e) {
        
        MessageBoxes.errormsg(null,e.getMessage(), "Error");
        }
    }//GEN-LAST:event_cClearActionPerformed

    private void cDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cDeleteActionPerformed
          try {
      if(UIEty.tcToStr(tCode)==null || UIEty.tcToStr(tCode).equals("")){
           MessageBoxes.wrnmsg(null,"Please Type Staff Code","Empty Staff Code");                 
                return;
        }  
      
      Staff s=uiToEntity(new Staff());
      
       Staff exist=sService.getDao().findStaffByCode(UIEty.tcToStr(tCode));
     
     if(exist!=null){
     sService.getDao().delete(exist);
     }else{
     MessageBoxes.warn(null,"No Staff Found.", getTabName());
     return;
     }   
              
    clear();
    } catch (Exception e) {        
        MessageBoxes.errormsg(null,e.getMessage(), "Error");
        }
    }//GEN-LAST:event_cDeleteActionPerformed

    private void cCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cCloseActionPerformed
        try {
            
        } catch (Exception e) {
        
        MessageBoxes.errormsg(null,e.getMessage(), "Error");
        }
    }//GEN-LAST:event_cCloseActionPerformed

    
      /**
     * @param cService the cService to set
     */
    public void setcService(StaffService cService) {
        this.sService = cService;
    }

    @Override
    public String getTabName() {
        return "Staff Form";
    }

    @Override
    public JPanel getJPanel() {
    
        return this;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cClear;
    private org.components.controls.CButton cClose;
    private org.components.controls.CButton cDelete;
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
    private org.components.controls.CLabel cLabel4;
    private org.components.controls.CLabel cLabel5;
    private org.components.controls.CLabel cLabel6;
    private org.components.controls.CLabel cLabel7;
    private org.components.controls.CLabel cLabel8;
    private org.components.controls.CLabel cLabel9;
    private org.components.containers.CPanel cPanel1;
    private org.components.controls.CButton cSave;
    private org.components.controls.CTextField tAddress1;
    private org.components.controls.CTextField tAddress2;
    private org.components.controls.CTextField tCity;
    private org.components.controls.CTextField tCode;
    private org.components.parent.controls.PDatePicker tDob;
    private org.components.controls.CTextField tEmail;
    private org.components.controls.CComboBox tGender;
    private org.components.controls.CComboBox tInitial;
    private org.components.controls.CTextField tMobile;
    private org.components.controls.CTextField tName;
    private javax.swing.JPasswordField tPassword;
    private org.components.controls.CTextField tPhone;
    private org.components.controls.CComboBox tReligion;
    private javax.swing.JPasswordField tRetypePassword;
    private org.components.controls.CComboBox tSecRole;
    private org.components.controls.CTextField tUsername;
    private org.components.controls.CDatePicker tWorkStarted;
    // End of variables declaration//GEN-END:variables
}
