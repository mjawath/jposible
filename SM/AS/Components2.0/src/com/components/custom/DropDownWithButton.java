package com.components.custom;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import org.biz.app.ui.util.ReflectionUtility;

/**
 *
 * @author nnjj
 */
public class DropDownWithButton<T> extends javax.swing.JPanel {

    List<T> model;
    Object selecedModel;
    String elString;

    /** Creates new form DropDownWithButton */
    public DropDownWithButton() {
        initComponents();
        model = new ArrayList();
    }

    public String getValue() {
        return cTextField1.getText();
    }
    //should overide ..to get a proper object as returnvalue

    public T getSelectedModel() {
     //get the model from the list by using /comparring the el exprestion language 
        //and reurn the value
        for (T object : model) {
            if(object!=null){
            if(getTextField().getText().equals(ReflectionUtility.getProperty(object, elString))){//acctuallly el expresstion 
            selecedModel =object;
                return object;
            }
            }
        }
        return null;
 
    }

    public JTextField getTextField() {
        return cTextField1;
    }

    public void addToModel(T obj) {
        model.add(obj);
    }

    public void setModel(List<T> list) {
        model = list;

    }

    public List<T>  getModel() {
       return model;
    }

    public void setELComparison(String el){
    this.elString=el;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cTextField1 = new org.components.controls.CTextField();
        cButton1 = new org.components.controls.CButton();

        setLayout(null);
        add(cTextField1);
        cTextField1.setBounds(0, 0, 124, 25);

        cButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cButton1.setPreferredSize(new java.awt.Dimension(37, 212));
        add(cButton1);
        cButton1.setBounds(130, 0, 19, 20);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CTextField cTextField1;
    // End of variables declaration//GEN-END:variables
}
/*
 class  used to load list of objects and its values 
 * in a popup 
 
 */