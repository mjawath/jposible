package org.components.windows;

 
import java.awt.Component;
import java.awt.Container;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import org.components.util.Sessions;

//http://code.google.com/p/krank/wiki/UsingDAO
/**
 *
 * @author mjawath
 */
public class MainWindow extends JFrame {

    public MainWindow() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
//            UIManager.put("javax.swing.plaf.FontUIResource","'Tahoma', 0, 24");
            UIManager.put("Table.font", new FontUIResource("Tahoma", 0, 20));
            UIManager.put("Table.background", new ColorUIResource(240, 140, 100));
//            System.setProperty("sun.awt.exception.handler", AwtHandler.class.getName());
        } catch (Exception e) {
        }
//        initComponents();
//        init();
    }
    String propfile;
    private final String tabBill = "Bill Counter";
    private final String tabviewBill = "View Bills ";
    private final String tabCashier = "Cashier Counter";
    private final String tabParcel = "Parcel Counter";
    private final String tabViewParcel = "View Parcel Counter Bills";
    private final String tabCasBill = "Cashier Bill Counter";
    private final String tab = " Counter";

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cstattus = new org.components.controls.CLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cstattus.setText("");
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cstattus, javax.swing.GroupLayout.DEFAULT_SIZE, 965, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(439, Short.MAX_VALUE)
                .addComponent(cstattus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    public void init() {
//
//        dbsu = new DBServerUtil();
//        dbsu.init();
//        dbsu.startServer();
        events();


    }

    

    void showError(Throwable e) {
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

    public void addToTabpanelToUI(String tabName, Object cls) {
        try {

            TabPanelUI tpui = (TabPanelUI) Sessions.getObj(tabName);

            if (tpui == null) {
                tpui = (TabPanelUI)cls;
                getjTabbedPane1().add(tabName, tpui);
                int sx = getjTabbedPane1().indexOfTab(tabName);
                Sessions.addToSession(tabName, tpui);
                initCloseButtonForTab(getjTabbedPane1(), sx);
                getjTabbedPane1().setSelectedIndex(sx);

            } else {
                int ix = getjTabbedPane1().indexOfTab(tabName);
                getjTabbedPane1().setSelectedIndex(ix);
//                initCloseButtonForTab(getjTabbedPane1(), ix);

            }


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
//                initCloseButtonForTab(getjTabbedPane1(), sx);
                getjTabbedPane1().setSelectedIndex(sx);
                tpui.setTabName(title);

            } else {
                 int ix = getjTabbedPane1().indexOfTab(title);
                 getjTabbedPane1().setSelectedIndex(ix);
//               initCloseButtonForTab(getjTabbedPane1(), ix);
           
                 tpui.setobj(obj);
            }
   
          
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
     public void addToTabpanelToUI(JPanel tab, String title, final Object obj) {
        try {
           
            JPanel tpui = (JPanel) Sessions.getObj(title);
       
            if (tpui == null) {
                tpui = tab;
                getjTabbedPane1().add(title, tpui);
                int sx = getjTabbedPane1().indexOfTab(title);
                Sessions.addToSession(title, tpui);
//                initCloseButtonForTab(getjTabbedPane1(), sx);
                getjTabbedPane1().setSelectedIndex(sx);
//                tpui.setTabName(title);

            } else {
                 int ix = getjTabbedPane1().indexOfTab(title);
                 getjTabbedPane1().setSelectedIndex(ix);
//               initCloseButtonForTab(getjTabbedPane1(), ix);
           
//                 tpui.setobj(obj);
            }
   
          
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public void initCloseButtonForTab(JTabbedPane pane,int index){
        try {
    TabCLosePnl p=new TabCLosePnl();
    
    
        JButton b=new JButton("View");
         b.setSize( 2,1);   
        p.add(b,0);
        JButton b2=new JButton("Close");
        b2.setSize(4,1);            
        p.add(b2,1);
     
      pane.setTabComponentAt(index,p);
        
        } catch (Exception e) {
       e.printStackTrace();
        }
    }
    
    public void closeTabPanel(){
    try {
      //  Sessions.
        getjTabbedPane1().setVisible(false);
        
    } catch (Exception e) {
e.printStackTrace();
    }
}
    public void addToTabpanelToUI(TabPanelUI tab, String title) {

        addToTabpanelToUI(tab, title, null);
    }

    public void addToTabPanel(TabPanelUI tab, String tabid, String tablbl) {
        try {

            TabPanelUI tpui = (TabPanelUI) Sessions.getObj(tabid);

            if (tpui == null) {
                tpui = tab;
                getjTabbedPane1().add(tabid, tpui);
                int sx = getjTabbedPane1().indexOfTab(tabid);
                Sessions.addToSession(tabid, tpui);
//                initCloseButtonForTab(getjTabbedPane1(), sx);
                getjTabbedPane1().setSelectedIndex(sx);
                tpui.tabName = tabid;
                getjTabbedPane1().getTabComponentAt(sx);
                JLabel lbl = new JLabel(tablbl);
                getjTabbedPane1().setTabComponentAt(sx, lbl);
            } else {
                int ix = getjTabbedPane1().indexOfTab(tabid);
                getjTabbedPane1().setSelectedIndex(ix);
//               initCloseButtonForTab(getjTabbedPane1(), ix);


//                 tpui.setobj(obj);
            }


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cstattus;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables

    JTabbedPane jTabbedPane1;
    
    public javax.swing.JTabbedPane getjTabbedPane1() {
        return jTabbedPane1;
    }
    

  
    
    

}
