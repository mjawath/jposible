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

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import org.biz.app.ui.event.OAction;
import org.biz.dao.service.Service;
import org.components.controls.CButton;
import org.components.controls.CxTable;
import org.components.test.ResultPage;



/**
 *
 * @author nnjj
 */
public class CPaginatedPanel extends javax.swing.JPanel {

    private String qryName = "";
    private Service service;
    private PagedListUI listUI;
    private QueryManager qryManager;
    int currentPage = 0;
    private CxTable ctable;
    
    /** Creates new form CPaginatedPanel */
    public CPaginatedPanel() {
        initComponents();
        firstaction.setCommand(command);
        tFind.setAction(firstaction);
        tnextpage.setAction(firstaction);
        tPreviousPage.setAction(firstaction);
        tlast.setAction(firstaction);
    }
    


    public void setSearchListener(QueryManager searchListener){
        qryManager=searchListener;
    }

    public void setSearchListener(QueryManager searchListener, Service service) {
        setSearchListener(searchListener);
        setService(service);
    }

    public void setService(Service service) {
        this.service = service;
        qryManager.setService(service);
    }
    
    public void init(Service service,QueryManager qm,CxTable table){
        this.service=service;
        this.qryManager=qm;
        qryManager.setService(service);
        this.ctable=table;
    }
    
     
    public void init(QueryManager qm,CxTable table){        
        this.qryManager=qm;
//        qryManager.setService(service);
        this.ctable=table;
    }
    
            
    private OAction  action = new OAction() {
            
            public Object doBackgroundTask(Object objs) {
                ArrayList al=(ArrayList)(objs); 
                CButton btn=(CButton)((ActionEvent)(al.get(0))).getSource(); 
                
//                if(service==null)return null;
                
                //create pages 
                //should check query is exisiting or modified
                if (tFind==btn) {
                    qryManager.getFirstPage();
                    
                }else
                if (tnextpage==btn) {//get excuting source
                    qryManager.getNextPage();
                   
                }else if (tPreviousPage==btn) {
                    qryManager.getPrePage();
                }
                else if (tlast==btn) {
                    qryManager.getLastPage();
                }
          
                return qryManager;
            }

            public void resultTask(Object objs) {
                if(objs==null)return;
                noOfPages();
                ctable.setModelCollection(((QueryManager)objs).getList() );
                ((QueryManager)objs).postQuery(((QueryManager)objs));

            }
        };
    

     
       
     private Command command = new Command(){
         
      public Object doBackgroundTask(Object ...objs) {

          
            if (qryManager == null) {
                return null;
            }
            ResultPage rp=null;
            long count = qryManager.executeCountQuery();
            int noOfRowsPerPage = qryManager.getNoOfRowsPerPage();
            int noOfPages = (int) Math.ceil((float) count / noOfRowsPerPage);
            if(objs!=null && objs.length<= 0 )return null;
            if(objs[0]  instanceof ActionEvent  && ((ActionEvent)objs[0]).getSource()==tFind){
            Object obj = qryManager.executeQuery(0);
            rp = new ResultPage((int) count, noOfPages, 0, obj);
               
            }
                      

            return rp;
        }

        public void doResultTask(Object ...objs) {
            if (objs == null) {
            }
            ResultPage l = (ResultPage) objs[0];
            if(l==null)return;
            if (qryManager != null) {
                qryManager.postQuery(l);
            }
            if (ctable != null) {
                ctable.setModelCollection((List) l.getResult());
            }
        }
     
     };
       
     private OAction firstaction = new OAction() {

       
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
//        qryManager.getCount();
        long pages = qryManager.getNoOfPages();
       
        tinfo.setText(" " + (qryManager.getCurrentPage() + 1) + " OF " + pages + " Pages " + qryManager.count()+ " Count");
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
  
    public void setTable(CxTable ctable){
        this.ctable=ctable;
    }
    public void findCommand(){
        tFind.doClick();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tPreviousPage = new org.components.controls.CButton();
        tlast = new org.components.controls.CButton();
        tnextpage = new org.components.controls.CButton();
        cPageCount = new org.components.controls.CTextFieldPopUp();
        tFind = new org.components.controls.CButton();
        tinfo = new org.components.controls.CLabel();

        setOpaque(false);
        setLayout(null);

        tPreviousPage.setText("<");
        add(tPreviousPage);
        tPreviousPage.setBounds(10, 10, 30, 20);

        tlast.setText(">>");
        add(tlast);
        tlast.setBounds(170, 10, 30, 20);

        tnextpage.setText(">");
        add(tnextpage);
        tnextpage.setBounds(130, 10, 30, 20);
        add(cPageCount);
        cPageCount.setBounds(50, 10, 30, 20);

        tFind.setText("Find");
        tFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tFindActionPerformed(evt);
            }
        });
        add(tFind);
        tFind.setBounds(90, 10, 30, 20);

        tinfo.setText("");
        add(tinfo);
        tinfo.setBounds(250, 10, 250, 25);
    }// </editor-fold>//GEN-END:initComponents

    private void cButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cButton6ActionPerformed

    private void tFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tFindActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tFindActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CTextFieldPopUp cPageCount;
    private org.components.controls.CButton tFind;
    private org.components.controls.CButton tPreviousPage;
    private org.components.controls.CLabel tinfo;
    private org.components.controls.CButton tlast;
    private org.components.controls.CButton tnextpage;
    // End of variables declaration//GEN-END:variables
}
