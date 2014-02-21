/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CustomerListUi.java
 *
 * Created on Dec 3, 2011, 4:54:01 PM
 */
package org.biz.invoicesystem.ui.list.master;

import java.util.Date;
import java.util.List;
import org.biz.app.ui.util.QueryManager;
import org.biz.app.ui.util.TableUtil;
import org.biz.dao.service.CQuery;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.service.master.CustomerService;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.windows.ListViewPanel;

public class CustomerListUi extends ListViewPanel<Customer> {

    private CustomerService cService;
    // List<Customer> customers;
    private Customer selectedCus;
    
    private TableInteractionListner tableInteractionListner = new TableInteractionListner() {
        @Override
        public Object[] getTableData(Object row) {
            Customer item = (Customer) row;
            return new Object[]{item, item.getId(), item.getCode(), item.getCompanyName(), item.getSavedDate(), item.getEditedDate()};
        }
    };
    
    

    @Override
    public void init() {
        super.init();                
        listUI.getTable().init(Customer.class, new Class[]{String.class, String.class, String.class, Date.class, Date.class}, new String[]{"id", "code", "companyName", "savedDate", "editedDate"});
        listUI.getTable().setTableInteractionListner(tableInteractionListner);
        listUI.getPagePanel().init(service, searchListener, listUI.getTable());
    }
    
    
    
    
    public CustomerListUi() {
        initComponents();
    }

    

    ///////////////////////////////////////////////////// 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void setService(Service service) {
        super.setService(service);
        cService = (CustomerService) service;
//        tbl.init(Item.class, new Class[]{String.class, String.class, String.class, Date.class, Date.class},
//                 new String[]{"id", "code", "description", "savedDate", "editedDate"});
//        tbl.setPropertiesEL(new String[]{"id", "code", "description", "savedDate", "editedDate"});
//        tbl.setTableInteractionListner(tableInteractionListner);
//        cPaginatedPanel1.init(service, searchListener, tbl);

    }
    
    private QueryManager searchListener = new QueryManager() {
        
        @Override
        public CQuery getCQuery() { 
            
            // 
            
//            String qry = "  c.code " + " like " + " ?1 ";//" where c."+myfield+" "+ myoperator +" ?1 ";
            return service.getQueryByCodeLike("");
        }

    };
}
