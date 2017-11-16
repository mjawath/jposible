package org.biz.dao.service;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.biz.dao.util.EntityService;
import org.biz.entity.BusObj;
import org.dao.util.DAOException;
import org.dao.util.JPAUtil;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

/**
 * @author mjawath
 */
public class GenericDAOUtil<T> {

    private EntityManager em;
    String classname;
    T cls;
    String orderby = "";
    private static Cache cache;

    static {
        if (cache == null) {
            cache = new Cache();

        }
    }

    public static Cache getCache() {
        return cache;
    }

//    public void setCls(Class<T> cls) {
//        this.cls = cls;
//        this.classname = cls.getSimpleName();
//    }
//    public static<T> Class<T> getCls(String clss) {
//        
//        return cls.getClass();
//    }
    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getClassname() {
        return classname;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

//    public static <T> T find(Object key) {
//        return (T) getEm().find(getCls(), key);
//
//    }
    public static <T> T getWhere(String property, Object key, Class cls) {

        String qry = "select c from  " + cls.getSimpleName() + " c  where c." + property + " ='" + key + "'";
        return ExecuteQuerySR(qry, cls);

    }

    public static <T> T getByPropertySR(String property, Number key, Class cls) {

        String qry = "select c from  " + cls.getSimpleName() + " c  where c." + property + " =" + key + "";
        return ExecuteQuerySR(qry, cls);

    }

    public static <T> T getByPropertySR(String property, String key, Class cls) {

        String qry = "select c from  " + cls.getSimpleName() + " c  where c." + property + " ='" + key + "' ";
        return ExecuteQuerySR(qry, cls);

    }

    public static <T> List<T> getByProperty(String property, Number key, Class cls) {

        String qry = "select c from  " + cls.getSimpleName() + " c  where c." + property + " =" + key + "";
        return ExecuteQuery(qry, cls);

    }

    public static <T> List<T> getByProperty(String property, String key, Class cls) {

        String qry = "select c from  " + cls.getSimpleName() + " c  where c." + property + " = '" + key + "'";
        return ExecuteQuery(qry, cls);

    }
        
    public static <T> List<T> getByPropertyLike(String property, String key, Class cls) {

        String qry = "select c from  " + cls.getSimpleName() + " c  where c." + property + " like '" + key + "%'";
        return ExecuteQuery(qry, cls);

    }

    
    public EntityService getES() {
        return EntityService.getEntityService();
    }

    public static <T> T findRefresh(Class classs, Object key) {

        EntityManager em = null;
        try {
            em = createEmNew();
            return (T)em.find(classs, key);
//            em.refresh(classs);
           
        }
        catch (Exception e) {
            em.close();
        }
        finally {
            if (em != null && em.isOpen()) {
                try {
                    em.clear();
                    em.close();
                }
                catch (Exception e) {
                }

            }
        }
        return null;
    }

    public static <T> T deatach(Object c, Object key) {
        EntityManager em = createEmNew();
        T cc = (T) em.find(c.getClass(), key);
        em.refresh(cc);
        em.detach(cc);
        return cc;
    }

//    public T find(Object key ){
//        em.find(T, key);
//        return null;
//    }
    public static <T> List<T> getAll(String orderby, Class cls) {
//        getEm().clear();

        Query query = createEmNew().createQuery("select c from " + cls.getSimpleName() + " c   "
                + getOrderBy(orderby));
        query.setHint(QueryHints.REFRESH, HintValues.TRUE);
        return query.getResultList();
    }
    
        public static <T> List<T> getAll(int page, int noOfRows,String orderby, Class cls) {
//        getEm().clear();
        
        
        Query query = createEmNew().createQuery("select c from " + cls.getSimpleName() + " c   " +
                getOrderBy(orderby));
        
        query.setHint(QueryHints.REFRESH, HintValues.TRUE);
        return query.getResultList();
    }
    
    public static <T> Query createSelectQuery(int numberOfItem,String orderby, Class cls) {
        Query query = createEmNew().createQuery("select c from " + cls.getSimpleName() + " c   "
                + getOrderBy(orderby));
        query.setHint(QueryHints.REFRESH, HintValues.TRUE);
        query.setFirstResult(0);
        query.setMaxResults(numberOfItem);
        return query;
    }
        
      public static <T> Query getAllQuery(String orderby, Class cls) {
//        getEm().clear();
        
        
        Query query = createEmNew().createQuery("select c from " + cls.getSimpleName() + " c   " +
                getOrderBy(orderby));
        query.setHint(QueryHints.REFRESH, HintValues.TRUE);
        return query;
    }

    public static <T> Query getAllCount(String orderby, Class cls) {
//        getEm().clear();


        Query query = createEmNew().createQuery("select count(c) from " + cls.getSimpleName() + " c   ");
                
        query.setHint(QueryHints.REFRESH, HintValues.TRUE);
        return query;
    }
    
    public static String getOrderBy(String orderby){
    
        if (!orderby.trim().isEmpty()) {
            return "order by  " + orderby ;//desc - decending 
        }
        return "";
    } 

    public static <T> T persistob(EntityManager em, T ob) {
        em.persist(ob);
//        em.flush();
        auditPersistenceData((BusObj) ob);
        return (T) ob;
    }

    public static <T> void persist(EntityManager em, T... ob) {

        for (T obj : ob) {
            persistob(em, obj);
        }
    }

    public static <T> void save(T... ob) {
        EntityManager em = null;
        try {
            em = createEmNew();
            em.getTransaction().begin();
            for (Object object : ob) {
                persist(em, object);
            }

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (em != null) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                try {
                    em.clear();
                    em.close();
                } catch (Exception e) {
                }

            }
        }

    }

