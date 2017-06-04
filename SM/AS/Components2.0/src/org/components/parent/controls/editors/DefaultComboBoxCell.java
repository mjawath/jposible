/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.controls.editors;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import org.biz.app.ui.util.StringUtility;
import org.biz.util.ReflectionUtility;

/**
 *
 * @author jawa
 */
public class DefaultComboBoxCell extends CellRenderer{

    public DefaultComboBoxCell(String property) {
        this.property = property;
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel lbl = new JLabel();//super.getListCellRendererComponent();new defaultce
        
        lbl.setText((String) getText(value));
        return lbl;
    }

    public Object getText(Object obj) {
        if (obj instanceof String || obj instanceof Number ||StringUtility.isEmptyString(property)) {
            return obj;
        }
        return ReflectionUtility.getDynamicValue(obj, property);
    }
    
}
