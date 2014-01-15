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

    
    public List moveToPage(String qry,Object [] param, int pageNo) {
        return getDao().pagedData(qry, pageNo,param);  

    }
    
        
    public Long getCount(String qry,Object [] param){
        return getDao().getCount(qry ,param);
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
    
    public void initUI(){
    }
    
    public void PrintTracer(String msg){
//        Tracer.printToOut("servies are set ");
        System.out.println("should move print traeer "+msg);
    }
    
    public int getNoOfRows(){
    return getDao().getNoOfRows();
    }
    
    public Object getByCode(String qry){
        return getDao().getByCodex(qry);
    }
    
    
    public List getByCodeLike(String qry){
        return getDao().getByCodeLike(qry);
    }
    
    
}
