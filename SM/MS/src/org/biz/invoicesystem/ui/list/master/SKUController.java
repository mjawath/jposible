/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.ui.list.master;

import org.biz.invoicesystem.entity.master.SKU;
import org.biz.invoicesystem.service.master.SKUService;
import org.components.windows.UIController;

/**
 *
 * @author jawa
 */
public class SKUController extends UIController<SKU>{

    
    public SKUController() {
        super();
        setService(new SKUService());
    }

    public void initUI() {
        
        
        
        SKUFrame frame = new SKUFrame();    
        
        setUIFrame(frame);
    }
    
}
