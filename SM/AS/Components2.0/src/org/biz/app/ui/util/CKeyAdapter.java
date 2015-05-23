/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import org.biz.app.ui.event.OAction;

/**
 *
 * @author d
 */
public class CKeyAdapter extends KeyAdapter  {

    

    int type;//key event type which triggered the event Pressed ,Typed ,Released
    
    public CKeyAdapter() {
    }
    
    public CKeyAdapter(int keyPressedType) {
    this();
    type=keyPressedType;
    }


    @Override
    public void keyPressed(final KeyEvent e) {
        if (type == KeyEvent.KEY_PRESSED) {
                final Command com= new Command() {
            @Override
            public Object doBackgroundTask(Object ...objs) {
                getParams().add(e);
                return CKeyAdapter.this.doBackgroundTask(getParams());
            }

            @Override
            public void doResultTask(Object ...objs) {
                CKeyAdapter.this.doResultTask(objs);
            }
        };
        com.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    
    public Object doBackgroundTask(Object ...obj) {

//        System.out.println("start executing-------" + com.objs);
        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {
        }
        System.out.println("executing task...........");
        return "key event";
    }

    public void doResultTask(Object ...objs) {
        System.out.println("-------result ##########----" + objs);

    }

    }  