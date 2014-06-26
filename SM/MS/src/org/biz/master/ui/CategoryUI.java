/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.master.ui;

import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.master.Category;
import org.biz.invoicesystem.service.master.CategoryService;
import org.components.windows.DetailPanel;

/**
 *
 * @author d
 */
public class CategoryUI extends DetailPanel<Category> {

    private CategoryService categoryService;
    private Category selected;
    /**
     * Creates new form CategoryUI
     */
    public CategoryUI() {
       super();
    }

    public void init() {
        super.init();
        initComponents();        
        clear();
    }
    
    public void clear() {
        tcode.clear();
        tdesc.clear();
           super.clear();
    }

    public void setService(Service service) {
        super.setService(service);
        categoryService=(CategoryService)service;
    }

    public Category getBusObject() {
        Category category = new Category();
        category.setCode(tcode.getValue());
        category.setDescription(tdesc.getValue());
        return category;
    }//todo crud control

    public void setBusObject(Category obj) {
        tcode.setValue(obj.getCode());
        tdesc.setValue(obj.getDescription());
    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cLabel1 = new org.components.controls.CLabel();
        tcode = new org.components.controls.CTextField();
        tdesc = new org.components.controls.CTextField();
        cLabel2 = new org.components.controls.CLabel();

        setLayout(null);

        cLabel1.setText("Description");
        add(cLabel1);
        cLabel1.setBounds(20, 70, 120, 25);
        add(tcode);
        tcode.setBounds(150, 40, 190, 25);
        add(tdesc);
        tdesc.setBounds(150, 70, 190, 25);

        cLabel2.setText("Category Name");
        add(cLabel2);
        cLabel2.setBounds(21, 39, 120, 25);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CTextField tcode;
    private org.components.controls.CTextField tdesc;
    // End of variables declaration//GEN-END:variables
}
