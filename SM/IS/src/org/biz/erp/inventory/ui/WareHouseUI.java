/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * WhereHouseUI.java
 *
 * Created on Apr 30, 2012, 8:30:39 PM
 */
package org.biz.erp.inventory.ui;

import javax.swing.JPanel;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.app.ui.util.UIEty;
import org.biz.app.ui.util.Validator;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.service.master.WareHouseService;
import org.components.windows.DetailPanel;

/**
 *
 * @author d
 */
public class WareHouseUI extends DetailPanel<Warehouse> {

    WareHouseService service;
    Warehouse warehouse;

    /**
     * Creates new form WhereHouseUI
     */
    public WareHouseUI() {
        super();
        
    }

    @Override
    public void init() {
        warehouse = new Warehouse();
        service = new WareHouseService();
        initComponents();
        super.init();
    }

    @Override
    public void setDataToUI(Warehouse obj) {
        tcode.setValue(obj.getCode());
        selectedObject = obj;
    }

    @Override
    public Warehouse uiToData() {

        Warehouse so = new Warehouse();
        so.setCode(tcode.getValue());
        return so;
    }

    @Override
    public void clear() {
        tcode.clear();
        selectedObject=null;
        super.clear();
    }

    @Override
    public boolean isValideEntity() {
        if (Validator.isEmptyOrNull(tcode.getValue())) {
            MessageBoxes.errormsg(this, "Item code Not valid", "Item code Not valid");
            return false;
        }
        return true;
    }

    public void uiety() {
        warehouse.setCode(UIEty.tcToStrE(tcode));
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tcode = new org.components.controls.CTextField();
        cLabel1 = new org.components.controls.CLabel();

        tcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tcodeActionPerformed(evt);
            }
        });

        cLabel1.setText("Warehouse name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(tcode, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tcodeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CTextField tcode;
    // End of variables declaration//GEN-END:variables


    @Override
    public JPanel getJPanel() {
        return this;
    }
}
