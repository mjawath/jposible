package org.biz.dao.util;
 


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.biz.dao.service.GenericDAO;
import org.biz.dao.service.Service;

/**
 *
 * @author mjawath
 */
public class EntityService {

    static GenericDAO genericDAO;
    static EntityService entityService;
    static Date currentDate;

    private EntityService() {
        genericDAO = new GenericDAO();
    }

    public static void init() {
        
        if (entityService == null) {
            entityService = new EntityService();
        }


    }

    public static EntityService getEntityService() {
        init();
        return entityService;

    }


    public String getUniqueKey(Service service,Class cls){
      boolean loop=true;
        String key = getKey("");
        Object ob = new Object();
        while(loop) {
            ob = service.getDao().find( key);
            if (ob!=null) {
                key = getKey("");
            }else{
            loop=false;
            }

        }
        return key;
    }

    public static  String getKeys() {
        String rn=""+AB.charAt(rnd.nextInt(AB.length()));
        String key = System.currentTimeMillis()+"-"+rn;
        return key;
    }
    public static String getKeyStr() {
    return randomString(15);
    }
    public String getKey(String shopName) {
        
        String rn=""+AB.charAt(rnd.nextInt(AB.length()));
        String rn2=""+AB.charAt(rnd.nextInt(AB.length()));
        String key = System.currentTimeMillis()+"-"+rn+rn2;

// / create a java calendar instance


// get a java.util.Date from the calendar instance.
// this date will represent the current instant, or "now".
//java.util.Date now = calendar.getTime();

// a java current time (now) instance
//java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());


        //java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());


        return key;
    }

    public String getKey() {
        
        String rn=""+AB.charAt(rnd.nextInt(AB.length()));
        String rn2=""+AB.charAt(rnd.nextInt(AB.length()));
        String key = System.currentTimeMillis()+"-"+rn+rn2;

// / create a java calendar instance


// get a java.util.Date from the calendar instance.
// this date will represent the current instant, or "now".
//java.util.Date now = calendar.getTime();

// a java current time (now) instance
//java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());


        //java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());


        return key;
    }
    
    static final String AB = "GHIJBK013LO8L2MNOP7ECD456QRS9ABKFTUVWXYZ";
    static Random rnd = new Random();

  static  String  randomString(int len) {
        
//        Calendar calendar = Calendar.getInstance();
//        StringBuilder sb = new StringBuilder(len);
//        for (int i = 0; i < len; i++) {
//            sb.append(AB.charAt(rnd.nextInt(AB.length())));
//        }
//        sb.append("-").append(calendar.getTimeInMillis() + "" + AB.charAt(rnd.nextInt(AB.length())));
//        return sb.toString();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
               ex.printStackTrace();
            }
            System.out.println(getKeys());

        }
    }
    public String getBetWeen(Date date){
        String dd= new SimpleDateFormat("dd/MM/yyyy").format(date);
        dd=dd.concat(" 00:00:00 ");
        String dd2= new SimpleDateFormat("dd/MM/yyyy ").format(date);
        dd2=dd2.concat(" 23:59:59 ");
        return "'"+dd+"' and  '"+dd2+"'";
    }

    public Query setBetWeenDates( Query query){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
                cal2.add(Calendar.DATE, 1);
        query.setParameter(1, cal1.getTime(), TemporalType.DATE);
        query.setParameter(2, cal2.getTime(), TemporalType.DATE);
        return query;

    }
  
    public Query setBetWeenDates( Query query,Date date ){
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(date.getTime());
        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(date.getTime());
                cal2.add(Calendar.DATE, 1);
        query.setParameter(1, cal1.getTime(), TemporalType.DATE);
        query.setParameter(2, cal2.getTime(), TemporalType.DATE);
        return query;

    }




    public String getWithinCurrentDate(){
        Date date = nowDate();

        return getBetWeen(date);
    }
    public static Date nowDate() {
    Date date = new Date();
    return date;

  }

    public Date getCurrentDate(){
        return null;
    }

    public static String objTodate(Object d) {
        if (d == null) {
            return "";
        }
        return new SimpleDateFormat("dd/MMM/yyyy").format(d);

    }

    public static String objTodate(String form,Object d) {
        if (d == null) {
            return "";
        }
        return new SimpleDateFormat(form).format(d);

    }

 
}
