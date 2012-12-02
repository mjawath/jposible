/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.chequeChequing.dao.accounts.bank;

import java.util.List;
import org.biz.chequeChequing.entity.accounts.bank.BankBranch;
import org.biz.dao.service.GenericDAO;

/**
 *
 * @author mjawath
 */
public class BankBranchDAO extends GenericDAO<BankBranch> {

    public BankBranchDAO() {
        setCls(BankBranch.class);
    }

    public List<BankBranch> getByCode(String code) {
        
        String qry=" c.code like '%"+code+"'";
        return pagedData(code, 0);
    }
    
    public List<BankBranch> byBankCode(String code) {
        
        String qry=" c..bank.code like '%"+code+"'";
        return pagedData(code, 0);
    }
}
