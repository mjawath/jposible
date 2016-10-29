/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.util;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;
import org.biz.util.ReflectionUtility;
import org.components.parent.controls.PTableColumn;
import org.components.parent.controls.PxTable;
import org.components.parent.controls.editors.BaseCellEditor;
import org.components.parent.controls.editors.TableInteractionListner;

/**
 *
 * @author nano
 */
public class TableUtil {

    public final static String newRowID = "cxMxCy%%76";

    public static String getNewRowId() {
        return System.currentTimeMillis() + "-" + Math.ceil(Math.random() * 10);
    }

    public static void createTableModel(JTable jTable, String[] columns, final Class[] columntypes) {

        DefaultTableModel dtm = new DefaultTableModel(
                new Object[][]{}, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
            Class[] types = columntypes;

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            
        };

        jTable.setModel(dtm);

    }

    
      public static void createTableModel(JTable jTable,  List<PTableColumn> columntypes) {

          
          String columns [] = new String[columntypes.size()];
          final Class columnCls [] = new Class[columntypes.size()];
          
          for (int i = 0; i < columns.length; i++) {
               columns[i] = columntypes.get(i).getTitle();              
               columnCls[i] = columntypes.get(i).getClassType();
          }
          
          DefaultTableModel dtm = new DefaultTableModel(
                new Object[][]{}, columns) {

            Class[] types = columnCls;

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }            
            
        };

        jTable.setModel(dtm);
        final NumberRenderer numberRenderer = new NumberRenderer();
        jTable.setDefaultRenderer(Double.class, numberRenderer);        
        jTable.setDefaultRenderer(Number.class, numberRenderer);        

    }
      
      
     private static class NumberRenderer extends DefaultTableCellRenderer.UIResource {

        NumberFormat formatter = NumberFormat.getNumberInstance();

        public NumberRenderer() {
            super();
            setHorizontalAlignment(SwingConstants.RIGHT);
            formatter.setGroupingUsed(true);
            formatter.setMinimumFractionDigits(2);
        }

        public void setValue(Object value) {
            if (formatter == null) {
                formatter = NumberFormat.getNumberInstance();
            }
            setText((value == null) ? "" : formatter.format(value));
            
        }
    }

    public static void createTableModel(JTable jTable, String[] columns) {

        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(columns);
        jTable.setModel(dtm);

    }

    public static void createTableModel(JTable jTable, String[] columns, final Class[] columntypes, final boolean editable) {

        DefaultTableModel dtm = new DefaultTableModel(
                new Object[][]{}, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return editable;
            }
            Class[] types = columntypes;

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        jTable.setModel(dtm);

    }

    public static DefaultTableModel getdtm(JTable jTable) {
        return (DefaultTableModel) jTable.getModel();
    }
    /*
     * add new row to o index for table
     */

    public static void addnewrow(JTable jTable, Vector row) {

        getdtm(jTable).insertRow(0, row);

    }

    public static void addnewrow(JTable jTable) {

        getdtm(jTable).addRow(new Object[]{});

    }

    public static void hideColumn(JTable jt, String st) {

        jt.getColumn(st).setWidth(0);
        jt.getColumn(st).setMinWidth(0);
        jt.getColumn(st).setMaxWidth(0);
    }
    
    public static void hideColumn(JTable jt, int index) {

        jt.getColumnModel().getColumn(index).setWidth(0);
        jt.getColumnModel().getColumn(index).setMinWidth(0);
        jt.getColumnModel().getColumn(index).setMaxWidth(0);
    }
    /*
     * add new row for table
     */

    public static void addrow(JTable jTable, Vector row) {
        jTable.scrollRectToVisible(jTable.getCellRect(jTable.getRowCount(), 0, true));        
        getdtm(jTable).addRow(row);
    }

    public static void addrow(JTable jTable, Object[] row) {
        getdtm(jTable).addRow(row);
        jTable.scrollRectToVisible(jTable.getCellRect(jTable.getRowCount(), 0, true));
        jTable.scrollRectToVisible(jTable.getCellRect(jTable.getRowCount(), 0, true));
    }
