/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.master.ui;

import org.biz.app.ui.util.UIEty;
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
    
    public void clear(){
    tcode.clear();
    tdesc.clear();
    }

    public void setService(Service service) {
        super.setService(service);
        categoryService=(CategoryService)service;
    }

    public Category getBusObject() {
        Category category = new Category();
        category.setCode(UIEty.tcToStr(tcode));
        category.setDescription(UIEty.tcToStr(tdesc));
        return category;
    }//todo crud control

    public void setBusObject(Category obj) {
        tcode.setText(obj.getCode());
        tdesc.setText(obj.getDescription());
    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cLabel1 = new org.components.controls.CLabel();
        tcode = new org.components.controls.CTextField();
        tdesc = new org.components.controls.CTextField();

        add(cLabel1);
        cLabel1.setBounds(21, 39, 104, 25);
        add(tcode);
        tcode.setBounds(130, 40, 190, 25);
        add(tdesc);
        tdesc.setBounds(130, 90, 190, 25);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CTextField tcode;
    private org.components.controls.CTextField tdesc;
    // End of variables declaration//GEN-END:variables
}
