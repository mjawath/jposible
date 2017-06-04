/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.ui.list.master;

import java.util.HashMap;
import java.util.Map;
import org.components.windows.SearchQueryUIPanel;
import static org.components.windows.SearchQueryUIPanel.QRY;

/**
 *
 * @author Jawad
 */
public class ItemSUI extends  SearchQueryUIPanel {

    
//    public static final int Listview_searchUIType=0;
//    public static final int POPUP_searchUIType=1;
//    private int searchUIType= Listview_searchUIType;
//    private ItemQueryManger iqm;
    
    
      
//    private ItemQueryManger iqm= new ItemQueryManger(this);
    
    /**
     * Creates new form ItemSUI
     */
    public ItemSUI() {
        super();
    }

  
    @Override
    protected void init() {        
//        UIType = Listview_searchUIType;
        super.init();
        initComponents();
    }
    
    
    public Map<String, Object> getQueryParameterMap() {

        Map<String, Object> p = new HashMap<>();
        p.put(QRY, ttSearch.getText());
        return p;
    }
    
    public boolean isOrderByCreationDate(){
        return tchkCreation.isSelected();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        tbtnGroup = new javax.swing.ButtonGroup();
        ttSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tbtnSearch = new javax.swing.JButton();
        tchkCreation = new org.components.controls.CCheckBox();
        rdoCode = new javax.swing.JRadioButton();
        rdoDesc = new javax.swing.JRadioButton();

        jButton1.setText("jButton1");

        jLabel1.setText("Search by Item Code /Desciption ");

        tbtnSearch.setText("Search");
        tbtnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtnSearchActionPerformed(evt);
            }
        });

        tchkCreation.setText("By creation date");

        tbtnGroup.add(rdoCode);
        rdoCode.setSelected(true);
        rdoCode.setText("Code");

        tbtnGroup.add(rdoDesc);
        rdoDesc.setText("Desc");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rdoCode)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoDesc))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ttSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tbtnSearch)
                        .addGap(18, 18, 18)
                        .addComponent(tchkCreation, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ttSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(tbtnSearch)
                    .addComponent(tchkCreation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoCode)
                    .addComponent(rdoDesc))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnSearchActionPerformed
    
        ((ItemController)controller).executeSearchForCustom();
      
//      qms.executeToFirstPageTask();
        
    }//GEN-LAST:event_tbtnSearchActionPerformed
    
     
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton rdoCode;
    private javax.swing.JRadioButton rdoDesc;
    private javax.swing.ButtonGroup tbtnGroup;
    private javax.swing.JButton tbtnSearch;
    private org.components.controls.CCheckBox tchkCreation;
    private javax.swing.JTextField ttSearch;
    // End of variables declaration//GEN-END:variables
}
