/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.erp.inventory.ui.detail;

import com.components.custom.TextFieldWithPopUP;
import java.util.List;
import org.biz.app.ui.util.QueryManager;
import org.biz.app.ui.util.StringUtility;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.service.inventory.InventoryJournalService;
import org.biz.invoicesystem.ui.transactions.GRNFrame;
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

    
    
    
    
}
