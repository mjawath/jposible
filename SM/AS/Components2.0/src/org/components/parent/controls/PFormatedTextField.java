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
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Date;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.InternationalFormatter;
import javax.swing.text.NumberFormatter;
import javax.swing.text.PlainDocument;
import org.components.parent.Documents.DocumentListenerx;

/**
 *
 * @author nano
 */
public class PFormatedTextField extends javax.swing.JFormattedTextField  implements IComponent{

    protected  IContainer container;
    private String id;
    private ActionTask actionTask;
    
    
    
    public void setFormate(Object formate){
        setFormatterFactory(getMyDefaultFormatterFactory(formate));        
    
    }
    
    public void setFormate(Object formate,Object formateDisplay,Object formateEdit) {
//        setFormatterFactory(new DefaultFormatterFactory(getMyDefaultFormatterFactory(formate),getMyDefaultFormatterFactory(formateDisplay),getMyDefaultFormatterFactory(formateEdit));
    }
    

    
    /**
     * Returns an AbstractFormatterFactory suitable for the passed in Object
     * type.
     */
    private AbstractFormatterFactory getMyDefaultFormatterFactory(Object type) {
        if (type instanceof DateFormat) {
            return new DefaultFormatterFactory(new DateFormatter((DateFormat) type));
        }
        if (type instanceof NumberFormat) {
            return new DefaultFormatterFactory(new NumberFormatter(
                    (NumberFormat) type));
        }
        if (type instanceof Format) {
            return new DefaultFormatterFactory(new InternationalFormatter(
                    (Format) type));
        }
        if (type instanceof Date) {
            return new DefaultFormatterFactory(new DateFormatter());
        }
        if (type instanceof Number) {
            AbstractFormatter displayFormatter = new NumberFormatter();
            ((NumberFormatter) displayFormatter).setValueClass(type.getClass());
            AbstractFormatter editFormatter = new NumberFormatter(
                    new DecimalFormat("#.#"));
            ((NumberFormatter) editFormatter).setValueClass(type.getClass());

            return new DefaultFormatterFactory(displayFormatter,
                    displayFormatter, editFormatter);
        }
        return new DefaultFormatterFactory(new DefaultFormatter());
    }



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
                final PFormatedTextField org = (PFormatedTextField) e.getComponent();

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
    public PFormatedTextField() {
        super();
        id=SystemUtil.getKeyStr();
//        addDocumentListener(docx);

        
        setDocument(new CDocument());//this Document's  insertString is not used 
        //i think we should use document filter class to filter out user inputs
        
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
