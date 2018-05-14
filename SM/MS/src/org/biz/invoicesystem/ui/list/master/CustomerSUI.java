/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.ui.list.master;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.biz.app.ui.util.StringUtility;
import org.components.parent.controls.editors.SearchAttributeModel;
import org.components.windows.SearchCriteria;
import org.components.windows.SearchQueryUIPanel;

/**
 *
 * @author Jawad
 */
public class CustomerSUI extends  SearchQueryUIPanel {
    /**
     * Creates new form CustomerSUI
     */
    public CustomerSUI() {
//        super();
//        initComponents();
    }

    @Override
    protected void init() {        
//        UIType = Listview_searchUIType;
        initComponents();
        super.init();
      
        tAttribute.setAttribute("description");
        tAttribute.setCollection(Arrays.asList(new SearchAttributeModel[]{
            new SearchAttributeModel("Code", "code"),
            new SearchAttributeModel("Customer Name", "customerName"),
            new SearchAttributeModel("Mobile", "mobile")
        }));
    }

    public List<SearchAttributeModel> getQueryParameterMap() {
        List<SearchAttributeModel> p = new ArrayList<>();
        String text = ttSearch.getText();
        if(StringUtility.isEmptyString(text))return p;
        Object selectedItem = tAttribute.getSelectedItem();
        if(selectedItem!=null){
            p.add(new SearchAttributeModel(((SearchAttributeModel)selectedItem).getAttribute()," like "+text + "%"));
        }
        return p;
    }

    public String getSearchTextFieldValue(){
        return ttSearch.getText();
    }
    
    public String getAttribute() {
        if(tAttribute==null || tAttribute.getSelectedItem()==null)return "code";
        return ((SearchAttributeModel)tAttribute.getSelectedItem()).getAttribute();
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        ttSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tbtnSearch = new javax.swing.JButton();
        tchkselect = new org.components.controls.CCheckBox();
        tAttribute = new org.components.controls.CComboBox();
        tload = new org.components.controls.CLabel();

        jButton1.setText("jButton1");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(ttSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tchkselect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tbtnSearch))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(tload, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnSearchActionPerformed
    
        tload.setText("trying fetch");
                
        ((CustomerController)controller).executeSearchForCustom();      
        
    }//GEN-LAST:event_tbtnSearchActionPerformed
    
     
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private org.components.controls.CComboBox tAttribute;
    private javax.swing.JButton tbtnSearch;
    private org.components.controls.CCheckBox tchkselect;
    private org.components.controls.CLabel tload;
    private javax.swing.JTextField ttSearch;
    // End of variables declaration//GEN-END:variables
}
