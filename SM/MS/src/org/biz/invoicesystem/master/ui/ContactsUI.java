/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ContactsUI.java
 *
 * Created on May 2, 2012, 12:42:57 PM
 */
package org.biz.invoicesystem.master.ui;

import com.components.custom.ActionTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import org.biz.app.ui.util.TableUtil;
import org.biz.invoicesystem.entity.contacts.ContactDetail;
import org.biz.invoicesystem.service.contacts.ContactsDetailService;
import org.components.windows.TabPanelUI;

/**
 *
 * @author d
 */
public class ContactsUI extends TabPanelUI {

    ContactDetail contactDetail;
    ContactsDetailService service;

    /**
     * Creates new form ContactsUI
     */
    public ContactsUI() {
        initComponents();
        init();
    }

    @Override
    public void init() {

        controlPanel1.setCrudController(this);
        super.init();
        contactDetail = new ContactDetail();
        service = new ContactsDetailService();
    }

    @Override
    public void events() {

        ttel.addaction(0, new ActionTask() {

            @Override
            public boolean action() {
                System.out.println(" tableadding tel no to");
                addToTable();
                return super.action();
            }
        });

        temail.addaction(0, new ActionTask() {

            @Override
            public boolean action() {
                System.out.println("adding email to table ");
                addToTable();
                return super.action();
            }
        });
    }

    public void addToTable() {

        String txt = ttel.getText();
        contactDetail.setPhones(ContactDetail.addString(contactDetail.getPhones(), txt));
        String obj = contactDetail.getPhones();
        String[] txts = obj.split(ContactDetail.divider);
        TableUtil.cleardata(tbltel);
        for (String str : txts) {
            TableUtil.addrow(tbltel, new Object[]{str});
        }

    }

