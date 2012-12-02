/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ItemStockViewUI.java
 *
 * Created on Mar 13, 2012, 6:42:40 AM
 */
package org.biz.erp.inventory.ui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import org.biz.app.ui.util.TableUtil;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.StockModel;
import org.biz.invoicesystem.service.inventory.InventoryJournalService;
import org.components.windows.TabPanelUI;

/**
 *
 * @author Jawath
 */
public class ItemStockViewUI extends TabPanelUI {

   
    List<InventoryJournal> list;
    List<StockModel> listStockModel;
    InventoryJournalService ijs;
    
    public ItemStockViewUI() {
        initComponents();
        init();
    }

    @Override
    public void init() {
        super.init();
        list=new ArrayList<InventoryJournal>();
        listStockModel=new ArrayList<StockModel>();
        ijs=new InventoryJournalService();
        list=ijs.getDao().getAll();
        //create Sotck model
        
        
    }

    @Override
    public void events() {
        
    }
    
    public void setTableData(){
        TableUtil.cleardata(tblStock);
        for (InventoryJournal inventoryJournal : list) {
            
        }
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblStock = new org.components.controls.CxTable();

        tblStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item CodeI", "Item Description", "Unit", "Shop", "Stock", "Item Mark", "Item In Hand"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblStock);
        tblStock.getColumnModel().getColumn(0).setResizable(false);
        tblStock.getColumnModel().getColumn(1).setResizable(false);
        tblStock.getColumnModel().getColumn(2).setResizable(false);
        tblStock.getColumnModel().getColumn(3).setResizable(false);
        tblStock.getColumnModel().getColumn(4).setResizable(false);
        tblStock.getColumnModel().getColumn(5).setResizable(false);
        tblStock.getColumnModel().getColumn(6).setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE)
                .addGap(66, 66, 66))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CxTable tblStock;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
            return "Inventory View";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }
}
