/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.windows;

import java.util.List;
import org.biz.app.ui.util.CPaginatedPanel;
import org.biz.app.ui.util.QueryManager;
import org.components.controls.CxTable;

/**
 *
 * @author jawath
 */
public class ListViewUI extends javax.swing.JPanel {

    
    private QueryManager queryManager;
    private CPaginatedPanel pagePanel;
    /**
     * Creates new form ListViewUI
     */
    public ListViewUI() {
        initComponents();
        pagePanel=cPaginatedPanel1;
    }
    
    public void initPaging(QueryManager qman){
//        cPaginatedPanel1.init(service, searchListener, tbl);
        queryManager=qman;
        setPaging(pagePanel, tbl);
        if(pagePanel==null)return;
        pagePanel.init(qman, tbl);
    }

    
    public void setPaging(CPaginatedPanel pp,CxTable  table){
        pagePanel=pp;
        tbl=table;
    }
    
    public void executeQuery(){
        pagePanel.findCommand();
    }
    
    
    public CxTable getTable(){
    return tbl;
    }

    public CPaginatedPanel getPagePanel(){
    return cPaginatedPanel1;
    }

    public void setSearchQueryUI(SearchQueryUIPanel queryPanel) {
        if (queryPanel == null) {
            return;
        }
        QueryManager qm = queryPanel.getQueryManager();
        initPaging(qm);
    }
    
    public void findCommand(){
        cPaginatedPanel1.findCommand();
    }
    public void setCollection(List list){
    tbl.setModelCollection(list);
    }
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cPanel1 = new org.components.containers.CPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new org.components.controls.CxTable();
        cPaginatedPanel1 = new org.biz.app.ui.util.CPaginatedPanel();

        jScrollPane2.setViewportView(tbl);

        javax.swing.GroupLayout cPanel1Layout = new javax.swing.GroupLayout(cPanel1);
        cPanel1.setLayout(cPanel1Layout);
        cPanel1Layout.setHorizontalGroup(
            cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cPaginatedPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
        );
        cPanel1Layout.setVerticalGroup(
            cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cPanel1Layout.createSequentialGroup()
                .addComponent(cPaginatedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 638, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(cPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 363, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(cPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected org.biz.app.ui.util.CPaginatedPanel cPaginatedPanel1;
    private org.components.containers.CPanel cPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    protected org.components.controls.CxTable tbl;
    // End of variables declaration//GEN-END:variables

}
