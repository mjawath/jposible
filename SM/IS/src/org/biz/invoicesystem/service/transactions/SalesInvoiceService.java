/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.service.transactions;

import java.util.ArrayList;
import org.biz.dao.service.Service;
import org.biz.entity.BusObj;
import org.biz.erp.ui.transaction.detail.InvoiceUI;
import org.biz.erp.ui.transactions.posted.PostedInvoicesListUI;
import org.biz.invoicesystem.dao.transactions.SalesInvoiceDAO;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.entity.master.UOM;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.biz.invoicesystem.service.master.ItemService;

/**
 *
 * @author mjawath
 */
public class SalesInvoiceService extends Service{
    
    private SalesInvoiceDAO dao;
    private InvoiceUI invoiceUI;
    private PostedInvoicesListUI invoicesListUI;

    public SalesInvoiceService() {
        super();
        dao = new SalesInvoiceDAO();
    }

    
    public void initServices() {
        dao = new SalesInvoiceDAO();
        invoiceUI.setService(SalesInvoiceService.this);//set other services to                 
        invoicesListUI.setService(SalesInvoiceService.this);
    }

    public SalesInvoiceDAO getDao() {
        return dao;
    }
    
    public void createInventoryJournal(SalesInvoice invoice){
//       invoice.setId(EntityService.getKeys());
        //inventory journal 
        InventoryJournal ij=new InventoryJournal();
//        ij.setId(   EntityService.getKeys()) ;
        for (SalesInvoiceLineItem sl : invoice.getLineItems()) {
            InventoryJournalLine ijl=new InventoryJournalLine();
            ijl.setId(sl.getId());
//            ijl.setDescription(sl.getId());
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
//       invoice.setId(EntityService.getKeys());
        //inventory journal
        InventoryJournal ij=new InventoryJournal();
//        ij.setId(   EntityService.getKeys()) ;
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
    
    public ItemService getitemService(){
    return new ItemService();
    }
    
    public void preCreate(BusObj toSave) {
        
        SalesInvoice sales = (SalesInvoice) toSave;
               sales.calculateTotal();
        //create customer statement
        //use the invoice as the reciept othervise print reciept separately
        //make updates to credit account
        System.out.println("sales invoice level preCreate");

        InventoryJournal  ij = new InventoryJournal();
    
        ij.setCode(sales.getCode());
        ij.setDocRefNo(sales.getDocRefNo());
        ij.setDocumentClass(sales.getClass().getSimpleName());
        for (SalesInvoiceLineItem lineItem : sales.getLineItems()) {
            InventoryJournalLine line = new InventoryJournalLine();
            line.setSku(lineItem.getSku());
            line.setQty(lineItem.getQty());
            line.setUom(lineItem.getUom());            
            ij.addIJLine(line);

        }
//        toSave.add(ij);

    }
    
      @Override
    public void preSave(BusObj toSave) {
        final SalesInvoice sales = (SalesInvoice) toSave;//CurrentBusObject();
        for (SalesInvoiceLineItem lineItem : sales.getLineItems()) {
            if(lineItem.getUom() == null && lineItem.getSku()!= null){
                final UOM primaryUOM = lineItem.getSku().getItem().getPrimaryUOM();
                lineItem.setUom(primaryUOM);
                System.out.println("item uom set "+lineItem.getUom());
            }
        }
        
        super.preSave(toSave); //To change body of generated methods, choose Tools | Templates.
    }
    
}
