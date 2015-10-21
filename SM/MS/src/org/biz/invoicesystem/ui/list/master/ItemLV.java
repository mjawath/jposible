/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.ui.list.master;

import java.util.ArrayList;
import java.util.List;
import org.biz.invoicesystem.entity.master.Item;
import org.components.parent.controls.PTableColumn;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.windows.ListViewUI;

/**
 *
 * @author Jawad
 */
public class ItemLV extends ListViewUI  {

    /**
     * Creates new form ItemLV
     */
    public ItemLV() {
        super();
        getTable().setTableInteractionListner(tableInteractionListner);
        List<PTableColumn> tblCols = new ArrayList();
          tblCols.add( new PTableColumn(String.class, "ID"));
          tblCols.add( new PTableColumn(String.class, "Code"));
          tblCols.add( new PTableColumn(String.class, "Desc"));
        
        getTable().init(Item.class, tblCols);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
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


    
        
    private TableInteractionListner tableInteractionListner = new TableInteractionListner() {

        @Override
        public Object[] getTableData(Object row) {
            Item  item = (Item) row;
            return new Object[]{item, item.getId(), item.getCode()};
        }

        @Override
        public void selectionChanged(Object newRowObject) {
        }  
    };
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
