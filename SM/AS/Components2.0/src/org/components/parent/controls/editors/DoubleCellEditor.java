/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.controls.editors;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.biz.app.ui.util.ComponentFactory;

/**
 *
 * @author nnjj
 */
public class DoubleCellEditor extends CellEditor {

    JTextField component;
    JTable tbl;
    // This method is called when a cell value is edited by the user.

    public DoubleCellEditor(JTable jt) {
        init(jt);
    }

    public JTable getTbl() {
        return tbl;
    }

    public void setTbl(JTable tbl) {
        this.tbl = tbl;
    }

    private void init(JTable jt) {
        //specify

        tbl = jt;
        component = new JTextField();
        ComponentFactory.createDoubleTextField(component);
        component.addActionListener(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (isCellValid()) {
                    stopCellEditing();
                }
            }
        });

    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int rowIndex, int vColIndex) {

        // 'value' is value contained in the cell located at (rowIndex, vColIndex)

        // Configure the component with the specified value
        ((JTextField) component).setText("" + value);
        ((JTextField) component).selectAll();

        // Return the configured component
        return component;
    }

    // This method is called when editing is completed.
    // It must return the new value to be stored in the cell.
    @Override
    public Object getCellEditorValue() {
        String s = ((JTextField) component).getText();
        if (!"".equals(s)) {
            Double dd = new Double((s));
            return dd;
        }
        return null;
    }

    @Override
    public JTextField getComponent() {
        return component;
    }
}
