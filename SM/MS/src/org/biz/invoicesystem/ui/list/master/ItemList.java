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


    }



    @Override
    public void updateEntityUI() {

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 906, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 493, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
   
    
   
    
    
}












