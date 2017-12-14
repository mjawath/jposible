package invoicingsystem;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainWindow.java
 *
 * Created on Dec 13, 2010, 10:16:22 PM
 */
import app.AppMainWindow;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import org.biz.books.ui.accounts.AccountsCreationUI;
import org.biz.books.ui.accounts.GeneralLedgerUI;
import org.biz.invoicesystem.master.ui.WareHouseUI;
import org.biz.invoicesystem.master.ui.ItemMasterTab;
import org.biz.invoicesystem.master.ui.PurchaseMasterUi;
import org.biz.invoicesystem.master.ui.ShopUI;
import org.biz.invoicesystem.master.ui.SupplierMasterTab;
import org.biz.invoicesystem.ui.transactions.*;
import org.components.util.Sessions;
import org.components.windows.TabPanelUI;


/**
 *
 * @author mjawath
 */
public class MainAppWindow extends AppMainWindow {

    public MainAppWindow() {
        try {
              System.out.println("");              
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
//            UIManager.put("javax.swing.plaf.FontUIResource","'Tahoma', 0, 24");
            UIManager.put("Table.font", new FontUIResource("Tahoma", 0, 20));
            UIManager.put("Table.background", new ColorUIResource(240, 140, 100));
//            System.setProperty("sun.awt.exception.handler", AwtHandler.class.getName());
        } catch (Exception e) {
        }
        initComponents();
        System.out.println("ddddddddd");
        init();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        cstattus = new org.components.controls.CLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mclose = new javax.swing.JMenuItem();
        mrefresh = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(600, 600));

        cstattus.setText("");

        jMenu1.setText("File");

        mclose.setText("Close Selected");
        jMenu1.add(mclose);

        mrefresh.setText("Refresh");
        jMenu1.add(mrefresh);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(560, 560, 560)
                .addComponent(cstattus, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cstattus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void init() {
//
//        dbsu = new DBServerUtil();
//        dbsu.init();
//        dbsu.startServer();
//        Sessions.create();
//        Sessions.addToSession("mainui", this);
//        addToTabpanelToUI(new ItemMasterUI(), "Item master");
    }

    public void init2() {
        mclose.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                   removeTab();
                    }
                });
        mrefresh.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                   refreshTab();
                    }
                });
        setjTabbedPane1(jTabbedPane1);
       
        addToTabpanelToUI(new ItemMasterTab(), "Item master");
        addToTabpanelToUI(new SupplierMasterTab(), "Master master");
//       addToTabpanelToUI(new InvoiceTestUI2() , "Invoice test");
//        addToTabpanelToUI(new AccountsCreationUI(), "acoouts master");
//        addToTabpanelToUI(new SalesInvoiceUI(), "Invoice master");
//        addToTabpanelToUI(new PurchaseInvoiceUI(), " purchase Invoice ");
//        addToTabpanelToUI(new ItemInventorySummary(), "item summery");
//        addToTabpanelToUI(new CategoryMasterUI(), "cateogory ui");
//        addToTabpanelToUI(new ProductMasterUI(), "product master ui");
//        addToTabpanelToUI(new PostedSalesInvoiceUI(), "poseted sales invoice");
//        addToTabpanelToUI(new GRNUI(), "GRN invoice");
//        addToTabpanelToUI(new BankDetailUI(), "GDN invoice");
//        addToTabpanelToUI(new BankDetailUI(), "bank ");
//        addToTabpanelToUI(new BankBranchDetailUI(), "bank branch");
//        addToTabpanelToUI(new ChequeDetailUI(), "cheque");

        addToTabpanelToUI(new ShopUI(), "shop statement");
        addToTabpanelToUI(new WareHouseUI(), "ware house");
        addToTabpanelToUI(new SingleTransferOrderUI(), "transfer order");
        events();
        
//      
//      
        addToTabpanelToUI(new PurchaseMasterUi(),"  * purcas Invoice Master *");
        
