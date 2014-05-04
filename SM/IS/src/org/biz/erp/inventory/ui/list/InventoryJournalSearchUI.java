/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.erp.inventory.ui.list;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.biz.app.ui.util.QueryManager;
import org.biz.dao.service.CQuery;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.components.windows.SearchQueryUIPanel;

/**
 *
 * @author jawath
 */
public class InventoryJournalSearchUI  extends SearchQueryUIPanel {

    /**
     * Creates new form InventoryJournalSearchUI
     */
    public InventoryJournalSearchUI() {
        super();
 
    }

    public void init() {
        initComponents();

        super.init();
        qms = new QueryManagerx();

        tfind.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                findAction();
            }
        });
    }
    
    class QueryManagerx extends QueryManager {
        
        public CQuery getCQuery() {
            if (getService() == null) {
                return null;
            }

            //get The date 


            return getService().getDao().getAllQuery();
        }

       
        public CQuery getCountQuery() {

            return getService().getDao().getAllCountQuery();
        }
    };
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cTextField1 = new org.components.controls.CTextField();
        twarehouse = new com.components.custom.TextFieldWithPopUP<Warehouse>();
        jLabel2 = new javax.swing.JLabel();
        tshop = new com.components.custom.TextFieldWithPopUP<Shop>();
        jLabel1 = new javax.swing.JLabel();
        cDatePicker2 = new org.components.controls.CDatePicker();
        tfind = new org.components.controls.CButton();

        twarehouse.setText("");

        jLabel2.setText("Shop");

        tshop.setText("");

        jLabel1.setText("Warehouse");

        tfind.setText("Find");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(twarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tshop, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(tfind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(299, 299, 299))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tshop, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(twarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CDatePicker cDatePicker2;
    private org.components.controls.CTextField cTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private org.components.controls.CButton tfind;
    private com.components.custom.TextFieldWithPopUP<Shop> tshop;
    private com.components.custom.TextFieldWithPopUP<Warehouse> twarehouse;
    // End of variables declaration//GEN-END:variables
}
