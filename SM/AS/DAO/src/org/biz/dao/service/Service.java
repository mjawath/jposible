/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.dao.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.biz.app.ui.util.Tracer;
import org.biz.dao.util.EntityService;
import org.biz.entity.BusObj;

/**
 *
 * @author mjawath
 */
public class Service<T extends BusObj> {

    EntityService es;
    Cache cache;
    GenericDAO dao;
    int pageSize;

    public Service() {

    }

    /**
     *
     */
    public Service(String dbname) {
        es = EntityService.getEntityService();
    }

    public GenericDAO getDao() {
//        
        if (es == null) {
            es = EntityService.getEntityService();
        }
        if (dao == null) {
            dao = new GenericDAO();
        }
        return dao;
    }

    public Cache getCache() {
        return getDao().getCache();
    }

    public List moveToPage(CQuery qry, int pageNo) {
        return getDao().pagedData(qry.getQuery(), pageNo);

    }

    public Long getCount(CQuery qry) {
        if (qry == null) {
            return 0l;
        }
        return getDao().getCount(qry.getQuery());
    }

    public void getNextPage(String qryname) {
        //current page ??
        //get count 1650
        //get rowsperpage  100      --> 17 pages
//        dao.getpagedDetail(qry);

        // get query string  by query name == > 
        // get current page from page panel r page object 
        // if can navigate goto  next page 
//        String s =dao.getqry(qry);
//        int page = dao.getCache().getbySpecialKey(qry, s, pageSize).getCurrentpage(qry);
//        if(possilbe ){
//        goto next page(s,page);
//        }
////                
//        String qry = getDao().getquery(qryname);
//        int cpageno=getDao().getCupage(qryname);
//        getDao().getcount(qry);        
//        getDao().pagedData(qry, cpageno);
        getDao().getNextPage(qryname);
    }

    public Object findByID(String id) {
        return dao.find(id);
    }

    public Object findByCode(String code) {
        return dao.getByCode(code);
    }

    public String getUniqueKey() {
        return EntityService.getKey("Test");
    }

    public void PrintTracer(String msg) {
//        Tracer.printToOut("servies are set ");
        System.out.println("should move print traeer " + msg);
    }

    public int getNoOfRows() {
        return getDao().getNoOfRows();
    }

    public <T> T getByCode(String qry) {
        return (T) getDao().getByCodex(qry);
    }

    public List getByWhere(String conditions) {
        return getDao().getByWhere(conditions);
    }

    public long getCountOfByWhere(String conditions) {
        return getDao().getCountOfByWhere(conditions);
    }

    public List getByCodeLike(String qry) {
        return getDao().getByCodeLike(qry);
    }

    public CQuery getQueryByCodeLike(String qry) {
        return getDao().getQueryByCodeLike(qry);
    }

    public CQuery getCountQueryByCodeLike(String qry) {

        return getDao().getCountQueryByCodeLike(qry);
    }

    public long getCountByCodeLike(String qry) {

        return getCount(getDao().getCountQueryByCodeLike(qry));
    }

    public List getByCodeLike(int page, String qry) {
        return getDao().getByCodeLike(page, qry);
    }

    public T save(T busObject) {
        long x = System.currentTimeMillis();

        if (!isValideEntity(busObject)) {//check for current business objects validity
            return null;
        }
        if (busObject == null) {
            Tracer.printToOut("Detail panel -> SaveX -> Bus Object is null ,Not saved");
            return null;
        }

        if (busObject.getId() == null) {
            Object id = ((BusObj) busObject).getId();//0 is the index of the main object , id is id property

            System.out.println("find b" + (System.currentTimeMillis() - x));

            Object obj = id == null ? null : getDao().find(id);

            if (obj == null) {
                //should retrieve new id and set to new objects

                return saveData(busObject);
//                postCreate(busObject);
            } else {
                Tracer.printToOut("Detail panel -> SaveX -> Bus Object ID logic has problem ,Not saved");
            }

        } else {//update mode
//            if (MessageBoxes.yesNo(detailView, "Current record already exist in the DATABASE Are you sure\n"
//                    + "You want to update the record", "Update Confirmation") == MessageBoxes.NO_OPTION) {
//                return null;
//            }
            final Object id = ((BusObj) busObject).getId();
            if (id == null) {
                System.out.println("------some thing is wrong --------");
                return null;
            }

            if (id instanceof String) {
                ((BusObj) busObject).setId((String) id);
            } else if (id instanceof Long) {
//                ((BusObj) busObject).setId((Long) id);
            }

            Tracer.printToOut("Updation is called  Object  is  found");
            T updated = updateData(busObject);
            return updated;

        }

//        result.add(toSave);
//        result.add(toUpdate);
//        result.add(toDelete);
        //call some observer method
        Tracer.printToOut("Detail panel Save is successfully performed , result is returned, method is called using BT");
        return busObject;
    }
    //this will be a transactional

    protected T saveData(T busObject) {

        T saved = (T) saveData(busObject, null, null, null);

        return saved;
    }

    protected T saveData(T busObject, List thingsToCreate, List thingsToUpdate, List thingsToDelete) {

//                toSave.add(busObject);
        preCreate(busObject);
//                getDao().saveUpdateDelete(toSave, toUpdate, toDelete);

        if (thingsToCreate == null) {
            thingsToCreate = new ArrayList();            
        }
   
        thingsToCreate.add(0, busObject);
        isValideEntity(busObject);
        T saved = (T) (getDao().saveUpdateDelete(thingsToCreate, thingsToUpdate, thingsToDelete)).get(0);
//                auditPersistenceData(busObject);
//                System.out.println("saved " + (System.currentTimeMillis() - x));  
        return saved;
    }

    //this will be a transactional
    protected T updateData(T busObject) {
        T saved = (T) updateData(busObject, null, null, null);
        return saved;
    }

    protected T updateData(T busObject, List thingsToCreate, List thingsToUpdate, List thingsToDelete) {

        preUpdate(busObject);


        if (thingsToUpdate == null) {
            thingsToUpdate = new ArrayList();
            }
        isValideEntity(busObject);
        thingsToUpdate.add(busObject); 
        getDao().saveUpdateDelete(thingsToCreate, thingsToUpdate, thingsToDelete);
        return busObject;
    }

    protected EntityManager startTransaction() {
        final EntityManager em = getDao().getNewEm();
        em.getTransaction().begin();
        return em;
    }

    protected void persist(EntityManager em, BusObj busObject) {
        getDao().persist(em, busObject);
    }

    protected void commit(EntityManager em) {
        getDao().commit(em);
    }

    protected boolean isValideEntity(T busObject) {
        return true;
    }

    public void preCreate(BusObj saveableItem) {

    }

    private void auditPersistenceData(BusObj bus) {

        Date cDate = GenericDAOUtil.currentTime();

//        for (BusObj bus : objs) {
//            bus.setId( EntityService.getKey(""));      
        bus.setSavedDate(cDate);
        bus.setEditedDate(cDate);
        bus.setDepententEntitiesIDs();

//        }
    }

   
    private void postCreate(BusObj saveableItem) {

    }

    private void preUpdate(BusObj saveableItem) {

    }

    private void postUpdate(BusObj saveableItem) {
    }

    public void delete(T selectedObject) {
       
            getDao().delete(selectedObject);


    }
    

    private void preDelete(BusObj saveableItem) {

    }

    private void postDelete(BusObj saveableItem) {

    }

}
