/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.ui.list.master;

import java.util.Date;
import org.biz.app.ui.util.QueryManager;
import org.biz.invoicesystem.entity.master.Category;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.windows.ListViewUI;

/**
 *
 * @author jawath
 */
public class CategoryLVUI extends ListViewUI {

    /**
     * Creates new form CategoryLVUI
     */
    public CategoryLVUI() {
        super();
//        initComponents();
    }
    
     @Override
    public void initPaging(QueryManager qm) {
   
        
//        initComponents();
        
        setPaging(cPaginatedPanel1,tbl);        
        super.initPaging(qm);    
//        super.initPaging(qm) ;    
        tbl.init(Warehouse.class, new Class[]{String.class, String.class, Date.class, Date.class},
                 new String[]{"id", "code", "savedDate", "editedDate"});
        tbl.setTableInteractionListner(tableInteractionListner);
        
    }
    
    

    private TableInteractionListner tableInteractionListner = new TableInteractionListner(){

        @Override
        public Object[] getTableData(Object row) {
            Category item= (Category)row;
            return new Object[]{item,item.getId(),item.getCode(),item.getDescription(),item.getSavedDate(),item.getEditedDate()};
        }
    
    };

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
