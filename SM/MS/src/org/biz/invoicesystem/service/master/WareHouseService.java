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

    static WareHouseDao dao=new WareHouseDao();
    
    public WareHouseService() {
    
    Sessions.addToSession(WareHouseDao.class.getSimpleName(), dao);
    }

    @Override
    public WareHouseDao getDao() {
        return dao;
    }
    
    
    public static WareHouseDao getDAO() {
        
        return dao;//(WareHouseDao)Sessions.getObj(WareHouseDao.class.getSimpleName());
        
    }
    
}
