/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invoicingsystem;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.EventObject;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.DefaultCellEditor;
import javax.swing.InputMap;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author d
 */
public class ModelEditableTable extends javax.swing.JTable {

    private TableInteractionListner tableInteractionListner;
    private boolean isCellEditableOnCellSelection; //responsible for defining weather cell is editable or not
    private boolean isCellBeingEditedWhileChanging; // set to true when selection changing so if other editors calles the stop 
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
        tableInteractionListner = new TableInteractionListner(this);
        getColumnModel().getColumn(0).setCellEditor(new mce(this));
        getColumnModel().getColumn(1).setCellEditor(new mce(this));

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
        /*
         key stroke        
         table.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "doMyArrowDown");
         table.getTable().getActionMap().put("doMyArrowDown", new ArrowDownAction());
         * 
         * 
         */
    }

    public void makeCellEditorsNavigatable() {
//            this.registerKeyboardAction(al, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
//        this.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
//        this.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_UP));
        this.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
        InputMap im = getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
//        im.put(KeyStroke.getKeyStroke("DOWN"), "none");
//        im.put(KeyStroke.getKeyStroke("UP"), "none");
//        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "none");
//         im.put(KeyStroke.getKeyStroke("UP"), "TABLE_KEY_UP_EVENT");

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
        setRowHeight(24);
        setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {

        super.changeSelection(rowIndex, columnIndex, toggle, extend);
        System.out.println("##########---selection changed " + rowIndex + "-" + columnIndex);
        if(getEditingColumn()==columnIndex && getEditingRow()==rowIndex)return;
        if (isCellEditableOnCellSelection) {
            isCellBeingEditedWhileChanging = true;
            editCellAt(rowIndex, columnIndex);
            isCellBeingEditedWhileChanging = false;
        }
    }

    @Override
    public boolean editCellAt(int row, int column) {
        boolean edit = super.editCellAt(row, column);
        System.out.println("************editing started s " + row + "-" + column);
        return edit;
    }

    @Override
    public boolean editCellAt(int row, int column, EventObject e) {
//        onBeforeEditStart();
        if (!this.isCellSelected(row, column)) {
            return false;
        }
        boolean edit = super.editCellAt(row, column, e);
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
        
        if (!isCellBeingEditedWhileChanging) {
            col = convertColumnIndexToModel(col);
            row = convertRowIndexToModel(row);
            if (col < getColumnCount() - 1 && row < getRowCount()) {
                changeSelection(row, ++col, false, false);
            } else if (col == getColumnCount() - 1 && row < getRowCount() - 1) {
                changeSelection(++row, 0, false, false);
            }
        }
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
        if (!isCellBeingEditedWhileChanging) {
            col = convertColumnIndexToModel(col);
            row = convertRowIndexToModel(row);
            if (col < getColumnCount() - 1 && row < getRowCount()) {
                changeSelection(row, ++col, false, false);
            } else if (col == getColumnCount() - 1 && row < getRowCount() - 1) {
                changeSelection(++row, 0, false, false);
            }
        }
    }
    /**
     * * selects cell which is previouse  to currently selected cell
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
    public TableInteractionListner getTableInteractionListner() {
        return tableInteractionListner;
    }

    /**
     * @param tableInteractionListner the tableInteractionListner to set
     */
    public void setTableInteractionListner(TableInteractionListner tableInteractionListner) {
        this.tableInteractionListner = tableInteractionListner;
    }
}

class mce extends DefaultCellEditor {

    TableInteractionListner listner;
    JTextField txt;
    ModelEditableTable table;

    public mce(final JTextField textField) {
        super(textField);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                    table.selectNextCell();
//                    stopCellEditing();
                return;
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    table.selectPreviousCell();
//                    stopCellEditing();
                return;
                }
                //stop editig
                table.setValueAt(textField.getText(), table.getEditingRow(), table.getEditingColumn());
            }
        });
    }

    public mce(ModelEditableTable table) {
        this(createTextField());
        this.table = table;
        listner = ((ModelEditableTable) table).getTableInteractionListner();
    }

    static JTextField createTextField() {
        return new JTextField();
    }
  
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        txt = (JTextField) super.getTableCellEditorComponent(table, value, isSelected, row, column);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txt.selectAll();
                txt.requestFocus();
            }
        });
        return txt;
    }

    @Override
    public boolean stopCellEditing() {

        if (listner != null && !listner.validateCell()) {
            return false;
        }
        return super.stopCellEditing();
    }
}

class TableInteractionListner {

    JTable table;

    public TableInteractionListner(JTable table) {
        this.table = table;
    }

    public void onCellStartEdit() {
    }

    public void onCellEditCompleted() {
    }

    public void onCellBeforeSelection() {
    }

    public boolean validateCell() {

        if (table.getSelectedColumn() == 1 && table.getSelectedRow() == 1 && "43".equals(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()))) {
            return false;
        }
        return true;
    }

    public void moveSelection() {
    }
}