/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Table.java
 *
 * Created on May 6, 2010, 10:48:54 AM
 */
package org.components.parent.controls;

import com.components.custom.IComponent;
import com.components.custom.IContainer;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.app.ui.util.TableUtil;
import org.biz.entity.BusObj;
import org.biz.util.ReflectionUtility;
import org.components.parent.controls.editors.CustomRenderer;
import org.components.parent.controls.editors.TableInteractionListner;
import org.util.PropertyUtil;

/**
 *
 * @author nano
 */
public class PxTable<T> extends JTable implements IComponent {

    protected IContainer container;
    private Class modelClass; 
    private String[] propertiesEL;
    private List modelCollection;    
    public static final String newRowId_cons = "#NewRow#";
    private int newRowId_SEED = -10000001;
    protected TableInteractionListner tableInteractionListner;
    private List<PTableColumn>  columnDefs;
    private boolean editable;

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    
    /**
     * Creates new form BeanForm
     */
    public PxTable() {
        initComponents();
        
//        this.setDefaultRenderer(String.class,new CustomRenderer());
//        this.setDefaultRenderer(Double.class,new CustomRenderer());
//        this.setDefaultRenderer(Object.class,new CustomRenderer());
        UIManager.put("JTable.autoStartsEdit", Boolean.TRUE);
        modelCollection = new ArrayList();
        getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //get selected object
                // set to detail panel  
                if (!e.getValueIsAdjusting()) {
                    return;
                }
                int row = getSelectedRow();
                Object obj = getSelectedObject();
//                if(tableInteractionListner==null)return;
//                tableInteractionListner.selectionChanged(obj);
                System.out.println("selection changes"+row);
            }
        });
        this.setDefaultRenderer(String.class, new CustomRenderer());
        this.setDefaultRenderer(Double.class, new CustomRenderer());
        this.setDefaultRenderer(Object.class, new CustomRenderer());
        this.setDefaultRenderer(Date.class, new DateRendererX());
        
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (tableInteractionListner != null) {
                        tableInteractionListner.onDoubleClicked(getSelectedObject());
                    }
                }
            }

        });
    }
    
    

    
    
    
    
   public void init(Class tableType, Class [] classType, String[] title){
        modelClass=tableType;
        setColumnTypes(classType, title);
        //Tables first column which holds the model object will be not visible
        TableUtil.hideColumn(this, 0);      
        
    }

    public void init(Class tableType, List<PTableColumn>  columnDefs){
        modelClass=tableType;
        this.columnDefs = columnDefs;
        setColumnTypes(columnDefs);
        //Tables first column which holds the model object will be not visible
        TableUtil.hideColumn(this, 0);      
        
    }
   
    /**
     *
     */
    public void addNewItemToLast() {
        Object obj = ReflectionUtility.getDynamicInstance(modelClass);
        addNewItemToLast(obj);
    }
    
    public void addNewItemToLast(Object obj) {
        int insertionPoint = getRowCount();        
        TableUtil.addModelToTable(obj, this);
        changeSelection(insertionPoint);
    }    
    
    
   public List getCollection(){
       List data= new ArrayList();
       
       for (int i = 0; i < getRowCount(); i++) {
           Object rowLine = getValueAt(i, 0);
           data.add(rowLine);
       }
       return data;
   } 
    
    
    
   
    static class DateRendererX extends DefaultTableCellRenderer.UIResource {
        DateFormat formatter=new SimpleDateFormat("HH:mm:ss dd-MM-yyyy ");
        public DateRendererX() { super(); }

        public void setValue(Object value) {
            if (formatter==null) {
                formatter = DateFormat.getDateInstance();
            }
            setText((value == null) ? "" : formatter.format(value));
        }
    }
    
    

    @Override
    public void setContainer(IContainer con) {
        this.container = con;
    }

    public IContainer getContainer() {
        return container;
    }

    public String[] getPropertiesEL() {
        return propertiesEL;
    }

    public void setPropertiesEL(String[] propertiesEL) {
        String [] prop = propertiesEL;
        this.propertiesEL =new String[propertiesEL.length+1];
        this.propertiesEL[0]="obj";
        int x=1;
        for (String str : prop) {
            this.propertiesEL[x++]=str;
        }

//     TableUtil.createTableModel(this, propertiesEL);   
    }

    public void setColumnHeader(String[] title) {
        if( propertiesEL==null)return;
        String [] tit=new String[(title.length+1)];
        tit[0]="objecyOF";
        int c=1;
        for (String str : title) {
            tit[c++]=str;
        }
        Class [] cls= ReflectionUtility.getFieldTypesForAttributesForTable(getModelClass(), getPropertiesEL());        
        TableUtil.createTableModel(this, tit,cls);
    }
    
    public void setColumnTypes(Class [] classType, String[] title) {
//        if( propertiesEL==null)return;
        String [] newTitle=new String[(title.length+1)];
        newTitle[0]="objecyOF";
        int c=1;
        for (String str : title) {
            newTitle[c++]=str;
        }        
        c=1;
        Class[] newclassTypes=new Class[classType.length+1];
        newclassTypes[0]=modelClass;
        for (Class  str : classType) {
            newclassTypes[c++] = str;
        }

        TableUtil.createTableModel(this, newTitle,newclassTypes);
        if(PropertyUtil.getApplicationmod()== 1){
        
        }
    }
    
    public void setColumnTypes(List<PTableColumn>  classType) {                
        PTableColumn idCol = new PTableColumn(modelClass, "objectIDColumn", null);
        classType.add(0, idCol);
        TableUtil.createTableModel(this, classType);        
    }

    
    public Class getModelClass() {
        return modelClass;
    }

    public void setModelClass(Class modelClass) {
        this.modelClass = modelClass;
    }

    public void initTable(Class modelClass,String[] propertiesEL,String[] title){
        setModelClass(modelClass);
        setPropertiesEL(propertiesEL);
        setColumnHeader(title);        
    }
    
    public List<T> getModelCollection() {
        //should get the collection from the tables
        //last row is dummy so remove it
        int si=modelCollection.size();
        ArrayList ar=new ArrayList(modelCollection);
        if(si>0)ar.remove(si-1);
        return ar;
    }

    public void setModelCollection(List<T> modelCollection) {        
        clear();
//        this.modelCollection=modelCollection;
        //for each item set values to table model
        if (modelCollection == null) {
            return;
        }
//        Collections.copy(this.modelCollection, modelCollection);
        this.modelCollection.addAll(modelCollection);
        
        if(tableInteractionListner!=null){
          
            Object [][] data=new Object[modelCollection.size()][];
            for (int x=0;x< modelCollection.size() ;x++) {
               Object [] objrow= tableInteractionListner.getTableData(modelCollection.get(x));
               data[x]=objrow;
            }
            TableUtil.filldata(this, data);
          return;
        }
//        for (Object obj : modelCollection) {
//            addModelToTable(obj);//slow method , 
//        }
    }

    public void clearUI(){
       TableUtil.cleardata(this);       
    }
   
    public void clear() {
        if (modelCollection != null) {
            TableUtil.cleardata(this);
            modelCollection.clear();
        }else{
        modelCollection=new ArrayList();
        }

    }
