/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CPanel.java
 *
 * Created on Oct 7, 2010, 1:21:25 PM
 */

package org.components.containers;

import com.components.custom.IComponent;
import com.components.custom.IContainer;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.biz.app.ui.util.Tracer;
import org.components.parent.containers.PPanel;
import org.components.windows.DetailPanel;

/**
 *
 * @author nano
 */
public class CPanel extends PPanel implements IContainer ,IComponent {

    private IContainer container;
    
    protected List<IComponent> focus = new ArrayList<IComponent>();

    public List<IComponent> getFocus(){
    return focus;
    }
    
    /** Creates new form BeanForm */
    public CPanel() {
        initComponents();
//         Action downKeyAction = new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("panel     **********Top Focus Execution Focus to Previous   ");
////                gotoPreviousComponent();
//            }
//        };
//        ComponentFactory.setKeyAction(this, downKeyAction, KeyEvent.VK_UP,0,JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);//first component specific key events are handled then event is passed to this

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void callBackAction() {
    }

  @Override
    public void gotoNextComponent(Component jc) {
        //get current focused compnentt
//        Component jc=KeyboardFocusManager.getCurrentKeyboardFocusManager().getPermanentFocusOwner();
      if(temCom!=null)  {
          ((Component)temCom).requestFocus();
          temCom=null;
      }
      focus.indexOf(jc);
        //find from compnent list
        int x=0;
        for (IComponent com : focus) {
            if(  com==jc){
            if(x>=0 && x< focus.size()-1){
                IComponent yx=focus.get(x+1);
            ((Component)yx).requestFocus();
            return;
            }
            }
            x++;
        }

    }
  
  
    
    
    public void requestFocus() {
        super.requestFocus();
        IComponent comx = focus.get(0);
        if (comx != null) {
            ((Component) comx).requestFocus();
        }
    }
  
    public Component getcurrentFocused(){
    return null; 
    }

    public void addToFocus(IComponent com) {
        focus.add(com);
        com.setContainer(this);
    }
    
    


    public void setTempFocusComponent(IComponent com) {
        temCom = com;
    }
    
    private IComponent temCom;

    @Override
    public void setContainer(IContainer con) {
     container=con;
    }

    @Override
    public IContainer getContainer() {
     return null;  
    }   

}
