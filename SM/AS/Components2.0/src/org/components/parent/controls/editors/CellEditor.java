/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.controls.editors;

import org.components.controls.ModelEditableTable;

/**
 *
 * @author nnjj
 */
public abstract  class CellEditor extends  mce {
    
    public CellEditor(ModelEditableTable table) {
        super(table);
    }   
    

    //for the sace of column value validation
     public boolean isCellValid() {
        return true;
    }      
    
     public void setTable(ModelEditableTable tbl) {
        this.table = tbl;
    } 
}
