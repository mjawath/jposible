/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.erp.inventory.ui.detail;

import java.util.Iterator;
import java.util.List;
import javax.swing.JComponent;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.app.ui.util.QueryManager;
import org.biz.app.ui.util.StringUtility;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.service.inventory.InventoryJournalService;
import org.components.windows.SearchQueryUIPanel;
import org.components.windows.UIController;

/**
 *
 * @author jawa
 */
public class InventoryJournalController extends UIController<InventoryJournal>{
    private InventoryJournalService ijs;

    public InventoryJournalController() {
        super();
        setService(new InventoryJournalService());
        ijs =(InventoryJournalService)getService();
    }

    @Override
    public void initUI() {
        InventoryJournalFrame frame = new InventoryJournalFrame();        
        setUIFrame(frame);
        frame.initPAging(this);
    }
    
    


    private class SearchQueryManager extends QueryManager {

        public Long executeCountQuery() {
            return ijs.getCountOfSummeryOfItems();

        }
        @Override
        public List executeQuery(int page) {
            //what ever we can implement here 
            return ijs.getSummeryOfItems(page);
        }
    }

    protected SearchQueryManager mmm = new SearchQueryManager();

 public QueryManager getQuerySummer(){
 return  new SearchQueryManager();
 }
    public List executeQuery(int page) {

        return ((InventoryJournalService)getService()).getInventorySummeryForMonth();
//        return getService().getByWhere(where);        
    }

    public Long executeCount() {
        SearchQueryUIPanel searchUI = (SearchQueryUIPanel) getListView().getSearchUI();
        String searchTextFieldValue = searchUI.getSearchTextFieldValue();
        
        if (StringUtility.isEmptyString(searchTextFieldValue)) {
            return getService().getDao().getAllCount();
        }
        String where = "";
        String attribute = searchUI.getAttribute();        
        where = searchTextFieldValue != null ? " c."+attribute+" like '" + searchTextFieldValue + "%' " : "";

//        String name = qmp.get("name");
//        where = name != null ? " c.name like " + name + " " : "";
        return getService().getCountOfByWhere(where);        
    }

    
    public JComponent onSalesInvoiceLineItemPriceChanged(InventoryJournalLineItemUI salesLineUI) {

        InventoryJournalLine salesInvoiceLineItem = salesLineUI.panelToData();
        
            final JComponent inValidComponent = salesLineUI.getInValidComponent();
        if (inValidComponent != null) {
            return inValidComponent;
        }
        salesLineUI.setDataToPanelIFNotFocused(salesInvoiceLineItem);

        InventoryJournal currentBusObject = detailView.uiToData();
        ///get the current line and update it        

        InventoryJournalLine selectedSL = (InventoryJournalLine) salesLineUI.getSelectedLineObject();
                if(salesInvoiceLineItem.getUom() ==null && salesInvoiceLineItem.getSku()!=null){
            salesInvoiceLineItem.setUom(salesInvoiceLineItem.getSku().getItem().getPrimaryUOM());
        }
        currentBusObject.addOrUpdateLine(selectedSL, salesInvoiceLineItem);
        
        /*if(this is a valid entry )
            then add to table 
        else revert or alert 
         */
        detailView.setVisualDataToUI(currentBusObject);
        salesLineUI.clearLineUI();
//        salesLineUI.requestFocus();
        return salesLineUI;
    }
    
    
    protected boolean isValideEntity() {
    
        

       InventoryJournal busObject = detailView.uiToData();
        if (busObject == null) {
            return false;
        }
        if (busObject.getLines() == null || busObject.getLines().isEmpty()) {
//            tuom.requestFocus();
            return false;
        }
        
        if (busObject.getWarehouse() == null) {
            MessageBoxes.errormsg(detailView, "Please provide a valid store room", "Invalid data");
//            twarehouse.requestFocus();
            return false;
        }
            
        for (Iterator<InventoryJournalLine> it = busObject.getLines().iterator(); it.hasNext();) {
            InventoryJournalLine inventoryJournalLine = it.next();
            if(!inventoryJournalLine.isValidLine())it.remove();

        }
        return true;
    }
    
    
}
