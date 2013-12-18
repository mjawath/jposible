/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.components.custom;

import java.awt.event.ActionListener;
import java.util.List;
import org.components.containers.CPanel;
import org.components.controls.CTextField;

/**
 *
 * @author d
 */
public class TextFieldWithPopUPx<T> extends CPanel {

    PagedPopUpPanel<T> pagedPopUpPanel;
    private CTextField fieldWithPopUP;
    ActionTask searchActionTask;
    ActionTask actionActionTask;

    public TextFieldWithPopUPx() {
        super();
        fieldWithPopUP = new CTextField();
        pagedPopUpPanel = new PagedPopUpPanel<T>(fieldWithPopUP) {
            @Override
            public void action() {
                if (actionActionTask != null) {
                    actionActionTask.actionCall();
                }

            }

            @Override
            public void search(String qry) {
                if (searchActionTask != null) {
                    searchActionTask.actionCall(qry);
                }
            }
        };
    }

    public void setPagedPopUpPanel(PagedPopUpPanel<T> pagedPopUpPanel) {
        this.pagedPopUpPanel = pagedPopUpPanel;
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

    public T getSelectedObject() {
        return pagedPopUpPanel.getSelectedObject();
    }

    public void setTitle(String[] title) {
        pagedPopUpPanel.setTitle(title);
    }

    public void setSelectedProperty(String property) {
        pagedPopUpPanel.setSelectedProperty(property);
    }

    public String getSelectedID() {
        return pagedPopUpPanel.getSelectedID();
    }

    public void setSelectedObject(T it) {
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

    @Override
    public void requestFocus() {
        fieldWithPopUP.requestFocus();
    }
    
    
}
