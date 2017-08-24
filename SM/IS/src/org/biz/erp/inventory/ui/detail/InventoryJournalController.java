/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.erp.inventory.ui.detail;

import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.service.inventory.InventoryJournalService;
import org.biz.invoicesystem.ui.transactions.GRNFrame;
import org.components.windows.UIController;

/**
 *
 * @author jawa
 */
public class InventoryJournalController extends UIController<InventoryJournal>{

    public InventoryJournalController() {
        super();
        setService(new InventoryJournalService());
    }

    @Override
    public void initUI() {
        InventoryJournalFrame frame = new InventoryJournalFrame();        
        setUIFrame(frame);
        frame.initPAging();
    }
    
    
    
    
    
}
