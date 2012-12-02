/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
  
package org.biz.app.ui.util;

import java.io.PrintWriter;
import java.io.StringWriter;


/**
 *
 * @author mjawath
 */
public class StackTrace {
  public static String getStackTrace(Throwable t)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        t.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }
}

