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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import org.biz.app.ui.util.ReflectionUtility;
import org.biz.app.ui.util.TableUtil;
import org.components.parent.controls.editors.TableInteractionListner;

/**
 *
 * @author nano
 */
public class PxTable<T> extends JTable implements IComponent {

    protected IContainer container;
    private Class modelClass; 
    private String[] propertiesEL;
    private List modelCollection;
    private String newRowId = newRowId_cons;
    private static final String newRowId_cons = "#NewRow#";
    private int newRowId_SEED = -10000001;
    private TableInteractionListner tableInteractionListner;
    

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
        this.propertiesEL[0]="line";
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
        return modelCollection;
    }

    public void setModelCollection(List<T> modelCollection) {        
        clear();
        this.modelCollection=new ArrayList();
        //for each item set values to table model
        if (modelCollection == null) {
            return;
        }
        for (Object obj : modelCollection) {
            addModelToTable(obj);
        }
    }

    /**
     * Creates new form BeanForm
     */
    public PxTable() {
        initComponents();
    }

    public void clear() {
        if (modelCollection != null) {
            TableUtil.cleardata(this);
            modelCollection.clear();
        }

    }

    public void addModelToTable(Object obj) {
       //obj should hv an unique id 
        Object bb=ReflectionUtility.getValue(obj, "id");
//        Object val=ReflectionUtility.getValue(getSelectedObject(), "id");
        if(bb==null ){
        ReflectionUtility.setValue(obj, "id", newRowId_SEED++ +newRowId_cons);
        }//sould check existing id , objects
        modelCollection.add(obj);
        TableUtil.addModelToTable(obj, this);
    }

    public void modelToTable(List list) {
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

    public void replaceModel(Object obj) {
        TableUtil.replaceSelectedModel(this, obj);
    }

    public void removeSelectedRow() {
        if(modelCollection==null)return;
        int sr = getSelectedRow();
        if (sr < 0) {
            return;
        }
        Object sele = this.getSelectedObject();
        Iterator it = modelCollection.iterator();
        for (; it.hasNext();) {
            Object obj1 = ReflectionUtility.getValue(sele, "id");
            Object obj2 = ReflectionUtility.getValue(it.next(), "id");
            if (obj1 != null && obj1.equals(obj2)) {
                it.remove();
                TableUtil.removerow(this, sr);
                return;
            }
        }

    }
    
    public void removeSelectedObject(){
    
    }

    public void copySelectedElement() {
           Object object= getSelectedObject();
           if(object!=null){
           //object.clone
           }
    }
    
    public Object getSelectedObject() {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        setRowHeight(25);
        setRowHeight(12);
           }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
/**bringing the tableutil functunality in to the table it  self with the object oriented level even
 */
