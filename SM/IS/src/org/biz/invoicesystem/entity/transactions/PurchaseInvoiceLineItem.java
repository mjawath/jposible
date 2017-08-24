/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.transactions;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author mjawath
 */
@Entity
@Table(name="LINEITEM")
public class PurchaseInvoiceLineItem extends SalesInvoiceLineItem  {

}
