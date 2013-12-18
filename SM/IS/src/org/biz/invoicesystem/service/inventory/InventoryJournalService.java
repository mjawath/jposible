/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.service.inventory;

import java.util.ArrayList;
import java.util.List;
import org.biz.dao.service.Service;
import org.biz.erp.inventory.dao.InventoryJournalDAO;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.entity.inventory.StockModel;

/**
 *
 * @author mjawath
 */
 public class InventoryJournalService extends Service {
    InventoryJournalDAO dao;
    List<InventoryJournal>  ijs;

    public InventoryJournalService() {
        dao = new InventoryJournalDAO();
    }

    @Override
    public InventoryJournalDAO getDao() {
        return dao;
    }
    
    public  List<StockModel> getStockModel(){
        //here can we optimise query and get InventoryJournalLine with join InventoryJournal?? 
        ijs=dao.getAll();
        
        List<StockModel> stockModels=new ArrayList<StockModel>();
        
        for (InventoryJournal inventoryJournal : ijs) {
            for (InventoryJournalLine jl : inventoryJournal.getLines()) {//
                
            }
        }
    
        return null;
    }
    public List getInventorySummeryForMonth(){
    
        return dao.getForLastMonthsummery(null);        
    }
    

}
