/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.books.entity.accounts.ledger;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.biz.entity.BusObj;

/**
 *
 *
 * @author nano
 *
 * this class represents the journal line item 
 */
@Entity
public class TransactionEvent extends BusObj {

    @OneToOne
    private Accounts accounts;   
    private BigDecimal cr;
    private BigDecimal dr;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateTrx;
    private String description;
    private String referceDoc;

    @OneToOne
    JournelEntry journelEntry;

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransactionEvent)) {
            return false;
        }
        TransactionEvent other = (TransactionEvent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Accounts.TransactionEntry[id=" + id + "]";
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public BigDecimal getCr() {
        return cr;
    }

    public void setCr(BigDecimal cr) {
        this.cr = cr;
    }

    public BigDecimal getDr() {
        return dr;
    }

    public void setDr(BigDecimal dr) {
        this.dr = dr;
    }

    public Date getDateTrx() {
        return dateTrx;
    }

    public void setDateTrx(Date dateTrx) {
        this.dateTrx = dateTrx;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReferceDoc() {
        return referceDoc;
    }

    public void setReferceDoc(String referceDoc) {
        this.referceDoc = referceDoc;
    }

    public JournelEntry getJournelEntry() {
        return journelEntry;
    }

    public void setJournelEntry(JournelEntry journelEntry) {
        this.journelEntry = journelEntry;
    }
  
}
