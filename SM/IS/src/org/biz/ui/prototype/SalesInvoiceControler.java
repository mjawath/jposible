/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.ui.prototype;

import java.util.List;
import org.biz.app.ui.util.QueryManager;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.biz.invoicesystem.service.transactions.SalesInvoiceService;
import org.components.windows.UIController;

/**
 *
 * @author user
 */
public class SalesInvoiceControler extends UIController<SalesInvoice>{


    private SalesInvoiceService salesService;

    private QueryManager qmForSearch = new QueryManager();
        
    public SalesInvoiceControler() {
        salesService = new SalesInvoiceService();
        currentBusObject = new SalesInvoice();
    }

    public void initUI(){
        SalesInvoiceDetailUI salesUI = new SalesInvoiceDetailUI();
        detailView = salesUI;
        setDetailView(detailView);
        SalesOverviewPanel ov = new SalesOverviewPanel();
        setListView(ov,getQueryForPage());
        salesUI.config();
        ov.config();
        
    }
    
    /**
     * @return the salesService
     */
    public SalesInvoiceService getSalesService() {
        return salesService;
    }

    /**
     * @param salesService the salesService to set
     */
    public void setSalesService(SalesInvoiceService salesService) {
        this.salesService = salesService;
    }

    public void findItem(String myQuery) {
    
    
    }
    

    
    public long getCountByCodeLike(String code) {        
        return salesService.getCountByCodeLike(code) ;
    }

    public List getByCodeLike(int page,String code) {
        return salesService.getByCodeLike(code);
    }



    public void showDetailView(Object newRowObject) {
        SalesInvoice si =(SalesInvoice)newRowObject;
//        detailScreen.setDataToUI(si);
    }
    

      
    
    public void onSalesInvoiceLineItemQTYChanged(GridDataLineDetailUI lineDetailUI) {
        SalesInvoiceLineDetailUI salesLineUI = (SalesInvoiceLineDetailUI) lineDetailUI;
        SalesInvoiceLineItem salesInvoiceLineItem = salesLineUI.UIToData();
        salesInvoiceLineItem.calculateLineItem();
        salesLineUI.setDataToUI(salesInvoiceLineItem);
        detailView.uiToData();
        currentBusObject.setTotal();
        detailView.setDataToUI(currentBusObject);
        salesLineUI.getPrice().requestFocus();
    }
    
    
     public void onSalesInvoiceLineItemPriceChanged(GridDataLineDetailUI lineDetailUI) {
        SalesInvoiceLineDetailUI salesLineUI = (SalesInvoiceLineDetailUI) lineDetailUI;       
        SalesInvoiceLineItem salesInvoiceLineItem = salesLineUI.UIToData();
        salesInvoiceLineItem.calculateLineItem();
        salesLineUI.setDataToUI(salesInvoiceLineItem);
        detailView.uiToData();
        currentBusObject.setTotal();
        detailView.setDataToUI(currentBusObject);
        salesLineUI.getPrice().requestFocus();
    }

}
