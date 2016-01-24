/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.master.ui;

import org.biz.MSResources;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.ItemAttribute;
import org.biz.invoicesystem.entity.master.SKU;
import org.biz.invoicesystem.ui.list.master.ItemController;
import org.biz.invoicesystem.ui.list.master.ItemLV;
import org.biz.invoicesystem.ui.list.master.SKUController;
import org.components.windows.DetailPanel;
import org.components.windows.UIController;

/**
 *
 * @author jawa
 */
public class SKUDetailUI extends DetailPanel<SKU> {

    
    private SKUController ic;
    /**
     * Creates new form SKUDetailUI
     */
    public SKUDetailUI() {
        super();
    }

    @Override
    public void init() {
        initComponents();

        super.init();     
        
        ItemController itc = new ItemController();
        titem.setListViewQueryManger(itc.getPopupQueryManger(), new ItemLV());
        titem.setSelectedProperty("code");
        
        
        addToFocus(titem);
        addToFocus(tSKUSearchKey);
        addToFocus(tItemAttributes);
        addToFocus(tAttributeValue);
        
        
    }

    @Override
    public void setController(UIController controller) {
        super.setController(controller); //To change body of generated methods, choose Tools | Templates.
        ic = (SKUController)controller;
    }

    @Override
    public void setDataToUI(SKU obj) {
        busObject = obj;
        titem.setSelectedObject(obj.getItem());
        tSKUSearchKey.setText(obj.getCode());
        tskuexpl.setText(obj.getExplainningSearchString());
        super.setDataToUI(obj);
    }

    @Override
    public SKU uiToData() {
        if(busObject ==null){
            busObject = new SKU();
        }
        busObject.setItem((Item)titem.getSelectedObject());
        busObject.setCode(tSKUSearchKey.getText());
        busObject.setExplainningSearchString(tskuexpl.getText());
        return busObject;
    }

    @Override
    public void clear() {
        super.clear(); 
        tAttributeValue.clear();
        tSKUSearchKey.clear();
        titem.clear();
        tblItemAttribute.clear();
//        tblItemAttribute.clear();
        setDataToUI(new SKU());
        
    }
    
    
   
    
    
    private void addUOM() {
        
        ItemAttribute uom = new ItemAttribute();
        SKU item = uiToData();
        item.addSKUorUpdate(uom);

        //prime  unit
        //logic changes type is defined 
        if (true/*!item.isUOMValid(uom)*/) {
            MessageBoxes.wrnmsg(this, MSResources._10001, MSResources._1000);
//            focusManager.setTemCom(tunitsymbot);
            return;
        }

        tblItemAttribute.addNewOrModifySelectedRow(uom);
//        focusManager.setTemCom(tunitsymbot);

    }
 
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cComboBox2 = new org.components.controls.CComboBox();
        tItemAttributes = new org.components.controls.CComboBox();
        cButton1 = new org.components.controls.CButton();
        tAttributeValue = new org.components.controls.CComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItemAttribute = new org.components.controls.CTableMaster();
        cButton2 = new org.components.controls.CButton();
        cLabel1 = new org.components.controls.CLabel();
        cLabel2 = new org.components.controls.CLabel();
        tSKUSearchKey = new org.components.controls.CTextField();
        cLabel3 = new org.components.controls.CLabel();
        cLabel4 = new org.components.controls.CLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        cTableMaster2 = new org.components.controls.CTableMaster();
        titem = new com.components.custom.TextFieldWithPopUP();
        jScrollPane3 = new javax.swing.JScrollPane();
        tskuexpl = new org.components.controls.CTextArea();

        cButton1.setText("+");
        cButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton1ActionPerformed(evt);
            }
        });

        tblItemAttribute.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Attribute", "Value"
            }
        ));
        jScrollPane1.setViewportView(tblItemAttribute);

        cButton2.setText("-");

        cLabel1.setText("Item");

        cLabel2.setText("Item Attributes");

        cLabel3.setText("Attribute value");

        cLabel4.setText("Unique Code");

        cTableMaster2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Barcode"
            }
        ));
        jScrollPane2.setViewportView(cTableMaster2);

        tskuexpl.setColumns(20);
        tskuexpl.setRows(5);
        jScrollPane3.setViewportView(tskuexpl);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(cLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tItemAttributes, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tAttributeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(titem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tSKUSearchKey, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(cLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(133, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(titem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tItemAttributes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tAttributeValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tSKUSearchKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton1ActionPerformed

        tblItemAttribute.getModelCollection();
        
        
        
        
    }//GEN-LAST:event_cButton1ActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CButton cButton2;
    private org.components.controls.CComboBox cComboBox2;
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel3;
    private org.components.controls.CLabel cLabel4;
    private org.components.controls.CTableMaster cTableMaster2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private org.components.controls.CComboBox tAttributeValue;
    private org.components.controls.CComboBox tItemAttributes;
    private org.components.controls.CTextField tSKUSearchKey;
    private org.components.controls.CTableMaster tblItemAttribute;
    private com.components.custom.TextFieldWithPopUP titem;
    private org.components.controls.CTextArea tskuexpl;
    // End of variables declaration//GEN-END:variables
}
