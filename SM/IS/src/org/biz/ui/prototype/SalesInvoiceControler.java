/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.ui.prototype;

import java.util.List;
import org.biz.app.ui.util.QueryManager;
import org.biz.invoicesystem.service.transactions.SalesInvoiceService;

/**
 *
 * @author user
 */
public class SalesInvoiceControler {

    private SalesInvoiceService salesService;

    private QueryManager qmForSearch = new QueryManager();
        
    public SalesInvoiceControler() {
        salesService = new SalesInvoiceService();
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
    

    public void setQty(Double qty) {
    }

    public long getCountByCodeLike(String code) {        
        return salesService.getCountByCodeLike(code) ;
    }

    public List getByCodeLike(int page,String code) {
        return salesService.getByCodeLike(code);
    }
    
    

}
