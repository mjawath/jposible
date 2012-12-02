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
import org.biz.app.ui.util.ComponentFactory;
import org.biz.app.ui.util.ReflectionUtility;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.UIEty;
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
    private Object model;

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

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
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
    JPopupMenu jpm;

    public void showPopUp() {
        try {
            if (getPropertiesEL() == null || getPropertiesEL().length == 0) {
                throw new BizException("not specified properties ");
            }
            if (cxTable1.getColumnCount() == 0) {
                throw new BizException("not specified column ");
            }

            if (!jpm.isVisible()) {
                jpm.setFocusable(false);
                this.setSize(600, 300);
                jpm.setSize(200, 200);
                this.setVisible(true);
                jpm.setVisible(true);
                jpm.show(textField, 30, 30);
                jpm.setFocusable(true);
            }

        } catch (Exception e) {

            System.out.println(" --------------   " + e.getMessage());
        }


    }

    public void init() {
        actionTasks = new ArrayList<ActionTask>();
        textField.addaction(0, new ActionTask() {

            @Override
            public boolean action() {
                System.out.println("====----***action task one **----====");
                getSeletedValue();
                return super.action();
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
                getSeletedValue();

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
                    System.out.println("popup panels related text field selected");
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
            try {
                search(textField.getText());
                setObjectToTable(list);
                showPopUp();

            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    //serach sort filter cache and within the cache we we do 
    //we find our entity
    public void search(String qry) {
    }

    public void getSeletedValue() {
        selectItem();
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

        cLabel1 = new org.components.controls.CLabel();
        cTextField1 = new org.components.controls.CTextField();
        cButton1 = new org.components.controls.CButton();
        cButton2 = new org.components.controls.CButton();
        cButton3 = new org.components.controls.CButton();
        cButton4 = new org.components.controls.CButton();
        cButton5 = new org.components.controls.CButton();
        cButton6 = new org.components.controls.CButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        cxTable1 = new org.components.controls.CxTable();

        cLabel1.setText("Popo fff");

        cButton1.setText(">");

        cButton2.setText(">>");

        cButton3.setText(">|");

        cButton4.setText("<");

        cButton5.setText("<<");

        cButton6.setText("|<");

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
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(cButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(cButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(cTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(cButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(cButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(cButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(cButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(cButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(cButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(cButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CButton cButton2;
    private org.components.controls.CButton cButton3;
    private org.components.controls.CButton cButton4;
    private org.components.controls.CButton cButton5;
    private org.components.controls.CButton cButton6;
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CTextField cTextField1;
    private org.components.controls.CxTable cxTable1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public void selectItem() {

        Object ob = TableUtil.getSelectedModelsValueAt(cxTable1, getSelectedColumn());

        if (ob != null) {
            //find object from list and select
            if (textField instanceof JTextField) {
                selectedID = ob.toString();
                //get selected object
                for (Object object : list) {
                    //compare property with Object property ob.equals(object);
                    if (ob.equals(ReflectionUtility.getProperty(object, "id"))) {
                        selectedObject = (T) object;//selected object can i make it generic
                        UIEty.objToUi(textField, ReflectionUtility.getProperty(selectedObject, getSelectedProperty()));

                    }
                }
                action();
            }


        }
        closePopup();

    }

    public void closePopup() {
        if (jpm.isVisible()) {
            jpm.setVisible(false);
        }
    }

    public void action() {

        System.out.println("action implemented ......");
    }

    public void addaction(ActionTask action) {
        actionTasks.add(action);
    }

    public void setObjectToTable(List lst) {
        list = lst;
        addToTable(lst);
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
        TableUtil.createTableModel(cxTable1, title);
    }

    public void setText(String txt) {
        setPopDesable(true);
        textField.setText(txt);
        setPopDesable(false);
    }
}
