/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.components.windows;

import org.biz.dao.service.Service;

/**
 *
 * @author user
 */
public class UIController<T> {
  
    protected T currentBusObject;
    protected Service service;
    protected ListViewPanel<T> listView;
    protected DetailPanel<T> detailView;
    
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
    }

    public ListViewPanel<T> getListView() {
        return listView;
    }

    public void setListView(ListViewPanel<T> listView) {
        this.listView = listView;
    }

    public DetailPanel<T> getDetailView() {
        return detailView;
    }

    public void setDetailView(DetailPanel<T> detailView) {
        this.detailView = detailView;
    }
    
    
    public void executeSearch(Object obj){
    
    }
}
