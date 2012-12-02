/*t
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.controls.editors;

import com.components.custom.PagedPopUpPanel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.EventObject;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author nnjj
 */
public class TablePopUpCellEditor extends CellEditor
         {

    private JTable tbl;
    PagedPopUpPanel popUpComponent;
    JTextField component;

    // This method is called when a cell value is edited by the user.
    public TablePopUpCellEditor(PagedPopUpPanel popUpComponent, JTable tbl) {
        this.popUpComponent = popUpComponent;
        init(tbl);

    }

    public static void main(String[] args) {
        
    }

  

   

    public TablePopUpCellEditor(JTable jt) {
        init(jt);


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
            }
        });

    }
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int rowIndex, int vColIndex) {
        
   //   init(tbl);
      
        // 'value' is value contained in the cell located at (rowIndex, vColIndex)
        if (!isSelected) {
            JLabel jl = new JLabel();
//            if (value == null) {
//                jl.setText("");
//            } else {
//                jl.setText(value.toString());
//            }
            return jl;
        }
        
        // Configure the component with the specified value
        if (value != null) {
            ((JTextField) component).setText("" + value);
            ((JTextField) component).selectAll();

        }
        // Return the configured compofnent
        return component;
    }

    
     @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        System.out.println("calling.........shuld select cellll");
        component.setText("");
        return true;
    }
    
    // This method is called when editing is completed.
    // It must return the new value to be stored in the cell.
    public Object getCellEditorValue() {
        return ((JTextField) component).getText();
    }

    public JTextField getComponent() {
        return component;
    }

    /**
     * @return the masterTbl
     */
    public JTable getMasterTbl() {
        return tbl;
    }

    /**
     * @param masterTbl the masterTbl to set
     */
    public void setMasterTbl(JTable masterTbl) {
        this.tbl = masterTbl;
    }
}
