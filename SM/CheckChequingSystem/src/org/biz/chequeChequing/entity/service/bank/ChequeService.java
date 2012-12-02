/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.chequeChequing.entity.service.bank;

import org.biz.chequeChequing.dao.accounts.bank.ChequeDAO;
import org.biz.dao.service.Service;

/**
 *
 * @author mjawath
 */
public class ChequeService extends Service {
    ChequeDAO dao;

    public ChequeService() {
    dao =  new ChequeDAO();
    }

    public void setDao(ChequeDAO dao) {
        this.dao = dao;
    }

    public ChequeDAO getDao() {
        return dao;
    }
    


}
