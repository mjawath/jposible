package org.components.parent.Documents;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Toolkit;
import java.math.BigInteger;
import java.text.NumberFormat;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NumberDocument extends PlainDocument {

    Long max;
    Long min;
    boolean allowZero = true;

    @Override
    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {
        if (str == null) {
            return;
        }
        if (str.equals("l") || str.equals("L")) {
            return;
        }



        String oldString = getText(0, getLength());
        String newString = oldString.substring(0, offs) + str
                + oldString.substring(offs);


        try {
//            if ("-".equals(newString)) {
//            super.insertString(offs, str, a);
//            return;
//            }
            Long d =Long.parseLong(newString);
           
            if ( d > max) {
                return;
            }

            if ( d < min) {
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

    public NumberDocument(Long min, Long max) {
        this.max = max;
        this.min = min;
    }

    public NumberDocument(Long min, Long max, boolean zero) {
        this.max = max;
        this.min = min;
        this.allowZero = false;
    }

    public NumberDocument() {
        min=Long.MIN_VALUE;
        max=Long.MAX_VALUE;
    }
    public NumberDocument(boolean zero) {
        min=Long.MIN_VALUE;
        max=Long.MAX_VALUE;
        this.allowZero= zero;
    }

    public NumberDocument(Long min) {
        this.min = min;
    }
    public static void main(String[] args) {
            BigInteger d =new BigInteger("256311242521246521456");
           NumberFormat nf = NumberFormat.getNumberInstance();
        try {
//            Number num = nf.parse("");
            System.out.println(" num  "+d);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
