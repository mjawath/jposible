/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.util;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import org.biz.dao.service.CQuery;
import org.biz.dao.service.GenericDAO;
import org.biz.dao.service.Service;
import org.components.test.ResultPage;

/**
 *
 * @author yy
 */
public  class QueryManager {
    
    private final static int noofrows=GenericDAO.noofrows;
    
    private int currentPage;
    private int noOfRowsPerPage=noofrows;
    private long count = 0;
    private Service service;
    private int noOfPages;
    protected List lastListPage;
    protected List<UIListener> uiListners = new ArrayList();

    
    
    public CQuery getCQuery() {
        return null;
    }

    public CQuery getCountQuery() {

        return null;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int cur) {
        this.currentPage = cur;
    }
    
    public void fetchPage(int x){
        //goto next page       
        
    }

    public void getNextPage() {
        int next = currentPage + 1;
        getCount();
        if (next > getNoOfPages()) {
            return;
        }
        lastListPage = executeQuery(next);
        currentPage++;
    }
    

    public Long count() {
        return count;//=service.getCount(getCQuery());
    }

    public void getPrePage() {
        int page = currentPage - 1;
        if (page < 0) {
            return;
        }
        getCount();
        lastListPage = executeQuery(page);
        currentPage = page;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int noOfRows() {
        return service.getDao().getNoOfRows();
    }

     public void getFirstPage() {
        int next = 0;
        getCount();
//        if(next>count)return null
//        if (service == null) {
//            return;
//        }
        lastListPage = executeQuery(next);
        currentPage = 0;
    }
    

    public List executeQuery(int page){    
         lastListPage = service.moveToPage(getCQuery(),page);
         return lastListPage;
    }
    
    public void getLastPage() {
        int next = noOfPages - 1;
        getCount();
        lastListPage = executeQuery(next);
        currentPage = next;
    }

    public Service getService() {
        return service;
    }

    public void getCount() {       
        count = executeCountQuery();
    }

    public Long executeCountQuery() {
        if(service==null)return -1l;
        return service.getCount(getCountQuery());
    }

    public void createPages() {
        noOfPages = (int) Math.ceil((float) count / noOfRowsPerPage);
    }

    public int getListSize() {
        if (lastListPage == null) {
            return -1;
        }
        return lastListPage.size();
    }

    public int getNoOfPages() {
        createPages();
        return noOfPages;
    }

    public List getList() {
        return lastListPage;
    }
    
    public int getNoOfRowsPerPage(){
    return noOfRowsPerPage;
    }

    /**
     * this method will be called after pagination events excecuted on EDt so be
     * causes
     */
    public void postQuery(Object objs) {

    }
    
    
    //*** new implementation
    public void goToFirstPage(){
        
        //get count query
        //get query
        // execute query 
        // update status 
        //update ui
    
        command.start(0);
    
    }
    
    public void notifyObservers(){
    
                                      
    }
    
    public void getListFromController(int x){
        
        
    }
    
    private void notifyGUIListners(){
    
        for (UIListener ui : uiListners) {
            ui.updateUI(this);
        }
        
    }
    
    
    
    private Command command = new Command() {

        public Object doBackgroundTask(Object... objs) {

            ResultPage rp = null;
            long count = QueryManager.this.executeCountQuery();
            int noOfRowsPerPage = QueryManager.this.getNoOfRowsPerPage();
            int noOfPages = (int) Math.ceil((float) count / noOfRowsPerPage);
            if (objs != null && objs.length <= 0) {
                return null;
            }
//            if(objs[0]  instanceof ActionEvent  && ((ActionEvent)objs[0]).getSource()==1){
            int pageType = (int) objs[0];
            int index = 0;
            if (pageType == 0) {
                index = 0;
                Object obj = QueryManager.this.executeQuery(index);
                rp = new ResultPage((int) count, noOfPages, index, obj);
                return rp;
            }

            return null;
        }

        public void doResultTask(Object... objs) {
            if (objs == null) {
                return;
            }
            ResultPage l = (ResultPage) objs[0];
            if (l == null) {
                return;
            }
            QueryManager.this.notifyGUIListners();
        }

    };


}

interface UIListener {

    public void updateUI(QueryManager ui);
    
}
