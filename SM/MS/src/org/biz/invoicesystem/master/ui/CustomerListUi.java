/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CustomerListUi.java
 *
 * Created on Dec 3, 2011, 4:54:01 PM
 */
package org.biz.invoicesystem.master.ui;

import java.util.List;
import javax.swing.JPanel;
import org.biz.app.ui.util.TableUtil;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.service.master.CustomerService;
import org.components.windows.TabPanelUI;
 
public class CustomerListUi extends TabPanelUI   {

       private CustomerService cService;
  
 // List<Customer> customers;
  
  private Customer selectedCus;

    @Override
    public void init() {
  
        try {
           
   cService=new CustomerService();
   //customers=new ArrayList<Customer>();
  // customer=new Customer();
   
   
        } catch (Exception e) {
        e.printStackTrace();
        }
        
    }
    public CustomerListUi() {
        initComponents();
    }
 
     //////////////////////////////////////////////////
      public void fillItemTbl(){
      TableUtil.cleardata(tblCustomerList);
      
          try {
         List<Customer> lsts=cService.getDao().getAll();   
              for (Customer i : lsts) {
    TableUtil.addrow(tblCustomerList, new Object[]{i.getCode(),i.getCustomerName(),i.getPhone(),0.0,true});                                
              }
          
          
          } catch (Exception e) {
          e.printStackTrace();
          }
           
      }
      
 ///////////////////////////////////////////////////// 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tCustomerSearch = new org.components.controls.CTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomerList = new org.components.controls.CxTable();
        cNewCustomer = new org.components.controls.CButton();
        cCustomerHistory = new org.components.controls.CButton();
        cDeleteCustomer = new org.components.controls.CButton();
        cBulkMail = new org.components.controls.CButton();
        cClose = new org.components.controls.CButton();
        cBulkSms = new org.components.controls.CButton();
        cCheckBox1 = new org.components.controls.CCheckBox();

        setLayout(null);

        jLabel1.setText("Customer Name Search");
        add(jLabel1);
        jLabel1.setBounds(10, 10, 112, 20);

        tCustomerSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCustomerSearchActionPerformed(evt);
            }
        });
        add(tCustomerSearch);
        tCustomerSearch.setBounds(140, 10, 470, 25);

        tblCustomerList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Name", "Telephone", "Mobile", "Due (Rs)", "Selection"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblCustomerList);
        tblCustomerList.getColumnModel().getColumn(1).setMinWidth(150);
        tblCustomerList.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblCustomerList.getColumnModel().getColumn(1).setMaxWidth(150);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 47, 760, 278);

        cNewCustomer.setText("New ");
        cNewCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cNewCustomerActionPerformed(evt);
            }
        });
        add(cNewCustomer);
        cNewCustomer.setBounds(10, 350, 121, 49);

        cCustomerHistory.setText("History");
        cCustomerHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cCustomerHistoryActionPerformed(evt);
            }
        });
        add(cCustomerHistory);
        cCustomerHistory.setBounds(270, 350, 121, 49);

        cDeleteCustomer.setText("Delete");
        cDeleteCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cDeleteCustomerActionPerformed(evt);
            }
        });
        add(cDeleteCustomer);
        cDeleteCustomer.setBounds(140, 350, 121, 49);

        cBulkMail.setText("Bulk Email");
        cBulkMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBulkMailActionPerformed(evt);
            }
        });
        add(cBulkMail);
        cBulkMail.setBounds(530, 350, 121, 49);

        cClose.setText("Close");
        cClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cCloseActionPerformed(evt);
            }
        });
        add(cClose);
        cClose.setBounds(660, 350, 110, 49);

        cBulkSms.setText("Bulk Sms");
        cBulkSms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBulkSmsActionPerformed(evt);
            }
        });
        add(cBulkSms);
        cBulkSms.setBounds(400, 350, 121, 49);

        cCheckBox1.setText("Remove Selection");
        add(cCheckBox1);
        cCheckBox1.setBounds(650, 330, 120, 23);
    }// </editor-fold>//GEN-END:initComponents

    private void cNewCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cNewCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cNewCustomerActionPerformed

    private void cCustomerHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cCustomerHistoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cCustomerHistoryActionPerformed

    private void cDeleteCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cDeleteCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cDeleteCustomerActionPerformed

    private void cBulkMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cBulkMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cBulkMailActionPerformed

    private void cCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cCloseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cCloseActionPerformed

    private void tCustomerSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCustomerSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCustomerSearchActionPerformed

    private void cBulkSmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cBulkSmsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cBulkSmsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cBulkMail;
    private org.components.controls.CButton cBulkSms;
    private org.components.controls.CCheckBox cCheckBox1;
    private org.components.controls.CButton cClose;
    private org.components.controls.CButton cCustomerHistory;
    private org.components.controls.CButton cDeleteCustomer;
    private org.components.controls.CButton cNewCustomer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CTextField tCustomerSearch;
    private org.components.controls.CxTable tblCustomerList;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
       return "Customer List";
    }

    @Override
    public JPanel getJPanel() {
    
        return this;    
    }
}
