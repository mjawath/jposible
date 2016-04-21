/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.ui.prototype;

import com.components.custom.ActionTask;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import org.biz.app.ui.util.UIEty;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.biz.invoicesystem.ui.list.master.CustomerController;
import org.biz.invoicesystem.ui.list.master.CustomerLV;
import org.components.parent.controls.PTableColumn;
import org.components.util.ComponentFactory;
import org.components.windows.DetailPanel;
import org.components.windows.UIController;

/**
 *
 * @author jawa
 */
public class SalesInvoiceTableDetail extends DetailPanel<SalesInvoice> {

    private SalesInvoiceControler controllerSalesInvoice;
    
    /**
     * Creates new form SalesDetailPanel
     */
    public SalesInvoiceTableDetail() {
        initComponents();
    }

    
    @Override
    public void init() {
        initComponents();

        super.init(); //To change body of generated methods, choose Tools | Templates.

        
 

        CustomerController cc = new CustomerController();
        tpopCustomer.setListViewQueryManger(cc.getPopupQueryManger(), new CustomerLV());
        tpopCustomer.setSelectedProperty("code");
        tpopCustomer.setActionTask(new ActionTask() {

            @Override
            public void actionCall() {
                Customer customer = (Customer) tpopCustomer.getSelectedObject();
                taddress.setText((customer).getAddress());
                tname.setText(customer.getCustomerName());
                System.out.println("finding customer " + customer.getId());

            }

        });

        ttaxPercen.setDocAction(new ActionTask() {

            public void actionCall() {
                controllerSalesInvoice.onSalesInvoiceDataChanged();
            }
        });
        
        

        List<PTableColumn> tblCols = new ArrayList();
        tblCols.add(new PTableColumn(Long.class, "ID"));
        tblCols.add(new PTableColumn(String.class, "Code"));
        tblCols.add(new PTableColumn(String.class, "Description"));
        tblCols.add(new PTableColumn(Double.class, "QTY"));
        tblCols.add(new PTableColumn(Double.class, "Price"));
        tblCols.add(new PTableColumn(Double.class, "LineAmount"));

        tableContainer1.initTable(SalesInvoiceLineItem.class, tblCols);
        salesInvoiceLineDetailTableUI1.setTableContainer(tableContainer1);        
        tableContainer1.addNewLineGotoNewLine();

        salesInvoiceLineDetailTableUI1.setSalesInvoiceController(controllerSalesInvoice);
        
        ActionTask at =new ActionTask(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("OOPs");
                tpopCustomer.requestFocus();
            }
        
        };
        ComponentFactory.setKeyAction(this, at, KeyEvent.VK_F8, KeyEvent.CTRL_DOWN_MASK);
        
