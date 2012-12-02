/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PostedInvoicesListUI.java
 *
 * Created on Feb 8, 2012, 10:57:40 PM
 */
package org.biz.erp.inventory.ui;

import java.util.List;
import javax.swing.JPanel;
import org.biz.app.ui.util.TableUtil;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.components.windows.TabPanelUI;

/**
 *
 * @author nnjj
 */
public class InventoryViewUI extends TabPanelUI {

    public InventoryViewUI() {
        initComponents();
    }

    
    public void addToTable(List<SalesInvoice> items) {
        TableUtil.cleardata(tblInvoice);
        if (items == null || items.isEmpty()) {
            return;
        }
        for (SalesInvoice item : items) {
            TableUtil.addrow(tblInvoice, new Object[]{item.getId(),item.getDocdate(), item.getCustomer().getCode()
                    });
        }

        TableUtil.addrow(tblInvoice, new Object[]{TableUtil.newRowID});
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblInvoice = new org.components.controls.CxTable();

        tblInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "code", "Invoice No", "Customer"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblInvoice);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CxTable tblInvoice;
    // End of variables declaration//GEN-END:variables

    public String getTabName() {
        return "Inventory UI ";
    }

    public JPanel getJPanel() {
        return this;
    }
}
