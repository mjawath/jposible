/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.erp.inventory.dao;

import java.util.Date;
import java.util.List;
import org.biz.app.ui.util.StringUtility;
import org.biz.dao.service.GenericDAO;
import org.biz.dao.service.CQuery;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.service.inventory.InventoryJournalService;

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
    
    public List getForLastMonthsummery(Date date,String sid,String wid) {
//        String qry="select i ,sum(l.qty)  "
//        + " from InventoryJournal c join c.lines l join l.item i  "
//                + " group by i ";
        /*
         select all line items within the month
         * create mothly summery object
         summery of shops ware houses qty
         * set the warehouse ,item , shop , qty ...etc....
         * 
         * 
         * 
         * 
         String qryx = "select i, ijls.uom ,ijls.qty   "
                + " from InventoryJournal  c left join c.lines ijls  join ijls.item i       ";
         */
       /* String qryx = "select c.shop ,c.warehouse, i, ijls.uom ,sum(ijls.qty)    "
                + " from InventoryJournal  c left join c.lines ijls left join  ijls.item i   "
                + " group by c.shop ,c.warehouse , i , ijls.uom  ";*/

//        String qryx = " select   i, ijls.uom, sum(ijls.qty)    "
//                    + " from InventoryJournal  c left join c.lines ijls left join  ijls.item i   "
//                    +"    "
//                    + " group by  i ,ijls.uom ";
//        
        
        String x= " where   "+ (StringUtility.isEmptyString(wid)?" ":" c.warehouse.id =?1 ") ;
         x +=  !StringUtility.isEmptyString(sid)?" ": " c.shop.id =?2  ";
        x+= " c.createdDate between ?3 and ?4 ";
//        Datea
         
         String qr = " select   i, ijls.uom, sum(ijls.qty)    "
                    + " from InventoryJournal  c left join c.lines ijls left join  ijls.item i   "
                + x
                    + " group by  i ,ijls.uom ";
        //
        List list = executeQuery(qr,new Object[]{date,sid,wid});
       
        return list;
    }
    
    public List getSummery(String sid, String wid) {

        String sel = " select   i, ijls.uom, sum(ijls.qty)    "
                     + " from InventoryJournal  c left join c.lines ijls left join  ijls.item i   ";
        String qr = " group by  i ,ijls.uom ";

        if (!StringUtility.isEmptyString(wid) && !StringUtility.isEmptyString(sid)) {

            sel +=   " where c.shop.id =?1 and  c.warehouse.id =?2  "
                    + qr;
            return executeQuery(sel, new Object[]{sid, wid});

        } else if (!StringUtility.isEmptyString(wid) && StringUtility.isEmptyString(sid)) {
            sel +=   " where   c.warehouse.id =?1  "
                    + qr;
            return executeQuery(sel, new Object[]{wid});

        } else if (StringUtility.isEmptyString(wid) && !StringUtility.isEmptyString(sid)) {
            sel +=   " where   c.shop.id =?1  "
                    + qr;
            return executeQuery(sel, new Object[]{sid});
        }
        sel +=  qr;
        return ExecuteQuery(sel);
        
    }
    //responsible for reutrning a string qury for pagiantion usage
    public CQuery getSummerySql(String sid, String wid) {

        String sel = " select   i, ijls.uom, sum(ijls.qty)    "
                     + " from InventoryJournal  c left join c.lines ijls left join  ijls.item i   ";
        String qr = " group by  i ,ijls.uom ";

        if (!StringUtility.isEmptyString(wid) && !StringUtility.isEmptyString(sid)) {

            sel +=   " where c.shop.id =?1 and  c.warehouse.id =?2  "
                    + qr;
            
            return    getQuery(sel,wid,sid);

        } else if (!StringUtility.isEmptyString(wid) && StringUtility.isEmptyString(sid)) {
            sel +=   " where   c.warehouse.id =?1  "
                    + qr;
            return getQuery(sel,wid);

        } else if (StringUtility.isEmptyString(wid) && !StringUtility.isEmptyString(sid)) {
            sel +=   " where   c.shop.id =?1  "
                    + qr;
            return getQuery(sel, sid);
        }
         
        return getQuery(sel, "");
        
        
    }

    
    public InventoryJournal refDoc(String refcode,String documentType) {
        String qry="select i   from InventoryJournal c  where c.refCode="+refcode+" and c.documentType="+documentType+" ";
        InventoryJournal ij=ExecuteQuerySR(qry);
        return ij;
    }
    
    public static void main(String[] args) {
        InventoryJournalService ijs=new InventoryJournalService();
//        List list= ijs.getDao().getSummery(new Date() );
    }

}
