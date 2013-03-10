/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.system.util;

/**
 *
 * @author d
 */
public class Tracer {
    
    public static void printTracer(Object message){
    if(!getTracerState())return;
        System.out.println(message);
    }
    
    
    public static boolean getTracerState(){
    return true;
    }
}
