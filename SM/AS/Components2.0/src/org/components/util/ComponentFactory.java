/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.components.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.Action;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import org.components.parent.Documents.DoubleDocument;
import org.components.parent.Documents.NumberDocument;

/**
 *
 * @author mjawath
 */
public class ComponentFactory {
    
    
    

    public static  void addKeyAction(JComponent com,final JComponent requestFocus){
    
        com.addKeyListener(new KeyAdapter() {        
            public void keyPressed(KeyEvent e) {
                if(KeyEvent.VK_ENTER==e.getKeyCode()){
                requestFocus.requestFocus();
                    e.consume();
                }
            }
        });
    }
    public static void addKeyAction(Object [][] list){
        for (Object[] pairCom : list) {
            addKeyAction((JComponent)pairCom[0], (JComponent)pairCom[1]);
        }
    }
    public static  void createDoubleTextField(JTextField field){
        field.setDocument(new DoubleDocument());
        field.setHorizontalAlignment(JTextField.RIGHT);
        field.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                try {
                    String txt= ((JTextField)input).getText();
                    if (txt.isEmpty()) {
                        return true;
                    }
                      Double ver= Double.parseDouble(txt);
                return true;
                } catch (Exception e) {
                    return false;
                }
            }
        });
    }
    public static  void createDoubleTextField(JTextField field,boolean zero){
        field.setDocument(new DoubleDocument());
        field.setHorizontalAlignment(JTextField.RIGHT);
        field.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                try {
                    String txt= ((JTextField)input).getText();
                    if (txt.isEmpty()) {
                        return true;
                    }
                      Double ver= Double.parseDouble(txt);
                return true;
                } catch (Exception e) {
                    return false;
                }
            }
        });
    }

    public static  void createLongTextField(JTextField field){
        field.setDocument(new NumberDocument());
        field.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                try {
                    String txt= ((JTextField)input).getText();
                    if (txt.isEmpty()) {
                        return true;
                    }
                      Long ver= Long.parseLong(txt);
                return true;
                } catch (Exception e) {
                    return false;
                }
            }
        });
        field.setHorizontalAlignment(JTextField.RIGHT);
    }
    public static  void createLongTextField(JTextField field,boolean  zero){
        field.setDocument(new NumberDocument(zero));
        field.setHorizontalAlignment(JTextField.RIGHT);
        field.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                try {
                    String txt= ((JTextField)input).getText();
                    if (txt.isEmpty()) {
                        return true;
                    }
                      Long ver= Long.parseLong(txt);
                return true;
                } catch (Exception e) {
                    return false;
                }
            }
        });
    }
    static long x=new Random().nextLong();
    
    public static void setKeyAction(JComponent component,Action escpli,int keycode){

        String xx="act"+ ++x;
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( keycode,0), xx);

        component.getActionMap().put(xx, escpli);
    }

    public static void setKeyAction(JComponent component,Action escpli,String keycode){
        String xx="act"+ ++x;
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( keycode), xx);

        component.getActionMap().put(xx, escpli);
    }

}
