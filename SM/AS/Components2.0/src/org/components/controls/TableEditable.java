/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Table.java
 *
 * Created on May 6, 2010, 10:48:54 AM
 */
package org.components.controls;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.EventObject;
import java.util.HashMap;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import org.components.parent.controls.PxTable;
import org.components.parent.controls.editors.TableColumnAction;
import org.components.parent.controls.editors.TableActions;

/**
 *
 * @author nano
 */
public class TableEditable extends PxTable {

    
    
    /** Creates new form BeanForm */
    public TableEditable() {
        initComponents();
        this.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        this.setSurrendersFocusOnKeystroke(true);
        ActionListener al = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            }
        };
        // i am disabling the default behaviour of table editing
        // so we can imlplement our own way of navigation 
        this.registerKeyboardAction(al, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
//        this.registerKeyboardAction(al, KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        this.setDefaultRenderer(String.class, new CustomRenderer());
        this.setDefaultRenderer(Double.class, new CustomRenderer());
        this.setDefaultRenderer(Object.class, new CustomRenderer());
        action=new TableActions(this, new HashMap<Integer, TableColumnAction>());
    }

 



    
    @Override
    public void editingStopped(ChangeEvent e) {
        super.editingStopped(e);        
//        if(TableUtil.newRowID.equals( TableUtil.getSelectedValue(this, 0))){
//           this.setValueAt( TableUtil.getNewRowId(), this.getSelectedRow(), 0);
//        }
        action.selectionAction();
        
                
                
//                boolean ab = //Action will return true if it need new row or 
//                if (((rowcount-1 )==selrow) && ab) { //false normal selection           
//                    TableUtil.addrow(this, new Object[]{});                    
//                    this.changeSelection(selrow+1 , 1, false, false);
//                } else {
//                    
//                if (((rowcount-1 ) > selrow) && ab){
//                    System.out.println("ee");
//                this.changeSelection(selrow+1, 1, false, false);
//                return;
//                }
//                selcol = (colcount - 1) == selcol ? selcol : ++selcol;
//                this.changeSelection(selrow, selcol, false, false);
//                }
                
                    
    }
    public void setTableAction(TableActions action ){
        this.action=action;
        action.setTbl(this);
    }
    
    public TableActions getTableSelection( ){
        return action;
    }
    
    TableActions action;
    public boolean action(){            
        return false;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 250, 215));
        setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "col 1", "col 2", "col 3", "col 4"
            }
        ));
        setIntercellSpacing(new java.awt.Dimension(10, 5));
        setSurrendersFocusOnKeystroke(true);
        getTableHeader().setReorderingAllowed(false);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
       
        int sr=getSelectedRow();
        
        if(sr ==-1 || sr==rowIndex || ( sr!=rowIndex && action.rowValid())){
            super.changeSelection(rowIndex, columnIndex, toggle, extend);
            action.commitChanges(sr);
//            editCellAt(rowIndex, columnIndex);
//            prepareEditor(this.getCellEditor(rowIndex, columnIndex), rowIndex, columnIndex);
        }
        editCellAt(sr, columnIndex);
    }

    
    


    class CustomRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (isSelected && hasFocus) {
                c.setBackground(Color.red);
            }
            int sr=table.getSelectedRow();
            int sc=table.getSelectedColumn();
//            if (sr==row && sc== column) {
//                c.setBackground(Color.red);
//            }

            // Formatting
            return c;
        }
    }

    @Override
    public boolean editCellAt(int row, int column, EventObject e) {
    
        if(isCellSelected(row, column)){
           boolean b=  super.editCellAt(row, column, e);
//           Component jc= getEditorComponent();
//           jc.dispatchEvent(new AWTEvent(e.getSource(),e.) {});
        return b;
    }
    return false; 
    }

   
    
}
