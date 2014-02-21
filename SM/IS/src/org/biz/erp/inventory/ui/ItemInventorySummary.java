/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ItemInventorySummary.java
 *
 * Created on Jun 28, 2011, 4:50:00 PM
 */
package org.biz.erp.inventory.ui;

import java.util.List;
import org.biz.app.ui.event.ButtonAction;
import org.biz.app.ui.util.QueryManager;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.UOM;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.service.inventory.InventoryJournalService;
import org.biz.invoicesystem.service.inventory.InventoryMonthlySummeryService;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.windows.ListViewPanel;

/**
 *
 * @author Administrator
 */
public class ItemInventorySummary extends ListViewPanel<Object> {

    private InventoryJournalService service;
    
    /** Creates new form ItemInventorySummary */
    public ItemInventorySummary() {
        super();
        
    }
    


    @Override
    public void init() {
        super.init();        
        initComponents();
//        selectitem();
        
        tbtnFind.addActionListener(findAction);
        tbl.init(InventoryJournal.class, new Class[]{Item.class, String.class,  String.class, String.class},
                 new String[]{"Item", "code","uom","qty"});
        tbl.setTableInteractionListner(tableInteractionListner);
        cPaginatedPanel1.init(service, searchListener, tbl);

    }
    
    private QueryManager searchListener = new QueryManager() {
//        @Override
        public String getQuery() {

                    Shop seleShop=tshop.getSelectedObject();
        Warehouse warehouse=twarehouse.getSelectedObject();
        List lst = service.getDao().getSummery(
                seleShop==null?null: seleShop.getId(),warehouse==null?null:warehouse.getId());
//        tbl.setModelCollection(lst);



            String qry ="";// "  c.code " + " like " + " ?1 ";//" where c."+myfield+" "+ myoperator +" ?1 ";
            return qry;
        }

//        @Override
        public Object[] getParams() {
            return new Object[]{ };
        }
    };
    
    private TableInteractionListner tableInteractionListner = new TableInteractionListner(){

        @Override
        public Object[] getTableData(Object row) {
            Object [] item= (Object[])row;
            Item it=(Item)item[0];
            UOM uom=(UOM)item[1];
            Double qty=(Double) item[2];
            return new Object[]{it,it.getCode(),uom.getCode(),qty};
        }
    
    };
    
    private ButtonAction  findAction=new ButtonAction(){
    
        @Override
        public Object executeTask(Object objs  ) {
            return findItemForQuery();
        }

        @Override
        public void resultTask(Object objs) {
            
        }  
    };
   
    
    private List findItemForQuery() {
        //find the item for 
        // provided warehoue
        //provoided shop 
        //provided time period  ???!!??
        //normally current date is used
        Shop seleShop=tshop.getSelectedObject();
        Warehouse warehouse=twarehouse.getSelectedObject();
        List lst = service.getDao().getSummery(
                seleShop==null?null: seleShop.getId(),warehouse==null?null:warehouse.getId());
//        tbl.setModelCollection(lst);

        return null;
    }
            
    
    
    InventoryMonthlySummeryService summeryService;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cDatePicker1 = new org.components.controls.CDatePicker();
        tbtnFind = new org.components.controls.CButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new org.components.controls.CxTable();
        cPaginatedPanel1 = new org.biz.app.ui.util.CPaginatedPanel();
        jLabel1 = new javax.swing.JLabel();
        twarehouse = new com.components.custom.TextFieldWithPopUP<Warehouse>();
        tshop = new com.components.custom.TextFieldWithPopUP<Shop>();
        jLabel2 = new javax.swing.JLabel();

        tbtnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtnFindActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(tbl);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(cPaginatedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cPaginatedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setText("Warehouse");

        twarehouse.setText("");

        tshop.setText("");

        jLabel2.setText("Shop");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(cDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbtnFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(twarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(tshop, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(198, 198, 198))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(twarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tshop, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tbtnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbtnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnFindActionPerformed

    }//GEN-LAST:event_tbtnFindActionPerformed

    @Override
    public void setService(Service service) {
    this.service=(InventoryJournalService) service;
    }

    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CDatePicker cDatePicker1;
    private org.biz.app.ui.util.CPaginatedPanel cPaginatedPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    protected org.components.controls.CxTable tbl;
    private org.components.controls.CButton tbtnFind;
    private com.components.custom.TextFieldWithPopUP<Shop> tshop;
    private com.components.custom.TextFieldWithPopUP<Warehouse> twarehouse;
    // End of variables declaration//GEN-END:variables
}
