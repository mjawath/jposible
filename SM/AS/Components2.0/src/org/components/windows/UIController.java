/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.windows;

import com.components.custom.TextFieldWithPopUP;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.app.ui.util.QueryManager;
import org.biz.app.ui.util.StringUtility;
import org.biz.app.ui.util.Tracer;
import org.biz.dao.service.GenericDAOUtil;
import org.biz.dao.service.Service;
import org.biz.entity.BusObj;

/**
 *
 * @author user
 */
public class UIController<T extends BusObj> {

    protected T selectedBusObject;
    protected T currentBusObject;
    protected Service service;
    protected MasterViewUI<T> listView;
    protected DetailPanel<T> detailView;
    protected UIFrame UIFrame;
    private final Class businessClass;

    public UIController() {
        businessClass = ((Class)(((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]));
    }
    

    public void setService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    public T getCurrentBusObject() {
        return currentBusObject;
    }

    public void setCurrentBusObject(T currentBusObject) {
        this.currentBusObject = currentBusObject;
        detailView.setSelectedBusObj(currentBusObject);
    }

    public void setSelectedBusObject(T selectedBusObject) {
        this.selectedBusObject = selectedBusObject;
        detailView.setSelectedBusObj(selectedBusObject);
    }

    public MasterViewUI<T> getListView() {
        return listView;
    }

    public void setListView(MasterViewUI<T> listView, QueryManager qm) {
        this.listView = listView;
        listView.setController(this);
        listView.setQueryMananger(qm);

    }

    public void setUIFrame(UIFrame frame) {
        UIFrame = frame;
        UIFrame.setController(this);
        detailView = UIFrame.getDetail();
        listView = UIFrame.getMaster();
        setDetailView(detailView);
        setListView(listView, getQueryForPage());
//        detailView.config();
        UIFrame.revalidate();
        UIFrame.repaint();
        listView.setDetailPanel(detailView);

    }

    public UIFrame getUIFrame() {
        return UIFrame;
    }

    //*** new implementation
    public void executeToFirstPageTask() {
        getPopupQueryManger().executeToFirstPageTask();
    }

    public void showFrame() {
        UIFrame.setVisible(true);

    }

    public void showFrame(String screenName) {
        UIFrame.setVisible(true);

    }

    public void showFrame(int screenType) {
        UIFrame.setVisible(true);
    }

    public DetailPanel<T> getDetailView() {
        return detailView;
    }

    public void setDetailView(DetailPanel<T> detailView) {
        this.detailView = detailView;
        detailView.setController(this);
    }

    private PoupQueryManger popupQry = new PoupQueryManger();

    public QueryManager getPopupQueryManger() {
        return popupQry;
    }

    protected boolean isValideEntity() {
        return true;
    }

    public void initUI() {

    }

    public void gotoDetailPanel() {

        UIFrame.goToDetail();

    }

    private class PoupQueryManger extends QueryManager {

        public Long executeCountQuery() {
            if (getGui() == null) {
                return -1l;
            } else if ((getGui() != null) && getGui() instanceof TextFieldWithPopUP) {
                return service.getCountByCodeLike(((TextFieldWithPopUP) getGui()).getText());
            }
            return -1l;
        }

        @Override
        public List executeQuery(int page) {
            if (getGui() == null) {
                return null;
            }
            if ((getGui() != null) && getGui() instanceof TextFieldWithPopUP) {
                List byCodeLike = service.getByCodeLike(page, ((TextFieldWithPopUP) getGui()).getText());
                return byCodeLike;
            }
            return null;

        }
    }

    private class SearchQueryManager extends QueryManager {

        public Long executeCountQuery() {
            return executeCount();

        }
        @Override
        public List executeQuery(int page) {
            //what ever we can implement here 
            return UIController.this.executeQuery(page);
        }
    }

    protected SearchQueryManager mmm = new SearchQueryManager();

    public QueryManager getQueryForPage() {
        return mmm;
    }

 
    public List executeQuery(int page) {
        SearchQueryUIPanel searchUI = (SearchQueryUIPanel) getListView().getSearchUI();
        String searchTextFieldValue = searchUI.getSearchTextFieldValue();

        if (StringUtility.isEmptyString(searchTextFieldValue)) {
            return getService().getDao().getAll();
        }
        String where = "";
        String attribute = searchUI.getAttribute();
        where = searchTextFieldValue != null ? " c." + attribute + " like '" + searchTextFieldValue + "%' " : "";
        
//        String name = qmp.get("name");
//        where = name != null ? " c.name like " + name +" " : "";
        return getService().getByWhere(where);        
    }

    public Long executeCount() {
        SearchQueryUIPanel searchUI = (SearchQueryUIPanel) getListView().getSearchUI();
        String searchTextFieldValue = searchUI.getSearchTextFieldValue();
        
        if (StringUtility.isEmptyString(searchTextFieldValue)) {
            return getService().getDao().getAllCount();
        }
        String where = "";
        String attribute = searchUI.getAttribute();        
        where = searchTextFieldValue != null ? " c."+attribute+" like '" + searchTextFieldValue + "%' " : "";

//        String name = qmp.get("name");
//        where = name != null ? " c.name like " + name + " " : "";
        return getService().getCountOfByWhere(where);        
    }

    public ArrayList save() {
        ArrayList resutls = new ArrayList();

        T busObject = (T) detailView.uiToData();
        currentBusObject = (T) busObject;

        if (!isValideEntity()) {//check for current business objects validity
            return null;
        }
//        service.preSave(toSave, toUpdate, toDelete);

//            if (toSave.isEmpty()) {
//                Tracer.printToOut("no object found to Save");
//                return;
//            }
        if (busObject == null) {
            Tracer.printToOut("Detail panel -> SaveX -> Bus Object is null ,Not saved");
            return null;
        }

        if (selectedBusObject == null) {

         service.save(busObject);

        } else {//update mode
            if (MessageBoxes.yesNo(detailView, "Current record already exist in the DATABASE Are you sure\n"
                    + "You want to update the record", "Update Confirmation") == MessageBoxes.NO_OPTION) {
                return null;
            }
            final Object id = ((BusObj) selectedBusObject).getId();
            if (id == null) {
                System.out.println("------some thing is wrong --------");
                return null;
            }

            if (id instanceof String) {
                ((BusObj) busObject).setId((String)id);
            } else if (id instanceof Long) {
//                ((BusObj) busObject).setId((Long) id);
            }
           
           resutls.add(service.save(busObject));
        }


        //call some observer method
        Tracer.printToOut("Detail panel Save is successfully performed , result is returned, method is called using BT");
        return resutls;
    }

    public void preCreate(ArrayList toSave, ArrayList toUpdate, ArrayList toDelete) {

    }

    private void auditPersistenceData(ArrayList<BusObj> objs) {

        Date cDate = GenericDAOUtil.currentTime();

        for (BusObj bus : objs) {
//            bus.setId( EntityService.getKey(""));      
            bus.setSavedDate(cDate);
            bus.setEditedDate(cDate);
            bus.setDepententEntitiesIDs();

        }
    }

    private void auditUpdatedData(ArrayList<BusObj> objs, Date startDate) {

        Date mDate = GenericDAOUtil.currentTime();
        for (BusObj bus : objs) {
            bus.setSavedDate(startDate);
            bus.setEditedDate(mDate);
            bus.setDepententEntitiesIDs();
        }
    }

    public void preSave(ArrayList toSave, ArrayList toUpdate, ArrayList toDelete) {

    }

    private void postCreate(ArrayList toSave, ArrayList toUpdate, ArrayList toDelete) {

    }

    private void preUpdate(ArrayList toSave, ArrayList toUpdate, ArrayList toDelete) {

    }

    private void postUpdate(ArrayList toSave, ArrayList toUpdate, ArrayList toDelete) {
    }

    public void delete(Object selectedObject) {
        if (service == null) {
            return;
        }
        if (selectedObject == null) {
            MessageBoxes.infomsg(detailView, "Please select an item to delete ", "Nothing  to delete ");
            return;
        }

        String[] ObjButtons = {"Yes", "No"};
        int PromptResult = JOptionPane.showOptionDialog(null, "Are you want to delete ?",
                "Delete Record", -1, 2, null, ObjButtons, ObjButtons[1]);

        if (PromptResult == 0) {
            service.delete((BusObj) selectedObject);
            clear();
        }

    }

    public void clear() {
        detailView.clear();
    }
    
    public void createNew() {
        selectedBusObject = null;
        currentBusObject = null;

    }
    
    public void print(){
        //
//        print
    }
    
    
    public void executeSearchForCustom() {

        getQueryForPage().executeToFirstPageTask();
    }

}
