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

/**dfdf
 *
 * @author d
 */
@Entity
public class ChequeState extends BusObj{
    
    @OneToOne
    Cheques cheques;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateDepositedToBank;
    Byte status;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateFrom; 
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateTo; 
    
}