        public static <T> void commit(EntityManager em) {

        try {          

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (em != null) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                try {
                    em.clear();
                    em.close();
                } catch (Exception e) {
                }

            }
        }

    }
    public static <T> T save(T ob) {
        EntityManager em = null;
        try {


            em = createEmNew();
            em.getTransaction().begin();
            if (ob instanceof BusObj) {
                BusObj bb = (BusObj) ob;
//                bb.setEditeddate(null);
//                bb.setSaveddate(null);//get server date
            }
            T savedobj = persistob(em, ob);

            em.getTransaction().commit();
            return savedobj;
        } catch (Exception e) {
            e.printStackTrace();
            if (em != null) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                try {
                    em.clear();
                    em.close();
                } catch (Exception e) {
                }

            }
        }
        return null;
    }
    
    private static void auditPersistenceData(BusObj bus) {

        Date cDate = GenericDAOUtil.currentTime();

//        for (BusObj bus : objs) {
//            bus.setId( EntityService.getKey(""));      
            bus.setSavedDate(cDate);
            bus.setEditedDate(cDate);
            bus.setDepententEntitiesIDs();

//        }
    }

    private static void auditUpdatedData(BusObj bus) {

        Date mDate = GenericDAOUtil.currentTime();
//        for (BusObj bus : objs) {
//        bus.setSavedDate(startDate);
        bus.setEditedDate(mDate);
        bus.setDepententEntitiesIDs();
//        }
    }

    public static <T> void saveList(List<T> ob) {
        EntityManager em = null;
        try {
            em = createEmNew();
            em.getTransaction().begin();
            for (T it : ob) {
                persistob(em, it);
            }

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (em != null) {//why no transaction is active ??? 
//                Exception in thread "main" java.lang.IllegalStateException: 
//                Exception Description: No transaction is currently active
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                try {
                    em.clear();
                    em.close();
                } catch (Exception e) {
                }

            }
        }

    }

    public static <T> void remove(EntityManager em, T ob) {
        em.remove(em.merge(ob));
    }

    public static <T> void delete(T ob) {
        EntityManager em = null;
        try {
            em = createEmNew();
            em.getTransaction().begin();
            remove(em, ob);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                try {
                    em.clear();
                    em.close();
                } catch (Exception e) {
                }

            }
        }

    }

    public static <T> void deleteAll( Class cls ) {
        EntityManager em = null;
        try {
            em = createEmNew();
            em.getTransaction().begin();
            Query query = em.createQuery(" delete  from  "+cls.getSimpleName()+"  c " );
            query.executeUpdate();
//            ExecuteQuery(query);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (em != null) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                try {
                    em.clear();
                    em.close();
                } catch (Exception e) {
                }

            }
        }

    }

    public static <T> void find(){
    
    }
    
    
    public static <T> T update(T ob) {
        EntityManager em = null;
        try {
            em = createEmNew();
            em.getTransaction().begin();
            merge(em, ob);
            em.getTransaction().commit();
            return ob;

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new DAOException(e);
        } finally {
            if (em != null) {
                try {
                    em.clear();
                    em.close();
                } catch (Exception e) {
                    throw new DAOException(e);
                }



            }
                    return null;

        }
    }

    public static <T> void merge(EntityManager em, T ob) {
        em.merge(ob);
        auditUpdatedData((BusObj) ob);

    }
   
    public  static List  saveUpdateDelete(List toSave, List toUpdate, List toDelete) {

        EntityManager em = null;

        try {
            em = GenericDAOUtil.createEmNew();
            em.getTransaction().begin();
            if (toSave!=null && !toSave.isEmpty()) {
                //persistence ..save
                for (Object e : toSave) {
                    em.persist(e);
                    auditPersistenceData((BusObj)e);
                }
                
            }
            if (toUpdate!=null && !toUpdate.isEmpty()) {
                for (Object e : toUpdate) {
                    em.merge(e);
                    auditUpdatedData((BusObj) e);
                }
            }
            if (toDelete !=null && !toDelete.isEmpty()) {
                for (Object e : toDelete) {
                    em.remove(em.merge(e));
                }
            }
            
            em.getTransaction().commit();
            return toSave;
        
        }catch (Exception ex) {
            ex.printStackTrace();
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new DAOException(ex);
        } finally {
            if (em != null) {
                try {
                    em.clear();
                    em.close();
                } catch (Exception e) {
                    throw new DAOException(e);
                }
            }
        }
    }

    public void begin() {
        getEm().getTransaction().begin();
    }

    public void commit() {
        getEm().getTransaction().commit();
    }

    public static <T> EntityManager createEmNew() {
        EntityManager em = JPAUtil.getEntityManager();
        return em;
    }

    public static <T> Query getQuery(String qryString, boolean ref) {
        Query query = createEmNew().createQuery(qryString);
        if (ref) {
            refreshOn(query);
        }
        return query;
    }

    public static Query getQueryWithClass(String qryString, Class c) {
        return createEmNew().createQuery(qryString, c);
    }

    public static Query getQuery(String qryString) {
        return createEmNew().createQuery(qryString);
    }
    //set parameters  as object array in the accoridng to the order of  to qry string 
    //eg select c from cusomter c where c.x =?1 and c.y= ?2  -===here new object[]{"jawath", 2005}

    public static Query getQuery(String qryString, Object[] ps) {
        Query q = createEmNew().createQuery(qryString);
        int x = 1;

        if (ps != null) {
            for (Object o : ps) {
                q.setParameter(x, o);
                x++;
            }
        }
        return q;
    }
    
    public static Query getQuery(String qryString, Map ps) {
        Query q = createEmNew().createQuery(qryString);
        for (Object object : ps.entrySet()) {
            Map.Entry enty=(Map.Entry)object;
            q.setParameter(enty.getKey().toString(), enty.getValue());
        }
        return q;
    }

    //set parameters  as object array in the accoridng to the order of  to qry string 
    //eg select c from cusomter c where c.x =?1 and c.y= ?2  -===here new object[]{"jawath", 2005}
    public static <T> List<T> ExecuteQuery(String qryString, Object[] ps) {
        return getQuery(qryString, ps).getResultList();
    }

      //set parameters  as object array in the accoridng to the order of  to qry string 
    //eg select c from cusomter c where c.x =?1 and c.y= ?2  -===here new object[]{"jawath", 2005}
    public static <T> List<T> ExecuteQuery(String qryString, Map ps) {
        return getQuery(qryString, ps).getResultList();
    }

    public static <T> List<T> ExecuteQuery(String qryString, Class cls) {
        Query query = JPAUtil.createQuery(qryString, cls);
        if (query == null) {
            return null;
        }
        query.setHint(QueryHints.REFRESH, HintValues.TRUE);
        List ts = query.getResultList();

        System.out.println("test");
        return ts;
    }

    public static List<Object[]> ExecuteNativeQuery(String qryString) {
        Query query = JPAUtil.getEntityManager().createNativeQuery(qryString);

        //   query.setHint(QueryHints.REFRESH, HintValues.TRUE);
        List<Object[]> ts = query.getResultList();
        return ts;
    }

    public static <T> List<T> ExecuteQuery(Query qryString) {
        qryString.setHint(QueryHints.REFRESH, HintValues.TRUE);
        List<T> ts = qryString.getResultList();
        return ts;
    }

    public static <T> T ExecuteQuerySR(String qryString, Class cl) {

        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery(qryString, cl);
        query.setHint(QueryHints.REFRESH, HintValues.TRUE);
        T ts = null;

        try {
            ts = (T) query.getSingleResult();

        } catch (Exception e) {

            if (e instanceof NoResultException) {
                return null;
            }
            e.printStackTrace();
        }
        return ts;
    }

    public Object ExecuteQueryOb(String qryString, Class cls) {
        Object ts = null;
        try {
            Query query = getEm().createQuery(qryString, cls);
            query.setHint(QueryHints.REFRESH, HintValues.TRUE);
            ts = query.getSingleResult();
        } catch (Exception e) {
            if (e instanceof NoResultException) {
                return null;
            }

        }

        return ts;
    }
