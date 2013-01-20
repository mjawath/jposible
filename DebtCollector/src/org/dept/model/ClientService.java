/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dept.model;

import org.biz.dao.service.Service;

/**
 *
 * @author d
 */
public class ClientService extends Service{
    
        
    ClientDAO dao;

    public ClientService() {
    dao = new ClientDAO();
    }

    public ClientDAO getDao() {
        return dao;
    }

}
