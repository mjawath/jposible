/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Detail.java
 *
 * Created on Dec 7, 2011, 10:14:09 PM
 */
package com.components.custom;

import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.biz.app.ui.util.BizException;
import org.biz.app.ui.util.Command;
import org.biz.app.ui.util.ComponentFactory;
import org.biz.util.ReflectionUtility;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.Tracer;
import org.biz.app.ui.util.UIEty;
import org.biz.entity.BusObj;
import org.components.controls.CPopupMenu;
import org.components.controls.CTextField;
import org.components.parent.controls.editors.TablePopUpCellEditor;

/**
 *
 * @author nnjj
 */
public abstract class PagedPopUpPanel<T> extends javax.swing.JPanel {

    JTable tbl;
    CTextField textField;
    TablePopUpCellEditor editor;
    int selectedColumn = 0;
    T selectedObject;
    String selectedID;
    String pageKey;
    List list;
    Boolean popupDisabled = false;
    JComponent nextFocusableComponent;
    boolean moveTonextcom = true;
    List<ActionTask> actionTasks;
    private String selectedProperty;
    private JPopupMenu jpm;
    private PopupListner popupListner;
    private Command command=  new Command(){

        @Override
        public Object executeTask() {
            return doSearch();
        }

        @Override
        public void resultTask(Object objs) {
            setSearchResult(objs);
        }
            
        };

    public Object doSearch() {
        if (popupListner == null) {
            Tracer.printToOut("Popuplistner in PagedPopUpPanel is NULL ");
            return null;
        }
//            String text=(String)command.objs.get(0);
        return popupListner.searchItem(textField.getText());
    }
    
    public void setSearchResult(Object objs) {
        if (objs == null && list != null) {
            setModelCollection(list);
            return;
        }
//            tItemCategory.setObjectToTable();
//            Object [][] objas=new Object[0][];
        setModelCollection((List) objs);
    }
    
    public void setSearchTask(Command com){
        this.command = com;
        
    }
    
    //TODO - should be replaced with list view to hold table
    public void setModelCollection(List<T> modelCollection) {
        cxTable1.clear();
        //for each item set values to table model
        if (modelCollection == null) {
            return;
        }

        Object[][] data = new Object[modelCollection.size()][];
        for (int x = 0; x < modelCollection.size(); x++) {
            Object[] objrow = popupListner.getTableData(modelCollection.get(x));
            data[x] = objrow;
        }
        TableUtil.filldata(cxTable1, data);
        showPopUp();
    }



    public int getSelectedColumn() {
        return selectedColumn;
    }

    public String getSelectedID() {
        //get selected item  from objects


        return selectedID;
    }

    public String getSelectedProperty() {
        return selectedProperty;
    }

    public void setSelectedProperty(String property) {
        this.selectedProperty = property;
    }

    public T getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedColumn(int selectedColumn) {
        this.selectedColumn = selectedColumn;
    }

    public void setSelectedID(String selectedID) {
        this.selectedID = selectedID;
    }

    public void setSelectedObject(T selectedObject) {
        this.selectedObject = selectedObject;
    }

    public void setPopDesable(Boolean disable) {
        popupDisabled = disable;
    }

    public JTable getTbl() {
        return tbl;
    }

    public void setTbl(JTable tbl) {
        this.tbl = tbl;
    }

    public CTextField getTextField() {
        return textField;
    }

    public void setTextField(CTextField textField) {
        this.textField = textField;
    }

