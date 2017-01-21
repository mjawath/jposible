/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SalesLineItemPanelV3.java
 *
 * Created on Sep 3, 2012, 9:26:33 AM
 */
package org.biz.invoicesystem.ui.transactions.components;

import com.components.custom.ActionTask;
import invoicingsystem.LineItemPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;
import org.biz.app.ui.util.UIEty;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.UOM;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;

/**
 *
 * @author d
 */
public class SalesLineItemPanelV3 extends LineItemPanel {

    /** Creates new form SalesLineItemPanelV3 */
    public SalesLineItemPanelV3() {
        initComponents();
    }

    protected SalesInvoiceLineItem salesline;
    
    private ISalesLineItemController controller;

    public SalesLineItemPanelV3(JFrame jf) {
        super(jf);
        initComponents();
        init();

    }
    public void init() {
      

        tqty.addaction(0, new ActionTask() {

            @Override
            public boolean action() {
                lineItemLogic();
                return super.action();
            }
        });
        tprice.addaction(0, new ActionTask() {

            public boolean action() {
                          lineItemLogic();
                lineAddAction();
                return super.action();
            }
        });

        tprice.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {

                lineItemLogic();
            }

        });

        tqty.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                lineItemLogic();

            }
        });

         titemcode.setActionActionTask(new ActionTask() {
            @Override
            public void actionCall() {
             itemAction();
            }});
         titemcode.setSearchActionTask(new ActionTask() {

            @Override
            public void actionCall(Object st) {
            List lst= itemSearch(st.toString());
            titemcode.getPagedPopUpPanel().setList(lst);
            }}
                 );

        titemcode.setPropertiesEL(new String[]{"id", "code", "description"});
        titemcode.setTitle(new String[]{"id", "Code", "Description"});
        titemcode.setSelectedProperty("code");


        salesline=new SalesInvoiceLineItem();

        addToFocus(titemcode);
        addToFocus(tdescription);
        addToFocus(tqty);
        addToFocus(tunit);
        addToFocus(tprice);

    }
 /**
     * Creates new form LineItemPanel
     */

    
    public void lineItemLogic() {
        panelToEty(salesline);
        salesline.calculateLineItem();
        etyToPanel(salesline);// TODO : change this according to the user seleection
        //provide tseparate method for line logic
    }
 public SalesInvoiceLineItem getSalesline() {
        return salesline;
    }

    public void setSalesline(SalesInvoiceLineItem salesline) {
        this.salesline = salesline;
    }

    public UOM getUnit() {
        return (UOM) tunit.getSelectedItem();
    }

    public SalesInvoiceLineItem panelToEty(SalesInvoiceLineItem salesline) {


        salesline.setQty(UIEty.tcToDouble(tqty));
        salesline.setDescription(UIEty.tcToStr(tdescription));
        salesline.setPrice(UIEty.tcToDouble(tprice));
        salesline.setLineAmount(UIEty.tcToDouble(tlinetotal));
        salesline.setItem(titemcode.getSelectedObject());
        //get the uom//         salesline.getItem()
        UOM uom = (UOM) tunit.getSelectedItem();
        salesline.setUom(uom);
        
        //ui to ety ..
        return salesline;
    }

    public List itemSearch(String qry) {
                return null;
    }

    public void itemAction() {
                SalesInvoiceLineItem sili = this.getSalesline();
                Item item = titemcode.getSelectedObject();
                loadUnit(item);
                sili.setItem(item);
                sili.setDescription(item.getDescription());
                //get sales price for pos
                sili.setPrice(item.getSalesPrice());
//                lineItemPanel.panelToEty(seil);
                this.etyToPanel(sili);
//                addsales(sili);
                setSalesline(sili);
                lineItemLogic();


    }
    public void selectEty() {
        salesline=(SalesInvoiceLineItem) jt.getSelectedObject();
        clear();
        selectedEtyToPanel();
    }

    public void loadUnit(List it) {

        tunit.setCollection(it);
    }

    public void loadUnit(Item it) {
        if (it != null) {
            loadUnit(it.getUoms());
        } else {
            loadUnit(Collections.emptyList());
        }
    }

    public void getLineItem() {

        salesline = (SalesInvoiceLineItem) super.salesline;

    }

    public void etyToPanel(SalesInvoiceLineItem salesline) {
        Item it = salesline.getItem();
        if (it != null) {
            UIEty.objToUi(titemcode, it.getCode());
            UIEty.objToUi(tdescription, salesline.getDescription());
//
        } else {
            UIEty.objToUi(titemcode, "");
            UIEty.objToUi(tdescription, "");
        }

        if (!tqty.hasFocus()) {
            UIEty.objToUi(tqty, salesline.getQty());
        }
        if (!tdescription.hasFocus()) {
            UIEty.objToUi(tdescription, salesline.getDescription());
        }
        if (!tlinetotal.hasFocus()) {
            UIEty.objToUi(tlinetotal, salesline.getLineAmount());
        }
        if (!tprice.hasFocus()) {
            UIEty.objToUi(tprice, salesline.getPrice());
        }

    }

    public void selectedEtyToPanel() {
        if(salesline==null)return;
        Item it = salesline.getItem();
        if (it != null) {
            titemcode.setTextItem(it.getCode());
//
        } else {
            titemcode.setTextItem("");
        }
        UIEty.objToUi(tqty, salesline.getQty());
        UIEty.objToUi(tdescription, salesline.getDescription());
        UIEty.objToUi(tlinetotal, salesline.getLineAmount());
        UIEty.objToUi(tprice, salesline.getPrice());

    }

    public void lineAddAction() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        tdescription = new org.components.controls.CTextField();
        tqty = new org.components.controls.CTextField();
        tprice = new org.components.controls.CTextField();
        tlinetotal = new org.components.controls.CTextField();
        titemcode = new com.components.custom.TextFieldWithPopUP<Item>();
        tunit = new org.components.controls.CComboBox<UOM>();

        tunit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tunitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titemcode, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tdescription, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(tqty, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tunit, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tprice, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(tlinetotal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titemcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tdescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tunit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tlinetotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tunitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tunitActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_tunitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private org.components.controls.CTextField tdescription;
    private com.components.custom.TextFieldWithPopUP<Item> titemcode;
    private org.components.controls.CTextField tlinetotal;
    private org.components.controls.CTextField tprice;
    private org.components.controls.CTextField tqty;
    private org.components.controls.CComboBox<UOM> tunit;
    // End of variables declaration//GEN-END:variables
}
