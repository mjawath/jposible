/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.erp.inventory.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import org.biz.app.ui.util.QueryManager;
import org.biz.dao.service.CQuery;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.service.inventory.InventoryJournalService;
import org.components.windows.SearchQueryUIPanel;

/**
 *
 * @author jawath
 */
public class ItemSummerySQUI extends SearchQueryUIPanel {

    /**
     * Creates new form ItemSummerySQUI
     */
    public ItemSummerySQUI() {
        super();
    }

    public void postInit(){
        
        initComponents();      
        
        super.postInit();
        
        qms=new QueryManagerx();
        tfind.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                findAction();
            }
        });
        
        
        
        
        
    }
    
    
    class QueryManagerx extends QueryManager {

        @Override
        public CQuery getCQuery() {
            if (getService() == null) {
                return null;
            }
            Shop seleShop = tShop.getSelectedObject();
            Warehouse warehouse = twarehouse.getSelectedObject();
//        List lst = service.getDao().getSummery(
//                seleShop==null?null: seleShop.getId(),warehouse==null?null:warehouse.getId());
////        tbl.setModelCollection(lst);
            InventoryJournalService ijs = (InventoryJournalService) getService();
            if (ijs == null) {
                return null;
            }

            return ijs.getDao().getSummerySql(titem.getValue());
//            return ijs.getDao().getSummerySql(titem.getValue(),seleShop==null?null: seleShop.getId(),warehouse==null?null:warehouse.getId());

        }

        @Override
        public void postQuery(final Object objs) {
            //create new thread 
            //get first  n object / item and 
            // get the summery of the inventory for those items 
            // set the items to table  in the EDT
            new Thread() {

                @Override
                public void run() {
                    lastListPage.size();
                 //if this  > 0 
                    //get first n item ids
                    // get the sql 
                    //set to table
                    ArrayList<String> str = new ArrayList();
                    List oobjs = ((QueryManagerx) objs).getList();
                    int v = 0;
                    for (Object strobj : oobjs) {
                        Object[] xd = (Object[]) strobj;
                        Item it = (Item) xd[0];
//                        str.add(it.getId());
                        if (v == 5) {
                            break;
                        }
                        v++;
                    }

                    List lst = new InventoryJournalService().getDao().getSummery("", "", str);
                    ((ItemSummeryLVUI) listView).setValueToTable(lst);
                }

            }.start();

        }

    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titem = new org.components.controls.CTextField();
        jLabel1 = new javax.swing.JLabel();
        tShop = new com.components.custom.TextFieldWithPopUP<Shop>();
        jLabel2 = new javax.swing.JLabel();
        tfind = new org.components.controls.CButton();
        twarehouse = new org.biz.ui.master.list.WareHousePopup();

        jLabel1.setText("Warehouse");

        tShop.setText("");

        jLabel2.setText("Shop");

        tfind.setText("Find");

        twarehouse.setText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titem, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(twarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tShop, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(tfind, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(347, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(titem, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(twarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tShop, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfind, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private com.components.custom.TextFieldWithPopUP<Shop> tShop;
    private org.components.controls.CButton tfind;
    private org.components.controls.CTextField titem;
    private org.biz.ui.master.list.WareHousePopup twarehouse;
    // End of variables declaration//GEN-END:variables
}
