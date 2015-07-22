/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.event;

import app.utils.SystemUtil;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import org.biz.app.ui.util.Command;
 
/**
 *
 * @author d
 */
public class OAction extends AbstractAction {

    Command com = new Command(){

        @Override
        public Object doBackgroundTask(Object ...objs) {
            return OAction.this.doBackgroundTask(this);
        }

        @Override
        public void doResultTask(Object ...objs) {
            OAction.this.doResultTask(objs);
        }
    
    };

    public OAction() {
    }
    
    public OAction(String name) {
    super(name);
    }
    
    

    public void setCommand(Command com) {
        this.com = com;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object btn = (Object) e.getSource();
        if (btn instanceof JButton) {
            SystemUtil.printParent((JButton) btn);
        }
        com.start(e);
    }

    public Object doBackgroundTask(Object ...objs) {

        return null;
    }

    public void doResultTask(Object ...objs) {
    }

}
