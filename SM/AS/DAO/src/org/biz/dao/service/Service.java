/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.dao.service;

import org.biz.dao.util.EntityService;

/**
 *
 * @author mjawath
 */
public class Service {

    EntityService es;
    Cache cache;
    GenericDAO dao;
    int pageSize;

    public Service() {
    }

    /**
     *
     */
    public Service(String dbname) {
        es = EntityService.getEntityService();
    }


    public GenericDAO getDao() {
//        
        if(es==null){
        es = EntityService.getEntityService();
        }
        if (dao == null) {
            dao = new GenericDAO();
        }
        return dao;
    }

    public Cache getCache() {
        return getDao().getCache();
    }

    public void moveToNextPage(String queryname, int currentpage) {
        getDao().pagedData(queryname, currentpage);

    }

    public void getNextPage(String qryname) {
       //current page ??
         //get count 1650
        //get rowsperpage  100      --> 17 pages
//        dao.getpagedDetail(qry);
        
        // get query string  by query name == > 
        // get current page from page panel r page object 
        // if can navigate goto  next page 
//        String s =dao.getqry(qry);
//        int page = dao.getCache().getbySpecialKey(qry, s, pageSize).getCurrentpage(qry);
//        if(possilbe ){
//        goto next page(s,page);
//        }
////                
//        String qry = getDao().getquery(qryname);
//        int cpageno=getDao().getCupage(qryname);
//        getDao().getcount(qry);        
//        getDao().pagedData(qry, cpageno);
        
         getDao().getNextPage(qryname);
    }

    public void getPreviousePage(String qry, int page) {
        //get last page
         getDao().getPreviousPage(qry);
    }

    public void getLastPage(String qry) {
         getDao().lastPage(qry);
    }

    public void getFirstPage(String qry) {
       getDao().firstPage(qry);
    }
    
    public String getUniqueKey(){
            return EntityService.getKey("Test");
    }
    
    public void initUI(){
    }
}
