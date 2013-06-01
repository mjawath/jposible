/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invoicingsystem;

import app.AppMainWindow;
import app.utils.SystemUtil;
import org.biz.invoicesystem.master.ui.ItemMasterUI2;
import org.biz.invoicesystem.service.transactions.SalesInvoiceService;
import org.components.util.Sessions;

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
              AppMainWindow app=new AppMainWindow();
//              app.addToTabpanelToUI("Item",ItemMasterUI2.class);
              app.setVisible(true);  
              System.out.println("=========00000000000000======================");

            }
        });
            SalesInvoiceService sis=new SalesInvoiceService();              

    }
    
}
