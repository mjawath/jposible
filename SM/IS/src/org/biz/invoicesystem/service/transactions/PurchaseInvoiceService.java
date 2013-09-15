/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.service.transactions;

import org.biz.dao.service.Service;
import org.biz.dao.util.EntityService;
import org.biz.invoicesystem.dao.transactions.PurchaseInvoiceDAO;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.entity.transactions.PurchaseInvoice;
import org.biz.invoicesystem.entity.transactions.PurchaseInvoiceLineItem;

/**
 *
 * @author mjawath
 */
public class PurchaseInvoiceService extends Service {
    PurchaseInvoiceDAO dao;

    public PurchaseInvoiceService() {
    dao = new PurchaseInvoiceDAO();
    }

    public PurchaseInvoiceDAO getDao() {
        return dao;
    }
    public void createInventoryJournal(PurchaseInvoice invoice){
       invoice.setId(EntityService.getKeys());
        //inventory journal 
        InventoryJournal ij=new InventoryJournal();
        ij.setDocType("PurchaseInvoice");
        ij.setDocumentClass(PurchaseInvoice.class.toString());        
        ij.setId(   EntityService.getKeys()) ;
        for (PurchaseInvoiceLineItem sl : invoice.getLineItems()) {
            InventoryJournalLine ijl=new InventoryJournalLine();
            ijl.setId(sl.getId());
            ijl.setDescription(sl.getId());
            ijl.setItem(sl.getItem());
            ijl.setQty(sl.getQty());
            ijl.setShop(sl.getShop());
            ijl.setItemMark(sl.getItemMark());
            ijl.setWarehouse(sl.getWarehouse());
            
            ij.addIJLine(ijl);
//            ijl.setShop(invoice.gets);
//            jt
                    //shop ware houses
//            ijl.setUom(sl.getQty());
        }
        dao.save(invoice,ij);
    }

}
