/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package research.ui.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.biz.dao.service.GenericDAO;
import org.biz.dao.util.EntityService;
import org.biz.invoicesystem.entity.master.*;
import org.dao.util.JPAUtil;

/**
 *
 * @author nnjj
 */
public class dbCreation {

    public static void main(String[] args) {

//        ItemDAO d=new ItemDAO();
//        Item x=new Item();
//        x.setId("nn");
//        UOM u=new UOM();
//        u.setId("xx");
//        u.setSimbol("123");
//        x.addUOMorUpdate(u);
//        d.update(x);
//      Query e=  new SalesInvoiceService().getDao().createQuery("select c from Item c");
//        System.out.println("exe cuting one........");
//        e.getResultList();
//        
//        System.out.println("exe cuting twoooo22...");
//        e.getResultList();
//        System.out.println("don e.................");

//        List s=new SalesInvoiceService().getDao().getAll();
//        for (Object object : s) {
//            SalesInvoice sl=(SalesInvoice)object;
//            Object x=sl.getLineItems();
//            if(x!=null){
//                System.out.println(x);
//            }
//        }
        
        dbCreation db = new dbCreation();
//        db.createDataBase();
        db.createmster();
//           List lsts = new ArrayList();

//            new dbCreation().createCategory();
//        new GenericDAO<Customer>().saveList(lsts);

//        SalesInvoice inv=new SalesInvoice();
//        inv.setId("677fdfd7");
//        new SalesInvoiceService().getDao().save(inv);

//        ItemDAO idao =new ItemDAO();
//        idao.setCls(Item.class);
//        System.out.println("***********"+
//        idao.pagedData(" c.code like ?1  ", 0,new Object[]{"%k"}));
    }

    public void createDataBase() {
        //before call this method should not initialis the emf any where perticularly in static initialisers!!!
        JPAUtil.createEMFWithCustomProperties(true);

    }

    
    public void createmster() {
        System.out.println("Creating master data");

        createShops();
        createWarehouses();
        createCategory();
        createItem();
        createStaff();
        createSuppliers();
    }


    public void createShops() {
        List lsts = new ArrayList();
        Date date = new Date();
        Shop shz = new Shop();
        shz.setId("123");
        shz.setCode("123s");
        lsts.add(shz);
        System.out.println("Created shop ");



        for (int i = 0; i < 10; i++) {

            Shop shx = new Shop();
            shx.setId(EntityService.getKeyStr());
            shx.setCode(EntityService.getKeyStr());
            shx.setSavedDate(date);
            shx.setEditedDate(date);
            lsts.add(shx);
        }
        System.out.println("Created shop List");


        new GenericDAO<Shop>().saveList(lsts);

    }

     public void createItem() {


        List lst22 = new ArrayList();
        for (int i = 0; i < 1500; i++) {
            Item cus = new Item();
            cus.setId(EntityService.getKeyStr());
            cus.setCode(EntityService.getKeyStr());

            cus.setDescription(EntityService.getKeyStr());
            cus.setSavedDate(new Date(System.currentTimeMillis() + i));
            cus.setEditedDate(new Date(System.currentTimeMillis() + i));
            lst22.add(cus);
//            UOM uom = cus.getPrimaryUOM();
//            uom.setId(cus.getId() + "cat");
//            cus.addUOM(uom);
           
            cus.setDepententEntitiesIDs();
        }
        new GenericDAO<Item>().saveList(lst22);
        System.out.println("Created Item List");

    }

    public void createWarehouses() {
        List lstw = new ArrayList();
        for (int i = 0; i < 40; i++) {
            Warehouse cus = new Warehouse();
            cus.setId(EntityService.getKeyStr());
            cus.setCode(EntityService.getKeyStr());
            cus.setSavedDate(new Date(System.currentTimeMillis() + i));
            cus.setEditedDate(new Date(System.currentTimeMillis() + i));
            lstw.add(cus);
        }
        
         Date date = new Date();
        Warehouse shz = new Warehouse();
        shz.setId("123");
        shz.setCode("123s");
        lstw.add(shz);
        System.out.println("warehouse .......... ");

        new GenericDAO<Warehouse>().saveList(lstw);


    }

    public void createCustomers() {
        List lstx = new ArrayList();
        for (int i = 0; i < 1500; i++) {
            Customer cus = new Customer();
            cus.setId(EntityService.getKeyStr());
            cus.setCode(EntityService.getKeyStr());
            cus.setCustomerName(EntityService.getKeyStr());
            cus.setSavedDate(new Date(System.currentTimeMillis() + i));
            cus.setEditedDate(new Date(System.currentTimeMillis() + i));
            lstx.add(cus);
        }
        new GenericDAO<Customer>().saveList(lstx);
        System.out.println("Created Customer List");

    }

    public void createStaff() {

        List lst2 = new ArrayList();
        for (int i = 0; i < 1500; i++) {
            Staff cus = new Staff();
            cus.setId(EntityService.getKeyStr());
            cus.setCode(EntityService.getKeyStr());
            cus.setName(EntityService.getKeyStr());
            cus.setSavedDate(new Date(System.currentTimeMillis() + i));
            cus.setEditedDate(new Date(System.currentTimeMillis() + i));
            lst2.add(cus);
        }
        new GenericDAO<Staff>().saveList(lst2);

    }

    public void createSuppliers() {
        List lst = new ArrayList();
        for (int i = 0; i < 1500; i++) {
            Supplier cus = new Supplier();
            cus.setId(EntityService.getKeyStr());
            cus.setCode(EntityService.getKeyStr());
            cus.setName(EntityService.getKeyStr());
            cus.setSavedDate(new Date(System.currentTimeMillis() + i));
            cus.setEditedDate(new Date(System.currentTimeMillis() + i));
            lst.add(cus);
        }
        new GenericDAO<Supplier>().saveList(lst);

        System.out.println("Created supplier List");

    }


    public void createCategory() {
        GenericDAO gd = new GenericDAO<Category>();
//        gd.deleteAll(Category.class);
        System.out.println("ready for category");
        List lsts = new ArrayList();

        for (int i = 0; i < 1500; i++) {

            Category shx = new Category();
            shx.setId(EntityService.getKeyStr());
            shx.setCode(EntityService.getKeyStr());
            shx.setSavedDate(new Date(System.currentTimeMillis() + i));
            shx.setEditedDate(new Date(System.currentTimeMillis() + i));
            lsts.add(shx);
        }
        gd.saveList(lsts);
        System.out.println(" category finalised");
    }
}
