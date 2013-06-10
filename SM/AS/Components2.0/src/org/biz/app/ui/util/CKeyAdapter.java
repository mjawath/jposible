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

        public CKeyAdapter() {
        }

        @Override
        public void keyReleased(KeyEvent e) {

            com.setParam("event param ");
            com.setView("event param ");
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
  