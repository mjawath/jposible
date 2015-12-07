/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.util;

import java.io.File;

/**
 *
 * @author jawa
 */
public class AppStatic {
    
    public final static String AppLocation_JavaHome = System.getProperty("java.home");
    public final static String AppLocation_Path = System.getProperty("user.dir");
    public final static String AppProperties_Path = AppLocation_Path + File.separator+"Settings"+File.separator+"conf.properties";
    public final static String User_Home_Path = System.getProperty("user.home");
    
    
}
