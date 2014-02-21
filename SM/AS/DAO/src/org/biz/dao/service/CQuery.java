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

    public CQuery(Query qry) {
    this.query=qry;
    }
    
    
    private Query query;
    
    public Query getQuery(){
//            javax.persistence.Query qu = GenericDAOUtil.getQuery(sq,param);
            return query;
    }
    
    public void setQuery(){
    
    }
    
}
