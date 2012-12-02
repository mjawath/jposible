/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.dao.transactions;

import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.transactions.PurchaseInvoice;

/**
 *
 * @author mjawath
 */
public class PurchaseInvoiceDAO extends GenericDAO<PurchaseInvoice>{

    
    public PurchaseInvoiceDAO() {
    setCls(PurchaseInvoice.class);
    }


    public void selectMontlyPurchase(int month){
        String qr="select * from PurchaseInvoice p ";
//         getQuery(qr);
//         getQuery(qr);
    }

    public void selectMontly(){}

}
