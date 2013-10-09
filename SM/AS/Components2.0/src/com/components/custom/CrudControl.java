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
    
    void postSave(Object objs);
    
    void postUpdate(Object deleObj);
    
    void postDelete(Object deleObj);
    
//    void postDelete();
    
}
