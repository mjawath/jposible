/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.master.ui;

import org.biz.invoicesystem.entity.master.Category;
import org.components.windows.DetailPanel;

/**
 *
 * @author d
 */
public class CategoryUI extends DetailPanel<Category> {

    private Category selected;

    /**
     * Creates new form CategoryUI
     */
    public CategoryUI() {
        super();
    }

    public void init() {

        initComponents();
        super.init();
        clear();
    }

    public void clear() {
        tcode.clear();
        tdesc.clear();
        super.clear();
    }

    public Category uiToData() {
        //do we set id        
        if (busObject == null) {
            busObject = new Category();
        }

        busObject.setCode(tcode.getValue());
        busObject.setDescription(tdesc.getValue());
        return busObject;
    }//todo crud control

    public void setDataToUI(Category obj) {
        super.setDataToUI(obj);
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

        setPreferredSize(new java.awt.Dimension(600, 200));
        setLayout(null);

        cLabel1.setText("Description");
        add(cLabel1);
        cLabel1.setBounds(20, 90, 120, 25);
        add(tcode);
        tcode.setBounds(150, 60, 190, 25);
        add(tdesc);
        tdesc.setBounds(150, 90, 190, 25);

        cLabel2.setText("Category Name");
        add(cLabel2);
        cLabel2.setBounds(20, 60, 120, 25);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CTextField tcode;
    private org.components.controls.CTextField tdesc;
    // End of variables declaration//GEN-END:variables
}
