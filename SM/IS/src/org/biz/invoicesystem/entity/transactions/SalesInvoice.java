/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.transactions;

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
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Staff;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.util.MathUtil;

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
    private String code;
    private String pekapoo;
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
    private Byte status;

    public SalesInvoice() {
        setLineItems(new ArrayList<SalesInvoiceLineItem>());   
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    
    public byte getStatus() {
        return status;
    }

    public String getPekapoo() {
        return pekapoo;
    }

    public void setPekapoo(String pekapoo) {
        this.pekapoo = pekapoo;
    }

    public void setStatus(Byte status) {
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
    
    public void addLine(SalesInvoiceLineItem salesInvoiceLineItem){
        if(lineItems==null ){
            return;
        }
        lineItems.add(salesInvoiceLineItem);
    }

    public void addOrUpdateLine(SalesInvoiceLineItem salesInvoiceLineItem) {
        if (lineItems == null) {
            return;
        }
        if(!lineItems.contains(salesInvoiceLineItem)){
            lineItems.add(salesInvoiceLineItem);
        }
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

    
    public void calculateTotal() {
        Double db = 0d;
        for (SalesInvoiceLineItem sl : lineItems) {
            db = MathUtil.add(db, sl.getLineAmount());
        }
        setSubTotal(db);
        Tracer.printToOut("Invoice sub totel  " + subTotal);
        setTotal(db);
        Tracer.printToOut("Invoice totel  " + total);        
        Tracer.printToOut("Invoice Tax  " +texPer);
        setSalesTaxDetail();
        Tracer.printToOut("Invoice Tax  Amount" + texAmount);        
        setDiscountDetail();
        Tracer.printToOut("Invoice Discount  " + discountPer + " "+discount);
        db = MathUtil.sub(db, texAmount);
        db = MathUtil.sub(db, discount);
        setTotal(db);
        Tracer.printToOut("Invoice totel  " + subTotal);
        Double bal = db;
        Tracer.printToOut("Recived  " + cashRecieveds);
        bal = MathUtil.sub(bal, getCashRecieveds());
        Tracer.printToOut("Invoice final totel  " + total);        
    }
    
    public Double setTotal() {
        Double db = 0d;
        for (SalesInvoiceLineItem sl : lineItems) {
            db = MathUtil.add(db, sl.getLineAmount());
        }
        setSubTotal(db);
        setTotal(db);
        setSalesTaxDetail();
        setDiscountDetail();
        db = MathUtil.sub(db, texAmount);
        db = MathUtil.sub(db, discount);

        setTotal(db);
        Double bal = db;
        bal = MathUtil.sub(bal, getCashRecieveds());
        Tracer.printToOut("Invoice totel  " + bal);
        return bal;

    }
    
    public Double calculateBalance(){
      return MathUtil.sub( total,cashRecieveds);
    }

    public Double setSalesTaxDetail() {        
          //calculate the tax amount and set it
        Double tax = texPer == null ? 0 : texPer;
        texAmount = MathUtil.multiply(total, (tax / 100));        
        return texAmount;
        
    }
    
    public Double setDiscountDetail() {
        
        Double disc = discountPer ==null?0:discountPer;
        discount = MathUtil.multiply(total, (disc / 100));
        return discount;

    }
}
