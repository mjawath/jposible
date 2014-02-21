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
    public void setService(Service service) {
        
        super.setService(service);
        categoryService=(CategoryService)service;
    }

    @Override
    public void init() {
        super.init();
        initComponents();       
    }
    
    @Override
    public void init(CxTable tbl) {
        super.init(tbl);
        tbl.init(Item.class, new Class[]{String.class, String.class, String.class, Date.class, Date.class},
                 new String[]{"id", "code", "description", "savedDate", "editedDate"});
        tbl.setPropertiesEL(new String[]{"id", "code", "description", "savedDate", "editedDate"});
        tbl.setTableInteractionListner(tableInteractionListner);
        listUI.getPagePanel().init(service, searchListener, tbl);


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
