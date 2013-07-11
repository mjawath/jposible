/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package research.ui.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.biz.dao.service.GenericDAO;
import org.biz.dao.util.EntityService;
import org.biz.invoicesystem.entity.master.*;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.service.transactions.SalesInvoiceService;
import org.eclipse.persistence.config.PersistenceUnitProperties;

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
//        createDataBase();
//       createmster();
//           List lsts = new ArrayList();

//            new dbCreation().createCategory();
//        new GenericDAO<Customer>().saveList(lsts);

        SalesInvoice inv=new SalesInvoice();
        inv.setId("6777");
        new SalesInvoiceService().getDao().save(inv);
    }

    public  void createmster() {

        List lsts = new ArrayList();


        Shop shz = new Shop();
        shz.setId("123");
        shz.setCode("12n3");
        lsts.add(shz);

        for (int i = 0; i < 1500; i++) {

            Shop shx = new Shop();
            shx.setId(EntityService.getKeyStr());
            shx.setCode(EntityService.getKeyStr());

            lsts.add(shx);
        }
        new GenericDAO<Customer>().saveList(lsts);


        List lst = new ArrayList();
        for (int i = 0; i < 1500; i++) {
            Supplier cus = new Supplier();
            cus.setId(EntityService.getKeyStr());
            cus.setCode(EntityService.getKeyStr());
            cus.setName(EntityService.getKeyStr());
            lst.add(cus);
        }
        new GenericDAO<Customer>().saveList(lst);
        List lstx = new ArrayList();
        for (int i = 0; i < 1500; i++) {
            Customer cus = new Customer();
            cus.setId(EntityService.getKeyStr());
            cus.setCode(EntityService.getKeyStr());
            cus.setCustomerName(EntityService.getKeyStr());
            lstx.add(cus);
        }
        new GenericDAO<Customer>().saveList(lstx);
        List lst22 = new ArrayList();
        for (int i = 0; i < 1500; i++) {
            Item cus = new Item();
            cus.setId(EntityService.getKeyStr());
            cus.setCode(EntityService.getKeyStr());
            cus.setDescription(EntityService.getKeyStr());
            lst22.add(cus);
        }
        new GenericDAO<Item>().saveList(lst22);
        List lst2 = new ArrayList();
        for (int i = 0; i < 1500; i++) {
            Staff cus = new Staff();
            cus.setId(EntityService.getKeyStr());
            cus.setCode(EntityService.getKeyStr());
            cus.setName(EntityService.getKeyStr());
            lst2.add(cus);
        }
        new GenericDAO<Staff>().saveList(lst2);
        
       List lstw = new ArrayList();
        for (int i = 0; i < 1500; i++) {
            Warehouse cus = new Warehouse();
            cus.setId(EntityService.getKeyStr());
            cus.setCode(EntityService.getKeyStr());

            lstw.add(cus);
        }
        new GenericDAO<Warehouse>().saveList(lstw);

    }

    public  void createDataBase() {
        //before call this method should not initialis the emf any where perticularly in static initialisers!!!
//        JPAUtil.createEMFWithCustomProperties();

        Map props = new HashMap();
//         props.put("eclipselink.jdbc.user","");
//         props.put("eclipselink.jdbc.password", "");\
        props.put(PersistenceUnitProperties.APP_LOCATION, "c:\\ddl\\");
//        props.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.DROP_AND_CREATE);
        props.put("eclipselink.ddl-generation", "drop-and-create-tables");
        props.put(PersistenceUnitProperties.DDL_GENERATION_MODE, PersistenceUnitProperties.DDL_BOTH_GENERATION);
//        props.put("eclipselink.logging.level", "FINE");
//        	<property name="eclipselink.ddl-generation.output-mode" value="both" />
//        	<property name="eclipselink.ddl-generation.output-mode" value="both" />
        props.put(PersistenceUnitProperties.CREATE_JDBC_DDL_FILE, "create.sql");
//        props.put(PersistenceUnitProperties.CREATE_JDBC_DDL_FILE, "create.sql");
        props.put(PersistenceUnitProperties.DROP_JDBC_DDL_FILE, "drop.sql");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InvoicingSystemPU", props);

       
     List s=   emf.createEntityManager().createQuery("select item from Item item").getResultList();

    }
    
    public void createCategory(){
        GenericDAO gd=new GenericDAO<Category>();
        gd.deleteAll(Category.class);
        System.out.println("ready for category");  
        List lsts = new ArrayList();

        for (int i = 0; i < 1500; i++) {

            Category shx = new Category();
            shx.setId(EntityService.getKeyStr());
            shx.setCode(EntityService.getKeyStr());

            lsts.add(shx);
        }
        gd.saveList(lsts);
        System.out.println(" category finalised");
    }
}
