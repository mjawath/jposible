/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.ui.list.master;

import java.util.List;
import org.biz.app.ui.util.QueryManager;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.service.master.ItemService;
import org.biz.master.ui.ItemMasterUI2;
import org.components.windows.SearchQueryUIPanel;
import org.components.windows.UIController;

/**
 *
 * @author jawa
 */
public class ItemController extends UIController<Item>{    
    
//    private ItemQueryManger iqm =new ItemQueryManger();
//    private simpleSearchListener sl =new simpleSearchListener();

    public ItemController() {
        super();
        setService(new ItemService());      
    }


   
    private class MYQueryManger extends QueryManager {

        public MYQueryManger() {
        }

        public Long executeCountQuery() {
            return executeList();

        }

        @Override
        public List executeQuery(int page) {
            //what ever we can implement here 
            return myexecuteQuery(page);
        }

    }
    
    
    public void initUI(){
        ItemMasterUI2 detail = new ItemMasterUI2();
        ItemMasterUI list = new ItemMasterUI();
        setDetailView(detail);
        setListView(list,mmm);
        detail.config();        
    }
    
    
         
    MYQueryManger mmm = new MYQueryManger();
            
     
     

     public void executeSearchForCustom(){
         
         mmm.executeToFirstPageTask();
     }
     
     
         
     public List myexecuteQuery(int page) {  
        
        String txt =  String.valueOf(listView.getSearchUI().getQueryParameterMap().get(SearchQueryUIPanel.QRY));        
        final List byCodeLike = getService().getByCodeLike(page, txt);        
        return byCodeLike;
    }
     private Long executeList(){
        String txt =  String.valueOf(listView.getSearchUI().getQueryParameterMap().get(SearchQueryUIPanel.QRY));
        return getService().getCountByCodeLike(txt);        
     }

    
}
