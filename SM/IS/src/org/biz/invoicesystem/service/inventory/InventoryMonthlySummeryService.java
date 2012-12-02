/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.service.inventory;

import org.biz.dao.service.Service;
import org.biz.erp.inventory.dao.InventoryMonthlySummeryDAO;

/**
 *
 * @author mjawath
 */
public class InventoryMonthlySummeryService extends Service {
    InventoryMonthlySummeryDAO dao;

    public InventoryMonthlySummeryService() {
    dao = new InventoryMonthlySummeryDAO();
    }

    @Override
    public InventoryMonthlySummeryDAO getDao() {
        return dao;
    }

}
