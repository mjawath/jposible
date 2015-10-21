/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.ui.list.master;

import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.service.master.CustomerService;
import org.biz.master.ui.CustomerDetailUI;
import org.components.windows.UIController;

/**
 *
 * @author jawa
 */
public class CustomerController extends UIController<Customer>{
    

    public CustomerController() {
        super();
        setService(new CustomerService());
    }
    
    public void initUI() {
        
        CustomerDetailUI detail = new CustomerDetailUI();
        CustomerListUi list = new CustomerListUi();
        setDetailView(detail);
        setListView(list, mmm);
        detail.config();
    }
    
    public void executeSearchForCustom() {

        getQueryForPage().executeToFirstPageTask();
    }
    
}
