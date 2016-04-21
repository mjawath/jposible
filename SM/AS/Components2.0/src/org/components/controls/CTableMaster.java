/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Table.java
 *
 * Created on May 6, 2010, 10:48:54 AM
 */
package org.components.controls;

import com.components.custom.IComponent;
import com.components.custom.IContainer;
import org.components.parent.controls.PxTable;

/**
 *
 * @author nano
 */
public class CTableMaster extends PxTable implements IComponent{

   protected  IContainer container;
    
    
    /** Creates new form BeanForm */
    public CTableMaster() {
        initComponents();
        this.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        this.setSurrendersFocusOnKeystroke(true);
        // i am disabling the default behaviour of table editing
        // so we can imlplement our own way of navigation         this.registerKeyboardAction(al, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
//        this.registerKeyboardAction(al, KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        this.setDefaultRenderer(String.class, new CustomRenderer());
        this.setDefaultRenderer(Double.class, new CustomRenderer());
        this.setDefaultRenderer(Object.class, new CustomRenderer());
//        action=new TableSelectionAction(this, new HashMap<Integer, TableSelectionAction>());
    }


    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 250, 215));
        setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "col 1", "col 2", "col 3", "col 4"
            }
        ));
        
        setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        setIntercellSpacing(new java.awt.Dimension(10, 5));
        setRowHeight(30);
        setSurrendersFocusOnKeystroke(true);
        getTableHeader().setReorderingAllowed(false);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

   @Override
    public void setContainer(IContainer con) {
        this.container =con;
    }

    public IContainer getContainer() {
        return container;
    }

 
    
    
    
}
