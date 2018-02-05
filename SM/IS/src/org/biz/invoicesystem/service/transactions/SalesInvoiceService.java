/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.service.transactions;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.biz.MS_Static;
import org.biz.app.ui.util.BizException;
import org.biz.dao.service.Service;
import org.biz.entity.BusObj;
import org.biz.erp.inventory.dao.InventoryJournalDAO;
import org.biz.erp.ui.transaction.detail.InvoiceUI;
import org.biz.erp.ui.transactions.posted.PostedInvoicesListUI;
import org.biz.invoicesystem.dao.transactions.SalesInvoiceDAO;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.biz.invoicesystem.service.master.ItemService;

/**
 *
 * @author mjawath
 */
public class SalesInvoiceService extends Service<SalesInvoice>{
    
    private SalesInvoiceDAO dao;
    private InventoryJournalDAO inventoryJournalDAO;
    private InvoiceUI invoiceUI;
    private PostedInvoicesListUI invoicesListUI;

    public SalesInvoiceService() {
        super();
        dao = new SalesInvoiceDAO();
        inventoryJournalDAO = new InventoryJournalDAO();
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
    protected SalesInvoice saveData(SalesInvoice busObject,List thingsToCreate,
        List thingsToUpdate,List thingsToDelete) {
        System.out.println("sales invoice level preCreate");
        final EntityManager em = startTransaction();
        
        busObject.calculateTotal();
        if(busObject.getShop()== null){
            Shop defaultShop = MS_Static.getDefaultShop();
            busObject.setShop(defaultShop);
        } 
        if(busObject.getWarehouse()== null){
            Warehouse defaultShop = MS_Static.getDefaultWareHouse();
            busObject.setWarehouse(defaultShop);
        }
        if(busObject.getWarehouse()== null){
            Warehouse defaultShop = MS_Static.getDefaultWareHouse();
            busObject.setWarehouse(defaultShop);
        }
        persist(em, busObject);
        
        InventoryJournal ij = new InventoryJournal();
        ij.setRefEntityID(busObject.getId());
        ij.setCode(busObject.getCode());
        ij.setDocRefNo(busObject.getDocRefNo());
        ij.setShop(busObject.getShop());
        ij.setWarehouse(busObject.getWarehouse());
        ij.setDocumentClass(busObject.getClass().getSimpleName());
        for (SalesInvoiceLineItem lineItem : busObject.getLineItems()) {
            InventoryJournalLine line = new InventoryJournalLine();
            line.setSku(lineItem.getSku());
            line.setQty(-lineItem.getQty());
            line.setUom(lineItem.getUom());            
            ij.addIJLine(line);

        }
        
        thingsToCreate  = new ArrayList();
        thingsToCreate.add(ij);
        persist(em,ij);
        commit(em);
        
        return busObject;
    }
    
    @Override
    protected SalesInvoice updateData(SalesInvoice busObject,List thingsToCreate,
            List thingsToUpdate,List thingsToDelete) {
        InventoryJournal ij = (InventoryJournal) inventoryJournalDAO.getByPropertySR("refEntityID",busObject.getId());
        if(ij==null) throw new BizException("PurchaseInvoicesevrice updateData ");
        busObject.calculateTotal();

        
        ij.setCode(busObject.getCode());
        ij.setDocRefNo(busObject.getDocRefNo());
        ij.setDocumentClass(busObject.getClass().getSimpleName());
        ij.setLines(null);
        if (busObject.getShop() == null) {
            Shop defaultShop = MS_Static.getDefaultShop();
            busObject.setShop(defaultShop);
        }
        if (busObject.getWarehouse() == null) {
            Warehouse defaultShop = MS_Static.getDefaultWareHouse();
            busObject.setWarehouse(defaultShop);
        }
        
        for (SalesInvoiceLineItem lineItem : busObject.getLineItems()) {
            InventoryJournalLine line = new InventoryJournalLine();
            line.setSku(lineItem.getSku());
            line.setQty(-(lineItem.getQty()));
            line.setUom(lineItem.getUom());
            ij.addIJLine(line);

        }
//        toSave.add(ij);
        
        thingsToUpdate  = new ArrayList();
        thingsToUpdate.add(ij);       
        return super.updateData(busObject,thingsToCreate,thingsToUpdate,thingsToDelete); 
    }
        
   public void delete(SalesInvoice selectedObject) {
        final SalesInvoice obj = getDao().find(selectedObject.getId());
        EntityManager em = startTransaction();
        getDao().delete(em, obj);
        final InventoryJournal inv = (InventoryJournal) inventoryJournalDAO.getByPropertySR("refEntityID",obj.getId());
        if(inv!=null)
            inventoryJournalDAO.delete(em, inv);
        commit(em);
       
   }

    
    
}
