/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.windows;

import java.util.ArrayList;

/**
 *
 * @author d
 */
public class DetailPanel<T> extends TabPanelUI {

    final protected ArrayList toSave;
    final protected ArrayList toDelete;
    final protected ArrayList toUpdate;
    
    protected  T busObject;

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
        //validate 
        //save  
           
        if (getService() != null) {
            preSave();
            if (!validateEntity()) {
                return;
            }
            getService().getDao().saveUpdateDelete(toSave, toUpdate, toDelete);
            postSave();
            toSave.clear();
            toUpdate.clear();
            toDelete.clear(); 
            clear();
        }
        super.save();
    }

    public boolean validateEntity() {
//         busObject=UIToEty();       
//        if(busObject==null)
//         return false;
        return true;
    }

    public void postSave() {
    }

    public void preSave() {
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
