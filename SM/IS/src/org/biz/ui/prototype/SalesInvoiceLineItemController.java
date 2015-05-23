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

    private ItemController itemController;
    private SalesInvoiceLineItem sili;
    
    public SalesInvoiceLineItemController() {
        
        sili = new SalesInvoiceLineItem();
    }

    public void setQty(Double qty) {
       sili.setQty(qty);
       sili.calculateLineItem();
        
    }

    public void setPrice(Double qty) {
        sili.setPrice(qty);
        sili.calculateLineItem();
        
    }
    

    public SalesInvoiceLineItem getSili() {
        return sili;
    }

    public void setSili(SalesInvoiceLineItem sili) {
        this.sili = sili;
    }

}
