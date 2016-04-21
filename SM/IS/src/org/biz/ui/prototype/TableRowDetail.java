/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.ui.prototype;

import org.components.containers.CPanel;
import org.components.parent.controls.editors.TableInteractionListner;

/**
 *
 * @author jawa
 */
public class TableRowDetail extends CPanel {

    
    protected TableContainer tableContainer;
    /**
     * Creates new form TableLineDetail
     */
    public TableRowDetail() {
        initComponents();
    }
    
    
    private TableInteractionListner tableInteractionListner = new TableInteractionListner() {

        @Override
        public Object[] getTableData(Object row) {
            return TableRowDetail.this.getTableData(row);
        }

        @Override
        public void selectionChanged(Object newRowObject) {
            onSelectionChanged(newRowObject);
        }

        @Override
        public void onDoubleClicked(Object newRowObject) {
            onTableDoubleClicked(newRowObject);
            
        }

    };
    
    public Object[] getTableData(Object row) {
        return null;
    }

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void setTableContainer(TableContainer tableContainer) {
        this.tableContainer = tableContainer;
        tableContainer.setTableListener(tableInteractionListner);
        tableContainer.setTableLineDetail(this);

    }

    public void initUI() {
    }
    
    
    
    public void onSelectionChanged(Object newRowObject) {
        clearUI();
        setDataToPanel(newRowObject);        
    }
    
    public void onTableDoubleClicked(Object newRowObject) {
//            ListViewUI.this.selectionChanged(newRowObject); //To change body of generated methods, choose Tools | Templates.
    }

    public void clearUI() {
        
        
    }

    public void onAcceptLineItem(Object obj){
    
    }
    public boolean isValidToAccept(){
    return true;
    }
    
    public void onRemoveLineItem(Object selectedObject) {
        
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
    }
 
    public boolean isValidToRemoveLineItem(Object selectedObject) {
        return true;

    }
}
