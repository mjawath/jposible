/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.service.master;

import java.util.List;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.dao.master.ShopDao;

/**
 *
 * @author d
 */
public class ShopService extends Service {

    final static ShopDao dao = new ShopDao();

    public ShopService() {
    }

    public ShopDao getDao() {
        return dao;
    }

    public static ShopDao getDAO() {
        return dao;
    }

    public void setList(List shops) {
        dao.setList(shops);
    }
}
