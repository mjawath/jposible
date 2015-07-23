/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.windows;

import com.components.custom.TextFieldWithPopUP;
import com.components.custom.TextFieldWithPopUP.ActionTest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.biz.app.ui.util.QueryManager;
import org.biz.app.ui.util.UIListener;
import org.biz.dao.service.Service;
import org.components.test.ResultPage;

/**
 *
 * @author jawath
 */
public class SearchQueryUIPanel<T> extends javax.swing.JPanel{

    protected QueryManager qms;
    protected ListViewUI listView;
    protected UIController<T> controller;
    private static final int Listview_searchUIType = 0;
    private static final int POPUP_searchUIType = 1;
    private int searchUIType = Listview_searchUIType;
    private UISQLParameter uisqlp;
    private int UIType = POPUP_searchUIType;
    
    public static final String QRY="SEARCH";
    /**
     * Creates new form SearchQueryUIPanel
     */
    public SearchQueryUIPanel() {
        
//        initComponents();
        init();
        
    }
   
    protected void init(){       
        qms = new MYQueryManger();
        if (POPUP_searchUIType == UIType) {
            initComponents();
            initSearchTextField();

        } else if (Listview_searchUIType == UIType) {
            
        }
        
    }

    private void initSearchTextField() {
//        sl =new simpleSearchListener();
        qms.addUIListener(sl);
       ActionTest at = new TextFieldWithPopUP.ActionTest(){            
            public void action() {
                qms.executeToFirstPageTask();
            }        
        };
        ttxtsearch.setAt(at);
        

    }
    
    

    
     simpleSearchListener sl =new simpleSearchListener();
    
     
         private class simpleSearchListener implements UIListener {

        public void updateUI(ResultPage result) {
            if (ttxtsearch != null && ttxtsearch.hasListView()) {                                
                ttxtsearch.showPopUp(result);
            }
            
            if(listView!=null){
                listView.setResult(result);
            }
            
        }

    }        
       

    public Long executeCountQuery() {        
        return controller.getService().getCountByCodeLike(ttxtsearch.getText());

    }

    public List executeQuery(int page) {  
        String txt =  ttxtsearch.getText();
        System.out.println(" executeQuery text "+txt);
        final List byCodeLike = controller.getService().getByCodeLike(page, txt);
        System.out.println(" executeQuery result "+byCodeLike.size());
        return byCodeLike;
    }

     
     private class MYQueryManger extends QueryManager {


        public MYQueryManger() {            
        }

        @Override
        public Long executeCountQuery() {
            return SearchQueryUIPanel.this.executeCountQuery(); //To change body of generated methods, choose Tools | Templates.
        }
        
     
        @Override
        public List executeQuery(int page) {
            //what ever we can implement here 
            return SearchQueryUIPanel.this.executeQuery(page);
        }      

    }
    
    public void setController(UIController controller){
    this.controller  = controller;
    }
    


    public Map<String, Object> getQueryParameterMap() {
        if (uisqlp != null) {
            return uisqlp.getQueryParameterMap();
        }
        Map<String, Object> p = new HashMap<>();
        p.put(QRY, ttxtsearch.getText());
        return p;
    }

    public void setServiceForQuery(Service service) {
//    qms=getQueryManager();
        if (qms == null) {
            return;
        }
        qms.setService(service);
    }


    public void findAction() {
         //get list view
        // listview get the pageer
        //pager e=> execute the find action
        if (listView == null) {
            return;
        }
        listView.findCommand();
    }

    public void setUISQLParameter(UISQLParameter sql) {
        uisqlp = sql;
    }

    public interface UISQLParameter {

        public Map<String, Object> getQueryParameterMap();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbtnSearch = new javax.swing.JButton();
        ttxtsearch = new com.components.custom.TextFieldWithPopUP();

        tbtnSearch.setText("Search");
        tbtnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ttxtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbtnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ttxtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbtnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnSearchActionPerformed
        controller.executeSearch(this);
        
        
    }//GEN-LAST:event_tbtnSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton tbtnSearch;
    private com.components.custom.TextFieldWithPopUP ttxtsearch;
    // End of variables declaration//GEN-END:variables


    public void setQueryManager(QueryManager qm) {
    }

    public QueryManager getQueryManager() {
        return qms;
    }
    
    public void setListView(ListViewUI listView){
        this.listView = listView;        
    }
    
    public void setListViewForPopup(ListViewUI lv){
        ttxtsearch.setListView(lv);
        lv.initPaging(qms);
    }
    
}
