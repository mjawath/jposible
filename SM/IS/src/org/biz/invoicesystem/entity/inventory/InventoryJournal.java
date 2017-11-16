/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.inventory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.biz.entity.BusObj;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;

/**
 *
 * @author Admin
 */
@Entity
public class InventoryJournal  extends BusObj  {

    public  static final long serialVersionUID = 1L;
    public  static final Byte pos_Invoice =0;
    public  static final Byte sales_Invoice = 1;
    public  static final Byte Item_In = 0;
    public  static final Byte Item_Out = 1;
    
    public static final String SALES = "SalesInvoice";
    public static final String PURCHASE = "PurchaseInvoice";
    public static final String ADJUSTMENT = "InventoryAdjustment";
//    public static final String SALES = "SalesInvoice";

    

    
    
    private String refEntityID; //this should be turned into id


    private String code; //this should be turned into id
    private Byte documentType;//invoice //transferorder//begbalance//adjestments
    private String documentClass;//classs type of document
    private String refCode;//reference document ids
    @JoinColumn(name = "inv_id")    
    @OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
    private List<InventoryJournalLine> lines;
    private Byte  inOrOut; // to represent the state of  inventory entry in a top level 

    
    public Byte getInOrOut() {
        return inOrOut;
    }

    public void setInOrOut(Byte inOrOut) {
        this.inOrOut = inOrOut;
    }
    @OneToOne
    private Shop shop;
    @OneToOne
    private Warehouse warehouse;
    
    String docType;
    String docRefNo;
    
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date entryDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date modifiedDate;
    

    
    public void addIJLine(InventoryJournalLine ij){
    if(lines==null){
    createInvJouLines();
    }
    
    lines.add(ij);
    }
    public boolean validateLineItem(){
    return true;
    }
    
    public Byte getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Byte documentType) {
        this.documentType = documentType;
    }

    public String getDocRefNo() {
        return docRefNo;
    }

    public void setDocRefNo(String docRefNo) {
        this.docRefNo = docRefNo;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocumentClass() {
        return documentClass;
    }

    public void setDocumentClass(String documentClass) {
        this.documentClass = documentClass;
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
 

    public List<InventoryJournalLine> getLines() {
        return lines;
    }

    public void setLines(List<InventoryJournalLine> lines) {
        this.lines = lines;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    /**
     * date of the document ,should be named as documentDate
     * @param entryDate 
     */
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }
    
    public void setTransactionOutType(){
        for (InventoryJournalLine in : lines) {
            if(in!=null && in.getQty()!=null){
                in.setQty(in.getQty()<=0?in.getQty():-in.getQty());
            }
        }
        inOrOut=Item_Out;
    }

    public void setTransactionLinePlus(){
        for (InventoryJournalLine in : lines) {
            if (in != null && in.getQty()!=null && in.getQty() <= 0) {
                in.setQty(-in.getQty());
            }
        }

    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        //// TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventoryJournalLine)) {
            return false;
        }
        InventoryJournal other = (InventoryJournal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "org.biz.invoicesystem.entity.master.InventoryJournal[id=" + id + "]";
    }

    private void createInvJouLines() {
        if(lines==null){
        lines = new ArrayList<InventoryJournalLine>();        
        return;
        }
        lines.clear();
        
    }

    @Override
    public void setDepententEntitiesIDs() {
        setLineID(lines);
    }
    
    
        public String getRefEntityID() {
        return refEntityID;
    }

    public void setRefEntityID(String refEntityID) {
        this.refEntityID = refEntityID;
    }
    
    public synchronized void addOrUpdateLine(InventoryJournalLine selectedLine, InventoryJournalLine newSalesInvoiceLineItem) {
        if (lines == null) {
            lines = new ArrayList<>();
            lines.add(newSalesInvoiceLineItem);
            return;
        }
        if (!lines.contains(selectedLine)) {
            lines.add(newSalesInvoiceLineItem);
        } else {
            int index = lines.indexOf(selectedLine);
            lines.set(index, newSalesInvoiceLineItem);
        }
    }
    
}
/*

  top level implementation of the inventory .
 * as a journal so that data mining can be eased 


* item mark is used to represents the
* sku for perticular item
 */


 enum TransactionType{
    
    
    SALESINVOICE(InventoryJournal.SALES),PURCHASEINVOICE(InventoryJournal.PURCHASE),ADJUSTMENT(InventoryJournal.ADJUSTMENT);
    
    private String value;

    private TransactionType(String value) {
        this.value = value;
    }
    
    
    public boolean isEqual(String pass) {
         if(pass == null){
         return false;
         }// check is not needed because name.equals(null) returns false 
        return value.equalsIgnoreCase(pass);
    }

    public String toString() {
       return this.value;
    }
    
    

    
}