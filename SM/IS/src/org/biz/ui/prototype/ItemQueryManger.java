/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.ui.prototype;

import java.util.List;
import org.biz.app.ui.util.QueryManager;
import org.components.windows.SearchQueryUIPanel;

/**
 *
 * @author jawa
 */
public class ItemQueryManger extends QueryManager {
    
    private SearchQueryUIPanel searchQueryUIPanel;
    
    public ItemQueryManger(SearchQueryUIPanel ui) {
        searchQueryUIPanel=ui;
    }
   
    
    
    
    @Override
        public List executeQuery(int page) {
         //what ever we can implement here 
            
            
            return null;
        }
    
   

}
