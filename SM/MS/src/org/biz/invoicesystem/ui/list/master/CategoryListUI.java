/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.ui.list.master;

import java.util.Date;
import java.util.List;
import org.biz.app.ui.util.QueryManager;
import org.biz.dao.service.CQuery;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.master.Category;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.service.master.CategoryService;
import org.components.controls.CxTable;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.windows.ListViewPanel;

/**
 *
 * @author d
 */
public class CategoryListUI extends ListViewPanel<Category> {

    private CategoryService categoryService;
    
    private List<Category> items;
    private TableInteractionListner tableInteractionListner = new TableInteractionListner(){

        @Override
        public Object[] getTableData(Object row) {
            Category item= (Category)row;
            return new Object[]{item,item.getId(),item.getCode(),item.getDescription(),item.getSavedDate(),item.getEditedDate()};
        }
    
    };

    /**
     * Creates new form CategoryListUI
     */
    public CategoryListUI() {
         super();
    }

    @Override
    public void init() {
        initComponents();      
        listUI = categoryLVUI1;
        searchQueryUI = categorySUI1;
        super.init();         

  
        //set query manager for pagination
    }
    



  
 

    private QueryManager searchListener = new QueryManager() {
        public CQuery getCQuery() {
            //" where c."+myfield+" "+ myoperator +" ?1 ";
            
            return service.getQueryByCodeLike("");
        }
    };

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        categoryLVUI1 = new org.biz.invoicesystem.ui.list.master.CategoryLVUI();
        categorySUI1 = new org.biz.invoicesystem.ui.list.master.CategorySUI();

        javax.swing.GroupLayout categorySUI1Layout = new javax.swing.GroupLayout(categorySUI1);
        categorySUI1.setLayout(categorySUI1Layout);
        categorySUI1Layout.setHorizontalGroup(
            categorySUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 823, Short.MAX_VALUE)
        );
        categorySUI1Layout.setVerticalGroup(
            categorySUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(categoryLVUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(categorySUI1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(categorySUI1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(categoryLVUI1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.biz.invoicesystem.ui.list.master.CategoryLVUI categoryLVUI1;
    private org.biz.invoicesystem.ui.list.master.CategorySUI categorySUI1;
    // End of variables declaration//GEN-END:variables
}
