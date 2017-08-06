/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.ui.list.master;

import java.util.Arrays;
import org.components.parent.controls.editors.SearchAttributeModel;
import org.components.windows.SearchQueryUIPanel;

/**
 *
 * @author jawa
 */
public class SupplierSEUI extends SearchQueryUIPanel {

    /**
     * Creates new form SupplierSEUI
     */
    public SupplierSEUI() {
        initComponents();
    }


    @Override
    protected void init() {        
//        UIType = Listview_searchUIType;
        super.init();
        initComponents();
        
        tAttribute.setAttribute("description");
        tAttribute.setCollection(Arrays.asList(new SearchAttributeModel[]{
            new SearchAttributeModel("Code", "code"),
            new SearchAttributeModel("Customer Name", "customerName"),
            new SearchAttributeModel("Mobile", "mobile")
        }));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ttSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tbtnSearch = new javax.swing.JButton();
        tchkselect = new org.components.controls.CCheckBox();
        tAttribute = new org.components.controls.CComboBox();

        jLabel1.setText("Search by Customer Code /Name ");

        tbtnSearch.setText("Search");
        tbtnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtnSearchActionPerformed(evt);
            }
        });

        tchkselect.setText("Start");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(ttSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tchkselect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tbtnSearch)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ttSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(tbtnSearch)
                    .addComponent(tchkselect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnSearchActionPerformed

        ((SupplierController)controller).executeSearchForCustom();

    }//GEN-LAST:event_tbtnSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private org.components.controls.CComboBox tAttribute;
    private javax.swing.JButton tbtnSearch;
    private org.components.controls.CCheckBox tchkselect;
    private javax.swing.JTextField ttSearch;
    // End of variables declaration//GEN-END:variables
}
