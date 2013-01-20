/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fx.window;

import java.lang.reflect.Method;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 *
 * @author d
 */
public class FXUtil {
    
    public static void executeOnFXTask(final Object obj,final String taskname){
       
        new Service() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                    
                        Method method= obj.getClass().getDeclaredMethod(taskname, (Class<?>[]) null);
                        method.invoke(obj, (Object[]) null);
                        return "";
                    }

                    @Override
                    protected void done() {
                        System.out.println("done the mothod ");
//                        setOBJB("---");
                    }
                };
            }
        }.start();
        
    }
    
     
    public static void executeOnFXTask(final Object obj,final String taskname,final Object [] objs){
       
        new Service() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                    //check lenth
                        Class [] ca= new Class[objs.length];
                        for (int i = 0; i < objs.length; i++) {
                            Object object = objs[i];
                            //null
                            ca[i]=object.getClass();
                        }
                        Method method= obj.getClass().getDeclaredMethod(taskname, ca);
                        method.invoke(obj, (Object[]) objs);
                        return "";
                    }

                    @Override
                    protected void done() {
                        System.out.println("done the mothod ");
//                        setOBJB("---");
                    }
                };
            }
        }.start();
        
    }

}
