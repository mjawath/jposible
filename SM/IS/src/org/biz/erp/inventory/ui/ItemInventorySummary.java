/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ItemInventorySummary.java
 *
 * Created on Jun 28, 2011, 4:50:00 PM
 */
package org.biz.erp.inventory.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.biz.app.ui.util.TableUtil;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.entity.inventory.InventoryMonthlySummery;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.transactions.PurchaseInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.service.inventory.InventoryJournalService;
import org.biz.invoicesystem.service.inventory.InventoryMonthlySummeryService;
import org.biz.invoicesystem.service.transactions.PurchaseInvoiceService;
import org.biz.invoicesystem.service.transactions.SalesInvoiceService;
import org.components.windows.TabPanelUI;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

/**
 *
 * @author Administrator
 */
public class ItemInventorySummary extends TabPanelUI {

    /** Creates new form ItemInventorySummary */
    public ItemInventorySummary() {
        initComponents();
        init();
    }

    @Override
    public void init() {
        selectitem();
    }

    public void selectitem() {

        //select this month purchases
        SalesInvoiceService salesinvService = new SalesInvoiceService();
        List<PurchaseInvoice> purchaseInvoices = new ArrayList<PurchaseInvoice>();
        PurchaseInvoiceService purinvService = new PurchaseInvoiceService();
        purchaseInvoices = purinvService.getDao().getAll();
        //select this month sales
        List<SalesInvoice> salesInvoices = new ArrayList<SalesInvoice>();
        salesInvoices = salesinvService.getDao().getAll();

        HashMap<String, Item> hashMap = new HashMap<String, Item>();
        //get all item from transaction to  hash map from db and get the summery.....

        //get last month inventorymonthly summery  journal adjestment / balance
        summeryService = new InventoryMonthlySummeryService();
        List<InventoryMonthlySummery> monthlySummerys = summeryService.getDao().getAll();//get last monthly
        //

    }
    InventoryMonthlySummeryService summeryService;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        cxTable1 = new org.components.controls.CxTable();
        cDatePicker1 = new org.components.controls.CDatePicker();
        cButton1 = new org.components.controls.CButton();
        cButton2 = new org.components.controls.CButton();
        cLabel1 = new org.components.controls.CLabel();
        tt = new org.components.controls.CTextField();

        cxTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Item Code", "Item Name", "In hand", "On Sales Order", "On Sales Invoice", "On Purchase Order", "On Purchase Invoice"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.Long.class, java.lang.Long.class, java.lang.Long.class, java.lang.Long.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(cxTable1);

        cButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton1ActionPerformed(evt);
            }
        });

        cButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton2ActionPerformed(evt);
            }
        });

        cLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cLabel1.setBackground(new java.awt.Color(255, 102, 0));
        cLabel1.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(cDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(cButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tt, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton1ActionPerformed

        //get last month
        //int lastmonth =summeryService.getDao().getLastMonth();
//        List<InventoryMonthlySummery > monthlySummerys= summeryService.getDao().getForLastMonth(new Date());//get last monthly
        List is = new InventoryJournalService().getDao().getForLastMonthsummery(new Date());

        TableUtil.cleardata(cxTable1);
        for (Object obj : is) {
            Object[] objx = (Object[]) obj;
            Item item = (Item) objx[0];
            TableUtil.addrow(cxTable1, new Object[]{item.getCode(), objx[1]});
        }
    }//GEN-LAST:event_cButton1ActionPerformed

    private void cButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton2ActionPerformed
//Create the barcode bean
        File outputFile = null;
        OutputStream out = null;
        try {

            Code39Bean bean = new Code39Bean();
            final int dpi = 150;
            //Configure the barcode generator
            bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar
            //width exactly one pixel
            bean.setWideFactor(3);
            bean.doQuietZone(false);
            //Open output file
            outputFile = new File(System.currentTimeMillis() + "out.png");
            out = new FileOutputStream(outputFile);

            //Set up the canvas provider for monochrome PNG output
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            //Generate the barcode
            bean.generateBarcode(canvas, tt.getText());
            cLabel1.setIcon(new ImageIcon(canvas.getBufferedImage()));
            //Signal end of generation
            canvas.finish();
        } catch (Exception e) {
        } finally {
            try {
                out.close();
            } catch (Exception ex) {
            }
        }
    }//GEN-LAST:event_cButton2ActionPerformed

    public void addToTable(List<InventoryJournal> ijs) {


        for (InventoryJournal inventoryJournal : ijs) {
            List<InventoryJournalLine> ijls = inventoryJournal.getLines();
            if (ijls != null) {
                for (InventoryJournalLine inventoryJournalLine : ijls) {
                }
            }
        }

    }

    public void addToTable(InventoryJournalLine item) {
        TableUtil.addrow(cxTable1, new Object[]{item.getItem().getCode(), item.getQty()});
    }

    @Override
    public String getTabName() {
        return "Item Summery";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CButton cButton2;
    private org.components.controls.CDatePicker cDatePicker1;
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CxTable cxTable1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CTextField tt;
    // End of variables declaration//GEN-END:variables
}
