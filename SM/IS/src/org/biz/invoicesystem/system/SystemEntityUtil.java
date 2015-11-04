/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.system;

import java.util.Date;
import org.biz.dao.util.EntityService;
import org.biz.invoicesystem.entity.master.Shop;

/**
 *
 * @author nnjj
 */
public class SystemEntityUtil {
    public static Shop getShop(){
        Shop s=new Shop();
//        s.setId("1");
        return s;
    }
    
 public static Date getSystemDate(){
 return EntityService.nowDate();
 }   
}
