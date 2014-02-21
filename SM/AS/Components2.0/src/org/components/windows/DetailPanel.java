/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.windows;

import app.AppMainWindow;
import app.utils.SystemUtil;
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
import javax.swing.JToolBar;
import org.biz.app.ui.util.Command;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.util.ReflectionUtility;
import org.biz.app.ui.util.Tracer;
import org.biz.dao.service.GenericDAOUtil;
import org.biz.dao.service.Service;
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
        Action topKeyAction = new AbstractAction() {
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
        focusManager = new FocusManager();
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
    
    
    public void addToToolbar(){
    
        
    }
    

    public void gotoNextComponent() {
        focusManager.gotoNextComponent();

    }

    public void gotoPreviousComponent() {
        focusManager.gotoPreviousComponent();
    }

    public void setNavForTableEditor(final PxTable tbl, CPanel pnl) {

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

    public void addToFocus(List<IComponent> coms) {
        for (IComponent e : coms) {
            addToFocus(e);
//            focusManager.addToFocus(e);
        }
    }

    public void addToFocus(CPanel cp) {
        List<IComponent> coms = cp.getFocus();
        if (coms.size() == 0) {
            return;
        }

        addToFocus(coms);

    }

    public void addToFocus(IComponent cp) {
        focusManager.addToFocus(cp);
        cp.setContainer(this);
    }


    @Override
    public Object saveX() {
        ArrayList result = new ArrayList();
        ArrayList toSave = new ArrayList();
        ArrayList toDelete = new ArrayList();
        ArrayList toUpdate = new ArrayList();

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

            if (obj == null) {
                //should retrieve new id and set to new objects
                
                toSave.add(busObject);
                preCreate(toSave, toUpdate, toDelete);
                auditPersistenceData(toSave);
                auditUpdatedData(toUpdate, key,GenericDAOUtil.currentTime());
                Tracer.printToOut(" Object  is not found  So creation will be called");
                service.getDao().saveUpdateDelete(toSave, toUpdate, toDelete);

                postCreate(toSave, toUpdate, toDelete);
            }
            else {
                ((BusObj) busObject).setId(key.toString());
                toUpdate.add(busObject);
                preUpdate(toSave, toUpdate, toDelete);
                auditPersistenceData(toSave);
                auditUpdatedData(toUpdate, key,((BusObj)obj).getSavedDate());

                Tracer.printToOut("Updation is called  Object  is  found");
                service.getDao().saveUpdateDelete(toSave, toUpdate, toDelete);
                postUpdate(toSave, toUpdate, toDelete);
            }
        }
        result.add(toSave);
        result.add(toUpdate);
        result.add(toDelete);
        Tracer.printToOut("Detail panel Save is successfully performed , result is returned, method is called using BT");
        return result;
    }

    private void auditPersistenceData(ArrayList<BusObj> objs) {

        Date cDate = GenericDAOUtil.currentTime();
        
        for (BusObj bus : objs) {            
            bus.setId( EntityService.getKey(""));
            bus.setSavedDate(cDate);
            bus.setDepententEntitiesIDs();
                
        }
    }

    private void auditUpdatedData(ArrayList<BusObj> objs, Object key,Date startDate) {


        Date mDate = GenericDAOUtil.currentTime();
        for (BusObj bus : objs) {
            bus.setSavedDate(startDate);
            bus.setEditedDate(mDate);
            bus.setDepententEntitiesIDs();
        }
    }

    public void preCreate(ArrayList objCreates, ArrayList objUpdates, ArrayList objDeletes) {
    }

    @Override
    public void delete() {
        if (service == null) {
            return;
        }
        ArrayList result = new ArrayList();
        ArrayList toSave = new ArrayList();
        ArrayList toDelete = new ArrayList();
        ArrayList toUpdate = new ArrayList();
        if (selectedObject == null) {
            MessageBoxes.infomsg(this, "Please select an item to delete ", "Nothing  to delete ");
            return;
        }
        T exist = (T) service.getDao().find(((BusObj) selectedObject).getId());
        if (exist == null) {
            return;
        }
        String[] ObjButtons = {"Yes", "No"};
        int PromptResult = JOptionPane.showOptionDialog(null, "Are you want to delete ?",
                "Delete Record", -1, 2, null, ObjButtons, ObjButtons[1]);

        if (PromptResult == 0) {
            preDelete(toSave, toUpdate, toDelete);
            service.getDao().delete(exist);
            postDelete(toSave, toUpdate, toDelete);
            clear();
        }

    }

    public void preDelete(ArrayList toSave, ArrayList toUpdate, ArrayList toDelete) {
    }

    public void postDelete(ArrayList toSave, ArrayList toUpdate, ArrayList toDelete) {
    }

    public void postCreate(ArrayList tosave, ArrayList toupdate, ArrayList todelete) {
//        System.out.println("-------+++++++++");
    }

    private void preUpdate(ArrayList toSave, ArrayList toUpdate, ArrayList toDelete) {
    }

    public void postUpdate(ArrayList toSave, ArrayList toUpdate, ArrayList toDelete) {
    }

    public boolean isValideEntity() {
//         busObject=UIToEty();       
//        if(busObject==null)
//         return false;
        return true;
    }

    public void onSaveComplete(Object objs) {
        ArrayList result = (ArrayList) objs;
        if (result == null) {
            return;
        }
//        onSaveComplete();
//        toSave.clear();
//        toUpdate.clear();
//        toDelete.clear();
        result.clear();
        clear();
        selectedObject = null;
        busObject = null;

    }

    public void preSave() {
    }

    public T getBusObject() {
        return null;
    }

    public void setBusObject(T obj) {
    }

    public void setSelectedBusObj(T obj) {
        this.selectedObject = obj;
    }

    public void etyToUI(T obj) {
    }

    public T UIToEty() {
        return null;
    }
    
    public void clear() {
        selectedObject = null;
        super.clear();
    }

    @Override
    public void updateEntityUI() {
        super.updateEntityUI();
        focusManager.resetFocus();
//        clear();
        SystemUtil.setTools(crudcontrolPanel);

    }

    @Override
    public void setService(Service service) {
        super.setService(service);
        commandGUI.invoke();
        
    }
    
    Command commandGUI = new Command() {
        @Override
        public Object executeTask() {
            return loadAfterService();
        }

        @Override
        public void resultTask(Object objs) {
            Object[] obs = (Object[]) objs;
            //set values to UI
            loadUIAfterService(obs);
        }
    };
    
    
    
    
    public Object[] loadAfterService(){
        return null;
    }
    
    public void loadUIAfterService(Object [] objs){
        
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
    protected com.components.custom.ControlPanel crudcontrolPanel;
    // End of variables declaration//GEN-END:variables
}