//        addToTabpanelToUI(new ContactsUI(),"  * contact Master *");
        addToTabpanelToUI(new GeneralLedgerUI(),"  *  General Ledger *");
        addToTabpanelToUI(new AccountsCreationUI(),"  * accounts Master *");
    System.gc();
    }
 
   

    public void showError(Throwable e) {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] trace = e.getStackTrace();
        for (int i = 0; i < trace.length; i++) {
            sb.append("\t\nat " + trace[i]);
        }

        Throwable cause = e.getCause();

        sb.append(cause);

        StackTraceElement[] causeTrace = cause.getStackTrace();
        for (int i = 0; i < causeTrace.length; i++) {
            sb.append("\t\nat " + causeTrace[i]);
        }


    }

    public void events() {
        getjTabbedPane1().addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {

                if (getjTabbedPane1().getSelectedComponent() == null) {
                    return;
                }

                Component jsp = (Component) getjTabbedPane1().getSelectedComponent();

//                for (Component component : jsp.getComponents()) {
                if (jsp instanceof TabPanelUI) {
                    TabPanelUI tc = (TabPanelUI) jsp;
                    tc.updateEntityUI();
                }
            }
//            }
        });



        
    }

    public void addToTabpanelToUI(String tabName, Class cls) {
        addToTabpanelToUI(tabName, cls, null);
    }

    public void addToTabpanelToUI(final String tabName, final Class cls, final Object obj) {

//        SwingWorker swingWorker = new SwingWorker() {
//
//            @Override
//            protected Object doInBackground() throws Exception {
//            this code has bugs becasuse when we add tabs this throws exceptions /
        //    beacase many swingw workers update tabs
        try {
            TabPanelUI tpui = (TabPanelUI) Sessions.getObj(tabName);
            int ix = getjTabbedPane1().indexOfTab(tabName);
            if (tpui == null) {
                tpui = (TabPanelUI) Class.forName(cls.getName()).newInstance();
                getjTabbedPane1().add(tabName, tpui);
                int sx = getjTabbedPane1().indexOfTab(tabName);
                getjTabbedPane1().setSelectedIndex(sx);
                Sessions.addToSession(tabName, tpui);

            } else {
                getjTabbedPane1().setSelectedIndex(ix);
                tpui.setobj(obj);
            }
        
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
//                return null;
//            }
//        };
//        swingWorker.execute();

    }

    public void addToTabpanelToUI(TabPanelUI tab, String title, final Object obj) {
        try {
            TabPanelUI tpui = (TabPanelUI) Sessions.getObj(title);
       
            if (tpui == null) {
                tpui = tab;
                getjTabbedPane1().add(title, tpui);
                int sx = getjTabbedPane1().indexOfTab(title);
                Sessions.addToSession(title, tpui);
                getjTabbedPane1().setSelectedIndex(sx);

            } else {
                 int ix = getjTabbedPane1().indexOfTab(title);
                 getjTabbedPane1().setSelectedIndex(ix);
              tpui.setobj(obj);
            }
         
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

//    public void addToTabpanelToUI(TabPanelUI tab, String title) {
//
////        addToTabpanelToUI(tab, title, null);
//         getjTabbedPane1().addTab(title, tab);
//        
//        
//    }

    public void selectTab(String title) {
        int sx = getjTabbedPane1().indexOfTab(title);
        if (sx != -1) {
            getjTabbedPane1().setSelectedIndex(sx);
        }
    }

    public void addToTabpdddanelToUI(String title, Container jp) {

        int ix = getjTabbedPane1().indexOfTab(title);
        if (ix == -1) {
            getjTabbedPane1().add(title, jp);
            int sx = getjTabbedPane1().indexOfTab(title);
            getjTabbedPane1().setSelectedIndex(sx);
        } else {
            getjTabbedPane1().setSelectedIndex(ix);
        }

    }

    public void removeTab() {
        int x = getjTabbedPane1().getSelectedIndex();
        if (x < 0) {
            return;
        }

        String tit = getjTabbedPane1().getTitleAt(x);
        Sessions.remove(tit);
        getjTabbedPane1().remove(x);

    }

    public void refreshTab() {
        int x = getjTabbedPane1().getSelectedIndex();
        if (x < 0) {
            return;
        }

        String tit = getjTabbedPane1().getTitleAt(x);
        getjTabbedPane1().remove(x);
        TabPanelUI tab= (TabPanelUI) Sessions.getObj(tit);
           String cls=   tab.getClass().getName();
       
        System.out.println(tab.getClass().getName());
        Sessions.remove(tit);
       
        try {
       TabPanelUI tabnew = (TabPanelUI) Class.forName(cls).newInstance();
       addToTabpanelToUI(tabnew, tit);
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public void selectNextTab(){
        int x=getjTabbedPane1().getSelectedIndex();
        int y=getjTabbedPane1().getTabCount();
        if (x== -1) {
            x=0;
            getjTabbedPane1().setSelectedIndex(x);
        }else{
            if (y>(x+1)) {
                 getjTabbedPane1().setSelectedIndex(++x);
            }
           
        }

    }
    public void selectPreviousTab(){
        int x=getjTabbedPane1().getSelectedIndex();
         int y=getjTabbedPane1().getTabCount();
         if (x== -1) {
            x=0;
            getjTabbedPane1().setSelectedIndex(x);
        }else{
             if (x>0) {
                  getjTabbedPane1().setSelectedIndex(--x);
             }
 
          
        }

    }


    
    public void closeLogin() {
        JDialog dialog = (JDialog) Sessions.getObj("login");        
        dialog.dispose();

    }
   

    

    public void showlogin() {
        JDialog dd = new JDialog(this, JDialog.ModalityType.MODELESS);
        dd.setSize(400, 300);
        dd.setLocationRelativeTo(null);

        dd.setVisible(true);
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cstattus;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem mclose;
    private javax.swing.JMenuItem mrefresh;
    // End of variables declaration//GEN-END:variables
}
