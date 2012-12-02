/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.chequeChequing.dao.accounts.bank;

import java.util.List;
import org.biz.dao.service.GenericDAO;
import org.biz.chequeChequing.entity.accounts.bank.Bank;

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
