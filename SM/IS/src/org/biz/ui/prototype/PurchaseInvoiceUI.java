/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.ui.prototype;

import com.biz.system.ISProperties;
import com.components.custom.ActionTask;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import org.biz.MS_Static;
import org.biz.app.ui.util.UIEty;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Staff;
import org.biz.invoicesystem.entity.master.Supplier;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.entity.master.WorkStation;
import org.biz.invoicesystem.entity.transactions.PurchaseInvoice;
import org.biz.invoicesystem.ui.list.master.SupplierController;
import org.components.util.ComponentFactory;
import org.components.windows.DetailPanel;
import org.components.windows.UIController;

/**
 *
 * @author LENOVO PC
 */
public class PurchaseInvoiceUI extends DetailPanel<PurchaseInvoice> {

    
    private PurchaseInvoiceControler invoiceControler;
    /**
     * Creates new form PurchaseInvoiceUI
     */
    public PurchaseInvoiceUI() {
//        initComponents();
    }

    
        @Override
    public void init() {
        initComponents();

        super.init();
        // controller can be set with external references so that later they can be used

        SupplierController cc = new SupplierController();
//        tpopCustomer.setListViewQueryManger(cc.getPopupQueryManger(), new SupplierListUi());
        tpopCustomer.setSelectedProperty("code");
        tpopCustomer.setActionTask(new ActionTask() {

            @Override
            public void actionCall() {
                Supplier customer = (Supplier) tpopCustomer.getSelectedObject();
                taddress.setText((customer).getAddress1());
                tname.setText(customer.getName());
            }

        });

        ttaxPercen.setDocAction(new ActionTask() {

            public void actionCall() {
                invoiceControler.onSalesInvoiceDataChanged();
            }
        });

        purchaseInvoiceLineDetailTableUI1.setTableContainer(tableContainer1);
        tableContainer1.addNewLineGotoNewLine();

//        purchaseInvoiceLineDetailTableUI1.setSalesInvoiceController(invoiceControler);

        ActionTask at = new ActionTask() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tpopCustomer.requestFocus();
            }
        };

        ComponentFactory.setKeyAction(this, at, KeyEvent.VK_F7, KeyEvent.CTRL_DOWN_MASK);

        addToFocus(purchaseInvoiceLineDetailTableUI1);
        addToFocus(tpopCustomer);
        addToFocus(ttaxPercen);
        addToFocus(ttaxAmount);
        addToFocus(tDiscountPer);
        addToFocus(tdiscAmount);
        addToFocus(tpayement);

    }

    public void setVisualDataToUI(PurchaseInvoice object) {
        busObject = (PurchaseInvoice) object;
//        cCode.setValueIfNotFocused(object.getCode());
        cInvNo.setValueIfNotFocused(object.getRefNo());
        ttaxAmount.setValueIfNotFocused(object.getTexAmount());
        ttaxPercen.setValueIfNotFocused(object.getTexPer());
        tDiscountPer.setValueIfNotFocused(object.getDiscountPer());
        tdiscAmount.setValueIfNotFocused(object.getDiscount());
        UIEty.objToUi(tlblTotal, object.getFinalTotal());
        tableContainer1.setCollection(object.getLineItems());
        

//        super.setDataToUI(object);
    }

    public void setDataToUI(PurchaseInvoice object) {
        setVisualDataToUI(object);
//        gridDataContainerUI1.setCollection(object.getLineItems());
//        super.setDataToUI(object);
    }

    public PurchaseInvoice uiToData() {
        busObject =super.uiToData();
       
//        busObject.setCode(UIEty.tcToStr(cCode));
        busObject.setRefNo(UIEty.tcToStr(cInvNo));
        busObject.setTexPer(UIEty.tcToDouble(ttaxPercen));
        busObject.setTexAmount(UIEty.tcToDouble(ttaxAmount));
        busObject.setDiscountPer(UIEty.tcToDouble(tDiscountPer));
        busObject.setDiscount(UIEty.tcToDouble(tdiscAmount));
        busObject.setLineItems(tableContainer1.getlistBusObject());
        busObject.setTotal();
//        busObject.calculateBalance();

        if(busObject.getShop()== null){
            Shop defaultShop = MS_Static.getDefaultShop();
            busObject.setShop(defaultShop);
        } 
        if(busObject.getWorkStation()== null){
            WorkStation defaultShop = MS_Static.getDefaultWorkStation();
            busObject.setWorkStation(defaultShop);
        }
        if(busObject.getWarehouse()== null){
            Warehouse defaultShop = MS_Static.getDefaultWareHouse();
            busObject.setWarehouse(defaultShop);
        }
        Staff loggedInStaff = MS_Static.getLoggedInStaff();
        if(loggedInStaff!=null){
        busObject.setStaff(loggedInStaff);
        }
        

        return busObject;
    }

    @Override
    public void setController(UIController controller) {
        super.setController(controller);
        invoiceControler = (PurchaseInvoiceControler) controller;
        busObject = invoiceControler.getCurrentBusObject();
        purchaseInvoiceLineDetailTableUI1.setSalesInvoiceController(controller);
    }

    @Override
    public void clear() {
        super.clear();
        tableContainer1.clearUI();

        Shop defaultShop = MS_Static.getDefaultShop();
        tlblShop.setValueOfText(" Shop "+defaultShop.getCode());
        Warehouse wh = MS_Static.getDefaultWareHouse();
        tlblWarehouse.setValueOfText(" WareHouse "+wh.getCode());
        Staff s = MS_Static.getLoggedInStaff();
        tlblstaff.setValueOfText(" Staff "+s.getCode());
        WorkStation ws = MS_Static.getDefaultWorkStation();
        tlblWorkStation.setValueOfText(" WorkStation "+ws.getCode());

    }

    public PurchaseInvoiceLineDetailTableUI getRowDetail() {
        if (ISProperties.getSalesInvoiceLineDetailUI() == 1) {
//            return new SalesInvoiceLineDetailTableUI2();
        }
        return new PurchaseInvoiceLineDetailTableUI();
    }

    private void codeGeneration() {
        busObject.setCode(cInvNo.getText());
        

    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableContainer1 = new org.biz.ui.prototype.TableContainer();
        purchaseInvoiceLineDetailTableUI1 = new org.biz.ui.prototype.PurchaseInvoiceLineDetailTableUI();
        cPanel1 = new org.components.containers.CPanel();
        tpayement = new org.components.controls.CTextField();
        cLabel9 = new org.components.controls.CLabel();
        cLabel10 = new org.components.controls.CLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        cLabel13 = new org.components.controls.CLabel();
        tdiscAmount = new org.components.controls.CCurrencyField();
        cLabel11 = new org.components.controls.CLabel();
        jLabel4 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        cLabel12 = new org.components.controls.CLabel();
        ttaxPercen = new org.components.controls.CCurrencyField();
        tDiscountPer = new org.components.controls.CCurrencyField();
        jRadioButton1 = new javax.swing.JRadioButton();
        ttaxAmount = new org.components.controls.CCurrencyField();
        tlblTotal = new javax.swing.JLabel();
        cLabel8 = new org.components.controls.CLabel();
        cPanel3 = new org.components.containers.CPanel();
        jLabel1 = new javax.swing.JLabel();
        cInvNo = new org.components.controls.CTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cDatePicker1 = new org.components.controls.CDatePicker();
        cLabel2 = new org.components.controls.CLabel();
        ccodegenerated = new org.components.controls.CLabel();
        cPanel2 = new org.components.containers.CPanel();
        tname = new org.components.controls.CTextField();
        tpopCustomer = new com.components.custom.TextFieldWithPopUP();
        cLabel1 = new org.components.controls.CLabel();
        cLabel5 = new org.components.controls.CLabel();
        cLabel6 = new org.components.controls.CLabel();
        cLabel7 = new org.components.controls.CLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taddress = new org.components.controls.CTextArea();
        tcustNew = new org.components.controls.CButton();
        tlblWarehouse = new org.components.controls.CLabel();
        tlblShop = new org.components.controls.CLabel();
        tlblWorkStation = new org.components.controls.CLabel();
        tlblstaff = new org.components.controls.CLabel();

        cLabel9.setText("Discount");

        cLabel10.setText("%");

        jRadioButton2.setText("Cheque");

        cLabel13.setText("Cash Returns:-");

        cLabel11.setText("%");

        jLabel4.setText("Total :-");

        jRadioButton3.setText("Credit card");

        cLabel12.setText("Payments");

        jRadioButton1.setText("Cash");

        tlblTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cLabel8.setText("Sales Tax");

        javax.swing.GroupLayout cPanel1Layout = new javax.swing.GroupLayout(cPanel1);
        cPanel1.setLayout(cPanel1Layout);
        cPanel1Layout.setHorizontalGroup(
            cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(tlblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cPanel1Layout.createSequentialGroup()
                        .addGroup(cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addComponent(cLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                            .addGroup(cPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE))
                            .addGroup(cPanel1Layout.createSequentialGroup()
                                .addGroup(cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(cPanel1Layout.createSequentialGroup()
                                        .addGroup(cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(ttaxAmount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(tDiscountPer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(tdiscAmount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(cPanel1Layout.createSequentialGroup()
                                        .addComponent(ttaxPercen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(12, 12, 12)))
                                .addGroup(cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(cPanel1Layout.createSequentialGroup()
                                .addComponent(tpayement, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(cPanel1Layout.createSequentialGroup()
                        .addComponent(cLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)))
                .addGap(78, 78, 78))
        );
        cPanel1Layout.setVerticalGroup(
            cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ttaxPercen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ttaxAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tDiscountPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tdiscAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(tlblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tpayement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel1.setText("Invoice Detail");

        jLabel2.setText("Inv Ref No");

        jLabel3.setText("Code #");

        cLabel2.setText("Invoice");

        ccodegenerated.setText("");

        javax.swing.GroupLayout cPanel3Layout = new javax.swing.GroupLayout(cPanel3);
        cPanel3.setLayout(cPanel3Layout);
        cPanel3Layout.setHorizontalGroup(
            cPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cPanel3Layout.createSequentialGroup()
                        .addGroup(cPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cInvNo, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(cDatePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ccodegenerated, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        cPanel3Layout.setVerticalGroup(
            cPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cPanel3Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(cPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ccodegenerated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(cInvNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        cLabel1.setText("Supplier Info (F7)");

        cLabel5.setText("Code");

        cLabel6.setText("Address");

        cLabel7.setText("Name");

        taddress.setColumns(20);
        taddress.setRows(5);
        jScrollPane2.setViewportView(taddress);

        tcustNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tcustNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cPanel2Layout = new javax.swing.GroupLayout(cPanel2);
        cPanel2.setLayout(cPanel2Layout);
        cPanel2Layout.setHorizontalGroup(
            cPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(cPanel2Layout.createSequentialGroup()
                        .addGroup(cPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(cPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(cPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(tname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(cPanel2Layout.createSequentialGroup()
                        .addComponent(cLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tpopCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tcustNew, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        cPanel2Layout.setVerticalGroup(
            cPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tcustNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tpopCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(cPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(cLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tlblWarehouse.setText("WareHouse");

        tlblShop.setText("Shop");

        tlblWorkStation.setText("Workstation");

        tlblstaff.setText("Staff");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(purchaseInvoiceLineDetailTableUI1, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
                            .addComponent(tableContainer1, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(cPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(cPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tlblShop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tlblWarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tlblstaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tlblWorkStation, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(purchaseInvoiceLineDetailTableUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tableContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addComponent(cPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(tlblWarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tlblShop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tlblWorkStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tlblstaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tcustNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcustNewActionPerformed
        System.out.println("adding new customer");
    }//GEN-LAST:event_tcustNewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CDatePicker cDatePicker1;
    private org.components.controls.CTextField cInvNo;
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel10;
    private org.components.controls.CLabel cLabel11;
    private org.components.controls.CLabel cLabel12;
    private org.components.controls.CLabel cLabel13;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel5;
    private org.components.controls.CLabel cLabel6;
    private org.components.controls.CLabel cLabel7;
    private org.components.controls.CLabel cLabel8;
    private org.components.controls.CLabel cLabel9;
    private org.components.containers.CPanel cPanel1;
    private org.components.containers.CPanel cPanel2;
    private org.components.containers.CPanel cPanel3;
    private org.components.controls.CLabel ccodegenerated;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane2;
    private org.biz.ui.prototype.PurchaseInvoiceLineDetailTableUI purchaseInvoiceLineDetailTableUI1;
    private org.components.controls.CCurrencyField tDiscountPer;
    private org.biz.ui.prototype.TableContainer tableContainer1;
    private org.components.controls.CTextArea taddress;
    private org.components.controls.CButton tcustNew;
    private org.components.controls.CCurrencyField tdiscAmount;
    private org.components.controls.CLabel tlblShop;
    private javax.swing.JLabel tlblTotal;
    private org.components.controls.CLabel tlblWarehouse;
    private org.components.controls.CLabel tlblWorkStation;
    private org.components.controls.CLabel tlblstaff;
    private org.components.controls.CTextField tname;
    private org.components.controls.CTextField tpayement;
    private com.components.custom.TextFieldWithPopUP tpopCustomer;
    private org.components.controls.CCurrencyField ttaxAmount;
    private org.components.controls.CCurrencyField ttaxPercen;
    // End of variables declaration//GEN-END:variables
}
