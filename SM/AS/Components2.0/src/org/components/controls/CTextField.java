/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jtextfield.java
 *
 * Created on May 4, 2010, 7:39:59 PM
 */
package org.components.controls;

import com.components.custom.CInputVerifier;
import java.awt.event.KeyEvent;
import org.biz.app.ui.util.UIEty;
import org.components.parent.controls.PTextField;

/**
 *
 * @author nano
 */
public class CTextField extends PTextField {

    


    /**
     * Creates new form BeanForm
     */
    public CTextField() {
    super();
//        setInputVerifier(new CInputVerifier());
    }



    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        setPreferredSize(new java.awt.Dimension(6, 25));
    }// </editor-fold>//GEN-END:initComponents

      
      @Override
      protected void processKeyEvent(KeyEvent e) {
//        if(e.getKeyCode()==KeyEvent.VK_ENTER){
//            postActionEvent();
//            return;
//        }
          super.processKeyEvent(e);
      }

      public void setInputVerifier(CInputVerifier inputVerifier) {
//        addActionListener(inputVerifier);
          super.setInputVerifier(inputVerifier);
      }

      public double getDoubleValue0() {

          try {
              String s = null;
              if (this.getText() != null) {
                  s = this.getText().trim();

              }
              return Double.parseDouble(s);
          } catch (Exception e) {
              return 0.0;
          }

      }

      public Double getDoubleValue() {

          try {
              String s = null;
              if (this.getText() != null) {
                  s = this.getText().trim();

              }
              return Double.parseDouble(s);
          } catch (Exception e) {
              return null;
          }

      }

      public void clear() {
          this.setText("");
          this.setToolTipText(null);
      }

      public Number getNumberValue() {
          return UIEty.tcToDouble(this);
      }

     

     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
