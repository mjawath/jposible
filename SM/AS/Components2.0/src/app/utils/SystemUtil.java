/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.utils;

import app.AppMainWindow;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import org.components.util.Sessions;
import org.components.windows.MainWindow;
import org.components.windows.TabPanelUI;

/**
 *
 * @author d
 */
public class SystemUtil {

    public static Sessions sessions = Sessions.getSession();

    public static void addTabToSessions(String key, Object comp) {
        sessions.addToSession(key, comp);
    }

    public static Object getObj(String key) {
        return sessions.getObj(key);
    }

    public static String getKeys() {
        String rn = "" + AB.charAt(rnd.nextInt(AB.length()));
        String key = System.currentTimeMillis() + "-" + rn;
        return key;
    }

    public static String getKeyStr() {
        return randomString(15);
    }

    static String randomString(int len) {

//        Calendar calendar = Calendar.getInstance();
//        StringBuilder sb = new StringBuilder(len);
//        for (int i = 0; i < len; i++) {
//            sb.append(AB.charAt(rnd.nextInt(AB.length())));
//        }
//        sb.append("-").append(calendar.getTimeInMillis() + "" + AB.charAt(rnd.nextInt(AB.length())));
//        return sb.toString();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    public String getKey(String shopName) {

        String rn = "" + AB.charAt(rnd.nextInt(AB.length()));
        String rn2 = "" + AB.charAt(rnd.nextInt(AB.length()));
        String key = System.currentTimeMillis() + "-" + rn + rn2;

// / create a java calendar instance


// get a java.util.Date from the calendar instance.
// this date will represent the current instant, or "now".
//java.util.Date now = calendar.getTime();

// a java current time (now) instance
//java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());


        //java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());


        return key;
    }

    public String getKey() {

        String rn = "" + AB.charAt(rnd.nextInt(AB.length()));
        String rn2 = "" + AB.charAt(rnd.nextInt(AB.length()));
        String key = System.currentTimeMillis() + "-" + rn + rn2;

// / create a java calendar instance


// get a java.util.Date from the calendar instance.
// this date will represent the current instant, or "now".
//java.util.Date now = calendar.getTime();

// a java current time (now) instance
//java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());


        //java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());


        return key;
    }
    static final String AB = "GHIJBK013LO8L2MNOP7ECD456QRS9ABKFTUVWXYZ";
    static Random rnd = new Random();
    
    public static void addToMainWindow(final TabPanelUI tab, final String tabname) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow jf = SystemStatic.getMainWindow();//MainWindow) Sessions.getObj("mainui");
                jf.addToTabpanelToUI(tab, tabname);
            }
        });

    }

    public static void addToMainWindow(final TabPanelUI tab, final String tabname,final String tablbl) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow jf = SystemStatic.getMainWindow();
                jf.addToTabPanel(tab, tabname,tablbl);
            }
        });

    }

    public static MainWindow getMainWindow() {
        MainWindow jf = (MainWindow) Sessions.getObj("mainui");
        return jf;
    }
    
    public static JToolBar getToolbar(){
    
        AppMainWindow mw=(AppMainWindow) getMainWindow();        
        return mw.getToolbar();        
    }
    private static ArrayList<JComponent> toolbarList=new ArrayList();

  
       public static void addSystemToolsToToolbar(){
           for (JComponent com : toolbarList) {
               getToolbar().add(com);
           }
    }
     
    public static void addToSystemToolsToToolbar(JComponent com) {

        toolbarList.add(com);
        getToolbar().add(com);

    }
    
    static {
    addSystemToolsToToolbar();
    }
     
    public static void setTools(JComponent com){
        getToolbar().removeAll();
        addSystemToolsToToolbar();
        if(com!=null){
        getToolbar().add(com);
        }        
        getToolbar().revalidate();
        getToolbar().repaint();
        
    }

    public static void bringTabToFront( final String tabname) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow jf = SystemStatic.getMainWindow();// Sessions.getObj("mainui");
                jf.addToTabpanelToUI( tabname,null);
            }
        });

    }

    public static void bringTabToFront(TabPanelUI tab) {

        MainWindow jf =  getMainWindow();
        jf.selectTab(tab.getTabName());

    }

}
