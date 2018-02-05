package org.biz.invoicesystem.dao.master;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.master.Customer;

public class CustomerDAO extends GenericDAO<Customer> {

    public CustomerDAO() {
        setCls(Customer.class);
    }

    public List loadComboItems() {

        List lst = new ArrayList<Object>();
        //   EntityManager em=createEmNew();
        try {
            lst = ExecuteQuery("select  c.type ,c.title From Customer c ");

            System.out.println("lst size " + lst.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    public Customer findCustomerByCode(String customercode) {
        Customer i = null;
        try {
//ExecuteQuery("");

            List<Customer> lst = ExecuteQuery("select i from Customer i Where i.code= '" + customercode + "'");
            for (Customer c : lst) {
                i = c;
            }

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
        }

        return i;
    }

    public List<Customer> byCode(String customercode) {
        String cus = "  c.code like '" + customercode + "%' ";
        List<Customer> lst = pagedData(cus, 0);
        return lst;
    }

    public static void main(String[] args) {
        try {
            CustomerDAO g = new CustomerDAO();

            g.loadComboItems();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
