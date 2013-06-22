/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.chequeChequing.dao.accounts.bank;

import java.util.List;
import org.biz.books.entity.accounts.bank.Bank;
import org.biz.dao.service.GenericDAO;

/**
 *
 * @author mjawath
 */
public class BankDAO extends GenericDAO {

    public BankDAO() {
        setCls(Bank.class);
    }
    public List<Bank> getByCode(String code) {
        
        String qry=" c.code like '%"+code+"'";
        return pagedData(code, 0);
    }
}
