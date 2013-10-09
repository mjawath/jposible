/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CPaginatedPanel.java
 *
 * Created on Apr 6, 2012, 6:51:44 AM
 */
package org.biz.app.ui.util; 

import java.util.ArrayList;
import java.util.List;
import org.biz.app.ui.event.ButtonAction;
import org.biz.dao.service.Service;
import org.components.controls.CButton;
import org.components.controls.CxTable;



/**
 *
 * @author nnjj
 */
public class CPaginatedPanel extends javax.swing.JPanel {

    /** Creates new form CPaginatedPanel */
    public CPaginatedPanel() {
        initComponents();
        tnextpage.addActionListener(action);
        tPreviousPage.addActionListener(action);  
        tFind.addActionListener(action);
        tlast.addActionListener(action);
        tfirst.addActionListener(action);
    }
    
    private String qryName = "";
    private Service service;
    private PagedListUI listUI;
    
    private QueryManager sl;
    int currentPage=0;
    private CxTable ctable;

    public void setSearchListener(QueryManager searchListener){
    sl=searchListener;
    }
    
    public void setService(Service service){
    this.service =service;
    sl.setService(service);
    }
    
    public void init(Service service,QueryManager qm,CxTable table){
        this.service=service;
        this.sl=qm;
        sl.setService(service);
        this.ctable=table;
    }
    
    
    private ButtonAction  action = new ButtonAction() {
            
            public Object executeTask(Object objs) {
                ArrayList al=(ArrayList)(objs); 
                CButton btn=(CButton)(al.get(0)); 
                
                if(service==null)return null;
                
                //create pages 
                //should check query is exisiting or modified
                if (tFind==btn) {
                    sl.getFirstPage();
                }else
                if (tnextpage==btn) {//get excuting source
                    sl.getNextPage();
                   
                }else if (tPreviousPage==btn) {
                    sl.getPrePage();
                }
                else if (tlast==btn) {
                    sl.getLastPage();
                }
                else if (tfirst==btn) {
                    sl.getFirstPage();
                }
                return sl;
            }

            public void resultTask(Object objs) {
                if(objs==null)return;
                noOfPages();
                ctable.setModelCollection(((QueryManager)objs).getList() );                
            }
        };
    
    private void setKeySearchEvent(){
        //key down
        //key up
        //page up
        //page down
        //home
        //end
        
        
    }

/*        private CKeyAdapter keyAdapter = new CKeyAdapter(KeyEvent.KEY_PRESSED) {
        @Override
        public Object executeTask() {


            if (service == null) {
                return null;
            }

            //create pages 
            //should check query is exisiting or modified
            if (tFind == btn) {
                sl.getFirstPage();
            }
            else if (tnextpage == btn) {//get excuting source
                sl.getNextPage();

            }
            else if (tPreviousPage == btn) {
                sl.getPrePage();
            }
            else if (tlast == btn) {
                sl.getLastPage();
            }
            else if (tfirst == btn) {
                sl.getFirstPage();
            }
            return sl;
        }

        @Override
        public void resultTask(Object objs) {
            if (objs == null) {
                return;
            }
            noOfPages();
            ctable.setModelCollection(((QueryManager) objs).getList());
        }
    };

*/
    public void setQryName(String qryName) {
        this.qryName = qryName;
    }

    public PagedListUI getListUI() {
        return listUI;
    }

    public void setListUI(PagedListUI listUI) {
        this.listUI = listUI;
    }

    public void noOfPages() {
        long pages = sl.getNoOfPages();
        sl.getCurrentPage();
//        if (pages < sl.getCurrentPage()) {//if any error occued we should catch exception
//            tinfo.setText("");
//            return;
//        }
        tinfo.setText(" " + (sl.getCurrentPage() + 1) + " OF " + pages + " Pages " + sl.count() + " Count");
    }

    public Service gerService() {
        return service;
    }

    public void getNextEntity() {
    }

    public void getPreviousEntity() {
    }

    public void getExecuteQuery() {
    }

    public void getNextPage() {
        service.getNextPage(qryName);
//        loadDataWithList();
    }

    public void getPreviousePage() {
//        service.getPreviousePage(qryName, 0);
    //u should be able to retreive everything from database to 
        //to be used in ui to show paginated infos like page number how amny pages 
        //need and how many row returned ...totel count 
        loadDataWithList();
        
    }

    public void getLastPage() {
//        service.getLastPage(qryName);
        loadDataWithList();
    }

    public void getFirstPage() {
//        service.getFirstPage(qryName);
        loadDataWithList();
    }

    public void loadDataWithList() {
        if (listUI != null) {// safty null check sould be imlemented !!
            List s = service.getCache().getbyQueryName(qryName).getList();
            listUI.loadDataWithList(s);
            System.out.println(s.size());
        }
    }
    
    
    public void setTable(CxTable ctable){
        this.ctable=ctable;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tPreviousPage = new org.components.controls.CButton();
        tfirst = new org.components.controls.CButton();
        tlast = new org.components.controls.CButton();
        tnextpage = new org.components.controls.CButton();
        cPageCount = new org.components.controls.CTextFieldPopUp();
        tFind = new org.components.controls.CButton();
        tinfo = new org.components.controls.CLabel();

        setLayout(null);

        tPreviousPage.setText("<");
        tPreviousPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tPreviousPageActionPerformed(evt);
            }
        });
        add(tPreviousPage);
        tPreviousPage.setBounds(40, 10, 13, 19);

        tfirst.setText("<<");
        tfirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfirstActionPerformed(evt);
            }
        });
        add(tfirst);
        tfirst.setBounds(10, 10, 21, 19);

        tlast.setText(">>");
        tlast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tlastActionPerformed(evt);
            }
        });
        add(tlast);
        tlast.setBounds(220, 10, 21, 19);

        tnextpage.setText(">");
        tnextpage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnextpageActionPerformed(evt);
            }
        });
        add(tnextpage);
        tnextpage.setBounds(190, 10, 30, 19);
        add(cPageCount);
        cPageCount.setBounds(70, 10, 90, 20);

        tFind.setText("Find");
        tFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tFindActionPerformed(evt);
            }
        });
        add(tFind);
        tFind.setBounds(160, 10, 25, 19);

        tinfo.setText("");
        add(tinfo);
        tinfo.setBounds(250, 10, 180, 25);
    }// </editor-fold>//GEN-END:initComponents

    private void tfirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfirstActionPerformed
//        getFirstPage();
       }//GEN-LAST:event_tfirstActionPerformed

    private void cButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cButton6ActionPerformed

    private void tPreviousPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tPreviousPageActionPerformed
//        getPreviousePage();
}//GEN-LAST:event_tPreviousPageActionPerformed

    private void tlastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tlastActionPerformed
//        getLastPage();

}//GEN-LAST:event_tlastActionPerformed

    private void tnextpageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnextpageActionPerformed
//        getNextPage();

}//GEN-LAST:event_tnextpageActionPerformed

    private void tFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tFindActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tFindActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CTextFieldPopUp cPageCount;
    private org.components.controls.CButton tFind;
    private org.components.controls.CButton tPreviousPage;
    private org.components.controls.CButton tfirst;
    private org.components.controls.CLabel tinfo;
    private org.components.controls.CButton tlast;
    private org.components.controls.CButton tnextpage;
    // End of variables declaration//GEN-END:variables
}
