/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.chequeChequing.entity.service.bank;

import org.biz.chequeChequing.dao.accounts.bank.BankBranchDAO;
import org.biz.dao.service.Service;

/**
 *
 * @author mjawath
 */
public class BankBranchService extends Service {
    BankBranchDAO dao;

    public BankBranchService() {
        dao = new BankBranchDAO();

    }


    public BankBranchDAO getDao() {
        return dao;
    }

    public void setDao(BankBranchDAO dao) {
        this.dao = dao;
    }


}
