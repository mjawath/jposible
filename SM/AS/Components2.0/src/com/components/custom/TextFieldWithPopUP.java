/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.components.custom;

import java.util.List;
import org.components.controls.CTextField;

/**
 *
 * @author d
 */
public class TextFieldWithPopUP<T> extends CTextField {

    PagedPopUpPanel<T> pagedPopUpPanel;

    ActionTask searchActionTask;
    ActionTask actionActionTask;

    public TextFieldWithPopUP() {
        super();
        pagedPopUpPanel = new PagedPopUpPanel<T>(this) {

            @Override
            public void action() {
                if(actionActionTask!=null)actionActionTask.actionCall();
                
            }

            @Override
            public void search(String qry) {
                if(searchActionTask!=null)searchActionTask.actionCall(qry);                
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
        this.setText(txt);
        pagedPopUpPanel.setPopDesable(false);
    }

    public T getSelectedObject(){
    return pagedPopUpPanel.getSelectedObject();
    }

    public void setTitle(String[] title) {
        pagedPopUpPanel.setTitle(title);
    }
    
    public void setSelectedProperty(String property) {
        pagedPopUpPanel.setSelectedProperty(property);
    }

    public String getSelectedID() {
        return        pagedPopUpPanel.getSelectedID();
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

    @Override
    public void clear() {
        super.clear();
        setSelectedObject(null);
        pagedPopUpPanel.setSelectedID(null);
    }

    public void initPopup(Class cls,Class[] columnsType, String[] columns, String selectedProp, PopupListner listner) {
        pagedPopUpPanel.setPoplistener(listner);        
        pagedPopUpPanel.setTableType(cls);        
        pagedPopUpPanel.setTitle(columnsType, columns);
        pagedPopUpPanel.setSelectedProperty(selectedProp);
    }
}
