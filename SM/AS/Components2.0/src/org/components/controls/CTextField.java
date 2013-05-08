/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jtextfield.java
 *
 * Created on May 4, 2010, 7:39:59 PM
 */
package org.components.controls;

import com.components.custom.ActionTask;
import com.components.custom.CInputVerifier;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import org.components.parent.controls.PTextField;

/**
 *
 * @author nano
 */
public class CTextField extends PTextField {


    JComponent nextFocusableComponent;
    JComponent previouseFocusedComponent;
    List<ActionTask> actionTasks;
    boolean moveTonextcom = true;

    protected void processFocusEvent(FocusEvent e) {
        super.processFocusEvent(e);
        if (e.isTemporary()) {
            return;
        }

        int id = e.getID();
        switch (id) {
            case FocusEvent.FOCUS_GAINED:
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        selectAll();
                    }
                });
                break;
            case FocusEvent.FOCUS_LOST:
                final CTextField org = (CTextField) e.getComponent();

                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        org.setCaretPosition(org.getText().length());
                    }
                });
                break;
        }




    }

    /** Creates new form BeanForm */
    public CTextField() {
        initComponents();
        init();
//        setInputVerifier(new CInputVerifier());
    }

    public void init() {


        actionTasks = new ArrayList<ActionTask>();

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //in the level of listner we add only one global 
                    // event handler which is used to capture all the events
                    // programmer who wish to implement an action 
                    // may have the freedom to use the super actions
                    //or he can just skip it by not calling it
                    //he can use call his method implementations and then call tthe super
                    // call the super then implement
                    // or dont call supp-override
                    // but ?? enable to implement such df????????
                    //?????????????????todo
                    if (actionTasks != null || !actionTasks.isEmpty()) {

                        for (ActionTask actionTask : actionTasks) {
                            actionTask.action();
                        }
                    }
                    if(getContainer()!=null)
                    getContainer().gotoNextComponent(CTextField.this);
                }
            }
        });


    }

    public void addaction(int idx, ActionTask action) {
        int c= actionTasks.size();
        
        if(c-1 < idx){
        actionTasks.add(action);return;
        }
        actionTasks.add(idx, action);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        setPreferredSize(new java.awt.Dimension(6, 25));
    }// </editor-fold>//GEN-END:initComponents

    public void setActionTask(ActionTask actionTask) {
        addActionListener(actionTask);
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
//        if(e.getKeyCode()==KeyEvent.VK_ENTER){
//            postActionEvent();
//            return;
//        }
        super.processKeyEvent(e);
    }

    public void setInputVerifier(CInputVerifier inputVerifier) {
//        addActionListener(inputVerifier);
        super.setInputVerifier(inputVerifier);
    }
    
    public  double getDoubleValue0() {

        try {
            String s = null;
            if (this.getText() != null) {
                s = this.getText().trim();

            }
            return Double.parseDouble(s);
        } catch (Exception e) {
            return 0.0;
        }

    }
    
    public  Double getDoubleValue() {

        try {
            String s = null;
            if (this.getText() != null) {
                s = this.getText().trim();

            }
            return Double.parseDouble(s);
        } catch (Exception e) {
            return null;
        }

    }
    
    public void clear(){
    this.setText("");
    this.setToolTipText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
