/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.controls.editors;

import javax.swing.AbstractCellEditor;

/**
 *
 * @author nnjj
 */
public abstract  class CellEditor extends  AbstractCellEditor
        implements ICellEditor {
    
    //for the sace of column value validation
     public boolean isCellValid() {
        return true;
    }

    @Override
    public void actionPerformed() {
       
    }

    @Override
    public void getEditingValue() {
        
    }

   
    
     
}
