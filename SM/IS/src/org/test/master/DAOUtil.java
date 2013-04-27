/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.test.master;

import org.biz.invoicesystem.dao.master.ItemDAO;
import org.dao.util.JPAUtil;

/**
 *
 * @author d
 */
public class DAOUtil {
    public static void main(String[] args) {
        //REbuilding 
        //REbuilding DATABASE check for static initialisations
//        JPAUtil.createEMFWithCustomProperties();
        //create test Items
        new ItemDAO().createTestData();
        
    }
}
