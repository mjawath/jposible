/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.util;

import java.util.ArrayList;

/**
 *
 * @author d
 */

public interface ICommand  {

    public Object doBackgroundTask(Object ...objs);
    
    public void doResultTask(Object  ...objs);
    
//    public ArrayList getParams();
}