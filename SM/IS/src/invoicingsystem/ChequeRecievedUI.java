/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ChequeRecievedUI.java
 *
 * Created on May 17, 2012, 9:47:49 PM
 */
package invoicingsystem;

import org.components.windows.TabPanelUI;

/**
 *
 * @author jawath
 * 
 */
public class ChequeRecievedUI extends TabPanelUI implements ChequeRecievedController{


    /** Creates new form ChequeRecievedUI */
    public ChequeRecievedUI() {
        initComponents();
    }

    @Override
    public void init() {

        super.init();
        
    }

    @Override
    public void events() {
        super.events();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chequeDetailUI1 = new checkchequingsystem.ChequeDetailUI();
        tcustomer = new org.components.controls.CTextField();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(chequeDetailUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(tcustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(tcustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                .addComponent(chequeDetailUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private checkchequingsystem.ChequeDetailUI chequeDetailUI1;
    private org.components.controls.CTextField tcustomer;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
return  "cheque recieved";
    }
}
/**
 * this ui does not exist alone ..it is a part of customer payments
 * chqs recieved from the customers are put in here
 * enter echeque number and 
 * enter bank 
 * customer is got from interface custoemer to get the thing done
 * maintaining a check registry for the purpose of payments 
 * customer , cheque
 *
 * 917
 *
 *
 *
 * customer pays with cheque
 * data fed :- customer , cheque number , amount date
 * when persisting 
 *          cheque become customer payed state
 *
 *          when cheque date  is ... cheque become bankable cheque
 *       user deposit it to bank -- cheqque become deposited cheque
 *          may be cashed - or return
 *if return returned cheque which can re deposited or unvalid
 * reposit the cheque
 *
 * analysis
 * cheque has many state
 *
 * ch # ,
 *
 * bank deposite slip
 * 
 *
 */