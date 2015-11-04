package org.biz.invoicesystem.service.inventory;

import org.biz.dao.service.Service;
import org.biz.erp.inventory.dao.TransferOrderDAO;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.entity.inventory.TransferOrder;
import org.biz.invoicesystem.entity.inventory.TransferOrderLineItem;

/**
 *
 * @author d
 */
public class TransferOrderService extends Service {

    TransferOrderDAO dao;

    public TransferOrderService() {
        dao = new TransferOrderDAO();
    }

    public TransferOrderDAO getDao() {
        return dao;
    }

    public void createInventoryJournal(TransferOrder invoice) {
//        invoice.setId(EntityService.getKeys());
        //inventory journal for out
        InventoryJournal ijOut = new InventoryJournal();
//        ijOut.setId(EntityService.getKeys());
        for (TransferOrderLineItem toli : invoice.getLineItemList()) {
            InventoryJournalLine ijl = new InventoryJournalLine();
            ijl.setId(toli.getId());
//            ijl.setDescription(toli.getId());
            ijl.setItem(toli.getItem());
            ijl.setQty(toli.getQty());
            ijl.setShop(invoice.getShopFrom());
            ijl.setItemMark(toli.getItemMark());
            ijl.setWarehouse(toli.getWareHouseFrom());
            ijl.setUom(toli.getUom());

            ijOut.addIJLine(ijl);
//            ijl.setShop(invoice.gets);
//            jt
            //shop ware houses
//            ijl.setUom(sl.getQty());
        }
        //inventory journal for in
        InventoryJournal ijIn = new InventoryJournal();
//        ijOut.setId(EntityService.getKeys());
        for (TransferOrderLineItem toli : invoice.getLineItemList()) {
            InventoryJournalLine ijl = new InventoryJournalLine();
            ijl.setId(toli.getId());
//            ijl.setDescription(toli.getId());
            ijl.setItem(toli.getItem());
            ijl.setQty(toli.getQty());
            ijl.setShop(toli.getShopTo());
            ijl.setItemMark(toli.getItemMark());
            ijl.setWarehouse(toli.getWareHouseTo());
            ijl.setUom(toli.getUom());

            ijIn.addIJLine(ijl);
//            ijl.setShop(invoice.gets);
//            jt
            //shop ware houses
//            ijl.setUom(sl.getQty());
        }
        dao.save(invoice, ijOut, ijIn);
    }
}
