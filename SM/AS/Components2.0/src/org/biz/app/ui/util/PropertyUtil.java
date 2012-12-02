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

    public static  Properties loadProperties(String file){

     Properties  properties = new Properties();
        try {
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            
        }
     return properties;
    }
}
