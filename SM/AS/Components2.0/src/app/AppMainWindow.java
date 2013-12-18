package app;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;
import org.biz.dao.service.Service;
import org.biz.util.ReflectionUtility;
import org.components.util.Sessions;
import org.components.windows.DetailPanel;
import org.components.windows.TabPanelUI;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainWindow.java
 *
 * Created on Dec 13, 2010, 10:16:22 PM
 */

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

    public void inii2(){}

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cButton1 = new org.components.controls.CButton();
        cstattus = new org.components.controls.CLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cButton1.setText("LogOut");
        cButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton1ActionPerformed(evt);
            }
        });

        cstattus.setText("");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(447, 447, 447)
                .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cstattus, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cstattus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton1ActionPerformed

//        showlogin();
    }//GEN-LAST:event_cButton1ActionPerformed

     
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
    private org.components.controls.CButton cButton1;
    private org.components.controls.CLabel cstattus;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
