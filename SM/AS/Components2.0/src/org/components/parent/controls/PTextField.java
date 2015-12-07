/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jtextfield.java
 *
 * Created on May 4, 2010, 7:39:59 PM
 */
package org.components.parent.controls;

import app.utils.SystemUtil;
import com.components.custom.ActionTask;
import com.components.custom.IComponent;
import com.components.custom.IContainer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import org.components.parent.Documents.DocumentListenerx;

/**
 *
 * @author nano
 */
public class PTextField extends javax.swing.JTextField implements IComponent{

    protected  IContainer container;
    private String id;
    private ActionTask actionTask;

    JComponent nextFocusableComponent;
    JComponent previouseFocusedComponent;
    List<ActionTask> actionTasks;
    
    
    boolean moveTonextcom = true;

    
    
    
    public ActionTask getActionTask() {
        return actionTask;
    }

    public void setActionTask(ActionTask actionTask) {
        this.actionTask = actionTask;
    }
    
   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
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
                final PTextField org = (PTextField) e.getComponent();

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
    public PTextField() {
        super();
        initComponents();
        init();
        id=SystemUtil.getKeyStr();
//        addDocumentListener(docx);        
        
        setDocument(new CDocument());
        
    }
    
    private class CDocument extends PlainDocument{

        @Override
        protected void removeUpdate(DefaultDocumentEvent chng) {
            super.removeUpdate(chng); 
            fireEventIfNotFromDoc();
        }

        @Override
        protected void insertUpdate(DefaultDocumentEvent chng, AttributeSet attr) {
            super.insertUpdate(chng, attr);
           
            fireEventIfNotFromDoc();
            
        }

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            super.insertString(offs, str, a); 
            fireEventIfNotFromDoc();
        }
        
        
    public void fireEventIfNotFromDoc(){
        if(!disableDocumentChangeEvent && docActionTask!=null ){
            docActionTask.actionFired(this);
        }
    }
    }
    
    
    public void init() {

        actionTasks = new ArrayList<ActionTask>();
//
//        addKeyListener(new KeyAdapter() {
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
////                    //in the level of listner we add only one global 
////                    // event handler which is used to capture all the events
////                    // programmer who wish to implement an action 
////                    // may have the freedom to use the super actions
////                    //or he can just skip it by not calling it
////                    //he can use call his method implementations and then call tthe super
////                    // call the super then implement
////                    // or dont call supp-override
////                    // but ?? enable to implement such df????????
////                    //?????????????????todo
//                    if (actionTasks != null || !actionTasks.isEmpty()) {
//
//                        for (ActionTask actionTask : actionTasks) {
//                            actionTask.actionCall(e);
//                        }
//                    }
//                    if (getContainer() != null) {
//                        container.gotoNextComponent(PTextField.this);
//                    }
//                }
//            }
//        });

    }
    
    public void addaction(int idx, ActionTask action) {
        int c = actionTasks.size();

        if (c - 1 < idx) {
            actionTasks.add(action);
            return;
        }
        actionTasks.add(idx, action);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setName("Form"); // NOI18N
    }// </editor-fold>//GEN-END:initComponents
    private String formater;

    /**
     * Get the value of formater
     *
     * @return the value of formater
     */
    public String getFormater() {
        return formater;
    }

    /**
     * Set the value of formater
     *
     * @param formater new value of formater
     */
    public void setFormater(String formater) {
        this.formater = formater;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void setContainer(IContainer con) {
        this.container =con;
    }

    public IContainer getContainer() {
        return container;
    }
    
    public void addDocumentListener(DocumentListenerx docx){
        getDocument().addDocumentListener(docx);
    }
    
    DocumentListenerx docx = new DocumentListenerx() {
        @Override
        public void action(DocumentEvent e) {
            if(docAction!=null && !disableDocumentChangeEvent){
            ActionEvent actionEvent=new ActionEvent(e,21,"docaction");            
            docAction.actionPerformed(actionEvent);
            }else if(docActionTask != null && !disableDocumentChangeEvent) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                    }
                });
                

            }
        }
    };
    private ActionListener docAction;
    private ActionTask docActionTask;
    
    public void setDocAction(ActionListener action){
        
        docAction =action;
    }
    
    public void setDocAction(ActionTask action) {

        docActionTask = action;
    }
     
    public void setEnterAction(){
    }
    
    ActionListener keyAct=new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
       
        }
    };

    boolean disableDocumentChangeEvent=false;
    
    /**
     * this will set fire document even change listener
     *
     * @param val
     */
    public void setValue(Object val) {
        disableDocumentChangeEvent = true;
        setText(val == null ? "" : val.toString());
        disableDocumentChangeEvent =false;
    }
    
    /**
     * this will set fire document even change listener
     *
     * @param val
     */
    public void setValueIfNotFocused(Object val) {
        if(hasFocus())return;
        disableDocumentChangeEvent = true;
        setText(val == null ? "" : val.toString());
        disableDocumentChangeEvent = false;
    }

    
    public void setValue(String val) {
        disableDocumentChangeEvent = true;
        setText(val == null ? "" : val);
        disableDocumentChangeEvent = false;
    }
    
    public String getValue(){
     String x = getText();
        if (x.isEmpty()) {
            return null;
        } else {
            return x;
        }
    }
}
