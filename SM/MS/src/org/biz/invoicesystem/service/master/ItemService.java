package org.biz.invoicesystem.service.master;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.dao.master.ItemDAO;
import org.biz.invoicesystem.ui.list.master.ItemList;
import org.biz.master.ui.ItemMasterUI2;

    
public class ItemService extends Service{
    
    public  final static String qryName="item list search ";
    private ItemMasterUI2 itemMasterUI2;
    private ItemList itemList;
    private static ItemDAO dao=new ItemDAO();
 
    public ItemService() {
        dao=new ItemDAO();
        
    }

    public ItemDAO getDao() {
        return dao;
    } 

 
    public Service categoryServise() {
        return new CategoryService();//get the service from cache
    }
    
    
    public static List getItemForPopup(String qry){
        List lst=new ArrayList();
        Object ob=dao.getByCode(qry);
        if(ob!=null && !((Vector)ob).isEmpty()){
           lst.add(ob);
          return lst; 
        }
//        lst.addAll(dao.getByCodeLike(qry));
        lst.addAll( dao.getByCodeOrDescriptionLike(qry));        
        return lst;
        
        
    }
 
    }
