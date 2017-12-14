/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.master.ui;

import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.service.master.WareHouseService;
import org.components.windows.UIController;

/**
 *
 * @author jawa
 */
public class WareHouseController extends UIController<Warehouse>{
    
    public WareHouseController() {
        super();
        setService(new WareHouseService());      
    }
    
    
    public void initUI(){
        WareHouseFrame frame = new WareHouseFrame();                
        setUIFrame(frame);
    }
    
}
