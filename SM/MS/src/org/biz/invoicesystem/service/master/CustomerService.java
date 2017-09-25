/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.service.master;

import org.biz.dao.service.Service;
import org.biz.invoicesystem.dao.master.CustomerDAO;
import org.biz.invoicesystem.entity.master.Customer;

/**
 *
 * @author mjawath
 */
public class CustomerService extends Service<Customer>  {
    CustomerDAO dao;

    public CustomerService() {
    dao = new CustomerDAO();
    }

    public CustomerDAO getDao() {
        return dao;
    }

    
    
}
