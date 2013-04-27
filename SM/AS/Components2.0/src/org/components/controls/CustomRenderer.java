/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.controls;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author d
 */
class CustomRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (isSelected && hasFocus) {
//                c.setBackground(Color.red);
                //set selected behaviour
            }
            // Text  Formatting
            return c;
        }
    }


    