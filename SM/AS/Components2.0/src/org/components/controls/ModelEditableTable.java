/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.EventObject;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellEditor;
import org.biz.app.ui.util.ReflectionUtility;
import org.components.parent.controls.PxTable;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.parent.controls.editors.BaseCellEditor;


/**
 *
 * @author d
 */
public class ModelEditableTable<T> extends PxTable implements ListSelectionListener{

    public static int ROW_OBJECT_INDEX=0; 
    private boolean isCellEditableOnCellSelection; //responsible for defining weather cell is editable or not
    
 // cell eding we can escapre from duplicate method calls  of edit cell at
    /**
     * Creates new form ModelEditableTable
     */
    public ModelEditableTable() {
        initComponents();
        

        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        };
        this.setSurrendersFocusOnKeystroke(true);
        setCellEditableOnCellSelection(true);
        setAutoCreateRowSorter(false);//
        getColumnModel().getColumn(0).setCellEditor(new BaseCellEditor(this));
        getColumnModel().getColumn(1).setCellEditor(new BaseCellEditor(this));

        
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    System.out.println("Enter pressed");
                }
                super.keyPressed(e);
            }
        });
        makeCellEditorsNavigatable();
        this.getSelectionModel().addListSelectionListener(this);
        
        /* default setters */
        /*
         key stroke        
         table.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "doMyArrowDown");
         table.getTable().getActionMap().put("doMyArrowDown", new ArrowDownAction());
         * 
         * 
         */
        
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
            
//                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT){
//                    System.out.println("--------------"+e);
//                    e.consume();
//                }
        
            }
        
        });
    }

    public void makeCellEditorsNavigatable() {
//            this.registerKeyboardAction(al, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
//        this.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
//        this.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_UP));
//        this.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
        InputMap im = getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
//        im.put(KeyStroke.getKeyStroke("DOWN"), "none");
//        im.put(KeyStroke.getKeyStroke("UP"), "none");
//        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "none");
//         im.put(KeyStroke.getKeyStroke("UP"), "TABLE_KEY_UP_EVENT");

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK), "TABLE_KEY_UP_EVENT");
        ActionMap actionmap = getActionMap();
        actionmap.put("TABLE_KEY_UP_EVENT", keyUP);
        
//         string act=(string) im.get(KeyStroke.getKeyStroke("pressed KP_RIGHT"));
//[ 4]	Hashtable$Entry	"pressed KP_RIGHT => selectNextColumn"	

    }
    
    private Action keyUP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("action key up ^");
        }
    };

    public void onBeforeEditStart() {
        int col = getEditingColumn();
        int row = getEditingRow();
        System.out.println(col + " be- " + row);
    }

    public void onAfterEditStart() {
        int col = getEditingColumn();
        int row = getEditingRow();
        System.out.println(col + " af- " + row);
    }

    public boolean isCellEditableOnCellSelection() {
        return isCellEditableOnCellSelection;
    }

    public void setCellEditableOnCellSelection(boolean makeCellEditableOnCellSelection) {
        this.isCellEditableOnCellSelection = makeCellEditableOnCellSelection;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Name"
            }
        ));
        setCellSelectionEnabled(true);
        setRowHeight(38);
        setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
        super.changeSelection(rowIndex, columnIndex, toggle, extend);
        System.out.println("##########---selection changed " + rowIndex + "-" + columnIndex);
//        editCellAt(rowIndex, columnIndex);
    }

    @Override
    public boolean editCellAt(int row, int column) {
        boolean edit = super.editCellAt(row, column);
        return edit;
    }

    @Override
    public boolean editCellAt(int row, int column, EventObject e) {
//        onBeforeEditStart();
        boolean edit = super.editCellAt(row, column, e);
        System.out.println("************editing started s " + row + "-" + column);
//        onAfterEditStart();
        return edit;
    }

    @Override
    public void editingCanceled(ChangeEvent e) {
        super.editingCanceled(e);
    }

    @Override
    public void editingStopped(ChangeEvent e) {

        int col = getEditingColumn();
        int row = getEditingRow();
        super.editingStopped(e);
        System.out.println("*******************edting Stopped "+col+" "+row);
      //now move to next editable cell
        System.out.println(e);
       
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        
        super.processKeyEvent(e);        
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            System.out.println("change selection endter");
        }
        
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("change selection left");
        }
    }   

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        super.setValueAt(aValue, row, column);
        //get the object bi of row
        Object model=getValueAt(row, ROW_OBJECT_INDEX);
        String attr=getAttriuteNameForColumn(column);
        if(attr==null)return;
        ReflectionUtility.setProperty(model, attr, aValue);        
    }
    
    public String getAttriuteNameForColumn(int column){
        int col=convertColumnIndexToModel(column);
        if(column<=0 || getPropertiesEL().length<=col-1)return null;
        return getPropertiesEL()[col];//column 
    }    
    
    @Override
    public TableCellEditor getCellEditor() {
        return super.getCellEditor();
    }

    /**
     * selects cell which is next to currently selected cell
     */
    public void selectNextCell() {
        int col = this.getSelectedColumn();
        int row = this.getSelectedRow();
            col = convertColumnIndexToModel(col);
            row = convertRowIndexToModel(row);
            if (col < getColumnCount() - 1 && row < getRowCount()) {
                changeSelection(row, ++col, false, false);
            } else if (col == getColumnCount() - 1 && row < getRowCount() - 1) {
                changeSelection(++row, 0, false, false);
            }
    }
    /**
     * selects cell which is previouse  to currently selected cell
     */
    public void selectPreviousCell() {
        int col = this.getSelectedColumn();
        int row = this.getSelectedRow();
        col = convertColumnIndexToModel(col);
        row = convertRowIndexToModel(row);
        if (col == 0 && row > 0) {
            changeSelection(row - 1, this.getColumnCount() - 1, false, false);
        }
        if (col == 0 && row == 0) {            
        } else if (col > 0 && row > 0) {
            changeSelection(row, --col, false, false);
        }        
    }
    /**
     * @return the tableInteractionListner
     */
    public void setEditorForColumn(int column,TableCellEditor cellEditor) {
        
    }

    public void setCellEditor(int column, BaseCellEditor ce) {
        ce.setTable(this);
        this.getColumnModel().getColumn(column).setCellEditor(ce);
    }

}

