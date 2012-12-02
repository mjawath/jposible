/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.books.service.accounts.ledger;

import org.biz.books.dao.accounts.ledger.AccountsDAO;
import org.biz.dao.service.Service;

/**
 *
 * @author mjawath
 */
public class AccountsService extends Service{
    AccountsDAO dao;
      public AccountsService() {
    dao = new AccountsDAO();
    }

    public AccountsDAO getDao() {
        return dao;
    }

}
