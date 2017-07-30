/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.ui.prototype;

import com.components.custom.ActionTask;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.invoicesystem.entity.master.SKU;
import org.biz.invoicesystem.entity.transactions.PurchaseInvoiceLineItem;
import org.biz.invoicesystem.ui.list.master.SKUController;
import org.biz.invoicesystem.ui.list.master.SKULVUI;
import org.components.parent.controls.PTableColumn;
import org.components.windows.UIController;

/**
 *
 * @author user
 */
public class PurchaseInvoiceLineDetailTableUI extends TableRowDetail  {
        
    private PurchaseInvoiceControler sic;
    private PurchaseInvoiceLineItem salesLine = new PurchaseInvoiceLineItem();
    

    /**
     * Creates new form SalesInvoiceLineDetailUI
     */
    public PurchaseInvoiceLineDetailTableUI() {
        initComponents();
//        setFocusable(true);
        initUI();
      

    }
    
    public Object getLineObject(){
        return salesLine;
    }
        
  
    
    public void setSalesInvoiceController(UIController  con ){
        sic = (PurchaseInvoiceControler) con;        
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
        tItemSearchTextComp = new com.components.custom.TextFieldWithPopUP<>();
        cLabel1 = new org.components.controls.CLabel();
        cLabel2 = new org.components.controls.CLabel();
        tQty = new org.components.controls.CCurrencyField();
        tPrice = new org.components.controls.CCurrencyField();
        cLabel3 = new org.components.controls.CLabel();

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

        cLabel1.setText("Price");

        cLabel2.setText("Qty");

        cLabel3.setText("Item");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tItemSearchTextComp, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tQty, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(120, 120, 120))
            .addGroup(layout.createSequentialGroup()
                .addComponent(cLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(cLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tItemSearchTextComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tQty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton1ActionPerformed


        tableContainer.acceptLineChanges();
        
//        mycontainer.add(new NewJPanel());
//        silineItem.add(this);
//        containerUI.addNewLineGotoNewLine();
        
        

        ///listener . addLineItem
    }//GEN-LAST:event_cButton1ActionPerformed

    private void cButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton2ActionPerformed
        tableContainer.removeSelected();  
        
        
    }//GEN-LAST:event_cButton2ActionPerformed

    
    public void initUI() {
//        ItemController ic = new ItemController();
        super.initUI();
        
        tItemSearchTextComp.setName("Item");
        tPrice.setName("PriceText");
        tQty.setName("Qty");
        
        SKUController  sk = new SKUController();
        
        tItemSearchTextComp.setListViewQueryManger(sk.getPopupQueryManger(), new SKULVUI());
        tItemSearchTextComp.setSelectedProperty("code");        


        tPrice.setDocAction(new MyCalculator());
        tPrice.setAlwaysEnterEvent(true);
        
        tQty.setDocAction(new MyCalculator());
        tQty.setActionTask(new ActionTask() {

            @Override
            public Component actionFired(Object source) {
//                sic.onSalesInvoiceLineItemQTYChanged(PurchaseInvoiceLineDetailTableUI.this);
                return tPrice;
            }
        });

      
        tPrice.setActionTask(new ActionTask() {

            @Override
            public Component actionFired(Object source) {
                final JComponent onSalesInvoiceLineItemPriceChanged = sic.onSalesInvoiceLineItemPriceChanged(PurchaseInvoiceLineDetailTableUI.this);
                
                //create the line item 
                // is this line item valid ?
                //add to business object validate business object
                // then add it to table                
                System.out.println("price value " +tPrice.getText());
                return onSalesInvoiceLineItemPriceChanged;              
              
                
            }
        });

        

        
        tTotal.setEditable(false);
        addToFocus(tItemSearchTextComp);        
        addToFocus(tQty);        
        addToFocus(tPrice);       
        
        

    }

    
    public void setTableContainer(TableContainer tableContainer) {
        super.setTableContainer(tableContainer);
        List<PTableColumn> tblCols = new ArrayList();
        final PTableColumn ptc1 = new PTableColumn(Long.class, "ID");        
        tblCols.add(ptc1);        
        PTableColumn colcode = new PTableColumn(String.class, "Code");
        colcode.setMinWidth(150);
        tblCols.add(colcode);
        final PTableColumn coldesc = new PTableColumn(String.class, "Description");
        tblCols.add(coldesc);
        coldesc.setMinWidth(200);
        tblCols.add(new PTableColumn(Double.class, "QTY"));
        tblCols.add(new PTableColumn(Double.class, "Price"));
        tblCols.add(new PTableColumn(Double.class, "LineAmount"));
        tableContainer.initTable(PurchaseInvoiceLineItem.class, tblCols);


    }
    
    public PurchaseInvoiceLineItem panelToData() {
        PurchaseInvoiceLineItem sil= new PurchaseInvoiceLineItem();
        sil.setSku(tItemSearchTextComp.getSelectedObject());
        sil.setQty(tQty.getDoubleValue());
        sil.setPrice(tPrice.getDoubleValue());
        return sil;

    }

    public void setDataToPanel(Object data) {
        PurchaseInvoiceLineItem li = (PurchaseInvoiceLineItem) data;
        tItemSearchTextComp.setSelectedObject(li.getSku());
        
        tQty.setValue(li.getQty());
        tPrice.setValue(li.getPrice());
        tTotal.setValue(li.getLineAmount());
        salesLine = li;
    }
    
    public void setDataToPanelIFNotFocused(Object data) {
        PurchaseInvoiceLineItem li = (PurchaseInvoiceLineItem) data;
        tItemSearchTextComp.setSelectedObject(li.getSku());
        
        tQty.setValueIfNotFocused(li.getQty());
        tPrice.setValueIfNotFocused(li.getPrice());
        tTotal.setValueIfNotFocused(li.getLineAmount());
        salesLine = li;
    }
    
     private class MyCalculator extends ActionTask{
        
        public void actionCall() {            
            sic.onSalesInvoiceLineItemDocChanged(PurchaseInvoiceLineDetailTableUI.this);
        }
        
    } 
   
   
     
    public void onRemoveLineItem(Object selectedObject) {
        sic.onRemoveLineItem((PurchaseInvoiceLineItem)selectedObject);
    } 

    public Object[] getTableData(Object row) {
        PurchaseInvoiceLineItem sl = (PurchaseInvoiceLineItem) row;
        SKU selItem = sl.getSku();
        return new Object[]{sl,sl.getId(), selItem != null ? sl.getSku().getCode() : "", selItem != null ? selItem.getExplainningSearchString(): "",
            sl.getQty(), sl.getPrice(), sl.getLineAmount()};
    }
    
    public void clearLineUI() {        
        super.clearLineUI();
        tItemSearchTextComp.clear();
        tQty.clear();
        tPrice.clear();
        tTotal.clear();
        salesLine =new PurchaseInvoiceLineItem();
    
    }
    
    public void clearAll() {
        super.clearAll();
        sic.clearTableUI();
        
    }

    public JComponent getInValidComponent() {
      
        if (tItemSearchTextComp.getSelectedObject()== null) {
            //ErrorAlert
            MessageBoxes.errormsg(tItemSearchTextComp, "Item should be selected", "Item");
            return tItemSearchTextComp;            
        }
        
        if (!tQty.isValidPositiveDoubleValue()) {
            //ErrorAlert
            MessageBoxes.errormsg(tQty, "Quantity Should be more then zero", "Quantity");
            return tQty;
        }
        if(!tPrice.isValidPositiveDoubleValue() ){
            //ErrorAlert
            MessageBoxes.errormsg(tPrice, "Price Should be more then zero", "Price");
            return tPrice;
            
        }
        
     
        return null;        
    }
  
    boolean isValidToAccept() {

        PurchaseInvoiceLineItem sl = panelToData();
        if(sl.getSku()==null || sl.getLineAmount()<=0 ||                
                sl.getQty()<=0 || sl.getPrice()<=0){
            return false;
        }
        
        return true;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CButton cButton2;
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel3;
    private com.components.custom.TextFieldWithPopUP<SKU> tItemSearchTextComp;
    private org.components.controls.CCurrencyField tPrice;
    private org.components.controls.CCurrencyField tQty;
    private org.components.controls.CTextField tTotal;
    // End of variables declaration//GEN-END:variables

    

}