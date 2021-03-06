/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.controls;

import com.components.custom.ActionTask;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import org.components.parent.Documents.DoubleDocument;

/**
 *
 * @author jawa
 */
public class CCurrencyField extends CFormattedTextField {

    /**
     * Creates new form CCurrencyField
     */
    public CCurrencyField() {
        super();
//        DecimalFormat decimalFormat = new java.text.DecimalFormat("#,##0");
//        
//        NumberFormatter numberFormatter = new javax.swing.text.NumberFormatter(decimalFormat);
//        numberFormatter.setAllowsInvalid(false);
//        DefaultFormatter defaultFormatter = new DefaultFormatter();  
//        defaultFormatter.setAllowsInvalid(false);
////        initComponents();
//        final DefaultFormatterFactory defaultFormatterFactory = new javax.swing.text.DefaultFormatterFactory(defaultFormatter,numberFormatter,numberFormatter,defaultFormatter);
        
        
//        defaultFormatterFactory.setNullFormatter(defaultFormatter);
//        final DefaultFormatterFactory defaultFormatterFactory = new DefaultFormatterFactory(new NumberFormatter());
//        final DefaultFormatter df = (DefaultFormatter)defaultFormatterFactory.getDefaultFormatter();
//        df.setCommitsOnValidEdit(true);
////        df.setAllowsInvalid(false);
//        setFormatterFactory(defaultFormatterFactory);
//        df.setOverwriteMode(false);
//        ((PlainDocument)getDocument()).setDocumentFilter(new DocumentFilter(){
//
//            @Override
//            public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
//                System.out.println(string);                
//                super.insertString(fb, offset, string, attr); //To change body of generated methods, choose Tools | Templates.
//            }
////        
//        });
        
        setDocumentFiltering();
        
//    setFormatterFactory(defaultFormatterFactory);
    
                
//        setDocumentFiltering();
        setHorizontalAlignment(SwingConstants.RIGHT);
        
    }

//    public void clear(){
//       super.clear();
//        setText("0.00");
//       
//    }
    
    Double max;
    Double min;
    boolean allowZero = true;

    
    
    private void setDocumentFiltering(){
//        ((PlainDocument)getDocument()).setDocumentFilter(new NumericAndLengthFilter(Integer.MAX_VALUE));
       
        setDocument(new DoubleDocument());
    }

   
    
    private class NumericAndLengthFilter extends DocumentFilter {

        /**
         * Number of characters allowed.
         */
        private int length = 0;

        /**
         * Restricts the number of charcacters can be entered by given length.
         *
         * @param length Number of characters allowed.
         */
        public NumericAndLengthFilter(int length) {
            this.length = length;
        }

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (isNumeric(fb.getDocument().getText(0, fb.getDocument().getLength()) + string)) {
                super.insertString(fb, offset, string, attr);
                fireEventIfNotFromDoc();
            }
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text,
                AttributeSet attrs) throws
                BadLocationException {

            if (isNumeric(fb.getDocument().getText(0, fb.getDocument().getLength()) + text)) {
                super.replace(fb, offset, length, text, attrs);
                fireEventIfNotFromDoc();
            }
        }

        @Override
        public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
            if (isNumeric(fb.getDocument().getText(0, fb.getDocument().getLength()) )) {
                super.remove(fb, offset, length); 
                fireEventIfNotFromDoc();
            }
        }

        private boolean isNumeric(String text) {
            if (text == null || text.trim().equals("")) {
                return false;
            }
            try {
                Double d = Double.parseDouble(text);
                return true;
            } catch (NumberFormatException e) {
//                Toolkit.getDefaultToolkit().beep();
                //here i should comenting it becas on sound
            }
            return false;

        }

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents

    protected void fireEventIfNotFromDoc() {
        if (!disableDocumentChangeEvent && docActionTask != null) {
            disableDocumentChangeEvent = true;
            docActionTask.actionFired(this);
            disableDocumentChangeEvent = false;
        }
    }
        boolean disableDocumentChangeEvent = false;
    boolean isAlwaysFireEventOnEnter = true;
        private ActionTask docActionTask;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
