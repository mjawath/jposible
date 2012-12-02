/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.chequeChequing.entity.service.bank;

import org.biz.chequeChequing.dao.accounts.bank.BankDAO;
import org.biz.dao.service.Service;

/**
 *
 * @author mjawath
 */
public class BankService extends Service {
    BankDAO dao;

    public BankService() {
        dao = new BankDAO();
    }


    public BankDAO getDao() {
        return dao;
    }

    public void setDao(BankDAO dao) {
        this.dao = dao;
    }

}
