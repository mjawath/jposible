 
package org.biz.invoicesystem.dao.master;

import java.util.ArrayList;
import java.util.List;
import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.master.Supplier;

/**
 *
 * @author mjawath
 */
public class RolesDAO extends GenericDAO<Supplier> {

    public RolesDAO() {
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
            lst = ExecuteQuery("select  s.securityRole  From Staff s ");

            System.out.println("lst size " + lst.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
}
