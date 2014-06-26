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
        wareHouseListUI1.init();
    }

    @Override
    public void setBusObject(Warehouse obj) {
        tcode.setValue(obj.getCode());
    }

    @Override
    public Warehouse getBusObject() {

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tcode = new org.components.controls.CTextField();
        cLabel1 = new org.components.controls.CLabel();
        wareHouseListUI1 = new org.biz.erp.inventory.ui.list.WareHouseListUI();

        cLabel1.setText("Warehouse name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(tcode, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 872, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(wareHouseListUI1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(wareHouseListUI1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CTextField tcode;
    private org.biz.erp.inventory.ui.list.WareHouseListUI wareHouseListUI1;
    // End of variables declaration//GEN-END:variables


    @Override
    public JPanel getJPanel() {
        return this;
    }
}
