/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.dao.master;

import java.util.ArrayList;
import java.util.List;
import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.master.Supplier;

/**
 *
 * @author mjawath
 */
public class SupplierDAO extends GenericDAO<Supplier> {

    public SupplierDAO() {
        setCls(Supplier.class);
    }

    public Supplier findSupplierByCode(String suppliercode) {
        Supplier i = null;
        try {
//ExecuteQuery("");

            List<Supplier> lst = ExecuteQuery("select i from Supplier i Where i.code= '" + suppliercode + "'");
            for (Supplier c : lst) {
                i = c;
            }

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
        }

        return i;
    }

    public List loadComboItems() {

        List lst = new ArrayList<Object>();
        //   EntityManager em=createEmNew();
        try {
            lst = ExecuteQuery("select  s.type ,s.title From Supplier s ");

            System.out.println("lst size " + lst.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    
    public List<Supplier> byCode(String code) {
        String cus = "  c.name like '" + code + "%' ";
        List<Supplier> lst = pagedData( cus, 0);
        return lst;
    }
}
