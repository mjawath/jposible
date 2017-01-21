/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.ui.prototype;

import com.components.custom.ActionTask;
import java.awt.Component;
import org.biz.invoicesystem.entity.master.SKU;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.biz.invoicesystem.ui.list.master.SKUController;
import org.biz.invoicesystem.ui.list.master.SKULVUI;
import org.components.controls.CTextField;
import org.components.windows.UIController;

/**
 *
 * @author user
 */
public class SalesInvoiceLineDetailUI extends GridDataLineDetailUI{
        
    private SalesInvoiceControler sic;
    private SalesInvoiceLineItem salesLine = new SalesInvoiceLineItem();
    /**
     * Creates new form SalesInvoiceLineDetailUI
     */
    public SalesInvoiceLineDetailUI() {
        initComponents();
//        setFocusable(true);
        initUI();

    }
    
    public Object getLineObject(){
        return salesLine;
    }
        
  
    
    public void setSalesInvoiceController(UIController  con ){
        sic = (SalesInvoiceControler) con;        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cButton2 = new org.components.controls.CButton();
        cButton1 = new org.components.controls.CButton();
        tTotal = new org.components.controls.CTextField();
        tPrice = new org.components.controls.CTextField();
        tQty = new org.components.controls.CTextField();
        tItemSearchTextComp = new com.components.custom.TextFieldWithPopUP<>();

        setPreferredSize(new java.awt.Dimension(671, 47));

        cButton2.setText("X");
        cButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton2ActionPerformed(evt);
            }
        });

        cButton1.setText("+");
        cButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tItemSearchTextComp, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tQty, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(tPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tItemSearchTextComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cButton1, tPrice, tQty, tTotal});

    }// </editor-fold>//GEN-END:initComponents

    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton1ActionPerformed

        //line items to sales line item
        //set the line item to sales invoice

        //        salesController.addLine();

//        mycontainer.add(new NewJPanel());
//        silineItem.add(this);
        containerUI.addNewLineGotoNewLine();
        
        

        ///listener . addLineItem
    }//GEN-LAST:event_cButton1ActionPerformed

    private void cButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton2ActionPerformed
        containerUI.removLineItem(this);        
    }//GEN-LAST:event_cButton2ActionPerformed
    
    
    public SalesInvoiceLineItem UIToData(){       

        salesLine.setSku(tItemSearchTextComp.getSelectedObject());
        salesLine.setQty(tQty.getDoubleValue());
        salesLine.setPrice(tPrice.getDoubleValue());        
        return salesLine;
        
    }
    
    public void setDataToUI(Object data){
        SalesInvoiceLineItem li  = (SalesInvoiceLineItem)data;
        tItemSearchTextComp.setSelectedObject(li.getSku());
        tQty.setValueIfNotFocused(li.getQty());
        tPrice.setValueIfNotFocused(li.getPrice());        
        tTotal.setValueIfNotFocused(li.getLineAmount());        
    }
    
    public void initUI() {
//        ItemController ic = new ItemController();
        
        SKUController  sk = new SKUController();
        tItemSearchTextComp.setListViewQueryManger(sk.getPopupQueryManger(), new SKULVUI());
        tItemSearchTextComp.setSelectedProperty("code");
//        ic.initUI();
//        tItemSearchTextComp.setListViewQueryManger(ic.getPopupQueryManger(),new ItemLV());
//        tItemSearchTextComp.setSelectedProperty("code");

        
        tQty.setDocAction(new MyCalculator());
        
        tPrice.setDocAction(new MyCalculator());
        
        tQty.setActionTask(new ActionTask() {

            @Override
            public Component actionFired(Object source) {
                sic.onSalesInvoiceLineItemQTYChanged(SalesInvoiceLineDetailUI.this);
                return null;
            }
        });

        
        tPrice.setActionTask(new ActionTask() {

            @Override
            public Component actionFired(Object source) {
                sic.onSalesInvoiceLineItemPriceChanged(SalesInvoiceLineDetailUI.this);
                return containerUI.goToNextRowFirstComOrCreateNew();
            }
        });
        
        
    //        tblLineItems.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            
//            public void valueChanged(ListSelectionEvent e) {
//                
//                tblLineItems.getSelectedObject();
//                
////                setDataToUI(tQty);
//            }
//        });
        
        addToFocus(tItemSearchTextComp);        
        addToFocus(tQty);        
        addToFocus(tPrice);
        
        
        

    }

    
     private class MyCalculator extends ActionTask{
        
        public void actionCall() {            
             sic.onSalesInvoiceLineItemQTYChanged(SalesInvoiceLineDetailUI.this);
        }
        
        
        
    } 
     
    public void setDataToUI(SalesInvoiceLineItem obj) {        
        tItemSearchTextComp.setSelectedObject(obj.getSku());
        tPrice.setValueIfNotFocused(obj.getPrice());
        tQty.setValueIfNotFocused(obj.getQty());
        tTotal.setValueIfNotFocused(obj.getLineAmount());
        
    }

    public SalesInvoiceLineItem uiToData() {
        salesLine.setSku(tItemSearchTextComp.getSelectedObject());
        
        return salesLine;
    }
     

    @Override
    public boolean isLineItemValid() {
        
        
        if(salesLine.getSku()==null){            
            tItemSearchTextComp.requestFocus();
            return false;
        }       
        
        if (salesLine.getQty()== null || salesLine.getQty() <= 0) {
            tQty.requestFocus();
            return false;
        }
        
        if (salesLine.getPrice()== null || salesLine.getPrice()<= 0) {
            tPrice.requestFocus();
            return false;
        }
//        
//        if (salesLine.getPrice() == null) {
//            tPrice.requestFocus();
//            return false;
//        }
        
        return true; //To change body of generated methods, choose Tools | Templates.
    }
    

     public CTextField getQty(){
     return tQty;
     }
     
     public CTextField getPrice(){
     return tPrice;
     }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CButton cButton2;
    private com.components.custom.TextFieldWithPopUP<SKU> tItemSearchTextComp;
    private org.components.controls.CTextField tPrice;
    private org.components.controls.CTextField tQty;
    private org.components.controls.CTextField tTotal;
    // End of variables declaration//GEN-END:variables

    @Override
    public String toString() {
        return "sales";
    }

    

}
