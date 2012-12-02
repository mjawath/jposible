/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.system;

import java.util.Date;
import java.util.Random;
import org.biz.dao.service.Service;
import org.biz.dao.util.EntityService;

/**
 *
 * @author nnjj
 */
public class SystemUtil {
  
 public static String timeStampKey(){
 return ""+System.currentTimeMillis();
 }
    
 public static Date getSystemDate(){
 return EntityService.nowDate();
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
     static final String AB = "GHIJBK013LO8L2MNOP7ECD456QRS9ABKFTUVWXYZ";
    static Random rnd = new Random();
}
