/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.dao.master;

import java.util.List;
import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.master.Warehouse;

/**
 *
 * @author d
 */
public class WareHouseDao extends GenericDAO<Warehouse> {

    public WareHouseDao() {
    setCls(Warehouse.class);
    }
    
    public List byCode(String qry){
        String qryx = " c.code like  '" + qry + "%'";
    return pagedData(qryx);
    }
    
    
    
}
