/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.ui.list.master;

import org.components.windows.SearchQueryUIPanel;

/**
 *
 * @author Jawad
 */
public class ItemSUI extends  SearchQueryUIPanel {

    
    public static final int Listview_searchUIType=0;
    public static final int POPUP_searchUIType=1;
    private int searchUIType= Listview_searchUIType;
//    private ItemQueryManger iqm;
    
    
      
//    private ItemQueryManger iqm= new ItemQueryManger(this);
    
    /**
     * Creates new form ItemSUI
     */
    public ItemSUI() {
        super();       
        ItemListViewUI itl =new ItemListViewUI();        
        itl.initPaging(qms);
        setListViewForPopup(itl);
    }
    
    
    
    
        
  
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 424, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
     
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
