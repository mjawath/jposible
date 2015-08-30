/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.components.custom;

import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.biz.app.ui.util.Command;
import org.biz.app.ui.util.ComponentFactory;
import org.biz.app.ui.util.QueryManager;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.Tracer;
import org.biz.app.ui.util.UIEty;
import org.biz.app.ui.util.UIListener;
import org.biz.entity.BusObj;
import org.biz.util.ReflectionUtility;
import org.components.containers.CPanel;
import org.components.controls.CPopupMenu;
import org.components.controls.CTextField;
import org.components.test.ResultPage;
import org.components.windows.MasterViewUI;
import org.components.windows.ListViewUI;

/**
 *
 * @author d
 */
public class TextFieldWithPopUP<T> extends CPanel implements UIListener{

    private JPopupMenu jpm;
    private ListViewUI listView;
    protected PagedPopUpPanel<T> pagedPopUpPanel;
    private CTextField fieldWithPopUP;
    private ActionTask searchActionTask;
    private ActionTask actionActionTask;
    private int selectedColumn = 0;
    private T selectedObject;
    private String selectedID;
    private String pageKey;
    private Boolean popupDisabled = false;
    private QueryManager qm;

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textField = new org.components.controls.CTextField();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 268, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(textField, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(textField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CTextField textField;
    // End of variables declaration//GEN-END:variables

    public TextFieldWithPopUP() {
        super();
        initComponents();

        fieldWithPopUP = textField;

        addToFocus(fieldWithPopUP);

        jpm = new CPopupMenu();
        jpm.setSize(200, 200);
        jpm.setFocusable(false);

        textField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {

                closePopup();
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
        
        textField.addaction(0, new ActionTask() {
            @Override
            public void actionCall(Object obj) {
                selectItem();
            }
        });
        
        
        ComponentFactory.setKeyAction(textField, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().redispatchEvent(listView.getTable(), e);
            }
        }, KeyEvent.VK_DOWN);
        ComponentFactory.setKeyAction(textField, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().redispatchEvent(listView.getTable(), e);
            }
        }, KeyEvent.VK_UP);
        textField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (jpm.isVisible()) {
                        KeyboardFocusManager.getCurrentKeyboardFocusManager().redispatchEvent(listView.getTable(), e);
                        e.consume();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (jpm.isVisible()) {
                        KeyboardFocusManager.getCurrentKeyboardFocusManager().redispatchEvent(listView.getTable(), e);
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

    }

    public void setListOverView(MasterViewUI<T> listViewPanel) {
        setListView(listViewPanel.getListViewUI());
    }

    public void setListView(ListViewUI ui) {
        listView = ui;

        listView.getTable().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                selectItem();
//                 editor.getComponent().postActionEvent();
            }
        });

        ComponentFactory.setKeyAction(textField, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().redispatchEvent(listView.getTable(), e);
            }
        }, KeyEvent.VK_DOWN);
        ComponentFactory.setKeyAction(textField, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().redispatchEvent(listView.getTable(), e);
            }
        }, KeyEvent.VK_UP);
        textField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (jpm.isVisible()) {
                        KeyboardFocusManager.getCurrentKeyboardFocusManager().redispatchEvent(listView.getTable(), e);
                        e.consume();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (jpm.isVisible()) {
                        KeyboardFocusManager.getCurrentKeyboardFocusManager().redispatchEvent(listView.getTable(), e);
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

        jpm.removeAll();
        jpm.add(listView);
        jpm.setPreferredSize(listView.getPreferredSize());

    }

    private Command command = new Command() {

        @Override
        public Object doBackgroundTask(Object... objs) {

            return doSearch();
        }

        @Override
        public void doResultTask(Object... objs) {
//            setSearchResult(objs);
        }

    };

    public Object doSearch() {
//        listView.executeQuery();
        return null;
    }

    private void searchWhenDocumentChange() {
        if (textField.isFocusOwner() && !popupDisabled) {

            if (qm != null) {
                qm.executeToFirstPageTask();
            } else if (at != null) {
                at.action();
            }
        }
    }

    public void setListViewQueryManger(QueryManager qm,ListViewUI listViewUI){
        this.qm =  qm ; 
        qm.setGui(this);
        qm.addUIListener(this);
        this.listView = listViewUI;
        listView.initPaging(qm);

    }
    
    public void setAt(ActionTest at) {
        this.at = at;
    }

    protected ActionTest at;

    @Override
    public void updateUI(ResultPage ui) {        
        showPopUp(ui);
    }

    public interface ActionTest {

        public abstract void action();
    }

    public int getSelectedColumn() {
        return selectedColumn;
    }

    public void selectItem() {
        popupDisabled = true;

        Object ob = TableUtil.getSelectedModelsValueAt(listView.getTable(), getSelectedColumn());
        if (ob != null) {
            if (textField instanceof JTextField) {
                selectedObject = (T) ob;
                setSelectedText();
//                action();
            }
        }
        closePopup();
        popupDisabled = false;

    }

    public void setPopDesable(Boolean disable) {
        popupDisabled = disable;
    }

    public void setSelectedText() {
        if (textField instanceof JTextField) {
            //get selected object
            if (selectedObject == null || getSelectedProperty() == null) {
                UIEty.objToUi(textField, "");
                return;
            }
            if (selectedObject instanceof BusObj) {
                UIEty.objToUi(textField, ReflectionUtility.getProperty(selectedObject, getSelectedProperty()));
            } else if (selectedObject instanceof String) {
                UIEty.objToUi(textField, selectedObject);
            }
        }
    }
    private String selectedProperty;

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

    public void closePopup() {
//        if (jpm.isVisible()) {
        setSelectedText();
        jpm.setVisible(false);
//        }
    }

    public void setSearchResult(Object objs) {
        pagedPopUpPanel.setSearchResult(objs);

    }

    public void setCommand(Command command) {
        pagedPopUpPanel.setCommand(command);
    }

    public PagedPopUpPanel<T> getPagedPopUpPanel() {
        return pagedPopUpPanel;
    }

    public ActionTask getActionActionTask() {
        return actionActionTask;
    }

    public void setActionActionTask(ActionTask actionActionTask) {
        this.actionActionTask = actionActionTask;
    }

    public ActionTask getSearchActionTask() {
        return searchActionTask;
    }

    public void setSearchActionTask(ActionTask searchActionTask) {
        this.searchActionTask = searchActionTask;
    }

    public void setPropertiesEL(String[] propertiesEL) {
        pagedPopUpPanel.setPropertiesEL(propertiesEL);
    }

    public void setTextItem(String txt) {
        pagedPopUpPanel.setPopDesable(true);
        fieldWithPopUP.setText(txt);
        pagedPopUpPanel.setPopDesable(false);
    }

    public void setTitle(String[] title) {
        pagedPopUpPanel.setTitle(title);
    }

    public String getSelectedID() {
        return pagedPopUpPanel.getSelectedID();
    }

    public void setSelectedObject(T it) {
        if(pagedPopUpPanel==null)return;
        pagedPopUpPanel.setPopDesable(true);
        pagedPopUpPanel.setSelectedObject(it);
        pagedPopUpPanel.setSelectedText();
        pagedPopUpPanel.setPopDesable(false);
    }

    public void setObjectToTable(List<T> listCust) {
        pagedPopUpPanel.setObjectToTable(listCust);
        pagedPopUpPanel.showPopUp();
    }

    public void clear() {
        fieldWithPopUP.clear();
        setSelectedObject(null);
        pagedPopUpPanel.setSelectedID(null);
    }

    public void initPopup(Class cls, Class[] columnsType, String[] columns, String selectedProp, PopupListner listner) {
        pagedPopUpPanel.setPoplistener(listner);
        pagedPopUpPanel.setTableType(cls);
        pagedPopUpPanel.setTitle(columnsType, columns);
        pagedPopUpPanel.setSelectedProperty(selectedProp);
    }

    public void setText(String text) {
        fieldWithPopUP.setText(text);

    }

    public void setDocAction(ActionListener action) {

        fieldWithPopUP.setDocAction(action);
    }

    public void addActionListener(ActionListener e) {
        fieldWithPopUP.addActionListener(e);
    }

    public void setActionTask(ActionTask actionTask) {
        fieldWithPopUP.addActionListener(actionTask);
    }

    public void addaction(int idx, ActionTask action) {
        fieldWithPopUP.addaction(idx, action);
    }

    public void setValue(Object val) {
        fieldWithPopUP.setValue(val);
    }

    public void setValue(String val) {
        fieldWithPopUP.setValue(val);
    }

    public String getText() {
        return fieldWithPopUP.getText();
    }

    @Override
    public void requestFocus() {
        fieldWithPopUP.requestFocus();
    }

    public boolean hasFocus() {

        return fieldWithPopUP.hasFocus();
    }

    public void setEditable(boolean isEditable) {
        fieldWithPopUP.setEditable(isEditable);
    }
    
    public void showPopUp(ResultPage rp){
        listView.setResult(rp);        
        showPopUp();
    }
        
    public void showPopUp() {        
        if (!jpm.isVisible() && textField.hasFocus()) {
            jpm.setFocusable(false);
            listView.setPreferredSize(new Dimension(600, 400));
            jpm.add(listView);
            jpm.setVisible(true);
            jpm.show(textField, 30, 30);
//                jpm.setFocusable(true);
        }
    }
//}

    public boolean hasListView() {
        return listView != null;
    }

}
