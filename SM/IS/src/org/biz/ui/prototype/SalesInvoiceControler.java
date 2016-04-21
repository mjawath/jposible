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
    

     
    public void onSalesInvoiceLineItemChanged(GridDataLineDetailUI lineDetailUI) {
        SalesInvoiceLineDetailUI salesLineUI = (SalesInvoiceLineDetailUI) lineDetailUI;
        SalesInvoiceLineItem salesInvoiceLineItem = salesLineUI.UIToData();
        salesInvoiceLineItem.calculateLineItem();
        salesLineUI.setDataToUI(salesInvoiceLineItem);
        detailView.uiToData();
        currentBusObject.setTotal();
        ((SalesInvoiceDetailUI) detailView).setVisualDataToUI(currentBusObject);
    }
    
    public void onSalesInvoiceLineItemQTYChanged(GridDataLineDetailUI lineDetailUI) {
        SalesInvoiceLineDetailUI salesLineUI = (SalesInvoiceLineDetailUI) lineDetailUI;
        SalesInvoiceLineItem salesInvoiceLineItem = salesLineUI.UIToData();
        salesInvoiceLineItem.calculateLineItem();
        salesLineUI.setDataToUI(salesInvoiceLineItem);
        detailView.uiToData();
        currentBusObject.setTotal();
        ((SalesInvoiceDetailUI)detailView).setVisualDataToUI(currentBusObject);
//        salesLineUI.getPrice().requestFocus();
    }
    
    
    public void onSalesInvoiceLineItemQTYChanged(SalesInvoiceLineDetailTableUI salesLineUI) {        
        SalesInvoiceLineItem salesInvoiceLineItem = salesLineUI.panelToData();
        salesInvoiceLineItem.calculateLineItem();
        salesLineUI.setDataToPanel(salesInvoiceLineItem);                
        SalesInvoice currentBusObject = detailView.uiToData();
        currentBusObject.addOrUpdateLine(salesInvoiceLineItem);
        currentBusObject.setTotal();
        detailView.setVisualDataToUI(currentBusObject);
//        salesLineUI.getPrice().requestFocus();
    }
    
    public void onSalesInvoiceLineItemPriceChanged(GridDataLineDetailUI lineDetailUI) {
        SalesInvoiceLineDetailUI salesLineUI = (SalesInvoiceLineDetailUI) lineDetailUI;       
        SalesInvoiceLineItem salesInvoiceLineItem = salesLineUI.UIToData();
        salesInvoiceLineItem.calculateLineItem();
        salesLineUI.setDataToUI(salesInvoiceLineItem);
        SalesInvoice currentBusObject = detailView.uiToData();
        currentBusObject.setTotal();
        ((SalesInvoiceDetailUI) detailView).setVisualDataToUI(currentBusObject);
//        salesLineUI.getPrice().requestFocus();
    }

    
    public void onSalesInvoiceDataChanged() {
        SalesInvoice currentBusObject = detailView.uiToData();        
        currentBusObject.calculateTotal(); 
        detailView.setVisualDataToUI(currentBusObject);
//        salesLineUI.getPrice().requestFocus();
    }
    
    
    public void onRemoveLineItem(SalesInvoiceLineItem sil){
        SalesInvoice si = detailView.uiToData();
        si.calculateTotal();
        detailView.setVisualDataToUI(si);
    }
    
     
     
    public void showFrame(String screenName) {
        if ("SaleInvoiceUIX".equals(screenName)) {
            UIFrame.setVisible(true);
        } else if("SaleDetailPanelUI".equals(screenName)){
                        
            UIFrame.setVisible(true);
        }
    }
     
     
}
