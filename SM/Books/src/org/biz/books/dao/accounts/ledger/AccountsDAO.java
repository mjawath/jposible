/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.books.dao.accounts.ledger;

import org.biz.books.entity.accounts.ledger.Accounts;
import org.biz.dao.service.GenericDAO;

/**
 *
 * @author mjawath
 */
public class AccountsDAO extends GenericDAO<Accounts> {

    public AccountsDAO() {
        setCls(Accounts.class);         
    }

}
