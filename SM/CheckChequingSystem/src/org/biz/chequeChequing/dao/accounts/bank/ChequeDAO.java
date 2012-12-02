/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.chequeChequing.dao.accounts.bank;

import org.biz.dao.service.GenericDAO;
import org.biz.chequeChequing.entity.accounts.bank.Cheques;

/**
 *
 * @author mjawath
 */
public class ChequeDAO extends  GenericDAO<Cheques>{


    public ChequeDAO() {
setCls(Cheques.class);
    }

}