    /**
     * Creates new form Detail
     */
    public PagedPopUpPanel(JTable tb, TablePopUpCellEditor field) {
        initComponents();
        tbl = tb;
        editor = field;
        textField = (CTextField) field.getComponent();
        editor.setMasterTbl(tbl);
        init();
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public PagedPopUpPanel(CTextField field) {
        initComponents();
        textField = field;
//        this.requestFocusInWindow();
//        cTextField1.requestFocus();



        init();

    }

    public PagedPopUpPanel(CTextField field, List model, String[] columnproperties, String[] columnTitle) {
        this(field);
        list = model;
        setPropertiesEL(columnproperties);
        setTitle(columnTitle);
    }

    public PagedPopUpPanel() {
        initComponents();
        tbl = new JTable();
    }
    
    public void showPopUp() {
        try {
//            if (getPropertiesEL() == null || getPropertiesEL().length == 0) {
//                throw new BizException("not specified properties ");
//            }
            if (cxTable1.getColumnCount() == 0) {
                throw new BizException("not specified column ");
            }

            if (!jpm.isVisible() && textField.hasFocus()) {
                jpm.setFocusable(false);
                this.setSize(600, 300);
                jpm.setSize(200, 200);
                this.setVisible(true);
                jpm.setVisible(true);
                jpm.show(textField, 30, 30);
                jpm.setFocusable(true);
            }
 
        } catch (Exception e) {
             Tracer.printToOut(" --------------   " + e.getMessage());
        }


    }

    public void init() {
        actionTasks = new ArrayList<ActionTask>();
        textField.addaction(0, new ActionTask() {
            @Override
            public void actionCall(Object obj) {
                selectItem();
            }
        });
        jpm = new CPopupMenu();
        this.setSize(600, 300);
        jpm.add(this);
        jpm.setSize(200, 200);
//        this.requestFocusInWindow();
//        cTextField1.requestFocus();
        jpm.setFocusable(false);

        textField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {

                closePopup();
            }
        });

        cxTable1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                selectItem();
//                 editor.getComponent().postActionEvent();
            }
        });


