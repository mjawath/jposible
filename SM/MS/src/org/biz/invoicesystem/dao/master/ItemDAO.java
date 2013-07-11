package org.biz.invoicesystem.dao.master;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.master.ui.SystemStatic;
import org.dao.util.JPAUtil;

/**
 *
 * @author mjawath
 */
public class ItemDAO extends GenericDAO<Item> {

    public static  String findItemListByCode="findItemListByCode";
    
    public ItemDAO() {
        setCls(Item.class);
        orderby ="c.editedDate , c.savedDate ";
    }

    
     public  List<Item> findItemListByCode(String itemcode) {
  String qry ="  c.code like '" + itemcode + "%' ";
        List<Item> lst = pagedData(qry,0);
//
        getCache().createAndAddCache(findItemListByCode,qry, 0, 0l, lst);
        return lst;
    }
     
    public Item findItemByCode(String itemcode) {
        Item i = null;
//        try { 
        String qry=" Where c.code='" + itemcode + "'";
        Item lst = ExecuteQuerySR(qry);
//   //EntityManager em=createEmNew();
//  
//    //        em.getTransaction().begin();
// List<Item> lst=em.createQuery("select i from item Where i.code=?1").setParameter(1,itemcode).getResultList();
        
// 
// em.getTransaction().commit();            
//em.close();
//        } catch (Exception e) {
//             
//        e.printStackTrace();
//        }finally{
//   
//        }
        return lst;
    }

    public void deleteItemByid(String itemid) {

        try {
            Item i = find(itemid);
            i.setInactive(true);

            EntityManager em = null;

            em.getTransaction().begin();
            em.merge(i);

            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
        }


    }

    public List<Item> selectAll() {
        List<Item> lst = null;
        try {

            lst = ExecuteQuery("select i From Item i");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;

    }

    //////////////////////////////////////////
    public List<Object[]> loadComboItems() {

        List<Object[]> lst = new ArrayList<Object[]>();
        //   EntityManager em=createEmNew();
        try {
            lst = ExecuteNativeQuery("select  c.category , c.unitOne , c.unitTwo c.location From Item c ");

            System.out.println("lst size " + lst.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    //////////////////////////////////////////
    public int getListSize() {
        int x = 0;
        try {

            Query query = JPAUtil.getEntityManager().createQuery("SELECT COUNT(i) from Item i");


            return ((Long) query.getSingleResult()).intValue();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    ////////////////////////////////////////////////////
    public List<Item> getPaginatedData(int pageNo, String generatedQuery) {
        List<Item> lst = new ArrayList<Item>();
        int strt = 0;
        try {

            System.out.println("calling page no is " + pageNo);
            EntityManager em = JPAUtil.getEntityManager();
//            
            //     Query query = em.createQuery("SELECT e FROM Item e order by e.id asc");
            Query query = em.createQuery(generatedQuery);

            strt = pageNo == 0 ? 0 : pageNo * SystemStatic.GRID_LIST_SIZE;

            System.out.println("starting no is " + strt);
            query.setFirstResult(strt + 1);
            query.setMaxResults(SystemStatic.GRID_LIST_SIZE);

            lst = query.getResultList();
            System.out.println("came here....");
        } catch (Exception e) {
        }

        return lst;
    }

    public List<Item> byCode(String qry) {
        String cus = "  c.code like '" + qry + "%' ";
        List<Item> lst = pagedData(cus, 0);//if lst size 0 then search barcode
        return lst;

    }
    //////////////////////////////////////////////////////

    public void createTestData() {
        System.out.println("persist working.....");

        for (int x = 0; x < 150; x++) {
            Item ii = new Item();

            ii.setId("" + x);
            ii.setCode("" + x);
            ii.setCommission(1000d);
            ii.setCost(3200d);
            ii.setDescription("des");
            ii.setDifferent(7);
            ii.setDiscount(5d);
            ii.setUnitOne("ggg");
            ii.setUnitTwo("dddd");
            ii.setSalesPrice(600d);
            ii.setSupplierId("seeeee");
            ii.setCrDate(new Date());
            ii.setSavedDate(new Date());
            ii.setEditedDate(new Date());
            this.save(ii);

        }
        System.out.println("saved....");
//      
    }
    
    public static void main(String[] args) {

        ItemDAO i = new ItemDAO();
        //   List l= i.likequeryy();                 //    
        List ls = i.getPaginatedData(1, "SELECT e FROM Item e Where e.description LIKE '%de%'  order by e.id asc");
        //   System.out.println("l size s "+ls.getListSize());
        System.out.println("l size s " + ls.size());
//   //     i.deleteItemByid("1000");
//////    
        
        System.out.println("saved....");
//      List<Item> lst=i.getIndexItems(0,500);
//      List<Item> lst1=i.getIndexItems(501, 1000);
//      
//        System.out.println("lst 500 item is "+lst.get(499).getId());
//        System.out.println("lst 500 item is "+lst1.get(501).getId());
//        System.out.println("lst size is "+lst.size());         
//        System.out.println("lst size is "+lst1.size());         
//    
    }
//           Query query = em.createQuery("SELECT e FROM Item e ");
//query.setHint("eclipselink.cursor.scrollable", true);
//ScrollableCursor scrollableCursor = (ScrollableCursor)query.getSingleResult();
////lst= scrollableCursor.next(100); 
//lst= query.getResultList();
//            System.out.println("came here....");
}
