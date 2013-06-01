/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.service.transactions;

import app.utils.SystemUtil;
import org.biz.dao.service.Service;
import org.biz.dao.util.EntityService;
import org.biz.erp.ui.transaction.detail.InvoiceUI;
import org.biz.invoicesystem.dao.transactions.SalesInvoiceDAO;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;

/**
 *
 * @author mjawath
 */
public class SalesInvoiceService extends Service{
    
    private SalesInvoiceDAO dao;
    private InvoiceUI invoiceUI;

    public SalesInvoiceService() {
        dao = new SalesInvoiceDAO();
        invoiceUI = new InvoiceUI();
        invoiceUI.setService(this);
        SystemUtil.addToMainWindow(invoiceUI, "sales");
    }

    public SalesInvoiceDAO getDao() {
        return dao;
    }
    
    public void createInventoryJournal(SalesInvoice invoice){
       invoice.setId(EntityService.getKeys());
        //inventory journal 
        InventoryJournal ij=new InventoryJournal();
        ij.setId(   EntityService.getKeys()) ;
        for (SalesInvoiceLineItem sl : invoice.getLineItems()) {
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

    public void createInventoryJournalForPos(SalesInvoice invoice){
       invoice.setId(EntityService.getKeys());
        //inventory journal
        InventoryJournal ij=new InventoryJournal();
        ij.setId(   EntityService.getKeys()) ;
        ij.setDocumentType(InventoryJournal.pos_Invoice);
        ij.setInOrOut(InventoryJournal.Item_In);
        for (SalesInvoiceLineItem sl : invoice.getLineItems()) {
            InventoryJournalLine ijl=new InventoryJournalLine();
            ijl.setId(sl.getId());
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
