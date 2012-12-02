/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.service.master;

import org.biz.dao.service.Service;
import org.biz.invoicesystem.dao.master.ProductDAO;

/**
 *
 * @author mjawath
 */
public class ProductService extends Service{
    ProductDAO dao;

    public ProductService() {
    dao = new ProductDAO();
    }

    public ProductDAO getDao() {
        return dao;
    }

}
