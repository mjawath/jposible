/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.ui.list.master;

import java.util.List;
import org.biz.app.ui.util.StringUtility;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.service.master.CustomerService;
import org.components.windows.UIController;

/**
 *
 * @author jawa
 */
public class CustomerController extends UIController<Customer> {

    public CustomerController() {
        super();
        setService(new CustomerService());
    }

    public void initUI() {

        CustomerFrame cf = new CustomerFrame();
        setUIFrame(cf);
//        
    }

    public List executeQuery(int page) {
        CustomerSUI searchUI = (CustomerSUI) getListView().getSearchUI();
        String searchTextFieldValue = searchUI.getSearchTextFieldValue();

        if (StringUtility.isEmptyString(searchTextFieldValue)) {
            return getService().getDao().getAll();
        }
        searchUI.getQueryParameterMap();
        
        String where = "";
        String attribute = searchUI.getAttribute();
        where = searchTextFieldValue != null ? " c." + attribute + " like '" + searchTextFieldValue + "%' " : "";
        
//        String name = qmp.get("name");
//        where = name != null ? " c.name like " + name +" " : "";
        return getService().getByWhere(where);        
    }

    public Long executeCount() {
        CustomerSUI searchUI = (CustomerSUI) getListView().getSearchUI();
        String searchTextFieldValue = searchUI.getSearchTextFieldValue();
        
        if (StringUtility.isEmptyString(searchTextFieldValue)) {
            return getService().getDao().getAllCount();
        }
        String where = "";
        String attribute = searchUI.getAttribute();        
        where = searchTextFieldValue != null ? " c."+attribute+" like '" + searchTextFieldValue + "%' " : "";

//        String name = qmp.get("name");
//        where = name != null ? " c.name like " + name + " " : "";
        return getService().getCountOfByWhere(where);        
    }



}
