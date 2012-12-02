/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.service.master;

import org.biz.dao.service.Service;
import org.biz.invoicesystem.dao.master.StaffDAO;

/**
 *
 * @author Administrator
 */
public class StaffService  extends Service  {
    
   private StaffDAO dao;

    public StaffService() {
        try {
    dao=new StaffDAO();
    
        } catch (Exception e) {
    e.printStackTrace();
        }
    }

    /**
     * @return the dao
     */
    public StaffDAO getDao() {
        return dao;
    }
   
   
    
}
