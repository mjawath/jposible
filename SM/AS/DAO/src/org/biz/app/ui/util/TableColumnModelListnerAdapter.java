/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.app.ui.util;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;

/**
 *
 * @author mjawath
 */
public abstract class TableColumnModelListnerAdapter implements TableColumnModelListener{

    public void columnAdded(TableColumnModelEvent e) {
    }

    public void columnRemoved(TableColumnModelEvent e) {
    }

    public void columnMoved(TableColumnModelEvent e) {
    }

    public void columnMarginChanged(ChangeEvent e) {
    }

    public void columnSelectionChanged(ListSelectionEvent e) {
    }

}
