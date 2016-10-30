/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.util;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author nnjj
 */
public class MathUtil {
    
    
    public static Double add(Double d1){
        if(d1==null ){
        return 0d;
        }
        return d1;
    }
    
    public static Double sub(Double d1,Double d2){
        if(d1==null && d2 !=null ){
        
        return -d2;
        }
        if(d1==null && d2 ==null ){                
            return 0d;
        }
        if( d2 == null){
        return d1;
        }
        d1=d1-d2;
        return d1;
    }
    
    public static Double add(Double d1,Double d2){
        if(d1==null && d2 !=null ){        
        return d2;
        }
        if(d1==null && d2 ==null ){        
        
            return 0d;
        }
        if( d2 == null){
        return d1;
        }
        d1=d1+d2;
        return d1;
    }
    
    public static Double multiply(Double d1,Double d2){
        if(d1==null || d2== null){
        return 0d;
        }        
        BigDecimal t=new BigDecimal(d1).multiply(new BigDecimal(d2));
//        Double t=d1*d2;
        return t.doubleValue();
    }
    private static Random random = new Random(0);
    /**
     * http://stackoverflow.com/questions/363681/generating-random-integers-in-a-specific-range
     * @param min inclusive
     * @param max exclusive , max -1 will be considered
     * @return 
     */
    public static int random(int min,int max){
//            random.setSeed(min);
                     
//        return random.nextInt(max - 1);
        return ThreadLocalRandom.current().nextInt(min, max );
    }
}
