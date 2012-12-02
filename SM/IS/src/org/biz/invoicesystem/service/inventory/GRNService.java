/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.service.inventory;

import org.biz.dao.service.Service;
import org.biz.erp.inventory.dao.GRNDAO;

/**
 *
 * @author mjawath
 */
public class GRNService extends Service {
    GRNDAO dao;

    public GRNService() {
    dao = new GRNDAO();
    }

    @Override
    public GRNDAO getDao() {
        return dao;
    }

}
