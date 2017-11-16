/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.service.inventory;

import java.util.Date;
import java.util.List;
import org.biz.dao.service.Service;
import org.biz.erp.inventory.dao.InventoryJournalDAO;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;

/**
 *
 * @author mjawath
 */
 public class InventoryJournalService extends Service {
    static InventoryJournalDAO dao;
    List<InventoryJournal>  ijs;

    public InventoryJournalService() {
        dao = new InventoryJournalDAO();
    }

    @Override
    public InventoryJournalDAO getDao() {
        return dao;
    }
    
    public List getInventorySummeryForMonth(){
        
        //get last inetorires with adjustments
        dao.getLastAdjustments();
        return dao.getAll();
//        return dao.getForLastMonthsummery(new Date(),"","");        
    }
    public List getSummeryOfItems(int pageNo){
    
        return dao.getSummeryOfItems(pageNo);        
    } 
    
     public long getCountOfSummeryOfItems(){       
       
        return dao.getCountOfSummeryOfItems();
        
    }


}
