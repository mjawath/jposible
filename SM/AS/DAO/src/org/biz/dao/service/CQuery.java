/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.dao.service;

import javax.persistence.Query;

/**
 *
 * @author jawath
 */
public class CQuery  {
    private String qryString; 

    public CQuery(Query qry) {
    this.query=qry;
    }
    
    public CQuery(String qry,Object[] param) {
     qryString=qry;
        setQuery(qry, param);
    }
    
    private Query query;
    
    public Query getQuery(){
//            javax.persistence.Query qu = GenericDAOUtil.getQuery(sq,param);
            return query;
    }

    public String getQryString() {
        return qryString;
    }
    
    
    
    public void setQuery(String qry,Object[] param){
             qryString=qry;

            query = GenericDAOUtil.getQuery(qry,param);
            
    }
    
}
