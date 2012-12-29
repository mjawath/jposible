/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.ui.fx.transactions;

import java.io.IOException;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author d
 */
public class FXTHREAd {
     public static void main(String[] args) {

         SwingUtilities.invokeLater(new Runnable() {
             @Override
             public void run() {

                 JFrame fr = new JFrame("Fx Test");
                 fr.setSize(600, 600);

                 final JFXPanel fXPanel = new JFXPanel();

                 fr.getContentPane().add(fXPanel);
                 fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                 fr.setVisible(true);


                 Platform.runLater(new Runnable() {
                     @Override
                     public void run() {
                         try {
                             Parent root = FXMLLoader.load(getClass().getResource("jjj.fxml"));
                             Scene scene = new Scene(root); //scene.getStylesheets().set();
                             fXPanel.setScene(scene);

                         } catch (IOException ex) {
                             ex.printStackTrace();
                         }
                     }
                 });
             }
         });
     }
}
