/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CComboBox.java
 *
 * Created on May 6, 2010, 6:56:55 PM
 */
package org.components.controls;

import com.components.custom.ActionTask;
import com.components.custom.IComponent;
import com.components.custom.IContainer;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import org.components.windows.DetailPanel;

/**
 *
 * @author nano
 */
public class CComboBox<E> extends JComboBox implements IComponent{


    protected  IContainer container;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    List<ActionTask> actionTasks;
    boolean moveTonextcom = true;

    /**
     * Creates new form BeanForm
     */
    public CComboBox() {
        initComponents();
//          addKeyListener(new KeyAdapter() {
//            public void keyPressed(KeyEvent e) {
//                if(e.getKeyCode()==KeyEvent.VK_ENTER){
//
//                   ActionEvent xx=new ActionEvent(this,ActionEvent.ACTION_FIRST,"");
//                    fireActionEvent();
//                    CComboBox.this.hidePopup();
//                    // just change the focus 
//                }
//            }        
//        }); 

//          addActionListener(new AbstractAction() {
//
//            public void actionPerformed(ActionEvent e) {
//              
////                fireActionEvent();
//            }
//        });

//        getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
//
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                    fireActionEvent();
//                    // just change the focus 
//                }
//            }
//        });
        init();

    }

    public void init() {
        

        actionTasks = new ArrayList<ActionTask>();

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (actionTasks != null || !actionTasks.isEmpty()) {

                        for (ActionTask actionTask : actionTasks) {
                            actionTask.action();
                        }
                    }
                    // just change the focus
                    IContainer cont = getContainer();
                    if (cont != null && cont instanceof DetailPanel) {
                        DetailPanel dp=(DetailPanel)cont;
                        dp.gotoNextComponent();
                    }
//                    getContainer().gotoNextComponent(CComboBox.this);
                        
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    IContainer cont = getContainer();
                    if (cont != null && cont instanceof DetailPanel) {
                        DetailPanel dp = (DetailPanel) cont;
                        dp.gotoPreviousComponent();
                    }
                }
            }
        });
        

        getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (actionTasks != null || !actionTasks.isEmpty()) {

                        for (ActionTask actionTask : actionTasks) {
                            actionTask.action();
                        }
                    }
                    if(getContainer()!=null)
                    getContainer().gotoNextComponent(CComboBox.this);
                    // just change the focus 
                }
            }
        });
    }

    protected void processFocusEvent(FocusEvent e) {

        super.processFocusEvent(e);
        int id = e.getID();
        switch (id) {
            case FocusEvent.FOCUS_GAINED:
//                showPopup();
                break;
            case FocusEvent.FOCUS_LOST:
                hidePopup();
                break;
        }

    }

    public void setModel(List model) {
        if (model != null) {
            this.setModel(new DefaultComboBoxModel(model.toArray()));
        } else {
            this. removeAllItems();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    

    @Override
    public void setContainer(IContainer con) {
        this.container =con;
    }

    public IContainer getContainer() {
        return container;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
