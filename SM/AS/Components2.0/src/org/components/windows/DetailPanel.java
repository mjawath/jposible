/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.windows;

import com.components.custom.FocusManager;
import com.components.custom.IComponent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import org.biz.util.ReflectionUtility;
import org.biz.app.ui.util.Tracer;
import org.biz.dao.service.GenericDAOUtil;
import org.biz.dao.util.EntityService;
import org.biz.entity.BusObj;
import org.components.containers.CPanel;
import org.components.parent.controls.PxTable;
import org.components.util.ComponentFactory;

/**
 *
 * @author d
 */
public class DetailPanel<T> extends TabPanelUI {

    protected T busObject;
    protected T selectedObject;
    protected FocusManager focusManager;

    /**
     * Creates new form DetailPanel
     */
    public DetailPanel() {
        super();
          
    }

    @Override
    public void config() {
        Action topKeyAction=new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Top Focus Execution Focus to Next");
                gotoNextComponent();
            }
        };
        ComponentFactory.setKeyAction(this, topKeyAction, KeyEvent.VK_DOWN);//first component specific key events are handled then event is passed to this
        ComponentFactory.setKeyAction(this, topKeyAction, KeyEvent.VK_ENTER);

        //action map ..if component specific events does not call e.consume() on thier level
        Action upKeyAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(" Detail Top Focus Execution Focus to Previous   ");
                gotoPreviousComponent();
            }
        };
        ComponentFactory.setKeyAction(this, upKeyAction, KeyEvent.VK_UP);//first component specific key events are handled then event is passed to this
        initComponents();
        this.crudcontrolPanel.setCrudController(this);
        focusManager=new FocusManager();
        super.config();
        
        
//        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control I"),"createNewFood");
//        panel.getActionMap().put("createNewFood", new
//       NewFoodAction());
        ComponentFactory.setKeyAction(this, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("----event estcape");
            }
        }, KeyEvent.VK_ESCAPE);
        
    }
    
    
    
    public void gotoNextComponent() {
        focusManager.gotoNextComponent();

    }

    public void gotoPreviousComponent() {
        focusManager.gotoPreviousComponent();
    }

    
    public void setNavForTableEditor(final PxTable tbl,CPanel pnl ){
    
       Action downKeyAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    tbl.selectNextRow();
                
            }
        };
        ComponentFactory.setKeyAction(this, downKeyAction, KeyEvent.VK_PAGE_DOWN);//first component specific key events are handled then event is passed to this
        
        Action upKeyAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    tbl.selectPreRow();
                
            }
        };
        ComponentFactory.setKeyAction(this, upKeyAction, KeyEvent.VK_PAGE_UP);//first component specific key events are handled then event is passed to this

    }

    public void addToFocus(List<IComponent> coms){
        for (IComponent e : coms) {
            addToFocus(e);
//            focusManager.addToFocus(e);
        }
    }
    
   public void addToFocus(CPanel cp) {
       List<IComponent> coms= cp.getFocus();
       if(coms.size()==0)return;
        
       addToFocus(coms);
       
   }
   
   public void addToFocus(IComponent cp) {
       focusManager.addToFocus(cp);      
       cp.setContainer(this);
   }
    
    
    public void etyToUI(T obj) {
    }

    public T UIToEty() {
        return null;
    }

    @Override
    public Object saveX() {
    ArrayList result=new ArrayList();
    ArrayList toSave=new ArrayList();
    ArrayList toDelete=new ArrayList();
    ArrayList toUpdate=new ArrayList();
   
        busObject = getBusObject();
        if (service != null) {
            if (!isValideEntity()) {
                return null;
            }
            preSave();
            
//            if (toSave.isEmpty()) {
//                Tracer.printToOut("no object found to Save");
//                return;
//            }
            Object key = ReflectionUtility.getProperty(selectedObject, "id");//0 is the index of the main object , id is id property

            if (busObject == null) {
                Tracer.printToOut("Detail panel -> SaveX -> Bus Object is null ,Not saved");
                return null;
            }
            Object obj = service.getDao().find(key);
            Date cDate = GenericDAOUtil.currentTime();
            Date mDate = GenericDAOUtil.currentTime();
            if (obj == null ) {                
                ReflectionUtility.setProperty(busObject,"id",EntityService.getKey(""));
                ReflectionUtility.setProperty(busObject, "savedDate", cDate);
                toSave.add(busObject);    
                
                Tracer.printToOut(" Object  is not found  So creation will be called");
                service.getDao().saveUpdateDelete(toSave, toUpdate, toDelete);
                
                postCreate(result);
            }
            else {
                ReflectionUtility.setProperty(busObject, "id", key);
                ReflectionUtility.setProperty(busObject,"editedDate",mDate);
                ReflectionUtility.setProperty(busObject, "savedDate", ((BusObj)obj).getSavedDate());

                toUpdate.add(busObject);
                Tracer.printToOut("Updation is called  Object  is  found");
                service.getDao().saveUpdateDelete(toSave, toUpdate, toDelete);
                postUpdate(result);
            }
             }
        result.add(toSave);
        result.add(toUpdate);
        result.add(toDelete);
        Tracer.printToOut("Detail panel Save is successfully performed , result is returned, method is called using BT");
        return result;
    }
    
    
    @Override
    public void delete() {
        if (service != null) {

            T exist = (T) service.getDao().find(((BusObj) selectedObject).getId());
            if (exist != null) {

                String[] ObjButtons = {"Yes", "No"};
                int PromptResult = JOptionPane.showOptionDialog(null, "Are you want to delete ?", "Item Form", -1, 2, null, ObjButtons, ObjButtons[1]);

                if (PromptResult == 0) {
                    service.getDao().delete(exist);
                    postDelete(exist);
                    clear();
                }
            }
            else {
//                MessageBoxes.errormsg(null, "No Item Found.", getTabName());
                return;

            }

        }
    }

    public void postDelete(Object deleObj) {
    }

    public void postCreate(Object deleObj) {
        System.out.println("-------+++++++++");
    }

    public void postUpdate(Object deleObj) {
        System.out.println("---%%%%%%%%%%%----+++++++++");
    }

    public boolean isValideEntity() {
//         busObject=UIToEty();       
//        if(busObject==null)
//         return false;
        return true;
    }

    public void postSave(Object objs) {
        ArrayList result=(ArrayList)objs;
        if(result==null)return;
//        postSave();
//        toSave.clear();
//        toUpdate.clear();
//        toDelete.clear();
        result.clear();
        clear();
        selectedObject = null;
        busObject=null;

    }

    public void preSave() {
    }
    
    public T getBusObject(){
        return null;
    }

    public void setBusObject(T obj) {
    }

    public void setSelectedBusObj(T obj) {
        this.selectedObject = obj;
    }

    @Override
    public void updateEntityUI() {
        super.updateEntityUI();
        focusManager.resetFocus();
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crudcontrolPanel = new com.components.custom.ControlPanel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 60, Short.MAX_VALUE)
                .addComponent(crudcontrolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crudcontrolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(259, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.components.custom.ControlPanel crudcontrolPanel;
    // End of variables declaration//GEN-END:variables
}
