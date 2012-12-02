/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.controls.editors;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import org.biz.app.ui.util.TableUtil;
import org.components.controls.TableEditable;

/**
 *
 * @author nnjj
 */
public class TableActions {

    JTable tbl;
    public static final int nextcol = -999;
    public static final int newrow = -998;
    public static final int specrow = -997;
    public static final int currow = -996;
    public static final int nextrow = -995;
    Map<Integer, TableColumnAction> tableactions;
    Integer[] unSelectableColumns;
    int actioncol = -1;

    public void setActioncol(int actioncol) {
        this.actioncol = actioncol;
    }

    public TableActions() {
        tableactions = new HashMap<Integer, TableColumnAction>();
        unSelectableColumns = new Integer[15];

    }

    public TableActions(JTable tbl) {
        this.tbl = tbl;
        tableactions = new HashMap<Integer, TableColumnAction>();
        unSelectableColumns = new Integer[15];

    }

    public TableActions(JTable tbl, Map<Integer, TableColumnAction> tableactions) {
        this.tbl = tbl;
        this.tableactions = tableactions;
    }

    public Map<Integer, TableColumnAction> getTableColumnActions() {
        return tableactions;
    }

    public void addColumnAction(int x, TableColumnAction action) {
        action.setTbl(tbl);
        tableactions.put(x, action);
    }

    public JTable getTbl() {
        return tbl;
    }

    public void setTbl(JTable tbl) {
        this.tbl = tbl;
    }

    public void selectionAction() {
        int selcol = tbl.getSelectedColumn();
        int selrow = tbl.getSelectedRow();
        int colcount = tbl.getColumnCount();
        int rowcount = tbl.getRowCount();

        TableColumnAction columnAction = tableactions.get(selcol);
        if (columnAction != null) {

            int r = columnAction.actionPerformed();
            if (r == newrow) {
                if (((rowcount - 1) == selrow)) { //false normal selection           
                    TableUtil.addnewrow(tbl);
                    ((TableEditable) tbl).getTableSelection().newRowAdded();
                    tbl.changeSelection(selrow + 1, 1, false, false);
                    return;
                }

                if (((rowcount - 1) > selrow)) {
                    if (TableUtil.getSelectedValue(tbl, 0) == null) { //false normal selection           
                        TableUtil.addnewrow(tbl);
                        ((TableEditable) tbl).getTableSelection().newRowAdded();
                        tbl.changeSelection(rowcount, 1, false, false);
                        return;
                    }
                    tbl.changeSelection(selrow + 1, 1, false, false);
                    return;
                }

            }
//                if (r == nextrow) {
//                    tbl.changeSelection(selrow + 1, 1, false, false
//                });
//                    return;
            if (r == nextcol) {
                selcol = (colcount - 1) == selcol ? selcol : ++selcol;
//                if (!isUnSelectableColumn(selcol)) {
//                    tbl.changeSelection(selrow, selcol, false, false);
//                } else {
                //todo if column is last or action  move to next row and first row33                        

//                    tbl.changeSelection(selrow, selcol , false, false);
//                }

                int nextcolx = selcol + 1;
                while (true) {
                    if (!isUnSelectableColumn(nextcolx) && nextcolx < (colcount - 1)) {
                        tbl.changeSelection(selrow, nextcolx, false, false);
                        return;
                    }
                    nextcolx++;
                }

            }
            if (r >= 0 && r <= (rowcount - 1)) {
                tbl.changeSelection(selrow, r, false, false);
                return;
            }
            return;


            //Action will return true if it need new row or 
        }


//        if (((rowcount - 1) == selrow && selcol == (colcount - 1))) { //false normal selection           
//            TableUtil.addrow(tbl, new Object[]{});
//            ((TableEditable)tbl).getTableSelection().newRowAdded();
//            tbl.changeSelection(selrow + 1, 1, false, false);
//            return;
//        }
        if (selcol == (colcount - 1) && (selrow < rowcount - 1)) {
            return;
        }
        if (((rowcount - 1) > selrow) && selcol == (colcount - 1)) {
            tbl.changeSelection(selrow + 1, 1, false, false);
            return;
        }

        if (selcol < (colcount - 1) && (selrow <= rowcount - 1)) {

            int nextcolx = selcol + 1;
            while (true) {
                if (!isUnSelectableColumn(nextcolx) && nextcolx < (colcount - 1)) {
                    tbl.changeSelection(selrow, nextcolx, false, false);
                    return;
                }

                nextcolx++;
                if (nextcolx >= (colcount - 1)) {
                    return;
                }
            }


        }

        if (selcol < (colcount - 1) && (selrow == rowcount - 1)) {

            int nextcolx = selcol + 1;
            while (true) {
                if (!isUnSelectableColumn(nextcolx) && nextcolx < (colcount - 1)) {
                    tbl.changeSelection(selrow, nextcolx, false, false);
                    return;
                }
                nextcolx++;
            }


        }

    }

    public void newRowAdded() {
    }

    public boolean rowValid() {
        return true;
    }

    public void commitChanges(int sr) {
    }

    public Integer[] getUnSelectableColumns() {
        return unSelectableColumns;
    }

    public void setUnSelectableColumns(Integer[] unSelectableColumns) {
        this.unSelectableColumns = unSelectableColumns;
    }

    public boolean isUnSelectableColumn(int x) {
        for (Integer integer : unSelectableColumns) {
            if (integer != null && integer == x) {
                return true;
            }

        }
        return false;

    }
}
