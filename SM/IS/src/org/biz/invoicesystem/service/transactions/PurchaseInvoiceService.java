/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.service.transactions;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.biz.app.ui.util.BizException;
import org.biz.dao.service.Service;
import org.biz.erp.inventory.dao.InventoryJournalDAO;
import org.biz.invoicesystem.dao.transactions.PurchaseInvoiceDAO;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.entity.transactions.PurchaseInvoice;
import org.biz.invoicesystem.entity.transactions.PurchaseInvoiceLineItem;

/**
 *
 * @author mjawath
 */
public class PurchaseInvoiceService extends Service<PurchaseInvoice> {
    PurchaseInvoiceDAO dao;
    InventoryJournalDAO inventoryJournalDAO;

    public PurchaseInvoiceService() {
    dao = new PurchaseInvoiceDAO();
    inventoryJournalDAO = new InventoryJournalDAO();
    }

    public PurchaseInvoiceDAO getDao() {
        return dao;
    }
  

      
    @Override
    protected PurchaseInvoice saveData(PurchaseInvoice busObject,List thingsToCreate,
            List thingsToUpdate,List thingsToDelete) {
        System.out.println("sales invoice level preCreate");
        final EntityManager em = startTransaction();
        
        busObject.calculateTotal();
        persist(em, busObject);
        
        InventoryJournal ij = new InventoryJournal();
        ij.setRefEntityID(busObject.getId());
        ij.setCode(busObject.getCode());
        ij.setDocRefNo(busObject.getDocRefNo());
        ij.setDocumentClass(busObject.getClass().getSimpleName());
        for (PurchaseInvoiceLineItem lineItem : busObject.getLineItems()) {
            InventoryJournalLine line = new InventoryJournalLine();
            line.setSku(lineItem.getSku());
            line.setQty(lineItem.getQty());
            line.setUom(lineItem.getUom());
            ij.addIJLine(line);

        }
//        toSave.add(ij);
        
        thingsToCreate  = new ArrayList();
        thingsToCreate.add(ij);
        persist(em,ij);
        commit(em);
        
        return busObject;
    }
    
        @Override
    protected PurchaseInvoice updateData(PurchaseInvoice busObject,List thingsToCreate,
            List thingsToUpdate,List thingsToDelete) {

        InventoryJournal ij = (InventoryJournal) inventoryJournalDAO.getByPropertySR("refEntityID",busObject.getId());
        if(ij==null) throw new BizException("PurchaseInvoicesevrice updateData ");
        busObject.calculateTotal();

        
        ij.setCode(busObject.getCode());
        ij.setDocRefNo(busObject.getDocRefNo());
        ij.setDocumentClass(busObject.getClass().getSimpleName());
        ij.setLines(null);
        for (PurchaseInvoiceLineItem lineItem : busObject.getLineItems()) {
            InventoryJournalLine line = new InventoryJournalLine();
            line.setSku(lineItem.getSku());
            line.setQty(lineItem.getQty());
            line.setUom(lineItem.getUom());
            ij.addIJLine(line);

        }
//        toSave.add(ij);
        
        thingsToUpdate  = new ArrayList();
        thingsToUpdate.add(ij);       
        return super.updateData(busObject,thingsToCreate,thingsToUpdate,thingsToDelete); 
    }
    
   public void delete(PurchaseInvoice selectedObject) {
        final PurchaseInvoice obj = getDao().find(selectedObject.getId());
        EntityManager em = startTransaction();
        getDao().delete(em, obj);
        final InventoryJournal inv =  (InventoryJournal) inventoryJournalDAO.getByPropertySR("refEntityID",obj.getId());
        if(inv!=null)//throw exception if null ???
            inventoryJournalDAO.delete(em, inv);
        commit(em);
       
   }


    

}
