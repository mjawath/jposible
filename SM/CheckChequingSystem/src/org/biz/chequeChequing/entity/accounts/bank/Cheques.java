/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.chequeChequing.entity.accounts.bank;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.biz.entity.BusObj;

/**
 *
 * @author mjawath
 */
@Entity
public class Cheques extends BusObj {
    private static final long serialVersionUID = 1L;
    
    @OneToOne
    private BankBranch bankBranch;
    private String chequeNumber;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBank;
    private Double amount;
    private String amountInWords;
    private Byte currentstatus;
    
    
    

    public BankBranch getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(BankBranch bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public Date getDateOfBank() {
        return dateOfBank;
    }

    public void setDateOfBank(Date dateOfBank) {
        this.dateOfBank = dateOfBank;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAmountInWords() {
        return amountInWords;
    }

    public void setAmountInWords(String amountInWords) {
        this.amountInWords = amountInWords;
    }
    

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cheques)) {
            return false;
        }
        Cheques other = (Cheques) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.business.accounts.NewEntity[id=" + id + "]";
    }
}
