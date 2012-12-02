/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.dao.util;

import javax.persistence.EntityManager;

/**
 *
 * @author nnjj
 */
public class  GetTypedResultQuery <T>{
    
    public T getResult(String qry,EntityManager em){
     
        em.createQuery(qry);
        return null;
    } 
}
