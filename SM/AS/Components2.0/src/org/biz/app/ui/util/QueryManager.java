/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.util;

import java.util.List;
import org.biz.dao.service.CQuery;
import org.biz.dao.service.Service;

/**
 *
 * @author yy
 */
public abstract class QueryManager {

    private     int currentPage;
    private     int noOfRowsPerPage;
    private     long count=0;
    private Service service;
    private     int noOfPages;
    protected List lastListPage;
    
    public  CQuery getCQuery(){
    return null;
    }

    public  CQuery getCountQuery(){
    
        return null;
    }
    
    public int getCurrentPage() {
        return currentPage;
    }
    
    public void setCurrentPage(int cur) {
       this.currentPage=cur;
    }
    
    public void getNextPage(){
        int next=currentPage+1;
        if(next>count)return;
        lastListPage=service.moveToPage(getCQuery(), next);
        currentPage++; 
    }
    
    public Long count(){
        return count;//=service.getCount(getCQuery());
    }
    
    public void getPrePage(){
        int page=currentPage-1;
        if(page<0)return ;
        lastListPage=service.moveToPage(getCQuery(),page);
        currentPage=page;
    }   
    
    public void setService(Service service) {
        this.service = service;
    }
    
    public int noOfRows(){
    return service.getDao().getNoOfRows();
    }
    
    public void getFirstPage(){
        int next=0;
        getCount();
//        if(next>count)return null
        if(service==null)return;
        lastListPage=service.moveToPage(getCQuery(), next);
        currentPage=0;

    }
   
    public void getLastPage() {
        int next=noOfPages-1;
        lastListPage=service.moveToPage(getCQuery(), next);
        currentPage=noOfPages;
    }

    
    public Service getService(){
        return service; 
    }
    
    public void getCount() {
        if (service == null) {
            count = 0;
            return;
        }
         count = service.getCount(getCountQuery());
    }
    
    public void createPages(){
         noOfPages= (int) Math.ceil((float) count / (float) service.getNoOfRows());
    }
    
    public int getListSize(){
    if(lastListPage==null)return -1;
    return lastListPage.size(); 
    }
    
    public int getNoOfPages(){
        createPages();
    return noOfPages;
    }
    
    public List getList(){
    return lastListPage;
    }
    
    /**
     * this method will be called after pagination events excecuted on EDt so be causes 
     */
    public void postQuery(){
    
    }
}
