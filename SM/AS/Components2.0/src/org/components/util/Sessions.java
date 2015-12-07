/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.components.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import org.biz.util.ReflectionUtility;
import org.components.windows.UIController;

/**
 *
 * @author mjawath
 */
public class Sessions {

    private static HashMap<String, UIController> controllerMap = new HashMap<>();
    
    public static void addController(String key, UIController controller) {
        controllerMap.put(key, controller);
    }

    public static UIController getController(String key) {
        return controllerMap.get(key);
    }
    
    public static UIController getControllerOrCreateNew(String key) {
        UIController cont = controllerMap.get(key);
        if(cont==null){
            cont = (UIController)ReflectionUtility.getDynamicInstance(key);
            cont.initUI();
            
        }
        return cont;
    }
    static  HashMap<String, Object> hashMap;
    public static  Sessions sessions = new Sessions();

    public static void create() {
        getSession();
    }
    private  Sessions() {
        hashMap = new HashMap<String, Object>();
       
    }

    public static Sessions  getSession(){
        if (sessions==null) {
            sessions = new Sessions();
        }
        if (hashMap==null) {
            hashMap = new HashMap<String, Object>();
        }
        return sessions;
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
