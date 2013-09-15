/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author d
 */
public class CKeyAdapter extends KeyAdapter implements ICommand {

    
    Command com = new Command(this);

    int type;//key event type which triggered the event Pressed ,Typed ,Released
    
    public CKeyAdapter() {
    }
    
    public CKeyAdapter(int keyPressedType) {
    this();
    type=keyPressedType;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (type == KeyEvent.KEY_PRESSED) {
            com.invoke();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (type==KeyEvent.KEY_RELEASED) {
            com.invoke();
        }
    }

    public void invoke(){
    com.invoke();
    }
    
    @Override
    public Object executeTask() {

        System.out.println("start executing-------" + com.objs);
        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {
        }
        System.out.println("executing task...........");
        return "key event";
    }

    @Override
    public void resultTask(Object objs) {
        System.out.println("-------result ##########----" + objs);

    }
}  