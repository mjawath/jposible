/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.erp.inventory.dao;

import java.util.Date;
import java.util.List;
import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.inventory.InventoryMonthlySummery;

/**
 *
 * @author mjawath
 */
public class InventoryMonthlySummeryDAO extends GenericDAO<InventoryMonthlySummery>{

    
    public InventoryMonthlySummeryDAO() {
    setCls(InventoryMonthlySummery.class);
    }

    public List<InventoryMonthlySummery> getForLastMonth(Date date) {
            
        String qry="select c from InventoryMonthlySummery c where c.summeriesedDate= ?1";
        List<InventoryMonthlySummery> list=ExecuteQuery(qry);
        return list;
    }


}
