 
package org.biz.invoicesystem.dao.master;

import java.util.List;
import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.master.StockLedger;
 
public class StockDao extends GenericDAO<StockLedger>{

    
    /*this dao created by zumry on 8-1-2012 
    this is main dao connecting with all the stock related entities
     * and calculate stock at the time..
     * 
     
     */
    public Double countItemStock(String itemid)throws Exception{
    Double dd=0.0;
        try {
      List<Object[]> lst=ExecuteNativeQuery("SELECT SUM(s.sold),SUM(s.inHand) From StockLedger s Where s.itemid='"+itemid+"'");
            for (Object o : lst) {
           Object [] oo=(Object []) o;     
           Double sold=(Double)oo[0];
      Double inHand=(Double)oo[1];
      
      dd=sold-inHand;     
            }
      
      
      
        } catch (Exception e) {
        e.printStackTrace();
        throw e;
        }
        return dd;
    }
      
    
    
    
}
