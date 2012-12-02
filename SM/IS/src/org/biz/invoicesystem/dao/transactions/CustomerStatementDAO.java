/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.dao.transactions;

import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.transactions.CustomerStatement;

/**
 *
 * @author mjawath
 */
public class CustomerStatementDAO extends GenericDAO<CustomerStatement>{

    
    public CustomerStatementDAO() {
    setCls(CustomerStatement.class);
    }


}
