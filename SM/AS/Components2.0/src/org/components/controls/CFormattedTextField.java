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

import com.components.custom.ActionTask;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import org.biz.app.ui.util.UIEty;
import org.components.parent.controls.PFormatedTextField;

/**
 *
 * @author nano
 */
public class CFormattedTextField extends PFormatedTextField {

    JComponent nextFocusableComponent;
    JComponent previouseFocusedComponent;
    List<ActionTask> actionTasks;
    boolean moveTonextcom = true;
    



    /**
     * Creates new form BeanForm
     */
    public CFormattedTextField() {
        super();        
        init();
//        setInputVerifier(new CInputVerifier());
    }

    public void init() {
        
        
        
        actionTasks = new ArrayList<ActionTask>();



    }

    public void addaction(int idx, ActionTask action) {
        int c = actionTasks.size();

        if (c - 1 < idx) {
            actionTasks.add(action);
            return;
        }
        actionTasks.add(idx, action);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        setPreferredSize(new java.awt.Dimension(6, 25));
    }// </editor-fold>//GEN-END:initComponents



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
