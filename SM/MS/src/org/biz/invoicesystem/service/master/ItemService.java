package org.biz.invoicesystem.service.master;

import org.biz.dao.service.Service;
import org.biz.invoicesystem.dao.master.ItemDAO;

    
public class ItemService extends Service{
    
    public  final static String qryName="item list search ";
    
    ItemDAO dao;

    public ItemService() {
    dao = new ItemDAO();
    }

    public ItemDAO getDao() {
        return dao;
    }
    
    
    
}
