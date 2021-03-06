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
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.biz.app.ui.util.Command;
import org.biz.app.ui.util.ComponentFactory;
import org.biz.app.ui.util.QueryManager;
import org.biz.app.ui.util.StringUtility;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.UIEty;
import org.biz.app.ui.util.UIListener;
import org.biz.entity.BusObj;
import org.biz.util.ReflectionUtility;
import org.components.containers.CPanel;
import org.components.controls.CPopupMenu;
import org.components.controls.CTextField;
import org.components.test.ResultPage;
import org.components.windows.ListViewUI;
import org.components.windows.MasterViewUI;

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
    
    public JTextField getTextField(){
    return textField;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textField = new org.components.controls.CTextField();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        textField.setMinimumSize(new java.awt.Dimension(14, 12));
        textField.setPreferredSize(new java.awt.Dimension(14, 12));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textField, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
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

//            JWindow w = new JWindow();
            @Override
            public void focusGained(FocusEvent e) {
//                System.out.println("location "+getLocationOnScreen());
//                w.setSize(200, 200);
//                w.setLocation(getLocationOnScreen().x ,getLocationOnScreen().y + getHeight());
//                w.setVisible(true);
                super.focusGained(e);
            }

            
            
            @Override
            public void focusLost(FocusEvent e) {
//                w.dispose();
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
               
            }
        });
        textField.setActionTask(new ActionTask(){
        
            public void actionCall() {
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
                
                if (e.getKeyCode() == KeyEvent.VK_F2) {
                    
                    System.out.println("Creating new ");
//                    listView.onDoubleClicked();
                   
                }
            }
        });

    }

    public void setListOverView(MasterViewUI listViewPanel) {
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
        final boolean istextNotEmpty = !StringUtility.isEmptyString(getText());
        if (istextNotEmpty &&  ob != null) {
            if (textField instanceof JTextField) {
                selectedObject = (T) ob;
                setSelectedText();   
                if(actionTask!=null)
                actionTask.actionCall();
            }
        }else if (istextNotEmpty && selectedObject!=null)
            selectedObject = null;
            setSelectedText();
                if( actionTask != null) {
                actionTask.actionCall();
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
                Object xx=ReflectionUtility.getProperty(selectedObject, getSelectedProperty());
                UIEty.objToUi(textField, xx);
             
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
        selectedObject = it;
        setPopDesable(true);
        setSelectedText();
        setPopDesable(false);     
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
        setPopDesable(true);                
        fieldWithPopUP.clear();        
        setSelectedObject(null);
        setPopDesable(false);
        if(pagedPopUpPanel!=null){
            pagedPopUpPanel.setSelectedID(null);
        }
    }

    public void initPopup(Class cls, Class[] columnsType, String[] columns, String selectedProp, PopupListner listner) {
        
        if(pagedPopUpPanel==null)return;
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

    private ActionTask actionTask;
    public void setActionTask(ActionTask actionTask) {
        this.actionTask = actionTask;
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
    public boolean requestFocusInWindow() {

        return fieldWithPopUP.requestFocusInWindow();
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
