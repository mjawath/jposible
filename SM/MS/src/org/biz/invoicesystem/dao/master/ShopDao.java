/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.dao.master;

import java.util.ArrayList;
import java.util.List;
import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.master.Shop;

/**
 *
 * @author d
 */
public class ShopDao extends GenericDAO<Shop> {

//    List shops;
    
    public ShopDao() {
        setCls(Shop.class);
//        shops = new ArrayList();
    }

    public List getItemByCode(String qry) {
        String qryx = " c.code like  '" + qry + "%'";
        return pagedData(qryx, 0);
        
    }

    public void setList(List shops) {
        this.list = shops;
    }
}