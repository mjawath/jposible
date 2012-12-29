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
import org.components.containers.CPanel;

/**
 *
 * @author mjawath
 */
public abstract class TabPanelUI extends CPanel implements TabChildUI,CrudControl{

    /** Creates new form TabPanelUI */
    public TabPanelUI() {
        initComponents();

    }

    public void init() {

             Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {

            public void eventDispatched(AWTEvent event) {
                if (((KeyEvent) event).getKeyCode() == KeyEvent.VK_ESCAPE && TabPanelUI.this.isShowing()) {
                    System.out.println("closing");
                };
            }
        }, AWTEvent.KEY_EVENT_MASK);
        events();
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

    public  JPanel getJPanel(){return this;};

    public void updateEntityUI() {
    }

    public void release(){

        
    }

    
    public void save() {
    
    }

    
    public void delete() {
    
    }

    
    public void clear() {
    
    }

    
    public void update() {
    
    }
    
   
    public void gotoList() {
   
    }

   
    

    public void setobj(Object obj) {
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
