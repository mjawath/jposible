/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.service.master;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.dao.master.SKUDAO;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.SKU;

/**
 *
 * @author jawa
 */
public class SKUService extends Service<SKU>{

    public static SKUDAO dao = new SKUDAO();
    
    public SKUService() {
        super();
      
    }
    
    public SKUDAO getDao() {
        return dao;
    }
    
     @Override
    protected SKU saveData(SKU busObject) {
        System.out.println("sales invoice level preCreate");
        
        ArrayList thingsToCreate  = new ArrayList();
        if(busObject.getItem()==null){
        Item item = new Item();
        
            item.setCode(busObject.getCode());
            busObject.setItem(item);
            thingsToCreate.add(item);
        }

        super.saveData(busObject, thingsToCreate, null, null);
        return busObject;
    }
    
}
