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

        setLayout(null);
        add(tcode);
        tcode.setBounds(60, 50, 260, 25);

        cLabel1.setText("Warehouse name");
        add(cLabel1);
        cLabel1.setBounds(20, 20, 130, 25);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CTextField tcode;
    // End of variables declaration//GEN-END:variables


    @Override
    public JPanel getJPanel() {
        return this;
    }
}
