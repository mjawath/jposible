/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.components.test;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nano
 */
public class Main {

    public String xyz=new String("nnnnnnn");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Main().getItem();
        
    }
    
    public void getItem(){
        try {
            Field field=getClass().getField("xyz");
            if(field.equals(xyz)){
                System.out.println("d");
            }
        }
        catch (Exception e){
        e.printStackTrace();
        }
    }

}
