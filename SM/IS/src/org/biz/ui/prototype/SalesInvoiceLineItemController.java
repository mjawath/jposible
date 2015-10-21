/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.ui.prototype;

import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;

/**
 *
 * @author user
 */
public class SalesInvoiceLineItemController {
    
    private SalesInvoiceLineItem salesInvoiceLineItem;
    
    public SalesInvoiceLineItemController() {        
        salesInvoiceLineItem = new SalesInvoiceLineItem();
    }

    public void setQty(Double qty) {
       salesInvoiceLineItem.setQty(qty);
       salesInvoiceLineItem.calculateLineItem();        
    }

    public void setPrice(Double qty) {
        salesInvoiceLineItem.setPrice(qty);
        salesInvoiceLineItem.calculateLineItem();
        
    }
    

    public SalesInvoiceLineItem getSalesInvoiceLineItem() {
        return salesInvoiceLineItem;
    }

    public void setSalesInvoiceLineItem(SalesInvoiceLineItem sili) {
        this.salesInvoiceLineItem = sili;
    }

}
