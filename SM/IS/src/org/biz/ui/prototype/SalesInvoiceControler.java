/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.ui.prototype;

import java.util.List;
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

            
    public SalesInvoiceControler() {
        super();
        salesService = new SalesInvoiceService();
        currentBusObject = new SalesInvoice();
        setService(salesService);
    }

    public void initUI(){        
        SalesUI sales = new SalesUI();
        setUIFrame(sales);
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
//        salesLineUI.getPrice().requestFocus();
    }
    
    
    public void onSalesInvoiceLineItemPriceChanged(GridDataLineDetailUI lineDetailUI) {
        SalesInvoiceLineDetailUI salesLineUI = (SalesInvoiceLineDetailUI) lineDetailUI;       
        SalesInvoiceLineItem salesInvoiceLineItem = salesLineUI.UIToData();
        salesInvoiceLineItem.calculateLineItem();
        salesLineUI.setDataToUI(salesInvoiceLineItem);
        detailView.uiToData();
        currentBusObject.setTotal();
        detailView.setDataToUI(currentBusObject);
//        salesLineUI.getPrice().requestFocus();
    }
    
    public void onSalesInvoiceDataChanged() {
        
        detailView.uiToData();
        currentBusObject.setTotal();
        detailView.setDataToUI(currentBusObject);
//        salesLineUI.getPrice().requestFocus();
    }
    
     
     
    public void showFrame(String screenName) {
        if ("SalesInvoicex".equals(screenName)) {
            UIFrame.setVisible(true);
        }
    }
     
     
}
