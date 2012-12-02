package org.biz.invoicesystem.dao.master;

import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.master.Product;

/**
 *
 * @author mjawath
 */
public class ProductDAO extends GenericDAO<Product>{
    
    public ProductDAO() {
    setCls(Product.class);
    }


}
