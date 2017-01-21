/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.dao.service;

import java.util.List;
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

    
    public List moveToPage(CQuery qry, int pageNo) {
        return getDao().pagedData(qry.getQuery(), pageNo);  

    }
    
        
    public Long getCount(CQuery qry){
        if(qry==null)return 0l;
        return getDao().getCount(qry.getQuery());
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
    
    public Object findByID(String id ){
        return dao.find(id);
    }
    
    public Object findByCode(String code ){
        return dao.getByCode(code);
    }
    
       
    public String getUniqueKey(){
            return EntityService.getKey("Test");
    }

    
    public void PrintTracer(String msg){
//        Tracer.printToOut("servies are set ");
        System.out.println("should move print traeer "+msg);
    }
    
    public int getNoOfRows(){
    return getDao().getNoOfRows();
    }
    
    public <T> T getByCode(String qry){
        return (T) getDao().getByCodex(qry);
    }
    
    public List getByWhere(String conditions) {
        return getDao().getByWhere(conditions);
    }
    
    public long getCountOfByWhere(String conditions) {
        return getDao().getCountOfByWhere(conditions);
    }
    
    public List getByCodeLike(String qry){
        return getDao().getByCodeLike(qry);
    }
    public CQuery getQueryByCodeLike(String qry){
        return getDao().getQueryByCodeLike(qry);
    }
    
     public CQuery getCountQueryByCodeLike(String qry){
         
        return getDao().getCountQueryByCodeLike(qry);
    }
     
    public long getCountByCodeLike(String qry){
         
        return getCount(getDao().getCountQueryByCodeLike(qry));
    }
     
     public List getByCodeLike(int page,String qry){
        return getDao().getByCodeLike(page,qry);
    }
   }
