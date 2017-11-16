/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.erp.inventory.ui.detail;

import com.components.custom.ActionTask;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.entity.master.SKU;
import org.biz.invoicesystem.ui.list.master.SKUController;
import org.biz.invoicesystem.ui.list.master.SKULVUI;
import org.biz.ui.prototype.TableContainer;
import org.biz.ui.prototype.TableRowDetail;
import org.components.parent.controls.PTableColumn;
import org.components.windows.UIController;

/**
 *
 * @author jawa
 */
public class InventoryJournalLineItemUI extends TableRowDetail {

    private InventoryJournalController journalController;
    private InventoryJournalLine journalLine = new InventoryJournalLine();
    
    /**
     * Creates new form InventoryJournalLineItemUI
     */
    public InventoryJournalLineItemUI() {
        initComponents();
        initUI();
    }

    @Override
    public void initUI() {
         super.initUI();
        
        tItemSearchTextComp.setName("Item");
        SKUController  sk = new SKUController();
        
        tItemSearchTextComp.setListViewQueryManger(sk.getPopupQueryManger(), new SKULVUI());
        tItemSearchTextComp.setSelectedProperty("code");        
        tItemSearchTextComp.setActionActionTask(new ActionTask(){
             @Override
             public Object actionFired(Object com) {
                 return tQty;
             }             
            
        });        
                
        tQty.setName("Qty");
        tQty.setActionTask(new ActionTask(){            
            @Override
            public Component actionFired(Object source) {                
               return addLine();
             }          
        });        
        
        
    }
    
    private JComponent addLine(){
        return journalController.onSalesInvoiceLineItemPriceChanged(this);
    }


    public void setTableContainer(TableContainer tableContainer) {
        super.setTableContainer(tableContainer);
        List<PTableColumn> tblCols = new ArrayList();
        final PTableColumn ptc1 = new PTableColumn(String.class, "ID");
        tblCols.add(ptc1);
        PTableColumn colcode = new PTableColumn(String.class, "Code");
        colcode.setMinWidth(150);
        tblCols.add(colcode);
        tblCols.add(new PTableColumn(Double.class, "QTY"));
        tableContainer.initTable(InventoryJournalLine.class, tblCols);

    }
    public InventoryJournalLine panelToData() {
        InventoryJournalLine sil = new InventoryJournalLine();
        sil.setSku(tItemSearchTextComp.getSelectedObject());
        sil.setQty(tQty.getDoubleValue());
//        sil.setPrice(tPrice.getDoubleValue());
        return sil;

    }
    
    public void setDataToPanel(Object data) {
        InventoryJournalLine li = (InventoryJournalLine) data;
        tItemSearchTextComp.setSelectedObject(li.getSku());
        
        tQty.setValue(li.getQty());
//        tPrice.setValue(li.getPrice());
        journalLine = li;
    }
    
    public void setDataToPanelIFNotFocused(Object data) {
        InventoryJournalLine li = (InventoryJournalLine) data;
        tItemSearchTextComp.setSelectedObject(li.getSku());
        
        tQty.setValueIfNotFocused(li.getQty());
//        tPrice.setValueIfNotFocused(li.getPrice());
//        tTotal.setValueIfNotFocused(li.getLineAmount());
        journalLine = li;
    }
    
        public void onRemoveLineItem(Object selectedObject) {
//        journalController.onRemoveLineItem((SalesInvoiceLineItem)selectedObject);
    } 

    public Object[] getTableData(Object row) {
        InventoryJournalLine sl = (InventoryJournalLine) row;
        SKU selItem = sl.getSku();
        return new Object[]{ sl,sl.getId(),selItem != null ? sl.getSku().getCode() + selItem.getExplainningSearchString():"" ,
            sl.getQty()};
    }
    
    public void clearLineUI() {        
        super.clearLineUI();
        tItemSearchTextComp.clear();
        tQty.clear();
        journalLine =new InventoryJournalLine();
    
    }
    
    public void clearAll() {
        super.clearAll();
//        journalLine.clearTableUI();
        
    }
  

    public Object getLineObject() {
        return journalLine;
    }

    public void setSalesInvoiceController(UIController con) {
        journalController = (InventoryJournalController) con;
    }
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lineDetailPanel = new org.components.containers.CPanel();
        tQty = new org.components.controls.CTextField();
        cLabel5 = new org.components.controls.CLabel();
        cLabel6 = new org.components.controls.CLabel();
        cLabel7 = new org.components.controls.CLabel();
        tItemSearchTextComp = new com.components.custom.TextFieldWithPopUP<>();
        tuom = new org.components.controls.CComboBox();

        lineDetailPanel.setBackground(new java.awt.Color(153, 255, 0));
        lineDetailPanel.setLayout(null);
        lineDetailPanel.add(tQty);
        tQty.setBounds(430, 20, 130, 30);

        cLabel5.setText("Item");
        lineDetailPanel.add(cLabel5);
        cLabel5.setBounds(0, 0, 70, 25);

        cLabel6.setText("UOM");
        lineDetailPanel.add(cLabel6);
        cLabel6.setBounds(290, 0, 104, 20);

        cLabel7.setText("Qty");
        lineDetailPanel.add(cLabel7);
        cLabel7.setBounds(430, 0, 80, 20);
        lineDetailPanel.add(tItemSearchTextComp);
        tItemSearchTextComp.setBounds(10, 20, 270, 30);
        lineDetailPanel.add(tuom);
        tuom.setBounds(290, 20, 130, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lineDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lineDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cLabel5;
    private org.components.controls.CLabel cLabel6;
    private org.components.controls.CLabel cLabel7;
    private org.components.containers.CPanel lineDetailPanel;
    private com.components.custom.TextFieldWithPopUP<SKU> tItemSearchTextComp;
    private org.components.controls.CTextField tQty;
    private org.components.controls.CComboBox tuom;
    // End of variables declaration//GEN-END:variables
}
