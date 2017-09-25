/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.ui.prototype;

import com.biz.system.ISProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.app.ui.util.StringUtility;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.entity.master.UOM;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.biz.invoicesystem.service.transactions.SalesInvoiceService;
import org.components.windows.SearchQueryUIPanel;
import org.components.windows.UIController;

/**
 *
 * @author user
 */
public class SalesInvoiceControler extends UIController<SalesInvoice> {

    private SalesInvoiceService salesService;

    public SalesInvoiceControler() {
        super();
        salesService = new SalesInvoiceService();
        currentBusObject = new SalesInvoice();
        setService(salesService);
    }

    public void initUI() {
        SalesUI sales = new SalesUI();
        setUIFrame(sales);
    }

    public List executeQuery(int page) {

        String txt = String.valueOf(listView.getSearchUI().getQueryParameterMap().get(SearchQueryUIPanel.QRY));
        if (StringUtility.isEmptyString(txt)) {
            return getService().getDao().getAll(page);
        }
        final List byCodeLike = getService().getByCodeLike(page, txt);
        return byCodeLike;
    }

    public Long executeCount() {
        SalesSearchUI ssui = (SalesSearchUI) listView.getSearchUI();
        Map<String, Object> queryParameterMap = ssui.getQueryParameterMap();

        String txt = String.valueOf(listView.getSearchUI().getQueryParameterMap().get(SearchQueryUIPanel.QRY));
        if (StringUtility.isEmptyString(txt)) {
            return (Long) getService().getDao().getAllCount();
        }
        return getService().getCountByCodeLike(txt);
    }

    public void showDetailView(Object newRowObject) {
        SalesInvoice si = (SalesInvoice) newRowObject;
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
        if(salesInvoiceLineItem.getUom() ==null && salesInvoiceLineItem.getSku()!=null){
            salesInvoiceLineItem.setUom(salesInvoiceLineItem.getSku().getItem().getPrimaryUOM());
        }
        salesLineUI.setDataToUI(salesInvoiceLineItem);
        detailView.uiToData();
        currentBusObject.setTotal();
        ((SalesInvoiceDetailUI) detailView).setVisualDataToUI(currentBusObject);
//        salesLineUI.getPrice().requestFocus();
    }

    public void onSalesInvoiceLineItemQTYChanged(SalesInvoiceLineDetailTableUI salesLineUI) {
        SalesInvoiceLineItem salesInvoiceLineItem = salesLineUI.panelToData();
        salesInvoiceLineItem.calculateLineItem();
        salesLineUI.setDataToPanelIFNotFocused(salesInvoiceLineItem);
//        SalesInvoice currentBusObject = detailView.uiToData();
//        currentBusObject.addOrUpdateLine(salesInvoiceLineItem);
//        currentBusObject.setTotal();
//        detailView.setVisualDataToUI(currentBusObject);
//        salesLineUI.getPrice().requestFocus();
    }

    public void onSalesInvoiceLineItemDocChanged(SalesInvoiceLineDetailTableUI salesLineUI) {
        SalesInvoiceLineItem salesInvoiceLineItem = salesLineUI.panelToData();
        salesInvoiceLineItem.calculateLineItem();
        salesLineUI.setDataToPanelIFNotFocused(salesInvoiceLineItem);
    }

    public JComponent onSalesInvoiceLineItemPriceChanged(SalesInvoiceLineDetailTableUI salesLineUI) {

        SalesInvoiceLineItem salesInvoiceLineItem = salesLineUI.panelToData();
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

        SalesInvoice currentBusObject = detailView.uiToData();
        ///get the current line and update it        

        SalesInvoiceLineItem selectedSL = (SalesInvoiceLineItem) salesLineUI.getSelectedLineObject();
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

    public void onSalesInvoiceLineItemPriceChanged(GridDataLineDetailUI lineDetailUI) {
        SalesInvoiceLineDetailUI salesLineUI = (SalesInvoiceLineDetailUI) lineDetailUI;
        SalesInvoiceLineItem salesInvoiceLineItem = salesLineUI.UIToData();
        salesInvoiceLineItem.calculateLineItem();
           if(salesInvoiceLineItem.getUom() ==null && salesInvoiceLineItem.getSku()!=null){
            salesInvoiceLineItem.setUom(salesInvoiceLineItem.getSku().getItem().getPrimaryUOM());
        }
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

    public void onRemoveLineItem(SalesInvoiceLineItem sil) {
        SalesInvoice si = detailView.uiToData();
        si.calculateTotal();
        detailView.setVisualDataToUI(si);
    }

    public void showFrame(String screenName) {
        if ("SaleInvoiceUIX".equals(screenName)) {
            UIFrame.setVisible(true);
        } else if ("SaleDetailPanelUI".equals(screenName)) {

            UIFrame.setVisible(true);
        }
    }

    public void clearTableUI() {
        SalesInvoice currentBusObject = detailView.uiToData();
        currentBusObject.calculateTotal();
        detailView.setVisualDataToUI(currentBusObject);
    }

    protected boolean isValideEntity() {

        List<SalesInvoiceLineItem> lineItems = currentBusObject.getLineItems();

        if (lineItems == null || lineItems.size() <= 0 ) {
            return false;
        }
        //todo do we have to validate sales line items line by line
        
        if(ISProperties.isCustomerMandotoryForInvoice() && currentBusObject.getCustomer()==null){
             MessageBoxes.infomsg(null,"No customer found" , "Validation errors" );
            return false;
        }

        return true;
    }

    public void preCreate(ArrayList toSave, ArrayList toUpdate, ArrayList toDelete) {
        ((SalesInvoice) currentBusObject).calculateTotal();
        //create customer statement
        //use the invoice as the reciept othervise print reciept separately
//        //make updates to credit account
//        System.out.println("sales invoice level preCreate");
//
//        InventoryJournal  ij = new InventoryJournal();
//        SalesInvoice sales = getCurrentBusObject();
//        ij.setCode(sales.getCode());
//        ij.setDocRefNo(sales.getDocRefNo());
//        ij.setDocumentClass(sales.getClass().getSimpleName());
//        for (SalesInvoiceLineItem lineItem : sales.getLineItems()) {
//            InventoryJournalLine line = new InventoryJournalLine();
//            line.setSku(lineItem.getSku());
//            line.setQty(lineItem.getQty());
//            line.setUom(lineItem.getUom());            
//            ij.addIJLine(line);
//
//        }
//        toSave.add(ij);

    }
     



    

}
