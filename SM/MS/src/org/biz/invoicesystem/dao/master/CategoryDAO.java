/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.dao.master;

import java.util.Date;
import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.master.Category;

/**
 *
 * @author mjawath
 */
public class CategoryDAO extends GenericDAO<Category>{

    public CategoryDAO() {
        setCls(Category.class);
        orderby = "c.editedDate  desc , c.savedDate  desc ";
    }

    public void createTestData() {
        
        for(int x=0;x<50;x++){
        Category cat= new Category();
        cat.setId(x+"ID");
        cat.setCode(x+"Code");
        cat.setDescription(x+"de");
        cat.setSavedDate(new Date());
        cat.setEditedDate(new Date());
        this.save(cat);
        }
    }

}