//        textField.setInputVerifier(new InputVerifier() {
//
//            @Override
//            public boolean verify(JComponent input) {
//                System.out.println("===------==action with some thing elselistner==-------==");
//                  for (ActionTask actionTask : actionTasks) {
//                        actionTask.action();
//                    }
//                return true;
//            }
//        });

        ComponentFactory.setKeyAction(textField, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().redispatchEvent(cxTable1, e);
            }
        }, KeyEvent.VK_DOWN);
        ComponentFactory.setKeyAction(textField, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().redispatchEvent(cxTable1, e);
            }
        }, KeyEvent.VK_UP);
        textField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (jpm.isVisible()) {
                        KeyboardFocusManager.getCurrentKeyboardFocusManager().redispatchEvent(cxTable1, e);
                        e.consume();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (jpm.isVisible()) {
                        KeyboardFocusManager.getCurrentKeyboardFocusManager().redispatchEvent(cxTable1, e);
                        e.consume();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (jpm.isVisible()) {
                        jpm.setVisible(false);
                        e.consume();
                    }
                }
            }
        });

        textField.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                searchWhenDocumentChange();
            }

            public void removeUpdate(DocumentEvent e) {
                searchWhenDocumentChange();
            }

            public void changedUpdate(DocumentEvent e) {
                searchWhenDocumentChange();
            }
        });


    }

    private void searchWhenDocumentChange() {
        if (textField.isFocusOwner() && !popupDisabled) {
            command.objs.add(textField.getText());
            command.invoke();//
            //
            //what ever class 
            //get item list 
            // get  attributes from obj
            //create data array
            //set to table
        }
    }
    
    public void setCommand(Command command){
    this.command = command;
    }
    
    public void setPoplistener(PopupListner listener){
    this.popupListner=listener;
    }

    //serach sort filter cache and within the cache we we do 
    //we find our entity
    public void search(String qry) {
    }

    
    public void setModel() {

        TableUtil.createTableModel(cxTable1, new String[]{"111", "22", "33,44", "55", "666", "777"}, new Class[]{
                    Object.class, String.class, String.class, Double.class, Double.class, Double.class, Double.class, Double.class
                });
    }

    public JComponent getNextFocusableComponent() {
        return nextFocusableComponent;
    }

    public void setNextFocusableComponent(JComponent nextFocusableComponent) {
        this.nextFocusableComponent = nextFocusableComponent;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        cxTable1 = new org.components.controls.CxTable();
        cPaginatedPanel1 = new org.biz.app.ui.util.CPaginatedPanel();

        cxTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        cxTable1.setFocusable(false);
        jScrollPane1.setViewportView(cxTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(cPaginatedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(cPaginatedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.biz.app.ui.util.CPaginatedPanel cPaginatedPanel1;
    private org.components.controls.CxTable cxTable1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public void selectItem() {
        popupDisabled = true;

        Object ob = TableUtil.getSelectedModelsValueAt(cxTable1, getSelectedColumn());
        if (ob != null) {
            if (textField instanceof JTextField) {
                selectedObject = (T) ob;
                setSelectedText();
                action();
            }
        }
        closePopup();
        popupDisabled = false;

    }
    
    public void setSelectedText() {
        if (textField instanceof JTextField) {
            //get selected object
            if(selectedObject==null ||getSelectedProperty()==null ){
                UIEty.objToUi(textField,"");
                return;
            }
            if(selectedObject instanceof BusObj){
            UIEty.objToUi(textField, ReflectionUtility.getProperty(selectedObject, getSelectedProperty()));
            }else if(selectedObject instanceof String ){
             UIEty.objToUi(textField,selectedObject);
            }
        }
    }

    public void closePopup() {
//        if (jpm.isVisible()) {
        setSelectedText();
        jpm.setVisible(false);
//        }
    }

    public void action() {

    }

    public void addaction(ActionTask action) {
        actionTasks.add(action);
    }

    public void setObjectToTable(List lst) {
//        list = lst;
        long yx = System.currentTimeMillis() ;

        if(lst==null)return;
        Object [][] x=new Object[lst.size()][];//fro list
        String[] prop = cxTable1.getPropertiesEL();

        int in=0;
        for(Object obj:list){
            x[in]= new Object[prop.length];
            x[in][0]=obj;
            for (int i = 1; i < prop.length; i++) {
                String var = prop[i];
                Object ob = ReflectionUtility.getProperty(obj, var);
                x[in][i]=ob;
            }
//            BeanUtils.describe(obj);
//            BeanUtils.populate(obj, null);
            in++;
        }
        long pr=System.currentTimeMillis();
        yx =  pr- yx;
        System.out.println("bench mark for looping and object [][] " + x.length + " object  causes " + yx);
        TableUtil.filldataObj(cxTable1, x);        
        yx = System.currentTimeMillis() - pr;
        System.out.println("bench mark for fill table with "+ x.length +" object  causes "+yx);
    }

    public void addToTable(List items) {
        TableUtil.cleardata(cxTable1);
        if (items == null || items.isEmpty()) {
            return;
        }
        for (Object itm : items) {
            addToTable(itm);
        }
//        TableUtil.addrowSR(cxTable1, new Object[]{TableUtil.newRowID, ""});
    }

    public void addToTable(Object item) {
//        TableUtil.addrowSR(cxTable1, data(item));
        TableUtil.addModelToTable(item, cxTable1);
    }

    public Object[] data(Object item) {
        return null;
    }

    public String[] getPropertiesEL() {
        return cxTable1.getPropertiesEL();
    }

    public void setPropertiesEL(String[] propertiesEL) {
        cxTable1.setPropertiesEL(propertiesEL);
    }

    public void setTitle(String[] title) {
        cxTable1.setColumnHeader(title);
    }

    public void setText(String txt) {
        setPopDesable(true);
        textField.setText(txt);
        setPopDesable(false);
    }
    
    public void setTableType(Class tableType) {
        cxTable1.setModelClass(tableType);    
    }
    public void setTitle(Class [] column,String[] title) {        
        cxTable1.setColumnTypes(column, title);
    }
}
/*
 searching with the jpa column query is givign bench mark test good result
 but tryng the getting the object [] from list of object result to set to table
 * using reflection
 
 */