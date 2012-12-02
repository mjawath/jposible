/*
 * AcountsCreationUI.java
 *
 * Created on 01-Apr-2011, 13:28:11
 */
package org.biz.books.ui.accounts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.UIEty;
import org.biz.books.entity.accounts.ledger.Accounts;
import org.biz.books.service.accounts.ledger.AccountsService;
import org.biz.dao.util.EntityService;
import org.components.windows.TabPanelUI;

/**
 *
 * @author mjawath
 */
public class AccountsCreationUI extends TabPanelUI {

    Accounts accounts;
    List<Accounts> listaccounts;
    AccountsService accountsService;
    EntityService es;

    /**
     * Creates new form AcountsCreationUI
     */
    public AccountsCreationUI() {
        initComponents();
        init();
    }

    @Override
    public void init() {
        accounts = new Accounts();
        accountsService = new AccountsService();
        es = EntityService.getEntityService();
        controlPanel1.setCrudController(this);

    }

    @Override
    public void events() {
        tacctype.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            accounts = uiToEty(accounts);
            accountsService.getDao().save(accounts);
            accounts=new Accounts();
            }
        });

    }

    public Accounts uiToEty(Accounts obj) {
        obj.setAccountsNo(UIEty.tcToStr(taccno));
        obj.setAccountsName(UIEty.tcToStr(taccname));
        obj.setPrimaryAccounts(UIEty.cmbtostr(tprimacctype));
        obj.setSubAccounts(UIEty.cmbtostr(tacctype));
        return obj;
    }

    public static void main(String[] args) {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cButton1 = new org.components.controls.CButton();
        taccname = new org.components.controls.CTextField();
        cLabel1 = new org.components.controls.CLabel();
        tprimacctype = new org.components.controls.CComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        cxTable1 = new org.components.controls.CxTable();
        taccno = new org.components.controls.CTextField();
        tacctype = new org.components.controls.CComboBox();
        controlPanel1 = new com.components.custom.ControlPanel();

        cButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton1ActionPerformed(evt);
            }
        });

        tprimacctype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Assets", "Capitol", "Liability", "Expense", "Income" }));

        jScrollPane1.setViewportView(cxTable1);

        tacctype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Assets", "Capitol", "Liability", "Expense", "Income" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(controlPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tprimacctype, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(taccname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(taccno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tacctype, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(84, 84, 84))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(223, 223, 223)
                                        .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(89, 89, 89)
                                        .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(controlPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(taccno, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(taccname, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tprimacctype, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tacctype, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143)
                .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton1ActionPerformed

        accounts.setId(es.getKey());
        accounts.setAccountsNo(UIEty.tcToStr(taccno));
        accounts.setAccountsName(UIEty.tcToStr(taccname));
        accounts.setSubAccounts(UIEty.cmbtostr(tacctype));
        accounts.setPrimaryAccounts(UIEty.cmbtostr(tprimacctype));
        accountsService.getDao().save(accounts);
        listaccounts = accountsService.getDao().getAll();
        addToTable(listaccounts);
        accounts = new Accounts();
        System.out.println("saved");



    }//GEN-LAST:event_cButton1ActionPerformed

    public void addToTable(List<Accounts> items) {
        TableUtil.cleardata(cxTable1);
        for (Accounts item : items) {
            addToTable(item);
        }
        TableUtil.addrow(cxTable1, new Object[]{TableUtil.newRowID, ""});
    }

    public void addToTable(Accounts accounts) {
        TableUtil.addrow(cxTable1, new Object[]{accounts.getId(), accounts.getAccountsNo(), accounts.getAccountsName(), accounts.getPrimaryAccounts()});
    }

    @Override
    public String getTabName() {
        return "accounts creation ";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CLabel cLabel1;
    private com.components.custom.ControlPanel controlPanel1;
    private org.components.controls.CxTable cxTable1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CTextField taccname;
    private org.components.controls.CTextField taccno;
    private org.components.controls.CComboBox tacctype;
    private org.components.controls.CComboBox tprimacctype;
    // End of variables declaration//GEN-END:variables
}
