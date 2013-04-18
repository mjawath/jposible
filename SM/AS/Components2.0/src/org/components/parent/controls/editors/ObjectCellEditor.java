/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.controls.editors;

import com.components.custom.PagedPopUpPanel;
import java.util.List;
import org.components.controls.CTextField;
import org.components.controls.ModelEditableTable;

/**
 *
 * @author d
 */
public class ObjectCellEditor<T> extends CellEditor{

    public ObjectCellEditor(ModelEditableTable table) {
        super(table);
       }

    public void initPopup(List item,String [] properties,String [] titles,String selected){
        pagedPopup = new PagedPopUpPanel<T>((CTextField) component) {
        };        
        pagedPopup.setObjectToTable(item);
        pagedPopup.setPropertiesEL(properties);
        pagedPopup.setTitle(titles);
        pagedPopup.setSelectedProperty(selected);    
    }
    
    @Override
    public Object getCellEditorValue() {
        System.out.println("editor  valide"+pagedPopup.getSelectedObject());
        return  pagedPopup.getSelectedObject();
    }
    
    
    PagedPopUpPanel<T> pagedPopup;

}
