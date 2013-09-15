package org.biz.dao.service;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Parameter;
import javax.persistence.Query;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.biz.dao.util.EntityService;
import org.biz.entity.BusObj;
import org.dao.util.DAOException;
import org.dao.util.JPAUtil;

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

    public EntityService getES() {
        return EntityService.getEntityService();
    }

    public void findRefresh(Object c, Object key) {
        getEm().find(c.getClass(), key);
        getEm().refresh(c);
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
        String order = "";
        if (!orderby.isEmpty()) {
            order = "order by  " + orderby ;//desc - decending 
        }
        Query query = createEmNew().createQuery("select c from " + cls.getSimpleName() + " c   " + order);
        query.setHint(QueryHints.REFRESH, HintValues.TRUE);
        return query.getResultList();
    }

    public static <T> void persistob(EntityManager em, Object ob) {
        em.persist(ob);
    }

    public static <T> void persist(EntityManager em, Object... ob) {

        for (Object obj : ob) {
            persistob(em, obj);
        }
    }

    public static <T> void save(Object... ob) {
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

    public static <T> void save(T ob) {
        EntityManager em = null;
        try {


            em = createEmNew();
            em.getTransaction().begin();
            if (ob instanceof BusObj) {
                BusObj bb = (BusObj) ob;
//                bb.setEditeddate(null);
//                bb.setSaveddate(null);//get server date
            }
            persist(em, ob);

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

    
    public static <T> void update(T ob) {
        EntityManager em = null;
        try {
            em = createEmNew();
            em.getTransaction().begin();
            merge(em, ob);
            em.getTransaction().commit();

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
        }
    }

    public static <T> void merge(EntityManager em, T ob) {
        em.merge(ob);
    }
   
    public  static void  saveUpdateDelete(ArrayList toSave, ArrayList toUpdate, ArrayList toDelete) {

        EntityManager em = null;

        try {
            em = GenericDAOUtil.createEmNew();
            em.getTransaction().begin();
            if (toSave!=null && !toSave.isEmpty()) {
                //persistence ..save
                for (Object e : toSave) {
                    em.persist(e);
                }
                
            }
            if (toUpdate!=null && !toUpdate.isEmpty()) {
                for (Object e : toUpdate) {
                    em.merge(e);
                }
            }
            if (toDelete !=null && !toDelete.isEmpty()) {
                for (Object e : toDelete) {
                    em.remove(em.merge(e));
                }
            }
            
            em.getTransaction().commit();
        
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

        for (Object o : ps) {
            q.setParameter(x, o);
            x++;
        }
        return q;
    }

    //set parameters  as object array in the accoridng to the order of  to qry string 
    //eg select c from cusomter c where c.x =?1 and c.y= ?2  -===here new object[]{"jawath", 2005}
    public static <T> List<T> ExecuteQuery(String qryString, Object[] ps) {
        return getQuery(qryString, ps).getResultList();
    }

    public static <T> List<T> ExecuteQuery(String qryString, Class cls) {
        Query query = JPAUtil.getEntityManager().createQuery(qryString, cls);
        query.setHint(QueryHints.REFRESH, HintValues.TRUE);
        List ts = query.getResultList();
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
        Date date = (Timestamp) createEmNew().createNativeQuery("select CURRENT_TIMESTAMP  ").getSingleResult();
        return date;
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
    
   
    public static void  createEMFWithCustomProperties(){
    
        JPAUtil.createEMFWithCustomProperties();
    }
    
    
}
