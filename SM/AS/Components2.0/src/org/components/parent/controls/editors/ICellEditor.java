/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.controls.editors;

import javax.swing.JComponent;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author nnjj
 */
public interface  ICellEditor extends TableCellEditor{
  void getEditingValue();
     JComponent getComponent();
    void actionPerformed();
   boolean isCellValid();
}
