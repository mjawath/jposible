/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.erp.inventory.ui.list;

import org.biz.invoicesystem.entity.master.Warehouse;
import org.components.windows.ListViewPanel;

/**
 *
 * @author d
 */
public class WareHouseListUI extends ListViewPanel<Warehouse>{ 
    
    

    /**query mange
     * Creates new form WareHouseListUI
     */
    public WareHouseListUI() {
//        initComponents();
        super();
    }

    @Override
    public void init() {
        initComponents();      
        listUI = wareHoueLV1;
        searchQueryUI = wareHouseSearchQueryUIPanel1;
        super.init();          
        //set query manager for pagination
    }
    
 
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        wareHoueLV1 = new org.biz.erp.inventory.ui.list.WareHoueLV();
        wareHouseSearchQueryUIPanel1 = new org.biz.erp.inventory.ui.list.WareHouseSUI();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(wareHoueLV1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(wareHouseSearchQueryUIPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(wareHouseSearchQueryUIPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(wareHoueLV1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.biz.erp.inventory.ui.list.WareHoueLV wareHoueLV1;
    private org.biz.erp.inventory.ui.list.WareHouseSUI wareHouseSearchQueryUIPanel1;
    // End of variables declaration//GEN-END:variables
}
