/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.windows;

import com.components.custom.TextFieldWithPopUP;
import com.components.custom.TextFieldWithPopUP.ActionTest;
import java.util.HashMap;
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
    protected static final int Listview_searchUIType = 0;
    protected static final int POPUP_searchUIType = 1;
    private int searchUIType = Listview_searchUIType;
    private UISQLParameter uisqlp;
    protected int UIType = POPUP_searchUIType;
    
    public static final String QRY="SEARCH";
    /**
     * Creates new form SearchQueryUIPanel
     */
    public SearchQueryUIPanel() {
        config();
//        initComponents();    
    }
   
    private void config(){    
        init();
        postInit();        
    
    }
    
    
    
    protected void init(){
//      qms = new MYQueryManger();
//        if (POPUP_searchUIType == UIType) {
////            initComponents();
////            initSearchTextField();
//
//        } else if (Listview_searchUIType == UIType) {
//            
//        }
    }
    
    protected void postInit(){       
      
        
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

            if (listView != null) {
                listView.setResult(result);
            }

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
                .addComponent(ttxtsearch, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbtnSearch)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ttxtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbtnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnSearchActionPerformed
        
        
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
