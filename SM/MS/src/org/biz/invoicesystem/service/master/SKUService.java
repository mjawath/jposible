/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.service.master;

import org.biz.dao.service.Service;
import org.biz.invoicesystem.dao.master.SKUDAO;

/**
 *
 * @author jawa
 */
public class SKUService extends Service{

    public static SKUDAO dao = new SKUDAO();
    
    public SKUService() {
        super();
      
    }
    
    public SKUDAO getDao() {
        return dao;
    }
    
    
    
    
}