/**
 * deprecated
 * @param obj 
 */
    public void addModelToTable(Object obj) {

        //obj should hv an unique id 
        Object bb = ReflectionUtility.getProperty(obj, "id");
//        Object val=ReflectionUtility.getValue(getSelectedObject(), "id");
        if (bb == null) {
            ReflectionUtility.setValue(obj, "id", newRowId_SEED++ + newRowId_cons);
        }//sould check existing id , objects
        modelCollection.add(obj);
        TableUtil.addModelToTable(obj, this);
    }
    
    
    /**
     * deprecated
     */
    public void setModelCollectionToTableNew(List list) {
        setModelCollectionToTable(list);
        addNewToLast();
    }
    
    /**
     * deprecated
     */
    public void setModelCollectionToTable(List list) {
        clear();
        modelCollection = new ArrayList();
        if (list == null || list.isEmpty()) {
            return;
        }
        for (Object row : list) {
            modelCollection.add(row);
            TableUtil.addModelToTable(row, this);
        }
    }
    
     /**
     * deprecated
     */ 
    public void setCollectionToTable(List list){
        TableUtil.setCollectionToTable(list, this);
    }

    public void refreshModel() {
        TableUtil.cleardata(this);
        if (modelCollection == null || modelCollection.isEmpty()) {
            return;
        }
        for (Object row : modelCollection) {
            TableUtil.addModelToTable(row, this);
        }


    }

    public void replaceSelectedModel(Object obj) {
        TableUtil.replaceSelectedModel(this, obj);
    }

    public void replaceModelAndSelectLastRow(Object obj) {
       replaceSelectedModel(obj);
       
        int x = modelCollection.indexOf(getSelectedObject());
        modelCollection.set(x, obj);
        int row=getSelectedRow();
        int rowCount = getRowCount()-1;
        if(row==rowCount)
        addNewToLast();
        else selectLast();//move to last row
       
    }
    
    public String getSelectedModelID() {
        Object ob = getSelectedObject();
        if (ob != null && ob instanceof BusObj) {
            return String.valueOf(((BusObj) ob).getId());
        }
        return null;
    }
    
    /**
     * deprecated
     */
    /**
     * if row is not selected adds new model otherwise update the selected row
     * object
     */
    public void addNewOrModifySelectedRow(Object obj) {
        Object lit = getSelectedObject();
        if (lit == null) {
            addModelToTable(obj);
            addNewToLast();
        } else {
            //pid of seleced 
            Object key=ReflectionUtility.getProperty(lit, "id");
            if(key==null){
                MessageBoxes.errormsg(this, "No key set for Selected Row of table in "
                    + "PXTABLE > addNewOrModifySelectedRow", "Error"); 
            return;
            }
            ReflectionUtility.setProperty(obj, "id", key);
            replaceModelAndSelectLastRow(obj);            
        }
    }
    
    public void x(Object objx) {
//        int x=0;
//        for (Object obj : modelCollection) {
//            if (objx.equals(((BusObj) obj).getId())) {
//            
//                break;
//            }
//            x++;
//        }
        int x=modelCollection.indexOf(getSelectedObject());
        modelCollection.set(x, objx);
    }
    
        /**
     * deprecated
     */
    public void addNewToLast(){
        int insertionPoint=getRowCount();
        addModelToTable(ReflectionUtility.getDynamicInstance(modelClass));
        changeSelection(insertionPoint);
    }

        public void removeSelectedRow() {
        if (modelCollection == null) {
            return;
        }
        int sr = getSelectedRow();
        if (sr < 0) {
            return;
        }
        Object sele = this.getSelectedObject();
        Iterator it = modelCollection.iterator();

        Object obj1 = ReflectionUtility.getValue(sele, "id");
        for (; it.hasNext();) {
            Object obj2 = ReflectionUtility.getValue(it.next(), "id");
            if (obj1 != null && obj1.equals(obj2)) {
                it.remove();
                TableUtil.removerow(this, sr);
                return;
            }
        }/*row remove logic*/
//        TableUtil.removerow(this, sr);

    }
        
    public void removeSelectedObject(){
        int selectedRow = getSelectedRow();
        if(selectedRow>=0){
            TableUtil.removerow(this, selectedRow);
        }
    
    }

    public void copySelectedElement() {
           Object object= getSelectedObject();
           if(object!=null){
           //object.clone
           }
    }
    
    public T getSelectedObject() {
        //loop the collection//TODO : Set collection??TODO: all the way exception ...
        /// have to modify the setting collection behaviour
        return TableUtil.getSelectedTableObject(this);

    }

    public <T> T getSelectedObject(Class<T> cls) {
        //loop the collection//TODO : Set collection??TODO: all the way exception ...
        /// have to modify the setting collection behaviour
        return (T) TableUtil.getSelectedTableObject(this,cls);

    }
    
    public void addrow(Object[] row) {
        TableUtil.addrow(this, row);
    }

    public Object[][] getTableRows() {
        return TableUtil.getDataObject(this);
    }
    
    public void moveSelectedLineUp() {
        int sr=getSelectedRow();
        TableUtil.moveRow(this,sr, -1);
    }
 
    public void moveSelectedLineDown() {
        int sr = getSelectedRow();
        TableUtil.moveRow(this, sr, 1);
        
    }
    
    public void changeSelection(int rowIndex) {
        changeSelection(rowIndex,0,true , false);        
        changeSelection(rowIndex,getColumnCount()-1,false,true);
    }

    @Override
    public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
