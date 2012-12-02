/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.chequeChequing.entity.accounts.bank;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import org.biz.entity.BusObj;

/**
 *
 * @author mjawath
 */
@Entity
public class BankBranch extends BusObj {
    private static final long serialVersionUID = 1L;
    String code;

    @OneToOne
    Bank bank;

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


 
}