//First row will be visble

    public static void addrowSR(JTable jTable, Object[] row) {
//    jTable.scrollRectToVisible(jTable.getCellRect(jTable.getRowCount() - 1, 0, true));
        getdtm(jTable).addRow(row);

    }

    /*
     * insert a new row into specific index
     * 
     */
    public static void insertrow(JTable jTable, Vector row, int point) {
        point = jTable.convertRowIndexToModel(point);
        getdtm(jTable).insertRow(point, row);

    }

    public static void insertrow(JTable jTable, Object[] row, int point) {
        point = jTable.convertRowIndexToModel(point);
        getdtm(jTable).insertRow(point, row);

    }
    /*
     * remove row
     */

    public static void replacerow(JTable jTable, Vector row, int point) {

        jTable.getColumnModel().getColumns().nextElement().getIdentifier();

        point = jTable.convertRowIndexToModel(point);
        DefaultTableModel dt = getdtm(jTable);

//        dt.removeRow(point);
//        dt.insertRow(point, row);
        int col = dt.getColumnCount();
        for (int i = 0; i < col; i++) {
            dt.setValueAt(row.get(i), point, i);
        }

        /*   getdtm(jTable).setDataVector(row, jTable.getd(point);// todo data updation without selection change
        getdtm(jTable).insertRow(point, row); // temp solution:  loop through the columns to set the values
         */
    }

    public static void replacerow(JTable jTable, Object[] row, int point) {
        DefaultTableModel dtm= getdtm(jTable);
        point = jTable.convertRowIndexToModel(point);
        for (int i = 0; i < row.length; i++) {
            Object object = row[i];
            dtm.setValueAt(object, point, i);
        }
        

    }

    public static void replacerowValues(JTable jTable, Object[] row, int point) {
        point = jTable.convertRowIndexToModel(point);
        for (int i = 0; i < row.length; i++) {
            Object va = row[i];
            getdtm(jTable).setValueAt(va, point, i);
        }


    }

    public static void removerow(JTable jTable, int point) {
        point = jTable.convertRowIndexToModel(point);
        getdtm(jTable).removeRow(point);

    }
    
    public static void moveRow(JTable jTable, int point, int pointDest) {
        point = jTable.convertRowIndexToModel(point);
        if ((point + pointDest) > -1 && (point + pointDest) < getdtm(jTable).getRowCount()) {
            TableCellEditor tce = jTable.getCellEditor();
            int editingcol = jTable.getEditingColumn();
            int editingrow = jTable.getEditingRow();
            if (tce != null) {
                tce.cancelCellEditing();
            }
            getdtm(jTable).moveRow(point, point, point + pointDest);
            jTable.changeSelection(point+pointDest, editingcol>-1?editingcol:jTable.getSelectedColumn(), true, false);
        }
    }

    public static void findRemoverow(JTable jTable, String code, int point) {
        Vector data = TableUtil.getDataVector(jTable);
        point = jTable.convertRowIndexToModel(point);
        for (int i = 0; i < data.size(); i++) {
            Vector r = (Vector) data.elementAt(i);
            Object ob = r.get(0);
            if (ob.equals(code)) {
                TableUtil.getdtm(jTable).removeRow(i);
                break;
            }
        }

    }

    /*
     * crates new table model
     */
    public static void createTableModel(JTable jTable, Vector data, Vector columns, final Class[] columntypes) {


        DefaultTableModel dtm = new DefaultTableModel(
                data, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            Class[] types = columntypes;

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        jTable.setModel(dtm);
    }
    /*
     * fill data by specifying data vector
     */

    public static void filldata(JTable jTable, Vector data) {
        cleardata(jTable);
        for (Object object : data) {
            getdtm(jTable).addRow((Vector) object);
        }

    }

    public static void filldata(JTable jTable, Object[] data) {
        cleardata(jTable);
        for (Object object : data) {
            getdtm(jTable).addRow((Object[]) object);
        }

    }
    
    public static void filldataObj(JTable jTable, Object[][] data) {
        cleardata(jTable);
        for (Object[] object : data) {
            getdtm(jTable).addRow(object);
        }

    }

    /*
     * get tables datavector..
     */
    public static Vector getDataVector(JTable jTable) {

        return getdtm(jTable).getDataVector();
    }

    public static Object[][] getDataObject(JTable jTable) {

        Vector rows = getdtm(jTable).getDataVector();
        Object data[][] = new Object[rows.size()][];

        int x = 0;
        for (Object row : rows) {
            data[x++] = ((Vector) row).toArray();
        }
        return data;
//           int x=0;
//
//        for (Object[] row : data) {
//            row=getdtm(jTable).getDataVector().toArray();
//            x++;
//        }
//            return data;
    }

//     public static  Object getfilteredmodelvalueat(JTable jt, int srow,int scol){
//
////         return jt.getRowSorter().getValueAt(jt.convertRowIndexToModel( srow),jt.convertColumnIndexToModel(scol));
//     }
    public static void cleardata(JTable jTable) {
        jTable.clearSelection();
        DefaultTableModel dtm = getdtm(jTable);
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(dtm.getRowCount() - 1);
        }
    }
