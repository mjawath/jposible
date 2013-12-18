/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.erp.inventory.dao;

import java.util.Date;
import java.util.List;
import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;

/**
 *
 * @author mjawath
 */
public class InventoryJournalDAO extends GenericDAO<InventoryJournal>{

    
    public InventoryJournalDAO() {
    setCls(InventoryJournal.class);
    }
    
    public List<InventoryJournal> getForLastMonth(Date date) {
        String qry="select c from InventoryJournal c where c.summeriesedDate= ?1";
        List<InventoryJournal> list=ExecuteQuery(qry);
        return list;
    }
    
    public List getForLastMonthsummery(Date date) {
        String qry="select i ,sum(l.qty)  "
        + " from InventoryJournal c join c.lines l join l.item i  "
                + " group by i ";
        /*
         select all line items within the month
         * create mothly summery object
         summery of shops ware houses qty
         * set the warehouse ,item , shop , qty ...etc....
         * 
         * 
         * 
         * 
         
         */
        String qryx = "select i ,sum(l.qty)  "
                + " from InventoryJournalLine ijl ,InventoryJournal  c join   join c.lines l join l.item i  "
                + " group by i ";

        List list=ExecuteQuery(qry);
        
        return list;
    }
    
    public InventoryJournal refDoc(String refcode,String documentType) {
        String qry="select i   from InventoryJournal c  where c.refCode="+refcode+" and c.documentType="+documentType+" ";
        InventoryJournal ij=ExecuteQuerySR(qry);
        return ij;
    }
    

}
