/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.dao.master;

import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.master.Category;

/**
 *
 * @author mjawath
 */
public class CategoryDAO extends GenericDAO<Category>{

    
    public CategoryDAO() {
    setCls(Category.class);
    }


}
