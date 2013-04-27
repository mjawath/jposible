/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Table.java
 *
 * Created on May 6, 2010, 10:48:54 AM
 */
package org.components.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import org.components.parent.controls.PxTable;
import org.components.parent.controls.editors.TableActions;

/**
 *
 * @author nano
 */
public class CxTable extends PxTable {

    /**
     * Creates new form BeanForm
     */
    public CxTable() {
        initComponents();
        this.setDefaultRenderer(String.class, new CustomRenderer());
        this.setDefaultRenderer(Double.class, new CustomRenderer());
        this.setDefaultRenderer(Object.class, new CustomRenderer());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 250, 215));
        setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "col 1", "col 2", "col 3", "col 4"
            }
        ));
        setIntercellSpacing(new java.awt.Dimension(10, 5));
        setRowHeight(20);
        setSurrendersFocusOnKeystroke(true);
        getTableHeader().setReorderingAllowed(false);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
