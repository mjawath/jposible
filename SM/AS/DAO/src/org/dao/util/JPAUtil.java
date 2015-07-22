package org.dao.util;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.biz.app.ui.util.Tracer;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.jpa.JpaHelper;

public class JPAUtil {

    public static boolean customPropery=false;
    static int etyvertion = 0;
    static int etyOldvertion = 0;
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static final String PU="InvoicingSystemPU";

//    static {
//        try {
//
//           entityManagerFactory = createEMFWithCustomProperties(true);//
//            entityManager = entityManagerFactory.createEntityManager();
//
//        } catch (Throwable e) {
//            e.printStackTrace();
//            throw new ExceptionInInitializerError(e);
//        }
//    }

    /*
     * create entitimanager factory if there is not any 
     */
    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = getEntityManagerFactory();
        }

        if (entityManagerFactory != null) {
            try {
                return entityManagerFactory.createEntityManager();

            } catch (Exception e) {
                Tracer.printToOut("Problem creating entity manager  in getEntityManager" + e.getMessage());

            }
        }
        return null;
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        try {
            if (entityManagerFactory == null) {
                entityManagerFactory = Persistence.createEntityManagerFactory(PU);
                return entityManagerFactory;
            }
        } catch (Exception e) {
            
            Tracer.printToOut("Problem creating entity manager factory in getEntityManagerFactory"+e.getMessage());

        }

        return entityManagerFactory;
    }

    public static Query createQuery(String key) {
        Query query = entityManager.createQuery(key);
        return query;
    }

    public static Query createQuery(String key, Class cls) {
        final EntityManager em = getEntityManager();
        if (em == null) {
            return null;
        }
        Query query = em.createQuery(key, cls);
        return query;
    }
    
    

    public static Connection getConnection() {
        Connection connection = entityManager.unwrap(Connection.class);
        return connection;
    }

    public static Connection getNewConnection() {
        Connection connection = entityManagerFactory.createEntityManager().unwrap(Connection.class);
        return connection;
    }

//    /**
//    @return returns a EntityManagerFactory for creating an EntityManager
//     */
    /**
     * Free up the resources consumed by the EntityManagerFactory.
     * Dont invoke this method at the middle of application.
     */
    public static void shutDownPersistenceFactory() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public static void startPersistence() {
        try {
            //how to start derby database

            entityManagerFactory = Persistence.createEntityManagerFactory("RaheemiyaPU");
            entityManager = entityManagerFactory.createEntityManager();

        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void clearCache() {
        entityManagerFactory.getCache().evictAll();
    }
    
    /**
     * reference http://wiki.eclipse.org/Using_EclipseLink_JPA_Extensions_%28ELUG%29#Using_EclipseLink_JPA_Extensions_for_Schema_Generation
     * key  persistence jpa ddl   Schema Generation
     *title  Using EclipseLink JPA Extensions for Schema Generation
     */
    public static EntityManagerFactory createEMFWithCustomProperties(boolean isProp) {

        Map props = new HashMap();
//         props.put("eclipselink.jdbc.user","");
//         props.put("eclipselink.jdbc.password", "");\
        
        props.put(PersistenceUnitProperties.APP_LOCATION, ".");
//        props.put(PersistenceUnitProperties.DDL_GENERATION_MODE, PersistenceUnitProperties.DDL_DATABASE_GENERATION);
        props.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.DROP_AND_CREATE);

        props.put(PersistenceUnitProperties.DEPLOY_ON_STARTUP, "true");

//        props.put("eclipselink.ddl-generation", "create-or-extend-tables");
//        props.put(PersistenceUnitProperties.DDL_GENERATION_MODE, PersistenceUnitProperties.DDL_BOTH_GENERATION);
//        props.put("eclipselink.logging.level", "FINE");
//        	<property name="eclipselink.ddl-generation.output-mode" value="both" />
//        	<property name="eclipselink.ddl-generation.output-mode" value="both" />
//        props.put(PersistenceUnitProperties.CREATE_JDBC_DDL_FILE, "create.sql");
//        props.put(PersistenceUnitProperties.DROP_JDBC_DDL_FILE, "drop.sql");

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU, props);
//        entityManagerFactory = emf;
        if(isProp){
        entityManagerFactory=Persistence.createEntityManagerFactory(PU,props);//set map to this
        }else{
        entityManagerFactory=Persistence.createEntityManagerFactory(PU);//set map to this

        }//        for (String string : emf.getProperties().keySet()) {
//            System.out.println(emf.getProperties().get(string));
//        }
        entityManager=entityManagerFactory.createEntityManager();
        
//                JpaHelper.getEntityManagerFactory(entityManager).refreshMetadata(props);

     List s=   entityManager.createQuery("select item from Item item").getResultList();
//        System.out.println(s);
        return entityManagerFactory;
    }
}
