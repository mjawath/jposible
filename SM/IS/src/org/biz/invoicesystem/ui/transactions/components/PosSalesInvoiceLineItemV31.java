/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PosSalesInvoiceLineItemV3.java
 *
 * Created on Sep 3, 2012, 4:40:06 PM
 */
package org.biz.invoicesystem.ui.transactions.components;

import app.utils.MathUtil;
import com.components.custom.ActionTask;
import com.components.custom.TextFieldWithPopUP;
import invoicingsystem.LineItemPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import org.biz.app.ui.util.UIEty;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;

/**
 *
 * @author d
 */
public class PosSalesInvoiceLineItemV31 extends LineItemPanel {

    protected SalesInvoiceLineItem salesline;

    /**
     * Creates new form LineItemPanel
     */
    public PosSalesInvoiceLineItemV31() {
        super();
        initComponents();
        init();


//               Object[][] xx = {{titemcode, tblInvoice}, {tsalesman, ttax}, {ttax, tsubtotal}
//        };
//        ComponentFactory.addKeyAction(xx);
        titemcode.addaction(4, new ActionTask() {
            @Override
            public boolean action() {
                return true;
            }
        });


//        tunit.setActionTask(new ActionTask(tprice));

        tprice.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                lineItemLogic();
            }
        });

    }

    public void init() {

        salesline = new SalesInvoiceLineItem();

        tprice.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                lineItemLogic();

            }
        });




        titemcode.addaction(2, new ActionTask() {
            public boolean action() {
                lineItemLogic();
                return super.action();
            }
        });
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
                PosSalesInvoiceLineItemV31.this.lineAddAction();
                return super.action();
            }
        });




        titemcode.setActionActionTask(new ActionTask() {
            @Override
            public void actionCall() {
                itemAction();
            }
        });
        titemcode.setSearchActionTask(new ActionTask() {
            @Override
            public void actionCall(Object st) {
                List lst = itemSearch(st.toString());
                titemcode.getPagedPopUpPanel().setList(lst);
            }
        });

        titemcode.setPropertiesEL(new String[]{"id", "code", "description"});
        titemcode.setTitle(new String[]{"id", "Code", "Description"});
        titemcode.setSelectedProperty("code");

        addToFocus(titemcode);
        addToFocus(tqty);
        addToFocus(tprice);
        addToFocus(titemcode);

    }

    public List itemSearch(String qry) {
        return null;
    }

    public void itemAction() {
        SalesInvoiceLineItem sili = this.getSalesline();
//        etyToPanel(sales);
        //validate here
        Item item = this.getItemcode().getSelectedObject();
        sili.setItem(item);
        sili.setDescription(item.getDescription());
        //get sales price for pos
        sili.setPrice(item.getSalesPrice());
//                lineItemPanel.panelToEty(seil);
        this.etyToPanel(sili);
//                addsales(sili);

        setSalesline(sili);//will the table
        lineItemLogic();//this will changed to get the panels current changed line item

    }

    public void lineItemLogic() {
        panelToEty(salesline);
        if (salesline.getItem() != null) {
            salesline.setLineAmount(MathUtil.multiply(salesline.getQty(), salesline.getPrice()));
        }
        etyToPanel(salesline);// TODO : change this according to the user seleection
        //provide tseparate method for line logic
    }

    public TextFieldWithPopUP<Item> getItemcode() {
        return titemcode;
    }

    public SalesInvoiceLineItem panelToEty(SalesInvoiceLineItem salesline) {

        salesline.setItem(titemcode.getSelectedObject());
        salesline.setQty(UIEty.tcToDouble(tqty));
        salesline.setDescription(UIEty.tcToStr(tdescription));
        salesline.setPrice(UIEty.tcToDouble(tprice));
        salesline.setLineAmount(UIEty.tcToDouble(tlinetotal));
        return salesline;
    }

    public void getLineItem() {
        salesline = (SalesInvoiceLineItem) super.salesline;
    }

    public void etyToPanel(SalesInvoiceLineItem salesline) {
        Item it = salesline.getItem();
        if (it != null) {
            titemcode.setSelectedObject(it);
            titemcode.setTextItem(it.getCode());
            UIEty.objToUi(tdescription, salesline.getDescription());
//
        } else {
            titemcode.setSelectedObject(null);
            titemcode.setTextItem("");
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

    public SalesInvoiceLineItem getSalesline() {
        return salesline;
    }

    public void setSalesline(SalesInvoiceLineItem salesline) {
        this.salesline = salesline;
    }

    public void selectedEtyToPanel() {
        if (salesline == null) {
            return;
        }
        Item it = salesline.getItem();
        if (it != null) {
            titemcode.setSelectedObject(it);
            titemcode.setTextItem(it.getCode());
            UIEty.objToUi(tdescription, salesline.getDescription());

//
        } else {
            titemcode.setSelectedObject(null);
            titemcode.setTextItem("");
            UIEty.objToUi(tdescription, "");
        }
        UIEty.objToUi(tqty, salesline.getQty());
        UIEty.objToUi(tdescription, salesline.getDescription());
        UIEty.objToUi(tlinetotal, salesline.getLineAmount());
        UIEty.objToUi(tprice, salesline.getPrice());

    }

    public void lineAddAction() {
    }

    public void selectEty() {
        salesline = jt.getSelectedObject(SalesInvoiceLineItem.class);
        selectedEtyToPanel();
    }

    public void clear() {
        tdescription.setText("");
        titemcode.setTextItem("");
        tlinetotal.setText("");
        tprice.setText("");
        tqty.setText("");
        titemcode.clear();
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
                .addGap(122, 122, 122)
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
                    .addComponent(tprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tlinetotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private org.components.controls.CTextField tdescription;
    private com.components.custom.TextFieldWithPopUP<Item> titemcode;
    private org.components.controls.CTextField tlinetotal;
    private org.components.controls.CTextField tprice;
    private org.components.controls.CTextField tqty;
    // End of variables declaration//GEN-END:variables
}
