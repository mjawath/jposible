package org.biz.invoicesystem.dao.master;

import java.util.ArrayList;
import java.util.List;
import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.master.Staff;

public class StaffDAO extends GenericDAO<Staff> {

    public StaffDAO() {
        setCls(Staff.class);
    }

    public List<Object[]> loadComboItems() {

        List<Object[]> lst = new ArrayList<Object[]>();
        //   EntityManager em=createEmNew();
        try {
            lst = ExecuteNativeQuery("select  c.securityRole , c.initial From Staff c ");

            System.out.println("lst size " + lst.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    public Staff findStaffByCode(String customercode) {
        Staff i = null;
        try {
//ExecuteQuery("");

            List<Staff> lst = ExecuteQuery("select i from Staff i Where i.code= '" + customercode + "'");
            for (Staff c : lst) {
                i = c;
            }

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

        }

        return i;
    }

    public Staff findStaffByUsername(String username, String shopname) {
        Staff i = null;
        try {
//ExecuteQuery("");

            List<Staff> lst = ExecuteQuery("select i from Staff i Where i.username= '" + username + "'");
            for (Staff c : lst) {
                i = c;
            }

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

        }

        return i;
    }

    public List<Staff> byCode(String code) {
        String cus = "  c.name like '" + code + "%' ";
        List<Staff> lst = pagedData(cus, 0);
        return lst;
    }

    public static void main(String[] args) {
        try {
            StaffDAO g = new StaffDAO();

            List<Staff> lst = g.getAll();
            long start = System.currentTimeMillis();
            for (Staff lst1 : lst) {
                System.out.println("printing staff " + lst1.getCode());
            }

            System.out.println(System.currentTimeMillis() - start);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
