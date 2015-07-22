/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.app.ui.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author mjawath
 */
public class Propt {
   
   public final static String path = System.getProperty("user.dir")+File.separator+"config"+File.separator;
   public final static  Properties  properties = loadProperties(path+"config.properties");
//   public final static  Properties  SystemProperties = loadProperties(path+"config.properties");
//   public final static  Properties  ComponentProperties = loadProperties(path+"config.properties");
    
    
    public static  Properties loadProperties(String file){

     Properties  properties = new Properties();
        try {
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
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
