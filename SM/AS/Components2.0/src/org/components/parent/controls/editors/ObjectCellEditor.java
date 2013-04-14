/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.controls.editors;

import org.components.controls.ModelEditableTable;

/**
 *
 * @author d
 */
public class ObjectCellEditor extends CellEditor{

    public ObjectCellEditor(ModelEditableTable table) {
        super(table);
    }

    @Override
    public Object getCellEditorValue() {
        System.out.println("getting object"); 
        return  "getting object";
    }
    
}
