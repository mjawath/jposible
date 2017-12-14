/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.ui.list.master;

import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.service.master.ItemService;
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
   
    
    public void initUI(){
        ItemMasterFrame frame = new ItemMasterFrame();                
        setUIFrame(frame);
    }
   
    
}
