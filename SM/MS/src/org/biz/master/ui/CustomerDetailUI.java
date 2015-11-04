package org.biz.master.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JPanel;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.app.ui.util.UIEty;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.service.master.CustomerService;
import org.components.windows.DetailPanel;

public class CustomerDetailUI extends DetailPanel<Customer> {

    private CustomerService cService;
    // List<Customer> customers;
    private Customer selectedCus;

    public CustomerDetailUI(){
    super();
    }
    
    @Override
    public void init() {
    initComponents();
    super.init();
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
                if (type != null) 
                    types.add(type);                             

                String title = (String) ss[0];
                if (title != null) 
                    titles.add(title);
            }
            UIEty.loadcombo(tCusType, types);
            UIEty.loadcombo(tCusTitle, titles);
//     
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public Customer uiToEntity(Customer c) throws Exception {
        try {
//            c.setId(EntityService.getEntityService().getKey(""));

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
        
    }

    
    public void setDataToUI(Customer obj) {
        this.selectedCus = obj;
        try {
            UIEty.objToUi(tCusCode, obj.getCode());
            UIEty.objToUi(tCusTitle, obj.getTitle());
            UIEty.objToUi(tCusName, obj.getCustomerName());
            tCusDOB.setDate(obj.getDob());
            UIEty.objToUi(tCusCompany, obj.getCompanyName());
            UIEty.objToUi(tCusReligion, obj.getReligion());
            UIEty.objToUi(tCusType, obj.getType());
            UIEty.objToUi(tCusDiscount, obj.getDiscount());
            UIEty.objToUi(tCusCreditLimit, obj.getCreditLimit());
            UIEty.objToUi(tCusSalesRep, obj.getSalesRep());
            UIEty.objToUi(tCusLoyalty, obj.getLoyaltyCardNo());
            UIEty.objToUi(tCusNIC, obj.getNicno());
            UIEty.objToUi(tCusAdd1, obj.getAddress());
            UIEty.objToUi(tCusAdd2, obj.getAddress2());
            UIEty.objToUi(tCusCity, obj.getCity());
            UIEty.objToUi(tCusPhone, obj.getPicLocation());
            UIEty.objToUi(tCusMobile, obj.getMobile());
            UIEty.objToUi(tCusEmail, obj.getEmail());


        }
        catch (Exception e) {
            throw e;
        }
    }

    public Customer uiToData(){
            Customer c=new Customer();
            try {
//            c.setId(EntityService.getEntityService().getKey(""));

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
    
    public void clearForm() {
        try {
            entity2Ui(new Customer());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void  clear(){
        setDataToUI(new Customer());
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

        cLabel6.setText("Address");

        tCusType.setEditable(true);

        cLabel7.setText("City");

        cLabel2.setText("Custmer Code");

        cLabel8.setText("Phone");

        cLabel1.setText("Discount (%)");

        cLabel4.setText("Custmer Name ");

        tCusName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusNameActionPerformed(evt);
            }
        });

        tCusDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusDiscountActionPerformed(evt);
            }
        });

        cLabel3.setText("Title");

        tCusTitle.setEditable(true);

        cLabel5.setText("DOB");

        tCusAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusAdd1ActionPerformed(evt);
            }
        });

        tCusAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusAdd2ActionPerformed(evt);
            }
        });

        tCusCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusCityActionPerformed(evt);
            }
        });

        tCusPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusPhoneActionPerformed(evt);
            }
        });

        cLabel9.setText("Mobile");

        tCusMobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusMobileActionPerformed(evt);
            }
        });

        cLabel10.setText("Email");

        tCusEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusEmailActionPerformed(evt);
            }
        });

        cLabel11.setText("S.Rep");

        cLabel12.setText("Company Name");

        tCusSalesRep.setEditable(true);

        tCusCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusCompanyActionPerformed(evt);
            }
        });

        cLabel13.setText("Type");

        tCusReligion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...", "Buddhist", "Hindu", "Muslim", "Christian" }));

        cLabel14.setText("Loyalty Card");

        tCusLoyalty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusLoyaltyActionPerformed(evt);
            }
        });

        cLabel15.setText("Credit Limit");

        tCusCreditLimit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusCreditLimitActionPerformed(evt);
            }
        });

        tCusCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusCodeActionPerformed(evt);
            }
        });

        cLabel16.setText("Religion");

        cLabel17.setText("NIC");

        tCusNIC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCusNICActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(cLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(cLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(161, 161, 161)
                        .addComponent(cLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tCusCode, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(tCusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(tCusName, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(tCusDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tCusCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(cLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(tCusNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(tCusReligion, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(cLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(tCusAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(tCusType, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160)
                        .addComponent(tCusAdd2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(tCusDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(cLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(tCusCity, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(400, 400, 400)
                        .addComponent(cLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(tCusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tCusCreditLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tCusSalesRep, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tCusLoyalty, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tCusPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tCusMobile, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tCusCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCusName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCusDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCusCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCusNIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCusReligion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCusAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCusType, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCusAdd2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCusDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCusCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(cLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(cLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tCusCreditLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tCusSalesRep, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(tCusLoyalty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(cLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tCusPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(tCusMobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tCusNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCusNameActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_tCusNameActionPerformed

    private void tCusDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCusDiscountActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_tCusDiscountActionPerformed

   
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
     
    public void setService(Service service) {
        this.service = service;
         cService= (CustomerService) service;
                try {

//            cService = new CustomerService();
            //customers=new ArrayList<Customer>();
            // customer=new Customer();

            loadComboItems();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        init(); 
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
