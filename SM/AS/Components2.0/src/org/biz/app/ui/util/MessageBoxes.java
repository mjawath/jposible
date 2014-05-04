/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.util;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class MessageBoxes {

    public static final int OK_OPTION = JOptionPane.OK_OPTION;
    public static final int CANCEL_OPTION = JOptionPane.CANCEL_OPTION;
    public static final int YES_OPTION = JOptionPane.YES_OPTION;
    public static final int NO_OPTION = JOptionPane.NO_OPTION;

    public static int okCancel(JComponent component, String msg, String title) {
        return JOptionPane.showConfirmDialog(component, msg,
                title, JOptionPane.OK_CANCEL_OPTION);
    }

    public static int yesNo(JComponent component, String msg, String title) {
       return JOptionPane.showConfirmDialog(component, msg,
                title, JOptionPane.YES_NO_OPTION);
    }

    public static void wrnmsg(JComponent component, String msg, String title) {
        JOptionPane.showMessageDialog(component, msg,
                title, JOptionPane.WARNING_MESSAGE);
        
    }

    public static void okmsg(JComponent component, String msg, String title) {
        JOptionPane.showMessageDialog(component, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void infomsg(JComponent component, String msg, String title) {
        JOptionPane.showMessageDialog(component, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void warn(JComponent component, String msg, String title) {
        JOptionPane.showMessageDialog(component, msg, title, JOptionPane.WARNING_MESSAGE);
    }

    public static void errormsg(JComponent component, String msg, String title) {
        JOptionPane.showMessageDialog(component, msg, title, JOptionPane.ERROR_MESSAGE);
    }

    public static int quesmsg(JComponent component, String msg, String title) {
        return JOptionPane.showConfirmDialog(component, msg,
                title, JOptionPane.YES_NO_OPTION);
    }
}