    public void addEmailToTable() {

        String txt = temail.getText();
        contactDetail.setEmails(ContactDetail.addString(contactDetail.getEmails(), txt));
        String obj = contactDetail.getPhones();
        String[] txts = obj.split(ContactDetail.divider);
        TableUtil.cleardata(tbleemail);
        for (String str : txts) {
            TableUtil.addrow(tbleemail, new Object[]{str});
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ttel = new org.components.controls.CTextField();
        cLabel1 = new org.components.controls.CLabel();
        cTextField2 = new org.components.controls.CTextField();
        cLabel2 = new org.components.controls.CLabel();
        temail = new org.components.controls.CTextField();
        cLabel3 = new org.components.controls.CLabel();
        cTextField4 = new org.components.controls.CTextField();
        cLabel4 = new org.components.controls.CLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cTable1 = new org.components.controls.CTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbleemail = new org.components.controls.CTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        cTable4 = new org.components.controls.CTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        cTable5 = new org.components.controls.CTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        cTable6 = new org.components.controls.CTable();
        cLabel5 = new org.components.controls.CLabel();
        cTextField5 = new org.components.controls.CTextField();
        cLabel6 = new org.components.controls.CLabel();
        cTextField6 = new org.components.controls.CTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        cTable7 = new org.components.controls.CTable();
        cTextField7 = new org.components.controls.CTextField();
        cLabel7 = new org.components.controls.CLabel();
        cTextField8 = new org.components.controls.CTextField();
        cTextField9 = new org.components.controls.CTextField();
        controlPanel1 = new com.components.custom.ControlPanel();
        cButton1 = new org.components.controls.CButton();
        btelrem = new org.components.controls.CButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbltel = new org.components.controls.CxTable();

        setLayout(null);

        ttel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttelActionPerformed(evt);
            }
        });
        add(ttel);
        ttel.setBounds(70, 30, 150, 25);

        cLabel1.setText("Tel");
        add(cLabel1);
        cLabel1.setBounds(10, 28, 68, 25);
        add(cTextField2);
        cTextField2.setBounds(80, 160, 140, 25);

        cLabel2.setText("Fax");
        add(cLabel2);
        cLabel2.setBounds(30, 160, 68, 25);
        add(temail);
        temail.setBounds(330, 30, 140, 25);

        cLabel3.setText("Email");
        add(cLabel3);
        cLabel3.setBounds(270, 30, 50, 25);
        add(cTextField4);
        cTextField4.setBounds(590, 170, 140, 25);

        cLabel4.setText("WebSite");
        add(cLabel4);
        cLabel4.setBounds(520, 170, 68, 25);

        cTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(cTable1);
        cTable1.getColumnModel().getColumn(0).setResizable(false);

        add(jScrollPane1);
        jScrollPane1.setBounds(80, 200, 142, 95);

        tbleemail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbleemail);
        tbleemail.getColumnModel().getColumn(0).setResizable(false);

        add(jScrollPane2);
        jScrollPane2.setBounds(330, 60, 142, 95);

        cTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(cTable4);
        cTable4.getColumnModel().getColumn(0).setResizable(false);

        add(jScrollPane4);
        jScrollPane4.setBounds(330, 190, 142, 95);

        cTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(cTable5);
        cTable5.getColumnModel().getColumn(0).setResizable(false);

        add(jScrollPane5);
        jScrollPane5.setBounds(600, 60, 142, 95);

        cTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(cTable6);
        cTable6.getColumnModel().getColumn(0).setResizable(false);

        add(jScrollPane6);
        jScrollPane6.setBounds(590, 200, 142, 95);

        cLabel5.setText("WebSite");
        add(cLabel5);
        cLabel5.setBounds(260, 170, 68, 20);
        add(cTextField5);
        cTextField5.setBounds(330, 160, 140, 25);

        cLabel6.setText("WebSite");
        add(cLabel6);
        cLabel6.setBounds(510, 30, 68, 20);
        add(cTextField6);
        cTextField6.setBounds(600, 30, 140, 25);

        cTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(cTable7);

        add(jScrollPane7);
        jScrollPane7.setBounds(50, 460, 452, 100);
        add(cTextField7);
        cTextField7.setBounds(100, 420, 110, 25);

        cLabel7.setText("Address");
        add(cLabel7);
        cLabel7.setBounds(20, 420, 70, 25);
        add(cTextField8);
        cTextField8.setBounds(220, 420, 90, 25);
        add(cTextField9);
        cTextField9.setBounds(320, 420, 100, 25);
        add(controlPanel1);
        controlPanel1.setBounds(570, 390, 340, 30);

        cButton1.setText("*");
        cButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton1ActionPerformed(evt);
            }
        });
        add(cButton1);
        cButton1.setBounds(230, 70, 40, 20);

        btelrem.setText("X");
        add(btelrem);
        btelrem.setBounds(230, 100, 40, 23);

        tbltel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tel #"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tbltel);
        tbltel.getColumnModel().getColumn(0).setResizable(false);

        add(jScrollPane8);
        jScrollPane8.setBounds(70, 70, 150, 70);
    }// </editor-fold>//GEN-END:initComponents

    private void ttelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttelActionPerformed

    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton btelrem;
    private org.components.controls.CButton cButton1;
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel3;
    private org.components.controls.CLabel cLabel4;
    private org.components.controls.CLabel cLabel5;
    private org.components.controls.CLabel cLabel6;
    private org.components.controls.CLabel cLabel7;
    private org.components.controls.CTable cTable1;
    private org.components.controls.CTable cTable4;
    private org.components.controls.CTable cTable5;
    private org.components.controls.CTable cTable6;
    private org.components.controls.CTable cTable7;
    private org.components.controls.CTextField cTextField2;
    private org.components.controls.CTextField cTextField4;
    private org.components.controls.CTextField cTextField5;
    private org.components.controls.CTextField cTextField6;
    private org.components.controls.CTextField cTextField7;
    private org.components.controls.CTextField cTextField8;
    private org.components.controls.CTextField cTextField9;
    private com.components.custom.ControlPanel controlPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private org.components.controls.CTable tbleemail;
    private org.components.controls.CxTable tbltel;
    private org.components.controls.CTextField temail;
    private org.components.controls.CTextField ttel;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return "Contacts ";
    }
}
