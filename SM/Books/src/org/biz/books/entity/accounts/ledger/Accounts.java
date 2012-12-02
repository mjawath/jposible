/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.books.entity.accounts.ledger;

import javax.persistence.Entity;
import org.biz.entity.BusObj;

/**
 *
 * @author mjawath
 */
@Entity
public class Accounts extends  BusObj{

    private static final long serialVersionUID = 1L;
    private static final String assests = "assests";
    private static final String liablity = "liablity";
    private static final String equity = "equity";
    private static final String expence = "expence";
    private static final String income = "income";
    String primaryAccounts;//assest = liability+equity
    String subAccounts;//assest+expence = liability+equity+income
    String accountsName;
    String accountsNo;

    
    public String getAccountsName() {
        return accountsName;
    }

    public void setAccountsName(String accountsName) {
        this.accountsName = accountsName;
    }

    public String getAccountsNo() {
        return accountsNo;
    }

    public void setAccountsNo(String accountsNo) {
        this.accountsNo = accountsNo;
    }

    public String getPrimaryAccounts() {
        return primaryAccounts;
    }

    public void setPrimaryAccounts(String primaryAccounts) {
        this.primaryAccounts = primaryAccounts;
    }

    public String getSubAccounts() {
        return subAccounts;
    }

    public void setSubAccounts(String subAccounts) {
        this.subAccounts = subAccounts;
    }

    enum primaryAccounts {

        assests, liablity, equity
    }

    enum subAccounts {

        assests, expence, liablity, equity, income
    }
}