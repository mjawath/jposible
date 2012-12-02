/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.util;

import java.lang.Thread.UncaughtExceptionHandler;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Mohamed jawath
 */
public final class GlobalExceptionHandler implements UncaughtExceptionHandler {

    String showError(Throwable e) {

        StringBuilder sb = new StringBuilder();
        String ety = e.getMessage();
        sb.append("--------------error-----------\t\n");
        sb.append("Exception is    " + ety);

        StackTraceElement[] trace = e.getStackTrace();
        for (int i = 0; i < trace.length; i++) {
            sb.append("\t\nat " + trace[i]);
        }
        sb.append("\t\nat----------couse of error--------");
        Throwable cause = e.getCause();
        if (cause != null) {
            sb.append(cause);
        }



//        StringBuilder sb = new StringBuilder();
//        StackTraceElement[] trace = e.getCause().getStackTrace();
//        for (int i = 0; i < trace.length; i++) {
//            sb.append(sb.toString() + "\t\nat " + trace[i]);
//        }
//           System.out.println("sb       "+sb);
//        Throwable cause = e.getCause();
//
//        sb.append(sb.toString()+ "\n\n" + cause + "\n\n"+e.getMessage());

//        StackTraceElement[] causeTrace = cause.getStackTrace();
//        for (int i = 0; i < causeTrace.length; i++) {
//             sb.append(sb.toString() + "\t\nat " + causeTrace[i]);
//        }
        return sb.toString();

    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("------catch exception      ----");
        if (e != null) {

            if (e instanceof ExceptionInInitializerError) {
                ExceptionInInitializerError cause = (ExceptionInInitializerError) e;
                if (cause.getException() instanceof Exception) {
                    JOptionPane.showMessageDialog(null, "Database error");
                    return;
                } else if (cause.getException() instanceof NumberFormatException) {
                    JOptionPane.showMessageDialog(null, "Input values error");
                    return;
                }
            } else if (e instanceof OutOfMemoryError) {
                JOptionPane.showMessageDialog(null, "Application is out of memory. ");
                return;
            }
            /** This is undefined exception stacktraces in the if else conditions*/
            error errorShower = new error();
            String ss = showError(e);
            errorShower.setText(ss);
            errorShower.setVisible(true);
            errorShower.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            e.printStackTrace();
//       JOptionPane.showMessageDialog(null, showError(e));
//       System.out.println("----------"+);

        }
           System.out.println("eeeeeeeeeeeeeeeeeeeeee00000000000000000000eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
    }
}
