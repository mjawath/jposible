/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BankDetailUI.java
 *
 * Created on May 26, 2011, 4:39:06 PM
 */
package checkchequingsystem;

import com.components.custom.PagedPopUpPanel;
import java.util.List;
import javax.swing.JPanel;
import org.biz.chequeChequing.entity.accounts.bank.Bank;
import org.biz.chequeChequing.entity.accounts.bank.BankBranch;
import org.biz.chequeChequing.entity.accounts.bank.Cheques;
import org.biz.chequeChequing.entity.service.bank.BankBranchService;
import org.biz.chequeChequing.entity.service.bank.BankService;
import org.biz.chequeChequing.entity.service.bank.ChequeService;
import org.components.windows.TabPanelUI;

/**
 *
 * @author Administrator
 */
public class ChequeDetailUI extends TabPanelUI {

    Cheques cheques;
    Bank selectedBank;
    BankBranch selectedBankBranch;
    List<BankBranch> lstbankBranch;
    ChequeService service;
    BankService bankService;
    BankBranchService branchService;
    PagedPopUpPanel bankPopUpPanel;
    PagedPopUpPanel branchPopUpPanel;

    /** Creates new form cheques */
    public ChequeDetailUI() {
        initComponents();
//        init();
    }

    @Override
    public void init() {
        controlPanel1.setCrudController(this);

        cheques = new Cheques();

        service = new ChequeService();

        bankService = new BankService();

        branchService = new BankBranchService();

        bankPopUpPanel = new PagedPopUpPanel(tbank) {

            @Override
            public void search(String qry) {
                setList(bankService.getDao().getByCode(qry));
            }

            @Override
            public void action() {
//                Bank bank = (Bank) getModel();
//                selectedBankBranch.setBank(bank);
                //load branches of the specific bank
//                lstbankBranch = branchService.getDao().byBankCode(bank.getId());
//                branchPopUpPanel.setList(lstbankBranch);
            }
        };
        bankPopUpPanel.setPropertiesEL(new String[]{"id", "code", "name", "num"});

        branchPopUpPanel = new PagedPopUpPanel(tbranch) {

            @Override
            public void search(String qry) {
                setList(branchService.getDao().getByCode(qry));
            }

            @Override
            public void action() {
//                BankBranch bankBranch = (BankBranch) getModel();
//                cheques.setBankBranch(bankBranch);

            }
        };
        branchPopUpPanel.setPropertiesEL(new String[]{"id", "code", "num"});

    }

    public Cheques uiety(){
    return null;
    }
    @Override
    public void save() {
                

        super.save();
    }

    public void uiToEty(Cheques item) {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cDatePicker1 = new org.components.controls.CDatePicker();
        cLabel1 = new org.components.controls.CLabel();
        tcode = new org.components.controls.CTextField();
        controlPanel1 = new com.components.custom.ControlPanel();
        tbank = new org.components.controls.CTextField();
        tbranch = new org.components.controls.CTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        cTable1 = new org.components.controls.CTable();
        cdate = new org.components.controls.CDatePicker();

        setLayout(null);

        cLabel1.setText("Code :");
        add(cLabel1);
        cLabel1.setBounds(10, 132, 71, 25);
        add(tcode);
        tcode.setBounds(118, 132, 100, 25);
        add(controlPanel1);
        controlPanel1.setBounds(140, 170, 370, 53);
        add(tbank);
        tbank.setBounds(150, 40, 170, 25);
        add(tbranch);
        tbranch.setBounds(150, 80, 150, 25);

        cTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(cTable1);

        add(jScrollPane1);
        jScrollPane1.setBounds(20, 290, 700, 190);
        add(cdate);
        cdate.setBounds(500, 20, 104, 22);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public String getTabName() {
        return "cheque system";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CDatePicker cDatePicker1;
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CTable cTable1;
    private org.components.controls.CDatePicker cdate;
    private com.components.custom.ControlPanel controlPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CTextField tbank;
    private org.components.controls.CTextField tbranch;
    private org.components.controls.CTextField tcode;
    // End of variables declaration//GEN-END:variables
}
