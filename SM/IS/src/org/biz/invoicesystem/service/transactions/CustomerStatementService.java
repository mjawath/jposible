/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.service.transactions;

import org.biz.invoicesystem.dao.transactions.CustomerStatementDAO;

/**
 *
 * @author mjawath
 */
public class CustomerStatementService {
    CustomerStatementDAO dao;

    public CustomerStatementService() {
    dao = new CustomerStatementDAO();
    }

    public CustomerStatementDAO getDao() {
        return dao;
    }

}
