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
import java.util.List;
import org.components.parent.containers.PPanel;

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

//        setFocusTraversalPolicy(new MyFocusPolicy());
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
            
    public Component gotoNextComponentNew(Component jc) {
        //get current focused compnentt
//        Component jc=KeyboardFocusManager.getCurrentKeyboardFocusManager().getPermanentFocusOwner();
        
        focus.indexOf(jc);
        //find from compnent list
        int x = 0;
        for (IComponent com : focus) {
            if (com == jc) {
                if (x >= 0 && x < focus.size() - 1) {
                    IComponent yx = focus.get(x + 1);                    
                    return ((Component) yx);
                }
            }
            x++;
        }
        return null;

    }

    
    public Component  gotoFirstComponent() {
        if(focus==null || focus.isEmpty()){
            return null;
        }else{
         if(focus.get(0) instanceof CPanel){
            return ((CPanel)focus.get(0)).gotoFirstComponent();
         }
         else{
             return ((Component) focus.get(0));
         }
        }     
    }
    
    public Component gotoNextComponentInThisPanel(Component com) {
        if (focus == null || focus.isEmpty()) {
            return null;
        } else {
            int x = focus.indexOf(com);
                
          
                if (x >= 0 && x+1 < focus.size()) {             
               final Component current = (Component)focus.get(x+1);
               if (current instanceof CPanel) {
                    return ((CPanel) current).gotoFirstComponent();
                } else {
                    return ((Component) current);
                }    
            }else{
            
                return null;
            
            }
            
        }
    }
    
    public Component gotoPreComponentInThisPanel(Component com) {
        if (focus == null || focus.isEmpty()) {
            return null;
        } else {
            int x = focus.indexOf(com);

            if (x > 0 && x  < focus.size()) {
                final Component current = (Component) focus.get(x - 1);
                if (current instanceof CPanel) {
                    return ((CPanel) current).gotoFirstComponent();
                } else {
                    return ((Component) current);
                }
            } else {

                return null;

            }

        }
    }
//    public void gotToNextComponent(Component aComponent) {
//        final Object parent = aComponent.getParent();
//        if (parent != null && parent instanceof CPanel) {
//            Component focus = ((CPanel) parent).gotoNextComponentNew(aComponent);
//            if (focus == null) {
//                gotToNextComponent((CPanel) parent);
//            } else {
//                if (focus instanceof CPanel) {
//                    Component focus2 = ((CPanel) focus).gotoFirstComponent();
//
//                } else {
//                    focus.requestFocus();
//                }
//            }
//        }
//
//    }
  
    private void getParentOfComponent(Component acomp) {
        if (acomp == null) {
            return;
        }
        if (acomp.getParent() instanceof CPanel) {
            final CPanel parent = (CPanel) acomp.getParent();
            if ((parent).getFocus().contains(acomp)) {
                (parent).gotoNextComponent(acomp);
            } else if (parent.getParent() != null && parent.getParent() instanceof CPanel) {
                getParentOfComponent(parent);
            }

        }
    }  
    
    public void requestFocus() {
        super.requestFocus();
        if(focus.size()<=0)return;
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
    
    
    public boolean hasFocusableComponents(){
        return focus!=null && !focus.isEmpty();
    }

    public boolean hasFocusableComponents(Component com) {
        return focus!=null && !focus.isEmpty() &&  focus.size()>1 && focus.indexOf(com)!=-1  && focus.indexOf(com)<=(focus.size()-2);
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
