/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.controls.editors;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import org.biz.util.ReflectionUtility;

/**
 *
 * @author d
 */
public class CellRenderer implements ListCellRenderer {

    String property;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel lbl = new JLabel();
        lbl.setText((String) getText(value));
        return lbl;
    }
    
    public Object getText(Object obj) {
        return ReflectionUtility.getProperty(obj, property);
    }
}
