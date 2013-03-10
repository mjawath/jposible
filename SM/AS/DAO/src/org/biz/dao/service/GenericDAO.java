package org.biz.dao.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.dao.util.JPAUtil;
import org.eclipse.persistence.queries.ScrollableCursor;
//http://code.google.com/p/krank/wiki/UsingDAO

/**
 *
 * @author mjawath
 */
public class GenericDAO<T> {

    protected List list;

    public static void main(String[] args) {
    }
    private EntityManager em;
    String classname;
    Class<T> cls;
    String orderby = "";
    protected Cache cache;
    int noofrows = 100;

    public GenericDAO(EntityManager em) {
        this.em = em;
    }

    public GenericDAO() {
        em = JPAUtil.getEntityManager();
        cache = new Cache();

    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public void setCls(Class<T> cls) {
        this.cls = cls;
        this.classname = cls.getSimpleName();
    }

    public Class<T> getCls() {
        return cls;
    }

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

    public T find(Object key) {
        return (T) getEm().find(getCls(), key);

    }

    public T getWhere(String property, Object key) {

        return GenericDAOUtil.getWhere(property, key, cls);

    }

    public T getByPropertySR(String property, Number key) {

        return GenericDAOUtil.getByPropertySR(property, key, cls);

    }

    public T getByPropertySR(String property, String key) {
        return GenericDAOUtil.getByPropertySR(property, key, cls);

    }

    public List<T> getByProperty(String property, Number key) {
        return GenericDAOUtil.getByProperty(property, key, cls);

    }

    public List<T> getByProperty(String property, String key) {
        return GenericDAOUtil.getByProperty(property, key, cls);

    }

//    public T find(Object key ){
//        em.find(T, key);
//        return null;
//    }
    //use to retreve every objects database contains
    //dont be dum to use it when u hav 10000 of records
    public List<T> getAll() {
//        getEm().clear();
        return GenericDAOUtil.getAll(orderby, cls);
    }

//    public List<T> getLastModefied() {
////        getEm().clear();
//        
//        orderby = " c.modifiedDate, c.createdDate  ";
////        return GenericDAOUtil.pagedData(orderby, cls);
//    }
    // save array of objects may be used to persist different kind of objects
    // eg car , dog ,customer in same time 
    public void save(Object... ob) {
        GenericDAOUtil.save(ob);
    }

//save a single object 
    public void save(T ob) {
        GenericDAOUtil.save(ob);
    }

    //save list of items
    public void saveList(List<T> ob) {
        GenericDAOUtil.saveList(ob);
    }

    //use this to delete a certain object 
    //@param is a rerieved object from database with a valid id
    public void delete(T ob) {
        GenericDAOUtil.delete(ob);
    }
//may be used to update a database entity

    public void update(T ob) {
        GenericDAOUtil.update(ob);
    }
//may be used to merge objects

    public void merge(EntityManager em, T ob) {
        em.merge(ob);
    }

    //use  this to execute the query 
    //@param query object
    public List<T> ExecuteQuery(Query qryString) {
        List<T> ts = GenericDAOUtil.ExecuteQuery(qryString);
        return ts;
    }

    //use  this to execute the query  string
    //@param query object is a string of jpql
    public List<T> ExecuteQuery(String qryString) {
        return GenericDAOUtil.ExecuteQuery(qryString, cls);
    }

    public List<Object[]> ExecuteNativeQuery(String qryString) {

        return GenericDAOUtil.ExecuteNativeQuery(qryString);
    }

    public T ExecuteQuerySR(String qryString) {
        qryString = createSelect() + qryString;
        return GenericDAOUtil.ExecuteQuerySR(qryString, cls);
    }

    public Object ExecuteQueryOb(String qryString) {

        return GenericDAOUtil.ExecuteQuerySR(qryString, cls);
    }

    public T deatach(Object c, Object key) {

        T cc = (T) GenericDAOUtil.deatach(c, key);
        return cc;
    }

    public void addPagedData(String qry, int pageNo, List addto) {
        addto.clear();
        List list = pagedData(qry, pageNo);
        if (list != null || !list.isEmpty()) {
            addto.addAll(list);

        }
    }

    public List pagedData(String qry, int pageNo) {
        String sq = createWhere(qry);
//        List lst=GenericDAOUtil.getCache().getbySpecialKey(classname, sq,pageNo);
//        if(lst!=null && !lst.isEmpty()){
//            System.out.println("dddddddddf");
//            return lst;
//        }
        Query qu = GenericDAOUtil.getQuery(sq);


        int fr = pageNo == 0 ? 0 : pageNo * noofrows;
        qu.setFirstResult(fr);//firstresult
        qu.setMaxResults(noofrows); //max result = noofrows+ 0
        return ExecuteQuery(qu);
    }

    public List getPagedData(String qryname, int pageNo) {

        String qry = getquery(qryname);
        int cpageno = getCupage(qryname);
        Long count = getcount(qry);

        Cache ch = getCache().getbyQueryName(qryname);
        ch.setCount(count);
        ch.setCurrentPage(cpageno);
        ch.list = pagedData(qry, cpageno);

        return ch.list;

    }

    public List<T> getByCode(String code) {

        String qry = " c.code like '%" + code + "'";
        return pagedData(code, 0);
    }

    public void getNextPage(String qryname) {
        String qry = getquery(qryname);
        int cpageno = getCupage(qryname);
        Long count = getcount(qry);
        int pages = (int) Math.ceil(count / noofrows);
        if (pages - 1 <= cpageno) {
            cpageno = pages;
        } else {
            cpageno++;
        }
        Cache ch = getCache().getbyQueryName(qryname);
        ch.setCount(count);
        ch.setCurrentPage(cpageno);
        ch.list = pagedData(qry, cpageno);
        System.out.println("size " + cpageno);
    }

    public void getPreviousPage(String qryname) {
        String qry = getquery(qryname);
        int cpageno = getCupage(qryname);
        if (cpageno <= 0) {
            return;
        }
        Long count = getcount(qry);

        Cache ch = getCache().getbyQueryName(qryname);
        ch.setCount(count);
        ch.setCurrentPage(cpageno - 1);
        ch.list = pagedData(qry, cpageno - 1);
        System.out.println("size " + cpageno);
    }

    public void firstPage(String qryname) {
        String qry = getquery(qryname);
        Long count = getcount(qry);
        Cache ch = getCache().getbyQueryName(qryname);
        ch.setCount(count);
        ch.setCurrentPage(0);
        ch.list = pagedData(qry, 0);
        System.out.println("size " + 0);
    }

    public void lastPage(String qryname) {
        String qry = getquery(qryname);
        Long count = getcount(qry);
        int pages = (int) Math.floor(count / noofrows);
        Cache ch = getCache().getbyQueryName(qryname);
        ch.setCount(count);
        ch.setCurrentPage(pages);
        ch.list = pagedData(qry, pages);
        System.out.println("size " + pages);
    }

    public List pagedData(String qry) {
//        String sq = createWhere(qry);
//        
////        List lst=GenericDAOUtil.getCache().getbySpecialKey(classname, sq,pageNo);
////        if(lst!=null && !lst.isEmpty()){
////            System.out.println("dddddddddf");
////            return lst;
////        }
//        Query qu = GenericDAOUtil.getQuery(sq);
//        int noofrows = 1000;
//        int fr = (2 - 1) * noofrows;
//        qu.setFirstResult(fr);//firstresult
//        qu.setMaxResults(noofrows); //max result = noofrows+ 0
        return pagedData(qry, 0);
    }

    public List pagedDataScr(String qryKey, String qry, int pageNo) {
        Query qu = GenericDAOUtil.getQuery(qry);
        ScrollableCursor scrollableCursor = (ScrollableCursor) qu.getSingleResult();

        return ExecuteQuery(qry);
    }

    public String createSelect() {
        return "select c from  " + classname + " c ";
    }

    public String createCount() {
        return "select count(c.id) from  " + classname + " c ";
    }

    public String createWhere(String whr) {

        return createSelect() + " where  " + whr;
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

    public Query createQuery(String qry) {
        Query qu = GenericDAOUtil.getQuery(qry);
        return qu;
    }

    public long getcount(String qry) {

        qry = createCount() + qry;
        return (Long) ExecuteQueryOb(qry);
    }

    String getquery(String qryname) {
        return getCache().getbyQueryName(qryname).getQuery();
    }

    int getCupage(String qryname) {
        return getCache().getbyQueryName(qryname).getCurrentPage();
    }

    public void getGeneratedQuery() {
//   Session session =  getEm().unwrap(JpaEntityManager.class).getActiveSession();
//DatabaseQuery databaseQuery = ((EJBQueryImpl)Query).getDatabaseQuery();
//databaseQuery.prepareCall(session, new DatabaseRecord());
//String sqlString = databaseQuery.getSQLString();
//       System.out.println("exe cuting twoooo22..."+sqlString); 
    }

    public static void createNewDatabase() {
        GenericDAOUtil.createEMFWithCustomProperties();
    }
}

/*
 * scnario : multi database or multi connection environment
 * simply create the instens of this class by passing the desired connection parameteres 
 */