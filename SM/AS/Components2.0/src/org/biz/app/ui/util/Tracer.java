/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.util;

/**
 *
 * @author d
 */
public class Tracer {
 
    //responsible for printing logs , file , system out
    
    public static void printToOut(String msg){
        
        /*TODO should be switchable */
        /*TODO should be Groupable */
            System.out.println(msg);
    }
    
    public static void exceptionOutPrint(String msg){
        
        /*TODO should be switchable */
        /*TODO should be Groupable */
            System.out.println("***********************EXception*****************************");
            System.out.println(msg);
    }
    
    public static void exceptionOutPrint(String msg,Exception e) {

        /*TODO should be switchable */
        /*TODO should be Groupable */
        System.out.println("***********************EXception*****************************");
        System.out.println(msg);
        e.printStackTrace();
    }
}
