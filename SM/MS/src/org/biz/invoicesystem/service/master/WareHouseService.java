/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.service.master;

import org.biz.dao.service.Service;
import org.biz.invoicesystem.dao.master.WareHouseDao;
import org.components.util.Sessions;

/**
 *
 * @author d
 */
public class WareHouseService extends Service{

    WareHouseDao dao;
    public WareHouseService() {
    dao=new WareHouseDao();
    Sessions.addToSession(WareHouseDao.class.getSimpleName(), dao);
    }

    @Override
    public WareHouseDao getDao() {
        return dao;
    }
    
    
    public static WareHouseDao getDAO() {
        
        return (WareHouseDao)Sessions.getObj(WareHouseDao.class.getSimpleName());
        
    }
    
}
