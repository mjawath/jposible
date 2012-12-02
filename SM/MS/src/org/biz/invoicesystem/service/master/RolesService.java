 
package org.biz.invoicesystem.service.master;

import org.biz.dao.service.Service;
import org.biz.invoicesystem.dao.master.RolesDAO;
  
public class RolesService extends Service {
    
    RolesDAO dao;

    public RolesDAO getDao() {
        return dao;
    }

   
    
    public RolesService() {
    dao=new RolesDAO();
    }

    
}
