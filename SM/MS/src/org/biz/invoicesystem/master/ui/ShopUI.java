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
import javax.swing.JPanel;
import org.biz.app.ui.util.UIEty;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.service.master.ShopService;
import org.biz.invoicesystem.system.SystemUtil;
import org.components.windows.TabPanelUI;

/**
 *
 * @author d
 */
public class ShopUI extends TabPanelUI {

    /** Creates new form ShopUI */
    public ShopUI() {
        initComponents();
        init();
    }
    Shop shop;
    List<Shop> shops;
    ShopService service;

    @Override
    public void init() {
        controlPanel1.setCrudController(this);
        shop = new Shop();
        service = new ShopService();

    }

    @Override
    public void save() {
        uiToety();
        shop.setId(SystemUtil.timeStampKey());
        service.getDao().save(shop);
        shop = new Shop();
        
        //get last updated or creaded ety 
        //make this a pattern
        shops = service.getDao().getAll();
        
        
    }

    private void uiToety() {
        shop.setCode(UIEty.tcToStr(tshopcode));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlPanel1 = new com.components.custom.ControlPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cxTable1 = new org.components.controls.CxTable();
        tshopcode = new org.components.controls.CTextField();
        cLabel1 = new org.components.controls.CLabel();
        cLabel2 = new org.components.controls.CLabel();
        tshopname = new org.components.controls.CTextField();

        setLayout(null);
        add(controlPanel1);
        controlPanel1.setBounds(318, 20, 356, 35);

        cxTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Shop Code", "Shop Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(cxTable1);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 158, 664, 131);
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
    private com.components.custom.ControlPanel controlPanel1;
    private org.components.controls.CxTable cxTable1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CTextField tshopcode;
    private org.components.controls.CTextField tshopname;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return "Shop ";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }
}
