/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.ui.list.master;

import java.util.Date;
import java.util.List;
import org.biz.app.ui.util.QueryManager;
import org.biz.dao.service.CQuery;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.master.Category;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.service.master.ItemService;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.windows.ListViewPanel;/**
 *
 * @author d
 */
public class ItemList extends ListViewPanel {

    private ItemService itemService;
    private List<Item> items;
    private TableInteractionListner tableInteractionListner = new TableInteractionListner(){

        @Override
        public Object[] getTableData(Object row) {
            Item item= (Item)row;
            Category cat= item.getCategory();
            return new Object[]{item,item.getId(),item.getCode(),item.getDescription(),cat==null?"":cat.getCode(),
                cat==null?"":cat.getDescription(),item.getSavedDate(),item.getEditedDate()};
        }
    
    };
    
    private String [] itemTableColumn=new String[]{""};
            /**
     * Creates new form ItemList
     */
    public ItemList() {
//        initComponents;
       super();
         
    }

    @Override
    public void init() {
        initComponents();
//        super.init();
    }
    

    @Override
    public void setService(Service service) {
        super.setService(service);
        itemService = (ItemService) service;
        init(tbl);
//        tbl.setModelClass(Item.class);        
//        tbl.setPropertiesEL(new String[]{"id","code"});
//        tbl.setColumnHeader(new String[]{"id","code","Saved","Edited"});
        tbl.init(Item.class, new Class[]{String.class, String.class, String.class,String.class,String.class, Date.class, Date.class},
                 new String[]{"id", "CODE", "Desc","Category CODE", "Category Desc", "savedDate", "editedDate"});
//        tbl.setPropertiesEL(new String[]{"id", "code", "description", "savedDate", "editedDate"});
        tbl.setTableInteractionListner(tableInteractionListner);
        cPaginatedPanel1.init(service, searchListener, tbl);

    }



    @Override
    public void updateEntityUI() {

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchPanel = new javax.swing.JPanel();
        tbtn = new org.components.controls.CButton();
        tsearch = new org.components.controls.CTextField();
        cComboBox1 = new org.components.controls.CComboBox();
        cPanel1 = new org.components.containers.CPanel();
        searchPanel1 = new javax.swing.JPanel();
        cPaginatedPanel1 = new org.biz.app.ui.util.CPaginatedPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new org.components.controls.CxTable();

        searchPanel.setLayout(null);

        tbtn.setText("Find");
        tbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtnActionPerformed(evt);
            }
        });
        searchPanel.add(tbtn);
        tbtn.setBounds(399, 16, 25, 19);

        tsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tsearchActionPerformed(evt);
            }
        });
        searchPanel.add(tsearch);
        tsearch.setBounds(64, 11, 317, 25);
        searchPanel.add(cComboBox1);
        cComboBox1.setBounds(450, 10, 100, 23);

        searchPanel1.setLayout(null);
        searchPanel1.add(cPaginatedPanel1);
        cPaginatedPanel1.setBounds(30, 10, 520, 40);

        jScrollPane2.setViewportView(tbl);

        javax.swing.GroupLayout cPanel1Layout = new javax.swing.GroupLayout(cPanel1);
        cPanel1.setLayout(cPanel1Layout);
        cPanel1Layout.setHorizontalGroup(
            cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
                    .addComponent(searchPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        cPanel1Layout.setVerticalGroup(
            cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tsearchActionPerformed
//
//        List list= itemService.getDao().getAll();
//        tbl.setModelCollection(list);
    }//GEN-LAST:event_tsearchActionPerformed

    private void tbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnActionPerformed

        //        if(cButton1==nextButton){
            //            pm.setNext();
            //        }
        //        pagination.setQuery(qry)
        //
        //
        //
        //
        //

        //      cPaginatedPanel1.search(qry,"db");

    }//GEN-LAST:event_tbtnActionPerformed
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CComboBox cComboBox1;
    protected org.biz.app.ui.util.CPaginatedPanel cPaginatedPanel1;
    private org.components.containers.CPanel cPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel searchPanel;
    protected javax.swing.JPanel searchPanel1;
    protected org.components.controls.CxTable tbl;
    private org.components.controls.CButton tbtn;
    private org.components.controls.CTextField tsearch;
    // End of variables declaration//GEN-END:variables
   
    
    private QueryManager searchListener = new QueryManager() {
        
        @Override
        public CQuery getCQuery() { 
            
            // 
            
//            String qry = "  c.code " + " like " + " ?1 ";//" where c."+myfield+" "+ myoperator +" ?1 ";
            return service.getQueryByCodeLike(tsearch.getText());
        }

    };
    
    
}












