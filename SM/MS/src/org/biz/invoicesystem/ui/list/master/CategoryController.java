/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.ui.list.master;

import org.biz.invoicesystem.entity.master.Category;
import org.biz.invoicesystem.service.master.CategoryService;
import org.components.windows.UIController;

/**
 *
 * @author jawa
 */
public class CategoryController extends UIController<Category>{
    
    
    public CategoryController() {
        super();
        setService(new CategoryService());      
    }
    
        
    public void initUI(){
        CategoryFrame cf = new CategoryFrame();
        detailView  = cf.getDetail();
        listView = cf.getMaster();
        setDetailView(detailView);        
        setListView(listView,getQueryForPage());
        detailView.config();
        setUIFrame(cf);
        cf.revalidate();
        cf.repaint();
    }

    

    
    
    public void executeSearchForCustom() {
        getQueryForPage().executeToFirstPageTask();
    }


    //menu structure 
    // controller 
    // parent 
    // child
    // further detail , master
    
}
