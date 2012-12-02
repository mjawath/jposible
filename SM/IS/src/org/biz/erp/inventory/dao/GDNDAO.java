/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.erp.inventory.dao;

import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.inventory.GDN;

/**
 *
 * @author mjawath
 */
public class GDNDAO extends GenericDAO<GDN>{

    
    public GDNDAO() {
    setCls(GDN.class);
    }


}
