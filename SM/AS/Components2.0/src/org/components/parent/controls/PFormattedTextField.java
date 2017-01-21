/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.controls;

import com.components.custom.ActionTask;
import com.components.custom.IComponent;
import com.components.custom.IContainer;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author jawa
 */
public class PFormattedTextField extends JFormattedTextField implements IComponent{

    
    private ActionTask actionTask;
    private ActionListener docAction;
    private ActionTask docActionTask;
    boolean disableDocumentChangeEvent = false;
    boolean isAlwaysFireEventOnEnter = true;
    
    protected IContainer container;
    /**
     * Creates new form PFormattedTextField
     */
    public PFormattedTextField() {
       
        initComponents();
        

        
        addFocusListener(new FocusAdapter() {

//            String value;

            @Override
            public void focusLost(FocusEvent e) {
//                if(isAlwaysFireEventOnEnter){
//                    if (actionTask != null) {
//                        Object obj = actionTask.actionFired(e);
//                        if (obj instanceof Component) {
//                            ((Component) obj).requestFocus();
//                        }
//                    }
//                    return;
//                }
//                else
//                if (!StringUtility.isSameString(value, getText())) {
//                    if (actionTask != null) {
//                        Object obj = actionTask.actionFired(e);
//                        if (obj instanceof Component) {
//                            ((Component) obj).requestFocus();
//                        }
//                    }
//                }
            }

            @Override
            public void focusGained(FocusEvent e) {
//                value = getValue()==null?"":getValue().toString();
            }

        });
                
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (isAlwaysFireEventOnEnter) {
                        if (actionTask != null) {
                            Object obj = actionTask.actionFired(e);
                            if (obj instanceof Component) {
                                ((Component) obj).requestFocus();
                            } else {
                            transferFocus();
                        }
                        }
                        return;
                    }
                }
            }

        });
        
//        NumberFormatter formatter= (NumberFormatter) getFormatter();
//        formatter.setAllowsInvalid(false);
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
                final JFormattedTextField org = (JFormattedTextField) e.getComponent();

                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        org.setCaretPosition(org.getText().length());
                    }
                });
                break;
        }

    }

        public ActionTask getActionTask() {
        return actionTask;
    }

    public void setActionTask(ActionTask actionTask) {
        this.actionTask = actionTask;
    }
    public void setDocAction(ActionListener action) {

        docAction = action;
    }

    public void setDocAction(ActionTask action) {

        docActionTask = action;
    }
    
    public void clear(){
        setText("");
    }
    
    
   /* private class CDocument extends PlainDocument {

        @Override
        protected void removeUpdate(AbstractDocument.DefaultDocumentEvent chng) {
            super.removeUpdate(chng);          
            fireEventIfNotFromDoc();
        }

        @Override
        protected void insertUpdate(AbstractDocument.DefaultDocumentEvent chng, AttributeSet attr) {
            super.insertUpdate(chng, attr);
            fireEventIfNotFromDoc();

        }

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            super.insertString(offs, str, a);
            fireEventIfNotFromDoc();
        }

      
}*/
    protected void fireEventIfNotFromDoc() {
        if (!disableDocumentChangeEvent && docActionTask != null) {
            disableDocumentChangeEvent = true;
            docActionTask.actionFired(this);
            disableDocumentChangeEvent = false;
        }
    }
    
    public void setToCurrencyField(){
        System.out.println("setting currency value");
        setHorizontalAlignment(RIGHT);
        
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(0);
        final DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        decimalFormat.setMinimumFractionDigits(0);

//        NumberFormatter formatter = new NumberFormatter(format);
//        formatter.setMinimum(5.0);
//        formatter.setMaximum(10000000.0);
//        formatter.setAllowsInvalid(false);
//        formatter.setOverwriteMode(true);
        
//        setFormatter(formatter);
//        
        
        NumberFormatter numberFormatter = new NumberFormatter(decimalFormat);
        numberFormatter.setAllowsInvalid(false);        
//        numberFormatter.setOverwriteMode(true);
//        setFormatterFactory(new DefaultFormatterFactory(numberFormatter));
        getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
//                System.out.println(" docu "+e);
                fireEventIfNotFromDoc();

            }

            public void removeUpdate(DocumentEvent e) {
//           System.out.println(" docu "+e);
                fireEventIfNotFromDoc();

            }

            public void changedUpdate(DocumentEvent e) {
//           System.out.println(" docu "+e);
                fireEventIfNotFromDoc();

            }
        });
//        new ParseAl
        
}
       private NumberFormat percentFormat; 
        //Create and set up number formats. These objects also
    //parse numbers input by user.
    private void setUpFormats() {
//        amountFormat = NumberFormat.getNumberInstance();

        percentFormat = NumberFormat.getNumberInstance();
        percentFormat.setMinimumFractionDigits(0);
        

//        paymentFormat = NumberFormat.getCurrencyInstance();
    }
    
    
    public void setText(String txt){
        disableDocumentChangeEvent=true;
        super.setText(txt);
        disableDocumentChangeEvent = false;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00;(¤#,##0.00)"))));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void setAlwaysEnterEvent(boolean  isAlways){
        isAlwaysFireEventOnEnter =isAlways;  
    }
    @Override
    public void setContainer(IContainer con) {
        this.container = con;
    }

    public IContainer getContainer() {
        return container;
    }
    
    /**
     * this will set fire document even change listener
     *
     * @param val
     */
    public void setValueIfNotFocused(Object val) {
        if (hasFocus()) {
            return;
        }
        disableDocumentChangeEvent = true;
        setText(val == null ? "" : val.toString());
        disableDocumentChangeEvent = false;
    }
    
    public Double getDoubleValue() {

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

    
    public boolean isValidPositiveDoubleValue() {
        return (getDoubleValue()!=null && getDoubleValue()> 0);
    }
}
