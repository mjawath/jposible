/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.controls.editors;

import com.components.custom.IComponent;
import com.components.custom.IContainer;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import org.components.controls.CTextField;
import org.components.controls.ModelEditableTable;



/**
 *
 * @author d
 */
public class mce extends AbstractCellEditor implements IComponent,TableCellEditor{

    JComponent component;
    TableInteractionListner listner;
    CTextField txt;
    ModelEditableTable table;    

        
    public mce(final CTextField textField) {
        super();
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    table.selectNextCell();//should call stop cell editing
//                    stopCellEditing();
                    return;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    table.selectPreviousCell();
//                    stopCellEditing();
                    return;
                }
//                table.setValueAt(textField.getText(), table.getEditingRow(), table.getEditingColumn());
            }
        });
        component=textField;
        
    }

    public mce(ModelEditableTable table) {
        this(createTextField());
        this.table = table;
        
        listner = ((ModelEditableTable) table).getTableInteractionListner();
    }

    static CTextField createTextField() {
        return new CTextField();
    }

    public Component getComponent(){
    return component;
    }
    
    @Override
    public boolean stopCellEditing() {

        if (listner != null && !listner.validateCell()) {
            return false;
        }
        return super.stopCellEditing();
    }

    @Override
    public void setContainer(IContainer con) {
    }

    @Override
    public IContainer getContainer() {
        return null;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return component;
    }
    
    
}
