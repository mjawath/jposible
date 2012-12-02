/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.books.entity.accounts.ledger;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import org.biz.entity.BusObj;

/**
 *
 * @author nano
 */
@Entity
public class JournelEntry extends BusObj {
    private static final long serialVersionUID = 1L;
    
    @JoinColumn(name = "jour_id")
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)//atleast two events needed for a perticular transection events
    List<TransactionEvent> transactionEntrys;//this is the journal line item
    String explanation;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date dateTrxEvent;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JournelEntry)) {
            return false;
        }
        JournelEntry other = (JournelEntry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Accounts.Transaction[id=" + id + "]";
    }
  
}
