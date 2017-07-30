/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.ui.list.master;

import org.biz.invoicesystem.entity.master.Supplier;
import org.biz.invoicesystem.service.master.SupplierService;
import org.biz.master.ui.SupplerDetailUI;
import org.components.windows.UIController;

/**
 *
 * @author jawa
 */
public class SupplierController extends UIController<Supplier>{
    
    
    public SupplierController() {
        super();
        setService(new SupplierService());
    }

    public void initUI() {

        SupplerDetailUI cf = new SupplerDetailUI();
//        setUIFrame(cf);
//        
    }
}
