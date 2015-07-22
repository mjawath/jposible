/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test.test;

import org.biz.app.ui.event.OAction;
import org.biz.app.ui.util.QueryManager;
import org.components.test.ResultPage;

/**
 *
 * @author Jawad
 */
public class PaginatedPanel extends javax.swing.JPanel {
    
    private QueryManager qryManager;
    /**
     * Creates new form PaginatedPanel
     */
    public PaginatedPanel() {
        initComponents();
       btnFirst.setAction(new nextaction());
      
    }

    //set next 
    
    
       private class   nextaction extends OAction {
            
            public Object doBackgroundTask(Object... objs) {
                if(qryManager==null)return null;
                long count = qryManager.executeCountQuery();
                int noOfRowsPerPage=qryManager.getNoOfRowsPerPage();
                int noOfPages = (int) Math.ceil((float) count / noOfRowsPerPage);
                Object obj=qryManager.executeQuery(0);
                ResultPage rp = new ResultPage(count,noOfPages,0,obj);
        
                return rp;
            }

            public void doResultTask(Object objs) {
                if(objs==null)return;
                ResultPage l=(ResultPage)objs;
                if(qryManager!=null)
                   qryManager.postQuery(objs);
            
            }
        };
       
       private OAction  firstaction = new OAction() {
            
            public Object doBackgroundTask(Object objs) {
              
               
                qryManager.getFirstPage();                   
               
                return qryManager;
            }

            public void resultTask(Object objs) {
                if(objs==null)return;


            }
        };
       
       private OAction  lastaction = new OAction() {
            
            public Object doBackgroundTask(Object objs) {
                

                qryManager.getLastPage();                
                return qryManager;
            }

            public void resultTask(Object objs) {
 
            }
        };
    
       private OAction  previoseaction = new OAction() {
            
            public Object doBackgroundTask(Object objs) {
                

                qryManager.getPrePage();                
                return qryManager;
            }

            public void resultTask(Object objs) {
                if(objs==null)return;
//                noOfPages();
//                ctable.setModelCollection(((QueryManager)objs).getList() );
//                ((QueryManager)objs).postQuery(((QueryManager)objs));

            }
        };
    
   
    
    public void doSearch(){
        //
    
    }
    
    public String builSearchQuery(){
    return "";
    }
    
    
    public String builCountQuery(){
    return "";
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnFirst = new org.components.controls.CButton();
        btnPreviouse = new org.components.controls.CButton();
        btnLast = new org.components.controls.CButton();
        btnNext = new org.components.controls.CButton();

        btnFirst.setText("First");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPreviouse.setText("<");
        btnPreviouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviouseActionPerformed(evt);
            }
        });

        btnLast.setText(">>");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPreviouse, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPreviouse, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(btnFirst, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreviouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviouseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPreviouseActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLastActionPerformed

  public void setSearchListener(QueryManager searchListener){
        qryManager=searchListener;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton btnFirst;
    private org.components.controls.CButton btnLast;
    private org.components.controls.CButton btnNext;
    private org.components.controls.CButton btnPreviouse;
    // End of variables declaration//GEN-END:variables
}
