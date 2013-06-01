/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.controls.editors;

import javax.swing.JTable;

/**
 *
 * @author d
 */
public class TableInteractionListner {

    JTable table;

    public TableInteractionListner(JTable table) {
        this.table = table;
    }

    public TableInteractionListner() {        
    }
    
    public void setTable(JTable table){
        this.table=table;
    }
    
    public void onCellStartEdit() {
    }

    public void onCellEditCompleted() {
    }

    public void onCellBeforeSelection() {
    }
    
    public void onCellEditing(Object cellObj,int column){
    
    }

    public boolean validateCell() {

        if (table.getSelectedColumn() == 1 && table.getSelectedRow() == 1 && "43".equals(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()))) {
            return false;
        }
        return true;
    }

    public void moveSelection() {
    }
    
    public boolean isCellEditable(int row,int column){
        return false;
    }
}