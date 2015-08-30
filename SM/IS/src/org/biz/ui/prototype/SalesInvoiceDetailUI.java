/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.ui.prototype;

import org.biz.invoicesystem.ui.list.master.ItemController;
import org.biz.app.ui.util.UIEty;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.components.windows.DetailPanel;

/**
 *
 * @author user
 */
public class SalesInvoiceDetailUI extends DetailPanel<SalesInvoice> implements LineItemAdder{

        
//    private CrudControllex controller ;
    private SalesInvoiceControler salesController;
    private SalesInvoiceLineItemController salesInvoiceController;
    private ItemController itemController;
    
    
    /**
     * Creates new form SalesInvoiceUI
     */
    public SalesInvoiceDetailUI() {

    }

    @Override
    public void init() {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        initComponents();
        
//        initUI();
        gridDataContainerUI1.setItemAdder(this);
        gridDataContainerUI1.addNewToTable();
    }
    
    

    public void setDataToUI(SalesInvoice object){
        busObject = (SalesInvoice) object;
        cCode.setText(object.getCode());
        cInvNo.setText(object.getInvNo());
        super.setDataToUI(object);
    }
 

    public SalesInvoice uiToData() {

        if (busObject == null) {
            busObject = new SalesInvoice();
        }
        busObject.setCode(UIEty.tcToStr(cCode));
        busObject.setInvNo(UIEty.tcToStr(cInvNo));        
        
        busObject.setInvNo(cInvNo.getText());
        return busObject;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gridDataContainerUI1 = new org.biz.ui.prototype.GridDataContainerUI();
        jLabel1 = new javax.swing.JLabel();
        cCode = new org.components.controls.CTextField();
        cInvNo = new org.components.controls.CTextField();

        jLabel1.setText("Invoice Detail");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gridDataContainerUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cCode, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cInvNo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(413, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(428, 428, 428))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(gridDataContainerUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1)
                        .addGap(39, 39, 39)
                        .addComponent(cCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cInvNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CTextField cCode;
    private org.components.controls.CTextField cInvNo;
    private org.biz.ui.prototype.GridDataContainerUI gridDataContainerUI1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public GridDataLineDetailUI getLineUI() {
        SalesInvoiceLineDetailUI sliDetail= new SalesInvoiceLineDetailUI();
        return sliDetail;        
    }
}
