/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.app.ui.util;

import javax.swing.JOptionPane;

/**
 *
 * @author mjawath
 */

public  class AwtHandler {
  public void handle(Throwable t) {
    // do something here
      System.out.println("awwwwwwwwwwwwwwwwwttttttttttttttt"+t.getMessage());
      JOptionPane.showMessageDialog(null,"ssssssssssssssssssss"+t.getMessage());
  }
}

