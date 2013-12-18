/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.components.custom;

/**
 *
 * @author nnjj
 */
public interface CrudControl {
    
    void save();
    
    Object saveX();
    
    void update();
    
    void clear();
    
    void delete();
    
    void gotoList();
    
    void printPage();
    
    void onSaveComplete(Object objs);
    
    void postUpdate(Object deleObj);
    
    void onDeleteComplete(Object deleObj);
    
//    void onDeleteComplete();
    
}
