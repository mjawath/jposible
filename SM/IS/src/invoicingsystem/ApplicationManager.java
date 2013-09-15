/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invoicingsystem;

import app.AppMainWindow;
import app.utils.SystemUtil;
import org.biz.app.ui.util.ReflectionUtility;
import org.biz.app.ui.util.Tracer;
import org.biz.dao.service.Service;
import org.biz.erp.ui.transaction.detail.InvoiceUI;
import org.biz.erp.ui.transactions.posted.PostedInvoicesListUI;
import org.biz.invoicesystem.service.master.CustomerService;
import org.biz.invoicesystem.service.master.ItemService;
import org.biz.invoicesystem.service.master.StaffService;
import org.biz.invoicesystem.service.master.SupplierService;
import org.biz.invoicesystem.service.transactions.SalesInvoiceService;
import org.biz.invoicesystem.ui.list.master.CustomerListUi;
import org.biz.invoicesystem.ui.list.master.ItemList;
import org.biz.invoicesystem.ui.list.master.StaffListUi;
import org.biz.invoicesystem.ui.list.master.SupplierListUi;
import org.biz.master.ui.CustomerDetailUI;
import org.biz.master.ui.ItemMasterUI2;
import org.biz.master.ui.StaffDetailUI;
import org.biz.master.ui.SupplerDetailUI;
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
//              app.addToTabpanelToUI("Item",ItemMasterUI2.class);
                app.setVisible(true);
                System.out.println("=========00000000000000======================");
                initUI(ItemService.class, "ITem", ItemMasterUI2.class, ItemList.class);
//                initUI(SalesInvoiceService.class, "Sales", InvoiceUI.class, PostedInvoicesListUI.class);
                initUI(SalesInvoiceService.class, "Sales", InvoiceUI.class, PostedInvoicesListUI.class);
                initUI(SupplierService.class, "Supplier", SupplerDetailUI.class, SupplierListUi.class);
                initUI(CustomerService.class, "Customer", CustomerDetailUI.class, CustomerListUi.class);
                initUI(StaffService.class, "Staff", StaffDetailUI.class, StaffListUi.class);
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
            final ListViewPanel obj3 = (ListViewPanel) ReflectionUtility.getDynamicInstance(lv);
            SystemUtil.addToMainWindow(obj2, title + "DETAIL");
            SystemUtil.addToMainWindow(obj3, title + "LIST");
            Tracer.printToOut("servies are set ");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Service obj = (Service) ReflectionUtility.getDynamicInstance(service);
                    obj2.setService(obj);
                    obj3.setService(obj);
                    Tracer.printToOut("servies are set ");
                }
            }).start();

        }
        catch (Exception e) {
            Tracer.printToOut(e.getMessage());
        }
    }
}
