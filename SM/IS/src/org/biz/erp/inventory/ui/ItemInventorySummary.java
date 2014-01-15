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

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.biz.app.ui.event.ButtonAction;
import org.biz.app.ui.util.QueryManager;
import org.biz.app.ui.util.TableUtil;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.UOM;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.service.inventory.InventoryJournalService;
import org.biz.invoicesystem.service.inventory.InventoryMonthlySummeryService;
import org.biz.utility.date.DateAndTimeUtility;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.windows.ListViewPanel;
import org.joda.time.DateTime;

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
        @Override
        public String getQuery() {
            String qry ="";// "  c.code " + " like " + " ?1 ";//" where c."+myfield+" "+ myoperator +" ?1 ";
            return qry;
        }

        @Override
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
        tbl.setModelCollection(lst);

        return null;
    }
            
    
    
    InventoryMonthlySummeryService summeryService;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cDatePicker1 = new org.components.controls.CDatePicker();
        tbtnFind = new org.components.controls.CButton();
        twarehouse = new com.components.custom.TextFieldWithPopUP<Warehouse>();
        tshop = new com.components.custom.TextFieldWithPopUP<Shop>();

        tbtnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtnFindActionPerformed(evt);
            }
        });

        twarehouse.setText("Warehouse");

        tshop.setText("Shop");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(260, 260, 260)
                .addComponent(cDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tbtnFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(twarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tshop, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbtnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(twarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tshop, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(404, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbtnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnFindActionPerformed

    }//GEN-LAST:event_tbtnFindActionPerformed

    @Override
    public void setService(Service service) {
    this.service=(InventoryJournalService) service;
    }

    
    
    public void addToTable(List<InventoryJournal> ijs) {


        for (InventoryJournal inventoryJournal : ijs) {
            List<InventoryJournalLine> ijls = inventoryJournal.getLines();
            if (ijls != null) {
                for (InventoryJournalLine inventoryJournalLine : ijls) {
                }
            }
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CDatePicker cDatePicker1;
    private org.components.controls.CButton tbtnFind;
    private com.components.custom.TextFieldWithPopUP<Shop> tshop;
    private com.components.custom.TextFieldWithPopUP<Warehouse> twarehouse;
    // End of variables declaration//GEN-END:variables
}
