/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.util;

import java.util.Properties;

/**
 *
 * @author jawa
 */
public class MenuProperties {
    
    private static final Properties properties =PropertyUtil.loadProperties(AppStatic.AppProperties_Path);
    
    
    public static Object getSS(){
        return properties.get("customer");
    }
    
}
