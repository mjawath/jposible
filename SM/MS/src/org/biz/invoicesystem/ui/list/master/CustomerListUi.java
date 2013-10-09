/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CustomerListUi.java
 *
 * Created on Dec 3, 2011, 4:54:01 PM
 */
package org.biz.invoicesystem.ui.list.master;

import java.util.Date;
import java.util.List;
import org.biz.app.ui.util.QueryManager;
import org.biz.app.ui.util.TableUtil;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.service.master.CustomerService;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.windows.ListViewPanel;

public class CustomerListUi extends ListViewPanel<Customer> {

    private CustomerService cService;
    // List<Customer> customers;
    private Customer selectedCus;
    
    private TableInteractionListner tableInteractionListner = new TableInteractionListner() {
        @Override
        public Object[] getTableData(Object row) {
            Customer item = (Customer) row;
            return new Object[]{item, item.getId(), item.getCode(), item.getCompanyName(), item.getSavedDate(), item.getEditedDate()};
        }
    };
    
    

    @Override
    public void init() {


            tbl.init(Customer.class, new Class[]{String.class, String.class, String.class, Date.class, Date.class}, new String[]{"id", "code", "companyName", "savedDate", "editedDate"});
            tbl.setTableInteractionListner(tableInteractionListner);
            cPaginatedPanel1.init(service, searchListener, tbl);

    }
    
    
    public CustomerListUi() {
        initComponents();
    }

    //////////////////////////////////////////////////
    public void fillItemTbl() {
        TableUtil.cleardata(tbl);

        try {
            List<Customer> lsts = cService.getDao().getAll();
            for (Customer i : lsts) {
                TableUtil.addrow(tbl, new Object[]{i.getCode(), i.getCustomerName(), i.getPhone(), 0.0, true});
            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    

    ///////////////////////////////////////////////////// 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tSearch = new org.components.controls.CTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new org.components.controls.CxTable();
        cNewCustomer = new org.components.controls.CButton();
        cCustomerHistory = new org.components.controls.CButton();
        cDeleteCustomer = new org.components.controls.CButton();
        cBulkMail = new org.components.controls.CButton();
        cClose = new org.components.controls.CButton();
        cBulkSms = new org.components.controls.CButton();
        cCheckBox1 = new org.components.controls.CCheckBox();
        cPaginatedPanel1 = new org.biz.app.ui.util.CPaginatedPanel();

        setLayout(null);

        jLabel1.setText("Customer Name Search");
        add(jLabel1);
        jLabel1.setBounds(10, 10, 112, 20);

        tSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tSearchActionPerformed(evt);
            }
        });
        add(tSearch);
        tSearch.setBounds(140, 10, 470, 25);

        tbl.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl);
        tbl.getColumnModel().getColumn(1).setMinWidth(150);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbl.getColumnModel().getColumn(1).setMaxWidth(150);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 75, 760, 330);

        cNewCustomer.setText("New ");
        cNewCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cNewCustomerActionPerformed(evt);
            }
        });
        add(cNewCustomer);
        cNewCustomer.setBounds(10, 430, 121, 49);

        cCustomerHistory.setText("History");
        cCustomerHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cCustomerHistoryActionPerformed(evt);
            }
        });
        add(cCustomerHistory);
        cCustomerHistory.setBounds(270, 430, 121, 49);

        cDeleteCustomer.setText("Delete");
        cDeleteCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cDeleteCustomerActionPerformed(evt);
            }
        });
        add(cDeleteCustomer);
        cDeleteCustomer.setBounds(140, 430, 121, 49);

        cBulkMail.setText("Bulk Email");
        cBulkMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBulkMailActionPerformed(evt);
            }
        });
        add(cBulkMail);
        cBulkMail.setBounds(530, 430, 121, 49);

        cClose.setText("Close");
        cClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cCloseActionPerformed(evt);
            }
        });
        add(cClose);
        cClose.setBounds(660, 430, 110, 49);

        cBulkSms.setText("Bulk Sms");
        cBulkSms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBulkSmsActionPerformed(evt);
            }
        });
        add(cBulkSms);
        cBulkSms.setBounds(400, 430, 121, 49);

        cCheckBox1.setText("Remove Selection");
        add(cCheckBox1);
        cCheckBox1.setBounds(650, 410, 120, 23);
        add(cPaginatedPanel1);
        cPaginatedPanel1.setBounds(100, 40, 440, 40);
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

    private void tSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tSearchActionPerformed

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
    private org.biz.app.ui.util.CPaginatedPanel cPaginatedPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CTextField tSearch;
    private org.components.controls.CxTable tbl;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setService(Service service) {
        super.setService(service);
        cService = (CustomerService) service;
        init();
        init(tbl);
    }
    private QueryManager searchListener = new QueryManager() {
        @Override
        public String getQuery() {
            String qry = "  c.code " + " like " + " ?1 ";//" where c."+myfield+" "+ myoperator +" @1 ";
            return qry;
        }

        @Override
        public Object[] getParams() {
            return new Object[]{tSearch.getText() + "%"};
        }
    };
}
