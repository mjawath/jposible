/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.app.ui.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author mjawath
 */
public class PropertyUtil {
    
   public final static  Properties  properties = loadProperties("config.properties");
   public final static  Properties  SystemProperties = loadProperties("config.properties");
   public final static  Properties  ComponentProperties = loadProperties("config.properties");
    

    public static  Properties loadProperties(String file){

     Properties  properties = new Properties();
        try {
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            
        }
     return properties;
    }
    
    public static byte getApplicationmod() {
        final Object value = properties.get("applicationmode");
        if (value != null) {
            try {
                return new Byte(value.toString());
            } catch (Exception e) {
            Tracer.exceptionOutPrint("Invalid Property specified in config.properyfile for applicationmode");
            }
        }
        return 0;
    }
    public static void main(String[] args) {
        System.out.println("============="+   getApplicationmod());
    }
    
}
