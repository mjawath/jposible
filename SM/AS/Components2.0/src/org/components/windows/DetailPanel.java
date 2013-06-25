/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.windows;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import org.biz.app.ui.util.ReflectionUtility;
import org.biz.app.ui.util.Tracer;
import org.biz.dao.service.GenericDAOUtil;
import org.biz.dao.util.EntityService;
import org.biz.entity.BusObj;

/**
 *
 * @author d
 */
public class DetailPanel<T> extends TabPanelUI {

    final protected ArrayList toSave;
    final protected ArrayList toDelete;
    final protected ArrayList toUpdate;
    protected T busObject;
    protected T selectedObject;

    /**
     * Creates new form DetailPanel
     */
    public DetailPanel() {
        initComponents();
        toSave = new ArrayList();
        toDelete = new ArrayList();
        toUpdate = new ArrayList();
    }

    public void etyToUI(T obj) {
    }

    public T UIToEty() {
        return null;
    }

    @Override
    public void save() {
        
        busObject = getBusObject();
        if (service != null) {
            if (!isValideEntity()) {
                return;
            }
            preSave();
            
//            if (toSave.isEmpty()) {
//                Tracer.printToOut("no object found to Save");
//                return;
//            }
            Object key = ReflectionUtility.getProperty(selectedObject, "id");//0 is the index of the main object , id is id property

            if (busObject == null) {
                Tracer.printToOut("Object id is null");
                return;
            }
            Object obj = service.getDao().find(key);
            Date cDate = GenericDAOUtil.currentTime();
            Date mDate = GenericDAOUtil.currentTime();
            if (obj == null ) {                
                ReflectionUtility.setProperty(busObject,"id",EntityService.getKey(""));
                ReflectionUtility.setProperty(busObject, "savedDate", cDate);
                toSave.add(busObject);    
                Tracer.printToOut(" Object  is not found  So creation will be called");
                service.getDao().saveUpdateDelete(toSave, toUpdate, toDelete);
                postCreate();
            }
            else {
                ReflectionUtility.setProperty(busObject, "id", key);
                ReflectionUtility.setProperty(busObject,"editedDate",mDate);
                ReflectionUtility.setProperty(busObject, "savedDate", ((BusObj)obj).getSavedDate());

                toUpdate.add(busObject);
                Tracer.printToOut("Updation is called  Object  is  found");
                service.getDao().saveUpdateDelete(toSave, toUpdate, toDelete);
                postUpdate();
            }
            postSave();
            toSave.clear();
            toUpdate.clear();
            toDelete.clear();
            clear();
            selectedObject=null;
        }
        super.save();
    }

    @Override
    public void delete() {
        if (service != null) {

            T exist = (T) service.getDao().find(((BusObj) selectedObject).getId());
            if (exist != null) {

                String[] ObjButtons = {"Yes", "No"};
                int PromptResult = JOptionPane.showOptionDialog(null, "Are you want to delete ?", "Item Form", -1, 2, null, ObjButtons, ObjButtons[1]);

                if (PromptResult == 0) {
                    service.getDao().delete(exist);
                    postDelete();
                    clear();
                }
            }
            else {
//                MessageBoxes.errormsg(null, "No Item Found.", getTabName());
                return;

            }

        }
    }

    public void postDelete() {
    }

    public void postCreate() {
        System.out.println("-------+++++++++");
    }

    public void postUpdate() {
        System.out.println("---%%%%%%%%%%%----+++++++++");
    }

    public boolean isValideEntity() {
//         busObject=UIToEty();       
//        if(busObject==null)
//         return false;
        return true;
    }

    public void postSave() {
    }

    public void preSave() {
    }
    
    public T getBusObject(){
        return null;
    }

    public void setBusObject(T obj) {
    }

    public void setSelectedBusObj(T obj) {
        this.selectedObject = obj;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
