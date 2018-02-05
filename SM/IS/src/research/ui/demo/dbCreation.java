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
import org.biz.invoicesystem.dao.master.CategoryDAO;
import org.biz.invoicesystem.entity.master.Category;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.SKU;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Staff;
import org.biz.invoicesystem.entity.master.Supplier;
import org.biz.invoicesystem.entity.master.UOM;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.entity.master.WorkStation;
import org.dao.util.JPAUtil;
import org.util.MathUtil;

/**
 *
 * @author nnjj
 */
public class dbCreation {

    public static void main(String[] args) {

        
//        GenericDAO<Customer> dao = new GenericDAO<>();
//        Map<String,Object> params = new HashMap<>();
////        params.put("key", ".code");
//        params.put("code", " c.code  like  c%");
////        params.put("customerName", "a%");
//
//        List list =dao.executeQuery("select c from Customer c where :code ",params );
//        System.out.println("============"+list.size());
        
//        select count(c.id
//        ) from Customer c where   c.
//        :code like    a % order by c
//        .savedDate desc, c
//        .editedDate desc
        
      
        dbCreation db = new dbCreation();
        db.createDataBase();
        db.createmaster();
    }

    public void createDataBase() {
        //before call this method should not initialis the emf any where perticularly in static initialisers!!!
        JPAUtil.setCustomeEMFProperty();

    }

    public void createmaster() {
        System.out.println("Creating master data");
        WorkStation station = new WorkStation();
        station.setId("123");
        station.setCode("123");
        new GenericDAO<WorkStation>().save(station);
        createShops();
        createWarehouses();
        createCategory();
        createItemSKU();
        createCustomers();
        createStaff();
        createSuppliers();
    }

    public void createShops() {
        List lsts = new ArrayList();
        Date date = new Date();
        Shop shz = new Shop();
        shz.setId("123");
        shz.setCode("123");
        lsts.add(shz);

        for (int i = 0; i < 10; i++) {

            Shop shx = new Shop();
//            shx.setId(EntityService.getKeyStr());
            shx.setCode(EntityService.getKeyStr());
            shx.setSavedDate(date);
            shx.setEditedDate(date);
            lsts.add(shx);
        }
        System.out.println("Created shop List");

        new GenericDAO<Shop>().saveList(lsts);

    }

    public void createItemSKU() {
       
        List<Category> cates= new CategoryDAO().select(10);

      List lst22 = new ArrayList();
        for (int i = 0; i < 1500; i++) {
            Item item = new Item();
            item.setCode(EntityService.getKeyStr());
            int random = MathUtil.random(0,10);
            System.out.println("random "+random);
            item.setCategory(cates.get(random));
            
            ArrayList uoms = new ArrayList();

            UOM uom3 = new UOM();
            uom3.setCode("PCS");
            uom3.setMulti(12d);
            uom3.setIsPrimary(true);

            uoms.add(uom3);

            UOM uom = new UOM();

            uom.setCode("CTN");
            uom.setMulti(100d);
            uom.setIsPrimary(false);
            uom.setGuom(uom3);
            uoms.add(uom);

            UOM uom2 = new UOM();
            uom2.setCode("DZ");
            uom2.setMulti(12d);
            uom2.setIsPrimary(false);
            uoms.add(uom3);
            item.setUoms(uoms);            
            
            for (int j = 0; j < 10; j++) {
                SKU sku = new SKU();
                sku.setCode(EntityService.getKeyStr());                         
                sku.setExplainningSearchString(EntityService.getKeyStr());
                sku.setItem(item); 
                sku.setSavedDate(new Date(System.currentTimeMillis() + i));
                sku.setEditedDate(new Date(System.currentTimeMillis() + i));
                lst22.add(sku);

            }

            item.setSavedDate(new Date(System.currentTimeMillis() + i));
            item.setEditedDate(new Date(System.currentTimeMillis() + i));

        }
        new GenericDAO<SKU>().saveList(lst22);
        System.out.println("Created Item List");

    }

    public void createWarehouses() {
        List lstw = new ArrayList();
        for (int i = 0; i < 40; i++) {
            Warehouse cus = new Warehouse();
//            cus.setId(EntityService.getKeyStr());
            cus.setCode(EntityService.getKeyStr());
            cus.setSavedDate(new Date(System.currentTimeMillis() + i));
            cus.setEditedDate(new Date(System.currentTimeMillis() + i));
            lstw.add(cus);
        }

        Date date = new Date();
        Warehouse shz = new Warehouse();
        shz.setId("123");
        shz.setCode("123");
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
        Customer sh = new Customer();
        sh.setId("123");
        sh.setCode("123");
        sh.setSavedDate(new Date(System.currentTimeMillis() + 5000));
        sh.setEditedDate(new Date(System.currentTimeMillis() + 5000));
        lstx.add(sh);
            
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
        
        Staff sh = new Staff();
        sh.setId("123");
        sh.setCode("123");
        sh.setSavedDate(new Date(System.currentTimeMillis() + 5000));
        sh.setEditedDate(new Date(System.currentTimeMillis() + 5000));
        lst2.add(sh);
        new GenericDAO<Staff>().saveList(lst2);

    }

    public void createSuppliers() {
        List lst = new ArrayList();
        for (int i = 0; i < 1500; i++) {
            Supplier cus = new Supplier();
//            cus.setId(EntityService.getKeyStr());
            cus.setCode(EntityService.getKeyStr());
            cus.setName(EntityService.getKeyStr());
            cus.setSavedDate(new Date(System.currentTimeMillis() + i));
            cus.setEditedDate(new Date(System.currentTimeMillis() + i));
            lst.add(cus);
        }
        Supplier sh = new Supplier();
        sh.setId("123");
        sh.setCode("123");
        sh.setSavedDate(new Date(System.currentTimeMillis() + 5000));
        sh.setEditedDate(new Date(System.currentTimeMillis() + 5000));
        lst.add(sh);
        
        new GenericDAO<Supplier>().saveList(lst);

        System.out.println("Created supplier List");

    }

    public void createCategory() {
        GenericDAO gd = new GenericDAO<Category>();
//        gd.deleteAll(Category.class);
        System.out.println("ready for category");
        List lsts = new ArrayList();
        Category sh = new Category();
        sh.setId("123");
        sh.setCode("123");
        sh.setSavedDate(new Date(System.currentTimeMillis() + 5000));
        sh.setEditedDate(new Date(System.currentTimeMillis() + 5000));
//        lsts.add(sh);
            
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
