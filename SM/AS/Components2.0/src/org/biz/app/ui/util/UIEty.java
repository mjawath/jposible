/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.util;

import com.components.custom.TextFieldWithPopUP;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;

/**
 *
 * @author nano
 */

public class UIEty {

    public static void setcombomodel(String[] str, JComboBox box) {
        box.setModel(new DefaultComboBoxModel(str));
    }

    public static void cmbModelWithoutNull(String[] str, JComboBox box) {

        ArrayList<String> vec=new ArrayList<String>();
        for (String st : str) {
            if(st!=null){
            vec.add(st);
            }
        }
        box.setModel(new DefaultComboBoxModel(vec.toArray()));
    }

    public static void loadcombo(JComboBox com, Set lst) {
        DefaultComboBoxModel dcbm = (DefaultComboBoxModel) com.getModel();
        if (dcbm.getSize() != 0) {
            dcbm.removeAllElements();
        }
        Iterator it = lst.iterator();
        ((DefaultComboBoxModel) com.getModel()).addElement("");

        while (it.hasNext()) {
            Object object = it.next();

            if ((object != null) && (!object.toString().isEmpty())) {
                ((DefaultComboBoxModel) com.getModel()).addElement(object);
            }
        }
    }

    public static String doubleToUI(Number d) {
        if (d == null) {
            return "";
        }
        return d.toString();

    }

    public static String objToUI(Object d) {
        if (d == null) {
            return "";
        }
        return d.toString();

    }

    public static Double uiToDouble(String dbl) {

        try {
            String s = null;
            if (dbl != null) {
                s = dbl.trim();
            }
            return Double.parseDouble(s);
        } catch (Exception e) {
            return null;
        }

    }

    public static Double uiToDouble(Object dbl) {

        try {
            String s = null;
            if (dbl != null) {
                s = dbl.toString().trim();
            }
            return Double.parseDouble(s);
        } catch (Exception e) {
            return null;
        }

    }

    public static Long uiToLong(Object dbl) {

        try {
            String s = null;
            if (dbl != null) {
                s = dbl.toString().trim();
            }
            return Long.parseLong(s);
        } catch (Exception e) {
            return null;
        }

    }

    public static Long uiToLong0(Object dbl) {

        try {
            String s = null;
            if (dbl != null) {
                s = dbl.toString().trim();
            }
            return Long.parseLong(s);
        } catch (Exception e) {
            return 0l;
        }

    }

    public static double uiToDbl0(Object dbl) {

        try {
            String s = null;
            if (dbl != null) {
                s = dbl.toString().trim();
            }
            return Double.parseDouble(s);
        } catch (Exception e) {
            return 0;
        }

    }

    public static Double tcToDouble(JTextComponent txtcom) {

        try {
            String s = null;
            if (txtcom.getText() != null) {
                s = txtcom.getText().trim();

            }
            return Double.parseDouble(s);
        } catch (Exception e) {
            return null;
        }

    }

    public static double tcToDble0(JTextComponent txtcom) {

        try {
            String s = null;
            if (txtcom.getText() != null) {
                s = txtcom.getText().trim();

            }
            return Double.parseDouble(s);
        } catch (Exception e) {
            return 0.0;
        }

    }

    public static long tcToLong0(JTextComponent txtcom) {

        try {
            String s = null;
            if (txtcom.getText() != null) {
                s = txtcom.getText().trim();

            }
            return Long.parseLong(s);
        } catch (Exception e) {
            return 0l;
        }

    }

    public static Long tcToLong(JTextComponent txtcom) {

        try {
            String s = null;
            if (txtcom.getText() != null) {
                s = txtcom.getText().trim();

            }
            return Long.parseLong(s);
        } catch (Exception e) {
            return null;
        }

    }

    public static Integer tcToInt(JTextComponent txtcom) {

        try {
            String s = null;
            if (txtcom.getText() != null) {
                s = txtcom.getText().trim();
            }
            return Integer.parseInt(s);
        } catch (Exception e) {
            return null;
        }

    }

    public static String uiToString(Object object) {
        if (object == null) {
            return null;
        } else {
            return object.toString().trim();
        }
    }

    public static String etyToStr(Object object) {
        if (object == null) {
            return "";
        } else {
            return object.toString().trim();
        }
    }

    public static String tcToStrE(JTextComponent txtcom) {
        if (txtcom.getText() == null) {
            return "";
        } else {
            return txtcom.getText().trim();
        }
    }

    public static String tcToStr(JTextComponent txtcom) {
        String x = txtcom.getText().trim();
        if (x.isEmpty()) {
            return null;
        } else {
            return x;
        }
    }
//selected rows columns value
    public static String colToStrE(JTable txtcom, int col) {
        String x = TableUtil.getSelectedValueE(txtcom, col);
        if (x.isEmpty()) {
            return null;
        } else {
            return x;
        }
    }

    // specified rows cols value
    public static String colToStrE(JTable txtcom,int row ,int col) {
        Object x = TableUtil.getValueat(txtcom, row,col);
        if (x==null) {
            return "";
        } else {
            return x.toString();
        }
    }

