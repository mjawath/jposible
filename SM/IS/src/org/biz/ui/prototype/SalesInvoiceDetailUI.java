/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.ui.prototype;

import javax.swing.JComponent;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.components.containers.CPanel;

/**
 *
 * @author user
 */
public class SalesInvoiceDetailUI                                                                                                                                                            extends CPanel implements LineItemAdder{

    
    private SalesInvoice salesInvoice;
//    private CrudControllex controller ;
    private SalesInvoiceControler salesController;
    private SalesInvoiceLineItemController salesInvoiceController;
    private ItemController itemController;
    
    
    /**
     * Creates new form SalesInvoiceUI
     */
    public SalesInvoiceDetailUI() {
        initComponents();
        
//        initUI();
        gridDataContainerUI1.setItemAdder(this);
        gridDataContainerUI1.addNewToTable();
    }
    
    

    public void setBussinesObject(Object object){
        salesInvoice = (SalesInvoice) object;
    }

    public void setUIToData(){
    }
    
    public void setDataToUI(Object data){
        
    }
    
    public Object getData(){
    return salesInvoice; 
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gridDataContainerUI1 = new org.biz.ui.prototype.GridDataContainerUI();
        jLabel1 = new javax.swing.JLabel();
        cTextField1 = new org.components.controls.CTextField();

        jLabel1.setText("Invoice Detail");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gridDataContainerUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(399, 399, 399))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(cTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(gridDataContainerUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(cTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CTextField cTextField1;
    private org.biz.ui.prototype.GridDataContainerUI gridDataContainerUI1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public GridDataLineDetailUI getLineUI() {
        SalesInvoiceLineDetailUI sliDetail= new SalesInvoiceLineDetailUI();
        return sliDetail;        
    }
}
