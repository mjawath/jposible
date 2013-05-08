/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.windows;

/**
 *
 * @author d
 */
public class DetailPanel<T> extends TabPanelUI {

    /**
     * Creates new form DetailPanel
     */
    public DetailPanel() {
        initComponents();
    }

    public void etyToUI(T obj){
        
    }
    
    public void UIToEty(){
    }

    @Override
    public void save() {
        //validate 
        //save
        
        super.save();
        postSave();
    }
    
    public void postSave(){
    
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
