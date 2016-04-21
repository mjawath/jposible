/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invoicingsystem;

import app.AppMainWindow;
import app.utils.SystemStatic;
import app.utils.SystemUtil;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.biz.DropMainMenu;
import org.biz.MenuBuilder;
import org.biz.app.ui.util.Tracer;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.ui.list.master.ItemMasterFrame;
import org.biz.invoicesystem.ui.transactions.WareHouseFrame;
import org.biz.util.ReflectionUtility;
import org.components.controls.CButton;
import org.components.controls.Menu;
import org.components.controls.MenuItem;
import org.components.controls.MenuItemComponent;
import org.components.util.ComponentFactory;
import org.components.util.Sessions;
import org.components.windows.DetailPanel;
import org.components.windows.MasterViewUI;
import org.components.windows.UIController;
import org.components.windows.UIFrame;

/**
 *
 * @author d
 */
public class ApplicationManager {

    private HashMap controllers = new HashMap();
    
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
                
//                setFocusTraversialPolicy();
                System.out.println("============Application is starting..........===================");
                AppMainWindow app = new AppMainWindow();
                ComponentFactory.setFocusTraversialPolicy();
                addSystemToolsToToolbar();
                app.setExtendedState(JFrame.MAXIMIZED_BOTH);
                app.setVisible(true);
                
//                setMenuBar();
                buildMenuBar();                
                
                
            }
        });

  
}
    
   
   
   
    
    private static void buildMenuBar(){
      
        //get Parent menu name
        //get child menu name
        //get controller class
        
        MenuItem menuItem = new MenuItem();
        menuItem.setDescription("Customer detail");
        menuItem.setParentMenu("Master >  Customer ");
        menuItem.setMenuName("Customer detail");
        menuItem.setActionClassName("org.biz.invoicesystem.ui.list.master.CustomerController");
        menuItem.setType(0);
        
        MenuBuilder menuBuilder = new MenuBuilder();
        menuBuilder.createMenuItem(menuItem);
        
        
        MenuItem menuItemx = new MenuItem("Master >  Item ","org.biz.invoicesystem.ui.list.master.ItemController",0,"Item detail","Item detail");
        menuBuilder.createMenuItem(menuItemx);
        MenuItem mii = new MenuItem("Master >  Item ", "org.biz.invoicesystem.ui.list.master.SKUController", 0, "Item SKU detail", "Item SKU detail");
        menuBuilder.createMenuItem(mii);
        
        MenuItem mi = new MenuItem("Transactions >  Sales ", "org.biz.ui.prototype.SalesInvoiceControler", 0, "Sales detail", "Sales detail");
        menuBuilder.createMenuItem(mi);
        
        
        MenuItem mis = new MenuItem("Transactions >  Sales ", "org.biz.ui.prototype.SalesInvoiceControler","SaleInvoiceUIX" ,0, "Sales detail x", "Sales detailx");
        menuBuilder.createMenuItem(mis);

        
        MenuItem mis2 = new MenuItem("Transactions >  Sales ", "org.biz.ui.prototype.SalesInvoiceControler", "SaleDetailPanelUI", 1, "Sales Detail Table", "menu of Sales detailx");
        menuBuilder.createMenuItem(mis2);
        
        JMenuBar mb = SystemUtil.getMenuBar();
        
        
//        Menu myMenuParent = new Menu();
//        myMenuParent.setName("Master");
//        myMenuParent.setText("Master");
//        
//        mb.add(myMenuParent);
//        
//        MenuItemComponent myMenu = new MenuItemComponent();
//        myMenuParent.add(myMenu);
//        
//        myMenu.setName("Category");
//        myMenu.setText("Category");
        
//        final CategoryController itc = new CategoryController();        
//        itc.initUI();
//        if(itc instanceof UIController){            
////            itc.get
//            myMenu.addActionListener(new ActionListener() {
//
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    itc.showFrame();
//                }
//            });
//            ItemController it = new ItemController();
//            it.initUI();
//            addMenu("Master", "Item", "Item Detail", "Item Master", it);
            
//            SalesInvoiceControler sic = new SalesInvoiceControler();
//            sic.initUI();            
//            addMenu("Transaction", "SalesInvoice", "Sales Invoic Detail", "Sales Invoic  Master", sic);

            
//            CustomerController customerController = new CustomerController();
//            customerController.initUI();
//            addMenu("Master", "Customer", "Customer Detail", "Customer Master", customerController);

//        }
    
    }
    
    private static void addMenu(String parentMenu,String childMenuName,final String MenuItemDetail,final String MenuItemMaster,final UIController controller){
        
        
        
        JMenuBar mb = SystemUtil.getMenuBar();
//        mb.add(myMenuParent);
        Menu myMenuParent  = null;
        for (Component component : mb.getComponents()) {
            if(component instanceof Menu){
                Menu mym = (Menu)component;
                if(parentMenu.equals(mym.getName())){
                    myMenuParent = mym;
                    break;
                }
            }
        }
        if(myMenuParent== null){
        myMenuParent = new Menu();
        myMenuParent.setName("menu"+parentMenu);
        myMenuParent.setText(parentMenu); 
        mb.add(myMenuParent);
        }        
        
        if(controller.getUIFrame()!= null ){
        MenuItemComponent myMenu = new MenuItemComponent();
        myMenuParent.add(myMenu);
        
        myMenu.setName("subMenu"+childMenuName);
        myMenu.setText(childMenuName);
        myMenu.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.showFrame();
                }
            });
        }else{            
        
        Menu myLocalMenuParent = new Menu();
        myLocalMenuParent.setName("subMenu"+childMenuName);
        myLocalMenuParent.setText(childMenuName); 
        myMenuParent.add(myLocalMenuParent);
            
            
        MenuItemComponent myMenu = new MenuItemComponent();
        myLocalMenuParent.add(myMenu);        
        
        myMenu.setName("subMenuDetail"+MenuItemDetail);
        myMenu.setText(MenuItemDetail);
        myMenu.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    showFrame(MenuItemMaster, controller.getDetailView());
                }
            });   
        
        
        
        MenuItemComponent myMenu2 = new MenuItemComponent();
        myLocalMenuParent.add(myMenu2);        
        myMenu2.setName("subMenuMaster"+MenuItemMaster);
        myMenu2.setText(MenuItemMaster);
        myMenu2.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
//                    controller.showFrame();//show master frame
                    showFrame(MenuItemDetail, controller.getListView());
                }
            });   
    }
    }

    
  
    public static void showFrame(String title,JComponent panel){
    
        UIFrame fr = new UIFrame();
        fr.setTitle(title);
        fr.setSize(1300,700);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        final Container contentPane = fr.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panel,BorderLayout.CENTER);
        fr.pack();
        fr.setVisible(true);
        fr.toFront();
    }
      
    
    

    public static void initUI(final Class service, String title, Class dp, Class lv) {
        try {
            final DetailPanel obj2 = (DetailPanel) ReflectionUtility.getDynamicInstance(dp);
            obj2.config();
            final MasterViewUI obj3 = (MasterViewUI) ReflectionUtility.getDynamicInstance(lv);
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
//            final MasterViewUI obj3 = (MasterViewUI) ReflectionUtility.getDynamicInstance(lv);
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

        
    public static void initListUI(final Class service, String tablabel,String title,  final MasterViewUI dp,DetailPanel det) {
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