    // specified rows cols value
    public static String colToStr(JTable txtcom,int row ,int col) {
        Object x = TableUtil.getValueat(txtcom, row,col);
        if (x==null) {
            return null;
        } else {
            return x.toString();
        }
    }

    public static String colToStr(JTable txtcom, int col) {
        Object x = TableUtil.getSelectedValue(txtcom, col);
        if (x == null) {
            return null;
        } else {
            return x.toString();
        }
    }

    public static Double colToDbl(JTable txtcom,int row, int col) {
        Object x = TableUtil.getValueat(txtcom, row, col);
        if (x != null) {
            String bb = x.toString();
            try {
                return Double.parseDouble(bb);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public static Double colToDbl(JTable txtcom, int col) {
        Object x = TableUtil.getSelectedValue(txtcom, col);
        if (x != null) {
            String bb = x.toString();
            try {
                return Double.parseDouble(bb);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public static Double colToDbl0(JTable txtcom,int row, int col) {
        Object x = TableUtil.getModelsValueAt(txtcom, row ,col);
        if (x != null) {
            String bb = x.toString();
            try {
                return Double.parseDouble(bb);
            } catch (Exception e) {
                return 0d;
            }
        }
        return 0d;
    }
    public static Double colToDbl0(JTable txtcom, int col) {
        Object x = TableUtil.getSelectedValue(txtcom, col);
        if (x != null) {
            String bb = x.toString();
            try {
                return Double.parseDouble(bb);
            } catch (Exception e) {
                return 0d;
            }
        }
        return 0d;
    }

    public static String cmbtostr(JComboBox jcb) {
        Object obj = null;
        if (jcb.isEditable()) {
            obj = jcb.getEditor().getItem();
        } else {
            obj = jcb.getSelectedItem();
        }
        if (obj != null) {
            return obj.toString();
        } else {
            return null;
        }

    }

    public static String dpToDate(Date date) {
        Object obj = date;
        if (obj != null) {
            return obj.toString();
        } else {
            return null;
        }

    }

    public static String getPlainDouble(Double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).toPlainString();
    }

    public static String getPlainDouble(String value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).toPlainString();
    }

    public static String objTodate(Object d) {
        if (d == null) {
            return "";
        }
        return new SimpleDateFormat("dd/MMM/yyyy").format(d);

    }

    public static String objTodate(String form, Object d) {
        if (d == null) {
            return "";
        }
        try {
            return new SimpleDateFormat(form).format(d);

        } catch (Exception e) {
        }
        return "";
    }

    public static void objToUi(JTextComponent jtc, Object val) {
        if (val != null) {
            jtc.setText(val.toString());
        } else {
            jtc.setText("");
        }

    }
    
    public static void objToUi(TextFieldWithPopUP jtc, Object val) {
        if (val != null) {
            jtc.setSelectedObject(val);
        } else {
            jtc.clear();
        }

    }

    public static void objToUi(JLabel jtc, Object val) {
        if (val != null) {
            jtc.setText(val.toString());
        } else {
            jtc.setText("");
        }
    }
    
    public static void objToUi(JComboBox jtc, Object val) {
        if (val != null) {
            jtc.setSelectedItem(val.toString());
        } else {
        }
    }

    public static void objToUi(JCheckBox jtc, Boolean val) {

        jtc.setSelected(val == null ? false : val);

    }

    public static String now() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        return sdf.format(date);

    }

    public static String nowTimesStamp() {
        Date date = new Date();

        //      return Long.toString(date.getTime()).substring(5, 13);
        return Long.toString(date.getTime());

    }

    public static Date nowDate() {
        Date date = new Date();
        return date;

    }

    public static String nowTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return sdf.format(date);

    }

    public static void main(String[] args) {
        System.out.println(nowTimesStamp());
    }

    public static  Double getmultyply(Double d1,Double d2 ){
        BigDecimal b1=new BigDecimal(d1==null?0:d1);
        BigDecimal b2=new BigDecimal(d1==null?0:d2);
        return b1.multiply(b2).doubleValue();
    }


    //ui validation
    public static boolean isTxtFieldNullOrEmpty(JTextComponent txt){
        return Validator.isEmptyOrNull(txt.getText());
    }

    
    
    public static void setKeyActionListner(JComponent component,Action escpli,int keycode){
       if( component instanceof JTextField){
           ((JTextField)component).addActionListener(escpli);
       }
    }
   static int x=-99;
    public static void setKeyAction(JComponent component,Action escpli,int keycode){

        String xx="act"+ ++x;
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke( keycode,0), xx);

        component.getActionMap().put(xx, escpli);
    }
    
    public static void setKeyAction(JComponent component,Action escpli,int keycode,int keyModifier){

        String xx="act"+ ++x;
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke( keycode,keyModifier), xx);
        System.out.println(        component.getActionMap().allKeys()
);
        component.getActionMap().put(xx, escpli);
    }

    public static void setKeyAction(JComponent component,Action escpli,String keycode){
        String xx="act"+ ++x;
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke( keycode), xx);

        component.getActionMap().put(xx, escpli);
    }

     
    public static void setKeyActionListner(JComponent component,ActionListener escpli,int keycode){
       if( component instanceof JTextField){
           ((JTextField)component).addActionListener(escpli);
       }
    }
}