        addToFocus(salesInvoiceLineDetailTableUI1);
        addToFocus(tpopCustomer);
        addToFocus(ttaxPercen);
        addToFocus(ttaxAmount);
        addToFocus(tDiscountPer);
        addToFocus(tdiscAmount);
        addToFocus(tpayement);

//        ComponentFactory.setKeyAction(cCode, delete, tabName);
//        sic.getPopupQueryManger();
    }
    
 
    public void setVisualDataToUI(SalesInvoice object) {
        busObject = (SalesInvoice) object;
        cCode.setValueIfNotFocused(object.getCode());
        cInvNo.setValueIfNotFocused(object.getInvNo());
        ttaxAmount.setValueIfNotFocused(object.getTexAmount());
        ttaxPercen.setValueIfNotFocused(object.getTexPer());
        tDiscountPer.setValueIfNotFocused(object.getDiscountPer());
        tdiscAmount.setValueIfNotFocused(object.getDiscount());
        UIEty.objToUi(tlblTotal, object.getTotal());

//        super.setDataToUI(object);
    }

    public void setDataToUI(SalesInvoice object) {
        setVisualDataToUI(object);
//        gridDataContainerUI1.setCollection(object.getLineItems());
//        super.setDataToUI(object);
    }

    public SalesInvoice uiToData() {

        //TODO- 
//        if (busObject == null) {
        SalesInvoice busObject = new SalesInvoice();
//        }
        busObject.setCode(UIEty.tcToStr(cCode));
        busObject.setInvNo(UIEty.tcToStr(cInvNo));
        busObject.setTexPer(UIEty.tcToDouble(ttaxPercen));
        busObject.setTexAmount(UIEty.tcToDouble(ttaxAmount));
        busObject.setDiscountPer(UIEty.tcToDouble(tDiscountPer));
        busObject.setDiscount(UIEty.tcToDouble(tdiscAmount));
        busObject.setLineItems(tableContainer1.getlistBusObject());
//        busObject.setTotal();
//        busObject.calculateBalance();
        return busObject;
    }
    
    @Override
    public void setController(UIController controller) {
        super.setController(controller);
        controllerSalesInvoice = (SalesInvoiceControler) controller;
        busObject = controllerSalesInvoice.getCurrentBusObject();
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        salesInvoiceLineDetailTableUI1 = new org.biz.ui.prototype.SalesInvoiceLineDetailTableUI();
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
        cPanel3 = new org.components.containers.CPanel();
        jLabel1 = new javax.swing.JLabel();
        cCode = new org.components.controls.CTextField();
        cInvNo = new org.components.controls.CTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cDatePicker1 = new org.components.controls.CDatePicker();
        tableContainer1 = new org.biz.ui.prototype.TableContainer();

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
                .addGroup(cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cPanel1Layout.createSequentialGroup()
                        .addComponent(cLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ttaxPercen, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cPanel1Layout.createSequentialGroup()
                        .addComponent(cLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cPanel1Layout.createSequentialGroup()
                                .addComponent(tDiscountPer, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(cLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ttaxAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tdiscAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(cPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cPanel1Layout.createSequentialGroup()
                                .addComponent(cLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tpayement, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(cPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(tlblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(cPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addGap(34, 34, 34)
                                .addComponent(jRadioButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton3))))
                    .addGroup(cPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(cLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
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
                .addGroup(cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tlblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(16, 16, 16)
                .addGroup(cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tpayement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3))
                .addGap(21, 21, 21)
                .addComponent(cLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        cLabel1.setText("Customer Info (F7)");

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
                    .addGroup(cPanel2Layout.createSequentialGroup()
                        .addGroup(cPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tcustNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(tpopCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(cPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(cLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Invoice Detail");

        jLabel2.setText("Inv No");

        jLabel3.setText("Code");

        javax.swing.GroupLayout cPanel3Layout = new javax.swing.GroupLayout(cPanel3);
        cPanel3.setLayout(cPanel3Layout);
        cPanel3Layout.setHorizontalGroup(
            cPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(cPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(cPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cInvNo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cCode, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        cPanel3Layout.setVerticalGroup(
            cPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(cInvNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tableContainer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(salesInvoiceLineDetailTableUI1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(cPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(cDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(salesInvoiceLineDetailTableUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tableContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tcustNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcustNewActionPerformed
        System.out.println("adding new customer");

    }//GEN-LAST:event_tcustNewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CTextField cCode;
    private org.components.controls.CDatePicker cDatePicker1;
    private org.components.controls.CTextField cInvNo;
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel10;
    private org.components.controls.CLabel cLabel11;
    private org.components.controls.CLabel cLabel12;
    private org.components.controls.CLabel cLabel13;
    private org.components.controls.CLabel cLabel5;
    private org.components.controls.CLabel cLabel6;
    private org.components.controls.CLabel cLabel7;
    private org.components.controls.CLabel cLabel8;
    private org.components.controls.CLabel cLabel9;
    private org.components.containers.CPanel cPanel1;
    private org.components.containers.CPanel cPanel2;
    private org.components.containers.CPanel cPanel3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane2;
    private org.biz.ui.prototype.SalesInvoiceLineDetailTableUI salesInvoiceLineDetailTableUI1;
    private org.components.controls.CCurrencyField tDiscountPer;
    private org.biz.ui.prototype.TableContainer tableContainer1;
    private org.components.controls.CTextArea taddress;
    private org.components.controls.CButton tcustNew;
    private org.components.controls.CCurrencyField tdiscAmount;
    private javax.swing.JLabel tlblTotal;
    private org.components.controls.CTextField tname;
    private org.components.controls.CTextField tpayement;
    private com.components.custom.TextFieldWithPopUP tpopCustomer;
    private org.components.controls.CCurrencyField ttaxAmount;
    private org.components.controls.CCurrencyField ttaxPercen;
    // End of variables declaration//GEN-END:variables
}
