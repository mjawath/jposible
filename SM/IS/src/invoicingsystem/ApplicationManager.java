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
import org.biz.erp.inventory.ui.list.InventoryJournalListViewUI;
import org.biz.erp.inventory.ui.detail.InventoryJournalUI;
import org.biz.erp.ui.transaction.detail.InvoiceUI;
import org.biz.erp.ui.transactions.posted.PostedInvoicesListUI;
import app.utils.SystemStatic;
import java.awt.Frame;
import org.biz.erp.inventory.ui.ItemInventorySummary;
import org.biz.erp.inventory.ui.WareHouseUI;
import org.biz.erp.inventory.ui.list.WareHouseListUI;
import org.biz.invoicesystem.master.ui.ShopUI;
import org.biz.invoicesystem.service.inventory.InventoryJournalService;
import org.biz.invoicesystem.service.master.CategoryService;
import org.biz.invoicesystem.service.master.ItemService;
import org.biz.invoicesystem.service.master.ShopService;
import org.biz.invoicesystem.service.master.WareHouseService;
import org.biz.invoicesystem.service.transactions.SalesInvoiceService;
import org.biz.invoicesystem.ui.list.master.CategoryListUI;
import org.biz.invoicesystem.ui.list.master.ItemList;
import org.biz.master.ui.CategoryUI;
import org.biz.master.ui.ItemMasterUI2;
import org.biz.ui.master.list.ShopListUI;
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

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Sessions.create();
                System.out.println("===============================");
                AppMainWindow app = new AppMainWindow();
                app.setExtendedState(Frame.MAXIMIZED_BOTH);
//              app.addToTabpanelToUI("Item",ItemMasterUI2.class);
                app.setVisible(true);
                DetailPanel item=new ItemMasterUI2();
//                initDetailUI(ItemService.class, "Item Detail " ,"ItemDetail",item);
//                initListUI(ItemService.class, "Item List " ,"ItemList",new ItemList(),item);
//                initUI(ItemService.class, "Item Detail " ,"ItemDetail",ItemMasterUI2.class);
                initUI(ItemService.class, "Item", ItemMasterUI2.class, ItemList.class);
                initUI(CategoryService.class, "Category", CategoryUI.class, CategoryListUI.class);
//                initUI(SalesInvoiceService.class, "Sales", InvoiceUI.class, PostedInvoicesListUI.class);
                initUI(InventoryJournalService.class, "InventoryJournal", InventoryJournalUI.class, InventoryJournalListViewUI.class);
//                initUI(SalesInvoiceService.class, "Sales", InvoiceUI.class, PostedInvoicesListUI.class);
//                initUI(SupplierService.class, "Supplier", SupplerDetailUI.class, SupplierListUi.class);
//                initUI(CustomerService.class, "Customer", CustomerDetailUI.class, CustomerListUi.class);
//                initUI(StaffService.class, "Staff", StaffDetailUI.class, StaffListUi.class);
                initUI(WareHouseService.class, "WareHouse", WareHouseUI.class, WareHouseListUI.class);
//                initUI(ShopService.class, "Shop", ShopUI.class, ShopListUI.class);
                 
        ((AppMainWindow)SystemStatic.getMainWindow()).addMenu("item", ItemMasterUI2.class,ItemService.class);
        ((AppMainWindow)SystemStatic.getMainWindow()).addMenu("itemList", ItemList.class,ItemService.class);
        ((AppMainWindow)SystemStatic.getMainWindow()).addMenu("Category", CategoryUI.class,CategoryService.class);
        ((AppMainWindow)SystemStatic.getMainWindow()).addMenu("CategoryList", CategoryListUI.class,CategoryService.class);
        ((AppMainWindow)SystemStatic.getMainWindow()).addMenu("InventoryJournalDETAIL", InventoryJournalUI.class,InventoryJournalService.class);
        ((AppMainWindow)SystemStatic.getMainWindow()).addMenu("InventoryJournalLIST", InventoryJournalListViewUI.class,InventoryJournalService.class);
        ((AppMainWindow)SystemStatic.getMainWindow()).addMenu("InventorySummery", ItemInventorySummary.class,InventoryJournalService.class);
            }
        });
//            SalesInvoiceService sis=new SalesInvoiceService(); 
//            sis.initUI();
//            ItemService itemser=new ItemService();
//            itemser.initUI();
        
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

}
