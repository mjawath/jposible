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
import org.biz.app.ui.util.Tracer;
import org.biz.entity.BusObj;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Staff;
import org.biz.invoicesystem.entity.master.Supplier;

/**
 *
 * @author mjawath
 */
@Entity
public class PurchaseInvoice extends BusObj {

    private String refNo;
    private String grnNo;
    private static final long serialVersionUID = 1L;
    @ManyToOne
    private Supplier supplier;
    @JoinColumn(name = "purchase_invoice_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<PurchaseInvoiceLineItem> lineItems;
    @ManyToOne
    Staff staff;
    String salesMan;
    String salesManager;
    @ManyToOne
    Shop shop;
    private String code;    
    private Double total;
    private Double subTotal;
    private Double discount;
    private Double discountPer;
    private Double texPer;
    private Double texAmount;
    private Double cashRecieveds;
    

    Double finalTotal;
    Double amountRecieved;
    Double discountpctge;
    Double tax;
    Double taxpctge;
    Byte type;//should hv final decaltration
    Byte status;//should hv final decaltration
    String remark;
 
    
    
 public void calculateTotal() {
//        Double db = 0d;
//        for (SalesInvoiceLineItem sl : lineItems) {
//            db = MathUtil.add(db, sl.getLineAmount());
//        }
//        setSubTotal(db);
//        Tracer.printToOut("Invoice sub totel  " + subTotal);
//        setTotal(db);
//        Tracer.printToOut("Invoice totel  " + total);        
//        Tracer.printToOut("Invoice Tax  " +texPer);
//        setSalesTaxDetail();
//        Tracer.printToOut("Invoice Tax  Amount" + texAmount);        
//        setDiscountDetail();
//        Tracer.printToOut("Invoice Discount  " + discountPer + " "+discount);
//        db = MathUtil.sub(db, texAmount);
//        db = MathUtil.sub(db, discount);
//        setTotal(db);
//        Tracer.printToOut("Invoice totel  " + subTotal);
//        Double bal = db;
//        Tracer.printToOut("Recived  " + cashRecieveds);
//        bal = MathUtil.sub(bal, getCashRecieveds());
//        Tracer.printToOut("Invoice final totel  " + total);        
    }
    
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

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date docdate;

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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getDiscountPer() {
        return discountPer;
    }

    public void setDiscountPer(Double discountPer) {
        this.discountPer = discountPer;
    }

    public Double getTexPer() {
        return texPer;
    }

    public void setTexPer(Double texPer) {
        this.texPer = texPer;
    }

    public Double getTexAmount() {
        return texAmount;
    }

    public void setTexAmount(Double texAmount) {
        this.texAmount = texAmount;
    }

    public Double getCashRecieveds() {
        return cashRecieveds;
    }

    public void setCashRecieveds(Double cashRecieveds) {
        this.cashRecieveds = cashRecieveds;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
     
     public void addOrUpdateLine(PurchaseInvoiceLineItem salesInvoiceLineItem) {
        if (lineItems == null) {
            return;
        }
        if (!lineItems.contains(salesInvoiceLineItem)) {
            lineItems.add(salesInvoiceLineItem);
        }
    }
     
     public synchronized void addOrUpdateLine(PurchaseInvoiceLineItem selectedLine, PurchaseInvoiceLineItem newSalesInvoiceLineItem) {
        if (lineItems == null) {
            lineItems = new ArrayList<>();
            lineItems.add(newSalesInvoiceLineItem);
            return;
        }
        if (!lineItems.contains(selectedLine)) {
            lineItems.add(newSalesInvoiceLineItem);
        } else {
            int index = lineItems.indexOf(selectedLine);
            lineItems.set(index, newSalesInvoiceLineItem);
        }
    }
}
