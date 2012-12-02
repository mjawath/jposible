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
import java.util.ArrayList;
import java.util.List;
import org.biz.app.ui.util.ReflectionUtility;
import org.biz.app.ui.util.TableUtil;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author nano
 */
public class PxTable extends JXTable implements IComponent {

    protected IContainer container;
    private Class modelClass;
    private String[] propertiesEL;
    private List modelCollection;
    private String newRowId = newRowId_cons;
    private static final String newRowId_cons = "NewRow#";
    private int newRowId_SEED = 1001;

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
        this.propertiesEL = propertiesEL;
//     TableUtil.createTableModel(this, propertiesEL);   
    }

    public void setColumnHeader(String[] title) {

        TableUtil.createTableModel(this, title);
    }

    public Class getModelClass() {
        return modelClass;
    }

    public void setModelClass(Class modelClass) {
        this.modelClass = modelClass;
    }

    public List getModelCollection() {
        return modelCollection;
    }

    public void setModelCollection(List modelCollection) {
        clear();
        this.modelCollection = modelCollection;
//        newRowId_SEED=10000;
//        for (int i = 0; i < modelCollection.size(); i++) {
//            Object object = modelCollection.get(i);
//            if(ReflectionUtility.getValue(object, "id")==null || StringUtility.isEmptyString( ReflectionUtility.getValue(object, "id"))){                
//                newRowId = newRowId_cons  +(++newRowId_SEED);                        
//                ReflectionUtility.setProperty(object, "id", newRowId);                 
//            }
//        }
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
        modelCollection.add(obj);
        TableUtil.addModelToTable(obj, this);
    }

    public void modelToTable(List list) {
//        clear();
        if (list == null || list.isEmpty()) {
            return;
        }
        for (Object row : list) {
            modelCollection.add(row);
            TableUtil.addModelToTable(row, this);
        }


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
        int sr=getSelectedRow();
        TableUtil.removerow(this, sr);
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
        setTerminateEditOnFocusLost(false);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
/**bringing the tableutil functunality in to the table it  self with the object oriented level even
 */
