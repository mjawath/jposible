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
import javax.swing.table.TableCellEditor;
import org.components.controls.CTextField;
import org.components.controls.ModelEditableTable;



/**
 *
 * @author d
 */
public class BaseCellEditor extends AbstractCellEditor implements IComponent,TableCellEditor{

    JComponent component;
    TableInteractionListner listner;
    CTextField txt;
    ModelEditableTable table;    

    public BaseCellEditor(){
        super();        
    }
    
    
    public BaseCellEditor(final CTextField textField) {
        super();
        component=textField;        
    }

    public BaseCellEditor(ModelEditableTable table) {
        this(createTextField());
        this.table = table;
        component.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                   BaseCellEditor.this. table.selectNextCell();//should call stop cell editing
//                    stopCellEditing();
                    return;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    BaseCellEditor.this.table.selectPreviousCell();
//                    stopCellEditing();
                    return;
                }
//                table.setValueAt(textField.getText(), table.getEditingRow(), table.getEditingColumn());
            }
        });
    
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
        if(value!=null){
        clear();
        }
        return component;
    }
    
    public void  clear(){
    }
     
    public boolean isCellValid() {
        return true;
    }      
    
     public void setTable(ModelEditableTable tbl) {
        this.table = tbl;
        init();
    } 
     
    public void init(){
        
    } 
}
