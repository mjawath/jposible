package org.biz.invoicesystem.service.master;

import app.utils.SystemUtil;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.dao.master.ItemDAO;
import org.biz.invoicesystem.ui.list.master.ItemList;
import org.biz.master.ui.ItemMasterUI2;

    
public class ItemService extends Service{
    
    public  final static String qryName="item list search ";
    private ItemMasterUI2 itemMasterUI2;
    private ItemList itemList;
    private ItemDAO dao;

    public ItemService() {
        dao=new ItemDAO();
        
    }

    public ItemDAO getDao() {
        return dao;
    }

    @Override
    public void initUI() {
        itemMasterUI2 = new ItemMasterUI2();
        itemList = new ItemList();
        SystemUtil.addToMainWindow(itemMasterUI2, "ITEMDETAIL");
        SystemUtil.addToMainWindow(itemList, "ITEMLIST");
        new Thread() {
            @Override
            public void run() {
                dao = new ItemDAO();
                itemList.setService(ItemService.this);
                itemMasterUI2.setService(ItemService.this);

            }
        }.start();
    }
    
    public Service categoryServise(){
            return new CategoryService();//get the service from cache
    }
    
}
