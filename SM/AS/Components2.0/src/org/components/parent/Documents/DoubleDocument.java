package org.components.parent.Documents;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class DoubleDocument extends PlainDocument {

    Double max;
    Double min;
    boolean allowZero = true;

    @Override
    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {
        if (str == null) {
            return;
        }
        if (str.equals("d") || str.equals("D")) {
            return;
        }



        String oldString = getText(0, getLength());
        String newString = oldString.substring(0, offs) + str
                + oldString.substring(offs);


        try {
            Double d = Double.parseDouble(newString);
            if (max != null && d >= max) {
                return;
            }

            if (min != null && d <= min) {
                return;
            }

            if (!allowZero && d == 0) {
                return;
            }

            super.insertString(offs, str, a);
        } catch (NumberFormatException e) {
            Toolkit.getDefaultToolkit().beep();
        }


    }

    public DoubleDocument(Double min, Double max) {
        this.max = max;
        this.min = min;
    }

    public DoubleDocument(Double min, Double max, boolean zero) {
        this.max = max;
        this.min = min;
        this.allowZero = false;
    }

    public DoubleDocument() {
    }

    public DoubleDocument(Double min) {
        this.min = min;
    }
    
    
}
