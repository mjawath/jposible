/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.dao.transactions;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;

/**
 *
 * @author mjawath
 */
public class SalesInvoiceDAO extends GenericDAO<SalesInvoice>{
    
    public SalesInvoiceDAO() {
        setCls(SalesInvoice.class);
    }

    public List<SalesInvoice> getByDay(Date date){
          String qr = "select  c from SalesInvoice c  where c.saveddate = ?1";
//          Query qur=   getQuery(qr);
//          qur.setParameter(1,date,TemporalType.DATE);
          List<SalesInvoice> sb =  ExecuteQuery(qr);
          return sb;
    }

    public void delete(String id){
        EntityManager em=null;
        try {
        String doc=SalesInvoice.class.getSimpleName();
        SalesInvoice invoice = new SalesInvoice();
        invoice.setId(id);
//        em= createEmNew();
        String qry="select c    from InventoryJournal c  where c.refCode='"+id+"' and c.documentType='"+doc+"' ";
        em.getTransaction().begin();
        em.remove(em.merge(invoice));
        Query query =em.createQuery(qry);
        InventoryJournal ij= (InventoryJournal) query.getSingleResult();
        em.remove(em.merge(ij));
        em.getTransaction().commit();    
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Deleteion error ..");
        }finally{
            if (em!=null) {
                em.close();
            }
        }

    }

    public void updateEV(SalesInvoice invoice,InventoryJournal uij){
        EntityManager em=null;
        try {
//        em= createEmNew();        
        em.getTransaction().begin();        
        
         String qry="select c    from InventoryJournal c  where c.refCode='"+invoice.getId()+"' and c.documentType='"+SalesInvoice.class.getSimpleName()+"' ";
         Query query =em.createQuery(qry);
        InventoryJournal ij= (InventoryJournal) query.getSingleResult();        
        em.remove(ij);
        em.merge(uij);
        em.merge(invoice);
        em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Deleteion error ..");
        }finally{
            if (em!=null) {
                em.close();
            }
        }

    }
}
