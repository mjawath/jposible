package app;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;
import org.biz.dao.service.Service;
import org.biz.util.ReflectionUtility;
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
    }
    String propfile;

 

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cstattus = new org.components.controls.CLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jToolBar1 = new javax.swing.JToolBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cstattus.setText("");

        jToolBar1.setRollover(true);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(cstattus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cstattus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public JToolBar getToolbar(){
    return jToolBar1;
    }
    
    public javax.swing.JTabbedPane getjTabbedPane1() {
        return jTabbedPane1;
    }
    public void setjTabbedPane1(JTabbedPane jTabbedPane1) {
        this.jTabbedPane1 = jTabbedPane1;
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
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cstattus;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
