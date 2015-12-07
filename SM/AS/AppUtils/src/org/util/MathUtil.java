/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.util;

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
        System.out.println("big");
//        BigDecimal t=new BigDecimal(d1).multiply(d2);
        Double t=d1*d2;
        return t;
    }
    
}
