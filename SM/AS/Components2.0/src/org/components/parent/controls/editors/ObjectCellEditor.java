/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.controls.editors;

import com.components.custom.PagedPopUpPanel;
import com.components.custom.TextFieldWithPopUP;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import org.components.controls.CTextField;
import org.components.controls.ModelEditableTable;

/**
 *
 * @author d
 */
public class ObjectCellEditor<T> extends mce {

    PagedPopUpPanel<T> pagedPopup;
    TextFieldWithPopUP<T> fieldWithPopUP;

    public ObjectCellEditor(ModelEditableTable table) {
        super(table);
        fieldWithPopUP = new TextFieldWithPopUP<T>();
    }

    public void initPopup(List item, String[] properties, String[] titles, String selected) {
        pagedPopup = new PagedPopUpPanel<T>((CTextField) component) {
        };
        fieldWithPopUP.setObjectToTable(item);
        fieldWithPopUP.setPropertiesEL(properties);
        fieldWithPopUP.setTitle(titles);
        fieldWithPopUP.setSelectedProperty(selected);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value != null) {
            clear();
            fieldWithPopUP.setSelectedObject((T) value);
        }else{
        fieldWithPopUP.clear();
        }//set the value to compoenent
        return fieldWithPopUP;
    }

    @Override
    public Object getCellEditorValue() {
        System.out.println("editor  valide" + fieldWithPopUP.getSelectedObject());
        return fieldWithPopUP.getSelectedObject();
    }

    @Override
    public void clear() {
        //clear goes hear
        pagedPopup.setSelectedObject(null);
        pagedPopup.setSelectedID(null);
    }
}
