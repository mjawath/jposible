package org.components.windows;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TabPanelUI.java
 *
 * Created on Nov 22, 2010, 1:58:08 PM
 */
import com.components.custom.CrudControl;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.biz.dao.service.Service;
import org.components.containers.CPanel;
import org.components.util.Sessions;

/**
 *
 * @author mjawath
 */
public abstract class TabPanelUI extends CPanel implements TabChildUI,CrudControl{

    protected String tabName;      
    protected Service service;
        
    /** Creates new form TabPanelUI */
    public TabPanelUI() {
        initComponents();
//        config();
    }
    
    public void config() {

        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            public void eventDispatched(AWTEvent event) {
                if (((KeyEvent) event).getKeyCode() == KeyEvent.VK_ESCAPE && TabPanelUI.this.isShowing()) {
                    System.out.println("closing");
                };
            }
        }, AWTEvent.KEY_EVENT_MASK);
        init();
        events();
    }

    public void init() {
    }

    public void events() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents

    public  String getTabName(){
    return this.toString();
    }
    
    public void setTabName(String tabname){
    this.tabName=tabname;
    }

    public  JPanel getJPanel(){return this;};

    public void updateEntityUI() {
    }

    public void release(){

        
    }

    
    public void save() {
        
        
    }
    
    
    public Object saveX() {
        
        return null;
    }
    
    //save should be encapsulated by a method
    // objects should be persistable ,updatable, deletable
    //also should add audit info
    //
    public void save(Object [] persited,Object [] updatated,Object [] deleted){
    
    }

    
    public void delete() {
    
    }

    
    public void clear() {
    
    }

    
    public void update() {
    
    }
    
   
    public void gotoList() {
   
    }

    @Override
    public void printPage() {
        
    }
    
    public void onSaveComplete(Object objs){
    
    }

    public void postUpdate(Object objs){
    }

    public void onDeleteComplete(Object objs){
    }


   
    public void setobj(Object obj) {
    }
    
    public static void addTabPane(JTabbedPane tab, TabPanelUI panelUI,String tabName ){
    try {
            TabPanelUI tpui = (TabPanelUI) Sessions.getObj(tabName);
            int ix = tab.indexOfTab(tabName);
            if (tpui == null) {
//                tpui = (TabPanelUI) Class.forName(cls.getName()).newInstance();
                tab.add(tabName, tpui);
                int sx = tab.indexOfTab(tabName);
                tab.setSelectedIndex(sx);
                Sessions.addToSession(tabName, tpui);

            } else {
                tab.setSelectedIndex(ix);
//                tpui.setobj(obj);
            }
        
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void setService(Service service){
        this.service=service;
    }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public Service getService() {
        return service;
    }
}
