/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.controls.editors;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author nnjj
 */
public interface ICellEditor extends TableCellEditor {

    void getEditingValue();

    void actionPerformed();

    boolean isCellValid();
    
    void setTable(JTable tbl);
}
