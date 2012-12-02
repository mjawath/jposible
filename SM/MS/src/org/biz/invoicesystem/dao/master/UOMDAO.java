/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.dao.master;

import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.master.UOM;

/**
 *
 * @author mjawath
 */
public class UOMDAO extends GenericDAO<UOM>{

    
    public UOMDAO() {
    setCls(UOM.class);
    }


}
