/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * orgbutton.java
 *
 * Created on May 11, 2010, 9:51:09 PM
 */

package org.components.parent.controls;

import app.utils.SystemUtil;
import com.components.custom.IComponent;
import com.components.custom.IContainer;
import javax.swing.JButton;

/**
 *
 * @author nano
 */
public class PButton extends JButton  implements IComponent{

    private String id;
    protected  IContainer container;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    /** Creates new form BeanForm */
    public PButton() {
        initComponents();
        id=SystemUtil.getKeyStr();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setText("Button");
        setFocusable(false);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void setContainer(IContainer con) {
    this.container=con;
    }

    @Override
    public IContainer getContainer() {
        return container;
    }

}
