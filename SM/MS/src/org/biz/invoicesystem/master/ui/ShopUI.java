/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ShopUI.java
 *
 * Created on Apr 30, 2012, 8:33:09 PM
 */
package org.biz.invoicesystem.master.ui;

import java.util.List;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.app.ui.util.UIEty;
import org.biz.app.ui.util.Validator;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.service.master.ShopService;
import org.components.windows.DetailPanel;

/**
 *
 * @author d
 */
public class ShopUI extends DetailPanel<Shop> {

    /** Creates new form ShopUI */
    public ShopUI() {
        super();
    }
    Shop shop;
    List<Shop> shops;
    ShopService service;


    
    
    @Override
    public void init() {
        
        initComponents();
        super.init();
    }
//
    @Override
    public void setBusObject(Shop obj) {
        tshopcode.setValue(obj.getCode());
//        tshopname.setValue(obj.get());
    }
//
    @Override
    public Shop getBusObject() {
        if (selectedObject == null) {
            selectedObject = new Shop();
        }
        selectedObject.setCode(tshopcode.getValue());
        selectedObject.setCode(tshopname.getValue());
        return selectedObject;
    }
//
    @Override
    public void clear() {
        tshopcode.clear();
        tshopname.clear();
        selectedObject=null;
        super.clear();
    }

    @Override
    public boolean isValideEntity() {
        if (Validator.isEmptyOrNull(tshopcode.getValue())) {
            MessageBoxes.errormsg(tshopcode, "Shop code Not valid", "Shop code Not valid");
            return false;
        }
        return true;
    }


/*    @Override
    public void save() {
        uiToety();
        shop.setId(SystemUtil.timeStampKey());
        service.getDao().save(shop);
        shop = new Shop();
        
        //get last updated or creaded ety 
        //make this a pattern
        shops = service.getDao().getAll();
        
        
    }
*/
    private void uiToety() {
        shop.setCode(UIEty.tcToStr(tshopcode));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tshopcode = new org.components.controls.CTextField();
        cLabel1 = new org.components.controls.CLabel();
        cLabel2 = new org.components.controls.CLabel();
        tshopname = new org.components.controls.CTextField();

        add(tshopcode);
        tshopcode.setBounds(118, 33, 131, 25);

        cLabel1.setText("Shop Code");
        add(cLabel1);
        cLabel1.setBounds(10, 33, 80, 25);

        cLabel2.setText("Shop Name");
        add(cLabel2);
        cLabel2.setBounds(10, 64, 80, 25);
        add(tshopname);
        tshopname.setBounds(118, 64, 131, 25);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CTextField tshopcode;
    private org.components.controls.CTextField tshopname;
    // End of variables declaration//GEN-END:variables


}
