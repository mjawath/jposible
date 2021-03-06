/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.windows;

import java.util.List;
import org.biz.app.ui.util.CPaginatedPanel;
import org.biz.app.ui.util.QueryManager;
import org.biz.entity.BusObj;
import org.components.controls.CxTable;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.test.ResultPage;

/**
 *
 * @author jawath
 */
public class ListViewUI extends javax.swing.JPanel {

    
    private QueryManager queryManager;
//    private CPaginatedPanel pagePanel;
    /**
     * Creates new form ListViewUI
     */
    public ListViewUI() {
        initComponents();

         tbl.setTableInteractionListner(tableInteractionListner);

    }
    
    public void initPaging(QueryManager qman){

        queryManager=qman;

        if(cPaginatedPanel1==null)return;
        cPaginatedPanel1.init(qman, tbl);
    }
    
    public void setResult(ResultPage resp){
        tbl.setModelCollection((List)resp.getResult());        
        tbl.selectFirst();
    }

    
    public void setPaging(CPaginatedPanel pp,CxTable  table){
//        pagePanel=pp;
        tbl=table;
    }
    
    public void executeQuery(){
        cPaginatedPanel1.findCommand();
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
    
    
    
    private TableInteractionListner tableInteractionListner = new TableInteractionListner() {

        @Override
        public Object[] getTableData(Object row) {
            return ListViewUI.this.getTableData(row);
        }

        @Override
        public void selectionChanged(Object newRowObject) {
            ListViewUI.this.selectionChanged(newRowObject); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void onDoubleClicked(Object newRowObject) {
            ListViewUI.this.onDoubleClicked(newRowObject);
        }
        
        

    };
         
    public Object[] getTableData(Object row) {
        return null;
    }
    
    public void selectionChanged(Object newRowObject) {
        if(controller==null )return;
        controller.setSelectedBusObject((BusObj) newRowObject);
    }

    public void onDoubleClicked(Object newRowObject) {
        if (controller == null) {
            return;
        }
        controller.setSelectedBusObject((BusObj) newRowObject);
        controller.gotoDetailPanel();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cPanel1 = new org.components.containers.CPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new org.components.controls.CxTable();
        cPaginatedPanel1 = new org.biz.app.ui.util.CPaginatedPanel();

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane2.setViewportView(tbl);
        if (tbl.getColumnModel().getColumnCount() > 0) {
            tbl.getColumnModel().getColumn(1).setMinWidth(200);
            tbl.getColumnModel().getColumn(3).setMinWidth(300);
            tbl.getColumnModel().getColumn(4).setMinWidth(250);
        }

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

    protected UIController controller;
    
    public void setController(UIController controller) {
        this.controller = controller;
    }

}
