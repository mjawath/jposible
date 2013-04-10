/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fx.window.ui;

import org.fx.window.SimpleCrudController;

/**
 *
 * @author d
 */
public class ProductController extends SimpleCrudController{   

    @Override
    public void save() {
       
        setUIToObject();
        super.save();
    }    
    
    
}
