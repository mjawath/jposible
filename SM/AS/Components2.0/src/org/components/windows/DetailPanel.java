/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.windows;

import app.utils.SystemUtil;
import com.components.custom.ControlPanel;
import com.components.custom.FocusManager;
import com.components.custom.IComponent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.apache.commons.beanutils.BeanUtils;
import org.biz.app.ui.event.OAction;
import org.biz.app.ui.util.Command;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.dao.service.Service;
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
    private UIController<T> controller;

    protected OAction delete = new OAction("Delete") {

        public Object doBackgroundTask(Object... objs) {
            System.out.println("doBackgroundTask delete Detail panel");
            controller.delete(selectedObject);
            return 1;

        }

        public void doResultTask(Object... objs) {
            System.out.println("doResultTask delete  Detail panel");
//            controller.pos(selectedObject);
        }

    };

    protected OAction saveAction = new OAction("Save") {

        @Override
        public Object doBackgroundTask(Object... objs) {
            System.out.println("doBackgroundTask saveAction on Detail panel");
            saveX();
            return objs;

        }

        @Override
        public void doResultTask(Object... objs) {
            System.out.println("doResultTask >saveAction> DetailPanel :- successfull");
            clear();
            postSave(null, null, null);
        }

    };

    private OAction clearAction = new OAction("Clear") {

        @Override
        public Object doBackgroundTask(Object... objs) {
            System.out.println("doBackgroundTask clear");
            return null;

        }

        @Override
        public void doResultTask(Object... objs) {
            System.out.println("doResultTask clearAction");
        }

    };
    
    private OAction printAction = new OAction("Clear") {

        @Override
        public Object doBackgroundTask(Object... objs) {
            System.out.println("doBackgroundTask clear");
            return null;

        }

        @Override
        public void doResultTask(Object... objs) {
            System.out.println("doResultTask clearAction");
        }

    };
    
    private OAction gotoListAction = new OAction("GoToList") {

        @Override
        public Object doBackgroundTask(Object... objs) {
            System.out.println("doBackgroundTask clear");
            return null;

        }

        @Override
        public void doResultTask(Object... objs) {
            System.out.println("doResultTask clearAction");
        }

    };
    /**
     * Creates new form DetailPanel
     */
    public DetailPanel() {
        super();

    }
    
    public void requestFocusToFirst(){
        
        
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
//        ComponentFactory.setKeyAction(this, topKeyAction, KeyEvent.VK_DOWN);//first component specific key events are handled then event is passed to this
//        ComponentFactory.setKeyAction(this, topKeyAction, KeyEvent.VK_ENTER);

        //action map ..if component specific events does not call e.consume() on thier level
        Action upKeyAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(" Detail Top Focus Execution Focus to Previous   ");
                gotoPreviousComponent();
            }
        };
//        ComponentFactory.setKeyAction(this, upKeyAction, KeyEvent.VK_UP);//first component specific key events are handled then event is passed to this
//        initComponents();
 
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
      
      
        ComponentFactory.setKeyAction(this, clearAction , KeyEvent.VK_F8);
        ComponentFactory.setKeyAction(this, saveAction, KeyEvent.VK_F5);
        ComponentFactory.setKeyAction(this, delete, KeyEvent.VK_F6);
//        ComponentFactory.setKeyAction(this, delete, KeyEvent.VK_ENTER);
        setCrudControl();
    }
    
    public void setCrudControl(){
        crudcontrolPanel = new ControlPanel();
        crudcontrolPanel.setBounds(10,10,555,30);
        this.add(crudcontrolPanel);
        
        
         crudcontrolPanel.setSaveAction(saveAction);
         crudcontrolPanel.setDeleteAction(delete);
         crudcontrolPanel.setClearAction(clearAction);
         
         crudcontrolPanel.setPrintAction(printAction);
         
         crudcontrolPanel.setGotoAction(gotoListAction);
           this.crudcontrolPanel.setCrudController(this);
         //get all the menues and tool buttons
         // set appropriate action to buttons
//         setToolbar();
         
    }
     
    public void setToolbar(){
        JButton btn = SystemUtil.getButton("SaveButton");        
        btn.setAction(saveAction);
        JButton btnD = SystemUtil.getButton("DeleteButton");        
        btnD.setAction(delete);
        JButton btnC = SystemUtil.getButton("ClearButton");        
        btnC.setAction(clearAction);
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

//    public void addToFocus(CPanel cp) {
//        List<IComponent> coms = cp.getFocus();
//        if (coms.size() == 0) {
//            return;
//        }
//
//        addToFocus(coms);
//
//    }

//    public void addToFocus(IComponent cp) {
//        focusManager.addToFocus(cp);
//        cp.setContainer(this);
//    }


    @Override
    public Object saveX() {        
        return controller.save();        
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

    public void postSave(ArrayList toSave, ArrayList toUpdate, ArrayList toDelete) {
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

    public void preSave(ArrayList toSave,ArrayList toUpdate,ArrayList toDelete) {
    }

    public T uiToData() {
        return null;
    }

    public void setDataToUI(T obj) {
//        controller.setBussinesObject(obj);                
        try {
            busObject = (T) BeanUtils.cloneBean(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setSelectedBusObj(T obj) {
        this.selectedObject = obj;                
        setDataToUI(obj);
        
    }
    
    
    public void setVisualDataToUI(T obj) {
        
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
        commandGUI.start();
        
    }
    
    Command commandGUI = new Command() {
        @Override
        public Object doBackgroundTask(Object ...objs) {
            return loadAfterService();
        }

        @Override
        public void doResultTask(Object ...objs) {
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

    public void showAsFrame(){
   
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                
                
                
                JFrame frame = new JFrame();
                frame.getContentPane().add(DetailPanel.this);
                frame.setSize(1000, 800);
                frame.setVisible(true);
            }
            
        });
        
    }
    
    public void setController(UIController controller){
        this.controller = controller;
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
                .addContainerGap(750, Short.MAX_VALUE)
                .addComponent(crudcontrolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crudcontrolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(517, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected com.components.custom.ControlPanel crudcontrolPanel;
    // End of variables declaration//GEN-END:variables
}
