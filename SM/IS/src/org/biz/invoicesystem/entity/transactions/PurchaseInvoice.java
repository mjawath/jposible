/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.transactions;

import org.util.MathUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Staff;
import org.biz.invoicesystem.entity.master.Supplier;

/**
 *
 * @author mjawath
 */
@Entity
public class PurchaseInvoice implements Serializable {

    @Id
    private String id;
    private String refNo;
    private String grnNo;
    private static final long serialVersionUID = 1L;
    @ManyToOne
    Supplier supplier;
    @JoinColumn(name = "purchase_invoice_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<PurchaseInvoiceLineItem> lineItems;
    @ManyToOne
    Staff staff;
    String salesMan;
    String salesManager;
    @OneToOne
    Shop shop;
    

    public Double getAmountRecieved() {
        return amountRecieved;
    }

    public void setAmountRecieved(Double amountRecieved) {
        this.amountRecieved = amountRecieved;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getDiscountpctge() {
        return discountpctge;
    }

    public void setDiscountpctge(Double discountpctge) {
        this.discountpctge = discountpctge;
    }

    public Date getDocdate() {
        return docdate;
    }

    public void setDocdate(Date docdate) {
        this.docdate = docdate;
    }

    public Date getEditeddate() {
        return editeddate;
    }

    public void setEditeddate(Date editeddate) {
        this.editeddate = editeddate;
    }

    public Double getFinalTotal() {
        return finalTotal;
    }

    public void setFinalTotal(Double finalTotal) {
        this.finalTotal = finalTotal;
    }

    public String getGrnNo() {
        return grnNo;
    }

    public void setGrnNo(String grnNo) {
        this.grnNo = grnNo;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getSalesMan() {
        return salesMan;
    }

    public void setSalesMan(String salesMan) {
        this.salesMan = salesMan;
    }

    public String getSalesManager() {
        return salesManager;
    }

    public void setSalesManager(String salesManager) {
        this.salesManager = salesManager;
    }

    public Date getSaveddate() {
        return saveddate;
    }

    public void setSaveddate(Date saveddate) {
        this.saveddate = saveddate;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTaxpctge() {
        return taxpctge;
    }

    public void setTaxpctge(Double taxpctge) {
        this.taxpctge = taxpctge;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
    Double subTotal;
    Double finalTotal;
    Double amountRecieved;
    Double discount;
    Double discountpctge;
    Double tax;
    Double taxpctge;
    Byte type;//should hv final decaltration
    Byte status;//should hv final decaltration
    String remark;
 
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date docdate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date saveddate;
       @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date editeddate;

    public List<PurchaseInvoiceLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<PurchaseInvoiceLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
    

    static public PurchaseInvoice createNewInvoice() {
        PurchaseInvoice sl = new PurchaseInvoice();
        sl.setLineItems(new ArrayList<PurchaseInvoiceLineItem>());
        return sl;
    }
    
     public Double setTotal() {
        Double db = 0d;
        for (PurchaseInvoiceLineItem sl : lineItems) {
            db= MathUtil.add(db, sl.getLineAmount());
        }
        setSubTotal(db);

        
//        db=MathUtil.sub(db, getTexAmount());        
        db=MathUtil.sub(db, getDiscount());
        
        
        setFinalTotal(db);
        Double bal=db;
        bal=MathUtil.sub(bal, 0d);        
        System.out.println("totel  " + bal);
        return bal;

    }
}