//        if (tableInteractionListner == null) return;
        
        if (tableInteractionListner != null && !tableInteractionListner.onBeforeRowSelectionChange()) {
        return;
        }
        super.changeSelection(rowIndex, columnIndex, toggle, extend);
//                if(rowIndex>=getRowCount())return;

        
        if(tableInteractionListner!=null)tableInteractionListner.selectionChanged(getSelectedObject());

    }
    
    public void selectNextRow() {
        int row = getSelectedRow();
        if (row == getRowCount() - 1) {
            return;
        }
        changeSelection(row + 1);
    }

    public void selectPreRow() {
        int row = getSelectedRow();
        if (row == 0) {
            return;
        }
        changeSelection(row - 1);
    }
    
    public void selectLast(){
    changeSelection(getRowCount()-1);
    } 
    
    
    public void selectFirst(){
        if(getRowCount()>0)
            changeSelection(0);
    } 

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        System.out.println("setting value at"+aValue+" "+row+" "+column);
        super.setValueAt(aValue, row, column);
    }

    @Override
    public Component prepareEditor(TableCellEditor editor, int row, int column) {
       Component com= super.prepareEditor(editor, row, column);
        return com;
    }

    public TableInteractionListner getTableInteractionListner() {
        return tableInteractionListner;
    }

    public void setTableInteractionListner(TableInteractionListner tableInteractionListner) {
        this.tableInteractionListner = tableInteractionListner;
        tableInteractionListner.setTable(this);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if(tableInteractionListner!=null)tableInteractionListner.isCellEditable(row, column);
        return false;
    }
    
    
    /**
     * get the list of object from the table
     * @return 
     */
    public <T> ArrayList<T> getObjects(Class<T> cls) {
        ArrayList<T> objs = new ArrayList();
        for (Object[] objects : getTableRows()) {
            try {
                
                // converts the table model into the list of ovjects using the 
                //reflection utility
                //set the properties
                T obj = cls.newInstance();
                int col=0;
                for (String prop : propertiesEL) {
                    
                    ReflectionUtility.setProperty(obj, prop, objects[col++]);//fucked off method to set the dynamic property
                }
                objs.add(obj);

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("DYNAMIC CREATION OF LIST FAILED -----------");
            }
        }
        return objs;
    }
    
    
    public void addItem(Object line) {                
        TableUtil.addModelToTable(line,this);
        selectLast();
    }

    
    public void updateSelectedRow(Object line) {
        final int selectedRow = getSelectedRow();
        if(selectedRow>=0){
            TableUtil.replaceModel(this, line, selectedRow);            
        }
    }
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        setRowHeight(25);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
/**bringing the tableutil functunality in to the table it  self with the object oriented level even
 */
