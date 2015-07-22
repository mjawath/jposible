/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.ui.list.master;

import java.util.Date;
import org.biz.app.ui.util.QueryManager;
import org.biz.invoicesystem.entity.master.Item;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.windows.ListViewUI;

/**
 *
 * @author Jawad
 */
public class ItemListViewUI extends ListViewUI {

    /**
     * Creates new form ItemListViewUI
     */
    public ItemListViewUI() {
        super();
    }
    
    
    @Override
    public void initPaging(QueryManager qm) {
   
       if(qm==null){
       
       } 
//        initComponents();      
              
        super.initPaging(qm);    
//        super.initPaging(qm) ;    
        tbl.init(Item.class, new Class[]{String.class, String.class, Date.class, Date.class},
                 new String[]{ "code", "description","savedDate", "editedDate"});
        tbl.setTableInteractionListner(tableInteractionListner);
        
    }
    
    private TableInteractionListner tableInteractionListner = new TableInteractionListner() {

        @Override
        public Object[] getTableData(Object row) {
            Item item = (Item) row;
            return new Object[]{item, item.getCode(), item.getDescription(), item.getSavedDate(), item.getEditedDate()};
        }
    };

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 914, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
