/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.components.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author mjawath
 */
public class Sessions {

    static  HashMap<String, Object> hashMap;
    public static  Sessions sessions;

    public static void create() {
        getSession();
    }
    private  Sessions() {
        hashMap = new HashMap<String, Object>();
       
    }

    public static void  getSession(){
        if (sessions==null) {
            sessions = new Sessions();
        }
        if (hashMap==null) {
            hashMap = new HashMap<String, Object>();
        }
    }

    public static void addToSession(String key,Object val){
    hashMap.put(key, val);
    }

    public static void remove(String key){
        hashMap.remove(key);
    }

    public static Collection values(){
        return  hashMap.values();
    }
    public static Set<String> keys(){
        return  hashMap.keySet();
    }

    public static  Object getObj(String key){
    return hashMap.get(key);
    }





}
