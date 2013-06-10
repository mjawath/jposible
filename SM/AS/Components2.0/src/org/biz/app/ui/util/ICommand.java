/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.util;

/**
 *
 * @author d
 */

public interface ICommand  {

    public Object executeTask();
    
    public void resultTask(Object  objs);
}