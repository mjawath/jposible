/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.components.windows;

import com.components.custom.TextFieldWithPopUP;
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
import org.biz.entity.PrimaryKey;
import org.components.test.MenuBuilder;

/**
 *
 * @author user
 */
public class UIController<T> {
    
    
  
    protected T currentBusObject;
    protected Service service;
    protected MasterViewUI<T> listView;
    protected DetailPanel<T> detailView;
    protected UIFrame UIFrame; 
    
    public void setBussinesObject(T bussObject){
        this.currentBusObject = bussObject;                
    }
    public void setService(Service  service){
        this.service = service;
    }
    
    public Service getService(){
    return service;
    }

    public T getCurrentBusObject() {
        return currentBusObject;
    }

    public void setCurrentBusObject(T currentBusObject) {
        this.currentBusObject = currentBusObject;
        detailView.setSelectedBusObj(currentBusObject);
    }

    public MasterViewUI<T> getListView() {
        return listView;
    }

    public void setListView(MasterViewUI<T> listView,QueryManager qm) {
        this.listView = listView;
        listView.setController(this);
        listView.setQueryMananger(qm);

    }
    public void setUIFrame(UIFrame frame){
        UIFrame = frame;
        detailView = UIFrame.getDetail();
        listView = UIFrame.getMaster();
        setDetailView(detailView);
        setListView(listView, getQueryForPage());
        detailView.config();        
        UIFrame.revalidate();
        UIFrame.repaint();        
        listView.setDetailPanel(detailView);
        
    }
    public UIFrame getUIFrame(){
    return UIFrame;
    }
    
    //*** new implementation
    public void executeToFirstPageTask() {
        getPopupQueryManger().executeToFirstPageTask();
    }
    
    public void showFrame(){
        UIFrame.setVisible(true);
        
    }

    
    public void showFrame(String screenName) {
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

    private boolean isValideEntity() {
        return true;
    }
    
    public void initUI(){
    
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

    
     private class MYQueryManger extends QueryManager {

        public Long executeCountQuery() {
            return executeCount();

        }        
                @Override
        public List executeQuery(int page) {
            //what ever we can implement here 
            return myexecuteQuery(page);
        }     
    }
     
    protected MYQueryManger mmm = new MYQueryManger();

    
    
    public QueryManager getQueryForPage(){
        return mmm;
    }
    
    public List myexecuteQuery(int page) {

        String txt = String.valueOf(listView.getSearchUI().getQueryParameterMap().get(SearchQueryUIPanel.QRY));
        if(StringUtility.isEmptyString(txt)){
            return getService().getDao().getAll(page);
        }
        final List byCodeLike = getService().getByCodeLike(page, txt);
        return byCodeLike;
    }

    private Long executeCount() {
        String txt = String.valueOf(listView.getSearchUI().getQueryParameterMap().get(SearchQueryUIPanel.QRY));
        if(StringUtility.isEmptyString(txt)){
            return (Long)getService().getDao().getAllCount();
        }
        return getService().getCountByCodeLike(txt);
    }

    
    public ArrayList save() {
        long x = System.currentTimeMillis();
        ArrayList result = new ArrayList();
        ArrayList toSave = new ArrayList();
        ArrayList toDelete = new ArrayList();
        ArrayList toUpdate = new ArrayList();
        System.out.println("  saving   " + (System.currentTimeMillis() - x));
        Object busObject = detailView.uiToData();

        if (!isValideEntity()) {
            return null;
        }
        preSave(toSave, toUpdate, toDelete);

//            if (toSave.isEmpty()) {
//                Tracer.printToOut("no object found to Save");
//                return;
//            }
        if (busObject == null) {
            Tracer.printToOut("Detail panel -> SaveX -> Bus Object is null ,Not saved");
            return null;
        }
        Object id = ((BusObj) busObject).getId();//0 is the index of the main object , id is id property

        System.out.println("find b" + (System.currentTimeMillis() - x));

        Object obj = id == null ? null : service.getDao().find(id);

        if (obj == null) {
            //should retrieve new id and set to new objects

            toSave.add(busObject);
            preCreate(toSave, toUpdate, toDelete);
            auditPersistenceData(toSave);
            auditUpdatedData(toUpdate, id, GenericDAOUtil.currentTime());
            Tracer.printToOut(" Object  is not found  So creation will be called");
            service.getDao().saveUpdateDelete(toSave, toUpdate, toDelete);
            System.out.println("saved " + (System.currentTimeMillis() - x));

            postCreate(toSave, toUpdate, toDelete);
        } else {
            if (MessageBoxes.yesNo(detailView, "Current record already exist in the DATABASE Are you sure\n"
                    + "You want to update the record", "Update Confirmation") == MessageBoxes.NO_OPTION) {
                return null;
            }

            ((BusObj) busObject).setId((PrimaryKey)id);
            toUpdate.add(busObject);
            preUpdate(toSave, toUpdate, toDelete);
            auditPersistenceData(toSave);
            auditUpdatedData(toUpdate, id, ((BusObj) obj).getSavedDate());

            Tracer.printToOut("Updation is called  Object  is  found");
            service.getDao().saveUpdateDelete(toSave, toUpdate, toDelete);
            postUpdate(toSave, toUpdate, toDelete);
        }

        result.add(toSave);
        result.add(toUpdate);
        result.add(toDelete);

        //call some observer method
        Tracer.printToOut("Detail panel Save is successfully performed , result is returned, method is called using BT");
        return result;
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

    private void auditUpdatedData(ArrayList<BusObj> objs, Object key,Date startDate) {


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
        ArrayList result = new ArrayList();
        ArrayList toSave = new ArrayList();
        ArrayList toDelete = new ArrayList();
        ArrayList toUpdate = new ArrayList();
        if (selectedObject == null) {
            MessageBoxes.infomsg(detailView, "Please select an item to delete ", "Nothing  to delete ");
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

    private void preDelete(ArrayList toSave, ArrayList toUpdate, ArrayList toDelete) {
        
    }

    private void postDelete(ArrayList toSave, ArrayList toUpdate, ArrayList toDelete) {
        
    }

    private void clear() {
        
    }

   
   public void setMenuStructure(){
       MenuBuilder mb = new MenuBuilder();
//       mb.set
   } 
    
  
}
