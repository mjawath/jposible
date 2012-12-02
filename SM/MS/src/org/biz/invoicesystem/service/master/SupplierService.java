/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.service.master;

import org.biz.dao.service.Service;
import org.biz.invoicesystem.dao.master.SupplierDAO;

/**
 *
 * @author mjawath
 */
public class SupplierService extends Service {
    SupplierDAO dao;

    public SupplierService() {
    dao = new SupplierDAO();
    }

    public SupplierDAO getDao() {
        return dao;
    }

}
