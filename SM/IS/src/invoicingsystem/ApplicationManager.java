/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invoicingsystem;

import app.AppMainWindow;
import app.utils.SystemUtil;
import org.biz.util.ReflectionUtility;
import org.biz.app.ui.util.Tracer;
import org.biz.dao.service.Service;
import app.utils.SystemStatic;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import org.biz.DropMainMenu;
import org.biz.erp.ui.transaction.detail.InvoiceDetailUI;
import org.biz.invoicesystem.ui.transactions.ItemMasterFrame;
import org.biz.invoicesystem.ui.transactions.WareHouseFrame;
import org.components.controls.CButton;
import org.components.util.Sessions;
import org.components.windows.DetailPanel;
import org.components.windows.ListViewPanel;

/**
 *
 * @author d
 */
public class ApplicationManager {

    public static void main(String[] args) {
//         GenericDAOUtil.createEMFWithCustomProperties();
       

            try {
                   for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
                } catch (Exception e) {
                    System.out.println("------");
                } 
//        final InvoiceDetailUI iv =new InvoiceDetailUI();
//                
//        
//        SwingUtilities.invokeLater(new Runnable() {
//
//            @Override
//            public void run() {                
//                iv.showAsFrame();
//            }
//        });
    
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Sessions.create();
                System.out.println("============Application is starting..........===================");
                AppMainWindow app = new AppMainWindow();
                addSystemToolsToToolbar();
                app.setExtendedState(Frame.MAXIMIZED_BOTH);
                app.setVisible(true);

                setMenuBar();
            }
        });

  
}
    
    

    public static void initUI(final Class service, String title, Class dp, Class lv) {
        try {
            final DetailPanel obj2 = (DetailPanel) ReflectionUtility.getDynamicInstance(dp);
            obj2.config();
            final ListViewPanel obj3 = (ListViewPanel) ReflectionUtility.getDynamicInstance(lv);
            obj3.config();
            obj3.setDetailPanel(obj2);
            SystemUtil.addToMainWindow(obj2, title + "DETAIL");
            SystemUtil.addToMainWindow(obj3, title + "LIST");
            Tracer.printToOut("UI are set ");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Service obj = (Service) ReflectionUtility.getDynamicInstance(service);
                    obj2.setService(obj);
                    obj3.setService(obj);
                    Tracer.printToOut("servies are set in new thread ");
                }
            }).start();

        }
        catch (Exception e) {
            Tracer.exceptionOutPrint("ApplicationManager> initUI ",e);
        }
        
    }

    public static void initUI(final Class service, String tablabel,String title,  Class dp) {
        try {
            final DetailPanel obj2 = (DetailPanel) ReflectionUtility.getDynamicInstance(dp);
            obj2.config();
//            final ListViewPanel obj3 = (ListViewPanel) ReflectionUtility.getDynamicInstance(lv);
//            obj3.config();
            obj2.setTabName(title);
            SystemUtil.addToMainWindow(obj2, title ,tablabel);
//            SystemUtil.addToMainWindow(obj3, title + "LIST");
            Tracer.printToOut("UI are set ");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Service obj = (Service) ReflectionUtility.getDynamicInstance(service);
                    obj2.setService(obj);
//                    obj3.setService(obj);
                    Tracer.printToOut("servies are set in new thread ");
                }
            }).start();

        }
        catch (Exception e) {
            Tracer.exceptionOutPrint(e.getMessage());
        }
        
    }

    
    public static void initDetailUI(final Class service, String tablabel,String title,  final DetailPanel dp) {
        try {
            dp.config();
            SystemUtil.addToMainWindow(dp, title ,tablabel);
//            SystemUtil.addToMainWindow(obj3, title + "LIST");
            Tracer.printToOut("Detail UI is set ");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Service obj = (Service) ReflectionUtility.getDynamicInstance(service);
                    dp.setService(obj);
//                    obj3.setService(obj);
                    Tracer.printToOut("servies are set in new thread ");
                }
            }).start();

        }
        catch (Exception e) {
            Tracer.exceptionOutPrint(e.getMessage());
        }
        
    }

        
    public static void initListUI(final Class service, String tablabel,String title,  final ListViewPanel dp,DetailPanel det) {
        try {
            dp.config();
            dp.setDetailPanel(det);
//            det.config();
            SystemUtil.addToMainWindow(dp, title ,tablabel);
//            SystemUtil.addToMainWindow(obj3, title + "LIST");
            Tracer.printToOut("Detail UI is set ");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Service obj = (Service) ReflectionUtility.getDynamicInstance(service);
                    dp.setService(obj);
//                    obj3.setService(obj);
                    Tracer.printToOut("servies are set in new thread ");
                }
            }).start();

        }
        catch (Exception e) {
            Tracer.exceptionOutPrint(e.getMessage());
        }
        
    }
    private static JPopupMenu popup = new JPopupMenu();

    public static void addSystemToolsToToolbar() {
        final CButton btn = new CButton();
        btn.setPreferredSize(new Dimension(100,180));
        btn.setText("Tools");
    
        
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //show popup              
//                popup.show(btn, btn.getWidth(), btn.getHeight());
                dropmenu.showPopup(btn);
            }
        });
        SystemUtil.addToSystemToolsToToolbar(btn);
    }
    static DropMainMenu dropmenu=new DropMainMenu();

    public static void setMenuBar() {
        JMenu menu = new JMenu("Trans");
        
        SystemStatic.getMainWindow().addMenu(menu);
        
        JMenuItem mi = new JMenuItem("GDN");
        JMenuItem mi2 = new JMenuItem("GRN");
        menu.add(mi);
        menu.add(mi2);
        
//        mi.addActionListener(new xx());
        
        setMenuItem(menu, "WareHouse", WareHouseFrame.class);
        setMenuItem(menu, "ItemMaster", ItemMasterFrame.class);
 
    }
    
    public static void setMenuItem(JMenu menu,String MenuName,Class frameName){
        JMenuItem menuItem = new JMenuItem(MenuName);
        JFrame fr =(JFrame)ReflectionUtility.getDynamicInstance(frameName);
       
        Act act =new Act(fr,popup);
        menuItem.addActionListener(act);
        menu.add(menuItem);
        
    }
}
 
class Act implements ActionListener {

    JPopupMenu pop;
    public Act(JFrame frm,JPopupMenu pop) {
        this.frm = frm;
        this.pop = pop;
    }

    
    
    
    
        JFrame frm;
        public void setJframe(JFrame frm){
            this.frm = frm;

        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
//            GRNFrame fr = new GRNFrame();
            frm.setVisible(true);
            pop.setVisible(false);
            System.out.println("================");
        }
}