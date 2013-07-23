/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.transactions;

import app.utils.MathUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.biz.app.ui.util.Tracer;
import org.biz.entity.BusObj;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.entity.master.Staff;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.entity.master.Shop;

/**
 *
 * @author mjawath
 */
@Entity
public class SalesInvoice extends BusObj {

    private static final long serialVersionUID = 145454545L;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    private Staff staff;
    @JoinColumn(name = "sales_invoice_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SalesInvoiceLineItem> lineItems;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date docdate;
    @OneToOne
    private Shop shop;
    @OneToOne
    private Warehouse warehouse;
    String docRefNo;
    private String invNo;
    private String Remarks;
    private String notes;
    private Double total;
    private Double subTotal;
    private Double discount;
    private Double discountPer;
    private Double texPer;
    private Double texAmount;
    private Double cashRecieveds;
    private byte status;

    public SalesInvoice() {
        setLineItems(new ArrayList<SalesInvoiceLineItem>());
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setLineItems(List<SalesInvoiceLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Date getDocdate() {
        return docdate;
    }

    public void setDocdate(Date docdate) {
        this.docdate = docdate;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public List<SalesInvoiceLineItem> getLineItems() {
        return lineItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String Remarks) {
        this.Remarks = Remarks;
    }

    public String getDocRefNo() {
        return docRefNo;
    }

    public void setDocRefNo(String docRefNo) {
        this.docRefNo = docRefNo;
    }

    public String getInvNo() {
        return invNo;
    }

    public void setInvNo(String invNo) {
        this.invNo = invNo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Double getCashRecieveds() {
        return cashRecieveds;
    }

    public void setCashRecieveds(Double cashRecieveds) {
        this.cashRecieveds = cashRecieveds;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getDiscountPer() {
        return discountPer;
    }

    public void setDiscountPer(Double discountPer) {
        this.discountPer = discountPer;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getTexAmount() {
        return texAmount;
    }

    public void setTexAmount(Double texAmount) {
        this.texAmount = texAmount;
    }

    public Double getTexPer() {
        return texPer;
    }

    public void setTexPer(Double texPer) {
        this.texPer = texPer;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    static public SalesInvoice createNewInvoice() {
        SalesInvoice sl = new SalesInvoice();
        sl.setLineItems(new ArrayList<SalesInvoiceLineItem>());
        return sl;
    }

    public SalesInvoice createNewInvoicex() {
        SalesInvoice sl = new SalesInvoice();
        setLineItems(new ArrayList<SalesInvoiceLineItem>());
        return sl;
    }

    public Double setTotal() {
        Double db = 0d;
        for (SalesInvoiceLineItem sl : lineItems) {
            db = MathUtil.add(db, sl.getLineAmount());
        }
        setSubTotal(db);


        db = MathUtil.sub(db, getTexAmount());
        db = MathUtil.sub(db, getDiscount());


        setTotal(db);
        Double bal = db;
        bal = MathUtil.sub(bal, getCashRecieveds());
        Tracer.printToOut("Invoice totel  " + bal);
        return bal;

    }
    
    public Double calculateBalance(){
      return MathUtil.sub( total,cashRecieveds);
    }
}
