/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.util;

import java.util.List;
import org.biz.dao.service.CQuery;
import org.biz.dao.service.GenericDAO;
import org.biz.dao.service.Service;

/**
 *
 * @author yy
 */
public abstract class QueryManager {
    
    private final static int noofrows=GenericDAO.noofrows;
    
    private int currentPage;
    private int noOfRowsPerPage=noofrows;
    private long count = 0;
    private Service service;
    private int noOfPages;
    protected List lastListPage;

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
        if (service == null) {
            return;
        }
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

    /**
     * this method will be called after pagination events excecuted on EDt so be
     * causes
     */
    public void postQuery(Object objs) {

    }
}
