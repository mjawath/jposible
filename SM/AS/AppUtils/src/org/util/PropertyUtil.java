package org.util;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author mjawath
 */
public class PropertyUtil {

    //property file name
    private static Properties properties = new Properties();
    private File configFile; 
    
    public static  Properties loadProperties(String file){
     
        try {
            properties.load(new FileInputStream(file));            
        } catch (IOException e) {
           
        }
     return properties;
    }        

    static {
        if(properties.isEmpty()){
            PropertyUtil.loadProperties(AppStatic.AppProperties_Path);
        }
    }

    
    public PropertyUtil() {
        init(getDefaultConfig());
    }

    private void init(File configfile) {
        this.configFile = configfile;
        load();
    }

    private File getDefaultConfig() {
        
        return new File(new File(AppStatic.AppLocation_Path), "unicentaopos.properties");
    }

    private void load() {
        // Load Properties
        try {
            InputStream in = new FileInputStream(configFile);
            if (in != null) {
                properties = new Properties();
                properties.load(in);
                in.close();
            }
        } catch (IOException e) {
        }
    }
    
    public static byte getApplicationmod(){
        return 1;
    }
    
    public static <T> T getProperty(String propName) {
        Object value = properties.get(propName);        
        if(value==null)return null;
        return (T)value;
    }
    
    public static  Boolean getPropertyBoolean(String propName) {
        String value = getProperty(propName);        
        if(value==null)return null;
        return Boolean.parseBoolean(value);
    }
    
    private static Object getProperty(Properties props, String name, Class<?> type) {
        String value = props.getProperty(name);
        if (value == null)
            throw new IllegalArgumentException("Missing configuration value: " + name);
        if (type == String.class)
            return value;
        if (type == boolean.class)
            return Boolean.parseBoolean(value);
        if (type == int.class)
            return Integer.parseInt(value);
        if (type == float.class)
            return Float.parseFloat(value);
        throw new IllegalArgumentException("Unknown configuration value type: " + type.getName());
    }

}
