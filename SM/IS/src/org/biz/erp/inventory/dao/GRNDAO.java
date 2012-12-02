/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.erp.inventory.dao;

import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.inventory.GRN;

/**
 *
 * @author mjawath
 */
public class GRNDAO extends GenericDAO<GRN>{

    
    public GRNDAO() {
    setCls(GRN.class);
    }


}
