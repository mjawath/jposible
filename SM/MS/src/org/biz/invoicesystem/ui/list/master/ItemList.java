/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.ui.list.master;

import java.util.Date;
import java.util.List;
import org.biz.app.ui.util.QueryManager;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.service.master.ItemService;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.windows.ListViewPanel;



/**
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
            return new Object[]{item,item.getId(),item.getCode(),item.getDescription(),item.getSavedDate(),item.getEditedDate()};
        }
    
    };
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
        super.init();
    }
    

    @Override
    public void setService(Service service) {
        super.setService(service);
        itemService = (ItemService) service;
        init(tbl);
//        tbl.setModelClass(Item.class);        
//        tbl.setPropertiesEL(new String[]{"id","code"});
//        tbl.setColumnHeader(new String[]{"id","code","Saved","Edited"});
        tbl.init(Item.class, new Class[]{String.class, String.class, String.class, Date.class, Date.class},
                 new String[]{"id", "code", "description", "savedDate", "editedDate"});
        tbl.setPropertiesEL(new String[]{"id", "code", "description", "savedDate", "editedDate"});
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

        add(searchPanel);
        searchPanel.setBounds(0, 0, 808, 50);
    }// </editor-fold>//GEN-END:initComponents

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

    private void tsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tsearchActionPerformed

        List list= itemService.getDao().getAll();
        tbl.setModelCollection(list);
    }//GEN-LAST:event_tsearchActionPerformed
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel searchPanel;
    private org.components.controls.CButton tbtn;
    private org.components.controls.CTextField tsearch;
    // End of variables declaration//GEN-END:variables
   
    
    private QueryManager searchListener = new QueryManager() {
        @Override
        public String getQuery() {
            String qry = "  c.code " + " like " + " ?1 ";//" where c."+myfield+" "+ myoperator +" ?1 ";
            return qry;
        }

        @Override
        public Object[] getParams() {
            return new Object[]{tsearch.getText() + "%"};
        }
    };
    
    
}