//
//    public List<T> ExecuteQuery(Query query, Class<T> cc) {
//        List<T> ts = query.getResultList();
//        return ts;
//    }

    public static <T> T ExecuteQuerySR(Query query) {
        T ts = null;
        try {
            query.setHint(QueryHints.REFRESH, HintValues.TRUE);
            ts = (T) query.getSingleResult();

        } catch (Exception e) {
            if (e instanceof NoResultException) {
                return null;
            }
            throw new RuntimeException(e);
        }
        return ts;
    }

    public static void refreshOn(Query query) {
        query.setHint(QueryHints.REFRESH, HintValues.TRUE);
    }

    public static <T> Date currentTime() {
        // what is the db 
        //db ==hsql then
        //db ==mysqla then

        Object singleResult = null;
        try {
            singleResult = createEmNew().createNativeQuery(" values  CURRENT_TIMESTAMP  ").getSingleResult();
        } catch (Exception e) {
            System.out.println("******!!!!ERROR!!***********DB IS NOT SUPPORTING values  CURRENT_TIMESTAMP  ");
            try {
                singleResult = createEmNew().createNativeQuery(" SELECT  CURRENT_TIMESTAMP  ").getSingleResult();

            } catch (Exception ee) {
                System.out.println("******!!!!ERROR!!***********DB IS NOT SUPPORTING  SELECT  CURRENT_TIMESTAMP   ");

            }
        }
 
        if(singleResult==null )return new Date();
        if((singleResult instanceof Timestamp)){
        Date date = (Timestamp) singleResult;
        return date;
        }
        if(singleResult instanceof String){
        Date date =Timestamp.valueOf(singleResult.toString());
        return date;
        }
        return null;
    }

    /*
    ///////////////
    //
    T ->
     * retruns the unique  type querys with the 
    <T ->
    /////////
     * 
     */
    public static List pagedData(String qryKey, String qry, int pageNo) {
        Query qu = GenericDAOUtil.getQuery(qry);
        int noofrows = 10;
        int fr = (pageNo == 0 ? 0 : pageNo) * noofrows;
        qu.setFirstResult(fr);//firstresult
        qu.setMaxResults(noofrows); //max result = noofrows+ 


        return ExecuteQuery(qu);
    }
//    public List pagedDataScr(String qryKey,String qry,int pageNo){
//    Query qu=GenericDAOUtil.getQuery(qry);
//    ScrollableCursor scrollableCursor = (ScrollableCursor)qu.getSingleResult();
//        
//        return ExecuteQuery(qry);
//    }
    
   
    
}
