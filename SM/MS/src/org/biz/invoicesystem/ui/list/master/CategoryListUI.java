/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.ui.list.master;

import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.master.Category;
import org.biz.invoicesystem.service.master.CategoryService;
import org.components.windows.ListViewPanel;

/**
 *
 * @author d
 */
public class CategoryListUI extends ListViewPanel<Category> {

    private CategoryService categoryService;
    /**
     * Creates new form CategoryListUI
     */
    public CategoryListUI() {
        initComponents();
    }

    
    
    @Override
    public void setService(Service service) {
        super.setService(service);
        categoryService=(CategoryService)service;
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
