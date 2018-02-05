/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.dao.master;

import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.master.SKU;

/**
 *
 * @author jawa
 */
public class SKUDAO extends GenericDAO<SKU> {

    public SKUDAO() {
        super();
        setCls(SKU.class);
    }

}
