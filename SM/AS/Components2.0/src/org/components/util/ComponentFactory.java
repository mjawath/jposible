/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.components.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.Action;
import javax.swing.InputMap;
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
    
    
    
        //set short cut for 
        // whole component navigation usign kkeys like up down enter
        /**
         * panel.getInputMap(con).put(KeyStroke.getKeyStroke("control I"),
         * "createNewFood"); panel.getActionMap().put("createNewFood", new
         * NewFoodAction());
         *         
*
         * short cut key
         *
         *
         * Action action = new AbstractAction("Do It") { ... };
         *
         * // This is the component we will register the keyboard shortcut with.
         * JPanel pnl = new JPanel();
         *
         * // Create KeyStroke that will be used to invoke the action. KeyStroke
         * keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_T,
         * InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK);
         *
         * // Register Action in component's ActionMap.
         * pnl.getActionMap().put("Do It", action);
         *
         * // Now register KeyStroke used to fire the action. I am registering
         * this with the // InputMap used when the component's parent window has
         * focus.
         * pnl.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "Do
         * It"); *
         */
    
    public static void setKeyAction(JComponent component,Action escpli,int keycode){

        setKeyAction(component, escpli, keycode, 0);//0 is no modifier
    }
    
    public static void setKeyAction(JComponent component,Action escpli,int keycode,int keyModifier){

        String xx="act"+ ++x;
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( keycode,keyModifier), xx);

        component.getActionMap().put(xx, escpli);
        
        
    }


    
       public static void setKeyAction(JComponent component,Action escpli,String keycode){
        String xx="act"+ ++x;
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke( keycode), xx);

        component.getActionMap().put(xx, escpli);
    }
    
    
    public static void setKeyAction(JComponent component,Action escpli,int keycode,int keyModifier, int focusBehave){

        String xx="act"+ ++x;
        component.getInputMap(focusBehave).put(KeyStroke.getKeyStroke( keycode,keyModifier), xx);

        component.getActionMap().put(xx, escpli);
        
        
    }

    public static void setKeyAction(JComponent component,Action escpli,String keycode,int focusBehave){
        String xx="act"+ ++x;
        component.getInputMap(focusBehave).put(KeyStroke.getKeyStroke( keycode), xx);

        component.getActionMap().put(xx, escpli);
    }
    
        public static void removeAction(JComponent component,  int keycode) {
        InputMap im= component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        Object obj=im.get(KeyStroke.getKeyStroke( keycode,0));
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(keycode, 0),
                "none");
    }


    public static void setKeyActxxion(JComponent component,Action escpli,int keycode){

        String xx="act"+ ++x;
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke( keycode,0), xx);

        component.getActionMap().put(xx, escpli);
    }

 
}
