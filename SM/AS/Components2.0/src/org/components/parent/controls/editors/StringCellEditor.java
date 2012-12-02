/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.controls.editors;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author nnjj
 */
public class StringCellEditor extends CellEditor {

    JTextField component;
    JTable tbl;

    public StringCellEditor(JTable tbl) {
        init(tbl);
    }

    @Override
    public Object getCellEditorValue() {
        
         String s = ((JTextField) component).getText();

        return component.getText();
    }

    private void init(JTable jt) {
        //specify

        tbl = jt;
        component = new JTextField();

        component.addActionListener(new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (isCellValid()) {
                    stopCellEditing();
                       component.setText("");
                }

//                int selcol = tbl.getSelectedColumn();
//                int selrow = tbl.getSelectedRow();
//                int colcount = tbl.getColumnCount();
//                int rowcount = tbl.getRowCount();
//
//                boolean ab = action();//Action will return true if it need new row or 
//                if (((rowcount - 1) == selrow) && ab) {              //false normal selection           
//                    TableUtil.addrow(tbl, new Object[]{});
//                    tbl.changeSelection(selrow + 1, 1, false, false);
//                } else {
//                    selcol = (colcount - 1) == selcol ? selcol : ++selcol;
//                    tbl.changeSelection(selrow, selcol, false, false);
//                }
            }
        });

    }

 

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//        CTextField component=new CTextField();
        if (!isSelected) {
            JLabel jl = new JLabel();
            
         
//            if (value == null) {
//                jl.setText("");
//            } else {
//                jl.setText(value.toString());
//            }
            return jl;
        }
        // 'value' is value contained in the cell located at (rowIndex, vColIndex)
        init(tbl);
        // Configure the component with the specified value
        if (value != null) {
          
              ((JTextField) component).setText("" + value);
            ((JTextField) component).selectAll();
 
        }   // Return the configured component
        return component;

    }

   
    
    
    @Override
    public boolean stopCellEditing() {
        boolean b = false;
        if (isCellValid()) {
            int selcol = tbl.getSelectedColumn();
            int selrow = tbl.getSelectedRow();
            b = super.stopCellEditing();
//            selcol++;
//            tbl.changeSelection(selrow, selcol, false, false);
            component.setText("");

        }
        return b;
    }

   
    

    public boolean isCellValid() {
        return true;
    }

    public JTable getTbl() {
        return tbl;
    }

    public void setTbl(JTable tbl) {
        this.tbl = tbl;
    }

    public JTextField getComponent() {
        return component;
    }
}
