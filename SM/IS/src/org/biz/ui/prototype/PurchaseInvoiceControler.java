/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.ui.prototype;

import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import org.biz.app.ui.util.StringUtility;
import org.biz.invoicesystem.entity.transactions.PurchaseInvoice;
import org.biz.invoicesystem.entity.transactions.PurchaseInvoiceLineItem;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.service.transactions.PurchaseInvoiceService;
import org.components.windows.SearchQueryUIPanel;
import org.components.windows.UIController;

/**
 *
 * @author user
 */
public class PurchaseInvoiceControler extends UIController<PurchaseInvoice> {

    private PurchaseInvoiceService salesService;

    public PurchaseInvoiceControler() {
        super();
        salesService = new PurchaseInvoiceService();
        currentBusObject = new PurchaseInvoice();
        setService(salesService);
    }

    public void initUI() {
        PurchaseUI sales = new PurchaseUI();
        setUIFrame(sales);
    }

    public void showDetailView(Object newRowObject) {
        SalesInvoice si = (SalesInvoice) newRowObject;
//        detailScreen.setDataToUI(si);
    }



    public void onSalesInvoiceLineItemQTYChanged(PurchaseInvoiceLineDetailTableUI salesLineUI) {
        PurchaseInvoiceLineItem salesInvoiceLineItem = salesLineUI.panelToData();
        salesInvoiceLineItem.calculateLineItem();
        salesLineUI.setDataToPanelIFNotFocused(salesInvoiceLineItem);
//        PurchaseInvoice currentBusObject = detailView.uiToData();
//        currentBusObject.addOrUpdateLine(salesInvoiceLineItem);
//        currentBusObject.setTotal();
//        detailView.setVisualDataToUI(currentBusObject);
//        salesLineUI.setFocusToPrice();
    }

    public void onSalesInvoiceLineItemDocChanged(PurchaseInvoiceLineDetailTableUI salesLineUI) {
        PurchaseInvoiceLineItem salesInvoiceLineItem = salesLineUI.panelToData();
        salesInvoiceLineItem.calculateLineItem();
        salesLineUI.setDataToPanelIFNotFocused(salesInvoiceLineItem);
    }

    public JComponent onSalesInvoiceLineItemPriceChanged(PurchaseInvoiceLineDetailTableUI salesLineUI) {

        PurchaseInvoiceLineItem salesInvoiceLineItem = salesLineUI.panelToData();
        salesInvoiceLineItem.calculateLineItem();
        final JComponent inValidComponent = salesLineUI.getInValidComponent();
        if (inValidComponent != null) {
            return inValidComponent;
        }
        //validate the line item
        //........
//        if (!salesInvoiceLineItem.isValid()) {
//            return ;
//        }

        salesLineUI.setDataToPanelIFNotFocused(salesInvoiceLineItem);

        PurchaseInvoice currentBusObject = detailView.uiToData();
        ///get the current line and update it        

        PurchaseInvoiceLineItem selectedSL = (PurchaseInvoiceLineItem) salesLineUI.getSelectedLineObject();
        if(salesInvoiceLineItem.getUom() ==null && salesInvoiceLineItem.getSku()!=null){
            salesInvoiceLineItem.setUom(salesInvoiceLineItem.getSku().getItem().getPrimaryUOM());
        }
        currentBusObject.addOrUpdateLine(selectedSL, salesInvoiceLineItem);
        currentBusObject.setTotal();
        /*if(this is a valid entry )
            then add to table 
        else revert or alert 
         */
        detailView.setVisualDataToUI(currentBusObject);
        salesLineUI.clearLineUI();
//        salesLineUI.requestFocus();
        return salesLineUI;
    }



    public void onSalesInvoiceDataChanged() {
        PurchaseInvoice currentBusObject = detailView.uiToData();
        currentBusObject.calculateTotal();
        detailView.setVisualDataToUI(currentBusObject);
//        salesLineUI.getPrice().requestFocus();
    }

    public void onRemoveLineItem(PurchaseInvoiceLineItem sil) {
        PurchaseInvoice si = detailView.uiToData();
        si.calculateTotal();
        detailView.setVisualDataToUI(si);
    }

    public void showFrame(String screenName) {
//        if ("SaleInvoiceUIX".equals(screenName)) {
            UIFrame.setVisible(true);
//        } else if ("SaleDetailPanelUI".equals(screenName)) {

//            UIFrame.setVisible(true);
//        }
    }

    public void clearTableUI() {
        PurchaseInvoice currentBusObject = detailView.uiToData();
        currentBusObject.calculateTotal();
        detailView.setVisualDataToUI(currentBusObject);
    }

    protected boolean isValideEntity() {

        List<PurchaseInvoiceLineItem> lineItems = currentBusObject.getLineItems();

        if (lineItems == null || lineItems.size() <= 0 ) {
            return false;
        }
        //todo do we have to validate sales line items line by line
        
//        if(ISProperties.isCustomerMandotoryForInvoice() && currentBusObject.getCustomer()==null){
//             MessageBoxes.infomsg(null,"No customer found" , "Validation errors" );
//            return false;
//        }

        return true;
    }



}