//give Views column index and get the models column index

    public static int selectedViewToModelColumn(JTable jt) {
        return jt.convertColumnIndexToModel(jt.getSelectedColumn());
    }
//give models column index and get the views column index

    public static int getViewColumnIndex(JTable jt, int modelcolumnindex) {
        return jt.convertColumnIndexToView(modelcolumnindex);
    }

    public static Object getModelsValueAt(JTable jt, int modelrowindex, int modelcolindex) {
// return jt.getValueAt(jt.convertRowIndexToView(modelrowindex), jt.convertColumnIndexToView(modelcolindex));
//        //above return stracnge values because of orginal model sorterd model
        return jt.getValueAt(modelrowindex, jt.convertColumnIndexToView(modelcolindex));
    }

    public static Object getSelectedModelsValueAt(JTable jt, int modelcolindex) {
        int sr = jt.getSelectedRow();
        if (sr < 0) {
            return null;
        }
        return jt.getValueAt(sr, jt.convertColumnIndexToView(modelcolindex));
    }

    /**
     * selected column selected row
     * @param jt
     * @return 
     */
    public static Object getSelectedValue(JTable jt) {
        if (jt.getSelectedRow() > -1) {
            return jt.getValueAt(jt.getSelectedRow(), jt.getSelectedColumn());
        }
        return null;
    }
    //get selected column return specified column value of selected row  if  null  retruns null

    public static Object getSelectedValue(JTable jt, int column) {
        if (jt.getSelectedRow() > -1) {
            if (jt.getEditingColumn() == column) {
                TableCellEditor ce = jt.getCellEditor();
                try {

                    Component jc = ((BaseCellEditor) ce).getComponent();
                    if (jc instanceof JTextComponent) {
                        return ((JTextComponent) jc).getText();

                    }
                    if (jc instanceof JComboBox) {
                        return ((JComboBox) jc).getSelectedItem();

                    }
                } catch (Exception e) {
                    return null;
                }
                return null;
            }
            return jt.getValueAt(jt.getSelectedRow(), column);
        }
        return null;

    }

    //get selected column return specified column value of selected row  if  null  retruns null
    public static Object getValueat(JTable jt, int row, int column) {
        if (row > -1) {
            if (jt.getEditingColumn() == column) {
                TableCellEditor ce = jt.getCellEditor();
                try {

                    Component jc = ((BaseCellEditor) ce).getComponent();
                    if (jc instanceof JTextComponent) {
                        return ((JTextComponent) jc).getText();

                    }
                    if (jc instanceof JComboBox) {
                        return ((JComboBox) jc).getSelectedItem();

                    }
                } catch (Exception e) {
                    return null;
                }
                return null;
            }
            return jt.getValueAt(row, column);
        }
        return null;

    }

    //get selected column return empty string if null
    public static String getSelectedValueE(JTable jt, int column) {
        if (jt.getSelectedRow() > -1) {
            Object ob = getSelectedValue(jt, column);
            if (ob == null) {
                return "";
            }
            return ob.toString();
        }
        return "";

    }

    public static void selectNextRow(JTable jt, KeyEvent e) {
        int x = jt.getSelectedRow();
        int r = jt.getRowCount();
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (x + 1 < r) {

                jt.getSelectionModel().setSelectionInterval(x + 1, x + 1);
                return;
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (x - 1 >= 0 && x - 1 < r) {
                jt.getSelectionModel().setSelectionInterval(x - 1, x - 1);
            }
        }



    }

    public static void selectNextRow(JTable jt, int e) {
        int x = jt.getSelectedRow();
        int r = jt.getRowCount();
        if (e == KeyEvent.VK_DOWN) {
            if (x + 1 < r) {
                jt.changeSelection(x + 1, x + 1, false, false);
//                jt.getSelectionModel().setSelectionInterval(x + 1, x + 1);
                return;
            }

        }
        if (e == KeyEvent.VK_UP) {
            if (x - 1 >= 0 && x - 1 < r) {
//                jt.getSelectionModel().setSelectionInterval(x - 1, x - 1);
                jt.changeSelection(x - 1, x - 1, false, false);
            }
        }



    }

    public static void setModelValueat(JTable jt, Object value, int modelrowindex, int modelcolindex) {
        jt.setValueAt(value, jt.convertRowIndexToView(modelrowindex), jt.convertColumnIndexToView(modelcolindex));
    }

    public static boolean isSelectedRowNewRow(JTable jt) {
        int x = jt.getSelectedRow();
        if (x == -1) {
            return true;
        } else {
            Object ob = jt.getValueAt(x, 0);
            if (ob == null) {
                return false;
            }
            if (newRowID.equals(ob)) {
                return true;
            } else {
                return false;
            }
        }

    }

    public static Object rowID(JTable jt) {
        int x = jt.getSelectedRow();
        if (x == -1) {
            return null;
        } else {
            Object ob = jt.getValueAt(x, 0);
            if (newRowID.equals(ob)) {
                return null;
            } else {
                return ob;
            }
        }

    }

    public static void setColumnEditor(JTable tb, int col, TableCellEditor ed) {
        tb.getColumnModel().getColumn(col).setCellEditor(ed);

    }

    /**
     * EL implementation of the table -we can call it the model table
     *  //table has properties
        //loop the properties 
        //get the valude 
        // create the row 
        //add the row to table
       
     */
    public static void addModelToTable(Object obj, PxTable table) {
        
        addrow(table, setRow(table, obj));
    }
    
    public static void setCollectionToTable(List listobj, PxTable table) {
        Vector data = new Vector();

        for (Object obj : listobj) {
            Vector row = new Vector();

            String[] prop = table.getPropertiesEL();
            if (prop == null) {
                return;
            }
            row.add(obj);
            for (int i = 1; i < prop.length; i++) {
                String var = prop[i];
                Object ob = ReflectionUtility.getProperty(obj, var);
                row.add(ob);
            }
            data.add(row);
        }

        Enumeration<TableColumn> tc = ((PxTable) table).getColumnModel().getColumns();
        Vector al = new Vector();
        while (tc.hasMoreElements()) {
            al.add(tc.nextElement().getIdentifier());
        }
        getdtm(table).setDataVector(data, al);
    }
    
    public static void replaceModel(PxTable table, Object obj, int point) {

       replacerow(table, setRow(table, obj), point);

    }
    
    private static Object[] setRow(PxTable table,Object obj){       
        TableInteractionListner prop = table.getTableInteractionListner();
        if (prop == null) {
            return new Object[0];
        }
        return prop.getTableData(obj);               
    } 

    public static void replaceSelectedModel(PxTable table, Object obj) {
        if (table.getSelectedRow() > -1) {
            replaceModel(table, obj, table.getSelectedRow());
        }
    }

    public static Object getSelectedID(PxTable jt) {

        int x = jt.getSelectedRow();
        if (x == -1) {
            return null;
        } else {
            Object ob = jt.getValueAt(x, 0);
            if (ob == null || newRowID.equals(ob)) {
                return null;
            } else {
                return ob;
            }
        }


    }

    /**
     * get the selected tables rows object or returns null
     *
     * @param tbl
     * @return
     */
    public static <T> T getSelectedTableObject(PxTable tbl) {
        int  sr=tbl.getSelectedRow();
        if(sr<0)return null;
//        Object ob = TableUtil.getSelectedID(tbl);
        Object ob=getValueat(tbl,sr ,0);
        return (T)ob;//(Object) ReflectionUtility.findByID(tbl.getModelCollection(), ob);
    }

    public static <T> T getSelectedTableObject(PxTable tbl, Class cls) {
        Object ob = TableUtil.getSelectedID(tbl);
        return (T) generateObject(tbl, cls);
//       return (Object)ReflectionUtility.findByID(tbl.getModelCollection(), ob);
    }

    public static Object generateObject(PxTable tbl, Class cls) {
        try {
            Object obj = cls.newInstance();
            int column = 0;
            for (String prop : tbl.getPropertiesEL()) {
                Object row = getdtm(tbl).getValueAt(tbl.getSelectedRow(), column);

                ReflectionUtility.setProperty(obj, prop, row);
                column++;
            }
            return obj;
        } catch (Exception ex) {
        }
        return null;
    }

    // dynamic table model modification
    public static void setColumn(JTable tb) {
//    tb.getColumnModel().set
    }
}
/*
todo addModelToTable()
 * should be able to parse a methods to evaluate
 * a custom property in the level of object -property as a method call
 *
 */
