/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.erp.inventory.ui;

import org.biz.app.ui.util.QueryManager;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.UOM;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.windows.ListViewUI;

/**
 *
 * @author jawath
 */
public class ItemSummeryLVUI extends ListViewUI {

    /**
     * Creates new form ItemSummeryLVUI
     */
    public ItemSummeryLVUI() {
        super();
    }
    
       @Override
    public void initPaging(QueryManager qm) {
//        initComponents();
        setPaging(cPaginatedPanel1,tbl);        
        super.initPaging(qm);    
        tbl.init(Item.class, new Class[]{ String.class, String.class, String.class, Double.class},
                 new String[]{ "Code","Description ","UOM", "QTY"});
        tbl.setTableInteractionListner(tableInteractionListner);
        
    }
    
    
    
     private TableInteractionListner tableInteractionListner = new TableInteractionListner(){

        @Override
        public Object[] getTableData(Object row) {
            Object [] item= (Object[])row;
            Item it=(Item)item[0];
            UOM uom=(UOM)item[1];
            Double qty=(Double) item[2];
            return new Object[]{it,it.getCode(),it.getDescription(),uom.getCode(),qty};
        }
    
    };
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 574, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
