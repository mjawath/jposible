package org.biz.invoicesystem.service.master;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;
import org.biz.app.ui.util.StringUtility;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.dao.master.ItemDAO;
import org.biz.invoicesystem.entity.master.Item;


    
public class ItemService extends Service<Item>{
    
    public  final static String qryName="item list search ";       
    public static ItemDAO dao=new ItemDAO();
 
    public ItemService() {
        dao=new ItemDAO();
        
    }

    public ItemDAO getDao() {
        return dao;
    } 

 
    public Service categoryServise() {
        return new CategoryService();//get the service from cache
    }

    @Override
    protected boolean isValideEntity(Item busObject) {

        return super.isValideEntity(busObject)
                && !StringUtility.isEmptyString(busObject.getCode())
                && !StringUtility.isEmptyString(busObject.getDescription())
                && !Objects.isNull(busObject.getCategory());
    }
    
    
    
    
    


 
 
    }
