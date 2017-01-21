/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.controls.editors;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import org.biz.app.ui.util.StringUtility;
import org.biz.util.ReflectionUtility;

/**
 *
 * @author d
 */
public class CellRenderer extends DefaultListCellRenderer {

    private JLabel lbl;
    String property;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public CellRenderer() {
        lbl = new JLabel();
        lbl.setPreferredSize(new Dimension(lbl.getWidth(), lbl.getHeight()));
    }


    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        lbl.setText((String) getText(value));
        return lbl;
    }

    public Object getText(Object obj) {
        if (StringUtility.isEmptyString(property)) {
            return obj;
        }
        return ReflectionUtility.getProperty(obj, property);
    }
}
