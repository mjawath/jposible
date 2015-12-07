package app;

import app.utils.SystemUtil;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;
import org.biz.dao.service.Service;
import org.biz.util.ReflectionUtility;
import org.components.controls.CButton;
import org.components.util.Sessions;
import org.components.windows.TabPanelUI;

/**
 *
 * @author mjawath
 */
public class AppMainWindow extends org.components.windows.MainWindow {

    public AppMainWindow() {
        super();
        initComponents();
        init();
        System.out.println("--------------------------------"); 
        Sessions.addToSession("mainui", this);
        addToSystem("SaveButton");
        addToSystem("DeleteButton");
        addToSystem("ClearButton");
        addToSystem("Button x");
        
        JButton bb = new JButton("asdfasfasdf");
        getToolbar().add(bb);
    }

    private void addToSystem(String btnT) {
        CButton btn = new CButton();
        btn.setBorder(BorderFactory.createLineBorder(Color.yellow));
        btn.setPreferredSize(new Dimension(100, 50));
        btn.setText(btnT);
        btn.setName(btnT);
        SystemUtil.addToSystemToolsToToolbar(btn);
    }
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cstattus = new org.components.controls.CLabel();
        topTabPane = new javax.swing.JTabbedPane();
        jToolBar1 = new javax.swing.JToolBar();
        cPanel1 = new org.components.containers.CPanel();
        tbtnItem = new org.components.controls.CButton();
        tbtnWarehouse = new org.components.controls.CButton();
        tbtnCategory = new org.components.controls.CButton();
        tbtnShop = new org.components.controls.CButton();
        tGRN = new org.components.controls.CButton();
        cButton6 = new org.components.controls.CButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        tmnuTrans = new javax.swing.JMenu();
        tmnuGDN = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cstattus.setText("");

        jToolBar1.setRollover(true);

        tbtnItem.setText("Item");

        tbtnWarehouse.setText("Ware House");
        tbtnWarehouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtnWarehouseActionPerformed(evt);
            }
        });

        tbtnCategory.setText("Category");

        tbtnShop.setText("Shop");

        tGRN.setText("GRN");

        cButton6.setText("GDN");

        javax.swing.GroupLayout cPanel1Layout = new javax.swing.GroupLayout(cPanel1);
        cPanel1.setLayout(cPanel1Layout);
        cPanel1Layout.setHorizontalGroup(
            cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbtnItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbtnWarehouse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbtnCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbtnShop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tGRN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        cPanel1Layout.setVerticalGroup(
            cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbtnWarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbtnItem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tbtnCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(tbtnShop, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tGRN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(273, Short.MAX_VALUE))
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        tmnuTrans.setText("Transaction");

        tmnuGDN.setText("GRN");
        tmnuTrans.add(tmnuGDN);

        jMenu2.setText("jMenu2");
        tmnuTrans.add(jMenu2);

        jMenuItem1.setText("jMenuItem1");
        tmnuTrans.add(jMenuItem1);

        jMenuBar1.add(tmnuTrans);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cstattus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(topTabPane)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(topTabPane, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cstattus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbtnWarehouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnWarehouseActionPerformed

   
        
    }//GEN-LAST:event_tbtnWarehouseActionPerformed
    
    public JToolBar getToolbar(){
    return jToolBar1;
    }
    
    public JMenuBar getJMenuBar(){
    return jMenuBar1;
    }
    
    public javax.swing.JTabbedPane getjTabbedPane1() {
        return topTabPane;
    }
    public void setjTabbedPane1(JTabbedPane jTabbedPane1) {
        this.topTabPane = jTabbedPane1;
    }
    
    public  void addMenu(final String title,final Class panel,final Class serviceClass){
      JMenuItem mi=  new JMenuItem(title);
      mi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
           addX(panel,serviceClass, title);
            }
        });
      mi.setName(title);
      jMenu1.add(mi); 
    }
    
    public static void addX(final Class panel, final Class serclasss, final String tt) {
        final TabPanelUI obj2 = (TabPanelUI) ReflectionUtility.getDynamicInstance(panel);
        obj2.config();
        SwingWorker nt = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                Service service = (Service) ReflectionUtility.getDynamicInstance(serclasss);
                return service;
            }

            @Override
            protected void done() {
                Service ser = null;
                try {
                    ser = (Service) get();
//                    obj2.config();
                     obj2.setService(ser);

                    //add to main frame
                    JFrame frame = new JFrame(tt);
                    obj2.setTabName(tt);
                    frame.getContentPane().add(obj2);
                    frame.setSize(900, 700);
                    frame.setVisible(true);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        };
        nt.execute();
        Sessions.addToSession(tt, obj2);

    }
    
    public void callMenu(String title) {
        for (Component com : jMenu1.getMenuComponents()) {
            if (com instanceof JMenuItem) {
                JMenuItem mi = (JMenuItem) com;
                if (title.equals(mi.getName())) {
                    mi.doClick();
                    return;
                }
            }
        }

    }
 
   
      public void addMenu(JMenu menu){
            jMenuBar1.add(menu);
    }
    
    public static void setMenuItemToMenu(JMenu menu,JMenuItem mi){
        menu.add(mi);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton6;
    private org.components.containers.CPanel cPanel1;
    private org.components.controls.CLabel cstattus;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JToolBar jToolBar1;
    private org.components.controls.CButton tGRN;
    private org.components.controls.CButton tbtnCategory;
    private org.components.controls.CButton tbtnItem;
    private org.components.controls.CButton tbtnShop;
    private org.components.controls.CButton tbtnWarehouse;
    private javax.swing.JMenuItem tmnuGDN;
    private javax.swing.JMenu tmnuTrans;
    private javax.swing.JTabbedPane topTabPane;
    // End of variables declaration//GEN-END:variables
}
