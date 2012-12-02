/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.inventory;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import org.biz.entity.BusObj;
import org.biz.invoicesystem.entity.master.Item;

/**
 *
 * @author d
 */
@Entity
public class PosInvoiceJournal extends BusObj {

    private String itemMark;
    @OneToOne
    private Item item;
    private Double Qty;
    private Byte type;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PosInvoiceJournal)) {
            return false;
        }
        PosInvoiceJournal other = (PosInvoiceJournal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.biz.invoicesystem.entity.inventory.PosInvoice[ id=" + id + " ]";
    }
}
