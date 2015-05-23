/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.erp.inventory.ui.list;

import java.util.Date;
import org.biz.app.ui.util.QueryManager;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.windows.ListViewUI;

/**
 *
 * @author jawath
 */
 public class WareHoueLV extends ListViewUI {

    /**
     * Creates new form WareHoueLV
     */
    public WareHoueLV() {
        super();
    }

    @Override
    public void initPaging(QueryManager qm) {
   
        
//        initComponents();
        
        setPaging(cPaginatedPanel1,tbl);        
        super.initPaging(qm);    
//        super.initPaging(qm) ;    
        tbl.init(Warehouse.class, new Class[]{String.class, String.class, Date.class, Date.class},
                 new String[]{ "code"});
        tbl.setTableInteractionListner(tableInteractionListner);
        
    }
    
    
    
    
    
    private TableInteractionListner tableInteractionListner = new TableInteractionListner() {
        @Override
        public Object[] getTableData(Object row) {
            Warehouse item = (Warehouse) row;
            return new Object[]{item, item.getCode()};
        }
    };
     
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1001, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 527, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
