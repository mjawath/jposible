/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invoicingsystem;

import app.AppMainWindow;
import org.biz.invoicesystem.service.master.ItemService;
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
//            SalesInvoiceService sis=new SalesInvoiceService(); 
//            sis.initUI();
            ItemService itemser=new ItemService();
            itemser.initUI();

    }
    
}
